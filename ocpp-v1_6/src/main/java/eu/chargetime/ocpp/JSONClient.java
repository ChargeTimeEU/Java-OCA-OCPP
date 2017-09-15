package eu.chargetime.ocpp;

import eu.chargetime.ocpp.feature.profile.ClientCoreProfile;

import javax.net.ssl.SSLContext;
import java.io.IOException;

/*
 * ChargeTime.eu - Java-OCA-OCPP
 *
 * MIT License
 *
 * Copyright (C) 2016 Thomas Volden <tv@chargetime.eu>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

/**
 * OCA OCPP version 1.6 JSON Web Socket implementation of the client.
 */
public class JSONClient extends Client {

    private final WebSocketTransmitter transmitter;

    /**
     * The core feature profile is required as a minimum.
     *
     * @param coreProfile   implementation of the core feature profile.
     * @param identity      identity of the charge point.
     */
    public JSONClient(ClientCoreProfile coreProfile, String identity) {
        this(coreProfile, identity, true);
    }

    /**
     * The core feature profile is required as a minimum.
     *
     * @param coreProfile        implementation of the core feature profile.
     * @param identity           identity of the charge point. Not used for JSON protocol.
     * @param handleRequestAsync sets the session request handler in async or blocking mode.
     */
    public JSONClient(ClientCoreProfile coreProfile, String identity, boolean handleRequestAsync) {
        this(new WebSocketTransmitter(), handleRequestAsync);
        addFeatureProfile(coreProfile);
    }

    private JSONClient(WebSocketTransmitter transmitter, boolean handleRequestAsync) {
        super(new Session(new JSONCommunicator(transmitter), new Queue(), handleRequestAsync));
        this.transmitter = transmitter;
    }

    public void enableWSS(SSLContext sslContext) throws IOException {
        transmitter.enableWSS(sslContext);
    }

}
