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
 * AC_ Charging_ Parameters
 * urn:x-oca:ocpp:uid:2:233250
 * EV AC charging parameters.
 *
 *
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "customData",
        "energyAmount",
        "evMinCurrent",
        "evMaxCurrent",
        "evMaxVoltage"
})
@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class ACChargingParametersType implements Validatable {

    private transient Validator<Object> requiredValidator = new RequiredValidator();

    /**
     * This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
     *
     */
    @JsonProperty("customData")
    public CustomDataType customData;
    /**
     * AC_ Charging_ Parameters. Energy_ Amount. Energy_ Amount
     * urn:x-oca:ocpp:uid:1:569211
     * Amount of energy requested (in Wh). This includes energy required for preconditioning.
     *
     * (Required)
     *
     */
    @JsonProperty("energyAmount")
    public Integer energyAmount;
    /**
     * AC_ Charging_ Parameters. EV_ Min. Current
     * urn:x-oca:ocpp:uid:1:569212
     * Minimum current (amps) supported by the electric vehicle (per phase).
     *
     * (Required)
     *
     */
    @JsonProperty("evMinCurrent")
    public Integer evMinCurrent;
    /**
     * AC_ Charging_ Parameters. EV_ Max. Current
     * urn:x-oca:ocpp:uid:1:569213
     * Maximum current (amps) supported by the electric vehicle (per phase). Includes cable capacity.
     *
     * (Required)
     *
     */
    @JsonProperty("evMaxCurrent")
    public Integer evMaxCurrent;
    /**
     * AC_ Charging_ Parameters. EV_ Max. Voltage
     * urn:x-oca:ocpp:uid:1:569214
     * Maximum voltage supported by the electric vehicle
     *
     * (Required)
     *
     */
    @JsonProperty("evMaxVoltage")
    public Integer evMaxVoltage;

    public ACChargingParametersType(Integer energyAmount,
                                    Integer evMinCurrent,
                                    Integer evMaxCurrent,
                                    Integer evMaxVoltage) {
        requiredValidator.validate(energyAmount);
        requiredValidator.validate(evMinCurrent);
        requiredValidator.validate(evMaxCurrent);
        requiredValidator.validate(evMaxVoltage);
        this.energyAmount = energyAmount;
        this.evMinCurrent = evMinCurrent;
        this.evMaxCurrent = evMaxCurrent;
        this.evMaxVoltage = evMaxVoltage;
    }

    public CustomDataType getCustomData() {
        return customData;
    }

    public void setCustomData(CustomDataType customData) {
        this.customData = customData;
    }

    public void setEnergyAmount(Integer energyAmount) {
        requiredValidator.validate(energyAmount);
        this.energyAmount = energyAmount;
    }

    public void setEvMinCurrent(Integer evMinCurrent) {
        requiredValidator.validate(evMinCurrent);
        this.evMinCurrent = evMinCurrent;
    }

    public void setEvMaxCurrent(Integer evMaxCurrent) {
        requiredValidator.validate(evMaxCurrent);
        this.evMaxCurrent = evMaxCurrent;
    }

    public void setEvMaxVoltage(Integer evMaxVoltage) {
        requiredValidator.validate(evMaxVoltage);
        this.evMaxVoltage = evMaxVoltage;
    }

    @Override
    public boolean validate() {
        return requiredValidator.safeValidate(energyAmount)
                &&requiredValidator.safeValidate(evMinCurrent)
                &&requiredValidator.safeValidate(evMaxCurrent)
                &&requiredValidator.safeValidate(evMaxVoltage);
    }
}
