package extrawest.ocpp.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import extrawest.ocpp.model.Confirmation;
import extrawest.ocpp.model.dataTypes.CustomDataType;
import extrawest.ocpp.model.dataTypes.StatusInfoType;
import extrawest.ocpp.model.dataTypes.enums.GetCertificateStatusEnumType;
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
        "status",
        "statusInfo",
        "ocspResult"
})
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class GetCertificateStatusResponse extends Confirmation {

    private transient Validator ocspResultValidator =
            new ValidatorBuilder()
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
     * This indicates whether the charging station was able to retrieve the OCSP certificate status.
     *
     * (Required)
     *
     */
    @JsonProperty("status")
    public GetCertificateStatusEnumType status;
    /**
     * Element providing more information about the status.
     *
     *
     */
    @JsonProperty("statusInfo")
    public StatusInfoType statusInfo;
    /**
     * OCSPResponse class as defined in &lt;<ref-ocpp_security_24, IETF RFC 6960>>. DER encoded (as defined in <<ref-ocpp_security_24, IETF RFC 6960>>), and then base64 encoded. MAY only be omitted when status is not Accepted.
     *
     *
     */
    @JsonProperty("ocspResult")
    public String ocspResult;

    public GetCertificateStatusResponse(GetCertificateStatusEnumType status) {
        requiredValidator.validate(status);
        this.status = status;
    }

    public void setCustomData(CustomDataType customData) {
        this.customData = customData;
    }

    public void setStatus(GetCertificateStatusEnumType status) {
        this.status = status;
    }

    public void setStatusInfo(StatusInfoType statusInfo) {
        this.statusInfo = statusInfo;
    }

    public void setOcspResult(String ocspResult) {
        ocspResultValidator.validate(ocspResult);
        this.ocspResult = ocspResult;
    }

    @Override
    public boolean validate() {
        return requiredValidator.safeValidate(status);
    }
}
