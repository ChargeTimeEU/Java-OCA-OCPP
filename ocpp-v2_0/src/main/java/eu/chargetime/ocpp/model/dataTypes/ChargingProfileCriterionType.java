package eu.chargetime.ocpp.model.dataTypes;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import eu.chargetime.ocpp.model.Validatable;
import eu.chargetime.ocpp.model.dataTypes.enums.ChargingLimitSourceEnumType;
import eu.chargetime.ocpp.model.dataTypes.enums.ChargingProfilePurposeEnumType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
        "chargingProfilePurpose",
        "stackLevel",
        "chargingProfileId",
        "chargingLimitSource"
})
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ChargingProfileCriterionType implements Validatable {

    /**
     * This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
     *
     */
    @JsonProperty("customData")
    public CustomDataType customData;
    /**
     * Charging_ Profile. Charging_ Profile_ Purpose. Charging_ Profile_ Purpose_ Code
     * urn:x-oca:ocpp:uid:1:569231
     * Defines the purpose of the schedule transferred by this profile
     *
     *
     */
    @JsonProperty("chargingProfilePurpose")
    public ChargingProfilePurposeEnumType chargingProfilePurpose;
    /**
     * Charging_ Profile. Stack_ Level. Counter
     * urn:x-oca:ocpp:uid:1:569230
     * Value determining level in hierarchy stack of profiles. Higher values have precedence over lower values. Lowest level is 0.
     *
     *
     */
    @JsonProperty("stackLevel")
    public Integer stackLevel;
    /**
     * List of all the chargingProfileIds requested. Any ChargingProfile that matches one of these profiles will be reported. If omitted, the Charging Station SHALL not filter on chargingProfileId. This field SHALL NOT contain more ids than set in &lt;<configkey-charging-profile-entries,ChargingProfileEntries.maxLimit>>
     *
     *
     *
     */
    @JsonProperty("chargingProfileId")
    public List<Integer> chargingProfileId;
    /**
     * For which charging limit sources, charging profiles SHALL be reported. If omitted, the Charging Station SHALL not filter on chargingLimitSource.
     *
     *
     */
    @JsonProperty("chargingLimitSource")
    public List<ChargingLimitSourceEnumType> chargingLimitSource;

    @Override
    public boolean validate() {
        return true;
    }
}
