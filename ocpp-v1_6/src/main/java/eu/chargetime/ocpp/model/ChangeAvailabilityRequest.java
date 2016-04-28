package eu.chargetime.ocpp.model;

/**
 * Created by Thomas Volden on 28-Apr-16.
 */
public class ChangeAvailabilityRequest implements Request
{
    private int connectorId;
    private String type;

    public int getConnectorId() {
        return connectorId;
    }

    public void setConnectorId(int connectorId) {
        this.connectorId = connectorId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String action() {
        return "ChangeAvailability";
    }
}
