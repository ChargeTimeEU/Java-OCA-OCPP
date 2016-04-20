package eu.chargetime.ocpp.test;

import eu.chargetime.ocpp.Client;
import eu.chargetime.ocpp.Transmitter;
import eu.chargetime.ocpp.TransmitterEvents;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.concurrent.CompletableFuture;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by Thomas Volden on 20-Apr-16.
 */
public class ClientTest
{
    Client client;
    TransmitterEvents events;

    @Mock
    Transmitter mockedTransmitter;

    String aMessage = "Message";
    String aPayload = "";

    @Before
    public void setup() {
        mockedTransmitter = mock(Transmitter.class);

        client = new Client(mockedTransmitter);
    }

    @Test
    public void connect_callsAdapter() {
        // Given
        String someUrl = "localhost";

        // When
        client.connect(someUrl);

        // Then
        verify(mockedTransmitter, times(1)).connect(eq(someUrl), anyObject());
    }

    @Test
    public void send_message_callsAdapter() {

        // When
        client.send(aMessage, aPayload);

        // Then
        verify(mockedTransmitter, times(1)).send(anyString());
    }

    public void messageReceived_sendMessage_completesPromis() {
        // Given
        doAnswer(invocation -> events = invocation.getArgumentAt(1, TransmitterEvents.class)).when(mockedTransmitter).connect(any(), any());
        client.connect("");
        CompletableFuture<String> promis = client.send(aMessage, aPayload);

        // When
        events.receivedMessage("[3,\"b0e21938-765d-4424-9f61-5064bdae3371\",{\"idTagInfo\": {\"status\":\"Accepted\"}}]");

        // Then
        assertThat(promis.isDone(), is(true));
    }

}