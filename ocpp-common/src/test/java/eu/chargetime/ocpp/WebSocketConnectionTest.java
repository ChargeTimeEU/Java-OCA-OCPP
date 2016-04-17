package eu.chargetime.ocpp;

import eu.chargetime.ocpp.v1_6.WebSocketConnection;
import org.junit.Before;
import org.junit.Test;

import java.net.URI;
import java.util.concurrent.CompletableFuture;

import static org.hamcrest.core.Is.is;

public class WebSocketConnectionTest
{
    WebSocketConnection webSocketConnection;

    @Before
    public void setup() throws Exception
    {
        webSocketConnection = new WebSocketConnection(URI.create("localhost"));
    }

    @Test
    public void sendRequest_BootNotification_RequestTransmitted()
    {
        // Given
        String request = "Boot";

        // When
        CompletableFuture future = webSocketConnection.sendRequest(request);

        // Then

    }

}