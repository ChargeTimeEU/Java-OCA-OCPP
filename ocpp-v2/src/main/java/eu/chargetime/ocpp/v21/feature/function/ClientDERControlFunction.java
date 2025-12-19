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

/** Class with client request creators and handlers for the DERControl functional block. */
public class ClientDERControlFunction implements Function {

  private final ClientDERControlEventHandler eventHandler;
  private final ArrayList<FunctionFeature> features;

  public ClientDERControlFunction(ClientDERControlEventHandler eventHandler) {
    this.eventHandler = eventHandler;
    features = new ArrayList<>();
    features.add(new ClearDERControlFeature(this));
    features.add(new GetDERControlFeature(this));
    features.add(new NotifyDERAlarmFeature(null));
    features.add(new NotifyDERStartStopFeature(null));
    features.add(new ReportDERControlFeature(this));
    features.add(new SetDERControlFeature(this));
  }

  @Override
  public FunctionFeature[] getFeatureList() {
    return features.toArray(new FunctionFeature[0]);
  }

  @Override
  public Confirmation handleRequest(UUID sessionIndex, Request request) {
    if (request instanceof ClearDERControlRequest) {
      return eventHandler.handleClearDERControlRequest((ClearDERControlRequest) request);
    } else if (request instanceof GetDERControlRequest) {
      return eventHandler.handleGetDERControlRequest((GetDERControlRequest) request);
    } else if (request instanceof ReportDERControlRequest) {
      return eventHandler.handleReportDERControlRequest((ReportDERControlRequest) request);
    } else if (request instanceof SetDERControlRequest) {
      return eventHandler.handleSetDERControlRequest((SetDERControlRequest) request);
    }
    return null;
  }

  /**
   * Create a client {@link NotifyDERAlarmRequest} with all required fields.
   *
   * @param controlType Name of DER control, e.g. LFMustTrip
   * @param timestamp Time of start or end of alarm.
   * @return an instance of {@link NotifyDERAlarmRequest}
   */
  public NotifyDERAlarmRequest createNotifyDERAlarmRequest(
      DERControlEnum controlType, ZonedDateTime timestamp) {
    return new NotifyDERAlarmRequest(controlType, timestamp);
  }

  /**
   * Create a client {@link NotifyDERStartStopRequest} with all required fields.
   *
   * @param controlId Id of the started or stopped DER control. Corresponds to the controlId of the
   *     SetDERControlRequest.
   * @param started True if DER control has started. False if it has ended.
   * @param timestamp Time of start or end of event.
   * @return an instance of {@link NotifyDERStartStopRequest}
   */
  public NotifyDERStartStopRequest createNotifyDERStartStopRequest(
      String controlId, Boolean started, ZonedDateTime timestamp) {
    return new NotifyDERStartStopRequest(controlId, started, timestamp);
  }
}
