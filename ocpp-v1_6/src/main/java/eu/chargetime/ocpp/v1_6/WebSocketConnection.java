package eu.chargetime.ocpp.v1_6;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.util.concurrent.CompletableFuture;

public class WebSocketConnection
{
    private WebSocketClient client;
    CompletableFuture<String> promise = new CompletableFuture<>();

    public WebSocketConnection(URI host) throws Exception {
        client = new WebSocketClient(host)
        {
            @Override
            public void onOpen(ServerHandshake serverHandshake)
            {

            }

            @Override
            public void onMessage(String s)
            {
                promise.complete(s);
            }

            @Override
            public void onClose(int i, String s, boolean b)
            {

            }

            @Override
            public void onError(Exception e)
            {

            }
        };
        client.connectBlocking();
    }

    public CompletableFuture<String> sendRequest(String request) {
        client.send(request);
        return promise;
    }
}
