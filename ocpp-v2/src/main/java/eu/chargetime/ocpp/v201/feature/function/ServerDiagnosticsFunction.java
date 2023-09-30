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

/** Class with server request creators and handlers for the Diagnostics functional block. */
public class ServerDiagnosticsFunction implements Function {

  private final ServerDiagnosticsEventHandler eventHandler;
  private final ArrayList<FunctionFeature> features;

  public ServerDiagnosticsFunction(ServerDiagnosticsEventHandler eventHandler) {
    this.eventHandler = eventHandler;
    features = new ArrayList<>();
    features.add(new ClearVariableMonitoringFeature(null));
    features.add(new CustomerInformationFeature(null));
    features.add(new GetLogFeature(null));
    features.add(new GetMonitoringReportFeature(null));
    features.add(new LogStatusNotificationFeature(this));
    features.add(new NotifyCustomerInformationFeature(this));
    features.add(new NotifyMonitoringReportFeature(this));
    features.add(new SetMonitoringBaseFeature(null));
    features.add(new SetMonitoringLevelFeature(null));
    features.add(new SetVariableMonitoringFeature(null));
  }

  @Override
  public FunctionFeature[] getFeatureList() {
    return features.toArray(new FunctionFeature[0]);
  }

  @Override
  public Confirmation handleRequest(UUID sessionIndex, Request request) {
    if (request instanceof LogStatusNotificationRequest) {
      return eventHandler.handleLogStatusNotificationRequest(
          sessionIndex, (LogStatusNotificationRequest) request);
    } else if (request instanceof NotifyCustomerInformationRequest) {
      return eventHandler.handleNotifyCustomerInformationRequest(
          sessionIndex, (NotifyCustomerInformationRequest) request);
    } else if (request instanceof NotifyMonitoringReportRequest) {
      return eventHandler.handleNotifyMonitoringReportRequest(
          sessionIndex, (NotifyMonitoringReportRequest) request);
    }
    return null;
  }

  /**
   * Create a server {@link ClearVariableMonitoringRequest} with all required fields.
   *
   * @param id List of the monitors to be cleared, identified by there Id.
   * @return an instance of {@link ClearVariableMonitoringRequest}
   */
  public ClearVariableMonitoringRequest createClearVariableMonitoringRequest(Integer[] id) {
    return new ClearVariableMonitoringRequest(id);
  }

  /**
   * Create a server {@link CustomerInformationRequest} with all required fields.
   *
   * @param requestId The Id of the request.
   * @param report Flag indicating whether the Charging Station should return
   *     NotifyCustomerInformationRequest messages containing information about the customer
   *     referred to.
   * @param clear Flag indicating whether the Charging Station should clear all information about
   *     the customer referred to.
   * @return an instance of {@link CustomerInformationRequest}
   */
  public CustomerInformationRequest createCustomerInformationRequest(
      Integer requestId, Boolean report, Boolean clear) {
    return new CustomerInformationRequest(requestId, report, clear);
  }

  /**
   * Create a server {@link GetLogRequest} with all required fields.
   *
   * @param log Generic class for the configuration of logging entries.
   * @param logType The type of log file that the Charging Station should send.
   * @param requestId The Id of this request
   * @return an instance of {@link GetLogRequest}
   */
  public GetLogRequest createGetLogRequest(LogParameters log, LogEnum logType, Integer requestId) {
    return new GetLogRequest(log, logType, requestId);
  }

  /**
   * Create a server {@link GetMonitoringReportRequest} with all required fields.
   *
   * @param requestId The Id of the request.
   * @return an instance of {@link GetMonitoringReportRequest}
   */
  public GetMonitoringReportRequest createGetMonitoringReportRequest(Integer requestId) {
    return new GetMonitoringReportRequest(requestId);
  }

  /**
   * Create a server {@link SetMonitoringBaseRequest} with all required fields.
   *
   * @param monitoringBase Specify which monitoring base will be set
   * @return an instance of {@link SetMonitoringBaseRequest}
   */
  public SetMonitoringBaseRequest createSetMonitoringBaseRequest(
      MonitoringBaseEnum monitoringBase) {
    return new SetMonitoringBaseRequest(monitoringBase);
  }

  /**
   * Create a server {@link SetMonitoringLevelRequest} with all required fields.
   *
   * @param severity The Charging Station SHALL only report events with a severity number lower than
   *     or equal to this severity. The severity range is 0-9, with 0 as the highest and 9 as the
   *     lowest severity level.
   * @return an instance of {@link SetMonitoringLevelRequest}
   */
  public SetMonitoringLevelRequest createSetMonitoringLevelRequest(Integer severity) {
    return new SetMonitoringLevelRequest(severity);
  }

  /**
   * Create a server {@link SetVariableMonitoringRequest} with all required fields.
   *
   * @param setMonitoringData Class to hold parameters of SetVariableMonitoring request.
   * @return an instance of {@link SetVariableMonitoringRequest}
   */
  public SetVariableMonitoringRequest createSetVariableMonitoringRequest(
      SetMonitoringData[] setMonitoringData) {
    return new SetVariableMonitoringRequest(setMonitoringData);
  }
}
