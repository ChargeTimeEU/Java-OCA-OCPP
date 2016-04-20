package eu.chargetime.ocpp.v1_6;

import eu.chargetime.ocpp.Transmitter;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

public class WebSocketConnection
{
    private WebSocketClient client;

    public WebSocketConnection(URI host, Transmitter transmitter) throws Exception {
        client = new WebSocketClient(host)
        {
            @Override
            public void onOpen(ServerHandshake serverHandshake)
            {
                transmitter.connected();
            }

            @Override
            public void onMessage(String s)
            {
                transmitter.receivedMessage(s);
            }

            @Override
            public void onClose(int i, String s, boolean b)
            {
                transmitter.disconnected();
            }

            @Override
            public void onError(Exception e)
            {

            }
        };
        client.connectBlocking();
    }

    public void disconnect() throws Exception
    {
        client.closeBlocking();
    }

    public void sendMessage(String request) {
        client.send(request);
    }
}
