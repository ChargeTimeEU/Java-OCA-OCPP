package extrawest.ocpp.model.dataTypes;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import extrawest.ocpp.model.Validatable;
import extrawest.ocpp.model.dataTypes.enums.VPNEnumType;
import extrawest.ocpp.model.validation.OCPP2PrimDatatypes;
import extrawest.ocpp.model.validation.RequiredValidator;
import extrawest.ocpp.model.validation.Validator;
import extrawest.ocpp.model.validation.ValidatorBuilder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


/**
 * VPN
 * urn:x-oca:ocpp:uid:2:233268
 * VPN Configuration settings
 *
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "customData",
        "server",
        "user",
        "group",
        "password",
        "key",
        "type"
})
@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class VPNType implements Validatable {

    private final transient Validator serverValidator =
            new ValidatorBuilder()
                    .setRequired(true)
                    .addRule(OCPP2PrimDatatypes.string512())
                    .build();

    private final transient Validator userValidator =
            new ValidatorBuilder()
                    .setRequired(true)
                    .addRule(OCPP2PrimDatatypes.string20())
                    .build();

    private final transient Validator groupValidator =
            new ValidatorBuilder()
                    .addRule(OCPP2PrimDatatypes.string20())
                    .build();

    private final transient Validator passwordValidator =
            new ValidatorBuilder()
                    .setRequired(true)
                    .addRule(OCPP2PrimDatatypes.string20())
                    .build();

    private final transient Validator keyValidator =
            new ValidatorBuilder()
                    .setRequired(true)
                    .addRule(OCPP2PrimDatatypes.string255())
                    .build();

    private final transient Validator<Object> requiredValidator = new RequiredValidator();

    /**
     * This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
     */
    @JsonProperty("customData")
    public CustomDataType customData;
    /**
     * VPN. Server. URI
     * urn:x-oca:ocpp:uid:1:569272
     * VPN Server Address
     * <p>
     * (Required)
     */
    @JsonProperty("server")
    public String server;
    /**
     * VPN. User. User_ Name
     * urn:x-oca:ocpp:uid:1:569273
     * VPN User
     * <p>
     * (Required)
     */
    @JsonProperty("user")
    public String user;
    /**
     * VPN. Group. Group_ Name
     * urn:x-oca:ocpp:uid:1:569274
     * VPN group.
     */
    @JsonProperty("group")
    public String group;
    /**
     * VPN. Password. Password
     * urn:x-oca:ocpp:uid:1:569275
     * VPN Password.
     * <p>
     * (Required)
     */
    @JsonProperty("password")
    public String password;
    /**
     * VPN. Key. VPN_ Key
     * urn:x-oca:ocpp:uid:1:569276
     * VPN shared secret.
     * <p>
     * (Required)
     */
    @JsonProperty("key")
    public String key;
    /**
     * VPN. Type. VPN_ Code
     * urn:x-oca:ocpp:uid:1:569277
     * Type of VPN
     * <p>
     * (Required)
     */
    @JsonProperty("type")
    public VPNEnumType type;

    public VPNType(String server,
                   String user,
                   String password,
                   String key,
                   VPNEnumType type) {
        requiredValidator.validate(type);
        serverValidator.validate(server);
        userValidator.validate(user);
        passwordValidator.validate(password);
        keyValidator.validate(key);
        this.server = server;
        this.user = user;
        this.password = password;
        this.key = key;
        this.type = type;
    }

    public void setCustomData(CustomDataType customData) {
        this.customData = customData;
    }

    public void setServer(String server) {
        serverValidator.validate(server);
        this.server = server;
    }

    public void setUser(String user) {
        userValidator.validate(user);
        this.user = user;
    }

    public void setGroup(String group) {
        groupValidator.validate(group);
        this.group = group;
    }

    public void setPassword(String password) {
        passwordValidator.validate(password);
        this.password = password;
    }

    public void setKey(String key) {
        keyValidator.validate(key);
        this.key = key;
    }

    public void setType(VPNEnumType type) {
        requiredValidator.safeValidate(type);
        this.type = type;
    }

    @Override
    public boolean validate() {
        return requiredValidator.safeValidate(type)
                &&serverValidator.safeValidate(server)
                &&userValidator.safeValidate(user)
                &&passwordValidator.safeValidate(password)
                &&keyValidator.safeValidate(key);
    }
}

