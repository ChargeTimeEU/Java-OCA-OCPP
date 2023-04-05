package eu.chargetime.ocpp.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import eu.chargetime.ocpp.model.RequestWithId;
import eu.chargetime.ocpp.model.dataTypes.CustomDataType;
import eu.chargetime.ocpp.model.dataTypes.NetworkConnectionProfileType;
import eu.chargetime.ocpp.model.validation.RequiredValidator;
import eu.chargetime.ocpp.model.validation.Validator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "customData",
        "configurationSlot",
        "connectionData"
})
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class SetNetworkProfileRequest extends RequestWithId {

    private transient Validator<Object> requiredValidator = new RequiredValidator();

    /**
     * This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
     *
     */
    @JsonProperty("customData")
    public CustomDataType customData;
    /**
     * Slot in which the configuration should be stored.
     *
     * (Required)
     *
     */
    @JsonProperty("configurationSlot")
    public Integer configurationSlot;
    /**
     * Communication_ Function
     * urn:x-oca:ocpp:uid:2:233304
     * The NetworkConnectionProfile defines the functional and technical parameters of a communication link.
     *
     * (Required)
     *
     */
    @JsonProperty("connectionData")
    public NetworkConnectionProfileType connectionData;

    public SetNetworkProfileRequest(Integer configurationSlot, NetworkConnectionProfileType connectionData) {
        requiredValidator.validate(configurationSlot);
        requiredValidator.validate(connectionData);
        this.configurationSlot = configurationSlot;
        this.connectionData = connectionData;
    }

    public void setCustomData(CustomDataType customData) {
        this.customData = customData;
    }

    public void setConfigurationSlot(Integer configurationSlot) {
        requiredValidator.validate(configurationSlot);
        this.configurationSlot = configurationSlot;
    }

    public void setConnectionData(NetworkConnectionProfileType connectionData) {
        requiredValidator.validate(connectionData);
        this.connectionData = connectionData;
    }

    @Override
    public boolean transactionRelated() {
        return false;
    }

    @Override
    public boolean validate() {
        return requiredValidator.safeValidate(configurationSlot)
                &&requiredValidator.safeValidate(connectionData);
    }
}
