package eu.chargetime.ocpp.model;

/**
 * Created by Thomas Volden on 25-Apr-16.
 */
public class AuthorizeRequest implements Request
{
    private String idTag;

    public AuthorizeRequest(String idToken)
    {
         idTag = idToken;
    }

    public String getIdTag()
    {
        return idTag;
    }

    @Override
    public String action()
    {
        return "Authorize";
    }
}
