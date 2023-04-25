package extrawest.ocpp.model.dataTypes.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import static extrawest.ocpp.util.EnumUtil.findByField;


/**
 * Status is OK if a value could be returned. Otherwise this will indicate the reason why a value could not be returned.
 *
 *
 */
public enum SetMonitoringStatusEnumType {

    ACCEPTED("Accepted"),
    UNKNOWN_COMPONENT("UnknownComponent"),
    UNKNOWN_VARIABLE("UnknownVariable"),
    UNSUPPORTED_MONITOR_TYPE("UnsupportedMonitorType"),
    REJECTED("Rejected"),
    DUPLICATE("Duplicate");
    private final String value;

    SetMonitoringStatusEnumType(String value) {
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
    public static SetMonitoringStatusEnumType fromValue(String value) {
        return findByField(
                SetMonitoringStatusEnumType.class,
                SetMonitoringStatusEnumType::value,
                value
        );
    }
}
