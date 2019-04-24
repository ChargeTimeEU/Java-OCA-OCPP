package eu.chargetime.ocpp.test;

import static eu.chargetime.ocpp.utilities.TestUtilities.join;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

import eu.chargetime.ocpp.JSONCommunicator;
import eu.chargetime.ocpp.Transmitter;
import eu.chargetime.ocpp.model.TestModel;
import eu.chargetime.ocpp.model.core.BootNotificationConfirmation;
import eu.chargetime.ocpp.model.core.BootNotificationRequest;
import eu.chargetime.ocpp.model.core.RegistrationStatus;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/*
ChargeTime.eu - Java-OCA-OCPP
Copyright (C) 2015-2016 Thomas Volden <tv@chargetime.eu>

MIT License

Copyright (C) 2016-2018 Thomas Volden

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
@RunWith(MockitoJUnitRunner.class)
public class JSONCommunicatorTest {
  private JSONCommunicator communicator;

  @Mock private Transmitter transmitter;

  @Before
  public void setup() {
    communicator = new JSONCommunicator(transmitter);
  }

  @Test
  public void unpackPayload_emptyPayload_returnRequestedType() throws Exception {
    // Given
    String payload = "{}";
    Class<?> type = BootNotificationRequest.class;

    // When
    Object result = communicator.unpackPayload(payload, type);

    // Then
    assertThat(result, instanceOf(type));
  }

  @Test
  public void unpackPayload_aStringPayload_returnsTestModelWithAString() throws Exception {
    // Given
    String aString = "Some string";
    String payload = "{\"stringTest\":\"%s\"}";

    // When
    TestModel model = communicator.unpackPayload(String.format(payload, aString), TestModel.class);

    // Then
    assertThat(model.getStringTest(), equalTo(aString));
  }

  @Test
  public void unpackPayload_aCalendarPayload_returnsTestModelWithACalendar() throws Exception {
    // Given
    String aCalendar = "2016-04-28T07:16:11.988Z";
    String payload = "{\"calendarTest\":\"%s\"}";

    Calendar someDate =
        new Calendar.Builder()
            .setDate(2016, 03, 28)
            .setTimeOfDay(07, 16, 11, 988)
            .setTimeZone(TimeZone.getTimeZone("GMT+00:00"))
            .build();

    // When
    TestModel model =
        communicator.unpackPayload(String.format(payload, aCalendar), TestModel.class);

    // Then
    assertThat(model.getCalendarTest().compareTo(someDate), is(0));
  }

  @Test
  public void unpackPayload_anIntegerPayload_returnsTestModelWithAnInteger() throws Exception {
    // Given
    Integer anInteger = 1337;
    String payload = "{\"integerTest\":%d}";

    // When
    TestModel model =
        communicator.unpackPayload(String.format(payload, anInteger), TestModel.class);

    // Then
    assertThat(model.getIntegerTest(), equalTo(anInteger));
  }

  @Test
  public void unpackPayload_aGenericIntPayload_returnsTestModelWithAGenericInt() throws Exception {
    // Given
    int anInteger = 1337;
    String payload = "{\"intTest\":%d}";

    // When
    TestModel model =
        communicator.unpackPayload(String.format(payload, anInteger), TestModel.class);

    // Then
    assertThat(model.getIntTest(), equalTo(anInteger));
  }

  @Test
  public void unpackPayload_aLongPayload_returnsTestModelWithALong() throws Exception {
    // Given
    Long aLong = 1337L;
    String payload = "{\"longTest\":%d}";

    // When
    TestModel model = communicator.unpackPayload(String.format(payload, aLong), TestModel.class);

    // Then
    assertThat(model.getLongTest(), equalTo(aLong));
  }

  @Test
  public void unpackPayload_aGenericLongPayload_returnsTestModelWithAGenericLong()
      throws Exception {
    // Given
    long aLong = 1337;
    String payload = "{\"genericLongTest\":%d}";

    // When
    TestModel model = communicator.unpackPayload(String.format(payload, aLong), TestModel.class);

    // Then
    assertThat(model.getGenericLongTest(), equalTo(aLong));
  }

  @Test
  public void unpackPayload_aDoublePayload_returnsTestModelWithADouble() throws Exception {
    // Given
    Double aDouble = 13.37D;
    String payload = "{\"doubleTest\":%f}";

    // When
    TestModel model =
        communicator.unpackPayload(String.format(Locale.US, payload, aDouble), TestModel.class);

    // Then
    assertThat(model.getDoubleTest(), equalTo(aDouble));
  }

  @Test
  public void unpackPayload_aGenericDoublePayload_returnsTestModelWithAGenericDouble()
      throws Exception {
    // Given
    double aDouble = 13.37;
    String payload = "{\"genericDoubleTest\":%f}";

    // When
    TestModel model =
        communicator.unpackPayload(String.format(Locale.US, payload, aDouble), TestModel.class);

    // Then
    assertThat(model.getGenericDoubleTest(), equalTo(aDouble));
  }

  @Test
  public void unpackPayload_aBooleanPayload_returnsTestModelWithABoolean() throws Exception {
    // Given
    Boolean aBoolean = false;
    String payload = "{\"booleanTest\":%b}";

    // When
    TestModel model = communicator.unpackPayload(String.format(payload, aBoolean), TestModel.class);

    // Then
    assertThat(model.getBooleanTest(), equalTo(aBoolean));
  }

  @Test
  public void unpackPayload_aGenericBooleanPayload_returnsTestModelWithAGenericBoolean()
      throws Exception {
    // Given
    boolean aBoolean = false;
    String payload = "{\"genericBooleanTest\":%b}";

    // When
    TestModel model = communicator.unpackPayload(String.format(payload, aBoolean), TestModel.class);

    // Then
    assertThat(model.isGenericBoleanTest(), equalTo(aBoolean));
  }

  @Test
  public void unpackPayload_anObjectPayload_returnsTestModelWithAnObject() throws Exception {
    // Given
    String payload = "{\"objectTest\":{}}";

    // When
    TestModel model = communicator.unpackPayload(payload, TestModel.class);

    // Then
    assertThat(model.getObjectTest(), instanceOf(TestModel.class));
  }

  @Test
  public void unpackPayload_anArrayOfInts_returnsTestModelWithAnArrayOfInts() throws Exception {
    // Given
    Integer[] anArray = {1, 2, 3};
    String payload = "{\"arrayTest\": [%s]}";

    // When
    TestModel model =
        communicator.unpackPayload(String.format(payload, join(",", anArray)), TestModel.class);

    // Then
    assertThat(model.getArrayTest(), equalTo(anArray));
  }

  @Test
  public void unpackPayload_bootNotificationCallResultPayload_returnBootNotificationConfirmation()
      throws Exception {
    // Given
    String currentTime = "2016-04-28T07:16:11.988Z";
    Calendar someDate =
        new Calendar.Builder()
            .setDate(2016, 03, 28)
            .setTimeOfDay(07, 16, 11, 988)
            .setTimeZone(TimeZone.getTimeZone("GMT+00:00"))
            .build();

    int interval = 300;
    RegistrationStatus status = RegistrationStatus.Accepted;
    String payload = "{\"currentTime\": \"%s\", \"interval\": %d, \"status\": \"%s\"}";
    Class<?> type = BootNotificationConfirmation.class;

    // When
    Object result =
        communicator.unpackPayload(String.format(payload, currentTime, interval, status), type);

    // Then
    assertThat(result, instanceOf(type));
    assertThat(((BootNotificationConfirmation) result).getCurrentTime().compareTo(someDate), is(0));
    assertThat(((BootNotificationConfirmation) result).getInterval(), is(interval));
    assertThat(((BootNotificationConfirmation) result).getStatus(), is(status));
  }

  @Test
  public void pack_bootNotificationRequest_returnsBootNotificationRequestPayload() {
    // Given
    String expected =
        "{\"chargePointVendor\":\"VendorX\",\"chargePointModel\":\"SingleSocketCharger\"}";
    BootNotificationRequest request = new BootNotificationRequest("VendorX", "SingleSocketCharger");

    // When
    Object payload = communicator.packPayload(request);

    // Then
    assertThat(payload, equalTo(expected));
  }

  @Test
  public void pack_bootNotificationConfirmation_returnsBootNotificationConfirmationPayload()
      throws Exception {
    // Given
    String expected =
        "{\"currentTime\":\"2016-04-28T06:41:13Z\",\"interval\":300,\"status\":\"Accepted\"}";
    BootNotificationConfirmation confirmation = new BootNotificationConfirmation();
    confirmation.setCurrentTime(createDateTimeInMillis(1461825673720L));
    confirmation.setInterval(300);
    confirmation.setStatus(RegistrationStatus.Accepted);

    // When
    Object payload = communicator.packPayload(confirmation);

    // Then
    assertThat(payload, equalTo(expected));
  }

  @Test
  public void disconnect_disconnects() {
    // When
    communicator.disconnect();

    // Then
    verify(transmitter, times(1)).disconnect();
  }

  @Test
  public void sendError_transmitsError() throws Exception {
    // Given
    String errorCode = "NotImplemented";
    String errorDescription = "Requested Action is not known by receiver";

    // When
    communicator.sendCallError(null, null, errorCode, errorDescription);

    // Then
    verify(transmitter, times(1)).send(anyString());
  }

  private Calendar createDateTimeInMillis(long dateInMillis) {
    Calendar dateTime = new GregorianCalendar(TimeZone.getTimeZone("GMT+00:00"));
    dateTime.setTimeInMillis(dateInMillis);
    return dateTime;
  }
}
