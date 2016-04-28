package eu.chargetime.ocpp.profiles;

import eu.chargetime.ocpp.model.*;

/**
 * Created by Thomas Volden on 25-Apr-16.
 */
public class CoreProfile implements Profile
{
    ClientCoreEventHandler eventHandler;

    public CoreProfile(ClientCoreEventHandler handler) {
        eventHandler = handler;
    }

    public AuthorizeRequest createAuthorizeRequest(String idToken) {
        return new AuthorizeRequest(idToken);
    }

    public BootNotificationRequest createBootNotificationRequest(String vendor, String model) {
        return new BootNotificationRequest(vendor, model);
    }

    @Override
    public Confirmation findConfirmation(Request request) {
        Confirmation output = null;
        if (request instanceof BootNotificationRequest) {
            output = new BootNotificationConfirmation();
        }
        else if (request instanceof AuthorizeRequest) {
            output = new AuthorizeConfirmation();
        }
        return output;
    }

    public Request findRequest(String action) {
        Request output = null;
        if ("ChangeAvailability".equals(action)) {
            output = new ChangeAvailabilityRequest();
        }
        return output;
    }
}
