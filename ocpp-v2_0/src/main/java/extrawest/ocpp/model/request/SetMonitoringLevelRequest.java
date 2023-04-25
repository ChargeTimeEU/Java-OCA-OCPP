package extrawest.ocpp.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import extrawest.ocpp.model.RequestWithId;
import extrawest.ocpp.model.dataTypes.CustomDataType;
import extrawest.ocpp.model.validation.IntegerBetweenZeroAndNine;
import extrawest.ocpp.model.validation.Validator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "customData",
        "severity"
})
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class SetMonitoringLevelRequest extends RequestWithId {

    private final transient Validator<Integer> integerBetweenZeroAndNineValidator = new IntegerBetweenZeroAndNine();

    /**
     * This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
     *
     */
    @JsonProperty("customData")
    public CustomDataType customData;
    /**
     * The Charging Station SHALL only report events with a severity number lower than or equal to this severity.
     * The severity range is 0-9, with 0 as the highest and 9 as the lowest severity level.
     *
     * The severity levels have the following meaning: +
     * *0-Danger* +
     * Indicates lives are potentially in danger. Urgent attention is needed and action should be taken immediately. +
     * *1-Hardware Failure* +
     * Indicates that the Charging Station is unable to continue regular operations due to Hardware issues. Action is required. +
     * *2-System Failure* +
     * Indicates that the Charging Station is unable to continue regular operations due to software or minor hardware issues. Action is required. +
     * *3-Critical* +
     * Indicates a critical error. Action is required. +
     * *4-Error* +
     * Indicates a non-urgent error. Action is required. +
     * *5-Alert* +
     * Indicates an alert event. Default severity for any type of monitoring event. +
     * *6-Warning* +
     * Indicates a warning event. Action may be required. +
     * *7-Notice* +
     * Indicates an unusual event. No immediate action is required. +
     * *8-Informational* +
     * Indicates a regular operational event. May be used for reporting, measuring throughput, etc. No action is required. +
     * *9-Debug* +
     * Indicates information useful to developers for debugging, not useful during operations.
     *
     *
     *
     * (Required)
     *
     */
    @JsonProperty("severity")
    public Integer severity;

    public SetMonitoringLevelRequest(Integer severity) {
        integerBetweenZeroAndNineValidator.validate(severity);
        this.severity = severity;
    }

    public void setCustomData(CustomDataType customData) {
        this.customData = customData;
    }

    public void setSeverity(Integer severity) {
        integerBetweenZeroAndNineValidator.validate(severity);
        this.severity = severity;
    }

    @Override
    public boolean transactionRelated() {
        return false;
    }

    @Override
    public boolean validate() {
        return integerBetweenZeroAndNineValidator.safeValidate(severity);
    }
}
