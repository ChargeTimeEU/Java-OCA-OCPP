package eu.chargetime.ocpp.model.dataTypes.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import static eu.chargetime.ocpp.util.EnumUtil.findByField;

/**
 * This contains whether the Charging Station has been registered
 * within the CSMS.
 *
 *
 */
public enum RegistrationStatusEnumType {

    ACCEPTED("Accepted"),
    PENDING("Pending"),
    REJECTED("Rejected");
    private final String value;

    RegistrationStatusEnumType(String value) {
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
    public static RegistrationStatusEnumType fromValue(String value) {
        return findByField(
                RegistrationStatusEnumType.class,
                RegistrationStatusEnumType::value,
                value
        );
    }

}
