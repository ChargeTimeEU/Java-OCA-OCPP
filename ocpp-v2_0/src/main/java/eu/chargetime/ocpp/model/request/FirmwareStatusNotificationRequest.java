package eu.chargetime.ocpp.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import eu.chargetime.ocpp.model.RequestWithId;
import eu.chargetime.ocpp.model.dataTypes.CustomDataType;
import eu.chargetime.ocpp.model.dataTypes.enums.FirmwareStatusEnumType;
import eu.chargetime.ocpp.model.validation.RequiredValidator;
import eu.chargetime.ocpp.model.validation.Validator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "customData",
        "status",
        "requestId"
})
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class FirmwareStatusNotificationRequest extends RequestWithId {

    private transient Validator<Object> requiredValidator = new RequiredValidator();

    /**
     * This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
     *
     */
    @JsonProperty("customData")
    public CustomDataType customData;
    /**
     * This contains the progress status of the firmware installation.
     *
     * (Required)
     *
     */
    @JsonProperty("status")
    public FirmwareStatusEnumType status;
    /**
     * The request id that was provided in the
     * UpdateFirmwareRequest that started this firmware update.
     * This field is mandatory, unless the message was triggered by a TriggerMessageRequest AND there is no firmware update ongoing.
     *
     *
     */
    @JsonProperty("requestId")
    public Integer requestId;

    public FirmwareStatusNotificationRequest(FirmwareStatusEnumType status) {
        requiredValidator.validate(status);
        this.status = status;
    }

    public void setCustomData(CustomDataType customData) {
        this.customData = customData;
    }

    public void setStatus(FirmwareStatusEnumType status) {
        requiredValidator.validate(status);
        this.status = status;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    @Override
    public boolean transactionRelated() {
        return false;
    }

    @Override
    public boolean validate() {
        return requiredValidator.safeValidate(status);
    }
}
