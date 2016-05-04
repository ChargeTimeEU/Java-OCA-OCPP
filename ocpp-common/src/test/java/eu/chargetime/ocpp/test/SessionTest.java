package eu.chargetime.ocpp.test;

import eu.chargetime.ocpp.*;
import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import groovy.transform.Synchronized;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.testng.TestException;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

/**
 * Created by Thomas Volden on 29-Apr-16.
 */
public class SessionTest {
    private Session session;

    CommunicatorEvents eventHandler;

    @Mock
    Communicator communicator = mock(Communicator.class);
    @Mock
    Queue queue = mock(Queue.class);
    @Mock
    SessionEvents sessionEvents = mock(SessionEvents.class);
    @Mock
    Feature feature = mock(Feature.class);

    @Before
    public void setup() throws Exception {
        when(sessionEvents.findFeatureByAction(any())).thenReturn(feature);
        session = new Session(communicator, queue);
        doAnswer(invocation -> eventHandler = invocation.getArgumentAt(1, CommunicatorEvents.class)).when(communicator).connect(any(), any());
        session.open(null, sessionEvents);
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
        Request someRequest     = new Request() {
            @Override
            public boolean validate() {
                return false;
            }
        };

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
        Confirmation conf = new Confirmation() {
            @Override
            public boolean validate() {
                return false;
            }
        };
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

    @Test
    public void onCall_unhandledCallback_callSendCallError() {
        // Given
        String someId = "Some id";
        when(sessionEvents.handleRequest(any())).thenReturn(null);

        // When
        eventHandler.onCall(someId, null, null);
        try { Thread.sleep(10); } catch (Exception ex) {} // TODO make async invoker injectable

        // then
        verify(communicator, times(1)).sendCallError(eq(someId), anyString(), anyString());
    }

    @Test
    public void onCall_handledCallback_callSendCallResult() {
        // Given
        String someId = "Some id";
        Confirmation aConfirmation = new Confirmation() {
            @Override
            public boolean validate() {
                return false;
            }
        };
        when(sessionEvents.handleRequest(any())).thenReturn(aConfirmation);

        // When
        eventHandler.onCall(someId, null, null);
        try { Thread.sleep(10); } catch (Exception ex) {} // TODO make async invoker injectable

        // then
        verify(communicator, times(1)).sendCallResult(anyString(), eq(aConfirmation));
    }

    @Test
    public void onCall_callbackThrowsException_callSendCallResult() {
        // Given
        String someId = "Some id";
        when(sessionEvents.handleRequest(any())).thenThrow(TestException.class);

        // When
        eventHandler.onCall(someId, null, null);
        try { Thread.sleep(10); } catch (Exception ex) {} // TODO make async invoker injectable

        // then
        verify(communicator, times(1)).sendCallError(eq(someId), anyString(), anyString());
    }

    @Test
    public void close_disconnects() {
        // When
        session.close();

        // Then
        verify(communicator, times(1)).disconnect();
    }

    @Test
    public void onCall_unknownAction_callSendCallError() {
        // Given
        String someId = "Some id";
        when(sessionEvents.findFeatureByAction(any())).thenReturn(null);

        // When
        eventHandler.onCall(someId, null, null);

        // then
        verify(communicator, times(1)).sendCallError(eq(someId), anyString(), anyString());
    }
}