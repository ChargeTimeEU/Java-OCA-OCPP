package eu.chargetime.ocpp;

/**
 * Created by Thomas Volden on 04-May-16.
 */
public class PropertyConstraintException extends Exception {
    private final String fieldKey;
    private final Object fieldValue;

    @Override
    public String getMessage() {
        return message;
    }

    public Object getFieldValue() {
        return fieldValue;
    }

    public String getFieldKey() {
        return fieldKey;
    }

    private final String message;

    public PropertyConstraintException(String fieldKey, Object fieldValue) {
        this(fieldKey, fieldValue, null);
    }

    public PropertyConstraintException(String fieldKey, Object fieldValue, String message) {

        this.fieldKey = fieldKey;
        this.fieldValue = fieldValue;
        this.message = message;
    }

    @Override
    public String toString() {
        String output;
        if (fieldValue != null) {
            output = String.format("Field %s %s with value %s", fieldKey, message, fieldValue);
        }
        else {
            output = String.format("Field %s has invalid value %s", fieldKey, fieldValue);
        }
        return output;
    }

}
