package eu.chargetime.ocpp.model.dataTypes.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import static eu.chargetime.ocpp.util.EnumUtil.findByField;


/**
 * The type of this monitor, e.g. a threshold, delta or periodic monitor.
 *
 *
 */
public enum MonitorEnumType {

    UPPER_THRESHOLD("UpperThreshold"),
    LOWER_THRESHOLD("LowerThreshold"),
    DELTA("Delta"),
    PERIODIC("Periodic"),
    PERIODIC_CLOCK_ALIGNED("PeriodicClockAligned");
    private final String value;

    MonitorEnumType(String value) {
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
    public static MonitorEnumType fromValue(String value) {
        return findByField(
                MonitorEnumType.class,
                MonitorEnumType::value,
                value
        );
    }
}
