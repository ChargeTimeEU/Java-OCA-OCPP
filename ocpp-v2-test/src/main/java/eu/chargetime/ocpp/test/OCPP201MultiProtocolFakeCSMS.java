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

import static eu.chargetime.ocpp.ProtocolVersion.OCPP1_6;
import static eu.chargetime.ocpp.ProtocolVersion.OCPP2_0_1;

import eu.chargetime.ocpp.*;
import eu.chargetime.ocpp.feature.profile.ServerCoreProfile;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.v201.feature.function.ServerProvisioningFunction;
import eu.chargetime.ocpp.v201.model.messages.GetVariablesRequest;
import eu.chargetime.ocpp.v201.model.messages.GetVariablesResponse;
import eu.chargetime.ocpp.v201.model.types.Component;
import eu.chargetime.ocpp.v201.model.types.GetVariableData;
import eu.chargetime.ocpp.v201.model.types.Variable;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.function.BiConsumer;
import javax.annotation.Nullable;

/** Multiple protocol capable fake CSMS supporting up to OCPP 2.0.1 */
public class OCPP201MultiProtocolFakeCSMS implements FakeCSMS {

  private final MultiProtocolJSONServer server;
  private final OCPP201MultiProtocolDummyHandlers dummyHandlers;

  private Confirmation receivedConfirmation;
  private Throwable receivedException;

  /**
   * Constructs the fake Charging Station Management System
   *
   * @param protocolVersions the {@link ProtocolVersion}s to support, ordered by preference
   */
  public OCPP201MultiProtocolFakeCSMS(List<ProtocolVersion> protocolVersions) {
    this(protocolVersions, new OCPP201MultiProtocolDummyHandlers());
  }

  /**
   * Constructs the fake Charging Station Management System
   *
   * @param protocolVersions the {@link ProtocolVersion}s to support, ordered by preference
   * @param dummyHandlers the {@link OCPP201MultiProtocolDummyHandlers} to fake OCPP 2.0.1 protocol
   *     handling
   */
  public OCPP201MultiProtocolFakeCSMS(
      List<ProtocolVersion> protocolVersions, OCPP201MultiProtocolDummyHandlers dummyHandlers) {
    this.dummyHandlers = dummyHandlers;
    server = new MultiProtocolJSONServer(protocolVersions);
    if (protocolVersions.contains(OCPP1_6)) {
      server.addFeatureProfile(new ServerCoreProfile(dummyHandlers.createServerCoreEventHandler()));
    }
    if (protocolVersions.contains(OCPP2_0_1)) {
      server.addFunction(
          OCPP2_0_1,
          new ServerProvisioningFunction(dummyHandlers.createServerProvisioningEventHandler()));
    }
  }

  /**
   * Gets the {@link MultiProtocolJSONServer}
   *
   * @return the {@link MultiProtocolJSONServer}
   */
  MultiProtocolJSONServer getServer() {
    return server;
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

  /**
   * Returns the dummy handlers
   *
   * @return the dummy handlers
   */
  @Override
  public DummyHandlers getDummyHandlers() {
    return dummyHandlers;
  }

  @Override
  public void sendGetVariablesRequest() {
    if (!OCPP2_0_1.equals(getDummyHandlers().getCurrentProtocolVersion())) {
      throw new UnsupportedOperationException(
          "Cannot send a GetVariablesRequest for "
              + getDummyHandlers().getCurrentProtocolVersion());
    }
    GetVariableData getVariableData = new GetVariableData(new Component(""), new Variable(""));
    GetVariablesRequest getVariablesRequest =
        new GetVariablesRequest(new GetVariableData[] {getVariableData});
    sendRequestAndWaitForResponse(getVariablesRequest, GetVariablesResponse.class);
  }

  void sendRequestAndWaitForResponse(
      Request request, @Nullable Class<? extends Confirmation> responseClass) {
    try {
      CompletableFuture<Confirmation> future =
          getServer()
              .send(getDummyHandlers().getCurrentSessionIndex(), request)
              .toCompletableFuture();
      BiConsumer<Confirmation, Throwable> action =
          (confirmation, throwable) -> {
            if (confirmation != null) {
              receivedConfirmation = confirmation;
              receivedException = null;
              checkConfirmation(confirmation);
            } else if (throwable != null) {
              receivedConfirmation = null;
              receivedException = throwable;
            } else {
              receivedConfirmation = null;
              receivedException = null;
            }
          };
      future.whenComplete(action);
      try {
        future.join();
      } catch (CompletionException e) {
        // ignore
      }
    } catch (NotConnectedException | OccurenceConstraintException | UnsupportedFeatureException e) {
      throw new RuntimeException(e);
    }
    if (receivedException != null) {
      throw new RuntimeException("Received exception in response to request", receivedException);
    }
    if (receivedConfirmation != null && responseClass != null) {
      if (!responseClass.isInstance(receivedConfirmation)) {
        throw new IllegalArgumentException("Received confirmation is not of expected class");
      }
    } else if (responseClass != null) {
      throw new IllegalStateException("Received neither a confirmation nor a throwable");
    }
  }

  void checkConfirmation(Confirmation confirmation) {
    if (confirmation instanceof GetVariablesResponse) {
      GetVariablesResponse getVariablesResponse = (GetVariablesResponse) confirmation;
      if (getVariablesResponse.getGetVariableResult().length != 1) {
        throw new PropertyConstraintException(
            getVariablesResponse.getGetVariableResult().length,
            "GetVariablesResponse getVariableResult length must be 1");
      }
    }
  }
}
