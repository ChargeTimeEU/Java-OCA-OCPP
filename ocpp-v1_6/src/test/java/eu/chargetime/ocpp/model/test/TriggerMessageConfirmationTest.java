package eu.chargetime.ocpp.model.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import eu.chargetime.ocpp.model.remotetrigger.TriggerMessageConfirmation;
import eu.chargetime.ocpp.model.remotetrigger.TriggerMessageStatus;
import org.junit.Before;
import org.junit.Test;

public class TriggerMessageConfirmationTest {
  TriggerMessageConfirmation confirmation;

  @Before
  public void setUp() throws Exception {
    confirmation = new TriggerMessageConfirmation();
  }

  @Test
  public void setStatus_status_IsSet() {
    // Given
    TriggerMessageStatus status = TriggerMessageStatus.Accepted;

    // When
    confirmation.setStatus(status);

    // Then
    assertThat(confirmation.getStatus(), equalTo(status));
  }

  @Test
  public void validate_statusIsNull_returnsFalse() {
    // when
    boolean isValid = confirmation.validate();

    // Then
    assertThat(isValid, is(false));
  }
}
