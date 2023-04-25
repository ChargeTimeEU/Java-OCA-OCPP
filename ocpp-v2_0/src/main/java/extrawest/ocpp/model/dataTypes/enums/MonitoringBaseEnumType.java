package extrawest.ocpp.model.dataTypes.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import static extrawest.ocpp.util.EnumUtil.findByField;


/**
 * Specify which monitoring base will be set
 *
 *
 */
public enum MonitoringBaseEnumType {

    ALL("All"),
    FACTORY_DEFAULT("FactoryDefault"),
    HARD_WIRED_ONLY("HardWiredOnly");
    private final String value;

    MonitoringBaseEnumType(String value) {
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
    public static MonitoringBaseEnumType fromValue(String value) {
        return findByField(
                MonitoringBaseEnumType.class,
                MonitoringBaseEnumType::value,
                value
        );
    }
}
