/*
 * ChargeTime.eu - Java-OCA-OCPP
 *
 * Copyright (C) 2025 Robert Schlabbach (robert.schlabbach@ubitricity.com)
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

import static eu.chargetime.ocpp.ProtocolVersion.OCPP2_1;

import eu.chargetime.ocpp.*;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.v21.feature.function.ServerDiagnosticsFunction;
import eu.chargetime.ocpp.v21.feature.function.ServerProvisioningFunction;
import eu.chargetime.ocpp.v21.model.messages.GetVariablesRequest;
import eu.chargetime.ocpp.v21.model.messages.GetVariablesResponse;
import eu.chargetime.ocpp.v21.model.types.Component;
import eu.chargetime.ocpp.v21.model.types.GetVariableData;
import eu.chargetime.ocpp.v21.model.types.Variable;
import java.util.List;

/** Multiple protocol capable fake CSMS supporting up to OCPP 2.1 */
public class OCPP21MultiProtocolFakeCSMS extends OCPP201MultiProtocolFakeCSMS {

  /**
   * Constructs the fake Charging Station Management System
   *
   * @param protocolVersions the {@link ProtocolVersion}s to support, ordered by preference
   */
  public OCPP21MultiProtocolFakeCSMS(List<ProtocolVersion> protocolVersions) {
    this(protocolVersions, new OCPP21MultiProtocolDummyHandlers());
  }

  /**
   * Constructs the fake Charging Station Management System
   *
   * @param protocolVersions the {@link ProtocolVersion}s to support, ordered by preference
   * @param dummyHandlers the {@link OCPP21MultiProtocolDummyHandlers} to fake OCPP 2.1 protocol
   *     handling
   */
  public OCPP21MultiProtocolFakeCSMS(
      List<ProtocolVersion> protocolVersions, OCPP21MultiProtocolDummyHandlers dummyHandlers) {
    super(protocolVersions, dummyHandlers);
    if (protocolVersions.contains(OCPP2_1)) {
      getServer()
          .addFunction(
              OCPP2_1,
              new ServerProvisioningFunction(
                  dummyHandlers.createOCPP21ServerProvisioningEventHandler()));
      getServer()
          .addFunction(
              OCPP2_1,
              new ServerDiagnosticsFunction(
                  dummyHandlers.createOCPP21ServerDiagnosticsEventHandler()));
    }
  }

  @Override
  public void sendGetVariablesRequest() {
    if (!OCPP2_1.equals(getDummyHandlers().getCurrentProtocolVersion())) {
      super.sendGetVariablesRequest();
    } else {
      GetVariableData getVariableData = new GetVariableData(new Component(""), new Variable(""));
      GetVariablesRequest getVariablesRequest =
          new GetVariablesRequest(new GetVariableData[] {getVariableData});
      sendRequestAndWaitForResponse(getVariablesRequest, GetVariablesResponse.class);
    }
  }

  @Override
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
