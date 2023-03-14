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

/** Class with server request creators and handlers for the RemoteControl functional block. */
public class ServerRemoteControlFunction implements Function {

  private final ArrayList<FunctionFeature> features;

  public ServerRemoteControlFunction() {
    features = new ArrayList<>();
    features.add(new RequestStartTransactionFeature(null));
    features.add(new RequestStopTransactionFeature(null));
    features.add(new TriggerMessageFeature(null));
    features.add(new UnlockConnectorFeature(null));
  }

  @Override
  public FunctionFeature[] getFeatureList() {
    return features.toArray(new FunctionFeature[0]);
  }

  @Override
  public Confirmation handleRequest(UUID sessionIndex, Request request) {
    return null;
  }

  /**
   * Create a server {@link RequestStartTransactionRequest} with all required fields.
   *
   * @param idToken A case insensitive identifier to use for the authorization and the type of
   *     authorization to support multiple forms of identifiers.
   * @param remoteStartId Id given by the server to this start request. The Charging Station might
   *     return this in the TransactionEventRequest, letting the server know which transaction was
   *     started for this request. Use to start a transaction.
   * @return an instance of {@link RequestStartTransactionRequest}
   */
  public RequestStartTransactionRequest createRequestStartTransactionRequest(
      IdToken idToken, Integer remoteStartId) {
    return new RequestStartTransactionRequest(idToken, remoteStartId);
  }

  /**
   * Create a server {@link RequestStopTransactionRequest} with all required fields.
   *
   * @param transactionId The identifier of the transaction which the Charging Station is requested
   *     to stop.
   * @return an instance of {@link RequestStopTransactionRequest}
   */
  public RequestStopTransactionRequest createRequestStopTransactionRequest(String transactionId) {
    return new RequestStopTransactionRequest(transactionId);
  }

  /**
   * Create a server {@link TriggerMessageRequest} with all required fields.
   *
   * @param requestedMessage Type of message to be triggered.
   * @return an instance of {@link TriggerMessageRequest}
   */
  public TriggerMessageRequest createTriggerMessageRequest(MessageTriggerEnum requestedMessage) {
    return new TriggerMessageRequest(requestedMessage);
  }

  /**
   * Create a server {@link UnlockConnectorRequest} with all required fields.
   *
   * @param evseId The identifier of the EVSE for which a connector needs to be unlocked.
   * @param connectorId The identifier of the connector that needs to be unlocked.
   * @return an instance of {@link UnlockConnectorRequest}
   */
  public UnlockConnectorRequest createUnlockConnectorRequest(Integer evseId, Integer connectorId) {
    return new UnlockConnectorRequest(evseId, connectorId);
  }
}
