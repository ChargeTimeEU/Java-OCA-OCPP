package extrawest.ocpp.model.dataTypes;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import extrawest.ocpp.model.Validatable;
import extrawest.ocpp.model.dataTypes.enums.APNAuthenticationEnumType;
import extrawest.ocpp.model.validation.OCPP2PrimDatatypes;
import extrawest.ocpp.model.validation.RequiredValidator;
import extrawest.ocpp.model.validation.Validator;
import extrawest.ocpp.model.validation.ValidatorBuilder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


/**
 * APN
 * urn:x-oca:ocpp:uid:2:233134
 * Collection of configuration data needed to make a data-connection over a cellular network.
 *
 * NOTE: When asking a GSM modem to dial in, it is possible to specify which mobile operator should be used. This can be done with the mobile country code (MCC) in combination with a mobile network code (MNC). Example: If your preferred network is Vodafone Netherlands, the MCC=204 and the MNC=04 which means the key PreferredNetwork = 20404 Some modems allows to specify a preferred network, which means, if this network is not available, a different network is used. If you specify UseOnlyPreferredNetwork and this network is not available, the modem will not dial in.
 *
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "customData",
        "apn",
        "apnUserName",
        "apnPassword",
        "simPin",
        "preferredNetwork",
        "useOnlyPreferredNetwork",
        "apnAuthentication"
})
@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class APNType implements Validatable {

    private final transient Validator apnValidator =
            new ValidatorBuilder()
                    .setRequired(true)
                    .addRule(OCPP2PrimDatatypes.string512())
                    .build();

    private final transient Validator apnUserNameValidator =
            new ValidatorBuilder()
                    .addRule(OCPP2PrimDatatypes.string20())
                    .build();

    private final transient Validator apnPasswordValidator =
            new ValidatorBuilder()
                    .addRule(OCPP2PrimDatatypes.string20())
                    .build();

    private final transient Validator preferredNetworkValidator =
            new ValidatorBuilder()
                    .addRule(OCPP2PrimDatatypes.string20())
                    .addRule(OCPP2PrimDatatypes.identifierString())
                    .build();

    private final transient Validator<Object> requiredValidator = new RequiredValidator();

    /**
     * This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
     *
     */
    @JsonProperty("customData") @JsonPropertyDescription("This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.")
    public CustomDataType customData;
    /**
     * APN. APN. URI
     * urn:x-oca:ocpp:uid:1:568814
     * The Access Point Name as an URL.
     *
     * (Required)
     *
     */
    @JsonProperty("apn") @JsonPropertyDescription("APN. APN. URI\r\nurn:x-oca:ocpp:uid:1:568814\r\nThe Access Point Name as an URL.\r\n")
    public String apn;
    /**
     * APN. APN. User_ Name
     * urn:x-oca:ocpp:uid:1:568818
     * APN username.
     *
     *
     */
    @JsonProperty("apnUserName") @JsonPropertyDescription("APN. APN. User_ Name\r\nurn:x-oca:ocpp:uid:1:568818\r\nAPN username.\r\n")
    public String apnUserName;
    /**
     * APN. APN. Password
     * urn:x-oca:ocpp:uid:1:568819
     * APN Password.
     *
     *
     */
    @JsonProperty("apnPassword") @JsonPropertyDescription("APN. APN. Password\r\nurn:x-oca:ocpp:uid:1:568819\r\nAPN Password.\r\n")
    public String apnPassword;
    /**
     * APN. SIMPIN. PIN_ Code
     * urn:x-oca:ocpp:uid:1:568821
     * SIM card pin code.
     *
     *
     */
    @JsonProperty("simPin") @JsonPropertyDescription("APN. SIMPIN. PIN_ Code\r\nurn:x-oca:ocpp:uid:1:568821\r\nSIM card pin code.\r\n")
    public Integer simPin;
    /**
     * APN. Preferred_ Network. Mobile_ Network_ ID
     * urn:x-oca:ocpp:uid:1:568822
     * Preferred network, written as MCC and MNC concatenated. See note.
     *
     *
     */
    @JsonProperty("preferredNetwork") @JsonPropertyDescription("APN. Preferred_ Network. Mobile_ Network_ ID\r\nurn:x-oca:ocpp:uid:1:568822\r\nPreferred network, written as MCC and MNC concatenated. See note.\r\n")
    public String preferredNetwork;
    /**
     * APN. Use_ Only_ Preferred_ Network. Indicator
     * urn:x-oca:ocpp:uid:1:568824
     * Default: false. Use only the preferred Network, do
     * not dial in when not available. See Note.
     *
     *
     */
    @JsonProperty("useOnlyPreferredNetwork") @JsonPropertyDescription("APN. Use_ Only_ Preferred_ Network. Indicator\r\nurn:x-oca:ocpp:uid:1:568824\r\nDefault: false. Use only the preferred Network, do\r\nnot dial in when not available. See Note.\r\n")
    public Boolean useOnlyPreferredNetwork = false;
    /**
     * APN. APN_ Authentication. APN_ Authentication_ Code
     * urn:x-oca:ocpp:uid:1:568828
     * Authentication method.
     *
     * (Required)
     *
     */
    @JsonProperty("apnAuthentication") @JsonPropertyDescription("APN. APN_ Authentication. APN_ Authentication_ Code\r\nurn:x-oca:ocpp:uid:1:568828\r\nAuthentication method.\r\n")
    public APNAuthenticationEnumType apnAuthentication;

    public APNType(String apn, APNAuthenticationEnumType apnAuthentication) {
        apnValidator.validate(apn);
        requiredValidator.validate(apnAuthentication);
        this.apn = apn;
        this.apnAuthentication = apnAuthentication;
    }

    public void setCustomData(CustomDataType customData) {
        this.customData = customData;
    }

    public void setApn(String apn) {
        apnValidator.validate(apn);
        this.apn = apn;
    }

    public void setApnUserName(String apnUserName) {
        apnUserNameValidator.validate(apnUserName);
        this.apnUserName = apnUserName;
    }

    public void setApnPassword(String apnPassword) {
        apnPasswordValidator.validate(apnPassword);
        this.apnPassword = apnPassword;
    }

    public void setSimPin(Integer simPin) {
        this.simPin = simPin;
    }

    public void setPreferredNetwork(String preferredNetwork) {
        preferredNetworkValidator.validate(preferredNetwork);
        this.preferredNetwork = preferredNetwork;
    }

    public void setUseOnlyPreferredNetwork(Boolean useOnlyPreferredNetwork) {
        this.useOnlyPreferredNetwork = useOnlyPreferredNetwork;
    }

    public void setApnAuthentication(APNAuthenticationEnumType apnAuthentication) {
        requiredValidator.validate(apnAuthentication);
        this.apnAuthentication = apnAuthentication;
    }

    @Override
    public boolean validate() {
        return apnValidator.safeValidate(apn)
                &&requiredValidator.safeValidate(apnAuthentication);
    }
}
