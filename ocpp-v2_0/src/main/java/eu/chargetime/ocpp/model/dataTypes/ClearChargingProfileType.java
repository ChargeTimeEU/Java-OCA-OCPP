package eu.chargetime.ocpp.model.dataTypes;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import eu.chargetime.ocpp.model.Validatable;
import eu.chargetime.ocpp.model.dataTypes.enums.ChargingProfilePurposeEnumType;
import lombok.Getter;
import lombok.Setter;


/**
 * Charging_ Profile
 * urn:x-oca:ocpp:uid:2:233255
 * A ChargingProfile consists of a ChargingSchedule, describing the amount of power or current that can be delivered per time interval.
 *
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "customData",
        "evseId",
        "chargingProfilePurpose",
        "stackLevel"
})
@Getter
@Setter
public class ClearChargingProfileType implements Validatable {

    /**
     * This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
     *
     */
    @JsonProperty("customData")
    public CustomDataType customData;
    /**
     * Identified_ Object. MRID. Numeric_ Identifier
     * urn:x-enexis:ecdm:uid:1:569198
     * Specifies the id of the EVSE for which to clear charging profiles. An evseId of zero (0) specifies the charging profile for the overall Charging Station. Absence of this parameter means the clearing applies to all charging profiles that match the other criteria in the request.
     *
     *
     *
     */
    @JsonProperty("evseId")
    public Integer evseId;
    /**
     * Charging_ Profile. Charging_ Profile_ Purpose. Charging_ Profile_ Purpose_ Code
     * urn:x-oca:ocpp:uid:1:569231
     * Specifies to purpose of the charging profiles that will be cleared, if they meet the other criteria in the request.
     *
     *
     */
    @JsonProperty("chargingProfilePurpose")
    public ChargingProfilePurposeEnumType chargingProfilePurpose;
    /**
     * Charging_ Profile. Stack_ Level. Counter
     * urn:x-oca:ocpp:uid:1:569230
     * Specifies the stackLevel for which charging profiles will be cleared, if they meet the other criteria in the request.
     *
     *
     */
    @JsonProperty("stackLevel")
    public Integer stackLevel;

    @Override
    public boolean validate() {
        return true;
    }
}
