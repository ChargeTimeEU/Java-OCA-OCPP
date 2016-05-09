package eu.chargetime.ocpp.model;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.utilities.ModelUtil;

import java.util.Calendar;

/**
 * Created by Thomas Volden on 27-Apr-16.
 */
public class IdTagInfo implements validatable
{
    private Calendar expiryDate;
    private String parentIdTag;

    public Calendar getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Calendar expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getParentIdTag() {
        return parentIdTag;
    }

    public void setParentIdTag(String parentIdTag) throws PropertyConstraintException {
        if (!"".equals(parentIdTag) && parentIdTag.length() > 20)
            throw new PropertyConstraintException("parentIdTag", parentIdTag, "Exceeded limit");

        this.parentIdTag = parentIdTag;
    }

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) throws PropertyConstraintException {
        if (! isValidStatus(status))
            throw new PropertyConstraintException("status", status, "Illegal value");

        this.status = status;
    }

    private boolean isValidStatus(String status) {
        return ModelUtil.isAmoung(status, "Accepted", "Blocked", "Expired", "Invalid", "ConcurrentTx");
    }

    @Override
    public boolean validate() {
        boolean valid = true;
        valid &= isValidStatus(this.status);
        return valid;
    }
}
