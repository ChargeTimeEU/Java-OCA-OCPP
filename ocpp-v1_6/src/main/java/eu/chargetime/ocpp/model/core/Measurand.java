package eu.chargetime.ocpp.model.core;

import java.util.Arrays;

public enum Measurand {
    Current_Export,
    Current_Import,
    Current_Offered,
    Energy_Active_Export_Register,
    Energy_Active_Import_Register,
    Energy_Reactive_Export_Register,
    Energy_Reactive_Import_Register,
    Energy_Active_Export_Interval,
    Energy_Active_Import_Interval,
    Energy_Reactive_Export_Interval,
    Energy_Reactive_Import_Interval,
    Frequency,
    Power_Active_Export,
    Power_Active_Import,
    Power_Factor,
    Power_Offered,
    Power_Reactive_Export,
    Power_Reactive_Import,
    RPM,
    SoC,
    Temperature,
    Voltage;

    public static Measurand valueOfStr(String str) {
        return Measurand.valueOf(str.replace(".", "_"));
    }

    public static String nameOfValue(Measurand measurand) {
        return measurand.name().replace("_", ".");
    }

    public static String[] getValues() {
        return Arrays.stream(Measurand.values()).map(Enum::name)
                                      .map(i -> i = i.replace("_", "."))
                                      .toArray(String[]::new);
    }
}
