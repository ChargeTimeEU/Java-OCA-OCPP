package eu.chargetime.ocpp.test;

import eu.chargetime.ocpp.Queue;
import eu.chargetime.ocpp.model.Request;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

/**
 * Created by Thomas Volden on 18-Apr-16.
 */
public class QueueTest
{
    Queue queue;


    @Before
    public void setUp() throws Exception
    {
        queue = new Queue();
    }

    @Test
    public void addRequest_getTicket()
    {
        // When
        String ticket = queue.store(null);
    }

    @Test
    public void turnInTicket_getRequest()
    {
        // Given
        Request request = mock(Request.class);
        String ticket = queue.store(request);

        // When
        Request result = queue.restoreRequest(ticket);

        // Then
        assertThat(result, equalTo(request));
    }

    @Test
    public void turnInTicket_emptyQueue_getNull()
    {
        // Given
        String invalidTicket = "Invalid";

        // When
        Request result = queue.restoreRequest(invalidTicket);

        // Then
        assertThat(result, is(nullValue(Request.class)));
    }

    @Test
    public void turnInTicket_invalidTicket_getNull()
    {
        // Given
        Request someRequest = mock(Request.class);
        String invalidTicket = "Invalid";

        // When
        queue.store(someRequest);
        Request result = queue.restoreRequest(invalidTicket);

        // Then
        assertThat(result, is(nullValue(Request.class)));
    }

    @Test
    public void turnInTicket_ticketAlreadyTurnedIn_getNull()
    {
        // Given
        Request someRequest = mock(Request.class);
        String ticket = queue.store(someRequest);

        // When
        queue.restoreRequest(ticket);
        Request result = queue.restoreRequest(ticket);

        // Then
        assertThat(result, is(nullValue(Request.class)));
    }
}