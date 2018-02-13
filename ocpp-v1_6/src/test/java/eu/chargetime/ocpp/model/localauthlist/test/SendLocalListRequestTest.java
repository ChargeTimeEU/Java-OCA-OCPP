package eu.chargetime.ocpp.model.localauthlist.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.model.localauthlist.AuthorizationData;
import eu.chargetime.ocpp.model.localauthlist.SendLocalListRequest;
import eu.chargetime.ocpp.model.localauthlist.UpdateType;
import eu.chargetime.ocpp.utilities.TestUtilities;

@RunWith(MockitoJUnitRunner.class)
public class SendLocalListRequestTest extends TestUtilities {
	private SendLocalListRequest request;
	@Mock
	private AuthorizationData data;
	
	@Before
	public void setUp() {
		request = new SendLocalListRequest();
	}
	
	@Test
	public void setListVersion_isNegativeAndZero_throwsPropertyConstraintException() {
		for(int i = -10; i <= 0; i ++) {
			try {
				// When
				request.setListVersion(i);
			} catch(PropertyConstraintException e) {
				// Then
	            assertThat(e.getFieldKey(), equalTo("listVersion"));
	            assertThat(e.getFieldValue(), equalTo(i));
			}
		}
	}
	
	@Test
	public void setListVersion_isNonZeroPostive_isCorrect() throws Exception {
		for(int i = 1; i <= 10; i ++) {
			// When
			request.setListVersion(i);
			// Then
			assertThat(request.getListVersion(), equalTo(i));
		}
	}
	
	@Test
	public void setLocalAuthorizationList_works() {
		// When
		AuthorizationData [] list = aList(data);
		request.setLocalAuthorizationList(aList(data));
		// Then
		assertThat(request.getLocalAuthorizationList(), equalTo(list));
	}
	
	@Test
	public void setUpdateType_allTypes_isCorrect() {
		for(UpdateType type : UpdateType.values()) {
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
	public void validate_localAuthorizationListNotSet_isValid() throws Exception {
		// When
		request.setListVersion(42);
		request.setUpdateType(UpdateType.Full);
		// Then
		assertThat(request.validate(), equalTo(true));
	}
	
	@Test
	public void validate_updateTypeIsNotSet_isNotValid() throws Exception {
		// When
		request.setListVersion(42);
		request.setLocalAuthorizationList(aList(data));
		// Then
		assertThat(request.validate(), equalTo(false));
	}
}
