package eu.chargetime.ocpp.model.localauthlist.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import eu.chargetime.ocpp.model.localauthlist.GetLocalListVersionRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GetLocalListVersionRequestTest {
  private GetLocalListVersionRequest request;

  @Before
  public void setUp() {
    request = new GetLocalListVersionRequest();
  }

  @Test
  public void validate_returnTrue() {
    // When
    boolean isValid = request.validate();

    // Then
    assertThat(isValid, is(equalTo(true)));
  }
}
