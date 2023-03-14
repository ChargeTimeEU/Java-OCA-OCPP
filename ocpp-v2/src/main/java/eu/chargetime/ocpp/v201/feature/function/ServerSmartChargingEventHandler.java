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

/** Call back handler for server events of the SmartCharging functional block. */
public interface ServerSmartChargingEventHandler {
  /**
   * Handle a {@link ClearedChargingLimitRequest} and return a {@link ClearedChargingLimitResponse}.
   *
   * @param sessionIndex identifier of the session on which the request was received.
   * @param request incoming {@link ClearedChargingLimitRequest} to handle.
   * @return outgoing {@link ClearedChargingLimitResponse} to reply with.
   */
  ClearedChargingLimitResponse handleClearedChargingLimitRequest(
      UUID sessionIndex, ClearedChargingLimitRequest request);

  /**
   * Handle a {@link NotifyChargingLimitRequest} and return a {@link NotifyChargingLimitResponse}.
   *
   * @param sessionIndex identifier of the session on which the request was received.
   * @param request incoming {@link NotifyChargingLimitRequest} to handle.
   * @return outgoing {@link NotifyChargingLimitResponse} to reply with.
   */
  NotifyChargingLimitResponse handleNotifyChargingLimitRequest(
      UUID sessionIndex, NotifyChargingLimitRequest request);

  /**
   * Handle a {@link NotifyEVChargingNeedsRequest} and return a {@link
   * NotifyEVChargingNeedsResponse}.
   *
   * @param sessionIndex identifier of the session on which the request was received.
   * @param request incoming {@link NotifyEVChargingNeedsRequest} to handle.
   * @return outgoing {@link NotifyEVChargingNeedsResponse} to reply with.
   */
  NotifyEVChargingNeedsResponse handleNotifyEVChargingNeedsRequest(
      UUID sessionIndex, NotifyEVChargingNeedsRequest request);

  /**
   * Handle a {@link NotifyEVChargingScheduleRequest} and return a {@link
   * NotifyEVChargingScheduleResponse}.
   *
   * @param sessionIndex identifier of the session on which the request was received.
   * @param request incoming {@link NotifyEVChargingScheduleRequest} to handle.
   * @return outgoing {@link NotifyEVChargingScheduleResponse} to reply with.
   */
  NotifyEVChargingScheduleResponse handleNotifyEVChargingScheduleRequest(
      UUID sessionIndex, NotifyEVChargingScheduleRequest request);

  /**
   * Handle a {@link ReportChargingProfilesRequest} and return a {@link
   * ReportChargingProfilesResponse}.
   *
   * @param sessionIndex identifier of the session on which the request was received.
   * @param request incoming {@link ReportChargingProfilesRequest} to handle.
   * @return outgoing {@link ReportChargingProfilesResponse} to reply with.
   */
  ReportChargingProfilesResponse handleReportChargingProfilesRequest(
      UUID sessionIndex, ReportChargingProfilesRequest request);
}
