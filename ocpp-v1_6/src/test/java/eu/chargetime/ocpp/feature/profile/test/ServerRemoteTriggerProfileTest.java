package eu.chargetime.ocpp.feature.profile.test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.feature.TriggerMessageFeature;
import eu.chargetime.ocpp.feature.profile.ServerRemoteTriggerProfile;
import eu.chargetime.ocpp.model.remotetrigger.TriggerMessageRequest;
import eu.chargetime.ocpp.model.remotetrigger.TriggerMessageRequestType;
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
public class ServerRemoteTriggerProfileTest extends ProfileTest {

  private ServerRemoteTriggerProfile profile;

  @Before
  public void setup() {
    profile = new ServerRemoteTriggerProfile();
  }

  @Test
  public void getFeatureList_containsTriggerMessageFeature() {
    // When
    Feature[] features = profile.getFeatureList();

    // Then
    assertThat(
        findFeature(features, "TriggerMessage"), is(instanceOf(TriggerMessageFeature.class)));
  }

  @Test
  public void createTriggerMessageRequest_returnsValidTriggerMessageRequest() {
    TriggerMessageRequestType type = TriggerMessageRequestType.DiagnosticsStatusNotification;
    int connectorId = 1;

    // When
    TriggerMessageRequest result = profile.createTriggerMessageRequest(type, connectorId);

    // Then
    assertThat(result.validate(), is(true));

    // When
    result = profile.createTriggerMessageRequest(type, null);

    // Then
    assertThat(result.validate(), is(true));
  }

  @Test
  public void createTriggerMessageRequest_returnsValidTriggerMessageRequestNoConnectorId() {
    TriggerMessageRequestType type = TriggerMessageRequestType.DiagnosticsStatusNotification;

    // When
    TriggerMessageRequest result = profile.createTriggerMessageRequest(type);

    // Then
    assertThat(result.validate(), is(true));
  }
}
