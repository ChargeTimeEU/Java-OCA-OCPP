package eu.chargetime.ocpp.test;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

import eu.chargetime.ocpp.*;
import eu.chargetime.ocpp.model.Message;
import eu.chargetime.ocpp.model.Request;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

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

@RunWith(MockitoJUnitRunner.class)
public class CommunicatorTest {

  private Communicator communicator;
  private RadioEvents eventHandler;

  @Mock private Receiver receiver;
  @Mock private Request transactionRelatedRequest;
  @Mock private Request normalRequest;
  @Mock private CommunicatorEvents events;

  @Before
  public void setup() throws Exception {

    when(transactionRelatedRequest.transactionRelated()).thenReturn(true);
    when(normalRequest.transactionRelated()).thenReturn(false);
    doAnswer(invocation -> eventHandler = invocation.getArgumentAt(0, RadioEvents.class))
        .when(receiver)
        .accept(any());

    communicator =
        new Communicator(receiver) {
          @Override
          public <T> T unpackPayload(Object payload, Class<T> type) throws Exception {
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
          protected Message parse(Object message) {
            return null;
          }
        };

    communicator.accept(events);
  }

  @Test
  public void sendCall_radioThrowsNotConnectedException_onErrorIsNotCalled() throws Exception {
    // Given
    String uniqueId = "some id";
    String action = "some action";
    doThrow(new NotConnectedException()).when(receiver).send(anyString());

    // When
    communicator.sendCall(uniqueId, action, transactionRelatedRequest);

    // Then
    verify(events, times(0)).onError(eq(uniqueId), any(), any(), any());
  }

  @Test
  public void sendCall_aNormalRequestAndRadioThrowsNotConnectedException_onErrorIsCalled()
      throws Exception {
    // Given
    String uniqueId = "some id";
    String action = "some action";
    doThrow(new NotConnectedException()).when(receiver).send(anyString());

    // When
    communicator.sendCall(uniqueId, action, normalRequest);

    // Then
    verify(events, times(1)).onError(eq(uniqueId), any(), any(), any());
  }

  @Test
  public void sendCall_transactionRelatedRequestsQueued_sendIsNotCalled() throws Exception {
    // Given
    doThrow(new NotConnectedException()).when(receiver).send(anyString());
    communicator.sendCall("previous id", "previous action", transactionRelatedRequest);

    String uniqueId = "some id";
    String action = "some action";

    // When
    communicator.sendCall(uniqueId, action, transactionRelatedRequest);

    // Then
    // Test implementation is rigged to return unique id as the call.
    verify(receiver, times(0)).send(eq(uniqueId));
  }

  @Test
  public void sendCall_queueNotEmpty_messagesAreProcessInSequence() throws Exception {
    // Given
    String action = "some action";
    String firstId = "first id";

    doThrow(new NotConnectedException()).when(receiver).send(anyString());
    communicator.sendCall(firstId, "previous action", transactionRelatedRequest);
    doNothing().when(receiver).send(anyString());

    String secondId = "second id";

    // When
    communicator.sendCall(secondId, action, transactionRelatedRequest);
    Thread.sleep(1100);

    // Then
    verify(receiver, times(2)).send(eq(firstId));
    verify(receiver, times(1)).send(eq(secondId));
  }

  @Test
  public void connected_transactionRelatedRequestsQueued_sendIsCalled() throws Exception {
    // Given
    doThrow(new NotConnectedException()).when(receiver).send(anyString());
    String uniqueId = "some id";
    String action = "some action";
    communicator.sendCall(uniqueId, action, transactionRelatedRequest);

    // When
    eventHandler.connected();
    Thread.sleep(100);

    // Then
    verify(receiver, times(2)).send(eq(uniqueId));
  }

  @Test
  public void connected_failedOnceBefore_sameRequestIsRetried() throws Exception {
    // Given
    doThrow(new NotConnectedException()).when(receiver).send(anyString());
    String uniqueId = "some id";
    String action = "some action";
    communicator.sendCall(uniqueId, action, transactionRelatedRequest);
    eventHandler.connected();
    Thread.sleep(100);

    // When
    eventHandler.connected();
    Thread.sleep(100);

    // Then
    verify(receiver, times(3)).send(eq(uniqueId));
  }
}
