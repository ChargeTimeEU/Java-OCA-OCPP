package eu.chargetime.ocpp.test;

import static org.mockito.Mockito.*;

import eu.chargetime.ocpp.*;
import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.SessionInformation;
import java.util.Optional;
import java.util.UUID;
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
public class ServerTest {

  private static final String LOCALHOST = "localhost";
  private static final int PORT = 42;

  private Server server;
  private SessionEvents sessionEvents;
  private ListenerEvents listenerEvents;
  private UUID sessionIndex;

  @Mock private Session session;
  @Mock private Feature feature;
  @Mock private Listener listener;
  @Mock private ServerEvents serverEvents;
  @Mock private Request request;
  @Mock private SessionInformation information;
  @Mock private IFeatureRepository featureRepository;
  @Mock private IPromiseRepository promiseRepository;

  @Before
  public void setup() {
    UUID sessionId = UUID.randomUUID();
    when(request.validate()).thenReturn(true);
    when(session.getSessionId()).thenReturn(sessionId);
    doAnswer(invocation -> listenerEvents = invocation.getArgumentAt(2, ListenerEvents.class))
        .when(listener)
        .open(anyString(), anyInt(), any());
    doAnswer(invocation -> sessionEvents = invocation.getArgumentAt(0, SessionEvents.class))
        .when(session)
        .accept(any());
    doAnswer(invocation -> sessionIndex = invocation.getArgumentAt(0, UUID.class))
        .when(serverEvents)
        .newSession(any(), any());

    when(featureRepository.findFeature(any())).thenReturn(Optional.of(feature));
    server = new Server(listener, featureRepository, promiseRepository);
  }

  @Test
  public void newSession_serverIsListening_sessionIsAccepted() {
    // Given
    server.open(LOCALHOST, PORT, serverEvents);

    // When
    listenerEvents.newSession(session, information);

    // Then
    verify(session, times(1)).accept(any());
  }

  @Test
  public void newSession_serverIsListening_callbackWithIndex0() {
    // Given
    server.open(LOCALHOST, PORT, serverEvents);

    // When
    listenerEvents.newSession(session, information);

    // Then
    verify(serverEvents, times(1)).newSession(any(UUID.class), eq(information));
  }

  @Test
  public void send_aMessage_isCommunicated() throws Exception {
    // Given
    server.open(LOCALHOST, PORT, serverEvents);
    listenerEvents.newSession(session, information);

    // When
    server.send(sessionIndex, request);

    // Then
    verify(session, times(1)).sendRequest(anyString(), eq(request), anyString());
  }

  @Test
  public void handleRequest_callsFeatureHandleRequest() throws UnsupportedFeatureException {
    // Given
    server.open(LOCALHOST, PORT, serverEvents);
    listenerEvents.newSession(session, information);

    // When
    sessionEvents.handleRequest(request);

    // Then
    verify(feature, times(1)).handleRequest(any(UUID.class), eq(request));
  }

  @Test
  public void send_aMessage_validatesMessage() throws Exception {
    // Given
    server.open(LOCALHOST, PORT, serverEvents);
    listenerEvents.newSession(session, information);

    // When
    server.send(sessionIndex, request);

    // Then
    verify(request, times(1)).validate();
  }
}
