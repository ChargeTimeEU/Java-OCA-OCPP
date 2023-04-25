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


/**
 * Relative_ Timer_ Interval
 * urn:x-oca:ocpp:uid:2:233270
 *
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "customData",
        "start",
        "duration"
})
@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class RelativeTimeIntervalType implements Validatable {

    private transient Validator<Object> requiredValidator = new RequiredValidator();

    /**
     * This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
     *
     */
    @JsonProperty("customData")
    private CustomDataType customDataType;
    /**
     * Relative_ Timer_ Interval. Start. Elapsed_ Time
     * urn:x-oca:ocpp:uid:1:569279
     * Start of the interval, in seconds from NOW.
     *
     * (Required)
     *
     */
    @JsonProperty("start")
    private Integer start;
    /**
     * Relative_ Timer_ Interval. Duration. Elapsed_ Time
     * urn:x-oca:ocpp:uid:1:569280
     * Duration of the interval, in seconds.
     *
     *
     */
    @JsonProperty("duration")
    private Integer duration;

    public RelativeTimeIntervalType(Integer start) {
        requiredValidator.validate(start);
        this.start = start;
    }

    public void setCustomDataType(CustomDataType customDataType) {
        this.customDataType = customDataType;
    }

    public void setStart(Integer start) {
        requiredValidator.validate(start);
        this.start = start;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    @Override
    public boolean validate() {
        return requiredValidator.safeValidate(start);
    }
}
