package extrawest.ocpp.model.dataTypes;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import extrawest.ocpp.model.Validatable;
import extrawest.ocpp.model.dataTypes.enums.IdTokenEnumType;
import extrawest.ocpp.model.validation.OCPP2PrimDatatypes;
import extrawest.ocpp.model.validation.RequiredValidator;
import extrawest.ocpp.model.validation.Validator;
import extrawest.ocpp.model.validation.ValidatorBuilder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;


/**
 * Contains a case insensitive identifier to use for the authorization and the type of authorization to support multiple forms of identifiers.
 *
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "customData",
        "additionalInfo",
        "idToken",
        "type"
})
@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class IdTokenType implements Validatable {

    private transient Validator idTokenValidator =
            new ValidatorBuilder().setRequired(true)
                    .addRule(OCPP2PrimDatatypes.string36())
                    .addRule(OCPP2PrimDatatypes.identifierString())
                    .build();

    private transient Validator<Object> requiredValidator = new RequiredValidator();

    /**
     * This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
     *
     */
    @JsonProperty("customData")
    public CustomDataType customDataType;
    @JsonProperty("additionalInfo")
    public List<AdditionalInfoType> additionalInfoType;
    /**
     * IdToken is case insensitive. Might hold the hidden id of an RFID tag, but can for example also contain a UUID.
     *
     * (Required)
     *
     */
    @JsonProperty("idToken")
    public String idToken;
    /**
     * Enumeration of possible idToken types.
     *
     * (Required)
     *
     */
    @JsonProperty("type")
    public IdTokenEnumType type;

    public IdTokenType(String idToken, IdTokenEnumType type) {
        idTokenValidator.validate(idToken);
        requiredValidator.validate(type);
        this.idToken = idToken;
        this.type = type;
    }

    public void setCustomDataType(CustomDataType customDataType) {
        this.customDataType = customDataType;
    }

    public void setAdditionalInfoType(List<AdditionalInfoType> additionalInfoType) {
        this.additionalInfoType = additionalInfoType;
    }

    public void setIdToken(String idToken) {
        idTokenValidator.validate(idToken);
        this.idToken = idToken;
    }

    public void setType(IdTokenEnumType type) {
        requiredValidator.validate(type);
        this.type = type;
    }

    @Override
    public boolean validate() {
        return (requiredValidator.safeValidate(type)
                && requiredValidator.safeValidate(idToken));
    }
}
