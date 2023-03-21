package eu.chargetime.ocpp.model.dataTypes.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import static eu.chargetime.ocpp.util.EnumUtil.findByField;

public enum GetCertificateIdUseEnumType {

    V_2_G_ROOT_CERTIFICATE("V2GRootCertificate"),
    MO_ROOT_CERTIFICATE("MORootCertificate"),
    CSMS_ROOT_CERTIFICATE("CSMSRootCertificate"),
    V_2_G_CERTIFICATE_CHAIN("V2GCertificateChain"),
    MANUFACTURER_ROOT_CERTIFICATE("ManufacturerRootCertificate");
    private final String value;

    GetCertificateIdUseEnumType(String value) {
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
    public static GetCertificateIdUseEnumType fromValue(String value) {
        return findByField(
                GetCertificateIdUseEnumType.class,
                GetCertificateIdUseEnumType::value,
                value
        );
    }
}
