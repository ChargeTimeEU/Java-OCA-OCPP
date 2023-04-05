package eu.chargetime.ocpp.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import eu.chargetime.ocpp.model.RequestWithId;
import eu.chargetime.ocpp.model.dataTypes.CustomDataType;
import eu.chargetime.ocpp.model.dataTypes.enums.InstallCertificateUseEnumType;
import eu.chargetime.ocpp.model.validation.OCPP2PrimDatatypes;
import eu.chargetime.ocpp.model.validation.RequiredValidator;
import eu.chargetime.ocpp.model.validation.Validator;
import eu.chargetime.ocpp.model.validation.ValidatorBuilder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "customData",
        "certificateType",
        "certificate"
})
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class InstallCertificateRequest extends RequestWithId {

    private transient Validator certificateValidator =
            new ValidatorBuilder()
                    .setRequired(true)
                    .addRule(OCPP2PrimDatatypes.string5500())
                    .build();

    private transient Validator<Object> requiredValidator = new RequiredValidator();

    /**
     * This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
     *
     */
    @JsonProperty("customData")
    public CustomDataType customData;
    /**
     * Indicates the certificate type that is sent.
     *
     * (Required)
     *
     */
    @JsonProperty("certificateType")
    public InstallCertificateUseEnumType certificateType;
    /**
     * A PEM encoded X.509 certificate.
     *
     * (Required)
     *
     */
    @JsonProperty("certificate")
    public String certificate;

    public InstallCertificateRequest(InstallCertificateUseEnumType certificateType, String certificate) {
        certificateValidator.validate(certificate);
        requiredValidator.validate(certificateType);
        this.certificateType = certificateType;
        this.certificate = certificate;
    }

    public void setCustomData(CustomDataType customData) {
        this.customData = customData;
    }

    public void setCertificateType(InstallCertificateUseEnumType certificateType) {
        requiredValidator.validate(certificateType);
        this.certificateType = certificateType;
    }

    public void setCertificate(String certificate) {
        certificateValidator.validate(certificate);
        this.certificate = certificate;
    }

    @Override
    public boolean transactionRelated() {
        return false;
    }

    @Override
    public boolean validate() {
        return certificateValidator.safeValidate(certificate)
                &&requiredValidator.safeValidate(certificateType);
    }
}
