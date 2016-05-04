package eu.chargetime.ocpp.model;

import eu.chargetime.ocpp.PropertyConstraintException;

import java.util.Calendar;

/**
 * Created by Thomas Volden on 27-Apr-16.
 */
public class IdTagInfo
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

    public String getStatus()
    {
        return status;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public boolean validate() {
        boolean validate = true;
        return validate;
    }
}
