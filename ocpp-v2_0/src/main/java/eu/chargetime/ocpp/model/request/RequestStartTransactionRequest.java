package eu.chargetime.ocpp.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import eu.chargetime.ocpp.model.RequestWithId;
import eu.chargetime.ocpp.model.dataTypes.ChargingProfileType;
import eu.chargetime.ocpp.model.dataTypes.CustomDataType;
import eu.chargetime.ocpp.model.dataTypes.IdTokenType;
import eu.chargetime.ocpp.model.validation.RequiredValidator;
import eu.chargetime.ocpp.model.validation.Validator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "customData",
        "evseId",
        "groupIdToken",
        "idToken",
        "remoteStartId",
        "chargingProfile"
})
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class RequestStartTransactionRequest extends RequestWithId {

    private transient Validator<Object> requiredValidator = new RequiredValidator();

    /**
     * This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
     *
     */
    @JsonProperty("customData")
    public CustomDataType customData;
    /**
     * Number of the EVSE on which to start the transaction. EvseId SHALL be &gt; 0
     *
     *
     */
    @JsonProperty("evseId")
    public Integer evseId;
    /**
     * Contains a case insensitive identifier to use for the authorization and the type of authorization to support multiple forms of identifiers.
     *
     *
     */
    @JsonProperty("groupIdToken")
    public IdTokenType groupIdToken;
    /**
     * Contains a case insensitive identifier to use for the authorization and the type of authorization to support multiple forms of identifiers.
     *
     * (Required)
     *
     */
    @JsonProperty("idToken")
    public IdTokenType idToken;
    /**
     * Id given by the server to this start request. The Charging Station might return this in the &lt;<transactioneventrequest, TransactionEventRequest>>, letting the server know which transaction was started for this request. Use to start a transaction.
     *
     * (Required)
     *
     */
    @JsonProperty("remoteStartId")
    public Integer remoteStartId;
    /**
     * Charging_ Profile
     * urn:x-oca:ocpp:uid:2:233255
     * A ChargingProfile consists of ChargingSchedule, describing the amount of power or current that can be delivered per time interval.
     *
     *
     */
    @JsonProperty("chargingProfile")
    public ChargingProfileType chargingProfile;

    public RequestStartTransactionRequest(IdTokenType idToken, Integer remoteStartId) {
        requiredValidator.validate(idToken);
        requiredValidator.validate(remoteStartId);
        this.idToken = idToken;
        this.remoteStartId = remoteStartId;
    }

    public void setCustomData(CustomDataType customData) {
        this.customData = customData;
    }

    public void setEvseId(Integer evseId) {
        this.evseId = evseId;
    }

    public void setGroupIdToken(IdTokenType groupIdToken) {
        this.groupIdToken = groupIdToken;
    }

    public void setIdToken(IdTokenType idToken) {
        requiredValidator.validate(idToken);
        this.idToken = idToken;
    }

    public void setRemoteStartId(Integer remoteStartId) {
        requiredValidator.validate(remoteStartId);
        this.remoteStartId = remoteStartId;
    }

    public void setChargingProfile(ChargingProfileType chargingProfile) {
        this.chargingProfile = chargingProfile;
    }

    @Override
    public boolean transactionRelated() {
        return false;
    }

    @Override
    public boolean validate() {
        return requiredValidator.safeValidate(idToken)
                &&requiredValidator.safeValidate(remoteStartId)
                &&idToken.validate();
    }
}
