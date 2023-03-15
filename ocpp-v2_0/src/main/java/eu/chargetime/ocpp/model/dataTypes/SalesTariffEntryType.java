package eu.chargetime.ocpp.model.dataTypes;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import eu.chargetime.ocpp.model.Validatable;
import eu.chargetime.ocpp.model.validation.IntegerGreaterThanOrEqualToZeroValidator;
import eu.chargetime.ocpp.model.validation.RequiredValidator;
import eu.chargetime.ocpp.model.validation.Validator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;


/**
 * Sales_ Tariff_ Entry
 * urn:x-oca:ocpp:uid:2:233271
 *
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "customData",
        "relativeTimeInterval",
        "ePriceLevel",
        "consumptionCost"
})
@Getter
@EqualsAndHashCode
@ToString
public class SalesTariffEntryType implements Validatable {

    private transient Validator<Object> requiredValidator = new RequiredValidator();
    private transient Validator<Integer> integerGreaterThanOrEqualToZeroValidator = new IntegerGreaterThanOrEqualToZeroValidator();

    /**
     * This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
     *
     */
    @JsonProperty("customData")
    private CustomDataType customDataType;
    /**
     * Relative_ Timer_ Interval
     * urn:x-oca:ocpp:uid:2:233270
     *
     * (Required)
     *
     */
    @JsonProperty("relativeTimeInterval")
    private RelativeTimeIntervalType relativeTimeIntervalType;
    /**
     * Sales_ Tariff_ Entry. E_ Price_ Level. Unsigned_ Integer
     * urn:x-oca:ocpp:uid:1:569281
     * Defines the price level of this SalesTariffEntry (referring to NumEPriceLevels). Small values for the EPriceLevel represent a cheaper TariffEntry. Large values for the EPriceLevel represent a more expensive TariffEntry.
     *
     *
     */
    @JsonProperty("ePriceLevel")
    private Integer ePriceLevel;
    @JsonProperty("consumptionCost")
    private List<ConsumptionCostType> consumptionCostType;

    public SalesTariffEntryType(RelativeTimeIntervalType relativeTimeIntervalType) {
        requiredValidator.validate(relativeTimeIntervalType);
        this.relativeTimeIntervalType = relativeTimeIntervalType;
    }

    public void setCustomDataType(CustomDataType customDataType) {
        this.customDataType = customDataType;
    }

    public void setRelativeTimeIntervalType(RelativeTimeIntervalType relativeTimeIntervalType) {
        requiredValidator.validate(relativeTimeIntervalType);
        this.relativeTimeIntervalType = relativeTimeIntervalType;
    }

    public void setePriceLevel(Integer ePriceLevel) {
        integerGreaterThanOrEqualToZeroValidator.validate(ePriceLevel);
        this.ePriceLevel = ePriceLevel;
    }

    public void setConsumptionCostType(List<ConsumptionCostType> consumptionCostType) {
        this.consumptionCostType = consumptionCostType;
    }

    @Override
    public boolean validate() {
        return relativeTimeIntervalType.validate();
    }
}
