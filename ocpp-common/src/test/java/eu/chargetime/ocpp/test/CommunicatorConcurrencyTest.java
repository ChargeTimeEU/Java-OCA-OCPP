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
import static org.mockito.Mockito.lenient;

import eu.chargetime.ocpp.*;
import eu.chargetime.ocpp.model.Message;
import eu.chargetime.ocpp.model.Request;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Tests for thread safety of Communicator after ConcurrentLinkedDeque and AtomicBoolean changes.
 */
@RunWith(MockitoJUnitRunner.class)
public class CommunicatorConcurrencyTest {

  private Communicator communicator;
  private RadioEvents eventHandler;
  private AtomicInteger sendCount;

  @Mock private Receiver receiver;
  @Mock private CommunicatorEvents events;

  @Before
  public void setup() throws Exception {
    sendCount = new AtomicInteger(0);
    doAnswer(invocation -> eventHandler = invocation.getArgument(0, RadioEvents.class))
        .when(receiver)
        .accept(any());

    communicator =
        new Communicator(receiver, true) {
          @Override
          public <T> T unpackPayload(Object payload, Class<T> type) {
            return null;
          }

          @Override
          public Object packPayload(Object payload) {
            return null;
          }

          @Override
          protected Object makeCallResult(String uniqueId, String action, Object payload) {
            return null;
          }

          @Override
          protected Object makeCall(String uniqueId, String action, Object payload) {
            return uniqueId;
          }

          @Override
          protected Object makeCallError(
              String uniqueId, String action, String errorCode, String errorDescription) {
            return null;
          }

          @Override
          protected Object makeCallResultError(
              String uniqueId, String action, String errorCode, String errorDescription) {
            return null;
          }

          @Override
          protected Object makeSend(String uniqueId, String action, Object payload) {
            return uniqueId;
          }

          @Override
          protected Message parse(Object message) {
            return null;
          }
        };
    communicator.accept(events);
  }

  @Test
  public void concurrentSendCall_transactionRelated_noExceptions() throws Exception {
    // Radio is closed, so all transaction-related requests go to queue
    when(receiver.isClosed()).thenReturn(true);

    int threadCount = 50;
    CountDownLatch startLatch = new CountDownLatch(1);
    CountDownLatch doneLatch = new CountDownLatch(threadCount);
    List<Throwable> errors = Collections.synchronizedList(new ArrayList<>());

    for (int i = 0; i < threadCount; i++) {
      final int idx = i;
      new Thread(
              () -> {
                try {
                  Request txRequest = mock(Request.class);
                  when(txRequest.transactionRelated()).thenReturn(true);
                  startLatch.await();
                  communicator.sendCall("id-" + idx, "action", txRequest);
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
    assertTrue("Concurrent sendCall threw exceptions: " + errors, errors.isEmpty());
  }

  @Test
  public void concurrentSendCallAndConnect_noExceptions() throws Exception {
    // Start with closed radio so requests queue up
    when(receiver.isClosed()).thenReturn(true);

    int threadCount = 20;
    CountDownLatch startLatch = new CountDownLatch(1);
    CountDownLatch doneLatch = new CountDownLatch(threadCount + 1);
    List<Throwable> errors = Collections.synchronizedList(new ArrayList<>());

    // Threads that queue transaction-related requests
    for (int i = 0; i < threadCount; i++) {
      final int idx = i;
      new Thread(
              () -> {
                try {
                  Request txRequest = mock(Request.class);
                  when(txRequest.transactionRelated()).thenReturn(true);
                  startLatch.await();
                  communicator.sendCall("id-" + idx, "action", txRequest);
                } catch (Throwable t) {
                  errors.add(t);
                } finally {
                  doneLatch.countDown();
                }
              })
          .start();
    }

    // Thread that triggers connection (processes queue)
    new Thread(
            () -> {
              try {
                startLatch.await();
                Thread.sleep(50); // Let some requests queue first
                lenient().when(receiver.isClosed()).thenReturn(false);
                eventHandler.connected();
              } catch (Throwable t) {
                errors.add(t);
              } finally {
                doneLatch.countDown();
              }
            })
        .start();

    startLatch.countDown();
    assertTrue("Threads did not complete", doneLatch.await(10, TimeUnit.SECONDS));
    assertTrue("Concurrent sendCall+connect threw exceptions: " + errors, errors.isEmpty());
  }
}
