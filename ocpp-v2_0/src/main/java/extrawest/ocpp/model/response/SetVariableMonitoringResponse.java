package extrawest.ocpp.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import extrawest.ocpp.model.Confirmation;
import extrawest.ocpp.model.dataTypes.CustomDataType;
import extrawest.ocpp.model.dataTypes.SetMonitoringResultType;
import extrawest.ocpp.model.validation.RequiredValidator;
import extrawest.ocpp.model.validation.Validator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "customData",
        "setMonitoringResult"
})
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class SetVariableMonitoringResponse extends Confirmation {

    private final transient Validator<Object> requiredValidator = new RequiredValidator();

    /**
     * This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
     *
     */
    @JsonProperty("customData")
    @JsonPropertyDescription("This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.")
    public CustomDataType customData;
    /**
     *
     * (Required)
     *
     */
    @JsonProperty("setMonitoringResult")
    public List<SetMonitoringResultType> setMonitoringResult;

    public SetVariableMonitoringResponse(List<SetMonitoringResultType> setMonitoringResult) {
        requiredValidator.validate(setMonitoringResult);
        this.setMonitoringResult = setMonitoringResult;
    }

    public void setCustomData(CustomDataType customData) {
        this.customData = customData;
    }

    public void setSetMonitoringResult(List<SetMonitoringResultType> setMonitoringResult) {
        requiredValidator.validate(setMonitoringResult);
        this.setMonitoringResult = setMonitoringResult;
    }

    @Override
    public boolean validate() {
        return requiredValidator.safeValidate(setMonitoringResult)
                &&setMonitoringResult.stream().filter(SetMonitoringResultType::validate).count() == setMonitoringResult.size();
    }
}
