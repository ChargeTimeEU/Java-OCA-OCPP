package eu.chargetime.ocpp.util;

import java.util.function.Function;

public final class EnumUtil {

    private EnumUtil() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static <T extends Enum<T>> T findByField(Class<T> enumType,
                                                    Function<T, String> fieldSelector,
                                                    String fieldValue) {
        for (T enumValue : enumType.getEnumConstants()) {
            if (fieldSelector.apply(enumValue).equals(fieldValue)) {
                return enumValue;
            }
        }
        throw new IllegalArgumentException("No enum constant %s with field value %s"
                .formatted(enumType.getName(), fieldValue));
    }

}
