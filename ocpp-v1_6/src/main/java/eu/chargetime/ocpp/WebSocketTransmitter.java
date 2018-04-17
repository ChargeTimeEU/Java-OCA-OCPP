package eu.chargetime.ocpp;

/*
 ChargeTime.eu - Java-OCA-OCPP
 Copyright (C) 2015-2016 Thomas Volden <tv@chargetime.eu>

 MIT License

 Copyright (C) 2016-2018 Thomas Volden

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

import eu.chargetime.ocpp.wss.WssSocketBuilder;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.exceptions.WebsocketNotConnectedException;
import org.java_websocket.extensions.IExtension;
import org.java_websocket.handshake.ServerHandshake;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.java_websocket.protocols.IProtocol;
import org.java_websocket.protocols.Protocol;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.net.ConnectException;
import java.net.URI;
import java.util.Collections;

/**
 * Web Socket implementation of the Transmitter.
 */
public class WebSocketTransmitter implements Transmitter
{
    private static final Logger logger = LoggerFactory.getLogger(WebSocketTransmitter.class);

    public static final String WSS_SCHEME = "wss";
    // In seconds
    private int pingInterval = 60;
    private volatile boolean closed = true;
    private WebSocketClient client;
    private WssSocketBuilder wssSocketBuilder;

    public WebSocketTransmitter() {
    }

    @Override
    public void connect(String uri, RadioEvents events) {
        final URI resource = URI.create(uri);
		Draft_6455 draft = new Draft_6455(Collections.<IExtension>emptyList(), Collections.<IProtocol>singletonList(new Protocol("ocpp1.6")));
        client = new WebSocketClient(resource, draft) {
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
                logger.debug("WebSocketClient.onClose: code = " + i + ", message = " + s + ", host closed = " + b);
                events.disconnected();
            }

            @Override
            public void onError(Exception ex)
            {
            	if(ex instanceof ConnectException) {
                	logger.warn("onError() triggered caused by: " +  ex);
            	} else {
            		logger.warn("onError() triggered", ex);
            	}
            }
        };

        if(WSS_SCHEME.equals(resource.getScheme())) {
		
			if(wssSocketBuilder == null) {
                throw new IllegalStateException("wssSocketBuilder must be set to support " + WSS_SCHEME + " scheme");
            }

            try {
                client.setSocket(wssSocketBuilder
                        .uri(resource)
                        .build());
            } catch (IOException ex) {
                logger.error("SSL socket creation failed", ex);
            }
        }

        client.setConnectionLostTimeout(pingInterval);

        try {
            client.connectBlocking();
            closed = false;
        } catch (Exception ex) {
        	logger.warn("client.connectBlocking() failed", ex);
        }
    }

    public void enableWSS(WssSocketBuilder wssSocketBuilder) {

        if(client != null) {
            throw new IllegalStateException("Cannot enable WSS on already connected client");
        }

        this.wssSocketBuilder = wssSocketBuilder;
    }

    public void setPingInterval(int interval) {
        this.pingInterval = pingInterval;

        if(client != null) {
            client.setConnectionLostTimeout(interval);
        }
    }

    @Override
    public void disconnect()
    {
        if(client == null) {
            return;
        }
        try {
            client.closeBlocking();
        } catch (Exception ex) {
        	logger.info("client.closeBlocking() failed", ex);
        } finally {
            client = null;
            closed = true;
        }
    }

    @Override
    public void send(Object request) throws NotConnectedException {
        logger.debug("Sending: " + request);
        try {
            client.send(request.toString());
        } catch (WebsocketNotConnectedException ex) {
            throw new NotConnectedException();
        }
    }

    public boolean isClosed() {
        return closed;
    }
}
