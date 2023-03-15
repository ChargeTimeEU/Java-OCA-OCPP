package eu.chargetime.ocpp.model.dataTypes.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import static eu.chargetime.ocpp.util.EnumUtil.findByField;


/**
 * Used algorithms for the hashes provided.
 *
 *
 */
public enum HashAlgorithmEnumType {

    SHA_256("SHA256"),
    SHA_384("SHA384"),
    SHA_512("SHA512");
    private final String value;

    HashAlgorithmEnumType(String value) {
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
    public static HashAlgorithmEnumType fromValue(String value) {
        return findByField(
                HashAlgorithmEnumType.class,
                HashAlgorithmEnumType::value,
                value
        );
    }

}
