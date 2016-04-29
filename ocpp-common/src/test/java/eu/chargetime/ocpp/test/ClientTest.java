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
    private TransmitterEvents events;
    private Feature feature;

    @Mock
    private Queue queue;
    @Mock
    private Communicator communicator;
    @Mock
    private Profile profile;

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

        queue = mock(Queue.class);
        communicator = mock(Communicator.class);
        profile = mock(Profile.class);
        client = new Client(queue, communicator);

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
        verify(communicator, times(1)).connect(eq(someUrl), anyObject());
    }

    @Test
    public void send_aMessage_isCommunicated() {
        // When
        client.send(request);

        // Then
        verify(communicator, times(1)).sendCall(anyString(), anyString(), eq(request));
    }

    // Proof of much needed refactoring
    public void responseReceived_aMessageWasSend_PromiseIsCompleted() {
        // Given
        String id = "testIdentification";
        //doAnswer(invocation -> events = invocation.getArgumentAt(1, TransmitterEvents.class)).when(mockedTransmitter).connect(any(), any());
        when(queue.store(any())).thenReturn(id);
        when(queue.restoreRequest(any())).thenReturn(request);
        when(communicator.unpackPayload(anyString(), any())).thenReturn(mock(Confirmation.class));

        // When
        client.connect(null);
        CompletableFuture<Confirmation> promise = client.send(request);
        events.receivedMessage(String.format("[3,\"%s\",{\"idTagInfo\": {\"status\":\"Accepted\"}}]", id));

        // Then
        assertThat(promise.isDone(), is(true));
    }
}