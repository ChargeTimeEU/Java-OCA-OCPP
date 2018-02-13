package eu.chargetime.ocpp.model.localauthlist.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import eu.chargetime.ocpp.model.localauthlist.SendLocalListConfirmation;
import eu.chargetime.ocpp.model.localauthlist.UpdateStatus;

@RunWith(MockitoJUnitRunner.class)
public class SendLocalListConfirmationTest {
	private SendLocalListConfirmation confirmation;
	
	@Before
	public void setUp() {
		confirmation = new SendLocalListConfirmation();
	}
	
	@Test
	public void setStatus_allStatus_allCorrect() {
		
		for(UpdateStatus status : UpdateStatus.values()) {
			// When
			confirmation.setStatus(status);
			// Then
			assertThat(confirmation.getStatus(), equalTo(status));
		}
	}
	
	@Test
	public void validate_statusIsNotSet_notValid() {
		// Then
		assertThat(confirmation.validate(), equalTo(false));
	}
	
	@Test
	public void validate_statusIsSet_valid() throws Exception {
		// When
		confirmation.setStatus(UpdateStatus.Accepted);
		// Then
		assertThat(confirmation.validate(), equalTo(true));
	}
}
