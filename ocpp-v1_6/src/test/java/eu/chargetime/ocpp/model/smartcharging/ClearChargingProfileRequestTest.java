package eu.chargetime.ocpp.model.smartcharging;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import eu.chargetime.ocpp.PropertyConstraintException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ClearChargingProfileRequestTest {

  @Rule public ExpectedException thrownException = ExpectedException.none();

  private ClearChargingProfileRequest request;

  @Before
  public void setup() {
    request = new ClearChargingProfileRequest();
  }

  @Test
  public void setConnectorId_outOfBounderies_throwsPropertyConstraintException() {

    thrownException.expect(instanceOf(PropertyConstraintException.class));
    thrownException.expectMessage(
        equalTo("Validation failed: [connectorId must be >= 0]. Current Value: [-1]"));

    Integer illegalValue = -1;

    request.setConnectorId(illegalValue);
  }

  @Test
  public void setStackLevel_outOfBounderies_throwsPropertyConstraintException() {

    thrownException.expect(instanceOf(PropertyConstraintException.class));
    thrownException.expectMessage(
        equalTo("Validation failed: [stackLevel must be >= 0]. Current Value: [-1]"));

    Integer illegalValue = -1;

    request.setStackLevel(illegalValue);
  }

  @Test
  public void setStackLevel_validValue_stackLevelEqualsValue() {
    // When
    Integer validValue = 1;
    request.setStackLevel(1);

    // Then
    assertThat(request.getStackLevel(), is(validValue));
  }

  @Test
  public void validate_clearChargingProfileRequestIsEmpty_returnTrue() {
    // When
    boolean isValid = request.validate();

    // Then
    assertThat(isValid, is(true));
  }

  @Test
  public void validate_connectorIdSet_returnTrue() {
    // Given
    request.setConnectorId(123);

    // When
    boolean isValid = request.validate();

    // Then
    assertThat(isValid, is(true));
  }

  @Test
  public void isTransactionRelated_returnsFalse() {
    // When
    boolean isTransactionRelated = request.transactionRelated();

    // Then
    assertThat(isTransactionRelated, is(false));
  }
}
