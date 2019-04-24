package eu.chargetime.ocpp.model.localauthlist.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.model.localauthlist.GetLocalListVersionConfirmation;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GetLocalListVersionConfirmationTest {

  @Rule public ExpectedException thrownException = ExpectedException.none();

  private GetLocalListVersionConfirmation confirmation;

  @Before
  public void setUp() {
    confirmation = new GetLocalListVersionConfirmation();
  }

  @Test
  public void setListVersion_lessThanNegativeOne_throwsPropertyConstraintException() {
    thrownException.expect(instanceOf(PropertyConstraintException.class));
    thrownException.expectMessage(
        equalTo("Validation failed: [listVersion must be >= -1]. Current Value: [-2]"));

    confirmation.setListVersion(-2);
  }

  @Test
  public void setListVersion_isNegativeOne_isCorrect() {
    // When
    confirmation.setListVersion(-1);

    // Then
    assertThat(confirmation.getListVersion(), equalTo(-1));
  }

  @Test
  public void setListVersion_isZero_isCorrect() {
    // When
    confirmation.setListVersion(0);

    // Then
    assertThat(confirmation.getListVersion(), equalTo(0));
  }

  @Test
  public void setListVersion_isNonZero_isCorrect() {
    for (int i = 1; i <= 100; i++) {
      // When
      confirmation.setListVersion(i);

      // Then
      assertThat(confirmation.getListVersion(), equalTo(i));
    }
  }

  @Test
  public void validate_listVersionIsNotSet_notValid() {
    // Then
    assertThat(confirmation.validate(), equalTo(false));
  }

  @Test
  public void validate_listVersionIsSet_Valid() {
    // When
    confirmation.setListVersion(10);
    // Then
    assertThat(confirmation.validate(), equalTo(true));
  }
}
