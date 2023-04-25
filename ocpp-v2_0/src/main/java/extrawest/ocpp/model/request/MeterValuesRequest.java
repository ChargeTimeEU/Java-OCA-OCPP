package extrawest.ocpp.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import extrawest.ocpp.model.RequestWithId;
import extrawest.ocpp.model.dataTypes.CustomDataType;
import extrawest.ocpp.model.dataTypes.MeterValueType;
import extrawest.ocpp.model.validation.RequiredValidator;
import extrawest.ocpp.model.validation.Validator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;


/**
 * Request_ Body
 * urn:x-enexis:ecdm:uid:2:234744
 *
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "customData",
        "evseId",
        "meterValue"
})
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class MeterValuesRequest extends RequestWithId {

    private final transient Validator<Object> requiredValidator = new RequiredValidator();

    /**
     * This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
     *
     */
    @JsonProperty("customData")
    public CustomDataType customData;
    /**
     * Request_ Body. EVSEID. Numeric_ Identifier
     * urn:x-enexis:ecdm:uid:1:571101
     * This contains a number (&gt;0) designating an EVSE of the Charging Station. ‘0’ (zero) is used to designate the main power meter.
     *
     * (Required)
     *
     */
    @JsonProperty("evseId")
    public Integer evseId;
    /**
     *
     * (Required)
     *
     */
    @JsonProperty("meterValue")
    public List<MeterValueType> meterValue;

    public MeterValuesRequest(Integer evseId, List<MeterValueType> meterValue) {
        requiredValidator.validate(evseId);
        requiredValidator.validate(meterValue);
        this.evseId = evseId;
        this.meterValue = meterValue;
    }

    public void setCustomData(CustomDataType customData) {
        this.customData = customData;
    }

    public void setEvseId(Integer evseId) {
        requiredValidator.validate(evseId);
        this.evseId = evseId;
    }

    public void setMeterValue(List<MeterValueType> meterValue) {
        requiredValidator.validate(meterValue);
        this.meterValue = meterValue;
    }

    @Override
    public boolean transactionRelated() {
        return true;
    }

    @Override
    public boolean validate() {
        return requiredValidator.safeValidate(evseId)
                &&requiredValidator.safeValidate(meterValue)
                &&meterValue.stream().filter(MeterValueType::validate).count() == meterValue.size();
    }
}
