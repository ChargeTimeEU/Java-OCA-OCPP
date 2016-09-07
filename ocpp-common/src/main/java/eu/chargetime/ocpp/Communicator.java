package eu.chargetime.ocpp;

import eu.chargetime.ocpp.model.*;

/*
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

/**
 * Abstract class.
 * Handles basic communication:
 * Pack and send messages.
 * Receive and unpack messages.
 * <p>
 * Requires a {@link Transmitter} to send and receive messages.
 * Must be overloaded to implement a specific format.
 */
public abstract class Communicator {
    protected Radio radio;

    /**
     * Convert a formatted string into a {@link Request}/{@link Confirmation}.
     * This is useful for call results, where the  confirmation type isn't given.
     *
     * @param   payload     the raw formatted payload.
     * @param   type        the expected return type.
     * @return the unpacked payload.
     * @throws Exception    error occurred while converting.
     */
    public abstract <T> T unpackPayload(String payload, Class<T> type) throws Exception;

    /**
     * Convert a {@link Request}/{@link Confirmation} into a formatted string.
     *
     * @param   payload     the payload model.
     * @return the payload in the form of a formatted string.
     */
    public abstract String packPayload(Object payload);

    /**
     * Create a call result envelope to transmit.
     *
     * @param   uniqueId    the id the receiver expects.
     * @param   payload     packed payload.
     * @return a fully packed message ready to send.
     */
    protected abstract String makeCallResult(String uniqueId, String payload);

    /**
     * Create a call envelope to transmit to the server.
     *
     * @param   uniqueId    the id the receiver must reply with.
     * @param   action      action name of the feature.
     * @param   payload     packed payload.
     * @return a fully packed message ready to send.
     */
    protected abstract String makeCall(String uniqueId, String action, String payload);

    /**
     * Create a call error envelope to transmit.
     *
     * @param   uniqueId            the id the receiver expects.
     * @param   errorCode           an OCPP error code.
     * @param   errorDescription    an associated error description.
     * @return a fully packed message ready to send.
     */
    protected abstract String makeCallError(String uniqueId, String errorCode, String errorDescription);

    /**
     * Identify an incoming call and parse it into one of the following:
     * {@link CallMessage} a request.
     * {@link CallResultMessage} a response.
     *
     * @param   message     the raw message
     * @return CallMessage or {@link CallResultMessage}
     */
    protected abstract Message parse(String message);

    /**
     * Handle required injections.
     *
     * @param   transmitter Injected {@link Transmitter}
     */
    public Communicator(Radio transmitter) {
        this.radio = transmitter;
    }

    /**
     * Use the injected {@link Transmitter} to connect to server.
     * @param   uri     the url and port of the server.
     * @param   events  handler for call back events.
     */
    public void connect(String uri, CommunicatorEvents events) {
        if (radio instanceof Transmitter)
            ((Transmitter) radio).connect(uri, new EventHandler(events));
    }

    /**
     * Use the injected {@link Transmitter} to accept a client.
     *
     * @param events handler for call back events.
     */
    public void accept(CommunicatorEvents events) {
        if (radio instanceof Receiver)
            ((Receiver) radio).accept(new EventHandler(events));
    }

    /**
     * Send a new {@link Request}.
     *
     * @param   uniqueId    the id the receiver should use to reply.
     * @param   action      action name of the {@link eu.chargetime.ocpp.feature.Feature}.
     * @param   request     the outgoing {@link Request}
     */
    public void sendCall(String uniqueId, String action, Request request) {
        radio.send(makeCall(uniqueId, action, packPayload(request)));
    }

    /**
     * Send a {@link Confirmation} reply to a {@link Request}.
     *
     * @param   uniqueId        the id the receiver expects.
     * @param   confirmation    the outgoing {@link Confirmation}
     */
    public void sendCallResult(String uniqueId, Confirmation confirmation) {
        radio.send(makeCallResult(uniqueId, packPayload(confirmation)));
    }

    /**
     * Send an error.
     *
     * @param   uniqueId            the id the receiver expects a response to.
     * @param   errorCode           an OCPP error Code
     * @param   errorDescription    a associated error description.
     */
    public void sendCallError(String uniqueId, String errorCode, String errorDescription) {
        radio.send(makeCallError(uniqueId, errorCode, errorDescription));
    }

    /**
     * Close down the connection. Uses the {@link Transmitter}.
     */
    public void disconnect() {
        radio.disconnect();
    }

    private class EventHandler implements RadioEvents {
        private final CommunicatorEvents events;

        public EventHandler(CommunicatorEvents events) {
            this.events = events;
        }

        @Override
        public void connected() {
            events.onConnected();
        }

        @Override
        public void receivedMessage(String input) {
            Message message = parse(input);
            if (message instanceof CallResultMessage) {
                events.onCallResult(message.getId(), message.getPayload());
            } else if (message instanceof CallErrorMessage) {
                CallErrorMessage call = (CallErrorMessage) message;
                events.onError(call.getId(), call.getErrorCode(), call.getErrorDescription(), call.getRawPayload());
            } else if (message instanceof CallMessage) {
                CallMessage call = (CallMessage) message;
                events.onCall(call.getId(), call.getAction(), call.getPayload());
            }
        }

        @Override
        public void disconnected() {
            events.onDisconnected();
        }
    }
}
