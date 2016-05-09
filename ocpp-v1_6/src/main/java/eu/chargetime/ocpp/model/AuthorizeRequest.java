package eu.chargetime.ocpp.model;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.utilities.ModelUtil;

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
        if (!ModelUtil.validate(idTag, 20))
            throw new PropertyConstraintException("idTag", idTag, "Exceeded limit");

        this.idTag = idTag;
    }

    @Override
    public boolean validate() {
        boolean valid = true;
        valid &= ModelUtil.validate(idTag, 20);
        return valid;
    }
}
