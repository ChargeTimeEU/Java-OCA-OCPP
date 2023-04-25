package extrawest.ocpp.model.dataTypes;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import extrawest.ocpp.model.Validatable;
import extrawest.ocpp.model.validation.OCPP2PrimDatatypes;
import extrawest.ocpp.model.validation.Validator;
import extrawest.ocpp.model.validation.ValidatorBuilder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Objects;


/**
 * Represents a UnitOfMeasure with a multiplier
 *
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "customData",
        "unit",
        "multiplier"
})
@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class UnitOfMeasureType implements Validatable {

    private transient Validator unitValidator =
            new ValidatorBuilder().addRule(OCPP2PrimDatatypes.string20()).build();

    /**
     * This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
     *
     */
    @JsonProperty("customData")
    public CustomDataType customDataType;
    /**
     * Unit of the value. Default = "Wh" if the (default) measurand is an "Energy" type.
     * This field SHALL use a value from the list Standardized Units of Measurements in Part 2 Appendices.
     * If an applicable unit is available in that list, otherwise a "custom" unit might be used.
     *
     *
     */
    @JsonProperty("unit")
    public String unit = "Wh";
    /**
     * Multiplier, this value represents the exponent to base 10. I.e. multiplier 3 means 10 raised to the 3rd power. Default is 0.
     *
     *
     */
    @JsonProperty("multiplier")
    public Integer multiplier = 0;

    public void setCustomDataType(CustomDataType customDataType) {
        this.customDataType = customDataType;
    }

    public void setUnit(String unit) {
        unitValidator.validate(unit);
        this.unit = unit;
    }

    public void setMultiplier(Integer multiplier) {
        this.multiplier = multiplier;
    }

    @Override
    public boolean validate() {
        if (!Objects.equals(unit, "")) return unitValidator.safeValidate(unit);
        return true;
    }
}
