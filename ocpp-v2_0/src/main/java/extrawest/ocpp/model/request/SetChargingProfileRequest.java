package extrawest.ocpp.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import extrawest.ocpp.model.RequestWithId;
import extrawest.ocpp.model.dataTypes.ChargingProfileType;
import extrawest.ocpp.model.dataTypes.CustomDataType;
import extrawest.ocpp.model.validation.RequiredValidator;
import extrawest.ocpp.model.validation.Validator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "customData",
        "evseId",
        "chargingProfile"
})
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class SetChargingProfileRequest extends RequestWithId {

    private final transient Validator<Object> requiredValidator = new RequiredValidator();

    /**
     * This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
     *
     */
    @JsonProperty("customData")
    public CustomDataType customData;
    /**
     * For TxDefaultProfile an evseId=0 applies the profile to each individual evse. For ChargingStationMaxProfile and ChargingStationExternalConstraints an evseId=0 contains an overal limit for the whole Charging Station.
     *
     * (Required)
     *
     */
    @JsonProperty("evseId")
    public Integer evseId;
    /**
     * Charging_ Profile
     * urn:x-oca:ocpp:uid:2:233255
     * A ChargingProfile consists of ChargingSchedule, describing the amount of power or current that can be delivered per time interval.
     *
     * (Required)
     *
     */
    @JsonProperty("chargingProfile")
    public ChargingProfileType chargingProfile;

    public SetChargingProfileRequest(Integer evseId, ChargingProfileType chargingProfile) {
        requiredValidator.validate(evseId);
        requiredValidator.validate(chargingProfile);
        this.evseId = evseId;
        this.chargingProfile = chargingProfile;
    }

    public void setCustomData(CustomDataType customData) {
        this.customData = customData;
    }

    public void setEvseId(Integer evseId) {
        requiredValidator.validate(evseId);
        this.evseId = evseId;
    }

    public void setChargingProfile(ChargingProfileType chargingProfile) {
        requiredValidator.validate(chargingProfile);
        this.chargingProfile = chargingProfile;
    }

    @Override
    public boolean transactionRelated() {
        return false;
    }

    @Override
    public boolean validate() {
        return requiredValidator.safeValidate(evseId)
                &&requiredValidator.safeValidate(chargingProfile);
    }
}
