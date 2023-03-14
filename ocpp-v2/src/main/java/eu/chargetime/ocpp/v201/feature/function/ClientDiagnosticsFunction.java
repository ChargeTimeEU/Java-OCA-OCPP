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

/** Class with client request creators and handlers for the Diagnostics functional block. */
public class ClientDiagnosticsFunction implements Function {

  private final ClientDiagnosticsEventHandler eventHandler;
  private final ArrayList<FunctionFeature> features;

  public ClientDiagnosticsFunction(ClientDiagnosticsEventHandler eventHandler) {
    this.eventHandler = eventHandler;
    features = new ArrayList<>();
    features.add(new ClearVariableMonitoringFeature(this));
    features.add(new CustomerInformationFeature(this));
    features.add(new GetLogFeature(this));
    features.add(new GetMonitoringReportFeature(this));
    features.add(new LogStatusNotificationFeature(null));
    features.add(new NotifyCustomerInformationFeature(null));
    features.add(new NotifyMonitoringReportFeature(null));
    features.add(new SetMonitoringBaseFeature(this));
    features.add(new SetMonitoringLevelFeature(this));
    features.add(new SetVariableMonitoringFeature(this));
  }

  @Override
  public FunctionFeature[] getFeatureList() {
    return features.toArray(new FunctionFeature[0]);
  }

  @Override
  public Confirmation handleRequest(UUID sessionIndex, Request request) {
    if (request instanceof ClearVariableMonitoringRequest) {
      return eventHandler.handleClearVariableMonitoringRequest(
          (ClearVariableMonitoringRequest) request);
    } else if (request instanceof CustomerInformationRequest) {
      return eventHandler.handleCustomerInformationRequest((CustomerInformationRequest) request);
    } else if (request instanceof GetLogRequest) {
      return eventHandler.handleGetLogRequest((GetLogRequest) request);
    } else if (request instanceof GetMonitoringReportRequest) {
      return eventHandler.handleGetMonitoringReportRequest((GetMonitoringReportRequest) request);
    } else if (request instanceof SetMonitoringBaseRequest) {
      return eventHandler.handleSetMonitoringBaseRequest((SetMonitoringBaseRequest) request);
    } else if (request instanceof SetMonitoringLevelRequest) {
      return eventHandler.handleSetMonitoringLevelRequest((SetMonitoringLevelRequest) request);
    } else if (request instanceof SetVariableMonitoringRequest) {
      return eventHandler.handleSetVariableMonitoringRequest(
          (SetVariableMonitoringRequest) request);
    }
    return null;
  }

  /**
   * Create a client {@link LogStatusNotificationRequest} with all required fields.
   *
   * @param status The status of the log upload.
   * @return an instance of {@link LogStatusNotificationRequest}
   */
  public LogStatusNotificationRequest createLogStatusNotificationRequest(
      UploadLogStatusEnum status) {
    return new LogStatusNotificationRequest(status);
  }

  /**
   * Create a client {@link NotifyCustomerInformationRequest} with all required fields.
   *
   * @param data (Part of) the requested data. No format specified in which the data is returned.
   *     Should be human readable.
   * @param seqNo Sequence number of this message. First message starts at 0.
   * @param generatedAt Timestamp of the moment this message was generated at the Charging Station.
   * @param requestId The Id of the request.
   * @return an instance of {@link NotifyCustomerInformationRequest}
   */
  public NotifyCustomerInformationRequest createNotifyCustomerInformationRequest(
      String data, Integer seqNo, ZonedDateTime generatedAt, Integer requestId) {
    return new NotifyCustomerInformationRequest(data, seqNo, generatedAt, requestId);
  }

  /**
   * Create a client {@link NotifyMonitoringReportRequest} with all required fields.
   *
   * @param requestId The id of the GetMonitoringRequest that requested this report.
   * @param seqNo Sequence number of this message. First message starts at 0.
   * @param generatedAt Timestamp of the moment this message was generated at the Charging Station.
   * @return an instance of {@link NotifyMonitoringReportRequest}
   */
  public NotifyMonitoringReportRequest createNotifyMonitoringReportRequest(
      Integer requestId, Integer seqNo, ZonedDateTime generatedAt) {
    return new NotifyMonitoringReportRequest(requestId, seqNo, generatedAt);
  }
}
