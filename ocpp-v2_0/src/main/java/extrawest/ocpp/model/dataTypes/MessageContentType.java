package extrawest.ocpp.model.dataTypes;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import extrawest.ocpp.model.Validatable;
import extrawest.ocpp.model.dataTypes.enums.MessageFormatEnumType;
import extrawest.ocpp.model.validation.OCPP2PrimDatatypes;
import extrawest.ocpp.model.validation.RequiredValidator;
import extrawest.ocpp.model.validation.Validator;
import extrawest.ocpp.model.validation.ValidatorBuilder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


/**
 * Message_ Content
 * urn:x-enexis:ecdm:uid:2:234490
 * Contains message details, for a message to be displayed on a Charging Station.
 *
 *
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "customData",
        "format",
        "language",
        "content"
})
@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class MessageContentType implements Validatable {

    private transient Validator<Object> requiredValidator = new RequiredValidator();

    private transient Validator contentValidator =
            new ValidatorBuilder().addRule(OCPP2PrimDatatypes.string512()).setRequired(true).build();

    private transient Validator languageValidator =
            new ValidatorBuilder().addRule(OCPP2PrimDatatypes.string8()).setRequired(true).build();

    /**
     * This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
     *
     */
    @JsonProperty("customData")
    public CustomDataType customDataType;
    /**
     * Message_ Content. Format. Message_ Format_ Code
     * urn:x-enexis:ecdm:uid:1:570848
     * Format of the message.
     *
     * (Required)
     *
     */
    @JsonProperty("format")
    public MessageFormatEnumType format;
    /**
     * Message_ Content. Language. Language_ Code
     * urn:x-enexis:ecdm:uid:1:570849
     * Message language identifier. Contains a language code as defined in &lt;<ref-RFC5646,[RFC5646]>>.
     *
     *
     */
    @JsonProperty("language")
    public String language;
    /**
     * Message_ Content. Content. Message
     * urn:x-enexis:ecdm:uid:1:570852
     * Message contents.
     *
     *
     * (Required)
     *
     */
    @JsonProperty("content")
    public String content;

    public MessageContentType(MessageFormatEnumType format, String content) {
        contentValidator.validate(content);
        requiredValidator.validate(format);
        this.format = format;
        this.content = content;
    }

    public void setCustomDataType(CustomDataType customDataType) {
        this.customDataType = customDataType;
    }

    public void setFormat(MessageFormatEnumType format) {
        requiredValidator.validate(format);
        this.format = format;
    }

    public void setLanguage(String language) {
        languageValidator.validate(language);
        this.language = language;
    }

    public void setContent(String content) {
        contentValidator.validate(content);
        this.content = content;
    }

    @Override
    public boolean validate() {
        return contentValidator.safeValidate(content)
                &&requiredValidator.safeValidate(format);
    }
}
