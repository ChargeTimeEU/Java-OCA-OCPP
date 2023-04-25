package extrawest.ocpp.model.dataTypes;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import extrawest.ocpp.model.Validatable;
import extrawest.ocpp.model.dataTypes.enums.OCPPInterfaceEnumType;
import extrawest.ocpp.model.dataTypes.enums.OCPPTransportEnumType;
import extrawest.ocpp.model.dataTypes.enums.OCPPVersionEnumType;
import extrawest.ocpp.model.validation.OCPP2PrimDatatypes;
import extrawest.ocpp.model.validation.RequiredValidator;
import extrawest.ocpp.model.validation.Validator;
import extrawest.ocpp.model.validation.ValidatorBuilder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


/**
 * Communication_ Function
 * urn:x-oca:ocpp:uid:2:233304
 * The NetworkConnectionProfile defines the functional and technical parameters of a communication link.
 *
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "customData",
        "apn",
        "ocppVersion",
        "ocppTransport",
        "ocppCsmsUrl",
        "messageTimeout",
        "securityProfile",
        "ocppInterface",
        "vpn"
})
@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class NetworkConnectionProfileType implements Validatable {

    private final transient Validator<Object> requiredValidator = new RequiredValidator();

    private final transient Validator ocppCsmsUrlValidator =
            new ValidatorBuilder()
                    .setRequired(true)
                    .addRule(OCPP2PrimDatatypes.string512())
                    .build();

    /**
     * This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
     *
     */
    @JsonProperty("customData")
    public CustomDataType customData;
    /**
     * APN
     * urn:x-oca:ocpp:uid:2:233134
     * Collection of configuration data needed to make a data-connection over a cellular network.
     *
     * NOTE: When asking a GSM modem to dial in, it is possible to specify which mobile operator should be used. This can be done with the mobile country code (MCC) in combination with a mobile network code (MNC). Example: If your preferred network is Vodafone Netherlands, the MCC=204 and the MNC=04 which means the key PreferredNetwork = 20404 Some modems allows to specify a preferred network, which means, if this network is not available, a different network is used. If you specify UseOnlyPreferredNetwork and this network is not available, the modem will not dial in.
     *
     *
     */
    @JsonProperty("apn")
    public APNType apn;
    /**
     * Communication_ Function. OCPP_ Version. OCPP_ Version_ Code
     * urn:x-oca:ocpp:uid:1:569355
     * Defines the OCPP version used for this communication function.
     *
     * (Required)
     *
     */
    @JsonProperty("ocppVersion")
    public OCPPVersionEnumType ocppVersion;
    /**
     * Communication_ Function. OCPP_ Transport. OCPP_ Transport_ Code
     * urn:x-oca:ocpp:uid:1:569356
     * Defines the transport protocol (e.g. SOAP or JSON). Note: SOAP is not supported in OCPP 2.0, but is supported by other versions of OCPP.
     *
     * (Required)
     *
     */
    @JsonProperty("ocppTransport")
    public OCPPTransportEnumType ocppTransport;
    /**
     * Communication_ Function. OCPP_ Central_ System_ URL. URI
     * urn:x-oca:ocpp:uid:1:569357
     * URL of the CSMS(s) that this Charging Station communicates with.
     *
     * (Required)
     *
     */
    @JsonProperty("ocppCsmsUrl")
    public String ocppCsmsUrl;
    /**
     * Duration in seconds before a message send by the Charging Station via this network connection times-out.
     * The best setting depends on the underlying network and response times of the CSMS.
     * If you are looking for a some guideline: use 30 seconds as a starting point.
     *
     * (Required)
     *
     */
    @JsonProperty("messageTimeout")
    public Integer messageTimeout;
    /**
     * This field specifies the security profile used when connecting to the CSMS with this NetworkConnectionProfile.
     *
     * (Required)
     *
     */
    @JsonProperty("securityProfile")
    public Integer securityProfile;
    /**
     * Applicable Network Interface.
     *
     * (Required)
     *
     */
    @JsonProperty("ocppInterface")
    public OCPPInterfaceEnumType ocppInterface;
    /**
     * VPN
     * urn:x-oca:ocpp:uid:2:233268
     * VPN Configuration settings
     *
     *
     */
    @JsonProperty("vpn")
    public VPNType vpn;

    public NetworkConnectionProfileType(OCPPVersionEnumType ocppVersion,
                                        OCPPTransportEnumType ocppTransport,
                                        String ocppCsmsUrl,
                                        Integer messageTimeout,
                                        Integer securityProfile,
                                        OCPPInterfaceEnumType ocppInterface) {
        ocppCsmsUrlValidator.validate(ocppCsmsUrl);
        requiredValidator.validate(ocppVersion);
        requiredValidator.validate(ocppTransport);
        requiredValidator.validate(messageTimeout);
        requiredValidator.validate(securityProfile);
        requiredValidator.validate(ocppInterface);
        this.ocppVersion = ocppVersion;
        this.ocppTransport = ocppTransport;
        this.ocppCsmsUrl = ocppCsmsUrl;
        this.messageTimeout = messageTimeout;
        this.securityProfile = securityProfile;
        this.ocppInterface = ocppInterface;
    }

    public void setCustomData(CustomDataType customData) {
        this.customData = customData;
    }

    public void setApn(APNType apn) {
        this.apn = apn;
    }

    public void setOcppVersion(OCPPVersionEnumType ocppVersion) {
        requiredValidator.validate(ocppVersion);
        this.ocppVersion = ocppVersion;
    }

    public void setOcppTransport(OCPPTransportEnumType ocppTransport) {
        requiredValidator.validate(ocppTransport);
        this.ocppTransport = ocppTransport;
    }

    public void setOcppCsmsUrl(String ocppCsmsUrl) {
        ocppCsmsUrlValidator.validate(ocppCsmsUrl);
        this.ocppCsmsUrl = ocppCsmsUrl;
    }

    public void setMessageTimeout(Integer messageTimeout) {
        requiredValidator.validate(messageTimeout);
        this.messageTimeout = messageTimeout;
    }

    public void setSecurityProfile(Integer securityProfile) {
        requiredValidator.validate(securityProfile);
        this.securityProfile = securityProfile;
    }

    public void setOcppInterface(OCPPInterfaceEnumType ocppInterface) {
        requiredValidator.validate(ocppInterface);
        this.ocppInterface = ocppInterface;
    }

    public void setVpn(VPNType vpn) {
        this.vpn = vpn;
    }

    @Override
    public boolean validate() {
        return ocppCsmsUrlValidator.safeValidate(ocppCsmsUrl)
                &&requiredValidator.safeValidate(ocppVersion)
                &&requiredValidator.safeValidate(ocppTransport)
                &&requiredValidator.safeValidate(messageTimeout)
                &&requiredValidator.safeValidate(securityProfile)
                &&requiredValidator.safeValidate(ocppInterface);
    }
}
