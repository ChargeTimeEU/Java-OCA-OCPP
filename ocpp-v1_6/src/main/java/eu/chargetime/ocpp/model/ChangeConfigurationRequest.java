package eu.chargetime.ocpp.model;

/**
 * Created by Thomas Volden on 02-May-16.
 */
public class ChangeConfigurationRequest implements Request {
    private String key;
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean validate() {
        return false;
    }
}
