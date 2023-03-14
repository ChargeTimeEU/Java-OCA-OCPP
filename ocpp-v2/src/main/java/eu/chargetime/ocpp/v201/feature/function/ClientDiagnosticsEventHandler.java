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

import eu.chargetime.ocpp.v201.model.messages.*;

/** Call back handler for client events of the Diagnostics functional block. */
public interface ClientDiagnosticsEventHandler {
  /**
   * Handle a {@link ClearVariableMonitoringRequest} and return a {@link
   * ClearVariableMonitoringResponse}.
   *
   * @param request incoming {@link ClearVariableMonitoringRequest} to handle.
   * @return outgoing {@link ClearVariableMonitoringResponse} to reply with.
   */
  ClearVariableMonitoringResponse handleClearVariableMonitoringRequest(
      ClearVariableMonitoringRequest request);

  /**
   * Handle a {@link CustomerInformationRequest} and return a {@link CustomerInformationResponse}.
   *
   * @param request incoming {@link CustomerInformationRequest} to handle.
   * @return outgoing {@link CustomerInformationResponse} to reply with.
   */
  CustomerInformationResponse handleCustomerInformationRequest(CustomerInformationRequest request);

  /**
   * Handle a {@link GetLogRequest} and return a {@link GetLogResponse}.
   *
   * @param request incoming {@link GetLogRequest} to handle.
   * @return outgoing {@link GetLogResponse} to reply with.
   */
  GetLogResponse handleGetLogRequest(GetLogRequest request);

  /**
   * Handle a {@link GetMonitoringReportRequest} and return a {@link GetMonitoringReportResponse}.
   *
   * @param request incoming {@link GetMonitoringReportRequest} to handle.
   * @return outgoing {@link GetMonitoringReportResponse} to reply with.
   */
  GetMonitoringReportResponse handleGetMonitoringReportRequest(GetMonitoringReportRequest request);

  /**
   * Handle a {@link SetMonitoringBaseRequest} and return a {@link SetMonitoringBaseResponse}.
   *
   * @param request incoming {@link SetMonitoringBaseRequest} to handle.
   * @return outgoing {@link SetMonitoringBaseResponse} to reply with.
   */
  SetMonitoringBaseResponse handleSetMonitoringBaseRequest(SetMonitoringBaseRequest request);

  /**
   * Handle a {@link SetMonitoringLevelRequest} and return a {@link SetMonitoringLevelResponse}.
   *
   * @param request incoming {@link SetMonitoringLevelRequest} to handle.
   * @return outgoing {@link SetMonitoringLevelResponse} to reply with.
   */
  SetMonitoringLevelResponse handleSetMonitoringLevelRequest(SetMonitoringLevelRequest request);

  /**
   * Handle a {@link SetVariableMonitoringRequest} and return a {@link
   * SetVariableMonitoringResponse}.
   *
   * @param request incoming {@link SetVariableMonitoringRequest} to handle.
   * @return outgoing {@link SetVariableMonitoringResponse} to reply with.
   */
  SetVariableMonitoringResponse handleSetVariableMonitoringRequest(
      SetVariableMonitoringRequest request);
}
