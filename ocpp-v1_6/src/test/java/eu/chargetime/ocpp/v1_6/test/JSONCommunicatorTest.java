package eu.chargetime.ocpp.v1_6.test;

import eu.chargetime.ocpp.JSONCommunicator;
import eu.chargetime.ocpp.Transmitter;
import eu.chargetime.ocpp.model.BootNotificationConfirmation;
import eu.chargetime.ocpp.model.BootNotificationRequest;
import eu.chargetime.ocpp.model.test.TestModel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.*;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
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
    public void unpack_emptyPayload_returnRequestedType() {
        // Given
        String payload = "{}";
        Class<?> type = BootNotificationRequest.class;

        // When
        Object result = communicator.unpackPayload(payload, type);

        // Then
        assertThat(result, instanceOf(type));
    }

    @Test
    public void unpack_aStringPayload_returnsTestModelWithAString() {
        // Given
        String aString = "Some string";
        String payload = "{\"stringTest\":\"%s\"}";

        // When
        TestModel model = communicator.unpackPayload(String.format(payload, aString), TestModel.class);

        // Then
        assertThat(model.getStringTest(), equalTo(aString));
    }

    // TODO find good solution for date compare
    public void unpack_aCalendarPayload_returnsTestModelWithACalendar() {
        // Given
        String aCalendar = "2016-04-28T07:16:11.988Z";
        String payload = "{\"calendarTest\":\"%s\"}";

        // When
        TestModel model = communicator.unpackPayload(String.format(payload, aCalendar), TestModel.class);

        // Then
        assertThat(model.getCalendarTest().getTime(), is(new Date(aCalendar)));
    }

    @Test
    public void unpack_anIntegerPayload_returnsTestModelWithAnInteger() {
        // Given
        Integer anInteger = 1337;
        String payload = "{\"integerTest\":%d}";

        // When
        TestModel model = communicator.unpackPayload(String.format(payload, anInteger), TestModel.class);

        // Then
        assertThat(model.getIntegerTest(), equalTo(anInteger));
    }

    @Test
    public void unpack_aGenericIntPayload_returnsTestModelWithAGenericInt() {
        // Given
        int anInteger = 1337;
        String payload = "{\"intTest\":%d}";

        // When
        TestModel model = communicator.unpackPayload(String.format(payload, anInteger), TestModel.class);

        // Then
        assertThat(model.getIntTest(), equalTo(anInteger));
    }

    @Test
    public void unpack_aLongPayload_returnsTestModelWithALong() {
        // Given
        Long aLong = 1337L;
        String payload = "{\"longTest\":%d}";

        // When
        TestModel model = communicator.unpackPayload(String.format(payload, aLong), TestModel.class);

        // Then
        assertThat(model.getLongTest(), equalTo(aLong));
    }

    @Test
    public void unpack_aGenericLongPayload_returnsTestModelWithAGenericLong() {
        // Given
        long aLong = 1337;
        String payload = "{\"genericLongTest\":%d}";

        // When
        TestModel model = communicator.unpackPayload(String.format(payload, aLong), TestModel.class);

        // Then
        assertThat(model.getGenericLongTest(), equalTo(aLong));
    }

    @Test
    public void unpack_aDoublePayload_returnsTestModelWithADouble() {
        // Given
        Double aDouble = 13.37D;
        String payload = "{\"doubleTest\":%f}";

        // When
        TestModel model = communicator.unpackPayload(String.format(payload, aDouble), TestModel.class);

        // Then
        assertThat(model.getDoubleTest(), equalTo(aDouble));
    }

    @Test
    public void unpack_aGenericDoublePayload_returnsTestModelWithAGenericDouble() {
        // Given
        double aDouble = 13.37;
        String payload = "{\"genericDoubleTest\":%f}";

        // When
        TestModel model = communicator.unpackPayload(String.format(payload, aDouble), TestModel.class);

        // Then
        assertThat(model.getGenericDoubleTest(), equalTo(aDouble));
    }

    @Test
    public void unpack_aBooleanPayload_returnsTestModelWithABoolean() {
        // Given
        Boolean aBoolean = false;
        String payload = "{\"booleanTest\":%b}";

        // When
        TestModel model = communicator.unpackPayload(String.format(payload, aBoolean), TestModel.class);

        // Then
        assertThat(model.getBooleanTest(), equalTo(aBoolean));
    }

    @Test
    public void unpack_aGenericBooleanPayload_returnsTestModelWithAGenericBoolean() {
        // Given
        boolean aBoolean = false;
        String payload = "{\"genericBooleanTest\":%b}";

        // When
        TestModel model = communicator.unpackPayload(String.format(payload, aBoolean), TestModel.class);

        // Then
        assertThat(model.isGenericBoleanTest(), equalTo(aBoolean));
    }

    @Test
    public void unpack_anObjectPayload_returnsTestModelWithAnObject() {
        // Given
        String payload = "{\"objectTest\":{}}";

        // When
        TestModel model = communicator.unpackPayload((payload), TestModel.class);

        // Then
        assertThat(model.getObjectTest(), instanceOf(TestModel.class));
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
/*
    private Date createDate(int year, int month, int day, int hours, int minuts, int seconds, int milliseconds) {
        Calendar dateTime = new GregorianCalendar(TimeZone.getTimeZone("GMT+00:00"));
        dateTime.set(year, month, day, hours, minuts, seconds);
        dateTime.getTimeInMillis()
    }
*/

}