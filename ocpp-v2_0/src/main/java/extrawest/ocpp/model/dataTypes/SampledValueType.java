package extrawest.ocpp.model.dataTypes;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import extrawest.ocpp.model.Validatable;
import extrawest.ocpp.model.validation.RequiredValidator;
import extrawest.ocpp.model.validation.Validator;
import extrawest.ocpp.model.dataTypes.enums.LocationEnumType;
import extrawest.ocpp.model.dataTypes.enums.MeasurandEnumType;
import extrawest.ocpp.model.dataTypes.enums.PhaseEnumType;
import extrawest.ocpp.model.dataTypes.enums.ReadingContextEnumType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


/**
 * Sampled_ Value
 * urn:x-oca:ocpp:uid:2:233266
 * Single sampled value in MeterValues. Each value can be accompanied by optional fields.
 *
 * To save on mobile data usage, default values of all of the optional fields are such that. The value without any additional fields will be interpreted, as a register reading of active import energy in Wh (Watt-hour) units.
 *
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "customData",
        "value",
        "context",
        "measurand",
        "phase",
        "location",
        "signedMeterValue",
        "unitOfMeasure"
})
@EqualsAndHashCode
@Getter
@ToString
@NoArgsConstructor
public class SampledValueType implements Validatable {

    private final transient Validator<Object> requiredValidator = new RequiredValidator();

    /**
     * This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
     *
     */
    @JsonProperty("customData")
    public CustomDataType customDataType;
    /**
     * Sampled_ Value. Value. Measure
     * urn:x-oca:ocpp:uid:1:569260
     * Indicates the measured value.
     *
     *
     * (Required)
     *
     */
    @JsonProperty("value")
    public Double value;
    /**
     * Sampled_ Value. Context. Reading_ Context_ Code
     * urn:x-oca:ocpp:uid:1:569261
     * Type of detail value: start, end or sample. Default = "Sample.Periodic"
     *
     *
     */
    @JsonProperty("context")
    public ReadingContextEnumType context = ReadingContextEnumType.fromValue("Sample.Periodic");
    /**
     * Sampled_ Value. Measurand. Measurand_ Code
     * urn:x-oca:ocpp:uid:1:569263
     * Type of measurement. Default = "Energy.Active.Import.Register"
     *
     *
     */
    @JsonProperty("measurand")
    public MeasurandEnumType measurand = MeasurandEnumType.ENERGY_ACTIVE_IMPORT_REGISTER;
    /**
     * Sampled_ Value. Phase. Phase_ Code
     * urn:x-oca:ocpp:uid:1:569264
     * Indicates how the measured value is to be interpreted. For instance between L1 and neutral (L1-N) Please note that not all values of phase are applicable to all Measurands. When phase is absent, the measured value is interpreted as an overall value.
     *
     *
     */
    @JsonProperty("phase")
    public PhaseEnumType phase;
    /**
     * Sampled_ Value. Location. Location_ Code
     * urn:x-oca:ocpp:uid:1:569265
     * Indicates where the measured value has been sampled. Default = "Outlet"
     *
     *
     *
     */
    @JsonProperty("location")
    public LocationEnumType location = LocationEnumType.fromValue("Outlet");
    /**
     * Represent a signed version of the meter value.
     *
     *
     */
    @JsonProperty("signedMeterValue")
    public SignedMeterValueType signedMeterValueType;
    /**
     * Represents a UnitOfMeasure with a multiplier
     *
     *
     */
    @JsonProperty("unitOfMeasure")
    public UnitOfMeasureType unitOfMeasureType;

    public SampledValueType(Double value) {
        requiredValidator.validate(value);
        this.value = value;
    }

    public void setCustomDataType(CustomDataType customDataType) {
        this.customDataType = customDataType;
    }

    public void setValue(Double value) {
        requiredValidator.validate(value);
        this.value = value;
    }

    public void setContext(ReadingContextEnumType context) {
        this.context = context;
    }

    public void setMeasurand(MeasurandEnumType measurand) {
        this.measurand = measurand;
    }

    public void setPhase(PhaseEnumType phase) {
        this.phase = phase;
    }

    public void setLocation(LocationEnumType location) {
        this.location = location;
    }

    public void setSignedMeterValueType(SignedMeterValueType signedMeterValueType) {
        this.signedMeterValueType = signedMeterValueType;
    }

    public void setUnitOfMeasureType(UnitOfMeasureType unitOfMeasureType) {
        this.unitOfMeasureType = unitOfMeasureType;
    }

    @Override
    public boolean validate() {
        return requiredValidator.safeValidate(value);
    }
}
