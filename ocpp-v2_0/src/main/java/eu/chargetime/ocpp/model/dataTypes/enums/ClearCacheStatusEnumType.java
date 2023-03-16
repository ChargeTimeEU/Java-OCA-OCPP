package eu.chargetime.ocpp.model.dataTypes.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import static eu.chargetime.ocpp.util.EnumUtil.findByField;


/**
 * Accepted if the Charging Station has executed the request, otherwise rejected.
 *
 *
 */
public enum ClearCacheStatusEnumType {

    ACCEPTED("Accepted"),
    REJECTED("Rejected");
    private final String value;

    ClearCacheStatusEnumType(String value) {
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
    public static ClearCacheStatusEnumType fromValue(String value) {
        return findByField(
                ClearCacheStatusEnumType.class,
                ClearCacheStatusEnumType::value,
                value
        );
    }
}
