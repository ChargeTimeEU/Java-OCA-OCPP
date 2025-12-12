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
import java.util.ArrayList;
import java.util.UUID;

/** Class with server request creators and handlers for the DERControl functional block. */
public class ServerDERControlFunction implements Function {

  private final ServerDERControlEventHandler eventHandler;
  private final ArrayList<FunctionFeature> features;

  public ServerDERControlFunction(ServerDERControlEventHandler eventHandler) {
    this.eventHandler = eventHandler;
    features = new ArrayList<>();
    features.add(new ClearDERControlFeature(null));
    features.add(new GetDERControlFeature(null));
    features.add(new NotifyDERAlarmFeature(this));
    features.add(new NotifyDERStartStopFeature(this));
    features.add(new ReportDERControlFeature(null));
    features.add(new SetDERControlFeature(null));
  }

  @Override
  public FunctionFeature[] getFeatureList() {
    return features.toArray(new FunctionFeature[0]);
  }

  @Override
  public Confirmation handleRequest(UUID sessionIndex, Request request) {
    if (request instanceof NotifyDERAlarmRequest) {
      return eventHandler.handleNotifyDERAlarmRequest(
          sessionIndex, (NotifyDERAlarmRequest) request);
    } else if (request instanceof NotifyDERStartStopRequest) {
      return eventHandler.handleNotifyDERStartStopRequest(
          sessionIndex, (NotifyDERStartStopRequest) request);
    }
    return null;
  }

  /**
   * Create a server {@link ClearDERControlRequest} with all required fields.
   *
   * @param isDefault True: clearing default DER controls. False: clearing scheduled controls.
   * @return an instance of {@link ClearDERControlRequest}
   */
  public ClearDERControlRequest createClearDERControlRequest(Boolean isDefault) {
    return new ClearDERControlRequest(isDefault);
  }

  /**
   * Create a server {@link GetDERControlRequest} with all required fields.
   *
   * @param requestId RequestId to be used in ReportDERControlRequest.
   * @return an instance of {@link GetDERControlRequest}
   */
  public GetDERControlRequest createGetDERControlRequest(Integer requestId) {
    return new GetDERControlRequest(requestId);
  }

  /**
   * Create a server {@link ReportDERControlRequest} with all required fields.
   *
   * @param requestId RequestId from GetDERControlRequest.
   * @return an instance of {@link ReportDERControlRequest}
   */
  public ReportDERControlRequest createReportDERControlRequest(Integer requestId) {
    return new ReportDERControlRequest(requestId);
  }

  /**
   * Create a server {@link SetDERControlRequest} with all required fields.
   *
   * @param isDefault True if this is a default DER control
   * @param controlId Unique id of this control, e.g. UUID
   * @param controlType Type of control. Determines which setting field below is used.
   * @return an instance of {@link SetDERControlRequest}
   */
  public SetDERControlRequest createSetDERControlRequest(
      Boolean isDefault, String controlId, DERControlEnum controlType) {
    return new SetDERControlRequest(isDefault, controlId, controlType);
  }
}
