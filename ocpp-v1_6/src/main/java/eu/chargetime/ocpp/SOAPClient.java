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

import com.sun.net.httpserver.HttpServer;
import eu.chargetime.ocpp.feature.profile.ClientCoreProfile;
import eu.chargetime.ocpp.model.SOAPHostInfo;

import javax.xml.soap.SOAPMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URL;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SOAPClient extends Client {
	private static final Logger logger = LoggerFactory.getLogger(SOAPClient.class);
	private static final String WSDL_CHARGE_POINT = "eu/chargetime/ocpp/OCPP_ChargePointService_1.6.wsdl";

    private SOAPCommunicator communicator;
    private WebServiceTransmitter transmitter;
    private URL callback;
    private HttpServer server;
    private ExecutorService threadPool;

    /**
     * The core feature profile is required.
     * The client will use the information taken from the callback parameter to open a HTTP based Web Service.
     *
     * @param chargeBoxIdentity required identity used in message header.
     * @param callback          call back info that the server can send requests to.
     * @param coreProfile       implementation of the core feature profile.
     */
    public SOAPClient(String chargeBoxIdentity, URL callback, ClientCoreProfile coreProfile) {
        this(chargeBoxIdentity, callback, coreProfile, true);
    }

    /**
     * The core feature profile is required.
     * The client will use the information taken from the callback parameter to open a HTTP based Web Service.
     *
     * @param chargeBoxIdentity  required identity used in message header.
     * @param callback           call back info that the server can send requests to.
     * @param coreProfile        implementation of the core feature profile.
     * @param handleRequestAsync sets the session request handler in async or blocking mode.
     */
    public SOAPClient(String chargeBoxIdentity, URL callback, ClientCoreProfile coreProfile, boolean handleRequestAsync) {
        this(new SOAPHostInfo.Builder().isClient(true).chargeBoxIdentity(chargeBoxIdentity).fromUrl(callback.toString()).namespace(SOAPHostInfo.NAMESPACE_CHARGEBOX).build(), new WebServiceTransmitter(), handleRequestAsync);
        this.callback = callback;
        addFeatureProfile(coreProfile);
    }

    private SOAPClient(SOAPHostInfo hostInfo, WebServiceTransmitter transmitter, boolean handleRequestAsync) {
        this(new SOAPCommunicator(hostInfo, transmitter), handleRequestAsync);
        this.transmitter = transmitter;
    }

    private SOAPClient(SOAPCommunicator communicator, boolean handleRequestAsync) {
        super(new Session(communicator, new Queue(), handleRequestAsync));
        this.communicator = communicator;
    }

    /**
     * Connect to server and set To header.
     * Client opens a WebService for incoming requests.
     *
     * @param uri url and port of the server
     */
    @Override
    public void connect(String uri, ClientEvents events) {
        communicator.setToUrl(uri);
        super.connect(uri, events);
        openWS();
    }

    /**
     * Disconnect from server
     * Closes down local callback service.
     */
    @Override
    public void disconnect() {
        super.disconnect();
        if (server != null) {
            server.stop(1);
            threadPool.shutdownNow();
        }
    }

    private int getPort() {
        return callback.getPort() == -1 ? 8000 : callback.getPort();
    }

    private void openWS() {
        try {
            server = HttpServer.create(new InetSocketAddress(callback.getHost(), getPort()), 0);
            server.createContext("/", new WSHttpHandler(WSDL_CHARGE_POINT, message -> {
                SOAPMessage soapMessage = null;
                try {
                    soapMessage = transmitter.relay(message.getMessage()).get();
                } catch (InterruptedException e) {
                    logger.warn("openWS() transmitter.relay failed", e);
                } catch (ExecutionException e) {
                    logger.warn("openWS() transmitter.relay failed", e);
                }
                return soapMessage;
            }));
            threadPool = Executors.newCachedThreadPool();
            server.setExecutor(threadPool);
            server.start();
        } catch (IOException e) {
            logger.warn("openWS() failed", e);
        }
    }


}
