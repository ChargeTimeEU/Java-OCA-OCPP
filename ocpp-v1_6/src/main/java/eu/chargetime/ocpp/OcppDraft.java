package eu.chargetime.ocpp;
/*
    ChargeTime.eu - Java-OCA-OCPP

    MIT License

    Copyright (C) 2016-2018 Thomas Volden <tv@chargetime.eu>

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

import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.exceptions.InvalidHandshakeException;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.handshake.ClientHandshakeBuilder;
import org.java_websocket.handshake.HandshakeBuilder;
import org.java_websocket.handshake.ServerHandshakeBuilder;

public class OcppDraft extends Draft_6455 {

    private final String SUBPROTOCOL = "ocpp1.6";

    @Override
    public ClientHandshakeBuilder postProcessHandshakeRequestAsClient(ClientHandshakeBuilder request) {
        ClientHandshakeBuilder clientHandshakeBuilder = postProcessHandshakeRequestAsClient(request);
        clientHandshakeBuilder.put("Sec-WebSocket-Protocol", SUBPROTOCOL);
        return clientHandshakeBuilder;
    }

    @Override
    public HandshakeBuilder postProcessHandshakeResponseAsServer(ClientHandshake request, ServerHandshakeBuilder response) throws InvalidHandshakeException {
        HandshakeBuilder handshakeBuilder = postProcessHandshakeResponseAsServer(request, response);
        String subProtocol = request.getFieldValue("Sec-WebSocket-Protocol");
        if (subProtocol.length() > 0) {
            handshakeBuilder.put("Sec-WebSocket-Protocol", subProtocol);
        }
        return handshakeBuilder;
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 31 * hash + SUBPROTOCOL.hashCode();
        return hash;
    }

    @Override
    public String toString() {
        String result = super.toString();
        result += " sub protocol: " + SUBPROTOCOL;
        return result;
    }
}