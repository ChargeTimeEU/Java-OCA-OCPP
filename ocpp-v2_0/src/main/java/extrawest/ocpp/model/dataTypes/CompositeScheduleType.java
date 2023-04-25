package extrawest.ocpp.model.dataTypes;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import extrawest.ocpp.model.Validatable;
import extrawest.ocpp.model.dataTypes.enums.ChargingRateUnitEnumType;
import extrawest.ocpp.model.validation.RequiredValidator;
import extrawest.ocpp.model.validation.Validator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;


/**
 * Composite_ Schedule
 * urn:x-oca:ocpp:uid:2:233362
 *
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "customData",
        "chargingSchedulePeriod",
        "evseId",
        "duration",
        "scheduleStart",
        "chargingRateUnit"
})
@ToString
@EqualsAndHashCode
@Getter
@NoArgsConstructor
public class CompositeScheduleType implements Validatable {

    private transient Validator<Object> requiredValidator = new RequiredValidator();

    /**
     * This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
     *
     */
    @JsonProperty("customData")
    public CustomDataType customData;
    /**
     *
     * (Required)
     *
     */
    @JsonProperty("chargingSchedulePeriod")
    public List<ChargingSchedulePeriodType> chargingSchedulePeriod;
    /**
     * The ID of the EVSE for which the
     * schedule is requested. When evseid=0, the
     * Charging Station calculated the expected
     * consumption for the grid connection.
     *
     * (Required)
     *
     */
    @JsonProperty("evseId")
    public Integer evseId;
    /**
     * Duration of the schedule in seconds.
     *
     * (Required)
     *
     */
    @JsonProperty("duration")
    public Integer duration;
    /**
     * Composite_ Schedule. Start. Date_ Time
     * urn:x-oca:ocpp:uid:1:569456
     * Date and time at which the schedule becomes active. All time measurements within the schedule are relative to this timestamp.
     *
     * (Required)
     *
     */
    @JsonProperty("scheduleStart")
    public LocalDateTime scheduleStart;
    /**
     * The unit of measure Limit is
     * expressed in.
     *
     * (Required)
     *
     */
    @JsonProperty("chargingRateUnit")
    public ChargingRateUnitEnumType chargingRateUnit;

    public CompositeScheduleType(List<ChargingSchedulePeriodType> chargingSchedulePeriod,
                                 Integer evseId, Integer duration,
                                 LocalDateTime scheduleStart,
                                 ChargingRateUnitEnumType chargingRateUnit) {
        requiredValidator.validate(chargingSchedulePeriod);
        requiredValidator.validate(evseId);
        requiredValidator.validate(duration);
        requiredValidator.validate(scheduleStart);
        requiredValidator.validate(chargingRateUnit);
        this.chargingSchedulePeriod = chargingSchedulePeriod;
        this.evseId = evseId;
        this.duration = duration;
        this.scheduleStart = scheduleStart;
        this.chargingRateUnit = chargingRateUnit;
    }

    public void setCustomData(CustomDataType customData) {
        this.customData = customData;
    }

    public void setChargingSchedulePeriod(List<ChargingSchedulePeriodType> chargingSchedulePeriod) {
        requiredValidator.validate(chargingSchedulePeriod);
        this.chargingSchedulePeriod = chargingSchedulePeriod;
    }

    public void setEvseId(Integer evseId) {
        requiredValidator.validate(evseId);
        this.evseId = evseId;
    }

    public void setDuration(Integer duration) {
        requiredValidator.validate(duration);
        this.duration = duration;
    }

    public void setScheduleStart(LocalDateTime scheduleStart) {
        requiredValidator.validate(scheduleStart);
        this.scheduleStart = scheduleStart;
    }

    public void setChargingRateUnit(ChargingRateUnitEnumType chargingRateUnit) {
        requiredValidator.validate(chargingRateUnit);
        this.chargingRateUnit = chargingRateUnit;
    }

    @Override
    public boolean validate() {
        return requiredValidator.safeValidate(chargingSchedulePeriod)
            &&requiredValidator.safeValidate(evseId)
            &&requiredValidator.safeValidate(duration)
            &&requiredValidator.safeValidate(scheduleStart)
            &&requiredValidator.safeValidate(chargingRateUnit);
    }
}
