package eu.chargetime.ocpp;

/**
 * Created by Thomas Volden on 20-Apr-16.
 */
public interface Transmitter
{
    void connect(String uri, TransmitterEvents events);
    void disconnect();
    void send(String message);
}
