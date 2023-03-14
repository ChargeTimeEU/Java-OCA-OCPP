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

/** Call back handler for client events of the DisplayMessage functional block. */
public interface ClientDisplayMessageEventHandler {
  /**
   * Handle a {@link ClearDisplayMessageRequest} and return a {@link ClearDisplayMessageResponse}.
   *
   * @param request incoming {@link ClearDisplayMessageRequest} to handle.
   * @return outgoing {@link ClearDisplayMessageResponse} to reply with.
   */
  ClearDisplayMessageResponse handleClearDisplayMessageRequest(ClearDisplayMessageRequest request);

  /**
   * Handle a {@link GetDisplayMessagesRequest} and return a {@link GetDisplayMessagesResponse}.
   *
   * @param request incoming {@link GetDisplayMessagesRequest} to handle.
   * @return outgoing {@link GetDisplayMessagesResponse} to reply with.
   */
  GetDisplayMessagesResponse handleGetDisplayMessagesRequest(GetDisplayMessagesRequest request);

  /**
   * Handle a {@link SetDisplayMessageRequest} and return a {@link SetDisplayMessageResponse}.
   *
   * @param request incoming {@link SetDisplayMessageRequest} to handle.
   * @return outgoing {@link SetDisplayMessageResponse} to reply with.
   */
  SetDisplayMessageResponse handleSetDisplayMessageRequest(SetDisplayMessageRequest request);
}
