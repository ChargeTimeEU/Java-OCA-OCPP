package eu.chargetime.ocpp.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.dataTypes.ClearMonitoringResultType;
import eu.chargetime.ocpp.model.dataTypes.CustomDataType;
import eu.chargetime.ocpp.model.validation.RequiredValidator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "customData",
        "clearMonitoringResult"
})
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class ClearVariableMonitoringResponse extends Confirmation {

    private transient RequiredValidator requiredValidator = new RequiredValidator();

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
    @JsonProperty("clearMonitoringResult")
    public List<ClearMonitoringResultType> clearMonitoringResult;

    public ClearVariableMonitoringResponse(List<ClearMonitoringResultType> clearMonitoringResult) {
        requiredValidator.validate(clearMonitoringResult);
        this.clearMonitoringResult = clearMonitoringResult;
    }

    public void setCustomData(CustomDataType customData) {
        this.customData = customData;
    }

    public void setClearMonitoringResult(List<ClearMonitoringResultType> clearMonitoringResult) {
        requiredValidator.validate(clearMonitoringResult);
        this.clearMonitoringResult = clearMonitoringResult;
    }

    @Override
    public boolean validate() {
        return requiredValidator.safeValidate(clearMonitoringResult);
    }
}
