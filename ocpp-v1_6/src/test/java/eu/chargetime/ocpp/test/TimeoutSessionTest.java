package eu.chargetime.ocpp.test;

import eu.chargetime.ocpp.*;
import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.model.TestRequest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

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
public class TimeoutSessionTest {

    private final long TIMEOUT = 10;
    private TimeoutSession session;
    private CommunicatorEvents eventHandler;

    @Mock
    Communicator communicator = mock(Communicator.class);
    @Mock
    Queue queue = mock(Queue.class);
    @Mock
    SessionEvents sessionEvents = mock(SessionEvents.class);
    @Mock
    Feature feature = mock(Feature.class);

    @Before
    public void setup() throws Exception {
        when(sessionEvents.findFeatureByAction(any())).thenReturn(feature);
        session = new TimeoutSession(communicator, queue, TIMEOUT);
        doAnswer(invocation -> eventHandler = invocation.getArgumentAt(1, CommunicatorEvents.class)).when(communicator).connect(any(), any());
        doAnswer(invocation -> eventHandler = invocation.getArgumentAt(0, CommunicatorEvents.class)).when(communicator).accept(any());
    }

    @Test
    public void timeoutExceeded_openAndConnected_handleConnectionClosed() throws Exception {
        // Given
        session.open(null, sessionEvents);
        eventHandler.onConnected();

        // When
        Thread.sleep(TIMEOUT + 2);

        // Then
        verify(sessionEvents, times(1)).handleConnectionClosed();
    }

    @Test
    public void timeoutExceeded_acceptAndConnected_handleConnectionClosed() throws Exception {
        // Given
        session.accept(sessionEvents);
        eventHandler.onConnected();

        // When
        Thread.sleep(TIMEOUT + 2);

        // Then
        verify(sessionEvents, times(1)).handleConnectionClosed();
    }

    @Test
    public void timeoutExceeded_openAndConnected_communicatorDisconnect() throws Exception {
        // Given
        session.open(null, sessionEvents);
        eventHandler.onConnected();

        // When
        Thread.sleep(TIMEOUT + 2);

        // Then
        verify(communicator, times(1)).disconnect();
    }

    @Test
    public void timeoutExceeded_acceptAndConnected_communicatorDisconnect() throws Exception {
        // Given
        session.accept(sessionEvents);
        eventHandler.onConnected();

        // When
        Thread.sleep(TIMEOUT + 2);

        // Then
        verify(communicator, times(1)).disconnect();
    }

    @Test
    public void handleRequest_openAndConnected_disconnectNotCalled() throws Exception {
        // Given
        when(communicator.unpackPayload(any(), any())).thenReturn(new TestRequest());
        session.open(null, sessionEvents);
        eventHandler.onConnected();
        Thread.sleep(TIMEOUT / 2);

        // When
        eventHandler.onCall("", null, null);
        Thread.sleep(TIMEOUT / 2);

        // Then
        verify(communicator, never()).disconnect();
    }

    @Test
    public void handleRequest_openAndConnected_handleConnectionClosedNotCalled() throws Exception {
        // Given
        when(communicator.unpackPayload(any(), any())).thenReturn(new TestRequest());
        session.open(null, sessionEvents);
        eventHandler.onConnected();
        Thread.sleep(TIMEOUT / 2);

        // When
        eventHandler.onCall("", null, null);
        Thread.sleep(TIMEOUT / 2);

        // Then
        verify(sessionEvents, never()).handleConnectionClosed();
    }
}