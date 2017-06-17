package eu.chargetime.ocpp;
/*
    ChargeTime.eu - Java-OCA-OCPP
    
    MIT License

    Copyright (C) 2016 Thomas Volden <tv@chargetime.eu>

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

import eu.chargetime.ocpp.model.SessionInformation;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.HashMap;

public class WebSocketListener implements Listener {
	private static final Logger logger = LoggerFactory.getLogger(WebSocketListener.class);
	
    private WebSocketServer server;
    private HashMap<WebSocket, WebSocketReceiver> sockets;
    private boolean handleRequestAsync;

    public WebSocketListener() {
        sockets = new HashMap<>();
    }

    @Override
    public void open(String hostname, int port, ListenerEvents handler) {
        server = new WebSocketServer(new InetSocketAddress(hostname, port)) {
            @Override
            public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
                WebSocketReceiver receiver = new WebSocketReceiver(message -> webSocket.send(message));
                sockets.put(webSocket, receiver);
                SessionInformation information = new SessionInformation.Builder()
                        .Identifier(clientHandshake.getResourceDescriptor())
                        .InternetAddress(webSocket.getRemoteSocketAddress()).build();

                handler.newSession(new Session(new JSONCommunicator(receiver), new Queue(), handleRequestAsync), information);
            }

            @Override
            public void onClose(WebSocket webSocket, int i, String s, boolean b) {
                sockets.get(webSocket).disconnect();
                sockets.remove(webSocket);
            }

            @Override
            public void onMessage(WebSocket webSocket, String s) {
                sockets.get(webSocket).relay(s);
            }

            @Override
            public void onError(WebSocket webSocket, Exception e) {

            }
        };
        server.start();
    }

    @Override
    public void close() {
        try {

            for (WebSocket ws : sockets.keySet())
                ws.close();

            sockets.clear();

            server.stop(1);

        } catch (IOException e) {
        	logger.info("close() failed", e);
        } catch (InterruptedException e) {
        	logger.info("close() failed", e);
        }
    }

    @Override
    public void setAsyncRequestHandler(boolean async) {
        this.handleRequestAsync = async;
    }
}
