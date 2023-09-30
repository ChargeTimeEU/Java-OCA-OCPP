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

/** Class with server request creators and handlers for the DisplayMessage functional block. */
public class ServerDisplayMessageFunction implements Function {

  private final ServerDisplayMessageEventHandler eventHandler;
  private final ArrayList<FunctionFeature> features;

  public ServerDisplayMessageFunction(ServerDisplayMessageEventHandler eventHandler) {
    this.eventHandler = eventHandler;
    features = new ArrayList<>();
    features.add(new ClearDisplayMessageFeature(null));
    features.add(new GetDisplayMessagesFeature(null));
    features.add(new NotifyDisplayMessagesFeature(this));
    features.add(new SetDisplayMessageFeature(null));
  }

  @Override
  public FunctionFeature[] getFeatureList() {
    return features.toArray(new FunctionFeature[0]);
  }

  @Override
  public Confirmation handleRequest(UUID sessionIndex, Request request) {
    if (request instanceof NotifyDisplayMessagesRequest) {
      return eventHandler.handleNotifyDisplayMessagesRequest(
          sessionIndex, (NotifyDisplayMessagesRequest) request);
    }
    return null;
  }

  /**
   * Create a server {@link ClearDisplayMessageRequest} with all required fields.
   *
   * @param id Id of the message that SHALL be removed from the Charging Station.
   * @return an instance of {@link ClearDisplayMessageRequest}
   */
  public ClearDisplayMessageRequest createClearDisplayMessageRequest(Integer id) {
    return new ClearDisplayMessageRequest(id);
  }

  /**
   * Create a server {@link GetDisplayMessagesRequest} with all required fields.
   *
   * @param requestId The Id of this request.
   * @return an instance of {@link GetDisplayMessagesRequest}
   */
  public GetDisplayMessagesRequest createGetDisplayMessagesRequest(Integer requestId) {
    return new GetDisplayMessagesRequest(requestId);
  }

  /**
   * Create a server {@link SetDisplayMessageRequest} with all required fields.
   *
   * @param message Message details, for a message to be displayed on a Charging Station.
   * @return an instance of {@link SetDisplayMessageRequest}
   */
  public SetDisplayMessageRequest createSetDisplayMessageRequest(MessageInfo message) {
    return new SetDisplayMessageRequest(message);
  }
}
