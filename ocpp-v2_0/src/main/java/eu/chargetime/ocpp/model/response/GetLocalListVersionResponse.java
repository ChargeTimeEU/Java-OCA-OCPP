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

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "customData",
        "versionNumber"
})
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class GetLocalListVersionResponse extends Confirmation {

    private transient Validator<Object> requiredValidator = new RequiredValidator();

    /**
     * This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
     *
     */
    @JsonProperty("customData")
    public CustomDataType customData;
    /**
     * This contains the current version number of the local authorization list in the Charging Station.
     *
     * (Required)
     *
     */
    @JsonProperty("versionNumber")
    public Integer versionNumber;

    public GetLocalListVersionResponse(Integer versionNumber) {
        requiredValidator.validate(versionNumber);
        this.versionNumber = versionNumber;
    }

    public void setCustomData(CustomDataType customData) {
        this.customData = customData;
    }

    public void setVersionNumber(Integer versionNumber) {
        requiredValidator.validate(versionNumber);
        this.versionNumber = versionNumber;
    }

    @Override
    public boolean validate() {
        return requiredValidator.safeValidate(versionNumber);
    }
}
