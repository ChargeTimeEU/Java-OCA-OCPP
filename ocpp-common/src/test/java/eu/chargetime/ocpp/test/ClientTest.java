package eu.chargetime.ocpp.test;

import eu.chargetime.ocpp.*;
import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.feature.profile.Profile;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.concurrent.CompletableFuture;

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
    private Feature feature;
    private SessionEvents events;

    @Mock
    private Session session = mock(Session.class);
    @Mock
    private Profile profile = mock(Profile.class);

    @Before
    public void setup() {
        request = new Request() {};
        feature = new Feature() {
            @Override
            public Class<? extends Request> getRequestType() {
                return request.getClass();
            }

            @Override
            public Class<? extends Confirmation> getConfirmationType() {
                return Confirmation.class;
            }

            @Override
            public String getAction() {
                return null;
            }
        };

        doAnswer(invocation -> events = invocation.getArgumentAt(1, SessionEvents.class)).when(session).open(any(), any());
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
        when(session.sendRequest(anyString(), any())).thenReturn(someUniqueId);

        // When
        client.connect(null);
        CompletableFuture<Confirmation> promise = client.send(request);
        events.handleConfirmation(someUniqueId, null);

        // Then
        assertThat(promise.isDone(), is(true));
    }
}