package eu.chargetime.ocpp.model.dataTypes;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import eu.chargetime.ocpp.model.Validatable;
import eu.chargetime.ocpp.model.validation.IntegerBetweenZeroAndOneHundred;
import eu.chargetime.ocpp.model.validation.RequiredValidator;
import eu.chargetime.ocpp.model.validation.Validator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;


/**
 * DC_ Charging_ Parameters
 * urn:x-oca:ocpp:uid:2:233251
 * EV DC charging parameters
 *
 *
 *
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "customData",
        "evMaxCurrent",
        "evMaxVoltage",
        "energyAmount",
        "evMaxPower",
        "stateOfCharge",
        "evEnergyCapacity",
        "fullSoC",
        "bulkSoC"
})
@Getter
@EqualsAndHashCode
@ToString
public class DCChargingParametersType implements Validatable {

    private transient Validator<Object> requiredValidator = new RequiredValidator();

    private transient Validator<Integer> intBetweenZeroAndOneHundredValidator = new IntegerBetweenZeroAndOneHundred();

    /**
     * This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
     *
     */
    @JsonProperty("customData")
    public CustomDataType customData;
    /**
     * DC_ Charging_ Parameters. EV_ Max. Current
     * urn:x-oca:ocpp:uid:1:569215
     * Maximum current (amps) supported by the electric vehicle. Includes cable capacity.
     *
     * (Required)
     *
     */
    @JsonProperty("evMaxCurrent")
    public Integer evMaxCurrent;
    /**
     * DC_ Charging_ Parameters. EV_ Max. Voltage
     * urn:x-oca:ocpp:uid:1:569216
     * Maximum voltage supported by the electric vehicle
     *
     * (Required)
     *
     */
    @JsonProperty("evMaxVoltage")
    public Integer evMaxVoltage;
    /**
     * DC_ Charging_ Parameters. Energy_ Amount. Energy_ Amount
     * urn:x-oca:ocpp:uid:1:569217
     * Amount of energy requested (in Wh). This inludes energy required for preconditioning.
     *
     *
     */
    @JsonProperty("energyAmount")
    public Integer energyAmount;
    /**
     * DC_ Charging_ Parameters. EV_ Max. Power
     * urn:x-oca:ocpp:uid:1:569218
     * Maximum power (in W) supported by the electric vehicle. Required for DC charging.
     *
     *
     */
    @JsonProperty("evMaxPower")
    public Integer evMaxPower;
    /**
     * DC_ Charging_ Parameters. State_ Of_ Charge. Numeric
     * urn:x-oca:ocpp:uid:1:569219
     * Energy available in the battery (in percent of the battery capacity)
     *
     *
     */
    @JsonProperty("stateOfCharge")
    public Integer stateOfCharge;
    /**
     * DC_ Charging_ Parameters. EV_ Energy_ Capacity. Numeric
     * urn:x-oca:ocpp:uid:1:569220
     * Capacity of the electric vehicle battery (in Wh)
     *
     *
     */
    @JsonProperty("evEnergyCapacity")
    public Integer evEnergyCapacity;
    /**
     * DC_ Charging_ Parameters. Full_ SOC. Percentage
     * urn:x-oca:ocpp:uid:1:569221
     * Percentage of SoC at which the EV considers the battery fully charged. (possible values: 0 - 100)
     *
     *
     */
    @JsonProperty("fullSoC")
    public Integer fullSoC;
    /**
     * DC_ Charging_ Parameters. Bulk_ SOC. Percentage
     * urn:x-oca:ocpp:uid:1:569222
     * Percentage of SoC at which the EV considers a fast charging process to end. (possible values: 0 - 100)
     *
     *
     */
    @JsonProperty("bulkSoC")
    public Integer bulkSoC;

    public DCChargingParametersType(Integer evMaxCurrent, Integer evMaxVoltage) {
        requiredValidator.validate(evMaxCurrent);
        requiredValidator.validate(evMaxVoltage);
        this.evMaxCurrent = evMaxCurrent;
        this.evMaxVoltage = evMaxVoltage;
    }

    public void setCustomData(CustomDataType customData) {
        this.customData = customData;
    }

    public void setEvMaxCurrent(Integer evMaxCurrent) {
        requiredValidator.validate(evMaxCurrent);
        this.evMaxCurrent = evMaxCurrent;
    }

    public void setEvMaxVoltage(Integer evMaxVoltage) {
        requiredValidator.validate(evMaxVoltage);
        this.evMaxVoltage = evMaxVoltage;
    }

    public void setEnergyAmount(Integer energyAmount) {
        this.energyAmount = energyAmount;
    }

    public void setEvMaxPower(Integer evMaxPower) {
        this.evMaxPower = evMaxPower;
    }

    public void setStateOfCharge(Integer stateOfCharge) {
        intBetweenZeroAndOneHundredValidator.validate(stateOfCharge);
        this.stateOfCharge = stateOfCharge;
    }

    public void setEvEnergyCapacity(Integer evEnergyCapacity) {
        this.evEnergyCapacity = evEnergyCapacity;
    }

    public void setFullSoC(Integer fullSoC) {
        intBetweenZeroAndOneHundredValidator.validate(fullSoC);
        this.fullSoC = fullSoC;
    }

    public void setBulkSoC(Integer bulkSoC) {
        intBetweenZeroAndOneHundredValidator.validate(bulkSoC);
        this.bulkSoC = bulkSoC;
    }

    @Override
    public boolean validate() {
        return requiredValidator.safeValidate(evMaxCurrent)
                &&requiredValidator.safeValidate(evMaxVoltage);
    }
}
