package eu.chargetime.ocpp.model.localauthlist.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.model.localauthlist.SendLocalListConfirmation;
import eu.chargetime.ocpp.model.localauthlist.UpdateStatus;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SendLocalListConfirmationTest {

  @Rule public ExpectedException thrownException = ExpectedException.none();

  private SendLocalListConfirmation confirmation;

  @Before
  public void setUp() {
    confirmation = new SendLocalListConfirmation();
  }

  @Test
  public void setStatus_allStatus_allCorrect() {

    for (UpdateStatus status : UpdateStatus.values()) {
      // When
      confirmation.setStatus(status);
      // Then
      assertThat(confirmation.getStatus(), equalTo(status));
    }
  }

  @Test
  public void setStatus_asNull_throwsPropertyConstraintException() {
    thrownException.expect(instanceOf(PropertyConstraintException.class));
    thrownException.expectMessage(
        equalTo("Validation failed: [updateStatus must be present]. Current Value: [null]"));

    confirmation.setStatus(null);
  }

  @Test
  public void validate_statusIsNotSet_notValid() {
    // Then
    assertThat(confirmation.validate(), equalTo(false));
  }

  @Test
  public void validate_statusIsSet_valid() {
    // When
    confirmation.setStatus(UpdateStatus.Accepted);
    // Then
    assertThat(confirmation.validate(), equalTo(true));
  }
}
