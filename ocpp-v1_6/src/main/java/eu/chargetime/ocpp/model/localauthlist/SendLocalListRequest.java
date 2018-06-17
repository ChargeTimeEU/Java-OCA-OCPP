package eu.chargetime.ocpp.model.localauthlist;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.model.Request;

public class SendLocalListRequest implements Request {

    private int listVersion = 0;
    private AuthorizationData[] localAuthorizationList = null;
    private UpdateType updateType = null;

    public SendLocalListRequest() {
    }

    public SendLocalListRequest(int listVersion, UpdateType updateType) {
        this.listVersion = listVersion;
        this.updateType = updateType;
    }

    public void setListVersion(int listVersion) {
        if (listVersion < 1) {
            throw new PropertyConstraintException(listVersion, "listVersion must be > 0");
        }
        this.listVersion = listVersion;
    }

    public int getListVersion() {
        return listVersion;
    }

    public void setLocalAuthorizationList(AuthorizationData[] localAuthorizationList) {
        this.localAuthorizationList = localAuthorizationList;
    }

    public AuthorizationData[] getLocalAuthorizationList() {
        return localAuthorizationList;
    }

    public void setUpdateType(UpdateType updateType) {
        this.updateType = updateType;
    }

    public UpdateType getUpdateType() {
        return updateType;
    }

    @Override
    public boolean validate() {
        boolean valid = (listVersion >= 1) && (updateType != null);

        if (localAuthorizationList != null) {
            for (AuthorizationData data : localAuthorizationList) {
                valid &= data.validate();
            }
        }

        return valid;
    }

    @Override
    public boolean transactionRelated() {
        return false;
    }
}
