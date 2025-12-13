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

import eu.chargetime.ocpp.feature.FunctionFeature;
import eu.chargetime.ocpp.feature.function.Function;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.v21.feature.*;
import eu.chargetime.ocpp.v21.model.messages.*;
import eu.chargetime.ocpp.v21.model.types.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.UUID;

/** Class with client request creators and handlers for the Transactions functional block. */
public class ClientTransactionsFunction implements Function {

  private final ClientTransactionsEventHandler eventHandler;
  private final ArrayList<FunctionFeature> features;

  public ClientTransactionsFunction(ClientTransactionsEventHandler eventHandler) {
    this.eventHandler = eventHandler;
    features = new ArrayList<>();
    features.add(new BatterySwapFeature(null));
    features.add(new GetTransactionStatusFeature(this));
    features.add(new RequestBatterySwapFeature(null));
    features.add(new TransactionEventFeature(null));
  }

  @Override
  public FunctionFeature[] getFeatureList() {
    return features.toArray(new FunctionFeature[0]);
  }

  @Override
  public Confirmation handleRequest(UUID sessionIndex, Request request) {
    if (request instanceof GetTransactionStatusRequest) {
      return eventHandler.handleGetTransactionStatusRequest((GetTransactionStatusRequest) request);
    }
    return null;
  }

  /**
   * Create a client {@link BatterySwapRequest} with all required fields.
   *
   * @param batteryData batteryData
   * @param eventType Battery in/out
   * @param idToken A case insensitive identifier to use for the authorization and the type of
   *     authorization to support multiple forms of identifiers.
   * @param requestId RequestId to correlate BatteryIn/Out events and optional
   *     RequestBatterySwapRequest.
   * @return an instance of {@link BatterySwapRequest}
   */
  public BatterySwapRequest createBatterySwapRequest(
      BatteryData[] batteryData,
      BatterySwapEventEnum eventType,
      IdToken idToken,
      Integer requestId) {
    return new BatterySwapRequest(batteryData, eventType, idToken, requestId);
  }

  /**
   * Create a client {@link RequestBatterySwapRequest} with all required fields.
   *
   * @param idToken A case insensitive identifier to use for the authorization and the type of
   *     authorization to support multiple forms of identifiers.
   * @param requestId Request id to match with BatterySwapRequest.
   * @return an instance of {@link RequestBatterySwapRequest}
   */
  public RequestBatterySwapRequest createRequestBatterySwapRequest(
      IdToken idToken, Integer requestId) {
    return new RequestBatterySwapRequest(idToken, requestId);
  }

  /**
   * Create a client {@link TransactionEventRequest} with all required fields.
   *
   * @param eventType The type of this event. The first TransactionEvent of a transaction SHALL
   *     contain: "Started" The last TransactionEvent of a transaction SHALL contain: "Ended" All
   *     others SHALL contain: "Updated"
   * @param timestamp The date and time at which this transaction event occurred.
   * @param triggerReason Reason the Charging Station sends this message to the CSMS
   * @param seqNo Incremental sequence number, helps with determining if all messages of a
   *     transaction have been received.
   * @param transactionInfo transactionInfo
   * @return an instance of {@link TransactionEventRequest}
   */
  public TransactionEventRequest createTransactionEventRequest(
      TransactionEventEnum eventType,
      ZonedDateTime timestamp,
      TriggerReasonEnum triggerReason,
      Integer seqNo,
      Transaction transactionInfo) {
    return new TransactionEventRequest(eventType, timestamp, triggerReason, seqNo, transactionInfo);
  }
}
