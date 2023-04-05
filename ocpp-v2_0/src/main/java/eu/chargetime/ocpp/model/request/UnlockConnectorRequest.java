package eu.chargetime.ocpp.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import eu.chargetime.ocpp.model.RequestWithId;
import eu.chargetime.ocpp.model.dataTypes.CustomDataType;
import eu.chargetime.ocpp.model.validation.RequiredValidator;
import eu.chargetime.ocpp.model.validation.Validator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "customData",
        "evseId",
        "connectorId"
})
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class UnlockConnectorRequest extends RequestWithId {

    private transient Validator<Object> requiredValidator = new RequiredValidator();

    /**
     * This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
     *
     */
    @JsonProperty("customData")
    public CustomDataType customData;
    /**
     * This contains the identifier of the EVSE for which a connector needs to be unlocked.
     *
     * (Required)
     *
     */
    @JsonProperty("evseId")
    public Integer evseId;
    /**
     * This contains the identifier of the connector that needs to be unlocked.
     *
     * (Required)
     *
     */
    @JsonProperty("connectorId")
    public Integer connectorId;

    public UnlockConnectorRequest(Integer evseId, Integer connectorId) {
        requiredValidator.validate(evseId);
        requiredValidator.validate(connectorId);
        this.evseId = evseId;
        this.connectorId = connectorId;
    }

    public void setCustomData(CustomDataType customData) {
        this.customData = customData;
    }

    public void setEvseId(Integer evseId) {
        requiredValidator.validate(evseId);
        this.evseId = evseId;
    }

    public void setConnectorId(Integer connectorId) {
        requiredValidator.validate(connectorId);
        this.connectorId = connectorId;
    }

    @Override
    public boolean transactionRelated() {
        return false;
    }

    @Override
    public boolean validate() {
        return requiredValidator.safeValidate(evseId)
                &&requiredValidator.safeValidate(connectorId);
    }
}
