package eu.chargetime.ocpp.v1_6.test;

import eu.chargetime.ocpp.Transmitter;
import eu.chargetime.ocpp.v1_6.WebSocketConnection;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.net.URI;
import java.util.concurrent.CompletableFuture;

import static org.hamcrest.core.Is.is;

public class WebSocketConnectionTest
{
    WebSocketConnection webSocketConnection;
    @Mock
    Transmitter transmitter;

    @Before
    public void setup() throws Exception
    {
        webSocketConnection = new WebSocketConnection(URI.create("localhost"), transmitter);
    }

    @Test
    public void sendRequest_BootNotification_RequestTransmitted()
    {
        // Given
        String request = "Boot";

        // When
        webSocketConnection.sendMessage(request);

        // Then
        // Mock
    }

}