package extrawest.ocpp.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import extrawest.ocpp.model.RequestWithId;
import extrawest.ocpp.model.dataTypes.CustomDataType;
import extrawest.ocpp.model.dataTypes.EVSEType;
import extrawest.ocpp.model.dataTypes.enums.OperationalStatusEnumType;
import extrawest.ocpp.model.validation.RequiredValidator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "customData",
        "evse",
        "operationalStatus"
})
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class ChangeAvailabilityRequest extends RequestWithId {

    private transient RequiredValidator requiredValidator = new RequiredValidator();

    /**
     * This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
     *
     */
    @JsonProperty("customData")
    public CustomDataType customData;
    /**
     * EVSE
     * urn:x-oca:ocpp:uid:2:233123
     * Electric Vehicle Supply Equipment
     *
     *
     */
    @JsonProperty("evse")
    public EVSEType evse;
    /**
     * This contains the type of availability change that the Charging Station should perform.
     *
     *
     * (Required)
     *
     */
    @JsonProperty("operationalStatus")
    public OperationalStatusEnumType operationalStatus;

    public ChangeAvailabilityRequest(OperationalStatusEnumType operationalStatus) {
        requiredValidator.validate(operationalStatus);
        this.operationalStatus = operationalStatus;
    }

    public void setCustomData(CustomDataType customData) {
        this.customData = customData;
    }

    public void setEvse(EVSEType evse) {
        this.evse = evse;
    }

    public void setOperationalStatus(OperationalStatusEnumType operationalStatus) {
        requiredValidator.validate(operationalStatus);
        this.operationalStatus = operationalStatus;
    }

    @Override
    public boolean transactionRelated() {
        return false;
    }

    @Override
    public boolean validate() {
        return requiredValidator.safeValidate(operationalStatus);
    }
}
