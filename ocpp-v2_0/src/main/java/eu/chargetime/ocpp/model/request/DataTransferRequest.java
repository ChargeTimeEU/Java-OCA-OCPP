package eu.chargetime.ocpp.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import eu.chargetime.ocpp.model.RequestWithId;
import eu.chargetime.ocpp.model.dataTypes.CustomDataType;
import eu.chargetime.ocpp.model.validation.OCPP2PrimDatatypes;
import eu.chargetime.ocpp.model.validation.Validator;
import eu.chargetime.ocpp.model.validation.ValidatorBuilder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "customData",
        "messageId",
        "data",
        "vendorId"
})
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class DataTransferRequest extends RequestWithId {

    private transient Validator messageIdValidator =
            new ValidatorBuilder().addRule(OCPP2PrimDatatypes.string50()).build();

    private final transient Validator vendorIdValidator =
            new ValidatorBuilder().setRequired(true).addRule(OCPP2PrimDatatypes.string255()).build();

    /**
     * This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
     *
     */
    @JsonProperty("customData")
    public CustomDataType customData;
    /**
     * May be used to indicate a specific message or implementation.
     *
     *
     */
    @JsonProperty("messageId")
    public String messageId;
    /**
     * Data without specified length or format. This needs to be decided by both parties (Open to implementation).
     *
     *
     */
    @JsonProperty("data")
    public Object data;
    /**
     * This identifies the Vendor specific implementation
     *
     *
     * (Required)
     *
     */
    @JsonProperty("vendorId")
    public String vendorId;

    public DataTransferRequest(String vendorId) {
        vendorIdValidator.validate(vendorId);
        this.vendorId = vendorId;
    }

    public void setCustomData(CustomDataType customData) {
        this.customData = customData;
    }

    public void setMessageId(String messageId) {
        messageIdValidator.validate(messageId);
        this.messageId = messageId;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setVendorId(String vendorId) {
        vendorIdValidator.validate(vendorId);
        this.vendorId = vendorId;
    }

    @Override
    public boolean transactionRelated() {
        return false;
    }

    @Override
    public boolean validate() {
        return vendorIdValidator.safeValidate(vendorId);
    }
}
