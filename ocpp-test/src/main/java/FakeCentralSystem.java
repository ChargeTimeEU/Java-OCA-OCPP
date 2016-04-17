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
    private String receivedMessage;
    private WebSocketServer server;
    private String callResultFormat = "[3,\"%s\",{%s}]";

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
                receivedMessage = s;
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
        String action = extractAction(receivedMessage);
        return "BootNotification".equals(action);
    }

    public boolean hasReceivedAuthorizeRequest()
    {
        String action = extractAction(receivedMessage);
        return "Authorize".equals(action);
    }

    private String extractAction(String message)
    {
        if ("".equals(message))
            return "";

        String[] segments = message.substring(1,message.length()-1).split(",");
        return segments[2].substring(1, segments[2].length()-1);
    }

    public void sendBootConfirmation(String id)
    {
        send(String.format(callResultFormat, id, ""));
    }

    private void send(String message)
    {
        Collection<WebSocket> con = server.connections();
        for (WebSocket ws : con) {
            ws.send(message);
        }
    }

    public void sendAuthorizeConfirmation(String id)
    {
        send(String.format(callResultFormat, id, ""));
    }

    public void clean()
    {
        receivedMessage = "";
    }
}
