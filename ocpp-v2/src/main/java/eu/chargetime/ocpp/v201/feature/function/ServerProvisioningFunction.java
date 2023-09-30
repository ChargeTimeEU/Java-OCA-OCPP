/*
   ChargeTime.eu - Java-OCA-OCPP

   MIT License

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

package eu.chargetime.ocpp.v201.feature.function;

import eu.chargetime.ocpp.feature.FunctionFeature;
import eu.chargetime.ocpp.feature.function.Function;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.v201.feature.*;
import eu.chargetime.ocpp.v201.model.messages.*;
import eu.chargetime.ocpp.v201.model.types.*;
import java.util.ArrayList;
import java.util.UUID;

/** Class with server request creators and handlers for the Provisioning functional block. */
public class ServerProvisioningFunction implements Function {

  private final ServerProvisioningEventHandler eventHandler;
  private final ArrayList<FunctionFeature> features;

  public ServerProvisioningFunction(ServerProvisioningEventHandler eventHandler) {
    this.eventHandler = eventHandler;
    features = new ArrayList<>();
    features.add(new BootNotificationFeature(this));
    features.add(new GetBaseReportFeature(null));
    features.add(new GetReportFeature(null));
    features.add(new GetVariablesFeature(null));
    features.add(new HeartbeatFeature(this));
    features.add(new NotifyReportFeature(this));
    features.add(new ResetFeature(null));
    features.add(new SetNetworkProfileFeature(null));
    features.add(new SetVariablesFeature(null));
  }

  @Override
  public FunctionFeature[] getFeatureList() {
    return features.toArray(new FunctionFeature[0]);
  }

  @Override
  public Confirmation handleRequest(UUID sessionIndex, Request request) {
    if (request instanceof BootNotificationRequest) {
      return eventHandler.handleBootNotificationRequest(
          sessionIndex, (BootNotificationRequest) request);
    } else if (request instanceof HeartbeatRequest) {
      return eventHandler.handleHeartbeatRequest(sessionIndex, (HeartbeatRequest) request);
    } else if (request instanceof NotifyReportRequest) {
      return eventHandler.handleNotifyReportRequest(sessionIndex, (NotifyReportRequest) request);
    }
    return null;
  }

  /**
   * Create a server {@link GetBaseReportRequest} with all required fields.
   *
   * @param requestId The Id of the request.
   * @param reportBase The report base.
   * @return an instance of {@link GetBaseReportRequest}
   */
  public GetBaseReportRequest createGetBaseReportRequest(
      Integer requestId, ReportBaseEnum reportBase) {
    return new GetBaseReportRequest(requestId, reportBase);
  }

  /**
   * Create a server {@link GetReportRequest} with all required fields.
   *
   * @param requestId The Id of the request.
   * @return an instance of {@link GetReportRequest}
   */
  public GetReportRequest createGetReportRequest(Integer requestId) {
    return new GetReportRequest(requestId);
  }

  /**
   * Create a server {@link GetVariablesRequest} with all required fields.
   *
   * @param getVariableData Class to hold parameters for GetVariables request.
   * @return an instance of {@link GetVariablesRequest}
   */
  public GetVariablesRequest createGetVariablesRequest(GetVariableData[] getVariableData) {
    return new GetVariablesRequest(getVariableData);
  }

  /**
   * Create a server {@link ResetRequest} with all required fields.
   *
   * @param type The type of reset that the Charging Station or EVSE should perform.
   * @return an instance of {@link ResetRequest}
   */
  public ResetRequest createResetRequest(ResetEnum type) {
    return new ResetRequest(type);
  }

  /**
   * Create a server {@link SetNetworkProfileRequest} with all required fields.
   *
   * @param configurationSlot Slot in which the configuration should be stored.
   * @param connectionData The NetworkConnectionProfile defines the functional and technical
   *     parameters of a communication link.
   * @return an instance of {@link SetNetworkProfileRequest}
   */
  public SetNetworkProfileRequest createSetNetworkProfileRequest(
      Integer configurationSlot, NetworkConnectionProfile connectionData) {
    return new SetNetworkProfileRequest(configurationSlot, connectionData);
  }

  /**
   * Create a server {@link SetVariablesRequest} with all required fields.
   *
   * @param setVariableData setVariableData
   * @return an instance of {@link SetVariablesRequest}
   */
  public SetVariablesRequest createSetVariablesRequest(SetVariableData[] setVariableData) {
    return new SetVariablesRequest(setVariableData);
  }
}
