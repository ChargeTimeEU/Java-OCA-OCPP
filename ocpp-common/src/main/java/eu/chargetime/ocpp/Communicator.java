package eu.chargetime.ocpp;

import eu.chargetime.ocpp.model.*;
import org.apache.ivy.core.report.ConfigurationResolveReport;
import org.json.JSONArray;

/**
 * Created by Thomas Volden on 26-Apr-16.
 */
public abstract class Communicator
{
    private final String CALLFORMAT = "[2,\"%s\",\"%s\",%s]";

    protected Transmitter transmitter;

    public Communicator(Transmitter transmitter) {
        this.transmitter = transmitter;
    }

    public abstract <T> T unpackPayload(String payload, Class<T> type);
    public abstract String packPayload(Object payload);

    protected abstract String makeCallResult(String uniqueId, String payload);
    protected abstract String makeCall(String uniqueId, String action, String payload);
    protected abstract Message parse(String message);

    public void connect(String uri, CommunicatorEvents events) {
        transmitter.connect(uri, new TransmitterEvents() {
            @Override
            public void connected() {
                events.onConnected();
            }

            @Override
            public void receivedMessage(String input) {
                System.out.println(input);
                Message message = parse(input);
                if(message instanceof CallResultMessage) {
                    events.onCallResult(message.getId(), message.getPayload());
                } else if (message instanceof CallMessage) {
                    CallMessage call = (CallMessage)message;
                    events.onCall(call.getId(), call.getAction(), call.getPayload());
                }
            }

            @Override
            public void disconnected() {
                events.onDisconnected();
            }
        });
    }

    public void sendCall(String uniqueId, String action, Request request) {
        transmitter.send(makeCall(uniqueId, action, packPayload(request)));
    }

    public void sendCallResult(String uniqueId, Confirmation confirmation) {
        transmitter.send(makeCallResult(uniqueId, packPayload(confirmation)));
    }

    public void disconnect() {
        transmitter.disconnect();
    }
}
