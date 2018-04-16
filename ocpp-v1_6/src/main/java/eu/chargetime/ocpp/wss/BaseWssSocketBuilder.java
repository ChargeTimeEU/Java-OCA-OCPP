package eu.chargetime.ocpp.wss;

/*
 ubitricity.com - Java-OCA-OCPP

 MIT License

 Copyright (C) 2018 Evgeny Pakhomov <eugene.pakhomov@ubitricity.com>

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

import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import java.net.URI;

/**
 * Base implementation of WssSocketBuilder.
 */
public class BaseWssSocketBuilder implements WssSocketBuilder {

    public static final int DEFAULT_WSS_PORT = 443;
    private Proxy proxy = Proxy.NO_PROXY;
    private SSLSocketFactory sslSocketFactory;
    private boolean tcpNoDelay;
    private boolean reuseAddr;
    private boolean autoClose = true;
    private URI uri;

    // 0 for infinite timeout
    private int connectionTimeout = 0;

    @Override
    public WssSocketBuilder proxy(Proxy proxy) {
        this.proxy = proxy;
        return this;
    }

    @Override
    public WssSocketBuilder sslSocketFactory(SSLSocketFactory sslSocketFactory) {
        this.sslSocketFactory = sslSocketFactory;
        return this;
    }

    @Override
    public WssSocketBuilder tcpNoDelay(boolean tcpNoDelay) {
        this.tcpNoDelay = tcpNoDelay;
        return this;
    }

    @Override
    public WssSocketBuilder reuseAddr(boolean reuseAddr) {
        this.reuseAddr = reuseAddr;
        return this;
    }

    @Override
    public WssSocketBuilder autoClose(boolean autoClose) {
        this.autoClose = autoClose;
        return this;
    }

    @Override
    public WssSocketBuilder uri(URI uri) {
        this.uri = uri;
        return this;
    }

    @Override
    public WssSocketBuilder connectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
        return this;
    }

    @Override
    public Socket build() throws IOException {
        Socket socket = new Socket(proxy);
        socket.setTcpNoDelay(tcpNoDelay);
        socket.setReuseAddress(reuseAddr);

        if( !socket.isBound() ) {
            socket.connect(new InetSocketAddress(uri.getHost(), getPort(uri)), connectionTimeout);
        }

        return sslSocketFactory.createSocket(socket, uri.getHost(), getPort(uri), autoClose);
    }

    private int getPort(URI uri) {
        int port = uri.getPort();
        if( port == -1 ) {
            String scheme = uri.getScheme();
            if("wss".equals(scheme)) {
                return DEFAULT_WSS_PORT;
            } else if("ws".equals(scheme)) {
                throw new IllegalArgumentException("Not supported scheme: " + scheme);
            } else {
                throw new IllegalArgumentException("Unknown scheme: " + scheme);
            }
        }
        return port;
    }

}
