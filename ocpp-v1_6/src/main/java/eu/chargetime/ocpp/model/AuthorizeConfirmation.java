package eu.chargetime.ocpp.model;

/**
 * Created by Thomas Volden on 26-Apr-16.
 */
public class AuthorizeConfirmation implements Confirmation
{
    private IdTagInfo idTagInfo;

    public IdTagInfo getIdTagInfo()
    {
        return idTagInfo;
    }

    public void setIdTagInfo(IdTagInfo idTagInfo)
    {
        this.idTagInfo = idTagInfo;
    }
}
