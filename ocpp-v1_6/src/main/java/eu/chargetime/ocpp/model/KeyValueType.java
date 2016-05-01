package eu.chargetime.ocpp.model;

/**
 * Created by Thomas Volden on 01-May-16.
 */
public class KeyValueType {
    private String key;
    private boolean readonly;
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public boolean isReadonly() {
        return readonly;
    }

    public void setReadonly(boolean readonly) {
        this.readonly = readonly;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
