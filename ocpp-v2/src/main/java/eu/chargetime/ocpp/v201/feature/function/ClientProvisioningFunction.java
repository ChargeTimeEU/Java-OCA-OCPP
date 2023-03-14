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
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.UUID;

/** Class with client request creators and handlers for the Provisioning functional block. */
public class ClientProvisioningFunction implements Function {

  private final ClientProvisioningEventHandler eventHandler;
  private final ArrayList<FunctionFeature> features;

  public ClientProvisioningFunction(ClientProvisioningEventHandler eventHandler) {
    this.eventHandler = eventHandler;
    features = new ArrayList<>();
    features.add(new BootNotificationFeature(null));
    features.add(new GetBaseReportFeature(this));
    features.add(new GetReportFeature(this));
    features.add(new GetVariablesFeature(this));
    features.add(new HeartbeatFeature(null));
    features.add(new NotifyReportFeature(null));
    features.add(new ResetFeature(this));
    features.add(new SetNetworkProfileFeature(this));
    features.add(new SetVariablesFeature(this));
  }

  @Override
  public FunctionFeature[] getFeatureList() {
    return features.toArray(new FunctionFeature[0]);
  }

  @Override
  public Confirmation handleRequest(UUID sessionIndex, Request request) {
    if (request instanceof GetBaseReportRequest) {
      return eventHandler.handleGetBaseReportRequest((GetBaseReportRequest) request);
    } else if (request instanceof GetReportRequest) {
      return eventHandler.handleGetReportRequest((GetReportRequest) request);
    } else if (request instanceof GetVariablesRequest) {
      return eventHandler.handleGetVariablesRequest((GetVariablesRequest) request);
    } else if (request instanceof ResetRequest) {
      return eventHandler.handleResetRequest((ResetRequest) request);
    } else if (request instanceof SetNetworkProfileRequest) {
      return eventHandler.handleSetNetworkProfileRequest((SetNetworkProfileRequest) request);
    } else if (request instanceof SetVariablesRequest) {
      return eventHandler.handleSetVariablesRequest((SetVariablesRequest) request);
    }
    return null;
  }

  /**
   * Create a client {@link BootNotificationRequest} with all required fields.
   *
   * @param chargingStation The physical system where an Electrical Vehicle (EV) can be charged.
   * @param reason The reason for sending this message to the CSMS.
   * @return an instance of {@link BootNotificationRequest}
   */
  public BootNotificationRequest createBootNotificationRequest(
      ChargingStation chargingStation, BootReasonEnum reason) {
    return new BootNotificationRequest(chargingStation, reason);
  }

  /**
   * Create a client {@link HeartbeatRequest}.
   *
   * @return an instance of {@link HeartbeatRequest}
   */
  public HeartbeatRequest createHeartbeatRequest() {
    return new HeartbeatRequest();
  }

  /**
   * Create a client {@link NotifyReportRequest} with all required fields.
   *
   * @param requestId The id of the GetReportRequest or GetBaseReportRequest that requested this
   *     report
   * @param generatedAt Timestamp of the moment this message was generated at the Charging Station.
   * @param seqNo Sequence number of this message. First message starts at 0.
   * @return an instance of {@link NotifyReportRequest}
   */
  public NotifyReportRequest createNotifyReportRequest(
      Integer requestId, ZonedDateTime generatedAt, Integer seqNo) {
    return new NotifyReportRequest(requestId, generatedAt, seqNo);
  }
}
