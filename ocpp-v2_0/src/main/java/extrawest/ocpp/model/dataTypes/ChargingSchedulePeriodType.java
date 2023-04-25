package extrawest.ocpp.model.dataTypes;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import extrawest.ocpp.model.Validatable;
import extrawest.ocpp.model.validation.RequiredValidator;
import extrawest.ocpp.model.validation.Validator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


/**
 * Charging_ Schedule_ Period
 * urn:x-oca:ocpp:uid:2:233257
 * Charging schedule period structure defines a time period in a charging schedule.
 *
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "customData",
        "startPeriod",
        "limit",
        "numberPhases",
        "phaseToUse"
})
@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class ChargingSchedulePeriodType implements Validatable {

    private transient Validator<Object> requiredValidator = new RequiredValidator();

    /**
     * This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
     *
     */
    @JsonProperty("customData")
    private CustomDataType customDataType;
    /**
     * Charging_ Schedule_ Period. Start_ Period. Elapsed_ Time
     * urn:x-oca:ocpp:uid:1:569240
     * Start of the period, in seconds from the start of schedule. The value of StartPeriod also defines the stop time of the previous period.
     *
     * (Required)
     *
     */
    @JsonProperty("startPeriod")
    private Integer startPeriod;
    /**
     * Charging_ Schedule_ Period. Limit. Measure
     * urn:x-oca:ocpp:uid:1:569241
     * Charging rate limit during the schedule period, in the applicable chargingRateUnit, for example in Amperes (A) or Watts (W). Accepts at most one digit fraction (e.g. 8.1).
     *
     * (Required)
     *
     */
    @JsonProperty("limit")
    private Double limit;
    /**
     * Charging_ Schedule_ Period. Number_ Phases. Counter
     * urn:x-oca:ocpp:uid:1:569242
     * The number of phases that can be used for charging. If a number of phases is needed, numberPhases=3 will be assumed unless another number is given.
     *
     *
     */
    @JsonProperty("numberPhases")
    private Integer numberPhases;
    /**
     * Values: 1..3, Used if numberPhases=1 and if the EVSE is capable of switching the phase connected to the EV, i.e. ACPhaseSwitchingSupported is defined and true. It’s not allowed unless both conditions above are true. If both conditions are true, and phaseToUse is omitted, the Charging Station / EVSE will make the selection on its own.
     *
     *
     *
     */
    @JsonProperty("phaseToUse")
    private Integer phaseToUse;

    public ChargingSchedulePeriodType(Integer startPeriod, Double limit) {
        requiredValidator.validate(startPeriod);
        requiredValidator.validate(limit);
        this.startPeriod = startPeriod;
        this.limit = limit;
    }

    public void setCustomDataType(CustomDataType customDataType) {
        this.customDataType = customDataType;
    }

    public void setStartPeriod(Integer startPeriod) {
        requiredValidator.validate(startPeriod);
        this.startPeriod = startPeriod;
    }

    public void setLimit(Double limit) {
        requiredValidator.validate(limit);
        this.limit = limit;
    }

    public void setNumberPhases(Integer numberPhases) {
        this.numberPhases = numberPhases;
    }

    public void setPhaseToUse(Integer phaseToUse) {
        this.phaseToUse = phaseToUse;
    }

    @Override
    public boolean validate() {
        return requiredValidator.safeValidate(startPeriod)
                && requiredValidator.safeValidate(limit);
    }
}
