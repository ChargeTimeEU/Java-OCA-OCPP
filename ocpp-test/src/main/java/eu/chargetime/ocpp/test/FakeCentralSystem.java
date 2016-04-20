package eu.chargetime.ocpp.test;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.InetSocketAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Thomas Volden on 15-Feb-16.
 */
public class FakeCentralSystem
{
    private final String CALLRESULTFORMAT = "[3,\"%s\",{%s}]";

    private Object[] receivedMessage;
    private WebSocketServer server;

    private static FakeCentralSystem instance;
    public static FakeCentralSystem getInstance () {
        if (instance == null)
            instance = new FakeCentralSystem();
        return instance;
    }

    private FakeCentralSystem() { }

    public void started() throws Exception
    {
        clean();
        if (server == null) {
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
                    System.out.println("CentralSystem - Message received: " + s);
                    receivedMessage = unpack(s);
                }

                @Override
                public void onError(WebSocket webSocket, Exception e)
                {

                }
            };
            server.start();
        }
    }

    public boolean hasReceivedBootNotification() {
        return receivedMessage != null && "BootNotification".equals(getAction());
    }

    public boolean hasReceivedBootNotification(String chargePointVendor, String chargePointModel) {
        boolean valid = hasReceivedBootNotification();
        if (valid) {
            JSONObject payload = getPayload();
            valid &= payload.getString("chargePointVendor").equals(chargePointVendor);
            valid &= payload.getString("chargePointModel").equals(chargePointModel);
        }
        return valid;
    }

    public boolean hasReceivedAuthorizeRequest()
    {
        return receivedMessage != null && "Authorize".equals(getAction());
    }

    private JSONObject getPayload()
    {
        return (JSONObject)receivedMessage[3];
    }

    private String getAction()
    {
        return receivedMessage[2].toString();
    }

    private String getUniqueId()
    {
        return receivedMessage[1].toString();
    }

    private Object[] unpack(String message)
    {
        if (message == null || "".equals(message))
            return null;

        JSONArray json = new JSONArray(message);
        Object[] output = new Object[json.length()];
        for (int i = 0; i < json.length(); i++) {
            output[i] = json.get(i);
        }
        return output;
    }

    private String now() {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        return formatter.format(new Date());
    }

    public enum RegistrationStatus {
        Accepted, Pending, Rejected
    }

    public void sendBootConfirmation(RegistrationStatus status) {
        String payload = "\"currentTime\": \"%s\", \"interval\": %d, \"status\": \"%s\"";
        sendConfirmation(String.format(payload, now(), 300, status));
    }

    public enum AuthorizationStatus {
        Accepted, Blocked, Expired, Invalid
    }

    public void sendAuthorizeConfirmation(AuthorizationStatus status) {
        String payload = "\"idTagInfo\": {\"status\":\"%s\"}";
        sendConfirmation(String.format(payload, status));
    }

    private void sendConfirmation(String payload) {
        send(String.format(CALLRESULTFORMAT, getUniqueId(), payload));
    }

    private void send(String message) {
        Collection<WebSocket> con = server.connections();
        for (WebSocket ws : con) {
            ws.send(message);
        }
    }

    private void clean()
    {
        receivedMessage = null;
    }
}
