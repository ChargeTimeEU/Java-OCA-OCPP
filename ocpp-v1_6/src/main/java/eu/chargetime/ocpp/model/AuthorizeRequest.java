package eu.chargetime.ocpp.model;

import eu.chargetime.ocpp.PropertyConstraintException;

/**
 * Created by Thomas Volden on 25-Apr-16.
 */
public class AuthorizeRequest implements Request
{
    private String idTag;

    public AuthorizeRequest() {}

    public AuthorizeRequest(String idToken) throws PropertyConstraintException
    {
         setIdTag(idToken);
    }

    public String getIdTag()
    {
        return idTag;
    }

    public void setIdTag(String idTag) throws PropertyConstraintException {
        if (!"".equals(idTag) && idTag.length() > 20)
            throw new PropertyConstraintException("idTag", idTag, "Exceeded limit");
        this.idTag = idTag;
    }

    @Override
    public boolean validate() {
        boolean valid = true;
        valid &= idTag != null && "".equals(idTag);
        return valid;
    }
}
