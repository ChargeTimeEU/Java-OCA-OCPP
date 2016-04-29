package eu.chargetime.ocpp.model;

/**
 * Created by Thomas Volden on 29-Apr-16.
 */
public class CallMessage extends Message {
    private String action;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
