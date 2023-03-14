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
import java.util.UUID;

/** Call back handler for server events of the Diagnostics functional block. */
public interface ServerDiagnosticsEventHandler {
  /**
   * Handle a {@link LogStatusNotificationRequest} and return a {@link
   * LogStatusNotificationResponse}.
   *
   * @param sessionIndex identifier of the session on which the request was received.
   * @param request incoming {@link LogStatusNotificationRequest} to handle.
   * @return outgoing {@link LogStatusNotificationResponse} to reply with.
   */
  LogStatusNotificationResponse handleLogStatusNotificationRequest(
      UUID sessionIndex, LogStatusNotificationRequest request);

  /**
   * Handle a {@link NotifyCustomerInformationRequest} and return a {@link
   * NotifyCustomerInformationResponse}.
   *
   * @param sessionIndex identifier of the session on which the request was received.
   * @param request incoming {@link NotifyCustomerInformationRequest} to handle.
   * @return outgoing {@link NotifyCustomerInformationResponse} to reply with.
   */
  NotifyCustomerInformationResponse handleNotifyCustomerInformationRequest(
      UUID sessionIndex, NotifyCustomerInformationRequest request);

  /**
   * Handle a {@link NotifyMonitoringReportRequest} and return a {@link
   * NotifyMonitoringReportResponse}.
   *
   * @param sessionIndex identifier of the session on which the request was received.
   * @param request incoming {@link NotifyMonitoringReportRequest} to handle.
   * @return outgoing {@link NotifyMonitoringReportResponse} to reply with.
   */
  NotifyMonitoringReportResponse handleNotifyMonitoringReportRequest(
      UUID sessionIndex, NotifyMonitoringReportRequest request);
}
