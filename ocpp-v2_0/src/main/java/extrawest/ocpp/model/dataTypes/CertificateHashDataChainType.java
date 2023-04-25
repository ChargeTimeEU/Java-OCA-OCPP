package extrawest.ocpp.model.dataTypes;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import extrawest.ocpp.model.Validatable;
import extrawest.ocpp.model.dataTypes.enums.GetCertificateIdUseEnumType;
import extrawest.ocpp.model.validation.RequiredValidator;
import extrawest.ocpp.model.validation.Validator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "customData",
        "certificateHashData",
        "certificateType",
        "childCertificateHashData"
})
@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class CertificateHashDataChainType implements Validatable {

    private transient Validator<Object> requiredValidator = new RequiredValidator();


    /**
     * This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
     *
     */
    @JsonProperty("customData")
    public CustomDataType customData;
    /**
     *
     * (Required)
     *
     */
    @JsonProperty("certificateHashData")
    public CertificateHashDataType certificateHashData;
    /**
     * Indicates the type of the requested certificate(s).
     *
     * (Required)
     *
     */
    @JsonProperty("certificateType")
    public GetCertificateIdUseEnumType certificateType;

    @JsonProperty("childCertificateHashData")
    public List<CertificateHashDataType> childCertificateHashData;

    public CertificateHashDataChainType(CertificateHashDataType certificateHashData, GetCertificateIdUseEnumType certificateType) {
        requiredValidator.validate(certificateType);
        requiredValidator.validate(certificateHashData);
        this.certificateHashData = certificateHashData;
        this.certificateType = certificateType;
    }

    public void setCustomData(CustomDataType customData) {
        this.customData = customData;
    }

    public void setCertificateHashData(CertificateHashDataType certificateHashData) {
        this.certificateHashData = certificateHashData;
    }

    public void setCertificateType(GetCertificateIdUseEnumType certificateType) {
        requiredValidator.validate(certificateType);
        this.certificateType = certificateType;
    }

    public void setChildCertificateHashData(List<CertificateHashDataType> childCertificateHashData) {
        requiredValidator.validate(certificateHashData);
        this.childCertificateHashData = childCertificateHashData;
    }

    @Override
    public boolean validate() {
        return requiredValidator.safeValidate(certificateType)
                && requiredValidator.safeValidate(certificateHashData)
                &&certificateHashData.validate();
    }
}
