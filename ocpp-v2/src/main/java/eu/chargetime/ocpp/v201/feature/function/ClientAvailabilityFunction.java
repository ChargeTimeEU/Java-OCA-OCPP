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

/** Class with client request creators and handlers for the Availability functional block. */
public class ClientAvailabilityFunction implements Function {

  private final ClientAvailabilityEventHandler eventHandler;
  private final ArrayList<FunctionFeature> features;

  public ClientAvailabilityFunction(ClientAvailabilityEventHandler eventHandler) {
    this.eventHandler = eventHandler;
    features = new ArrayList<>();
    features.add(new ChangeAvailabilityFeature(this));
    features.add(new NotifyEventFeature(null));
    features.add(new StatusNotificationFeature(null));
  }

  @Override
  public FunctionFeature[] getFeatureList() {
    return features.toArray(new FunctionFeature[0]);
  }

  @Override
  public Confirmation handleRequest(UUID sessionIndex, Request request) {
    if (request instanceof ChangeAvailabilityRequest) {
      return eventHandler.handleChangeAvailabilityRequest((ChangeAvailabilityRequest) request);
    }
    return null;
  }

  /**
   * Create a client {@link NotifyEventRequest} with all required fields.
   *
   * @param generatedAt Timestamp of the moment this message was generated at the Charging Station.
   * @param seqNo Sequence number of this message. First message starts at 0.
   * @param eventData Class to report an event notification for a component-variable.
   * @return an instance of {@link NotifyEventRequest}
   */
  public NotifyEventRequest createNotifyEventRequest(
      ZonedDateTime generatedAt, Integer seqNo, EventData[] eventData) {
    return new NotifyEventRequest(generatedAt, seqNo, eventData);
  }

  /**
   * Create a client {@link StatusNotificationRequest} with all required fields.
   *
   * @param timestamp The time for which the status is reported. If absent time of receipt of the
   *     message will be assumed.
   * @param connectorStatus The current status of the Connector.
   * @param evseId The id of the EVSE to which the connector belongs for which the the status is
   *     reported.
   * @param connectorId The id of the connector within the EVSE for which the status is reported.
   * @return an instance of {@link StatusNotificationRequest}
   */
  public StatusNotificationRequest createStatusNotificationRequest(
      ZonedDateTime timestamp,
      ConnectorStatusEnum connectorStatus,
      Integer evseId,
      Integer connectorId) {
    return new StatusNotificationRequest(timestamp, connectorStatus, evseId, connectorId);
  }
}
