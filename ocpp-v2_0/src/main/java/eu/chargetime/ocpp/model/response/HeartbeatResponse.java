package eu.chargetime.ocpp.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.dataTypes.CustomDataType;
import eu.chargetime.ocpp.model.validation.RequiredValidator;
import eu.chargetime.ocpp.model.validation.Validator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "customData",
        "currentTime"
})
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class HeartbeatResponse extends Confirmation {

    private transient Validator<Object> requiredValidator = new RequiredValidator();

    /**
     * This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
     *
     */
    @JsonProperty("customData")
    public CustomDataType customData;
    /**
     * Contains the current time of the CSMS.
     *
     * (Required)
     *
     */
    @JsonProperty("currentTime")
    public Date currentTime;

    public HeartbeatResponse(Date currentTime) {
        requiredValidator.validate(currentTime);
        this.currentTime = currentTime;
    }

    public void setCustomData(CustomDataType customData) {
        this.customData = customData;
    }

    public void setCurrentTime(Date currentTime) {
        requiredValidator.validate(currentTime);
        this.currentTime = currentTime;
    }

    @Override
    public boolean validate() {
        return requiredValidator.safeValidate(currentTime);
    }
}
