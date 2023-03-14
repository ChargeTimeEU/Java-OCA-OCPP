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

/** Class with client request creators and handlers for the Reservation functional block. */
public class ClientReservationFunction implements Function {

  private final ClientReservationEventHandler eventHandler;
  private final ArrayList<FunctionFeature> features;

  public ClientReservationFunction(ClientReservationEventHandler eventHandler) {
    this.eventHandler = eventHandler;
    features = new ArrayList<>();
    features.add(new CancelReservationFeature(this));
    features.add(new ReservationStatusUpdateFeature(null));
    features.add(new ReserveNowFeature(this));
  }

  @Override
  public FunctionFeature[] getFeatureList() {
    return features.toArray(new FunctionFeature[0]);
  }

  @Override
  public Confirmation handleRequest(UUID sessionIndex, Request request) {
    if (request instanceof CancelReservationRequest) {
      return eventHandler.handleCancelReservationRequest((CancelReservationRequest) request);
    } else if (request instanceof ReserveNowRequest) {
      return eventHandler.handleReserveNowRequest((ReserveNowRequest) request);
    }
    return null;
  }

  /**
   * Create a client {@link ReservationStatusUpdateRequest} with all required fields.
   *
   * @param reservationId The ID of the reservation.
   * @param reservationUpdateStatus The updated reservation status.
   * @return an instance of {@link ReservationStatusUpdateRequest}
   */
  public ReservationStatusUpdateRequest createReservationStatusUpdateRequest(
      Integer reservationId, ReservationUpdateStatusEnum reservationUpdateStatus) {
    return new ReservationStatusUpdateRequest(reservationId, reservationUpdateStatus);
  }
}
