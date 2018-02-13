package eu.chargetime.ocpp.model.localauthlist.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.model.localauthlist.GetLocalListVersionConfirmation;

@RunWith(MockitoJUnitRunner.class)
public class GetLocalListVersionConfirmationTest {
	GetLocalListVersionConfirmation confirmation;
	
	@Before
	public void setUp() {
		confirmation =  new GetLocalListVersionConfirmation();
	}
	
	@Test
	public void setListVersion_lessThanNegativeOne_throwsPropertyConstraintException() {
		try {
			confirmation.setListVersion(-2);
		} catch (PropertyConstraintException e) {
            // Then
            assertThat(e.getFieldKey(), equalTo("listVersion"));
            assertThat(e.getFieldValue(), equalTo(-2));
		}
	}
	
	@Test
	public void setListVersion_isNegativeOne_isCorrect() throws Exception {
		// When
		confirmation.setListVersion(-1);

		// Then
		assertThat(confirmation.getListVersion(), equalTo(-1));
	}
	
	@Test
	public void setListVersion_isZero_isCorrect() throws Exception {
		// When
		confirmation.setListVersion(0);

		// Then
		assertThat(confirmation.getListVersion(), equalTo(0));
	}
	
	@Test
	public void setListVersion_isNonZero_isCorrect() throws Exception {
		for(int i = 1; i <= 100; i++) {
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
	public void validate_listVersionIsSet_Valid() throws Exception {
		// When
		confirmation.setListVersion(10);
		// Then
		assertThat(confirmation.validate(), equalTo(true));
	}
}
