package extrawest.ocpp.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import extrawest.ocpp.model.RequestWithId;
import extrawest.ocpp.model.dataTypes.CustomDataType;
import extrawest.ocpp.model.validation.OCPP2PrimDatatypes;
import extrawest.ocpp.model.validation.Validator;
import extrawest.ocpp.model.validation.ValidatorBuilder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "customData",
        "checksum"
})
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class UnpublishFirmwareRequest extends RequestWithId {

    private final transient Validator checksumValidator =
            new ValidatorBuilder()
                    .addRule(OCPP2PrimDatatypes.identifierString())
                    .addRule(OCPP2PrimDatatypes.string32())
                    .build();

    /**
     * This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
     *
     */
    @JsonProperty("customData")
    public CustomDataType customData;
    /**
     * The MD5 checksum over the entire firmware file as a hexadecimal string of length 32.
     *
     * (Required)
     *
     */
    @JsonProperty("checksum")
    public String checksum;

    public UnpublishFirmwareRequest(String checksum) {
        checksumValidator.validate(checksum);
        this.checksum = checksum;
    }

    public void setCustomData(CustomDataType customData) {
        this.customData = customData;
    }

    public void setChecksum(String checksum) {
        checksumValidator.validate(checksum);
        this.checksum = checksum;
    }

    @Override
    public boolean transactionRelated() {
        return false;
    }

    @Override
    public boolean validate() {
        return checksumValidator.safeValidate(checksum);
    }
}
