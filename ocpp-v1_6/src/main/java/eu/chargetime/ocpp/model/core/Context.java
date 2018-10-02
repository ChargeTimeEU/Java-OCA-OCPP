package eu.chargetime.ocpp.model.core;

import java.util.Arrays;

public enum Context {
    Interruption_Begin,
    Interruption_End,
    Other,
    Sample_Clock,
    Sample_Periodic,
    Transaction_Begin,
    Transaction_End,
    Trigger;

    public static Context valueOfStr(String str) {
        return Context.valueOf(str.replace(".", "_"));
    }

    public static String nameOfValue(Context context) {
        return context.name().replace("_", ".");
    }

    public static String[] getValues() {
        return Arrays.stream(Context.values()).map(Enum::name)
                                              .map(i -> i = i.replace("_", "."))
                                              .toArray(String[]::new);
    }
}
