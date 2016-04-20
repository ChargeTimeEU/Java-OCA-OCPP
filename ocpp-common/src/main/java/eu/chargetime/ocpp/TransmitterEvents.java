package eu.chargetime.ocpp;

/**
 * Created by Thomas Volden on 18-Apr-16.
 */
public interface TransmitterEvents
{
    void connected();
    void receivedMessage(String s);
    void disconnected();
}
