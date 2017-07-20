package eu.chargetime.ocpp;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.exceptions.WebsocketNotConnectedException;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	private static final Logger logger = LoggerFactory.getLogger(WebSocketTransmitter.class);
	
    private WebSocketClient client;

    @Override
    public void connect(String uri, RadioEvents events) {
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
            	logger.warn("onError() triggered", ex);
            }
        };
        try {
            client.connectBlocking();
        } catch (Exception ex) {
        	logger.warn("client.connectBlocking() failed", ex);
        }
    }

    @Override
    public void disconnect()
    {
        try {
            client.closeBlocking();
        } catch (Exception ex) {
        	logger.info("client.closeBlocking() failed", ex);
        }
    }

    @Override
    public void send(Object request) throws NotConnectedException {
        try {
            client.send(request.toString());
        } catch (WebsocketNotConnectedException ex) {
            throw new NotConnectedException();
        }
    }
}
