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

/** Class with client request creators and handlers for the DisplayMessage functional block. */
public class ClientDisplayMessageFunction implements Function {

  private final ClientDisplayMessageEventHandler eventHandler;
  private final ArrayList<FunctionFeature> features;

  public ClientDisplayMessageFunction(ClientDisplayMessageEventHandler eventHandler) {
    this.eventHandler = eventHandler;
    features = new ArrayList<>();
    features.add(new ClearDisplayMessageFeature(this));
    features.add(new GetDisplayMessagesFeature(this));
    features.add(new NotifyDisplayMessagesFeature(null));
    features.add(new SetDisplayMessageFeature(this));
  }

  @Override
  public FunctionFeature[] getFeatureList() {
    return features.toArray(new FunctionFeature[0]);
  }

  @Override
  public Confirmation handleRequest(UUID sessionIndex, Request request) {
    if (request instanceof ClearDisplayMessageRequest) {
      return eventHandler.handleClearDisplayMessageRequest((ClearDisplayMessageRequest) request);
    } else if (request instanceof GetDisplayMessagesRequest) {
      return eventHandler.handleGetDisplayMessagesRequest((GetDisplayMessagesRequest) request);
    } else if (request instanceof SetDisplayMessageRequest) {
      return eventHandler.handleSetDisplayMessageRequest((SetDisplayMessageRequest) request);
    }
    return null;
  }

  /**
   * Create a client {@link NotifyDisplayMessagesRequest} with all required fields.
   *
   * @param requestId The id of the GetDisplayMessagesRequest that requested this message.
   * @return an instance of {@link NotifyDisplayMessagesRequest}
   */
  public NotifyDisplayMessagesRequest createNotifyDisplayMessagesRequest(Integer requestId) {
    return new NotifyDisplayMessagesRequest(requestId);
  }
}
