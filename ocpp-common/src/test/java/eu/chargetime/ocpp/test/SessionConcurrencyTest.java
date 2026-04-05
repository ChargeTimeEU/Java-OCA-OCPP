package eu.chargetime.ocpp.test;

/*
   ChargeTime.eu - Java-OCA-OCPP

   MIT License

   Permission is hereby granted, free of charge, to any person obtaining a copy
   of this software and associated documentation files (the "Software"), to deal
   in the Software without restriction, including without limitation the rights
   to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
   copies of the Software, and to permit persons to whom the Software is
   furnished to do so, subject to the following conditions:

   The above copyright notice and this permission notice shall be included in all
   copies or substantial portions of the Software.

   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
   IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
   FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
   AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
   LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
   SOFTWARE.
*/

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import eu.chargetime.ocpp.*;
import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.TestRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Tests for Session thread safety after removing synchronized from onCall() and adding
 * pendingPromises auto-cleanup.
 */
@RunWith(MockitoJUnitRunner.class)
public class SessionConcurrencyTest {

  private Session session;
  private CommunicatorEvents eventHandler;

  @Mock private Communicator communicator;
  @Mock private Queue queue;
  @Mock private SessionEvents sessionEvents;
  @Mock private Feature feature;
  @Mock private FeatureRepository featureRepository;
  @Mock private PromiseFulfiller fulfiller;

  @Before
  public void setup() throws Exception {
    when(featureRepository.findFeature(any())).thenReturn(Optional.of(feature));
    when(feature.getConfirmationType()).thenAnswer(invocation -> Confirmation.class);
    when(communicator.unpackPayload(any(), any())).thenReturn(new TestRequest());

    session = new Session(communicator, queue, fulfiller, featureRepository);

    doAnswer(invocation -> eventHandler = invocation.getArgument(1, CommunicatorEvents.class))
        .when(communicator)
        .connect(any(), any());
    session.open(null, sessionEvents);
  }

  @Test
  public void concurrentOnCall_allRequestsProcessed() throws Exception {
    int threadCount = 50;
    CountDownLatch startLatch = new CountDownLatch(1);
    CountDownLatch doneLatch = new CountDownLatch(threadCount);
    List<Throwable> errors = Collections.synchronizedList(new ArrayList<>());

    // Fulfiller completes the promise immediately
    doAnswer(
            invocation -> {
              CompletableFuture<Confirmation> future = invocation.getArgument(0);
              if (future != null) {
                Confirmation conf =
                    new Confirmation() {
                      @Override
                      public boolean validate() {
                        return true;
                      }
                    };
                future.complete(conf);
              }
              return null;
            })
        .when(fulfiller)
        .fulfill(any(), any(), any());

    for (int i = 0; i < threadCount; i++) {
      final String id = "call-" + i;
      new Thread(
              () -> {
                try {
                  startLatch.await();
                  eventHandler.onCall(id, "TestAction", "{}");
                } catch (Throwable t) {
                  errors.add(t);
                } finally {
                  doneLatch.countDown();
                }
              })
          .start();
    }

    startLatch.countDown();
    assertTrue("Threads did not complete", doneLatch.await(10, TimeUnit.SECONDS));
    assertTrue("Concurrent onCall threw exceptions: " + errors, errors.isEmpty());

    // All calls should have been dispatched
    verify(fulfiller, times(threadCount)).fulfill(any(), any(), any());
  }

  @Test
  public void onCall_pendingPromiseCleanedUpAfterCompletion() throws Exception {
    // Fulfiller completes the promise immediately
    doAnswer(
            invocation -> {
              CompletableFuture<Confirmation> future = invocation.getArgument(0);
              if (future != null) {
                Confirmation conf =
                    new Confirmation() {
                      @Override
                      public boolean validate() {
                        return true;
                      }
                    };
                future.complete(conf);
              }
              return null;
            })
        .when(fulfiller)
        .fulfill(any(), any(), any());

    eventHandler.onCall("test-id", "TestAction", "{}");

    // Give whenComplete callback time to run
    Thread.sleep(100);

    // Attempting to complete an already-cleaned promise should return false
    boolean result = session.completePendingPromise("test-id", mock(Confirmation.class));
    assertTrue("pendingPromise should have been auto-removed after completion", !result);
  }

  @Test
  public void concurrentOnCallAndCompletePendingPromise_noExceptions() throws Exception {
    int threadCount = 30;
    CountDownLatch startLatch = new CountDownLatch(1);
    CountDownLatch doneLatch = new CountDownLatch(threadCount * 2);
    List<Throwable> errors = Collections.synchronizedList(new ArrayList<>());

    // Fulfiller stores promise but does NOT complete it immediately
    doNothing().when(fulfiller).fulfill(any(), any(), any());

    // Threads that send onCall
    for (int i = 0; i < threadCount; i++) {
      final String id = "async-" + i;
      new Thread(
              () -> {
                try {
                  startLatch.await();
                  eventHandler.onCall(id, "TestAction", "{}");
                } catch (Throwable t) {
                  errors.add(t);
                } finally {
                  doneLatch.countDown();
                }
              })
          .start();
    }

    // Threads that try to complete promises
    for (int i = 0; i < threadCount; i++) {
      final String id = "async-" + i;
      new Thread(
              () -> {
                try {
                  startLatch.await();
                  Thread.sleep(50); // Let onCall add the promise first
                  Confirmation conf =
                      new Confirmation() {
                        @Override
                        public boolean validate() {
                          return true;
                        }
                      };
                  try {
                    session.completePendingPromise(id, conf);
                  } catch (Exception e) {
                    // May fail if promise not yet added — that's OK
                  }
                } catch (Throwable t) {
                  errors.add(t);
                } finally {
                  doneLatch.countDown();
                }
              })
          .start();
    }

    startLatch.countDown();
    assertTrue("Threads did not complete", doneLatch.await(10, TimeUnit.SECONDS));
    assertTrue("Concurrent operations threw exceptions: " + errors, errors.isEmpty());
  }
}
