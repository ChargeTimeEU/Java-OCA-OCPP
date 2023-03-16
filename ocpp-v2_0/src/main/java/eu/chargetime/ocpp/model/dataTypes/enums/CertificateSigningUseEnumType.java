package eu.chargetime.ocpp.model.dataTypes.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import static eu.chargetime.ocpp.util.EnumUtil.findByField;


/**
 * Indicates the type of the signed certificate that is returned. When omitted the certificate is used for both the 15118 connection (if implemented) and the Charging Station to CSMS connection. This field is required when a typeOfCertificate was included in the &lt;<signcertificaterequest,SignCertificateRequest>> that requested this certificate to be signed AND both the 15118 connection and the Charging Station connection are implemented.
 *
 *
 *
 */
public enum CertificateSigningUseEnumType {

    CHARGING_STATION_CERTIFICATE("ChargingStationCertificate"),
    V_2_G_CERTIFICATE("V2GCertificate");
    private final String value;

    CertificateSigningUseEnumType(String value) {
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
    public static CertificateSigningUseEnumType fromValue(String value) {
        return findByField(
                CertificateSigningUseEnumType.class,
                CertificateSigningUseEnumType::value,
                value
        );
    }
}
