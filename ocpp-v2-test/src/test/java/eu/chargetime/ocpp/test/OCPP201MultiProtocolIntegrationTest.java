/*
 * ChargeTime.eu - Java-OCA-OCPP
 *
 * Copyright (C) 2023 Robert Schlabbach (robert.schlabbach@ubitricity.com)
 *
 * MIT License
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

package eu.chargetime.ocpp.test;

import static eu.chargetime.ocpp.ProtocolVersion.OCPP1_6;
import static eu.chargetime.ocpp.ProtocolVersion.OCPP2_0_1;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;

import eu.chargetime.ocpp.ProtocolVersion;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

/** multiple protocol capable client and server integration test up to OCPP 2.0.1 */
@RunWith(MockitoJUnitRunner.class)
public class OCPP201MultiProtocolIntegrationTest {

  private static final List<ProtocolVersion> OCPP1_6_ONLY = Collections.singletonList(OCPP1_6);
  private static final List<ProtocolVersion> OCPP2_0_1_ONLY = Collections.singletonList(OCPP2_0_1);
  private static final List<ProtocolVersion> OCPP1_6_FIRST = Arrays.asList(OCPP1_6, OCPP2_0_1);
  private static final List<ProtocolVersion> OCPP2_0_1_FIRST = Arrays.asList(OCPP2_0_1, OCPP1_6);

  private List<FakeCSMS> csmsList;

  @Before
  public void setup() {
    csmsList = new ArrayList<>();
  }

  @After
  public void teardown() {
    for (FakeCSMS csms : csmsList) {
      csms.stopServer();
    }
  }

  private FakeCSMS setupAndStartCSMS(List<ProtocolVersion> protocolVersions) {
    FakeCSMS csms = new OCPP201MultiProtocolFakeCSMS(protocolVersions);
    csmsList.add(csms);
    csms.startServer();
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    assertThat(csms.getPort(), not(0));
    return csms;
  }

  private FakeChargingStation buildAndConnectChargingStation(
      List<ProtocolVersion> protocolVersions, FakeCSMS csms) {
    FakeChargingStation cs;
    try {
      cs = new OCPP201MultiProtocolFakeChargingStation(protocolVersions);
    } catch (MalformedURLException e) {
      throw new RuntimeException(e);
    }
    cs.connect(csms.getPort());
    return cs;
  }

  private void reconnectChargingStation(FakeChargingStation cs, FakeCSMS csms) {
    cs.disconnect();
    cs.connect(csms.getPort());
  }

  @Test
  public void testMultiProtocolChargingStationReconnectingWithDifferentProtocolVersions() {
    FakeCSMS ocpp16OnlyCSMS = setupAndStartCSMS(OCPP1_6_ONLY);
    FakeChargingStation cs = buildAndConnectChargingStation(OCPP2_0_1_FIRST, ocpp16OnlyCSMS);
    assertThat(cs.getProtocolVersion(), is(OCPP1_6));
    cs.sendBootNotification("vendor", "model");

    FakeCSMS ocpp201OnlyCSMS = setupAndStartCSMS(OCPP2_0_1_ONLY);
    reconnectChargingStation(cs, ocpp201OnlyCSMS);
    assertThat(cs.getProtocolVersion(), is(OCPP2_0_1));
    cs.sendBootNotification("vendor", "model");

    reconnectChargingStation(cs, ocpp16OnlyCSMS);
    assertThat(cs.getProtocolVersion(), is(OCPP1_6));
    cs.sendBootNotification("vendor", "model");
  }

  @Test
  public void testMultiProtocolCsmsRunningParallelSessionsWithDifferentProtocolVersions() {
    FakeCSMS csms = setupAndStartCSMS(OCPP2_0_1_FIRST);

    FakeChargingStation cs16 = buildAndConnectChargingStation(OCPP1_6_ONLY, csms);
    cs16.sendBootNotification("vendor", "model");

    FakeChargingStation cs201 = buildAndConnectChargingStation(OCPP2_0_1_ONLY, csms);
    cs201.sendBootNotification("vendor", "model");

    reconnectChargingStation(cs16, csms);
    cs16.sendBootNotification("vendor", "model");

    cs201.sendBootNotification("vendor", "model");
  }
}
