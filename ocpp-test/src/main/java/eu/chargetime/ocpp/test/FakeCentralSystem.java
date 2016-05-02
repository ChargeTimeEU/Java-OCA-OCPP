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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Created by Thomas Volden on 15-Feb-16.
 */
public class FakeCentralSystem
{
    private static final String CALLFORMAT = "[2,\"%s\",\"%s\",{%s}]";
    private static final String CALLRESULTFORMAT = "[3,\"%s\",{%s}]";

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

    public void receivedMessageIsNot(String action) {
        assertThat(receivedMessage, is(not(nullValue())));
        assertThat(getAction(), is(not(equalTo(action))));
    }

    public void hasReceivedBootNotification() {
        assertThat(receivedMessage, is(not(nullValue())));
        assertThat(getAction(), equalTo("BootNotification"));
    }

    public void hasReceivedBootNotification(String chargePointVendor, String chargePointModel) {
        JSONObject payload = getCallPayload();
        assertThat(payload.getString("chargePointVendor"), equalTo(chargePointVendor));
        assertThat(payload.getString("chargePointModel"), equalTo(chargePointModel));
    }

    public boolean hasReceivedAuthorizeRequest()
    {
        return receivedMessage != null && "Authorize".equals(getAction());
    }

    private JSONObject getCallPayload()
    {
        return (JSONObject)receivedMessage[3];
    }
    private JSONObject getCallResultPayload()
    {
        return (JSONObject)receivedMessage[2];
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
            if (!json.isNull(i))
                output[i] = json.get(i);
        }
        return output;
    }

    private String now() {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        return formatter.format(new Date());
    }

    public void hasReceivedChangeAvailabilityConfirmation(String status)
    {
        assertThat(getUniqueId(), equalTo("ChangeAvailability"));
        assertThat(getCallResultPayload().getString("status"), equalTo(status));
    }

    public void hasReceivedGetConfigurationConfirmation() {
        assertThat(getUniqueId(), equalTo("GetConfiguration"));
    }

    public void hasReceivedChangeConfigurationConfirmation() {
        assertThat(getUniqueId(), equalTo("ChangeConfiguration"));
    }

    public void sendGetConfigurationRequest(String... keys) {
        String payload = "\"key\":%s";
        sendRequest("GetConfiguration", String.format(payload, formatList(keys)));
    }

    public void sendChangeConfigurationRequest(String key, String value) {
        String payload = "\"key\":\"%s\",\"value\":\"%s\"";
        sendRequest("ChangeConfiguration", String.format(payload, key, value));
    }

    public String formatList(String[] items) {
        StringBuilder output = new StringBuilder();
        for (String item : items)
            output.append(String.format(",\"%s\"", item));
        return String.format("[%s]", output.substring(1));
    }

    public enum AvailabilityType {
        Inoperative, Operative
    }

    public void sendChangeAvailability(int connectorId, AvailabilityType type)
    {
        String payload = "\"connectorId\":%d,\"type\":\"%s\"";
        sendRequest("ChangeAvailability", String.format(payload, connectorId, type));
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

    private void sendRequest(String action, String payload) {
        send(String.format(CALLFORMAT, action, action, payload));
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
