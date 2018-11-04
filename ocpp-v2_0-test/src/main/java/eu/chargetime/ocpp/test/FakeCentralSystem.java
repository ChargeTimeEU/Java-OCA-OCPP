package eu.chargetime.ocpp.test;
/*
    ChargeTime.eu - Java-OCA-OCPP
    
    MIT License

    Copyright (C) 2018 Thomas Volden <tv@chargetime.eu>

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

import eu.chargetime.ocpp.IServerAPI;
import eu.chargetime.ocpp.JSONConfiguration;
import eu.chargetime.ocpp.JSONServer;
import eu.chargetime.ocpp.ServerEvents;
import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.model.SessionInformation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class FakeCentralSystem {
    private static final Logger logger = LoggerFactory.getLogger(FakeCentralSystem.class);
    private final int port = 8887;
    private final String host = "127.0.0.1";

    private IServerAPI server;
    private boolean isStarted;
    private UUID currentSession;

    public FakeCentralSystem() {
        JSONConfiguration configuration =
                JSONConfiguration.get()
                        .setParameter(JSONConfiguration.REUSE_ADDR_PARAMETER, true);
        server = new JSONServer(configuration);
        isStarted = false;
    }

    public void addFeature(Feature feature) {
        server.addFeature(feature);
    }

    public void started() throws Exception {
        if (!isStarted) {
            server.open(host, port, new ServerEvents() {
                @Override
                public void newSession(UUID sessionIndex, SessionInformation information) {
                    currentSession = sessionIndex;
                }

                @Override
                public void lostSession(UUID sessionIndex) {
                    currentSession = null;
                }
            });
            logger.info("Server started on host: {}, port: {}", host, port);
            isStarted = true;
        }
    }

    public void stopped() {
        server.close();
        isStarted = false;
    }
}
