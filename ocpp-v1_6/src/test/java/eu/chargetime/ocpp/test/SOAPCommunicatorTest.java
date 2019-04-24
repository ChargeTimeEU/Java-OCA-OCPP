package eu.chargetime.ocpp.test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import eu.chargetime.ocpp.SOAPCommunicator;
import eu.chargetime.ocpp.Transmitter;
import eu.chargetime.ocpp.model.SOAPHostInfo;
import eu.chargetime.ocpp.model.TestModel;
import eu.chargetime.ocpp.model.core.BootNotificationConfirmation;
import eu.chargetime.ocpp.model.core.BootNotificationRequest;
import eu.chargetime.ocpp.model.core.RegistrationStatus;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

/*
   ChargeTime.eu - Java-OCA-OCPP

   MIT License

   Copyright (C) 2016-2018 Thomas Volden <tv@chargetime.eu>

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
public class SOAPCommunicatorTest {

  private SOAPCommunicator communicator;
  private String chargeBoxIdentity = "testIdentity";
  private String fromUrl = "http://localhost";
  private String namespace = "urn://Ocpp/Cs/2015/10";

  @Mock private Transmitter transmitter;

  @Before
  public void setup() {
    SOAPHostInfo hostInfo =
        new SOAPHostInfo.Builder()
            .chargeBoxIdentity(chargeBoxIdentity)
            .fromUrl(fromUrl)
            .namespace(namespace)
            .build();
    communicator = new SOAPCommunicator(hostInfo, transmitter);
  }

  @Test
  public void unpackPayload_emptyPayload_returnRequestedType() throws Exception {
    // Given
    Document payload =
        stringToDocument(
            "<bootNotificationRequest xmlns=\"urn://Ocpp/Cs/2015/10\"></bootNotificationRequest>");
    Class<?> type = BootNotificationRequest.class;

    // When
    Object result = communicator.unpackPayload(payload, type);

    // Then
    assertThat(result, instanceOf(type));
  }

  @Test
  @Ignore("Only works for ocpp namespaces")
  public void unpackPayload_aStringPayload_returnsTestModelWithAString() throws Exception {
    // Given
    String aString = "Some string";
    String xml = "<testModel><stringTest>%s</stringTest></testModel>";
    Document payload = stringToDocument(String.format(xml, aString));

    // When
    TestModel model = communicator.unpackPayload(payload, TestModel.class);

    // Then
    assertThat(model.getStringTest(), equalTo(aString));
  }

  @Test
  @Ignore("Only works for ocpp namespaces")
  public void unpackPayload_aCalendarPayload_returnsTestModelWithACalendar() throws Exception {
    // Given
    String aCalendar = "2016-04-28T07:16:11.988Z";
    String xml = "<testModel><calendarTest>%s</calendarTest></testModel>";
    Document payload = stringToDocument(String.format(xml, aCalendar));

    Calendar someDate =
        new Calendar.Builder()
            .setDate(2016, 03, 28)
            .setTimeOfDay(07, 16, 11, 988)
            .setTimeZone(TimeZone.getTimeZone("GMT+00:00"))
            .build();

    // When
    TestModel model = communicator.unpackPayload(payload, TestModel.class);

    // Then
    assertThat(model.getCalendarTest().compareTo(someDate), is(0));
  }

  @Test
  @Ignore("Only works for ocpp namespaces")
  public void unpackPayload_anIntegerPayload_returnsTestModelWithAnInteger() throws Exception {
    // Given
    Integer anInteger = 1337;
    String xml = "<testModel><integerTest>%d</integerTest></testModel>";
    Document payload = stringToDocument(String.format(xml, anInteger));

    // When
    TestModel model = communicator.unpackPayload(payload, TestModel.class);

    // Then
    assertThat(model.getIntegerTest(), equalTo(anInteger));
  }

  @Test
  @Ignore("Only works for ocpp namespaces")
  public void unpackPayload_aGenericIntPayload_returnsTestModelWithAGenericInt() throws Exception {
    // Given
    int anInteger = 1337;
    String xml = "<testModel><intTest>%d</intTest></testModel>";
    Document payload = stringToDocument(String.format(xml, anInteger));

    // When
    TestModel model = communicator.unpackPayload(payload, TestModel.class);

    // Then
    assertThat(model.getIntTest(), equalTo(anInteger));
  }

  @Test
  @Ignore("Only works for ocpp namespaces")
  public void unpackPayload_aLongPayload_returnsTestModelWithALong() throws Exception {
    // Given
    Long aLong = 1337L;
    String xml = "<testModel><longTest>%d</longTest></testModel>";
    Document payload = stringToDocument(String.format(xml, aLong));

    // When
    TestModel model = communicator.unpackPayload(payload, TestModel.class);

    // Then
    assertThat(model.getLongTest(), equalTo(aLong));
  }

  @Test
  @Ignore("Only works for ocpp namespaces")
  public void unpackPayload_aGenericLongPayload_returnsTestModelWithAGenericLong()
      throws Exception {
    // Given
    long aLong = 1337;
    String xml = "<testModel><genericLongTest>%d</genericLongTest></testModel>";
    Document payload = stringToDocument(String.format(xml, aLong));

    // When
    TestModel model = communicator.unpackPayload(payload, TestModel.class);

    // Then
    assertThat(model.getGenericLongTest(), equalTo(aLong));
  }

  @Test
  @Ignore("Only works for ocpp namespaces")
  public void unpackPayload_aDoublePayload_returnsTestModelWithADouble() throws Exception {
    // Given
    Double aDouble = 13.37D;
    String xml = "<testModel><doubleTest>%f</doubleTest></testModel>";
    Document payload = stringToDocument(String.format(Locale.US, xml, aDouble));

    // When
    TestModel model = communicator.unpackPayload(payload, TestModel.class);

    // Then
    assertThat(model.getDoubleTest(), equalTo(aDouble));
  }

  @Test
  @Ignore("Only works for ocpp namespaces")
  public void unpackPayload_aGenericDoublePayload_returnsTestModelWithAGenericDouble()
      throws Exception {
    // Given
    double aDouble = 13.37;
    String xml = "<testModel><genericDoubleTest>%f</genericDoubleTest></testModel>";
    Document payload = stringToDocument(String.format(Locale.US, xml, aDouble));

    // When
    TestModel model = communicator.unpackPayload(payload, TestModel.class);

    // Then
    assertThat(model.getGenericDoubleTest(), equalTo(aDouble));
  }

  @Test
  @Ignore("Only works for ocpp namespaces")
  public void unpackPayload_aBooleanPayload_returnsTestModelWithABoolean() throws Exception {
    // Given
    Boolean aBoolean = false;
    String xml = "<testModel><booleanTest>%b</booleanTest></testModel>";
    Document payload = stringToDocument(String.format(xml, aBoolean));

    // When
    TestModel model = communicator.unpackPayload(payload, TestModel.class);

    // Then
    assertThat(model.getBooleanTest(), equalTo(aBoolean));
  }

  @Test
  public void unpackPayload_aGenericBooleanPayload_returnsTestModelWithAGenericBoolean()
      throws Exception {
    // Given
    boolean aBoolean = false;
    String xml = "<testModel><genericBooleanTest>%b</genericBooleanTest></testModel>";
    Document payload = stringToDocument(String.format(xml, aBoolean));

    // When
    TestModel model = communicator.unpackPayload(payload, TestModel.class);

    // Then
    assertThat(model.isGenericBoleanTest(), equalTo(aBoolean));
  }

  @Test
  @Ignore("Only works for ocpp namespaces")
  public void unpackPayload_anObjectPayload_returnsTestModelWithAnObject() throws Exception {
    // Given
    String xml = "<testModel><objectTest></objectTest></testModel>";
    Document payload = stringToDocument(xml);

    // When
    TestModel model = communicator.unpackPayload(payload, TestModel.class);

    // Then
    assertThat(model.getObjectTest(), instanceOf(TestModel.class));
  }

  @Test
  @Ignore("Only works for ocpp namespaces")
  public void unpackPayload_anArrayOfInts_returnsTestModelWithAnArrayOfInts() throws Exception {
    // Given
    Integer[] anArray = {1, 2, 3};
    String xml =
        "<testModel><arrayTest>1</arrayTest><arrayTest>2</arrayTest><arrayTest>3</arrayTest></testModel>";
    Document payload = stringToDocument(xml);

    // When
    TestModel model = communicator.unpackPayload(payload, TestModel.class);

    // Then
    assertThat(model.getArrayTest(), equalTo(anArray));
  }

  @Test
  public void unpackPayload_bootNotificationCallResultPayload_returnBootNotificationConfirmation()
      throws Exception {
    // Given
    String currentType = "2016-04-28T07:16:11.988Z";
    Calendar someDate =
        new Calendar.Builder()
            .setDate(2016, 03, 28)
            .setTimeOfDay(07, 16, 11, 988)
            .setTimeZone(TimeZone.getTimeZone("GMT+00:00"))
            .build();
    int interval = 300;
    RegistrationStatus status = RegistrationStatus.Accepted;
    String xml =
        "<bootNotificationResponse xmlns=\"urn://Ocpp/Cs/2015/10/\"><currentTime>%s</currentTime><interval>%d</interval><status>%s</status></bootNotificationResponse>";
    Document payload = stringToDocument(String.format(xml, currentType, interval, status));
    Class<?> type = BootNotificationConfirmation.class;

    // When
    Object result = communicator.unpackPayload(payload, type);

    // Then
    assertThat(result, instanceOf(type));
    assertThat(((BootNotificationConfirmation) result).getCurrentTime().compareTo(someDate), is(0));
    assertThat(((BootNotificationConfirmation) result).getStatus(), is(status));
    assertThat(((BootNotificationConfirmation) result).getInterval(), is(interval));
  }

  @Test
  public void pack_bootNotificationRequest_returnsBootNotificationRequestPayload() {
    // Given
    String expected =
        "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><bootNotificationRequest xmlns=\"urn://Ocpp/Cs/2015/10\"><chargePointVendor>VendorX</chargePointVendor><chargePointModel>SingleSocketCharger</chargePointModel></bootNotificationRequest>";

    BootNotificationRequest request = new BootNotificationRequest("VendorX", "SingleSocketCharger");

    // When
    Document payload = (Document) communicator.packPayload(request);

    // Then
    assertThat(docToString(payload), equalTo(expected));
  }

  public static String docToString(Document doc) {
    try {
      StringWriter sw = new StringWriter();
      TransformerFactory tf = TransformerFactory.newInstance();
      Transformer transformer = tf.newTransformer();
      transformer.transform(new DOMSource(doc), new StreamResult(sw));
      return sw.toString();
    } catch (Exception ex) {
      throw new RuntimeException("Error converting to String", ex);
    }
  }

  public static Document stringToDocument(String xml) throws Exception {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setNamespaceAware(true);
    DocumentBuilder db = factory.newDocumentBuilder();

    InputSource is = new InputSource();
    is.setCharacterStream(new StringReader(xml));
    return db.parse(is);
  }
}
