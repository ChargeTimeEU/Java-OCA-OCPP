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

package eu.chargetime.ocpp.v21.feature.function;

import eu.chargetime.ocpp.v21.model.messages.*;

/** Call back handler for client events of the DERControl functional block. */
public interface ClientDERControlEventHandler {
  /**
   * Handle a {@link ClearDERControlRequest} and return a {@link ClearDERControlResponse}.
   *
   * @param request incoming {@link ClearDERControlRequest} to handle.
   * @return outgoing {@link ClearDERControlResponse} to reply with.
   */
  ClearDERControlResponse handleClearDERControlRequest(ClearDERControlRequest request);

  /**
   * Handle a {@link GetDERControlRequest} and return a {@link GetDERControlResponse}.
   *
   * @param request incoming {@link GetDERControlRequest} to handle.
   * @return outgoing {@link GetDERControlResponse} to reply with.
   */
  GetDERControlResponse handleGetDERControlRequest(GetDERControlRequest request);

  /**
   * Handle a {@link ReportDERControlRequest} and return a {@link ReportDERControlResponse}.
   *
   * @param request incoming {@link ReportDERControlRequest} to handle.
   * @return outgoing {@link ReportDERControlResponse} to reply with.
   */
  ReportDERControlResponse handleReportDERControlRequest(ReportDERControlRequest request);

  /**
   * Handle a {@link SetDERControlRequest} and return a {@link SetDERControlResponse}.
   *
   * @param request incoming {@link SetDERControlRequest} to handle.
   * @return outgoing {@link SetDERControlResponse} to reply with.
   */
  SetDERControlResponse handleSetDERControlRequest(SetDERControlRequest request);
}
