package eu.chargetime.ocpp.model;

/**
 * Created by Thomas Volden on 03-May-16.
 */
public class ClearCacheConfirmation implements Confirmation{
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String status;

    @Override
    public boolean validate() {
        return false;
    }
}
