package eu.chargetime.ocpp.test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

import eu.chargetime.ocpp.Queue;
import eu.chargetime.ocpp.model.Request;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;

/*
ChargeTime.eu - Java-OCA-OCPP
Copyright (C) 2015-2016 Thomas Volden <tv@chargetime.eu>

MIT License

Copyright (C) 2016-2018 Thomas Volden

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
public class QueueTest {
  private Queue queue;

  @Before
  public void setUp() throws Exception {
    queue = new Queue();
  }

  @Test
  public void addRequest_getTicket() {
    // Given
    Request request = mock(Request.class);

    // When
    String ticket = queue.store(request);

    // Then
    assertThat(ticket, is(notNullValue()));
  }

  @Test
  public void turnInTicket_getRequest() {
    // Given
    Request request = mock(Request.class);
    String ticket = queue.store(request);

    // When
    Request result = queue.restoreRequest(ticket).get();

    // Then
    assertThat(result, equalTo(request));
  }

  @Test
  public void turnInTicket_emptyQueue_getNull() {
    // Given
    String invalidTicket = "Invalid";

    // When
    Optional<Request> result = queue.restoreRequest(invalidTicket);

    // Then
    assertThat(result, is(Optional.empty()));
  }

  @Test
  public void turnInTicket_invalidTicket_getNull() {
    // Given
    Request someRequest = mock(Request.class);
    String invalidTicket = "Invalid";

    // When
    queue.store(someRequest);
    Optional<Request> result = queue.restoreRequest(invalidTicket);

    // Then
    assertThat(result, is(Optional.empty()));
  }

  @Test
  public void turnInTicket_ticketAlreadyTurnedIn_getNull() {
    // Given
    Request someRequest = mock(Request.class);
    String ticket = queue.store(someRequest);

    // When
    queue.restoreRequest(ticket);
    Optional<Request> result = queue.restoreRequest(ticket);

    // Then
    assertThat(result, is(Optional.empty()));
  }
}
