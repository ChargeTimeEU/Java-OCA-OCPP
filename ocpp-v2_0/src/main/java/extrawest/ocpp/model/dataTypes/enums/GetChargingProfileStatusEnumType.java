package extrawest.ocpp.model.dataTypes.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import static extrawest.ocpp.util.EnumUtil.findByField;


/**
 * This indicates whether the Charging Station is able to process this request and will send &lt;<reportchargingprofilesrequest, ReportChargingProfilesRequest>> messages.
 *
 *
 */
public enum GetChargingProfileStatusEnumType {

    ACCEPTED("Accepted"),
    NO_PROFILES("NoProfiles");
    private final String value;

    GetChargingProfileStatusEnumType(String value) {
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
    public static GetChargingProfileStatusEnumType fromValue(String value) {
        return findByField(
                GetChargingProfileStatusEnumType.class,
                GetChargingProfileStatusEnumType::value,
                value
        );
    }
}
