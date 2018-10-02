package eu.chargetime.ocpp.model.core;

import java.util.Arrays;

public enum Phase {
    L1,
    L2,
    L3,
    N,
    L1_N,
    L2_N,
    L3_N,
    L1_L2,
    L2_L3,
    L3_L1;

    public static Phase valueOfStr(String str) {
        return Phase.valueOf(str.replace("-", "_"));
    }

    public static String nameOfValue(Phase phase) {
        return phase.name().replace("_", "-");
    }

    public static String[] getValues() {
        return Arrays.stream(Phase.values()).map(Enum::name)
                                  .map(i -> i = i.replace("_", "-"))
                                  .toArray(String[]::new);
    }
}
