package eu.chargetime.ocpp.test;

import eu.chargetime.ocpp.*;
import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.feature.profile.Profile;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.TestConfirmation;
import eu.chargetime.ocpp.utilities.TestUtilities;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

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
public class ServerTest extends TestUtilities {

    private static final String LOCALHOST = "localhost";
    private static final int PORT = 42;

    private Server server;
    private Request request;
    private SessionEvents sessionEvents;
    private ListenerEvents listenerEvents;

    @Mock
    private Session session = mock(Session.class);
    @Mock
    private Profile profile = mock(Profile.class);
    @Mock
    private Feature feature = mock(Feature.class);
    @Mock
    private Listener listener = mock(Listener.class);
    @Mock
    private ServerEvents serverEvents = mock(ServerEvents.class);

    @Before
    public void setup() {

        request = () -> false;
        doReturn(request.getClass()).when(feature).getRequestType();
        doReturn(TestConfirmation.class).when(feature).getConfirmationType();
        when(feature.getAction()).thenReturn(null);
        doAnswer(invocation -> listenerEvents = invocation.getArgumentAt(2, ListenerEvents.class)).when(listener).open(anyString(), anyInt(), any());
        doAnswer(invocation -> sessionEvents = invocation.getArgumentAt(0, SessionEvents.class)).when(session).accept(any());

        server = new Server(listener) {
        };

        when(profile.getFeatureList()).thenReturn(aList(feature));
        server.addFeatureProfile(profile);
    }

    @Test
    public void newSession_serverIsListening_sessionIsAccepted() {
        // Given
        server.open(LOCALHOST, PORT, serverEvents);

        // When
        listenerEvents.newSession(session);

        // Then
        verify(session, times(1)).accept(any());
    }

    @Test
    public void newSession_serverIsListening_callbackWithIndex0() {
        // Given
        server.open(LOCALHOST, PORT, serverEvents);

        // When
        listenerEvents.newSession(session);

        // Then
        verify(serverEvents, times(1)).newSession(eq(0));
    }

    @Test
    public void send_aMessage_isCommunicated() throws Exception {
        // Given
        String someUniqueId = "some id";
        int sessionIndex = 0;

        when(session.sendRequest(any(), any())).thenReturn(someUniqueId);
        server.open(LOCALHOST, PORT, serverEvents);
        listenerEvents.newSession(session);

        // When
        server.send(sessionIndex, request);

        // Then
        verify(session, times(1)).sendRequest(anyString(), eq(request));
    }

    @Test
    public void handleRequest_callsFeatureHandleRequest() {
        // Given
        server.open(LOCALHOST, PORT, serverEvents);
        listenerEvents.newSession(session);

        // When
        sessionEvents.handleRequest(request);

        // Then
        verify(feature, times(1)).handleRequest(eq(0), eq(request));
    }

}