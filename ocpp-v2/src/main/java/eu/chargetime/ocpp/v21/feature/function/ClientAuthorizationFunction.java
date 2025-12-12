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

/** Class with client request creators and handlers for the Authorization functional block. */
public class ClientAuthorizationFunction implements Function {

  private final ClientAuthorizationEventHandler eventHandler;
  private final ArrayList<FunctionFeature> features;

  public ClientAuthorizationFunction(ClientAuthorizationEventHandler eventHandler) {
    this.eventHandler = eventHandler;
    features = new ArrayList<>();
    features.add(new AuthorizeFeature(null));
    features.add(new ClearCacheFeature(this));
    features.add(new NotifySettlementFeature(null));
    features.add(new NotifyWebPaymentStartedFeature(this));
    features.add(new VatNumberValidationFeature(null));
  }

  @Override
  public FunctionFeature[] getFeatureList() {
    return features.toArray(new FunctionFeature[0]);
  }

  @Override
  public Confirmation handleRequest(UUID sessionIndex, Request request) {
    if (request instanceof ClearCacheRequest) {
      return eventHandler.handleClearCacheRequest((ClearCacheRequest) request);
    } else if (request instanceof NotifyWebPaymentStartedRequest) {
      return eventHandler.handleNotifyWebPaymentStartedRequest(
          (NotifyWebPaymentStartedRequest) request);
    }
    return null;
  }

  /**
   * Create a client {@link AuthorizeRequest} with all required fields.
   *
   * @param idToken A case insensitive identifier to use for the authorization and the type of
   *     authorization to support multiple forms of identifiers.
   * @return an instance of {@link AuthorizeRequest}
   */
  public AuthorizeRequest createAuthorizeRequest(IdToken idToken) {
    return new AuthorizeRequest(idToken);
  }

  /**
   * Create a client {@link NotifySettlementRequest} with all required fields.
   *
   * @param pspRef The payment reference received from the payment terminal and is used as the value
   *     for idToken.
   * @param status The status of the settlement attempt.
   * @param settlementAmount The amount that was settled, or attempted to be settled (in case of
   *     failure).
   * @param settlementTime The time when the settlement was done.
   * @return an instance of {@link NotifySettlementRequest}
   */
  public NotifySettlementRequest createNotifySettlementRequest(
      String pspRef,
      PaymentStatusEnum status,
      Double settlementAmount,
      ZonedDateTime settlementTime) {
    return new NotifySettlementRequest(pspRef, status, settlementAmount, settlementTime);
  }

  /**
   * Create a client {@link VatNumberValidationRequest} with all required fields.
   *
   * @param vatNumber VAT number to check.
   * @return an instance of {@link VatNumberValidationRequest}
   */
  public VatNumberValidationRequest createVatNumberValidationRequest(String vatNumber) {
    return new VatNumberValidationRequest(vatNumber);
  }
}
