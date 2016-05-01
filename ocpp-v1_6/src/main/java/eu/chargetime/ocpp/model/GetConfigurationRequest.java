package eu.chargetime.ocpp.model;

/**
 * Created by Thomas Volden on 01-May-16.
 */
public class GetConfigurationRequest implements Request {
    private String[] key;

    public String[] getKey() {
        return key;
    }

    public void setKey(String[] key) {
        this.key = key;
    }
}
