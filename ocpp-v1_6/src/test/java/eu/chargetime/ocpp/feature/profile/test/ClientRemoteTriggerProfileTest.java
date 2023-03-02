package eu.chargetime.ocpp.feature.profile.test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

import eu.chargetime.ocpp.feature.profile.ClientRemoteTriggerEventHandler;
import eu.chargetime.ocpp.feature.profile.ClientRemoteTriggerProfile;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.remotetrigger.TriggerMessageConfirmation;
import eu.chargetime.ocpp.model.remotetrigger.TriggerMessageRequest;
import eu.chargetime.ocpp.model.remotetrigger.TriggerMessageRequestType;
import eu.chargetime.ocpp.model.remotetrigger.TriggerMessageStatus;
import java.util.UUID;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/*
 * ChargeTime.eu - Java-OCA-OCPP
 *
 * MIT License
 *
 * Copyright (C) 2017 Emil Christopher Solli Melar <emil@iconsultable.no>
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

@RunWith(MockitoJUnitRunner.class)
public class ClientRemoteTriggerProfileTest {

  private static final UUID SESSION_NULL = null;
  private ClientRemoteTriggerProfile profile;

  @Mock private ClientRemoteTriggerEventHandler handler;

  @Before
  public void setup() {
    profile = new ClientRemoteTriggerProfile(handler);
  }

  @Test
  public void handleRequest_TriggerMessageReqest_callsHandleTriggerMessageReqestRequest() {
    // Given
    TriggerMessageRequest request =
        new TriggerMessageRequest(TriggerMessageRequestType.BootNotification);

    // When
    profile.handleRequest(SESSION_NULL, request);

    // Then
    verify(handler, times(1)).handleTriggerMessageRequest(request);
  }

  @Test
  public void handleRequest_SetTriggerMessageRequest_returnsTriggerMessageRequest() {
    // Given
    when(handler.handleTriggerMessageRequest(any()))
        .thenReturn(new TriggerMessageConfirmation(TriggerMessageStatus.Accepted));
    TriggerMessageRequest request =
        new TriggerMessageRequest(TriggerMessageRequestType.BootNotification);

    // When
    Confirmation conf = profile.handleRequest(SESSION_NULL, request);

    // Then
    assertThat(conf, instanceOf(TriggerMessageConfirmation.class));
  }
}
