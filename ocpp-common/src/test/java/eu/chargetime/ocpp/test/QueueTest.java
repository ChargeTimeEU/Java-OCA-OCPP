package eu.chargetime.ocpp.test;

import eu.chargetime.ocpp.Queue;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

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
        String ticket = queue.store("");
    }

    @Test
    public void turnInTicket_getRequest()
    {
        // Given
        String request = "test";
        String ticket = queue.store(request);

        // When
        String result = queue.restoreRequest(ticket);

        // Then
        assertThat(result, equalTo(request));
    }

    @Test
    public void turnInTicket_emptyQueue_getNull()
    {
        // Given
        String invalidTicket = "Invalid";

        // When
        String result = queue.restoreRequest(invalidTicket);

        // Then
        assertThat(result, is(nullValue(String.class)));
    }

    @Test
    public void turnInTicket_invalidTicket_getNull()
    {
        // Given
        String someRequest = "someRequest";
        String invalidTicket = "Invalid";

        // When
        queue.store(someRequest);
        String result = queue.restoreRequest(invalidTicket);

        // Then
        assertThat(result, is(nullValue(String.class)));
    }

    @Test
    public void turnInTicket_ticketAlreadyTurnedIn_getNull()
    {
        // Given
        String someRequest = "someRequest";
        String ticket = queue.store(someRequest);

        // When
        queue.restoreRequest(ticket);
        String result = queue.restoreRequest(ticket);

        // Then
        assertThat(result, is(nullValue(String.class)));
    }
}