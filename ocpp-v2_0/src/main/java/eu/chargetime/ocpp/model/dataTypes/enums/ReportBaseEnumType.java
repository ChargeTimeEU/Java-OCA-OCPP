package eu.chargetime.ocpp.model.dataTypes.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import static eu.chargetime.ocpp.util.EnumUtil.findByField;


/**
 * This field specifies the report base.
 *
 *
 */
public enum ReportBaseEnumType {

    CONFIGURATION_INVENTORY("ConfigurationInventory"),
    FULL_INVENTORY("FullInventory"),
    SUMMARY_INVENTORY("SummaryInventory");
    private final String value;

    ReportBaseEnumType(String value) {
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
    public static ReportBaseEnumType fromValue(String value) {
        return findByField(
                ReportBaseEnumType.class,
                ReportBaseEnumType::value,
                value
        );
    }
}
