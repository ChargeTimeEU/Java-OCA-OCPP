package eu.chargetime.ocpp.feature.profile;

import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.model.Confirmation;

/**
 * Created by Thomas Volden on 25-Apr-16.
 */
public interface Profile {
    Feature[] getFeatureList();
}
