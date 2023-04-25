package extrawest.ocpp.model.dataTypes.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import static extrawest.ocpp.util.EnumUtil.findByField;

/**
 * This contains whether the Charging Station has been registered
 * within the CSMS.
 *
 *
 */
public enum RegistrationStatusEnumType {

    /** Charging Station is accepted by the CSMS. */
    ACCEPTED("Accepted"),

    /**
     * CSMS is not yet ready to accept the Charging Station. CSMS may send messages to retrieve
     * information or prepare the Charging Station.
     */
    PENDING("Pending"),

    /**
     * Charging Station is not accepted by CSMS. This may happen when the Charging Station id is not
     * known by CSMS.
     */
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
