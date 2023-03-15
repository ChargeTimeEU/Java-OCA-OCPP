package eu.chargetime.ocpp.model.dataTypes;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import eu.chargetime.ocpp.model.Validatable;
import eu.chargetime.ocpp.model.dataTypes.enums.ChargingProfileKindEnumType;
import eu.chargetime.ocpp.model.dataTypes.enums.ChargingProfilePurposeEnumType;
import eu.chargetime.ocpp.model.dataTypes.enums.RecurrencyKindEnumType;
import eu.chargetime.ocpp.model.validation.OCPP2PrimDatatypes;
import eu.chargetime.ocpp.model.validation.RequiredValidator;
import eu.chargetime.ocpp.model.validation.Validator;
import eu.chargetime.ocpp.model.validation.ValidatorBuilder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;
import java.util.List;


/**
 * Charging_ Profile
 * urn:x-oca:ocpp:uid:2:233255
 * A ChargingProfile consists of ChargingSchedule, describing the amount of power or current that can be delivered per time interval.
 *
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "customData",
        "id",
        "stackLevel",
        "chargingProfilePurpose",
        "chargingProfileKind",
        "recurrencyKind",
        "validFrom",
        "validTo",
        "chargingSchedule",
        "transactionId"
})
@Getter
@ToString
@EqualsAndHashCode
public class ChargingProfileType implements Validatable {

    private transient Validator<Object> requiredValidator = new RequiredValidator();

    private final transient Validator transactionIdValidator =
            new ValidatorBuilder()
                    .addRule(OCPP2PrimDatatypes.string36())
                    .addRule(OCPP2PrimDatatypes.identifierString())
                    .build();

    /**
     * This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
     *
     */
    @JsonProperty("customData")
    private CustomDataType customDataType;
    /**
     * Identified_ Object. MRID. Numeric_ Identifier
     * urn:x-enexis:ecdm:uid:1:569198
     * Id of ChargingProfile.
     *
     * (Required)
     *
     */
    @JsonProperty("id")
    private Integer id;
    /**
     * Charging_ Profile. Stack_ Level. Counter
     * urn:x-oca:ocpp:uid:1:569230
     * Value determining level in hierarchy stack of profiles. Higher values have precedence over lower values. Lowest level is 0.
     *
     * (Required)
     *
     */
    @JsonProperty("stackLevel")
    private Integer stackLevel;
    /**
     * Charging_ Profile. Charging_ Profile_ Purpose. Charging_ Profile_ Purpose_ Code
     * urn:x-oca:ocpp:uid:1:569231
     * Defines the purpose of the schedule transferred by this profile
     *
     * (Required)
     *
     */
    @JsonProperty("chargingProfilePurpose")
    private ChargingProfilePurposeEnumType chargingProfilePurpose;
    /**
     * Charging_ Profile. Charging_ Profile_ Kind. Charging_ Profile_ Kind_ Code
     * urn:x-oca:ocpp:uid:1:569232
     * Indicates the kind of schedule.
     *
     * (Required)
     *
     */
    @JsonProperty("chargingProfileKind")
    private ChargingProfileKindEnumType chargingProfileKind;
    /**
     * Charging_ Profile. Recurrency_ Kind. Recurrency_ Kind_ Code
     * urn:x-oca:ocpp:uid:1:569233
     * Indicates the start point of a recurrence.
     *
     *
     */
    @JsonProperty("recurrencyKind")
    private RecurrencyKindEnumType recurrencyKind;
    /**
     * Charging_ Profile. Valid_ From. Date_ Time
     * urn:x-oca:ocpp:uid:1:569234
     * Point in time at which the profile starts to be valid. If absent, the profile is valid as soon as it is received by the Charging Station.
     *
     *
     */
    @JsonProperty("validFrom")
    private Date validFrom;
    /**
     * Charging_ Profile. Valid_ To. Date_ Time
     * urn:x-oca:ocpp:uid:1:569235
     * Point in time at which the profile stops to be valid. If absent, the profile is valid until it is replaced by another profile.
     *
     *
     */
    @JsonProperty("validTo")
    private Date validTo;
    /**
     *
     * (Required)
     *
     */
    @JsonProperty("chargingSchedule")
    private List<ChargingScheduleType> chargingScheduleType;
    /**
     * SHALL only be included if ChargingProfilePurpose is set to TxProfile. The transactionId is used to match the profile to a specific transaction.
     *
     *
     */
    @JsonProperty("transactionId")
    private String transactionId;

    public ChargingProfileType(Integer id,
                               Integer stackLevel,
                               ChargingProfilePurposeEnumType chargingProfilePurpose,
                               ChargingProfileKindEnumType chargingProfileKind,
                               List<ChargingScheduleType> chargingScheduleType) {
        requiredValidator.validate(id);
        requiredValidator.validate(stackLevel);
        requiredValidator.validate(chargingProfilePurpose);
        requiredValidator.validate(chargingProfileKind);
        requiredValidator.validate(chargingScheduleType);
        this.id = id;
        this.stackLevel = stackLevel;
        this.chargingProfilePurpose = chargingProfilePurpose;
        this.chargingProfileKind = chargingProfileKind;
        this.chargingScheduleType = chargingScheduleType;
    }

    public void setCustomDataType(CustomDataType customDataType) {
        this.customDataType = customDataType;
    }

    public void setId(Integer id) {
        requiredValidator.validate(id);
        this.id = id;
    }

    public void setStackLevel(Integer stackLevel) {
        requiredValidator.validate(stackLevel);
        this.stackLevel = stackLevel;
    }

    public void setChargingProfilePurpose(ChargingProfilePurposeEnumType chargingProfilePurpose) {
        requiredValidator.validate(chargingProfilePurpose);
        this.chargingProfilePurpose = chargingProfilePurpose;
    }

    public void setChargingProfileKind(ChargingProfileKindEnumType chargingProfileKind) {
        requiredValidator.validate(chargingProfileKind);
        this.chargingProfileKind = chargingProfileKind;
    }

    public void setRecurrencyKind(RecurrencyKindEnumType recurrencyKind) {
        this.recurrencyKind = recurrencyKind;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    public void setValidTo(Date validTo) {
        this.validTo = validTo;
    }

    public void setChargingScheduleType(List<ChargingScheduleType> chargingScheduleType) {
        requiredValidator.validate(chargingScheduleType);
        this.chargingScheduleType = chargingScheduleType;
    }

    public void setTransactionId(String transactionId) {
        transactionIdValidator.validate(transactionId);
        this.transactionId = transactionId;
    }

    @Override
    public boolean validate() {
        return requiredValidator.safeValidate(id)
                &&requiredValidator.safeValidate(stackLevel)
                &&requiredValidator.safeValidate(chargingProfilePurpose)
                &&requiredValidator.safeValidate(chargingProfileKind)
                &&requiredValidator.safeValidate(chargingScheduleType);
    }
}
