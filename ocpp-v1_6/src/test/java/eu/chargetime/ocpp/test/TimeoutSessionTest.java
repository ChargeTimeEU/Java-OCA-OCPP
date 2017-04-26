package eu.chargetime.ocpp.test;

import eu.chargetime.ocpp.*;
import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.model.TestConfirmation;
import eu.chargetime.ocpp.model.TestRequest;
import eu.chargetime.ocpp.utilities.TimeoutTimer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/*
    ChargeTime.eu - Java-OCA-OCPP
    
    MIT License

    Copyright (C) 2016 Thomas Volden <tv@chargetime.eu>

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
public class TimeoutSessionTest {

    private TimeoutSession session;
    private CommunicatorEvents eventHandler;

    @Mock
    private Communicator communicator;
    @Mock
    private Queue queue;
    @Mock
    private SessionEvents sessionEvents;
    @Mock
    private Feature feature;
    @Mock
    private TimeoutTimer timeoutTimer;

    @Before
    public void setup() throws Exception {
        when(sessionEvents.findFeatureByAction(any())).thenReturn(feature);
        when(sessionEvents.findFeatureByRequest(any())).thenReturn(feature);

        session = new TimeoutSession(communicator, queue, false);
        doAnswer(invocation -> eventHandler = invocation.getArgumentAt(1, CommunicatorEvents.class)).when(communicator).connect(any(), any());
        doAnswer(invocation -> eventHandler = invocation.getArgumentAt(0, CommunicatorEvents.class)).when(communicator).accept(any());

        session.setTimeoutTimer(timeoutTimer);
    }

    @Test
    public void onConnected_opened_beginTimeout() throws Exception {
        // Given
        session.open(null, sessionEvents);

        // When
        eventHandler.onConnected();

        // Then
        verify(timeoutTimer, times(1)).begin();
    }

    @Test
    public void onDisconnected_opened_endTimeout() throws Exception {
        // Given
        session.open(null, sessionEvents);

        // When
        eventHandler.onDisconnected();

        // Then
        verify(timeoutTimer, times(1)).end();
    }

    @Test
    public void onConnected_accepted_beginTimeout() throws Exception {
        // Given
        session.accept(sessionEvents);

        // When
        eventHandler.onConnected();

        // Then
        verify(timeoutTimer, times(1)).begin();
    }

    @Test
    public void onDisconnected_accepted_endTimeout() throws Exception {
        // Given
        session.accept(sessionEvents);

        // When
        eventHandler.onDisconnected();

        // Then
        verify(timeoutTimer, times(1)).end();
    }

    @Test
    public void onCall_request_resetTimeout() throws Exception {
        // Given
        session.open(null, sessionEvents);
        when(communicator.unpackPayload(any(), any())).thenReturn(new TestRequest());

        // When
        eventHandler.onCall("", null, null);

        // Then
        verify(timeoutTimer, times(1)).reset();
    }

    @Test
    public void onCall_confirmation_resetTimeout() throws Exception {
        // Given
        session.open(null, sessionEvents);
        when(communicator.unpackPayload(any(), any())).thenReturn(new TestConfirmation());

        // When
        eventHandler.onCallResult("", null, null);

        // Then
        verify(timeoutTimer, times(1)).reset();
    }
}