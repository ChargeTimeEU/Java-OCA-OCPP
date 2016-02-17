import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;
import java.util.Collection;

/**
 * Created by Thomas Volden on 15-Feb-16.
 */
public class FakeCentralSystem
{
    private boolean receivedMessage;
    private WebSocketServer server;

    public void start() throws Exception
    {
        clean();
        server = new WebSocketServer(new InetSocketAddress("localhost", 8887))
        {
            @Override
            public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake)
            {
            }

            @Override
            public void onClose(WebSocket webSocket, int i, String s, boolean b)
            {
            }

            @Override
            public void onMessage(WebSocket webSocket, String s)
            {
                receivedMessage = true;
            }

            @Override
            public void onError(WebSocket webSocket, Exception e)
            {

            }
        };
        server.start();
    }

    public boolean hasReceivedBootNotification()
    {
        return receivedMessage;
    }

    public void sendBootConfirmation()
    {
        Collection<WebSocket> con = server.connections();
        synchronized ( con ) {
            for (WebSocket ws : con) {
                ws.send("boot");
            }
        }
    }

    public void clean()
    {
        receivedMessage = false;
    }
}
