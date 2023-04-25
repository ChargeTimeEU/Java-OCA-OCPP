package extrawest.ocpp.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import extrawest.ocpp.model.RequestWithId;
import extrawest.ocpp.model.dataTypes.CustomDataType;
import extrawest.ocpp.model.dataTypes.IdTokenType;
import extrawest.ocpp.model.dataTypes.OCSPRequestDataType;
import extrawest.ocpp.model.validation.OCPP2PrimDatatypes;
import extrawest.ocpp.model.validation.RequiredValidator;
import extrawest.ocpp.model.validation.Validator;
import extrawest.ocpp.model.validation.ValidatorBuilder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "customData",
        "idToken",
        "certificate",
        "iso15118CertificateHashData"
})
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class AuthorizeRequest extends RequestWithId {

    private transient Validator certificateValidator =
            new ValidatorBuilder().addRule(OCPP2PrimDatatypes.string5500()).build();

    private transient RequiredValidator requiredValidator = new RequiredValidator();

    /**
     * This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
     *
     */
    @JsonProperty("customData")
    public CustomDataType customData;
    /**
     * Contains a case insensitive identifier to use for the authorization and the type of authorization to support multiple forms of identifiers.
     *
     * (Required)
     *
     */
    @JsonProperty("idToken")
    public IdTokenType idToken;
    /**
     * The X.509 certificated presented by EV and encoded in PEM format.
     *
     *
     */
    @JsonProperty("certificate")
    public String certificate;

    @JsonProperty("iso15118CertificateHashData")
    public List<OCSPRequestDataType> iso15118CertificateHashData;

    @Override
    public boolean transactionRelated() {
        return false;
    }

    public AuthorizeRequest(IdTokenType idTokenType) {
        this.idToken = idTokenType;
    }

    public void setCustomData(CustomDataType customData) {
        this.customData = customData;
    }

    public void setIdToken(IdTokenType idToken) {
        this.idToken = idToken;
    }

    public void setCertificate(String certificate) {
        certificateValidator.validate(certificate);
        this.certificate = certificate;
    }

    public void setIso15118CertificateHashData(List<OCSPRequestDataType> iso15118CertificateHashData) {
        this.iso15118CertificateHashData = iso15118CertificateHashData;
    }

    @Override
    public boolean validate() {
        return requiredValidator.safeValidate(idToken);
    }
}
