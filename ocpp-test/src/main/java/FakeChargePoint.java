import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

/**
 * Created by Thomas Volden on 15-Feb-16.
 */
public class FakeChargePoint
{
    private WebSocketClient client;
    private boolean receivedConfirmation;

    public void connected() throws Exception
    {
        clean();
        client = new WebSocketClient(URI.create("ws://localhost:8887"))
        {
            @Override
            public void onOpen(ServerHandshake serverHandshake)
            {
            }

            @Override
            public void onMessage(String s)
            {
                receivedConfirmation = true;
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

    public void sendBootNotification()
    {
        client.send("boot");
    }

    public boolean hasReceivedBootConfirmation()
    {
        return receivedConfirmation;
    }

    public void clean()
    {
        receivedConfirmation = false;
    }
}
