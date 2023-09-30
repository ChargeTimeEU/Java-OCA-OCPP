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

/** Call back handler for client events of the Provisioning functional block. */
public interface ClientProvisioningEventHandler {
  /**
   * Handle a {@link GetBaseReportRequest} and return a {@link GetBaseReportResponse}.
   *
   * @param request incoming {@link GetBaseReportRequest} to handle.
   * @return outgoing {@link GetBaseReportResponse} to reply with.
   */
  GetBaseReportResponse handleGetBaseReportRequest(GetBaseReportRequest request);

  /**
   * Handle a {@link GetReportRequest} and return a {@link GetReportResponse}.
   *
   * @param request incoming {@link GetReportRequest} to handle.
   * @return outgoing {@link GetReportResponse} to reply with.
   */
  GetReportResponse handleGetReportRequest(GetReportRequest request);

  /**
   * Handle a {@link GetVariablesRequest} and return a {@link GetVariablesResponse}.
   *
   * @param request incoming {@link GetVariablesRequest} to handle.
   * @return outgoing {@link GetVariablesResponse} to reply with.
   */
  GetVariablesResponse handleGetVariablesRequest(GetVariablesRequest request);

  /**
   * Handle a {@link ResetRequest} and return a {@link ResetResponse}.
   *
   * @param request incoming {@link ResetRequest} to handle.
   * @return outgoing {@link ResetResponse} to reply with.
   */
  ResetResponse handleResetRequest(ResetRequest request);

  /**
   * Handle a {@link SetNetworkProfileRequest} and return a {@link SetNetworkProfileResponse}.
   *
   * @param request incoming {@link SetNetworkProfileRequest} to handle.
   * @return outgoing {@link SetNetworkProfileResponse} to reply with.
   */
  SetNetworkProfileResponse handleSetNetworkProfileRequest(SetNetworkProfileRequest request);

  /**
   * Handle a {@link SetVariablesRequest} and return a {@link SetVariablesResponse}.
   *
   * @param request incoming {@link SetVariablesRequest} to handle.
   * @return outgoing {@link SetVariablesResponse} to reply with.
   */
  SetVariablesResponse handleSetVariablesRequest(SetVariablesRequest request);
}
