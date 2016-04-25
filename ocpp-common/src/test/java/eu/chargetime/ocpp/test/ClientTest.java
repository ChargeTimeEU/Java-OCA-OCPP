package eu.chargetime.ocpp.test;

import eu.chargetime.ocpp.*;
import eu.chargetime.ocpp.model.Request;
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
    private TransmitterEvents events;

    private String aMessage = "Message";
    private String aPayload = "";

    @Mock
    private Transmitter mockedTransmitter;
    @Mock
    private Queue queue;
    @Mock
    private Request request;

    @Before
    public void setup() {
        request = mock(Request.class);
        mockedTransmitter = mock(Transmitter.class);
        queue = mock(Queue.class);
        client = new Client(mockedTransmitter, queue);
    }

    @Test
    public void connect_connects() {
        // Given
        String someUrl = "localhost";

        // When
        client.connect(someUrl);

        // Then
        verify(mockedTransmitter, times(1)).connect(eq(someUrl), anyObject());
    }

    @Test
    public void send_aMessage_isTransmitted() {
        // When
        client.send(request);

        // Then
        verify(mockedTransmitter, times(1)).send(anyString());
    }

    @Test
    public void responseReceived_aMessageWasSend_PromiseIsCompleted() {
        // Given
        String id = "testIdentification";
        doAnswer(invocation -> events = invocation.getArgumentAt(1, TransmitterEvents.class)).when(mockedTransmitter).connect(any(), any());
        when(queue.store(any())).thenReturn(id);

        // When
        client.connect(null);
        CompletableFuture<Request> promise = client.send(request);
        events.receivedMessage(String.format("[3,\"%s\",{\"idTagInfo\": {\"status\":\"Accepted\"}}]", id));

        // Then
        assertThat(promise.isDone(), is(true));
    }
}