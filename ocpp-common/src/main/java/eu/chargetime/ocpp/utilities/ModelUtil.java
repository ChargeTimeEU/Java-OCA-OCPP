package eu.chargetime.ocpp.utilities;

/**
 * Created by Thomas Volden on 09-May-16.
 */
public class ModelUtil {

    public static boolean isAmoung(Object needle, Object... hayStack) {
        boolean found = false;
        for (Object straw: hayStack) {
            if (found = straw.equals(needle)) {
                break;
            }
        }
        return found;
    }
}
