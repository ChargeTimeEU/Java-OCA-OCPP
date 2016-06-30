package eu.chargetime.ocpp;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

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
 * Web Socket implementation of the Transmitter.
 */
public class WebSocketTransmitter implements Transmitter
{
    private WebSocketClient client;

    @Override
    public void connect(String uri, TransmitterEvents events) {
        client = new WebSocketClient(URI.create(uri))
        {
            @Override
            public void onOpen(ServerHandshake serverHandshake)
            {
                events.connected();
            }

            @Override
            public void onMessage(String s)
            {
                events.receivedMessage(s);
            }

            @Override
            public void onClose(int i, String s, boolean b)
            {
                events.disconnected();
            }

            @Override
            public void onError(Exception ex)
            {
                ex.printStackTrace();
            }
        };
        try {
            client.connectBlocking();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void disconnect()
    {
        try {
            client.closeBlocking();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void send(String request) {
        client.send(request);
    }
}
