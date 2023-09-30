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

/** Call back handler for server events of the Provisioning functional block. */
public interface ServerProvisioningEventHandler {
  /**
   * Handle a {@link BootNotificationRequest} and return a {@link BootNotificationResponse}.
   *
   * @param sessionIndex identifier of the session on which the request was received.
   * @param request incoming {@link BootNotificationRequest} to handle.
   * @return outgoing {@link BootNotificationResponse} to reply with.
   */
  BootNotificationResponse handleBootNotificationRequest(
      UUID sessionIndex, BootNotificationRequest request);

  /**
   * Handle a {@link HeartbeatRequest} and return a {@link HeartbeatResponse}.
   *
   * @param sessionIndex identifier of the session on which the request was received.
   * @param request incoming {@link HeartbeatRequest} to handle.
   * @return outgoing {@link HeartbeatResponse} to reply with.
   */
  HeartbeatResponse handleHeartbeatRequest(UUID sessionIndex, HeartbeatRequest request);

  /**
   * Handle a {@link NotifyReportRequest} and return a {@link NotifyReportResponse}.
   *
   * @param sessionIndex identifier of the session on which the request was received.
   * @param request incoming {@link NotifyReportRequest} to handle.
   * @return outgoing {@link NotifyReportResponse} to reply with.
   */
  NotifyReportResponse handleNotifyReportRequest(UUID sessionIndex, NotifyReportRequest request);
}
