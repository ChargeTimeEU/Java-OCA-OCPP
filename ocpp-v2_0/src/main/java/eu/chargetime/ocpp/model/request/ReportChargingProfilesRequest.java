package eu.chargetime.ocpp.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import eu.chargetime.ocpp.model.RequestWithId;
import eu.chargetime.ocpp.model.dataTypes.ChargingProfileType;
import eu.chargetime.ocpp.model.dataTypes.CustomDataType;
import eu.chargetime.ocpp.model.dataTypes.enums.ChargingLimitSourceEnumType;
import eu.chargetime.ocpp.model.validation.RequiredValidator;
import eu.chargetime.ocpp.model.validation.Validator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "customData",
        "requestId",
        "chargingLimitSource",
        "chargingProfile",
        "tbc",
        "evseId"
})
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class ReportChargingProfilesRequest extends RequestWithId {

    private transient Validator<Object> requiredValidator = new RequiredValidator();

    /**
     * This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
     *
     */
    @JsonProperty("customData")
    public CustomDataType customData;
    /**
     * Id used to match the &lt;<getchargingprofilesrequest, GetChargingProfilesRequest>> message with the resulting ReportChargingProfilesRequest messages. When the CSMS provided a requestId in the <<getchargingprofilesrequest, GetChargingProfilesRequest>>, this field SHALL contain the same value.
     *
     * (Required)
     *
     */
    @JsonProperty("requestId")
    public Integer requestId;
    /**
     * Source that has installed this charging profile.
     *
     * (Required)
     *
     */
    @JsonProperty("chargingLimitSource")
    public ChargingLimitSourceEnumType chargingLimitSource;
    /**
     *
     * (Required)
     *
     */
    @JsonProperty("chargingProfile")
    public List<ChargingProfileType> chargingProfile;
    /**
     * To Be Continued. Default value when omitted: false. false indicates that there are no further messages as part of this report.
     *
     *
     */
    @JsonProperty("tbc")
    public Boolean tbc = false;
    /**
     * The evse to which the charging profile applies. If evseId = 0, the message contains an overall limit for the Charging Station.
     *
     * (Required)
     *
     */
    @JsonProperty("evseId")
    public Integer evseId;

    public ReportChargingProfilesRequest(Integer requestId,
                                         ChargingLimitSourceEnumType chargingLimitSource,
                                         List<ChargingProfileType> chargingProfile,
                                         Integer evseId) {
        requiredValidator.validate(requestId);
        requiredValidator.validate(chargingLimitSource);
        requiredValidator.validate(chargingProfile);
        requiredValidator.validate(evseId);
        this.requestId = requestId;
        this.chargingLimitSource = chargingLimitSource;
        this.chargingProfile = chargingProfile;
        this.evseId = evseId;
    }

    public void setCustomData(CustomDataType customData) {
        this.customData = customData;
    }

    public void setRequestId(Integer requestId) {
        requiredValidator.validate(requestId);
        this.requestId = requestId;
    }

    public void setChargingLimitSource(ChargingLimitSourceEnumType chargingLimitSource) {
        requiredValidator.validate(chargingLimitSource);
        this.chargingLimitSource = chargingLimitSource;
    }

    public void setChargingProfile(List<ChargingProfileType> chargingProfile) {
        requiredValidator.validate(chargingProfile);
        this.chargingProfile = chargingProfile;
    }

    public void setTbc(Boolean tbc) {
        this.tbc = tbc;
    }

    public void setEvseId(Integer evseId) {
        requiredValidator.validate(evseId);
        this.evseId = evseId;
    }

    @Override
    public boolean transactionRelated() {
        return false;
    }

    @Override
    public boolean validate() {
        return requiredValidator.safeValidate(requestId)
                &&requiredValidator.safeValidate(chargingLimitSource)
                &&requiredValidator.safeValidate(chargingProfile)
                &&requiredValidator.safeValidate(evseId)
                &&chargingProfile.stream().filter(ChargingProfileType::validate).count() == chargingProfile.size();
    }
}
