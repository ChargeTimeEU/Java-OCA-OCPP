package eu.chargetime.ocpp;

import eu.chargetime.ocpp.model.*;
import org.apache.ivy.core.report.ConfigurationResolveReport;
import org.json.JSONArray;

/**
 ChargeTime.eu - Java-OCA-OCPP
 Copyright (C) 2015-2016 Thomas Volden <tv@chargetime.eu>

 MIT License

 Copyright (c) 2016 Thomas Volden

 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all
 copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 SOFTWARE.
 */
public abstract class Communicator {
    protected Transmitter transmitter;

    public abstract <T> T unpackPayload(String payload, Class<T> type);
    public abstract String packPayload(Object payload);
    protected abstract String makeCallResult(String uniqueId, String payload);
    protected abstract String makeCall(String uniqueId, String action, String payload);
    protected abstract String makeCallError(String uniqueId, String errorCode, String errorDescription);


    protected abstract Message parse(String message);

    public Communicator(Transmitter transmitter) {
        this.transmitter = transmitter;
    }

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

    public void sendCallError(String uniqueId, String errorCode, String errorDescription) {
        transmitter.send(makeCallError(uniqueId, errorCode, errorDescription));
    }

    public void disconnect() {
        transmitter.disconnect();
    }
}
