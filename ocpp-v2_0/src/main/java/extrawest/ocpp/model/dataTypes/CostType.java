package extrawest.ocpp.model.dataTypes;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import extrawest.ocpp.model.Validatable;
import extrawest.ocpp.model.dataTypes.enums.CostKindEnumType;
import extrawest.ocpp.model.validation.RequiredValidator;
import extrawest.ocpp.model.validation.Validator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


/**
 * Cost
 * urn:x-oca:ocpp:uid:2:233258
 *
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "customData",
        "costKind",
        "amount",
        "amountMultiplier"
})
@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class CostType implements Validatable {

    private transient Validator<Object> requiredValidator = new RequiredValidator();

    /**
     * This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
     *
     */
    @JsonProperty("customData")
    private CustomDataType customDataType;
    /**
     * Cost. Cost_ Kind. Cost_ Kind_ Code
     * urn:x-oca:ocpp:uid:1:569243
     * The kind of cost referred to in the message element amount
     *
     * (Required)
     *
     */
    @JsonProperty("costKind")
    private CostKindEnumType costKind;
    /**
     * Cost. Amount. Amount
     * urn:x-oca:ocpp:uid:1:569244
     * The estimated or actual cost per kWh
     *
     * (Required)
     *
     */
    @JsonProperty("amount")
    private Integer amount;
    /**
     * Cost. Amount_ Multiplier. Integer
     * urn:x-oca:ocpp:uid:1:569245
     * Values: -3..3, The amountMultiplier defines the exponent to base 10 (dec). The final value is determined by: amount * 10 ^ amountMultiplier
     *
     *
     */
    @JsonProperty("amountMultiplier")
    private Integer amountMultiplier;

    public CostType(CostKindEnumType costKind, Integer amount) {
        this.costKind = costKind;
        this.amount = amount;
    }

    public void setCustomDataType(CustomDataType customDataType) {
        this.customDataType = customDataType;
    }

    public void setCostKind(CostKindEnumType costKind) {
        requiredValidator.validate(costKind);
        this.costKind = costKind;
    }

    public void setAmount(Integer amount) {
        requiredValidator.validate(amount);
        this.amount = amount;
    }

    public void setAmountMultiplier(Integer amountMultiplier) {
        this.amountMultiplier = amountMultiplier;
    }

    @Override
    public boolean validate() {
        return requiredValidator.safeValidate(costKind)
                &&requiredValidator.safeValidate(amount);
    }
}
