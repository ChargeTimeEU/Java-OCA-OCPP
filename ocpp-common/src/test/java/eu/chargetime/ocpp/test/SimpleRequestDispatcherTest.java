package eu.chargetime.ocpp.test;
/*
   ChargeTime.eu - Java-OCA-OCPP

   MIT License

   Copyright (C) 2016-2018 Thomas Volden <tv@chargetime.eu>

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

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import eu.chargetime.ocpp.SessionEvents;
import eu.chargetime.ocpp.SimplePromiseFulfiller;
import eu.chargetime.ocpp.UnsupportedFeatureException;
import eu.chargetime.ocpp.model.Confirmation;
import java.util.concurrent.CompletableFuture;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SimpleRequestDispatcherTest {

  private SimplePromiseFulfiller sut;

  @Mock private SessionEvents eventsMock;

  public SimpleRequestDispatcherTest() {
    sut = new SimplePromiseFulfiller();
  }

  @Test
  public void fulfill_throwsException_completesWithException() throws UnsupportedFeatureException {
    // Given
    RuntimeException expectedException = new RuntimeException();
    when(eventsMock.handleRequest(any())).thenThrow(expectedException);
    CompletableFuture<Confirmation> promise = new CompletableFuture<>();

    final Throwable[] result = new Throwable[1];
    promise.whenComplete((confirmation, throwable) -> result[0] = throwable);

    // When
    sut.fulfill(promise, eventsMock, null);

    // Then
    assertThat(result[0], is(expectedException));
  }
}
