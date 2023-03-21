package eu.chargetime.ocpp.model.dataTypes.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import static eu.chargetime.ocpp.util.EnumUtil.findByField;


/**
 * This contains the type of log file that the Charging Station
 * should send.
 *
 *
 */
public enum LogEnumType {

    DIAGNOSTICS_LOG("DiagnosticsLog"),
    SECURITY_LOG("SecurityLog");
    private final String value;

    LogEnumType(String value) {
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
    public static LogEnumType fromValue(String value) {
        return findByField(
                LogEnumType.class,
                LogEnumType::value,
                value
        );
    }
}
