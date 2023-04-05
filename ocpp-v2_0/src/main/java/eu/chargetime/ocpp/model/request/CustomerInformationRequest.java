package eu.chargetime.ocpp.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import eu.chargetime.ocpp.model.RequestWithId;
import eu.chargetime.ocpp.model.dataTypes.CertificateHashDataType;
import eu.chargetime.ocpp.model.dataTypes.CustomDataType;
import eu.chargetime.ocpp.model.dataTypes.IdTokenType;
import eu.chargetime.ocpp.model.validation.RequiredValidator;
import eu.chargetime.ocpp.model.validation.Validator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "customData",
        "customerCertificate",
        "idToken",
        "requestId",
        "report",
        "clear",
        "customerIdentifier"
})
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CustomerInformationRequest extends RequestWithId {

    private transient Validator<Object> requiredValidator = new RequiredValidator();

    /**
     * This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
     *
     */
    @JsonProperty("customData")
    public CustomDataType customData;

    @JsonProperty("customerCertificate")
    public CertificateHashDataType customerCertificate;
    /**
     * Contains a case insensitive identifier to use for the authorization and the type of authorization to support multiple forms of identifiers.
     *
     *
     */
    @JsonProperty("idToken")
    public IdTokenType idToken;
    /**
     * The Id of the request.
     *
     *
     * (Required)
     *
     */
    @JsonProperty("requestId")
    public Integer requestId;
    /**
     * Flag indicating whether the Charging Station should return NotifyCustomerInformationRequest messages containing information about the customer referred to.
     *
     * (Required)
     *
     */
    @JsonProperty("report")
    public Boolean report;
    /**
     * Flag indicating whether the Charging Station should clear all information about the customer referred to.
     *
     * (Required)
     *
     */
    @JsonProperty("clear")
    public Boolean clear;
    /**
     * A (e.g. vendor specific) identifier of the customer this request refers to. This field contains a custom identifier other than IdToken and Certificate.
     * One of the possible identifiers (customerIdentifier, customerIdToken or customerCertificate) should be in the request message.
     *
     *
     */
    @JsonProperty("customerIdentifier")
    public String customerIdentifier;

    public CustomerInformationRequest(Integer requestId, Boolean report, Boolean clear) {
        requiredValidator.validate(requestId);
        requiredValidator.validate(report);
        requiredValidator.validate(clear);
        this.requestId = requestId;
        this.report = report;
        this.clear = clear;
    }

    public void setCustomData(CustomDataType customData) {
        this.customData = customData;
    }

    public void setCustomerCertificate(CertificateHashDataType customerCertificate) {
        this.customerCertificate = customerCertificate;
    }

    public void setIdToken(IdTokenType idToken) {
        this.idToken = idToken;
    }

    public void setRequestId(Integer requestId) {
        requiredValidator.validate(requestId);
        this.requestId = requestId;
    }

    public void setReport(Boolean report) {
        requiredValidator.validate(report);
        this.report = report;
    }

    public void setClear(Boolean clear) {
        requiredValidator.validate(clear);
        this.clear = clear;
    }

    public void setCustomerIdentifier(String customerIdentifier) {
        this.customerIdentifier = customerIdentifier;
    }

    @Override
    public boolean transactionRelated() {
        return false;
    }

    @Override
    public boolean validate() {
        return requiredValidator.safeValidate(requestId)
                &&requiredValidator.safeValidate(report)
                &&requiredValidator.safeValidate(clear);
    }
}
