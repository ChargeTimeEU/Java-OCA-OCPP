package eu.chargetime.ocpp.test;

import eu.chargetime.ocpp.*;
import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.TestRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

/*
 ChargeTime.eu - Java-OCA-OCPP
 Copyright (C) 2015-2016 Thomas Volden <tv@chargetime.eu>

 MIT License

 Copyright (c) 2016 Thomas Volden

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
public class SessionTest {
    private Session session;

    private CommunicatorEvents eventHandler;

    @Mock
    private Communicator communicator;
    @Mock
    private Queue queue;
    @Mock
    private SessionEvents sessionEvents;
    @Mock
    private Feature feature;

    @Before
    public void setup() throws Exception {
        when(sessionEvents.findFeatureByAction(any())).thenReturn(feature);
        session = new Session(communicator, queue, false);
        doAnswer(invocation -> eventHandler = invocation.getArgumentAt(1, CommunicatorEvents.class)).when(communicator).connect(any(), any());
        session.open(null, sessionEvents);
    }

    @Test
    public void sendRequest_getUniqueIdReturnedFromQueue() {
        // Given
        String someId = "test id";
        when(queue.store(any())).thenReturn(someId);

        // When
        String id = session.sendRequest(null, null);

        // Then
        assertThat(id, equalTo(someId));
    }

    @Test
    public void sendRequest_sendRequestToCommunicator() {
        // Given
        String  someAction      = "Test action";
        Request someRequest     = new Request() {
            @Override
            public boolean transactionRelated() {
                return false;
            }

            @Override
            public boolean validate() {
                return false;
            }
        };

        // When
        session.sendRequest(someAction, someRequest);

        // Then
        verify(communicator, times(1)).sendCall(anyString(), eq(someAction), eq(someRequest));
    }

    @Test
    public void sendRequest_fixedUniqueId_sendsUniqueIdToCommunicator() {
        // Given
        String someUniqueId = "test id";
        when(queue.store(any())).thenReturn(someUniqueId);

        // When
        session.sendRequest(null, null);

        // Then
        verify(communicator, times(1)).sendCall(eq(someUniqueId), anyString(), any());
    }

    @Test
    public void sendConfirmation_sendsConfirmationToCommunicator() {
        // Given
        Confirmation conf = () -> false;
        String someUniqueId = "Some id";
        String action = "Some action";

        // When
        session.sendConfirmation(someUniqueId, action, conf);

        // Then
        verify(communicator, times(1)).sendCallResult(eq(someUniqueId), eq(action), eq(conf));
    }

    @Test
    public void open_connectsViaCommunicator() {
        // Given
        String someUri = "localhost";

        // When
        session.open(someUri, null);

        // Then
        verify(communicator, times(1)).connect(eq(someUri), any());
    }

    @Test
    public void onCall_unhandledCallback_callSendCallError() throws Exception {
        // Given
        String someId = "Some id";
        when(sessionEvents.handleRequest(any())).thenReturn(null);
        when(communicator.unpackPayload(any(), any())).thenReturn(new TestRequest());

        // When
        eventHandler.onCall(someId, null, null);

        // then
        verify(communicator, times(1)).sendCallError(eq(someId), anyString(), anyString(), anyString());
    }

    @Test
    public void onCall_handledCallback_callSendCallResult() throws Exception {
        // Given
        String someId = "Some id";
        Confirmation aConfirmation = () -> true;
        when(sessionEvents.handleRequest(any())).thenReturn(aConfirmation);
        when(communicator.unpackPayload(any(), any())).thenReturn(new TestRequest());

        // When
        eventHandler.onCall(someId, null, null);

        // then
        verify(communicator, times(1)).sendCallResult(anyString(), anyString(), eq(aConfirmation));
    }

    @Test
    public void onCall_callbackThrowsException_callSendCallResult() throws Exception {
        // Given
        String someId = "Some id";
        when(sessionEvents.handleRequest(any())).thenThrow(Exception.class);
        when(communicator.unpackPayload(any(), any())).thenReturn(new TestRequest());

        // When
        eventHandler.onCall(someId, null, null);

        // then
        verify(communicator, times(1)).sendCallError(eq(someId), anyString(), anyString(), anyString());
    }

    @Test
    public void close_disconnects() {
        // When
        session.close();

        // Then
        verify(communicator, times(1)).disconnect();
    }

    @Test
    public void onCall_unknownAction_callSendCallError() {
        // Given
        String someId = "Some id";
        when(sessionEvents.findFeatureByAction(any())).thenReturn(null);

        // When
        eventHandler.onCall(someId, null, null);

        // Then
        verify(communicator, times(1)).sendCallError(eq(someId), anyString(), anyString(), anyString());
    }

    @Test
    public void onCall_sessionAccepted_callIsForwarded() throws Exception {
        // Given
        String someId = "Some id";
        session.accept(sessionEvents);
        when(communicator.unpackPayload(any(), any())).thenReturn(new TestRequest());

        // When
        eventHandler.onCall(someId, null, null);

        // Then
        verify(sessionEvents, times(1)).handleRequest(any());
    }
}