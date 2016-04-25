package eu.chargetime.ocpp.test;

import eu.chargetime.ocpp.*;
import eu.chargetime.ocpp.model.AuthorizeRequest;
import eu.chargetime.ocpp.model.BootNotificationRequest;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.profiles.ClientCoreEventHandler;
import eu.chargetime.ocpp.profiles.CoreProfile;
import eu.chargetime.ocpp.v1_6.WebSocketTransmitter;

/**
 * Created by Thomas Volden on 15-Feb-16.
 */
public class FakeChargePoint
{
    private Client client;
    private Request receivedConfirmation;
    private CoreProfile core;

    public FakeChargePoint() {
        core = new CoreProfile(new ClientCoreEventHandler() {});
        client = new Client(new WebSocketTransmitter(), new Queue());
    }

    public void connect() {
        try {
            client.connect("ws://localhost:8887");
        } catch (Exception ex) {
            System.err.println(ex.getStackTrace());
        }
    }

    public void sendBootNotification(String vendor, String model) {
        Request request = core.createBootNotificationRequest(vendor, model);
        send(request);
    }

    public void sendAuthorizeRequest(String idToken) {
        Request request = core.createAuthorizeRequest(idToken);
        send(request);
    }

    private void send(Request request)
    {
        client.send(request).whenComplete((s, ex) -> receivedConfirmation = s);
    }

    public boolean hasReceivedBootConfirmation() {
        return (receivedConfirmation instanceof BootNotificationRequest);
    }

    public boolean hasReceivedAuthorizeConfirmation() {
        return receivedConfirmation instanceof AuthorizeRequest;
    }

    public void disconnect()
    {
        client.disconnect();
    }
}
