package eu.chargetime.ocpp.v1_6.test;

import eu.chargetime.ocpp.TransmitterEvents;
import eu.chargetime.ocpp.v1_6.WebSocketTransmitter;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.net.URI;

import static org.hamcrest.core.Is.is;

public class WebSocketTransmitterTest
{
    WebSocketTransmitter webSocketTransmitter;
    @Mock
    TransmitterEvents transmitterEvents;

    @Before
    public void setup() throws Exception
    {
        webSocketTransmitter = new WebSocketTransmitter();
    }

    @Test
    public void sendRequest_BootNotification_RequestTransmitted()
    {
        // Given
        String request = "Boot";

        // When
        webSocketTransmitter.send(request);

        // Then
        // Mock
    }

}