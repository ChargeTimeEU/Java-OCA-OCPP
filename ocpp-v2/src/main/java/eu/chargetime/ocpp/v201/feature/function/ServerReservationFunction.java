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
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.UUID;

/** Class with server request creators and handlers for the Reservation functional block. */
public class ServerReservationFunction implements Function {

  private final ServerReservationEventHandler eventHandler;
  private final ArrayList<FunctionFeature> features;

  public ServerReservationFunction(ServerReservationEventHandler eventHandler) {
    this.eventHandler = eventHandler;
    features = new ArrayList<>();
    features.add(new CancelReservationFeature(null));
    features.add(new ReservationStatusUpdateFeature(this));
    features.add(new ReserveNowFeature(null));
  }

  @Override
  public FunctionFeature[] getFeatureList() {
    return features.toArray(new FunctionFeature[0]);
  }

  @Override
  public Confirmation handleRequest(UUID sessionIndex, Request request) {
    if (request instanceof ReservationStatusUpdateRequest) {
      return eventHandler.handleReservationStatusUpdateRequest(
          sessionIndex, (ReservationStatusUpdateRequest) request);
    }
    return null;
  }

  /**
   * Create a server {@link CancelReservationRequest} with all required fields.
   *
   * @param reservationId Id of the reservation to cancel.
   * @return an instance of {@link CancelReservationRequest}
   */
  public CancelReservationRequest createCancelReservationRequest(Integer reservationId) {
    return new CancelReservationRequest(reservationId);
  }

  /**
   * Create a server {@link ReserveNowRequest} with all required fields.
   *
   * @param id Id of reservation.
   * @param expiryDateTime Date and time at which the reservation expires.
   * @param idToken A case insensitive identifier to use for the authorization and the type of
   *     authorization to support multiple forms of identifiers.
   * @return an instance of {@link ReserveNowRequest}
   */
  public ReserveNowRequest createReserveNowRequest(
      Integer id, ZonedDateTime expiryDateTime, IdToken idToken) {
    return new ReserveNowRequest(id, expiryDateTime, idToken);
  }
}
