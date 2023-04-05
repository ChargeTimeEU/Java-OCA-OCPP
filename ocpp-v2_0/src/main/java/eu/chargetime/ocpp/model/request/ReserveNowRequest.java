package eu.chargetime.ocpp.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import eu.chargetime.ocpp.model.RequestWithId;
import eu.chargetime.ocpp.model.dataTypes.CustomDataType;
import eu.chargetime.ocpp.model.dataTypes.IdTokenType;
import eu.chargetime.ocpp.model.dataTypes.enums.ConnectorEnumType;
import eu.chargetime.ocpp.model.validation.RequiredValidator;
import eu.chargetime.ocpp.model.validation.Validator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "customData",
        "id",
        "expiryDateTime",
        "connectorType",
        "idToken",
        "evseId",
        "groupIdToken"
})
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class ReserveNowRequest extends RequestWithId {

    private transient Validator<Object> requiredValidator = new RequiredValidator();

    /**
     * This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
     *
     */
    @JsonProperty("customData")
    public CustomDataType customData;
    /**
     * Id of reservation.
     *
     * (Required)
     *
     */
    @JsonProperty("id")
    public Integer id;
    /**
     * Date and time at which the reservation expires.
     *
     * (Required)
     *
     */
    @JsonProperty("expiryDateTime")
    public Date expiryDateTime;
    /**
     * This field specifies the connector type.
     *
     *
     */
    @JsonProperty("connectorType")
    public ConnectorEnumType connectorType;
    /**
     * Contains a case insensitive identifier to use for the authorization and the type of authorization to support multiple forms of identifiers.
     *
     * (Required)
     *
     */
    @JsonProperty("idToken")
    public IdTokenType idToken;
    /**
     * This contains ID of the evse to be reserved.
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

    public ReserveNowRequest(Integer id, Date expiryDateTime, IdTokenType idToken) {
        requiredValidator.validate(id);
        requiredValidator.validate(expiryDateTime);
        requiredValidator.validate(idToken);
        this.id = id;
        this.expiryDateTime = expiryDateTime;
        this.idToken = idToken;
    }

    public void setCustomData(CustomDataType customData) {
        this.customData = customData;
    }

    public void setId(Integer id) {
        requiredValidator.validate(id);
        this.id = id;
    }

    public void setExpiryDateTime(Date expiryDateTime) {
        requiredValidator.validate(expiryDateTime);
        this.expiryDateTime = expiryDateTime;
    }

    public void setConnectorType(ConnectorEnumType connectorType) {
        this.connectorType = connectorType;
    }

    public void setIdToken(IdTokenType idToken) {
        requiredValidator.validate(idToken);
        this.idToken = idToken;
    }

    public void setEvseId(Integer evseId) {
        this.evseId = evseId;
    }

    public void setGroupIdToken(IdTokenType groupIdToken) {
        this.groupIdToken = groupIdToken;
    }

    @Override
    public boolean transactionRelated() {
        return false;
    }

    @Override
    public boolean validate() {
        return requiredValidator.safeValidate(id)
                &&requiredValidator.safeValidate(expiryDateTime)
                &&requiredValidator.safeValidate(idToken)
                && idToken.validate();
    }
}
