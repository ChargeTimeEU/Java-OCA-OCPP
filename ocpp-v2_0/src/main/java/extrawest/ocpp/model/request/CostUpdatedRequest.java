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

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "customData",
        "totalCost",
        "transactionId"
})
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CostUpdatedRequest extends RequestWithId {

    private final transient Validator identifierString36Validator =
            new ValidatorBuilder()
                    .addRule(OCPP2PrimDatatypes.identifierString())
                    .addRule(OCPP2PrimDatatypes.string36())
                    .setRequired(true)
                    .build();

    private transient RequiredValidator requiredValidator = new RequiredValidator();

    /**
     * This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
     *
     */
    @JsonProperty("customData")
    public CustomDataType customData;
    /**
     * Current total cost, based on the information known by the CSMS, of the transaction including taxes. In the currency configured with the configuration Variable: [&lt;<configkey-currency, Currency>>]
     *
     *
     * (Required)
     *
     */
    @JsonProperty("totalCost")
    public Float totalCost;
    /**
     * Transaction Id of the transaction the current cost are asked for.
     *
     *
     * (Required)
     *
     */
    @JsonProperty("transactionId")
    public String transactionId;

    public CostUpdatedRequest(Float totalCost, String transactionId) {
        identifierString36Validator.validate(transactionId);
        requiredValidator.validate(totalCost);
        this.totalCost = totalCost;
        this.transactionId = transactionId;
    }

    public void setCustomData(CustomDataType customData) {
        this.customData = customData;
    }

    public void setTotalCost(Float totalCost) {
        requiredValidator.validate(totalCost);
        this.totalCost = totalCost;
    }

    public void setTransactionId(String transactionId) {
        identifierString36Validator.validate(transactionId);
        this.transactionId = transactionId;
    }

    @Override
    public boolean transactionRelated() {
        return false;
    }

    @Override
    public boolean validate() {
        return identifierString36Validator.safeValidate(transactionId)
                &&requiredValidator.safeValidate(totalCost);
    }
}
