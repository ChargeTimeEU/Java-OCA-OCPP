package eu.chargetime.ocpp.feature.profile.test;
/*
 * ChargeTime.eu - Java-OCA-OCPP
 *
 * MIT License
 *
 * Copyright (C) 2019 Kevin Raddatz <kevin.raddatz@valtech-mobility.com>
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

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.feature.GetLocalListVersionFeature;
import eu.chargetime.ocpp.feature.SendLocalListFeature;
import eu.chargetime.ocpp.feature.profile.ClientLocalAuthListEventHandler;
import eu.chargetime.ocpp.feature.profile.ClientLocalAuthListProfile;
import eu.chargetime.ocpp.model.localauthlist.GetLocalListVersionRequest;
import eu.chargetime.ocpp.model.localauthlist.SendLocalListRequest;
import eu.chargetime.ocpp.model.localauthlist.UpdateType;
import java.util.UUID;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ClientLocalAuthListProfileTest extends ProfileTest {
  private static final UUID SESSION_NULL = null;

  private ClientLocalAuthListProfile profile;

  @Mock private ClientLocalAuthListEventHandler handler;

  @Before
  public void setup() {
    profile = new ClientLocalAuthListProfile(handler);
  }

  @Test
  public void getFeatureList_containsGetLocalListVersionFeature() {
    // When
    Feature[] featureList = profile.getFeatureList();

    // Then
    assertThat(
        findFeature(featureList, "GetLocalListVersion"),
        is(instanceOf(GetLocalListVersionFeature.class)));
  }

  @Test
  public void getFeatureList_containsSendLocalListFeature() {
    // When
    Feature[] featureList = profile.getFeatureList();

    // Then
    assertThat(
        findFeature(featureList, "SendLocalList"), is(instanceOf(SendLocalListFeature.class)));
  }

  public void handleRequest_GetLocalListVersion_callsHandleGetLocalListVersionRequest() {
    // Given
    GetLocalListVersionRequest request = new GetLocalListVersionRequest();

    // When
    profile.handleRequest(SESSION_NULL, request);

    // Then
    verify(handler, times(1)).handleGetLocalListVersionRequest(eq(request));
  }

  public void handleRequest_SendLocalList_callsHandleSendLocalListRequest() {
    // Given
    SendLocalListRequest request = new SendLocalListRequest(0, UpdateType.Full);

    // When
    profile.handleRequest(SESSION_NULL, request);

    // Then
    verify(handler, times(1)).handleSendLocalListRequest(eq(request));
  }
}
