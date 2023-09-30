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

/** Class with client request creators and handlers for the SmartCharging functional block. */
public class ClientSmartChargingFunction implements Function {

  private final ClientSmartChargingEventHandler eventHandler;
  private final ArrayList<FunctionFeature> features;

  public ClientSmartChargingFunction(ClientSmartChargingEventHandler eventHandler) {
    this.eventHandler = eventHandler;
    features = new ArrayList<>();
    features.add(new ClearChargingProfileFeature(this));
    features.add(new ClearedChargingLimitFeature(null));
    features.add(new GetChargingProfilesFeature(this));
    features.add(new GetCompositeScheduleFeature(this));
    features.add(new NotifyChargingLimitFeature(null));
    features.add(new NotifyEVChargingNeedsFeature(null));
    features.add(new NotifyEVChargingScheduleFeature(null));
    features.add(new ReportChargingProfilesFeature(null));
    features.add(new SetChargingProfileFeature(this));
  }

  @Override
  public FunctionFeature[] getFeatureList() {
    return features.toArray(new FunctionFeature[0]);
  }

  @Override
  public Confirmation handleRequest(UUID sessionIndex, Request request) {
    if (request instanceof ClearChargingProfileRequest) {
      return eventHandler.handleClearChargingProfileRequest((ClearChargingProfileRequest) request);
    } else if (request instanceof GetChargingProfilesRequest) {
      return eventHandler.handleGetChargingProfilesRequest((GetChargingProfilesRequest) request);
    } else if (request instanceof GetCompositeScheduleRequest) {
      return eventHandler.handleGetCompositeScheduleRequest((GetCompositeScheduleRequest) request);
    } else if (request instanceof SetChargingProfileRequest) {
      return eventHandler.handleSetChargingProfileRequest((SetChargingProfileRequest) request);
    }
    return null;
  }

  /**
   * Create a client {@link ClearedChargingLimitRequest} with all required fields.
   *
   * @param chargingLimitSource Source of the charging limit.
   * @return an instance of {@link ClearedChargingLimitRequest}
   */
  public ClearedChargingLimitRequest createClearedChargingLimitRequest(
      ChargingLimitSourceEnum chargingLimitSource) {
    return new ClearedChargingLimitRequest(chargingLimitSource);
  }

  /**
   * Create a client {@link NotifyChargingLimitRequest} with all required fields.
   *
   * @param chargingLimit Charging Limit
   * @return an instance of {@link NotifyChargingLimitRequest}
   */
  public NotifyChargingLimitRequest createNotifyChargingLimitRequest(ChargingLimit chargingLimit) {
    return new NotifyChargingLimitRequest(chargingLimit);
  }

  /**
   * Create a client {@link NotifyEVChargingNeedsRequest} with all required fields.
   *
   * @param chargingNeeds Charging Needs
   * @param evseId The EVSE and connector to which the EV is connected. EvseId may not be 0.
   * @return an instance of {@link NotifyEVChargingNeedsRequest}
   */
  public NotifyEVChargingNeedsRequest createNotifyEVChargingNeedsRequest(
      ChargingNeeds chargingNeeds, Integer evseId) {
    return new NotifyEVChargingNeedsRequest(chargingNeeds, evseId);
  }

  /**
   * Create a client {@link NotifyEVChargingScheduleRequest} with all required fields.
   *
   * @param timeBase Periods contained in the charging profile are relative to this point in time.
   * @param chargingSchedule Charging schedule structure defines a list of charging periods, as used
   *     in: GetCompositeSchedule.conf and ChargingProfile.
   * @param evseId The charging schedule contained in this notification applies to an EVSE. EvseId
   *     must be greater than 0.
   * @return an instance of {@link NotifyEVChargingScheduleRequest}
   */
  public NotifyEVChargingScheduleRequest createNotifyEVChargingScheduleRequest(
      ZonedDateTime timeBase, ChargingSchedule chargingSchedule, Integer evseId) {
    return new NotifyEVChargingScheduleRequest(timeBase, chargingSchedule, evseId);
  }

  /**
   * Create a client {@link ReportChargingProfilesRequest} with all required fields.
   *
   * @param requestId Id used to match the GetChargingProfilesRequest message with the resulting
   *     ReportChargingProfilesRequest messages. When the CSMS provided a requestId in the
   *     GetChargingProfilesRequest, this field SHALL contain the same value.
   * @param chargingLimitSource Source that has installed this charging profile.
   * @param chargingProfile A ChargingProfile consists of ChargingSchedule, describing the amount of
   *     power or current that can be delivered per time interval.
   * @param evseId The evse to which the charging profile applies. If evseId = 0, the message
   *     contains an overall limit for the Charging Station.
   * @return an instance of {@link ReportChargingProfilesRequest}
   */
  public ReportChargingProfilesRequest createReportChargingProfilesRequest(
      Integer requestId,
      ChargingLimitSourceEnum chargingLimitSource,
      ChargingProfile[] chargingProfile,
      Integer evseId) {
    return new ReportChargingProfilesRequest(
        requestId, chargingLimitSource, chargingProfile, evseId);
  }
}
