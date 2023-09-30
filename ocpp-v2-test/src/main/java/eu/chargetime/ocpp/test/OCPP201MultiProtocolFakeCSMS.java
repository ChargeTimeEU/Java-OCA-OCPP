/*
 * ChargeTime.eu - Java-OCA-OCPP
 *
 * Copyright (C) 2023 Robert Schlabbach (robert.schlabbach@ubitricity.com)
 *
 * MIT License
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

package eu.chargetime.ocpp.test;

import eu.chargetime.ocpp.MultiProtocolJSONServer;
import eu.chargetime.ocpp.ProtocolVersion;
import eu.chargetime.ocpp.feature.profile.ServerCoreProfile;
import eu.chargetime.ocpp.v201.feature.function.ServerProvisioningFunction;
import java.util.List;

/** Multiple protocol capable fake CSMS supporting up to OCPP 2.0.1 */
public class OCPP201MultiProtocolFakeCSMS implements FakeCSMS {

  private final MultiProtocolJSONServer server;
  private final OCPP201MultiProtocolDummyHandlers dummyHandlers;

  /**
   * Constructs the fake Charging Station Management System
   *
   * @param protocolVersions the {@link ProtocolVersion}s to support, ordered by preference
   */
  public OCPP201MultiProtocolFakeCSMS(List<ProtocolVersion> protocolVersions) {
    server = new MultiProtocolJSONServer(protocolVersions);
    dummyHandlers = new OCPP201MultiProtocolDummyHandlers();
    if (protocolVersions.contains(ProtocolVersion.OCPP1_6)) {
      server.addFeatureProfile(new ServerCoreProfile(dummyHandlers.createServerCoreEventHandler()));
    }
    if (protocolVersions.contains(ProtocolVersion.OCPP2_0_1)) {
      server.addFunction(
          ProtocolVersion.OCPP2_0_1,
          new ServerProvisioningFunction(dummyHandlers.createServerProvisioningEventHandler()));
    }
  }

  /** Starts the fake CSMS server on the local host, using an available port */
  @Override
  public void startServer() {
    server.open("localhost", 0, dummyHandlers.generateServerEventsHandler());
  }

  /**
   * Gets the port number the fake CSMS server is listening on
   *
   * @return the port number the fake CSMS server is listening on
   */
  @Override
  public int getPort() {
    return server.getPort();
  }

  /** Stops the fake CSMS server */
  @Override
  public void stopServer() {
    server.close();
  }
}
