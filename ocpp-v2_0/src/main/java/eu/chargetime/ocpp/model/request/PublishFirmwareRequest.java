package eu.chargetime.ocpp.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import eu.chargetime.ocpp.model.RequestWithId;
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
        "location",
        "retries",
        "checksum",
        "requestId",
        "retryInterval"
})
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class PublishFirmwareRequest extends RequestWithId {

    private transient Validator<Object> requiredValidator = new RequiredValidator();

    /**
     * This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
     *
     */
    @JsonProperty("customData")
    public CustomDataType customData;
    /**
     * This contains a string containing a URI pointing to a
     * location from which to retrieve the firmware.
     *
     * (Required)
     *
     */
    @JsonProperty("location")
    public String location;
    /**
     * This specifies how many times Charging Station must try
     * to download the firmware before giving up. If this field is not
     * present, it is left to Charging Station to decide how many times it wants to retry.
     *
     *
     */
    @JsonProperty("retries")
    public Integer retries;
    /**
     * The MD5 checksum over the entire firmware file as a hexadecimal string of length 32.
     *
     * (Required)
     *
     */
    @JsonProperty("checksum")
    public String checksum;
    /**
     * The Id of the request.
     *
     * (Required)
     *
     */
    @JsonProperty("requestId")
    public Integer requestId;
    /**
     * The interval in seconds
     * after which a retry may be
     * attempted. If this field is not
     * present, it is left to Charging
     * Station to decide how long to wait
     * between attempts.
     *
     *
     */
    @JsonProperty("retryInterval")
    public Integer retryInterval;

    public PublishFirmwareRequest(String location, String checksum, Integer requestId) {
        requiredValidator.validate(location);
        requiredValidator.validate(checksum);
        requiredValidator.validate(requestId);
        this.location = location;
        this.checksum = checksum;
        this.requestId = requestId;
    }

    public void setCustomData(CustomDataType customData) {
        this.customData = customData;
    }

    public void setLocation(String location) {
        requiredValidator.validate(location);
        this.location = location;
    }

    public void setRetries(Integer retries) {
        this.retries = retries;
    }

    public void setChecksum(String checksum) {
        requiredValidator.validate(checksum);
        this.checksum = checksum;
    }

    public void setRequestId(Integer requestId) {
        requiredValidator.validate(requestId);
        this.requestId = requestId;
    }

    public void setRetryInterval(Integer retryInterval) {
        this.retryInterval = retryInterval;
    }

    @Override
    public boolean transactionRelated() {
        return false;
    }

    @Override
    public boolean validate() {
        return requiredValidator.safeValidate(location)
                &&requiredValidator.safeValidate(checksum)
                &&requiredValidator.safeValidate(requestId);
    }
}
