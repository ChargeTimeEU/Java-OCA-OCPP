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
 ChargeTime.eu - Java-OCA-OCPP
 Copyright (C) 2015-2016 Thomas Volden <tv@chargetime.eu>

 MIT License

 Copyright (c) 2016 Thomas Volden

 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all
 copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 SOFTWARE.
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

    public void hasReceivedHeartbeat() {
        assertThat(receivedMessage, is(not(nullValue())));
        assertThat(getAction(), equalTo("Heartbeat"));
    }

    public void hasReceivedDataTransferRequest() {
        assertThat(receivedMessage, is(not(nullValue())));
        assertThat(getAction(), equalTo("DataTransfer"));
    }
    public boolean hasReceivedAuthorizeRequest()
    {
        return receivedMessage != null && "Authorize".equals(getAction());
    }

    public boolean hasReceivedMeterValuesRequest() {
        return receivedMessage != null && "MeterValues".equals(getAction());
    }

    public boolean hasReceivedStartTransactionRequest() {
        return receivedMessage != null && "StartTransaction".equals(getAction());
    }

    public boolean hasReceivedStatusNotificationRequest() {
        return receivedMessage != null && "StatusNotification".equals(getAction());
    }

    public boolean hasReceivedStopTransactionRequest() {
        return receivedMessage != null && "StopTransaction".equals(getAction());
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

    public void hasReceivedClearCacheConfirmation() {
        assertThat(getUniqueId(), equalTo("ClearCache"));
    }

    public void hasReceivedDataTransferConfirmation() {
        assertThat(getUniqueId(), equalTo("DataTransfer"));
    }

    public void hasReceivedRemoteStartTransactionConfirmation(String status) {
        assertThat(getUniqueId(), equalTo("RemoteStartTransaction"));
        assertThat(getCallResultPayload().getString("status"), equalTo(status));
    }

    public void hasReceivedRemoteStopTransactionConfirmation(String status) {
        assertThat(getUniqueId(), equalTo("RemoteStopTransaction"));
        assertThat(getCallResultPayload().getString("status"), equalTo(status));
    }

    public void hasReceivedResetConfirmation(String status) {
        assertThat(getUniqueId(), equalTo("Reset"));
        assertThat(getCallResultPayload().getString("status"), equalTo(status));
    }

    public void hasReceivedUnlockConnectorConfirmation(String status) {
        assertThat(getUniqueId(), equalTo("UnlockConnector"));
        assertThat(getCallResultPayload().getString("status"), equalTo(status));
    }

    public void sendGetConfigurationRequest(String... keys) {
        String payload = "\"key\":%s";
        sendRequest("GetConfiguration", String.format(payload, formatList(keys)));
    }

    public void sendChangeConfigurationRequest(String key, String value) {
        String payload = "\"key\":\"%s\",\"value\":\"%s\"";
        sendRequest("ChangeConfiguration", String.format(payload, key, value));
    }

    public void sendClearCacheRequest() {
        sendRequest("ClearCache", "");
    }

    public String formatList(String[] items) {
        StringBuilder output = new StringBuilder();
        for (String item : items)
            output.append(String.format(",\"%s\"", item));
        return String.format("[%s]", output.substring(1));
    }

    public void sendDataTransferRequest(String vendorId, String messageId, String data) {
        String payload = "\"vendorId\": \"%s\", \"messageId\": %s, \"data\": \"%s\"";
        sendRequest("DataTransfer", String.format(payload, vendorId, messageId, data));
    }

    public void sendRemoteStartTransactionRequest(String idTag) {
        String payload = "\"idTag\": { \"idToken\": \"%s\"}";
        sendRequest("RemoteStartTransaction", String.format(payload, idTag));
    }

    public void sendRemoteStopTransactionRequest(int transactionId) {
        String payload = "\"transactionId\": %d";
        sendRequest("RemoteStopTransaction", String.format(payload, transactionId));
    }

    public void sendResetRequest(String type) {
        String payload = "\"type\": \"%s\"";
        sendRequest("Reset", String.format(payload, type));
    }

    public void sendUnlockConnectorRequest(int connectorId) {
        String payload = "\"connectorId\": %d";
        sendRequest("UnlockConnector", String.format(payload, connectorId));
    }

    public enum AvailabilityType {
        Inoperative, Operative
    }
    public void sendChangeAvailabilityRequest(int connectorId, AvailabilityType type)
    {
        String payload = "\"connectorId\":%d,\"type\":\"%s\"";
        sendRequest("ChangeAvailability", String.format(payload, connectorId, type));
    }

    public enum RegistrationStatus {
        Accepted, Pending, Rejected
    }
    public void sendHeartbeatConfirmation() {
        String payload = "\"currentTime\": \"%s\"";
        sendConfirmation(String.format(payload, now()));
    }
    public void sendStatusNotificationConfirmation() {
        sendConfirmation("");
    }

    public void sendStartTransactionConfirmation() {
        String payload = "\"idTagInfo\": {\"status\": \"Accepted\"}, \"transactionId\": 42";
        sendConfirmation(payload);
    }

    public void sendStopTransactionConfirmation() {
        String payload = "\"idTagInfo\": {\"status\": \"Accepted\"}";
        sendConfirmation(payload);
    }

    public void sendMeterValuesConfirmation() {
        sendConfirmation("");
    }

    public void sendBootConfirmation(RegistrationStatus status) {
        String payload = "\"currentTime\": \"%s\", \"interval\": %d, \"status\": \"%s\"";
        sendConfirmation(String.format(payload, now(), 300, status));
    }

    public void sendDataTransferConfirmation(String status) {
        String payload = "\"status\": \"%s\"";
        sendConfirmation(String.format(payload, status));
    }

    public enum AuthorizationStatus {
        Accepted, Blocked, Expired, Invalid;
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
