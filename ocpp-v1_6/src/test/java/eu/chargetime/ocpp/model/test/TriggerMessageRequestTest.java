package eu.chargetime.ocpp.model.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.model.remotetrigger.TriggerMessageRequest;
import eu.chargetime.ocpp.model.remotetrigger.TriggerMessageRequestType;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/*
ChargeTime.eu - Java-OCA-OCPP
Copyright (C) 2017 Emil Christopher Solli Melar <emil@iconsultable.no>

MIT License

Copyright (C) 2017 Emil Christopher Solli Melar

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

public class TriggerMessageRequestTest {

  @Rule public ExpectedException thrownException = ExpectedException.none();

  private TriggerMessageRequest request;

  @Before
  public void setUp() {
    request = new TriggerMessageRequest();
  }

  @Test
  public void setConnectorId_zeroInteger_throwsPropertyConstraintException() {
    testInvalidConnectorId(0);
  }

  @Test
  public void setConnectorId_negativeInteger_throwsPropertyConstraintException() {
    testInvalidConnectorId(-42);
  }

  private void testInvalidConnectorId(int invalidConnectorId) {
    thrownException.expect(instanceOf(PropertyConstraintException.class));
    thrownException.expectMessage(
        equalTo(
            "Validation failed: [connectorId must be > 0]. Current Value: ["
                + invalidConnectorId
                + "]"));

    request.setConnectorId(invalidConnectorId);
  }

  @Test
  public void setConnectorId_positiveInteger_connectorIdIsSet() {
    // Given
    Integer positive = 42;

    // When
    request.setConnectorId(positive);

    // Then
    assertThat(request.getConnectorId(), equalTo(positive));
  }

  @Test
  public void setRequestedMessage_requestedMessage_IsSet() {
    // Given
    TriggerMessageRequestType type = TriggerMessageRequestType.Heartbeat;

    // When
    request.setRequestedMessage(type);

    // Then
    assertThat(request.getRequestedMessage(), equalTo(type));
  }

  @Test
  public void setRequestedMessageInConstructor_requestedMessage_IsSet() {
    // Given
    TriggerMessageRequestType type = TriggerMessageRequestType.Heartbeat;

    // When
    TriggerMessageRequest constructorRequest = new TriggerMessageRequest(type);

    // Then
    assertThat(constructorRequest.getRequestedMessage(), equalTo(type));
  }
}
