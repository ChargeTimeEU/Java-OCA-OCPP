package eu.chargetime.ocpp.model;

/**
 * Created by Thomas Volden on 29-Apr-16.
 */
public class Message {
    private String id;
    private String payload;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }
}
