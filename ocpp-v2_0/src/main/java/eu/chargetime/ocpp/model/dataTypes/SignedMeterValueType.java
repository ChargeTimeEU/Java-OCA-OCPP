package eu.chargetime.ocpp.model.dataTypes;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import eu.chargetime.ocpp.model.Validatable;
import eu.chargetime.ocpp.model.validation.OCPP2PrimDatatypes;
import eu.chargetime.ocpp.model.validation.Validator;
import eu.chargetime.ocpp.model.validation.ValidatorBuilder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;


/**
 * Represent a signed version of the meter value.
 *
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "customData",
        "signedMeterData",
        "signingMethod",
        "encodingMethod",
        "publicKey"
})
@Getter
@EqualsAndHashCode
@ToString
public class SignedMeterValueType implements Validatable {

    private transient Validator signedMeterDataValidator =
            new ValidatorBuilder().setRequired(true).addRule(OCPP2PrimDatatypes.string2500()).build();

    private transient Validator signingMethodValidator =
            new ValidatorBuilder().setRequired(true).addRule(OCPP2PrimDatatypes.string50()).build();

    private transient Validator encodingMethodValidator =
            new ValidatorBuilder().setRequired(true).addRule(OCPP2PrimDatatypes.string50()).build();

    private transient Validator publicKeyValidator =
            new ValidatorBuilder().setRequired(true).addRule(OCPP2PrimDatatypes.string2500()).build();

    /**
     * This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
     *
     */
    @JsonProperty("customData")
    public CustomDataType customDataType;
    /**
     * Base64 encoded, contains the signed data which might contain more then just the meter value. It can contain information like timestamps, reference to a customer etc.
     *
     * (Required)
     *
     */
    @JsonProperty("signedMeterData")
    public String signedMeterData;
    /**
     * Method used to create the digital signature.
     *
     * (Required)
     *
     */
    @JsonProperty("signingMethod")
    public String signingMethod;
    /**
     * Method used to encode the meter values before applying the digital signature algorithm.
     *
     * (Required)
     *
     */
    @JsonProperty("encodingMethod")
    public String encodingMethod;
    /**
     * Base64 encoded, sending depends on configuration variable _PublicKeyWithSignedMeterValue_.
     *
     * (Required)
     *
     */
    @JsonProperty("publicKey")
    public String publicKey;

    public SignedMeterValueType(String signedMeterData, String signingMethod, String encodingMethod, String publicKey) {
        signedMeterDataValidator.validate(signedMeterData);
        signingMethodValidator.validate(signingMethod);
        encodingMethodValidator.validate(encodingMethod);
        publicKeyValidator.validate(publicKey);
        this.signedMeterData = signedMeterData;
        this.signingMethod = signingMethod;
        this.encodingMethod = encodingMethod;
        this.publicKey = publicKey;
    }

    public void setCustomDataType(CustomDataType customDataType) {
        this.customDataType = customDataType;
    }

    public void setSignedMeterData(String signedMeterData) {
        signedMeterDataValidator.validate(signedMeterData);
        this.signedMeterData = signedMeterData;
    }

    public void setSigningMethod(String signingMethod) {
        signingMethodValidator.validate(signingMethod);
        this.signingMethod = signingMethod;
    }

    public void setEncodingMethod(String encodingMethod) {
        encodingMethodValidator.validate(encodingMethod);
        this.encodingMethod = encodingMethod;
    }

    public void setPublicKey(String publicKey) {
        publicKeyValidator.validate(publicKey);
        this.publicKey = publicKey;
    }

    @Override
    public boolean validate() {
        return signedMeterDataValidator.safeValidate(signedMeterData)
        &&signingMethodValidator.safeValidate(signingMethod)
        &&encodingMethodValidator.safeValidate(encodingMethod)
        &&publicKeyValidator.safeValidate(publicKey);
    }
}
