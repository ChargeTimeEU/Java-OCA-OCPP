package extrawest.ocpp.model.dataTypes.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import static extrawest.ocpp.util.EnumUtil.findByField;


/**
 * Indicates the certificate type that is sent.
 *
 *
 */
public enum InstallCertificateUseEnumType {

    V_2_G_ROOT_CERTIFICATE("V2GRootCertificate"),
    MO_ROOT_CERTIFICATE("MORootCertificate"),
    CSMS_ROOT_CERTIFICATE("CSMSRootCertificate"),
    MANUFACTURER_ROOT_CERTIFICATE("ManufacturerRootCertificate");
    private final String value;

    InstallCertificateUseEnumType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    @JsonValue
    public String value() {
        return this.value;
    }

    @JsonCreator
    public static InstallCertificateUseEnumType fromValue(String value) {
        return findByField(
                InstallCertificateUseEnumType.class,
                InstallCertificateUseEnumType::value,
                value
        );
    }
}
