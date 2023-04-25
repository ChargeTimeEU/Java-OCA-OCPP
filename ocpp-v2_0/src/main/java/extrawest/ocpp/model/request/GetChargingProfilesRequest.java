package extrawest.ocpp.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import extrawest.ocpp.model.RequestWithId;
import extrawest.ocpp.model.dataTypes.ChargingProfileCriterionType;
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
        "requestId",
        "evseId",
        "chargingProfile"
})
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class GetChargingProfilesRequest extends RequestWithId {

    private transient Validator<Object> requiredValidator = new RequiredValidator();

    /**
     * This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
     *
     */
    @JsonProperty("customData")
    public CustomDataType customData;
    /**
     * Reference identification that is to be used by the Charging Station in the &lt;<reportchargingprofilesrequest, ReportChargingProfilesRequest>> when provided.
     *
     * (Required)
     *
     */
    @JsonProperty("requestId")
    public Integer requestId;
    /**
     * For which EVSE installed charging profiles SHALL be reported. If 0, only charging profiles installed on the Charging Station itself (the grid connection) SHALL be reported. If omitted, all installed charging profiles SHALL be reported.
     *
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
    public ChargingProfileCriterionType chargingProfile;

    public GetChargingProfilesRequest(Integer requestId, ChargingProfileCriterionType chargingProfile) {
        requiredValidator.validate(requestId);
        requiredValidator.validate(chargingProfile);
        this.requestId = requestId;
        this.chargingProfile = chargingProfile;
    }

    public void setCustomData(CustomDataType customData) {
        this.customData = customData;
    }

    public void setRequestId(Integer requestId) {
        requiredValidator.validate(requestId);
        this.requestId = requestId;
    }

    public void setEvseId(Integer evseId) {
        this.evseId = evseId;
    }

    public void setChargingProfile(ChargingProfileCriterionType chargingProfile) {
        requiredValidator.validate(chargingProfile);
        this.chargingProfile = chargingProfile;
    }

    @Override
    public boolean transactionRelated() {
        return false;
    }

    @Override
    public boolean validate() {
        return requiredValidator.safeValidate(requestId)
                &&requiredValidator.safeValidate(chargingProfile)
                && chargingProfile.validate();
    }
}
