package extrawest.ocpp.model.dataTypes;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import extrawest.ocpp.model.Validatable;
import extrawest.ocpp.model.dataTypes.enums.HashAlgorithmEnumType;
import extrawest.ocpp.model.validation.OCPP2PrimDatatypes;
import extrawest.ocpp.model.validation.RequiredValidator;
import extrawest.ocpp.model.validation.Validator;
import extrawest.ocpp.model.validation.ValidatorBuilder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "customData",
        "hashAlgorithm",
        "issuerNameHash",
        "issuerKeyHash",
        "serialNumber",
        "responderURL"
})
@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class OCSPRequestDataType implements Validatable {

    private transient Validator<Object> requiredValidator = new RequiredValidator();
    private final transient Validator issuerNameHashValidator =
            new ValidatorBuilder().setRequired(true).addRule(OCPP2PrimDatatypes.string128()).build();
    private final transient Validator issuerKeyHashValidator =
            new ValidatorBuilder().setRequired(true).addRule(OCPP2PrimDatatypes.string128()).build();
    private final transient Validator serialNumberValidator =
            new ValidatorBuilder().setRequired(true).addRule(OCPP2PrimDatatypes.string40()).build();
    private final transient Validator responderURLValidator =
            new ValidatorBuilder().setRequired(true).addRule(OCPP2PrimDatatypes.string512()).build();

    /**
     * This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
     *
     */
    @JsonProperty("customData")
    public CustomDataType customDataType;
    /**
     * Used algorithms for the hashes provided.
     *
     * (Required)
     *
     */
    @JsonProperty("hashAlgorithm")
    public HashAlgorithmEnumType hashAlgorithm;
    /**
     * Hashed value of the Issuer DN (Distinguished Name).
     *
     *
     * (Required)
     *
     */
    @JsonProperty("issuerNameHash")
    public String issuerNameHash;
    /**
     * Hashed value of the issuers public key
     *
     * (Required)
     *
     */
    @JsonProperty("issuerKeyHash")
    public String issuerKeyHash;
    /**
     * The serial number of the certificate.
     *
     * (Required)
     *
     */
    @JsonProperty("serialNumber")
    public String serialNumber;
    /**
     * This contains the responder URL (Case insensitive).
     *
     *
     * (Required)
     *
     */
    @JsonProperty("responderURL")
    public String responderURL;

    public OCSPRequestDataType(HashAlgorithmEnumType hashAlgorithm, String issuerNameHash, String issuerKeyHash, String serialNumber, String responderURL) {
        requiredValidator.validate(hashAlgorithm);
        issuerNameHashValidator.validate(issuerNameHash);
        issuerKeyHashValidator.validate(issuerKeyHash);
        serialNumberValidator.validate(serialNumber);
        responderURLValidator.validate(responderURL);
        this.hashAlgorithm = hashAlgorithm;
        this.issuerNameHash = issuerNameHash;
        this.issuerKeyHash = issuerKeyHash;
        this.serialNumber = serialNumber;
        this.responderURL = responderURL;
    }

    public void setCustomDataType(CustomDataType customDataType) {
        this.customDataType = customDataType;
    }

    public void setHashAlgorithm(HashAlgorithmEnumType hashAlgorithm) {
        requiredValidator.validate(hashAlgorithm);
        this.hashAlgorithm = hashAlgorithm;
    }

    public void setIssuerNameHash(String issuerNameHash) {
        issuerNameHashValidator.validate(issuerNameHash);
        this.issuerNameHash = issuerNameHash;
    }

    public void setIssuerKeyHash(String issuerKeyHash) {
        issuerKeyHashValidator.validate(issuerKeyHash);
        this.issuerKeyHash = issuerKeyHash;
    }

    public void setSerialNumber(String serialNumber) {
        serialNumberValidator.validate(serialNumber);
        this.serialNumber = serialNumber;
    }

    public void setResponderURL(String responderURL) {
        responderURLValidator.validate(responderURL);
        this.responderURL = responderURL;
    }

    @Override
    public boolean validate() {
        return issuerNameHashValidator.safeValidate(issuerNameHash)
                &&issuerKeyHashValidator.safeValidate(issuerKeyHash)
                &&requiredValidator.safeValidate(hashAlgorithm)
                &&serialNumberValidator.safeValidate(serialNumber)
                &&responderURLValidator.safeValidate(responderURL);
    }
}
