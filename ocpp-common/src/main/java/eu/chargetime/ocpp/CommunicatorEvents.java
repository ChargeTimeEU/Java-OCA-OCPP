package eu.chargetime.ocpp;

/**
 * Created by Thomas Volden on 29-Apr-16.
 */
public interface CommunicatorEvents {
    void onCallResult(String id, String payload);
    void onCall(String id, String action, String payload);
    void onError(String id, String payload);
    void onDisconnected();
    void onConnected();
}
