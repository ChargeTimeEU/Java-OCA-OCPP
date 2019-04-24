package eu.chargetime.ocpp.feature.profile.test;
/*
   ChargeTime.eu - Java-OCA-OCPP

   MIT License

   Copyright (C) 2016-2018 Thomas Volden <tv@chargetime.eu>
   Copyright (C) 2018 Mikhail Kladkevich <kladmv@ecp-share.com>

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
import static org.junit.Assert.assertThat;

import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.feature.GetLocalListVersionFeature;
import eu.chargetime.ocpp.feature.SendLocalListFeature;
import eu.chargetime.ocpp.feature.profile.ServerLocalAuthListProfile;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ServerLocalAuthListProfileTest extends ProfileTest {

  ServerLocalAuthListProfile profile;

  @Before
  public void setup() {
    profile = new ServerLocalAuthListProfile();
  }

  @Test
  public void getFeatureList_containsGetLocalListVersionFeature() {
    // When
    Feature[] featureList = profile.getFeatureList();

    // Then
    assertThat(
        findFeature(featureList, "GetLocalListVersion"),
        CoreMatchers.is(instanceOf(GetLocalListVersionFeature.class)));
  }

  @Test
  public void getFeatureList_containsSendLocalListFeature() {
    // When
    Feature[] featureList = profile.getFeatureList();

    // Then
    assertThat(
        findFeature(featureList, "SendLocalList"),
        CoreMatchers.is(instanceOf(SendLocalListFeature.class)));
  }
}
