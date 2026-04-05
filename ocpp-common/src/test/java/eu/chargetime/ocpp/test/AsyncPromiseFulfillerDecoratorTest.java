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

import eu.chargetime.ocpp.AsyncPromiseFulfillerDecorator;
import eu.chargetime.ocpp.PromiseFulfiller;
import eu.chargetime.ocpp.SessionEvents;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AsyncPromiseFulfillerDecoratorTest {

  @Mock private PromiseFulfiller innerFulfiller;
  @Mock private SessionEvents sessionEvents;
  @Mock private Request request;

  private AsyncPromiseFulfillerDecorator decorator;

  @Before
  public void setup() {
    // Reset to a fresh default executor before each test to avoid cross-test pollution
    AsyncPromiseFulfillerDecorator.setExecutor(createFreshExecutor());
    decorator = new AsyncPromiseFulfillerDecorator(innerFulfiller);
  }

  private static ExecutorService createFreshExecutor() {
    int coreSize = Runtime.getRuntime().availableProcessors();
    int maxSize = coreSize * 2;
    return new java.util.concurrent.ThreadPoolExecutor(
        coreSize,
        maxSize,
        60L,
        TimeUnit.SECONDS,
        new java.util.concurrent.LinkedBlockingQueue<>(1000),
        new java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy());
  }

  @Test
  public void fulfill_delegatesToInnerFulfiller() throws Exception {
    CompletableFuture<Confirmation> promise = new CompletableFuture<>();
    CountDownLatch latch = new CountDownLatch(1);

    doAnswer(
            invocation -> {
              latch.countDown();
              return null;
            })
        .when(innerFulfiller)
        .fulfill(any(), any(), any());

    decorator.fulfill(promise, sessionEvents, request);

    assertTrue("Inner fulfiller was not called within timeout", latch.await(5, TimeUnit.SECONDS));
    verify(innerFulfiller).fulfill(promise, sessionEvents, request);
  }

  @Test
  public void fulfill_executesAsynchronously_doesNotBlockCaller() throws Exception {
    CountDownLatch blockingLatch = new CountDownLatch(1);
    CountDownLatch callerReturned = new CountDownLatch(1);

    doAnswer(
            invocation -> {
              // Block the inner fulfiller until we signal
              blockingLatch.await(5, TimeUnit.SECONDS);
              return null;
            })
        .when(innerFulfiller)
        .fulfill(any(), any(), any());

    // fulfill() should return immediately without blocking
    decorator.fulfill(null, sessionEvents, request);
    callerReturned.countDown();

    assertTrue("fulfill() blocked the caller thread", callerReturned.getCount() == 0);

    // Release the blocked task
    blockingLatch.countDown();
  }

  @Test
  public void fulfill_withCustomExecutor_usesCustomExecutor() throws Exception {
    ExecutorService customExecutor = Executors.newSingleThreadExecutor();
    AsyncPromiseFulfillerDecorator.setExecutor(customExecutor);

    CountDownLatch latch = new CountDownLatch(1);
    doAnswer(
            invocation -> {
              latch.countDown();
              return null;
            })
        .when(innerFulfiller)
        .fulfill(any(), any(), any());

    decorator.fulfill(null, sessionEvents, request);

    assertTrue("Task was not executed on custom executor", latch.await(5, TimeUnit.SECONDS));
    verify(innerFulfiller).fulfill(null, sessionEvents, request);

    customExecutor.shutdown();
  }

  @Test
  public void fulfill_multipleConcurrentCalls_allExecuted() throws Exception {
    int callCount = 20;
    CountDownLatch latch = new CountDownLatch(callCount);

    doAnswer(
            invocation -> {
              latch.countDown();
              return null;
            })
        .when(innerFulfiller)
        .fulfill(any(), any(), any());

    for (int i = 0; i < callCount; i++) {
      decorator.fulfill(null, sessionEvents, request);
    }

    assertTrue("Not all concurrent fulfill calls were executed", latch.await(10, TimeUnit.SECONDS));
    verify(innerFulfiller, times(callCount)).fulfill(any(), any(), any());
  }

  @Test
  public void fulfill_whenInnerFulfillerThrows_doesNotCrashExecutor() throws Exception {
    doThrow(new RuntimeException("handler error"))
        .when(innerFulfiller)
        .fulfill(any(), any(), any());

    // First call triggers exception
    decorator.fulfill(null, sessionEvents, request);
    Thread.sleep(200);

    // Second call should still work (executor not dead)
    CountDownLatch latch = new CountDownLatch(1);
    doAnswer(
            invocation -> {
              latch.countDown();
              return null;
            })
        .when(innerFulfiller)
        .fulfill(any(), any(), any());

    decorator.fulfill(null, sessionEvents, request);

    assertTrue("Executor died after exception in fulfiller", latch.await(5, TimeUnit.SECONDS));
  }
}
