package eu.chargetime.ocpp.feature.profile.test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import eu.chargetime.ocpp.feature.ClearChargingProfileFeature;
import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.feature.SetChargingProfileFeature;
import eu.chargetime.ocpp.feature.profile.ServerSmartChargingProfile;
import eu.chargetime.ocpp.model.core.*;
import eu.chargetime.ocpp.model.smartcharging.ClearChargingProfileRequest;
import eu.chargetime.ocpp.model.smartcharging.SetChargingProfileRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/*
 * ChargeTime.eu - Java-OCA-OCPP
 *
 * MIT License
 *
 * Copyright (C) 2017 Emil Christopher Solli Melar <emil@iconsultable.no>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

@RunWith(MockitoJUnitRunner.class)
public class ServerSmartChargingProfileTest extends ProfileTest {

  private ServerSmartChargingProfile smartCharging;

  @Before
  public void setup() {
    smartCharging = new ServerSmartChargingProfile();
  }

  @Test
  public void getFeatureList_containsSetChargingProfileFeature() {
    // When
    Feature[] features = smartCharging.getFeatureList();

    // Then
    assertThat(
        findFeature(features, "SetChargingProfile"),
        is(instanceOf(SetChargingProfileFeature.class)));
  }

  @Test
  public void createSetChargingProfileRequest_returnsValidSetChargingProfileRequest() {

    int connectorId = 123;

    ChargingProfile profile = new ChargingProfile();

    int profileId = 666;
    profile.setChargingProfileId(profileId);
    profile.setChargingProfilePurpose(ChargingProfilePurposeType.TxProfile);
    profile.setStackLevel(42);
    profile.setChargingProfileKind(ChargingProfileKindType.Absolute);

    ChargingSchedule schedule = new ChargingSchedule();
    schedule.setChargingRateUnit(ChargingRateUnitType.A);
    ChargingSchedulePeriod period = new ChargingSchedulePeriod();
    period.setStartPeriod(0);
    period.setLimit(20.3);
    period.setNumberPhases(1);
    schedule.setChargingSchedulePeriod(new ChargingSchedulePeriod[] {period});
    profile.setChargingSchedule(schedule);

    // When
    SetChargingProfileRequest result =
        smartCharging.createSetChargingProfileRequest(connectorId, profile);

    // Then
    assertThat(result.validate(), is(true));
    assertThat(result.getConnectorId(), is(connectorId));

    ChargingProfile resultProfile = result.getCsChargingProfiles();
    assertThat(profile, is(resultProfile));
  }

  @Test
  public void getFeatureList_containsClearChargingProfileFeature() {
    // When
    Feature[] features = smartCharging.getFeatureList();

    // Then
    assertThat(
        findFeature(features, "ClearChargingProfile"),
        is(instanceOf(ClearChargingProfileFeature.class)));
  }

  @Test
  public void createClearCacheRequest_returnsValidClearCacheRequest() {
    // When
    ClearChargingProfileRequest result = smartCharging.createClearChargingProfileRequest();

    // Then
    assertThat(result.validate(), is(true));
  }
}
