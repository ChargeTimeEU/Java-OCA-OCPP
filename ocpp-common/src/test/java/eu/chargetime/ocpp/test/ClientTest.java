package eu.chargetime.ocpp.test;

import eu.chargetime.ocpp.*;
import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.feature.profile.Profile;
import eu.chargetime.ocpp.model.test.TestConfirmation;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.concurrent.CompletableFuture;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by Thomas Volden on 20-Apr-16.
 */
public class ClientTest
{
    private Client client;
    private Request request;
    private SessionEvents eventHandler;

    @Mock
    private Session session = mock(Session.class);
    @Mock
    private Profile profile = mock(Profile.class);
    @Mock
    private Feature feature = mock(Feature.class);

    @Before
    public void setup() {
        request = new Request() {};
        doReturn(request.getClass()).when(feature).getRequestType();
        doReturn(TestConfirmation.class).when(feature).getConfirmationType();
        when(feature.getAction()).thenReturn(null);
        doAnswer(invocation -> eventHandler = invocation.getArgumentAt(1, SessionEvents.class)).when(session).open(any(), any());

        client = new Client(session);

        when(profile.getFeatureList()).thenReturn(aList(feature));
        client.addFeatureProfile(profile);
    }

    private <T> T[] aList(T... objects) {
        return objects;
    }

    @Test
    public void connect_connects() {
        // Given
        String someUrl = "localhost";

        // When
        client.connect(someUrl);

        // Then
        verify(session, times(1)).open(eq(someUrl), anyObject());
    }

    @Test
    public void send_aMessage_isCommunicated() {
        // When
        client.send(request);

        // Then
        verify(session, times(1)).sendRequest(anyString(), eq(request));
    }

    @Test
    public void responseReceived_aMessageWasSend_PromiseIsCompleted() {
        // Given
        String someUniqueId = "Some id";
        when(session.sendRequest(any(), any())).thenReturn(someUniqueId);

        // When
        client.connect(null);
        CompletableFuture<Confirmation> promise = client.send(request);
        eventHandler.handleConfirmation(someUniqueId, null);

        // Then
        assertThat(promise.isDone(), is(true));
    }

    @Test
    public void handleRequest_returnsConfirmation() {
        // Given
        client.connect(null);
        when(feature.handleRequest(request)).thenReturn(new TestConfirmation());

        // When
        Confirmation conf = eventHandler.handleRequest(request);

        // Then
        assertThat(conf, instanceOf(TestConfirmation.class));
    }

    @Test
    public void handleRequest_callsFeatureHandleRequest() {
        //Given
        client.connect(null);

        // When
        eventHandler.handleRequest(request);

        // Then
        verify(feature, times(1)).handleRequest(eq(request));
    }

}