package eu.chargetime.ocpp.model.core;

import java.util.Arrays;

public enum Unit {
    Wh,
    kWh,
    varh,
    kvarh,
    W,
    kW,
    VA,
    kVA,
    var,
    kvar,
    A,
    V,
    Celsius,
    Fahrenheit,
    K,
    Percent;

    public static String[] getValues() {
        return Arrays.stream(Unit.values()).map(Enum::name).toArray(String[]::new);
    }
}
