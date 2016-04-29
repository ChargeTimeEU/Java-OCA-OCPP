package eu.chargetime.ocpp.test;

import eu.chargetime.ocpp.TransmitterEvents;
import eu.chargetime.ocpp.WebSocketTransmitter;
import org.junit.Before;
import org.mockito.Mock;

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

}