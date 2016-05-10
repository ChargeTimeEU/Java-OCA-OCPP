package eu.chargetime.ocpp.utilities;

/**
 * Created by Thomas Volden on 09-May-16.
 */
public abstract class ModelUtil {

    public static boolean isAmong(Object needle, Object... hayStack) {
        boolean found = false;
        if (hayStack != null) {
            for (Object straw : hayStack) {
                if (found = isNullOrEqual(straw, needle)) {
                    break;
                }
            }
        }
        return found;
    }

    private static boolean isNullOrEqual(Object object1, Object object2) {
        boolean nullOrEqual = false;
        if (object1 == null && object2 == null) {
            nullOrEqual = true;
        } else if (object1 != null && object2 != null) {
            nullOrEqual = object1.equals(object2);
        }
        return nullOrEqual;
    }

    public static boolean validate(String input, int maxLength) {
        return input != null && input.length() <= maxLength;
    }
}
