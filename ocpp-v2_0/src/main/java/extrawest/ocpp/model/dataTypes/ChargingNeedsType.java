package extrawest.ocpp.model.dataTypes;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import extrawest.ocpp.model.Validatable;
import extrawest.ocpp.model.dataTypes.enums.EnergyTransferModeEnumType;
import extrawest.ocpp.model.validation.RequiredValidator;
import extrawest.ocpp.model.validation.Validator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;


/**
 * Charging_ Needs
 * urn:x-oca:ocpp:uid:2:233249
 *
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "customData",
        "acChargingParameters",
        "dcChargingParameters",
        "requestedEnergyTransfer",
        "departureTime"
})
@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class ChargingNeedsType implements Validatable {

    private transient Validator<Object> requiredValidator = new RequiredValidator();

    /**
     * This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
     *
     */
    @JsonProperty("customData")
    public CustomDataType customData;
    /**
     * AC_ Charging_ Parameters
     * urn:x-oca:ocpp:uid:2:233250
     * EV AC charging parameters.
     *
     *
     *
     */
    @JsonProperty("acChargingParameters")
    public ACChargingParametersType acChargingParameters;
    /**
     * DC_ Charging_ Parameters
     * urn:x-oca:ocpp:uid:2:233251
     * EV DC charging parameters
     *
     *
     *
     *
     */
    @JsonProperty("dcChargingParameters")
    public DCChargingParametersType dcChargingParameters;
    /**
     * Charging_ Needs. Requested. Energy_ Transfer_ Mode_ Code
     * urn:x-oca:ocpp:uid:1:569209
     * Mode of energy transfer requested by the EV.
     *
     * (Required)
     *
     */
    @JsonProperty("requestedEnergyTransfer")
    public EnergyTransferModeEnumType requestedEnergyTransfer;
    /**
     * Charging_ Needs. Departure_ Time. Date_ Time
     * urn:x-oca:ocpp:uid:1:569223
     * Estimated departure time of the EV.
     *
     *
     */
    @JsonProperty("departureTime")
    public LocalDateTime departureTime;

    public ChargingNeedsType(EnergyTransferModeEnumType requestedEnergyTransfer) {
        requiredValidator.validate(requestedEnergyTransfer);
        this.requestedEnergyTransfer = requestedEnergyTransfer;
    }

    public void setCustomData(CustomDataType customData) {
        this.customData = customData;
    }

    public void setAcChargingParameters(ACChargingParametersType acChargingParameters) {
        this.acChargingParameters = acChargingParameters;
    }

    public void setDcChargingParameters(DCChargingParametersType dcChargingParameters) {
        this.dcChargingParameters = dcChargingParameters;
    }

    public void setRequestedEnergyTransfer(EnergyTransferModeEnumType requestedEnergyTransfer) {
        requiredValidator.validate(requestedEnergyTransfer);
        this.requestedEnergyTransfer = requestedEnergyTransfer;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    @Override
    public boolean validate() {
        return requiredValidator.safeValidate(requestedEnergyTransfer);
    }
}
