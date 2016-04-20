package eu.chargetime.ocpp.test;

import eu.chargetime.ocpp.Client;
import eu.chargetime.ocpp.v1_6.WebSocketTransmitter;

/**
 * Created by Thomas Volden on 15-Feb-16.
 */
public class FakeChargePoint
{
    Client client;
    private String receivedConfirmation;

    public FakeChargePoint() {
        client = new Client(new WebSocketTransmitter());
    }

    public void connect() {
        try {
            client.connect("ws://localhost:8887");
        } catch (Exception ex) {
            System.err.println(ex.getStackTrace());
        }
    }

    public void sendBootNotification(String chargePointVendor, String chargePointModel) {
        String payload = "\"chargePointVendor\": \"%s\", \"chargePointModel\": \"%s\"";
        send("BootNotification", String.format(payload, chargePointVendor, chargePointModel));
    }

    public void sendAuthorizeRequest(String idToken) {
        String payload = "\"idTag\": \"%s\"";
        send("Authorize", String.format(payload, idToken));
    }

    private void send(String request, String payload)
    {
        client.send(request, payload).whenComplete((s, ex) -> receivedConfirmation = s);
    }

    public boolean hasReceivedBootConfirmation() {
        return "BootNotification".equals(receivedConfirmation);
    }

    public boolean hasReceivedAuthorizeConfirmation() {
        return "Authorize".equals(receivedConfirmation);
    }

    public void disconnect()
    {
        client.disconnect();
    }
}
