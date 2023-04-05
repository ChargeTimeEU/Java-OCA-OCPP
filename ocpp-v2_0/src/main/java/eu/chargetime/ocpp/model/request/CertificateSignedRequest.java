package eu.chargetime.ocpp.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import eu.chargetime.ocpp.model.RequestWithId;
import eu.chargetime.ocpp.model.dataTypes.CustomDataType;
import eu.chargetime.ocpp.model.dataTypes.enums.CertificateSigningUseEnumType;
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
        "certificateChain",
        "certificateType"
})
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CertificateSignedRequest extends RequestWithId {

    private transient Validator certificateChainValidator =
            new ValidatorBuilder().setRequired(true).addRule(OCPP2PrimDatatypes.string10000()).build();

    /**
     * This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
     *
     */
    @JsonProperty("customData")
    public CustomDataType customData;
    /**
     * The signed PEM encoded X.509 certificate. This can also contain the necessary sub CA certificates. In that case, the order of the bundle should follow the certificate chain, starting from the leaf certificate.
     *
     * The Configuration Variable &lt;<configkey-max-certificate-chain-size,MaxCertificateChainSize>> can be used to limit the maximum size of this field.
     *
     * (Required)
     *
     */
    @JsonProperty("certificateChain")
    public String certificateChain;
    /**
     * Indicates the type of the signed certificate that is returned. When omitted the certificate is used for both the 15118 connection (if implemented) and the Charging Station to CSMS connection. This field is required when a typeOfCertificate was included in the &lt;<signcertificaterequest,SignCertificateRequest>> that requested this certificate to be signed AND both the 15118 connection and the Charging Station connection are implemented.
     *
     *
     *
     */
    @JsonProperty("certificateType")
    public CertificateSigningUseEnumType certificateType;

    public CertificateSignedRequest(String certificateChain) {
        certificateChainValidator.validate(certificateChain);
        this.certificateChain = certificateChain;
    }

    public void setCustomData(CustomDataType customData) {
        this.customData = customData;
    }

    public void setCertificateChain(String certificateChain) {
        certificateChainValidator.validate(certificateChain);
        this.certificateChain = certificateChain;
    }

    public void setCertificateType(CertificateSigningUseEnumType certificateType) {
        this.certificateType = certificateType;
    }

    @Override
    public boolean transactionRelated() {
        return false;
    }

    @Override
    public boolean validate() {
        return certificateChainValidator.safeValidate(certificateChain);
    }
}
