package eu.chargetime.ocpp.model.dataTypes.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import static eu.chargetime.ocpp.util.EnumUtil.findByField;


/**
 * Certificate status information.
 * - if all certificates are valid: return 'Accepted'.
 * - if one of the certificates was revoked, return 'CertificateRevoked'.
 */
public enum AuthorizeCertificateStatusEnumType {

    ACCEPTED("Accepted"),
    SIGNATURE_ERROR("SignatureError"),
    CERTIFICATE_EXPIRED("CertificateExpired"),
    CERTIFICATE_REVOKED("CertificateRevoked"),
    NO_CERTIFICATE_AVAILABLE("NoCertificateAvailable"),
    CERT_CHAIN_ERROR("CertChainError"),
    CONTRACT_CANCELLED("ContractCancelled");
    private final String value;

    AuthorizeCertificateStatusEnumType(String value) {
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
    public static AuthorizeCertificateStatusEnumType fromValue(String value) {
        return findByField(
                AuthorizeCertificateStatusEnumType.class,
                AuthorizeCertificateStatusEnumType::value,
                value
        );
    }

}
