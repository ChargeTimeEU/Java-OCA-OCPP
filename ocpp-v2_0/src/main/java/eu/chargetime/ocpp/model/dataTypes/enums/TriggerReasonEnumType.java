package eu.chargetime.ocpp.model.dataTypes.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import static eu.chargetime.ocpp.util.EnumUtil.findByField;

/**
 * Reason the Charging Station sends this message to the CSMS
 *
 *
 */
public enum TriggerReasonEnumType {

    AUTHORIZED("Authorized"),
    CABLE_PLUGGED_IN("CablePluggedIn"),
    CHARGING_RATE_CHANGED("ChargingRateChanged"),
    CHARGING_STATE_CHANGED("ChargingStateChanged"),
    DEAUTHORIZED("Deauthorized"),
    ENERGY_LIMIT_REACHED("EnergyLimitReached"),
    EV_COMMUNICATION_LOST("EVCommunicationLost"),
    EV_CONNECT_TIMEOUT("EVConnectTimeout"),
    METER_VALUE_CLOCK("MeterValueClock"),
    METER_VALUE_PERIODIC("MeterValuePeriodic"),
    TIME_LIMIT_REACHED("TimeLimitReached"),
    TRIGGER("Trigger"),
    UNLOCK_COMMAND("UnlockCommand"),
    STOP_AUTHORIZED("StopAuthorized"),
    EV_DEPARTED("EVDeparted"),
    EV_DETECTED("EVDetected"),
    REMOTE_STOP("RemoteStop"),
    REMOTE_START("RemoteStart"),
    ABNORMAL_CONDITION("AbnormalCondition"),
    SIGNED_DATA_RECEIVED("SignedDataReceived"),
    RESET_COMMAND("ResetCommand");
    private final String value;

    TriggerReasonEnumType(String value) {
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
    public static TriggerReasonEnumType fromValue(String value) {
        return findByField(
                TriggerReasonEnumType.class,
                TriggerReasonEnumType::value,
                value
        );
    }

}
