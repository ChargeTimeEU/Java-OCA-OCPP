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

/** Class with server request creators and handlers for the SmartCharging functional block. */
public class ServerSmartChargingFunction implements Function {

  private final ServerSmartChargingEventHandler eventHandler;
  private final ArrayList<FunctionFeature> features;

  public ServerSmartChargingFunction(ServerSmartChargingEventHandler eventHandler) {
    this.eventHandler = eventHandler;
    features = new ArrayList<>();
    features.add(new ClearChargingProfileFeature(null));
    features.add(new ClearedChargingLimitFeature(this));
    features.add(new GetChargingProfilesFeature(null));
    features.add(new GetCompositeScheduleFeature(null));
    features.add(new NotifyChargingLimitFeature(this));
    features.add(new NotifyEVChargingNeedsFeature(this));
    features.add(new NotifyEVChargingScheduleFeature(this));
    features.add(new ReportChargingProfilesFeature(this));
    features.add(new SetChargingProfileFeature(null));
  }

  @Override
  public FunctionFeature[] getFeatureList() {
    return features.toArray(new FunctionFeature[0]);
  }

  @Override
  public Confirmation handleRequest(UUID sessionIndex, Request request) {
    if (request instanceof ClearedChargingLimitRequest) {
      return eventHandler.handleClearedChargingLimitRequest(
          sessionIndex, (ClearedChargingLimitRequest) request);
    } else if (request instanceof NotifyChargingLimitRequest) {
      return eventHandler.handleNotifyChargingLimitRequest(
          sessionIndex, (NotifyChargingLimitRequest) request);
    } else if (request instanceof NotifyEVChargingNeedsRequest) {
      return eventHandler.handleNotifyEVChargingNeedsRequest(
          sessionIndex, (NotifyEVChargingNeedsRequest) request);
    } else if (request instanceof NotifyEVChargingScheduleRequest) {
      return eventHandler.handleNotifyEVChargingScheduleRequest(
          sessionIndex, (NotifyEVChargingScheduleRequest) request);
    } else if (request instanceof ReportChargingProfilesRequest) {
      return eventHandler.handleReportChargingProfilesRequest(
          sessionIndex, (ReportChargingProfilesRequest) request);
    }
    return null;
  }

  /**
   * Create a server {@link ClearChargingProfileRequest}.
   *
   * @return an instance of {@link ClearChargingProfileRequest}
   */
  public ClearChargingProfileRequest createClearChargingProfileRequest() {
    return new ClearChargingProfileRequest();
  }

  /**
   * Create a server {@link GetChargingProfilesRequest} with all required fields.
   *
   * @param requestId Reference identification that is to be used by the Charging Station in the
   *     ReportChargingProfilesRequest when provided.
   * @param chargingProfile A ChargingProfile consists of ChargingSchedule, describing the amount of
   *     power or current that can be delivered per time interval.
   * @return an instance of {@link GetChargingProfilesRequest}
   */
  public GetChargingProfilesRequest createGetChargingProfilesRequest(
      Integer requestId, ChargingProfileCriterion chargingProfile) {
    return new GetChargingProfilesRequest(requestId, chargingProfile);
  }

  /**
   * Create a server {@link GetCompositeScheduleRequest} with all required fields.
   *
   * @param duration Length of the requested schedule in seconds.
   * @param evseId The ID of the EVSE for which the schedule is requested. When evseid=0, the
   *     Charging Station will calculate the expected consumption for the grid connection.
   * @return an instance of {@link GetCompositeScheduleRequest}
   */
  public GetCompositeScheduleRequest createGetCompositeScheduleRequest(
      Integer duration, Integer evseId) {
    return new GetCompositeScheduleRequest(duration, evseId);
  }

  /**
   * Create a server {@link SetChargingProfileRequest} with all required fields.
   *
   * @param evseId For TxDefaultProfile an evseId=0 applies the profile to each individual evse. For
   *     ChargingStationMaxProfile and ChargingStationExternalConstraints an evseId=0 contains an
   *     overal limit for the whole Charging Station.
   * @param chargingProfile A ChargingProfile consists of ChargingSchedule, describing the amount of
   *     power or current that can be delivered per time interval.
   * @return an instance of {@link SetChargingProfileRequest}
   */
  public SetChargingProfileRequest createSetChargingProfileRequest(
      Integer evseId, ChargingProfile chargingProfile) {
    return new SetChargingProfileRequest(evseId, chargingProfile);
  }
}
