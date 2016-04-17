import eu.chargetime.ocpp.v1_6.WebSocketConnection;

import java.net.URI;

/**
 * Created by Thomas Volden on 15-Feb-16.
 */
public class FakeChargePoint
{
    private WebSocketConnection webSocketConnection;
    private String receivedConfirmation;
    private String callFormat = "[2,\"%s\",\"%s\",{%s}]";

    public void connected() throws Exception
    {
        clean();
        webSocketConnection = new WebSocketConnection(URI.create("ws://localhost:8887"));
    }

    private boolean match(String message, String id)
    {
        if (message == null || "".equals(message) || id == null || "".equals(id))
            return false;

        String[] segments = message.substring(1, message.length()-1).split(",");
        return id.equals(segments[1].substring(1, segments[1].length()-1));
    }

    public void sendBootNotification(String id)
    {
        webSocketConnection.sendRequest(String.format(callFormat, id, "BootNotification", "")).whenComplete((s, throwable) -> receivedConfirmation = s);
    }

    public void sendAuthorizeRequest(String id)
    {
        webSocketConnection.sendRequest(String.format(callFormat, id, "Authorize", "")).whenComplete((s, throwable) -> receivedConfirmation = s);
    }

    public boolean hasConfirmation(String id)
    {
        return match(receivedConfirmation, id);
    }

    public void clean()
    {
        receivedConfirmation = "";
    }
}
