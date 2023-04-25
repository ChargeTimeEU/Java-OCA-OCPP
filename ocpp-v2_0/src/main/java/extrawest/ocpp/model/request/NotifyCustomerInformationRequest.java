package extrawest.ocpp.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import extrawest.ocpp.model.RequestWithId;
import extrawest.ocpp.model.dataTypes.CustomDataType;
import extrawest.ocpp.model.validation.OCPP2PrimDatatypes;
import extrawest.ocpp.model.validation.RequiredValidator;
import extrawest.ocpp.model.validation.Validator;
import extrawest.ocpp.model.validation.ValidatorBuilder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "customData",
        "data",
        "tbc",
        "seqNo",
        "generatedAt",
        "requestId"
})
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class NotifyCustomerInformationRequest extends RequestWithId {

    private transient Validator<Object> requiredValidator = new RequiredValidator();

    private transient Validator dataValidator =
            new ValidatorBuilder()
                    .setRequired(true)
                    .addRule(OCPP2PrimDatatypes.string512())
                    .build();

    /**
     * This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
     *
     */
    @JsonProperty("customData")
    public CustomDataType customData;
    /**
     * (Part of) the requested data. No format specified in which the data is returned. Should be human readable.
     *
     * (Required)
     *
     */
    @JsonProperty("data")
    public String data;
    /**
     * “to be continued” indicator. Indicates whether another part of the monitoringData follows in an upcoming notifyMonitoringReportRequest message. Default value when omitted is false.
     *
     *
     */
    @JsonProperty("tbc")
    public Boolean tbc = false;
    /**
     * Sequence number of this message. First message starts at 0.
     *
     * (Required)
     *
     */
    @JsonProperty("seqNo")
    public Integer seqNo;
    /**
     * Timestamp of the moment this message was generated at the Charging Station.
     *
     * (Required)
     *
     */
    @JsonProperty("generatedAt")
    public LocalDateTime generatedAt;
    /**
     * The Id of the request.
     *
     *
     * (Required)
     *
     */
    @JsonProperty("requestId")
    public Integer requestId;

    public NotifyCustomerInformationRequest(String data, Integer seqNo, LocalDateTime generatedAt, Integer requestId) {
        dataValidator.validate(data);
        requiredValidator.validate(seqNo);
        requiredValidator.validate(generatedAt);
        requiredValidator.validate(requestId);
        this.data = data;
        this.seqNo = seqNo;
        this.generatedAt = generatedAt;
        this.requestId = requestId;
    }

    public void setCustomData(CustomDataType customData) {
        this.customData = customData;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setTbc(Boolean tbc) {
        this.tbc = tbc;
    }

    public void setSeqNo(Integer seqNo) {
        requiredValidator.validate(seqNo);
        this.seqNo = seqNo;
    }

    public void setGeneratedAt(LocalDateTime generatedAt) {
        requiredValidator.validate(generatedAt);
        this.generatedAt = generatedAt;
    }

    public void setRequestId(Integer requestId) {
        requiredValidator.validate(requestId);
        this.requestId = requestId;
    }

    @Override
    public boolean transactionRelated() {
        return false;
    }

    @Override
    public boolean validate() {
        return dataValidator.safeValidate(data)&&
                requiredValidator.safeValidate(seqNo)
                &&requiredValidator.safeValidate(generatedAt)
                &&requiredValidator.safeValidate(requestId);
    }
}
