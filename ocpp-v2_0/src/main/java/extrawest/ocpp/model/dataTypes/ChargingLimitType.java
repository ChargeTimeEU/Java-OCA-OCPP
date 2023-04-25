package extrawest.ocpp.model.dataTypes;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import extrawest.ocpp.model.Validatable;
import extrawest.ocpp.model.dataTypes.enums.ChargingLimitSourceEnumType;
import extrawest.ocpp.model.validation.RequiredValidator;
import extrawest.ocpp.model.validation.Validator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


/**
 * Charging_ Limit
 * urn:x-enexis:ecdm:uid:2:234489
 *
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "customData",
        "chargingLimitSource",
        "isGridCritical"
})
@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class ChargingLimitType implements Validatable {

    private transient Validator<Object> requiredValidator = new RequiredValidator();

    /**
     * This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
     *
     */
    @JsonProperty("customData")
    public CustomDataType customData;
    /**
     * Charging_ Limit. Charging_ Limit_ Source. Charging_ Limit_ Source_ Code
     * urn:x-enexis:ecdm:uid:1:570845
     * Represents the source of the charging limit.
     *
     * (Required)
     *
     */
    @JsonProperty("chargingLimitSource")
    public ChargingLimitSourceEnumType chargingLimitSource;
    /**
     * Charging_ Limit. Is_ Grid_ Critical. Indicator
     * urn:x-enexis:ecdm:uid:1:570847
     * Indicates whether the charging limit is critical for the grid.
     *
     *
     */
    @JsonProperty("isGridCritical")
    public Boolean isGridCritical;

    public ChargingLimitType(ChargingLimitSourceEnumType chargingLimitSource) {
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

    public void setGridCritical(Boolean gridCritical) {
        isGridCritical = gridCritical;
    }

    @Override
    public boolean validate() {
        return requiredValidator.safeValidate(chargingLimitSource);
    }
}
