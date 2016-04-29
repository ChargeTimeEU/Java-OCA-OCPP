package eu.chargetime.ocpp.v1_6.test;

import eu.chargetime.ocpp.JSONCommunicator;
import eu.chargetime.ocpp.Transmitter;
import eu.chargetime.ocpp.model.BootNotificationConfirmation;
import eu.chargetime.ocpp.model.BootNotificationRequest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.*;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.mock;

/**
 * Created by Thomas Volden on 27-Apr-16.
 */
public class JSONCommunicatorTest
{
    private JSONCommunicator communicator;

    @Mock
    Transmitter transmitter = mock(Transmitter.class);

    @Before
    public void setup() {
        communicator = new JSONCommunicator(transmitter);
    }

    @Test
    public void unpack_bootNotificationCallPayload_returnBootNotificationRequest() {
        // Given
        String payload = "{\"chargePointModel\":\"SingleSocketCharger\",\"chargePointVendor\":\"VendorX\"}";
        Class<?> type = BootNotificationRequest.class;

        // When
        Object result = communicator.unpackPayload(payload, type);

        // Then
        assertThat(result, instanceOf(type));
    }

    @Test
    public void unpack_bootNotificationCallResultPayload_returnBootNotificationConfirmation() {
        // Given
        String currentType = "2016-04-28T07:16:11.988Z";
        int interval = 300;
        String status = "Accepted";
        String payload = "{\"currentTime\": \"%s\", \"interval\": %d, \"status\": \"%s\"}";
        Class<?> type = BootNotificationConfirmation.class;

        // When
        Object result = communicator.unpackPayload(String.format(payload, currentType, interval, status), type);

        // Then
        assertThat(result, instanceOf(type));
        assertThat(((BootNotificationConfirmation)result).getCurrentTime(), equalTo(currentType));
        assertThat(((BootNotificationConfirmation)result).getInterval(), is(interval));
        assertThat(((BootNotificationConfirmation)result).getStatus(), is(status));
    }

    @Test
    public void pack_bootNotificationRequest_returnsBootNotificationRequestPayload() {
        // Given
        String expected = "{\"chargePointModel\":\"SingleSocketCharger\",\"chargePointVendor\":\"VendorX\"}";
        BootNotificationRequest request = new BootNotificationRequest("VendorX", "SingleSocketCharger");

        // When
        String payload = communicator.packPayload(request);

        // Then
        assertThat(payload, equalTo(expected));
    }

    @Test
    public void pack_bootNotificationConfirmation_returnsBootNotificationConfirmationPayload() {
        // Given
        String expected = "{\"currentTime\":\"2016-04-28T06:41:13.720Z\",\"interval\":300,\"status\":\"Accepted\"}";
        BootNotificationConfirmation confirmation = new BootNotificationConfirmation();
        confirmation.setCurrentTime(createDateTimeInMillis(1461825673720L));
        confirmation.setInterval(300);
        confirmation.setStatus("Accepted");

        // When
        String payload = communicator.packPayload(confirmation);

        // Then
        assertThat(payload, equalTo(expected));
    }

    private Calendar createDateTimeInMillis(long dateInMillis) {
        Calendar dateTime = new GregorianCalendar(TimeZone.getTimeZone("GMT+00:00"));
        dateTime.setTimeInMillis(dateInMillis);
        return dateTime;
    }

}