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

import java.time.LocalDateTime;
import java.util.List;


/**
 * Meter_ Value
 * urn:x-oca:ocpp:uid:2:233265
 * Collection of one or more sampled values in MeterValuesRequest and TransactionEvent. All sampled values in a MeterValue are sampled at the same point in time.
 *
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "customData",
        "sampledValue",
        "timestamp"
})
@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class MeterValueType implements Validatable {

    private final transient Validator<Object> requiredValidator = new RequiredValidator();

    /**
     * This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
     *
     */
    @JsonProperty("customData")
    public CustomDataType customDataType;
    /**
     *
     * (Required)
     *
     */
    @JsonProperty("sampledValue")
    public List<SampledValueType> sampledValueType;
    /**
     * Meter_ Value. Timestamp. Date_ Time
     * urn:x-oca:ocpp:uid:1:569259
     * Timestamp for measured value(s).
     *
     * (Required)
     *
     */
    @JsonProperty("timestamp")
    public LocalDateTime timestamp;

    public MeterValueType(List<SampledValueType> sampledValueType, LocalDateTime timestamp) {
        requiredValidator.validate(sampledValueType);
        requiredValidator.validate(timestamp);
        this.sampledValueType = sampledValueType;
        this.timestamp = timestamp;
    }

    public void setCustomDataType(CustomDataType customDataType) {
        this.customDataType = customDataType;
    }

    public void setSampledValueType(List<SampledValueType> sampledValueType) {
        requiredValidator.validate(sampledValueType);
        this.sampledValueType = sampledValueType;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        requiredValidator.validate(timestamp);
        this.timestamp = timestamp;
    }

    @Override
    public boolean validate() {
        return requiredValidator.safeValidate(timestamp)
                &&requiredValidator.safeValidate(sampledValueType)
                &&sampledValueType.stream().filter(SampledValueType::validate).count()==sampledValueType.size();
    }
}
