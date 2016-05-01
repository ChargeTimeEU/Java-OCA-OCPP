package eu.chargetime.ocpp.model;

/**
 * Created by Thomas Volden on 01-May-16.
 */
public class GetConfigurationConfirmation implements Confirmation {
    private KeyValueType[] configurationKey;

    public KeyValueType[] getConfigurationKey() {
        return configurationKey;
    }

    public void setConfigurationKey(KeyValueType[] configurationKey) {
        this.configurationKey = configurationKey;
    }

    public String[] getUnknownKey() {
        return unknownKey;
    }

    public void setUnknownKey(String[] unknownKey) {
        this.unknownKey = unknownKey;
    }

    private String[] unknownKey;
}
