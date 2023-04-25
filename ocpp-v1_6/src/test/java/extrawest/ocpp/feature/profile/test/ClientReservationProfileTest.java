package extrawest.ocpp.feature.profile.test;
/*
   ChargeTime.eu - Java-OCA-OCPP

   MIT License

   Copyright (C) 2016-2018 Thomas Volden <tv@chargetime.eu>
   Copyright (C) 2018 Mikhail Kladkevich <kladmv@ecp-share.com>
   Copyright (C) 2019 Kevin Raddatz <kevin.raddatz@valtech-mobility.com>

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

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import extrawest.ocpp.feature.CancelReservationFeature;
import extrawest.ocpp.feature.Feature;
import extrawest.ocpp.feature.ReserveNowFeature;
import extrawest.ocpp.feature.profile.ClientReservationEventHandler;
import extrawest.ocpp.feature.profile.ClientReservationProfile;
import extrawest.ocpp.model.reservation.CancelReservationRequest;
import extrawest.ocpp.model.reservation.ReserveNowRequest;
import java.time.ZonedDateTime;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ClientReservationProfileTest extends ProfileTest {
  private static final UUID SESSION_NULL = null;
  ClientReservationProfile profile;

  @Mock private ClientReservationEventHandler handler;

  @Before
  public void setup() {
    profile = new ClientReservationProfile(handler);
  }

  @Test
  public void getFeatureList_containsReserveNowFeature() {
    // When
    Feature[] features = profile.getFeatureList();

    // Then
    assertThat(findFeature(features, "ReserveNow"), is(instanceOf(ReserveNowFeature.class)));
  }

  @Test
  public void getFeatureList_containsCancelReservationFeature() {
    // When
    Feature[] features = profile.getFeatureList();

    // Then
    assertThat(
        findFeature(features, "CancelReservation"), is(instanceOf(CancelReservationFeature.class)));
  }

  @Test
  public void handleRequest_aReserveNowRequest_callsHandleReserveNowRequest() {
    // Given
    ReserveNowRequest request = new ReserveNowRequest(0, ZonedDateTime.now(), "idTag", 0);

    // When
    profile.handleRequest(SESSION_NULL, request);

    // Then
    verify(handler, times(1)).handleReserveNowRequest(eq(request));
  }

  @Test
  public void handleRequest_aCancelReservationRequest_callsHandleCancelReservationRequest() {
    // Given
    CancelReservationRequest request = new CancelReservationRequest(0);

    // When
    profile.handleRequest(SESSION_NULL, request);

    // Then
    verify(handler, times(1)).handleCancelReservationRequest(eq(request));
  }
}