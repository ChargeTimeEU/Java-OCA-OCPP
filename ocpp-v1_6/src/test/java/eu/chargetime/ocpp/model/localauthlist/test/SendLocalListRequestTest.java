package eu.chargetime.ocpp.model.localauthlist.test;

import static eu.chargetime.ocpp.utilities.TestUtilities.aList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.model.core.AuthorizationStatus;
import eu.chargetime.ocpp.model.core.IdTagInfo;
import eu.chargetime.ocpp.model.localauthlist.AuthorizationData;
import eu.chargetime.ocpp.model.localauthlist.SendLocalListRequest;
import eu.chargetime.ocpp.model.localauthlist.UpdateType;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SendLocalListRequestTest {

  @Rule public ExpectedException thrownException = ExpectedException.none();

  private SendLocalListRequest request;

  @Mock private AuthorizationData data;

  @Before
  public void setUp() {
    request = new SendLocalListRequest();
  }

  @Test
  public void setListVersion_asNegative_throwsPropertyConstraintException() {
    testInvalidListVersion(-1);
  }

  private void testInvalidListVersion(int invalidVersion) {
    thrownException.expect(instanceOf(PropertyConstraintException.class));
    thrownException.expectMessage(
        equalTo(
            "Validation failed: [listVersion must be >= 0]. Current Value: ["
                + invalidVersion
                + "]"));

    request.setListVersion(invalidVersion);
  }

  @Test
  public void setListVersion_isNotNegative_isCorrect() {
    for (int i = 0; i <= 10; i++) {
      // When
      request.setListVersion(i);
      // Then
      assertThat(request.getListVersion(), equalTo(i));
    }
  }

  @Test
  public void setLocalAuthorizationList_works() {
    // When
    AuthorizationData[] list = aList(data);
    request.setLocalAuthorizationList(aList(data));
    // Then
    assertThat(request.getLocalAuthorizationList(), equalTo(list));
  }

  @Test
  public void setUpdateType_allTypes_isCorrect() {
    for (UpdateType type : UpdateType.values()) {
      // When
      request.setUpdateType(type);
      // Then
      assertThat(request.getUpdateType(), equalTo(type));
    }
  }

  @Test
  public void validate_listVersionNotSet_isNotValid() {
    // When
    request.setLocalAuthorizationList(aList(data));
    request.setUpdateType(UpdateType.Full);
    // Then
    assertThat(request.validate(), equalTo(false));
  }

  @Test
  public void validate_localAuthorizationListNotSet_isValid() {
    // When
    request.setListVersion(42);
    request.setUpdateType(UpdateType.Full);
    // Then
    assertThat(request.validate(), equalTo(true));
  }

  @Test
  public void validate_updateTypeIsNotSet_isNotValid() {
    // When
    request.setListVersion(42);
    request.setLocalAuthorizationList(aList(data));
    // Then
    assertThat(request.validate(), equalTo(false));
  }

  @Test
  public void validate_updateTypeFullWithTagInfo_isValid() {
    // When
    request.setListVersion(42);
    request.setUpdateType(UpdateType.Full);
    request.setLocalAuthorizationList(aList(data));

    when(data.validate()).thenReturn(true);
    when(data.getIdTagInfo()).thenReturn(new IdTagInfo(AuthorizationStatus.Accepted));

    // Then
    assertThat(request.validate(), equalTo(true));
    verify(data, times(1)).getIdTagInfo();
    verify(data, times(1)).validate();
  }

  @Test
  public void validate_updateTypeFullEmptyTagInfo_isNotValid() {
    // When
    request.setListVersion(42);
    request.setUpdateType(UpdateType.Full);
    request.setLocalAuthorizationList(aList(data));

    when(data.validate()).thenReturn(true);
    when(data.getIdTagInfo()).thenReturn(null);

    // Then
    assertThat(request.validate(), equalTo(false));
  }

  @Test
  public void validate_updateTypeDifferentialEmptyIdTagInfo_isValid() {
    // When
    request.setListVersion(42);
    request.setUpdateType(UpdateType.Differential);
    request.setLocalAuthorizationList(aList(data));

    when(data.validate()).thenReturn(true);

    // Then
    assertThat(request.validate(), equalTo(true));
  }
}
