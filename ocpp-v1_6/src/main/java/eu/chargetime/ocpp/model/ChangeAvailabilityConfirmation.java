package eu.chargetime.ocpp.model;

/**
 * Created by Thomas Volden on 30-Apr-16.
 */
public class ChangeAvailabilityConfirmation implements Confirmation {

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ChangeAvailabilityConfirmation() { }

    public ChangeAvailabilityConfirmation(String status) {
        this.status = status;
    }

    @Override
    public boolean validate() {
        return false;
    }
}
