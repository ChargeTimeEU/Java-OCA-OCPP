package extrawest.ocpp.model.dataTypes;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import extrawest.ocpp.model.Validatable;
import extrawest.ocpp.model.validation.OCPP2PrimDatatypes;
import extrawest.ocpp.model.validation.RequiredValidator;
import extrawest.ocpp.model.validation.Validator;
import extrawest.ocpp.model.validation.ValidatorBuilder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;


/**
 * Firmware
 * urn:x-enexis:ecdm:uid:2:233291
 * Represents a copy of the firmware that can be loaded/updated on the Charging Station.
 *
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "customData",
        "location",
        "retrieveDateTime",
        "installDateTime",
        "signingCertificate",
        "signature"
})
@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class FirmwareType implements Validatable {

    private final transient Validator<Object> requiredValidator = new RequiredValidator();

    private final transient Validator locationValidator =
            new ValidatorBuilder()
                    .setRequired(true)
                    .addRule(OCPP2PrimDatatypes.string512())
                    .build();

    private final transient Validator signingCertificateValidator =
            new ValidatorBuilder()
                    .addRule(OCPP2PrimDatatypes.string5500())
                    .build();

    private final transient Validator signatureValidator =
            new ValidatorBuilder()
                    .addRule(OCPP2PrimDatatypes.string800())
                    .build();

    /**
     * This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
     *
     */
    @JsonProperty("customData")
    public CustomDataType customData;
    /**
     * Firmware. Location. URI
     * urn:x-enexis:ecdm:uid:1:569460
     * URI defining the origin of the firmware.
     *
     * (Required)
     *
     */
    @JsonProperty("location")
    public String location;
    /**
     * Firmware. Retrieve. Date_ Time
     * urn:x-enexis:ecdm:uid:1:569461
     * Date and time at which the firmware shall be retrieved.
     *
     * (Required)
     *
     */
    @JsonProperty("retrieveDateTime")
    public LocalDateTime retrieveDateTime;
    /**
     * Firmware. Install. Date_ Time
     * urn:x-enexis:ecdm:uid:1:569462
     * Date and time at which the firmware shall be installed.
     *
     *
     */
    @JsonProperty("installDateTime")
    public LocalDateTime installDateTime;
    /**
     * Certificate with which the firmware was signed.
     * PEM encoded X.509 certificate.
     *
     *
     */
    @JsonProperty("signingCertificate")
    public String signingCertificate;
    /**
     * Firmware. Signature. Signature
     * urn:x-enexis:ecdm:uid:1:569464
     * Base64 encoded firmware signature.
     *
     *
     */
    @JsonProperty("signature")
    public String signature;

    public FirmwareType(String location, LocalDateTime retrieveDateTime) {
        locationValidator.validate(location);
        requiredValidator.validate(retrieveDateTime);
        this.location = location;
        this.retrieveDateTime = retrieveDateTime;
    }

    public void setCustomData(CustomDataType customData) {
        this.customData = customData;
    }

    public void setLocation(String location) {
        locationValidator.validate(location);
        this.location = location;
    }

    public void setRetrieveDateTime(LocalDateTime retrieveDateTime) {
        requiredValidator.validate(retrieveDateTime);
        this.retrieveDateTime = retrieveDateTime;
    }

    public void setInstallDateTime(LocalDateTime installDateTime) {
        this.installDateTime = installDateTime;
    }

    public void setSigningCertificate(String signingCertificate) {
        signingCertificateValidator.validate(signingCertificate);
        this.signingCertificate = signingCertificate;
    }

    public void setSignature(String signature) {
        signatureValidator.validate(signature);
        this.signature = signature;
    }

    @Override
    public boolean validate() {
        return locationValidator.safeValidate(location)
                &&requiredValidator.safeValidate(retrieveDateTime);
    }
}
