package eu.chargetime.ocpp;

import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;

/**
 * Created by Thomas Volden on 29-Apr-16.
 */
public interface SessionEvents {
    Feature findFeatureByAction(String action);
    Feature findFeatureByRequest(Request request);

    void            handleConfirmation(String uniqueId, Confirmation confirmation);
    Confirmation    handleRequest(Request request);
}
