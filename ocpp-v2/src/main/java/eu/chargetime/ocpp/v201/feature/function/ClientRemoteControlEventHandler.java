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

/** Call back handler for client events of the RemoteControl functional block. */
public interface ClientRemoteControlEventHandler {
  /**
   * Handle a {@link RequestStartTransactionRequest} and return a {@link
   * RequestStartTransactionResponse}.
   *
   * @param request incoming {@link RequestStartTransactionRequest} to handle.
   * @return outgoing {@link RequestStartTransactionResponse} to reply with.
   */
  RequestStartTransactionResponse handleRequestStartTransactionRequest(
      RequestStartTransactionRequest request);

  /**
   * Handle a {@link RequestStopTransactionRequest} and return a {@link
   * RequestStopTransactionResponse}.
   *
   * @param request incoming {@link RequestStopTransactionRequest} to handle.
   * @return outgoing {@link RequestStopTransactionResponse} to reply with.
   */
  RequestStopTransactionResponse handleRequestStopTransactionRequest(
      RequestStopTransactionRequest request);

  /**
   * Handle a {@link TriggerMessageRequest} and return a {@link TriggerMessageResponse}.
   *
   * @param request incoming {@link TriggerMessageRequest} to handle.
   * @return outgoing {@link TriggerMessageResponse} to reply with.
   */
  TriggerMessageResponse handleTriggerMessageRequest(TriggerMessageRequest request);

  /**
   * Handle a {@link UnlockConnectorRequest} and return a {@link UnlockConnectorResponse}.
   *
   * @param request incoming {@link UnlockConnectorRequest} to handle.
   * @return outgoing {@link UnlockConnectorResponse} to reply with.
   */
  UnlockConnectorResponse handleUnlockConnectorRequest(UnlockConnectorRequest request);
}
