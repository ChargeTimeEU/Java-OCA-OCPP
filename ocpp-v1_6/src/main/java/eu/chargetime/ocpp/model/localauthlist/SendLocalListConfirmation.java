package eu.chargetime.ocpp.model.localauthlist;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.model.Confirmation;

public class SendLocalListConfirmation implements Confirmation {

    private UpdateStatus status;

    public SendLocalListConfirmation() {
    }

    public SendLocalListConfirmation(UpdateStatus status) {
        this.status = status;
    }

    /**
     * This indicates whether the Charge Point has successfully received and applied the update of
     * the local authorization list.
     *
     * @return UpdateStatus, status of localAuthList updating.
     */
    public UpdateStatus getStatus() {
        return status;
    }

    /**
     * Required.
     * This indicates whether the Charge Point has successfully received and applied the update of
     * the local authorization list.
     *
     * @param status {@link UpdateStatus}, status of localAuthList updating.
     */
    public void setStatus(UpdateStatus status) {
        if (status == null) {
            throw new PropertyConstraintException(null, "updateStatus must be present");
        }

        this.status = status;
    }

    @Override
    public boolean validate() {
        return status != null;
    }
}
