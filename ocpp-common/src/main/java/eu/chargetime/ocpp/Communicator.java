package eu.chargetime.ocpp;

/**
 * Created by Thomas Volden on 26-Apr-16.
 */
public interface Communicator
{
    <T> T unpack(String payload, Class<T> type);
    String pack(Object payload);
}
