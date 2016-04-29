package eu.chargetime.ocpp.test;

import eu.chargetime.ocpp.Communicator;
import eu.chargetime.ocpp.Queue;
import eu.chargetime.ocpp.Session;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

/**
 * Created by Thomas Volden on 29-Apr-16.
 */
public class SessionTest {
    private Session session;

    @Mock
    Communicator communicator = mock(Communicator.class);
    @Mock
    Queue queue = mock(Queue.class);

    @Before
    public void setup() throws Exception {
        session = new Session(communicator, queue);
    }

    @Test
    public void sendRequest_getUniqueIdReturnedFromQueue() {
        // Given
        String someId = "test id";
        when(queue.store(any())).thenReturn(someId);

        // When
        String id = session.sendRequest(null, null);

        // Then
        assertThat(id, equalTo(someId));
    }

    @Test
    public void sendRequest_sendRequestToCommunicator() {
        // Given
        String  someAction      = "Test action";
        Request someRequest     = new Request() {};

        // When
        session.sendRequest(someAction, someRequest);

        // Then
        verify(communicator, times(1)).sendCall(anyString(), eq(someAction), eq(someRequest));
    }

    @Test
    public void sendRequest_fixedUniqueId_sendsUniqueIdToCommunicator() {
        // Given
        String someUniqueId = "test id";
        when(queue.store(any())).thenReturn(someUniqueId);

        // When
        session.sendRequest(null, null);

        // Then
        verify(communicator, times(1)).sendCall(eq(someUniqueId), anyString(), any());
    }

    @Test
    public void sendConfirmation_sendsConfirmationToCommunicator(){
        // Given
        Confirmation conf = new Confirmation() {};
        String someUniqueId = "Some id";

        // When
        session.sendConfirmation(someUniqueId, conf);

        // Then
        verify(communicator, times(1)).sendCallResult(eq(someUniqueId), eq(conf));
    }

    @Test
    public void open_connectsViaCommunicator() {
        // Given
        String someUri = "localhost";

        // When
        session.open(someUri, null);

        // Then
        verify(communicator, times(1)).connect(eq(someUri), any());
    }
}