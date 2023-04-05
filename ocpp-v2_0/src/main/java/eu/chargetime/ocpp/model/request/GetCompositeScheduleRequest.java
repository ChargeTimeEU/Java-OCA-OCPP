package eu.chargetime.ocpp.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import eu.chargetime.ocpp.model.RequestWithId;
import eu.chargetime.ocpp.model.dataTypes.CustomDataType;
import eu.chargetime.ocpp.model.dataTypes.enums.ChargingRateUnitEnumType;
import eu.chargetime.ocpp.model.validation.RequiredValidator;
import eu.chargetime.ocpp.model.validation.Validator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "customData",
        "duration",
        "chargingRateUnit",
        "evseId"
})
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class GetCompositeScheduleRequest extends RequestWithId {

    private transient Validator<Object> requiredValidator = new RequiredValidator();

    /**
     * This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
     *
     */
    @JsonProperty("customData")
    public CustomDataType customData;
    /**
     * Length of the requested schedule in seconds.
     *
     *
     * (Required)
     *
     */
    @JsonProperty("duration")
    public Integer duration;
    /**
     * Can be used to force a power or current profile.
     *
     *
     *
     */
    @JsonProperty("chargingRateUnit")
    public ChargingRateUnitEnumType chargingRateUnit;
    /**
     * The ID of the EVSE for which the schedule is requested. When evseid=0, the Charging Station will calculate the expected consumption for the grid connection.
     *
     * (Required)
     *
     */
    @JsonProperty("evseId")
    public Integer evseId;

    public GetCompositeScheduleRequest(Integer duration, Integer evseId) {
        requiredValidator.validate(duration);
        requiredValidator.validate(evseId);
        this.duration = duration;
        this.evseId = evseId;
    }

    public void setCustomData(CustomDataType customData) {
        this.customData = customData;
    }

    public void setDuration(Integer duration) {
        requiredValidator.validate(duration);
        this.duration = duration;
    }

    public void setChargingRateUnit(ChargingRateUnitEnumType chargingRateUnit) {
        this.chargingRateUnit = chargingRateUnit;
    }

    public void setEvseId(Integer evseId) {
        requiredValidator.validate(evseId);
        this.evseId = evseId;
    }

    @Override
    public boolean transactionRelated() {
        return false;
    }

    @Override
    public boolean validate() {
        return requiredValidator.safeValidate(duration)
                &&requiredValidator.safeValidate(evseId);
    }
}
