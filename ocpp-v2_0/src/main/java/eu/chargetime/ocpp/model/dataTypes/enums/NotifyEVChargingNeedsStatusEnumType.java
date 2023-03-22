package eu.chargetime.ocpp.model.dataTypes.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import static eu.chargetime.ocpp.util.EnumUtil.findByField;


/**
 * Returns whether the CSMS has been able to process the message successfully. It does not imply that the evChargingNeeds can be met with the current charging profile.
 *
 *
 */
public enum NotifyEVChargingNeedsStatusEnumType {

    ACCEPTED("Accepted"),
    REJECTED("Rejected"),
    PROCESSING("Processing");
    private final String value;

    NotifyEVChargingNeedsStatusEnumType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    @JsonValue
    public String value() {
        return this.value;
    }

    @JsonCreator
    public static NotifyEVChargingNeedsStatusEnumType fromValue(String value) {
        return findByField(
                NotifyEVChargingNeedsStatusEnumType.class,
                NotifyEVChargingNeedsStatusEnumType::value,
                value
        );
    }
}
