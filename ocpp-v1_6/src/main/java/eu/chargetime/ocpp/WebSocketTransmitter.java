package eu.chargetime.ocpp;

import eu.chargetime.ocpp.Transmitter;
import eu.chargetime.ocpp.TransmitterEvents;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

public class WebSocketTransmitter implements Transmitter
{
    private WebSocketClient client;

    @Override
    public void connect(String uri, TransmitterEvents events) {
        client = new WebSocketClient(URI.create(uri))
        {
            @Override
            public void onOpen(ServerHandshake serverHandshake)
            {
                events.connected();
            }

            @Override
            public void onMessage(String s)
            {
                events.receivedMessage(s);
            }

            @Override
            public void onClose(int i, String s, boolean b)
            {
                events.disconnected();
            }

            @Override
            public void onError(Exception ex)
            {
                ex.printStackTrace();
            }
        };
        try {
            client.connectBlocking();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void disconnect()
    {
        try {
            client.closeBlocking();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void send(String request) {
        client.send(request);
    }
}
