package eu.chargetime.ocpp.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import eu.chargetime.ocpp.model.RequestWithId;
import eu.chargetime.ocpp.model.dataTypes.CustomDataType;
import eu.chargetime.ocpp.model.dataTypes.SetMonitoringDataType;
import eu.chargetime.ocpp.model.validation.RequiredValidator;
import eu.chargetime.ocpp.model.validation.Validator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "customData",
        "setMonitoringData"
})
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class SetVariableMonitoringRequest extends RequestWithId {

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
    @JsonProperty("setMonitoringData")
    public List<SetMonitoringDataType> setMonitoringData;

    public SetVariableMonitoringRequest(List<SetMonitoringDataType> setMonitoringData) {
        requiredValidator.validate(setMonitoringData);
        this.setMonitoringData = setMonitoringData;
    }

    public void setCustomData(CustomDataType customData) {
        this.customData = customData;
    }

    public void setSetMonitoringData(List<SetMonitoringDataType> setMonitoringData) {
        requiredValidator.validate(setMonitoringData);
        this.setMonitoringData = setMonitoringData;
    }

    @Override
    public boolean transactionRelated() {
        return false;
    }

    @Override
    public boolean validate() {
        return requiredValidator.safeValidate(setMonitoringData)
                &&setMonitoringData.stream().filter(SetMonitoringDataType::validate).count() == setMonitoringData.size();
    }
}
