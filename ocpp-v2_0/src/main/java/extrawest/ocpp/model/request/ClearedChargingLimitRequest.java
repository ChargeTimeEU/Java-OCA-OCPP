package extrawest.ocpp.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import extrawest.ocpp.model.RequestWithId;
import extrawest.ocpp.model.dataTypes.CustomDataType;
import extrawest.ocpp.model.dataTypes.enums.ChargingLimitSourceEnumType;
import extrawest.ocpp.model.validation.RequiredValidator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "customData",
        "chargingLimitSource",
        "evseId"
})
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class ClearedChargingLimitRequest extends RequestWithId {

    private transient RequiredValidator requiredValidator = new RequiredValidator();

    /**
     * This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
     *
     */
    @JsonProperty("customData")
    public CustomDataType customData;
    /**
     * Source of the charging limit.
     *
     * (Required)
     *
     */
    @JsonProperty("chargingLimitSource")
    public ChargingLimitSourceEnumType chargingLimitSource;
    /**
     * EVSE Identifier.
     *
     *
     */
    @JsonProperty("evseId")
    public Integer evseId;

    public ClearedChargingLimitRequest(ChargingLimitSourceEnumType chargingLimitSource) {
        requiredValidator.validate(chargingLimitSource);
        this.chargingLimitSource = chargingLimitSource;
    }

    public void setCustomData(CustomDataType customData) {
        this.customData = customData;
    }

    public void setChargingLimitSource(ChargingLimitSourceEnumType chargingLimitSource) {
        requiredValidator.validate(chargingLimitSource);
        this.chargingLimitSource = chargingLimitSource;
    }

    public void setEvseId(Integer evseId) {
        this.evseId = evseId;
    }

    @Override
    public boolean transactionRelated() {
        return false;
    }

    @Override
    public boolean validate() {
        return requiredValidator.safeValidate(chargingLimitSource);
    }
}
