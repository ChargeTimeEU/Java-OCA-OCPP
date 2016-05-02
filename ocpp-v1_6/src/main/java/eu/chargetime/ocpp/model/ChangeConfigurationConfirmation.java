package eu.chargetime.ocpp.model;

/**
 * Created by Thomas Volden on 02-May-16.
 */
public class ChangeConfigurationConfirmation implements Confirmation{
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
