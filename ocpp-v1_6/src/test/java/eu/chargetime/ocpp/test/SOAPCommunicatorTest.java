package eu.chargetime.ocpp.test;

import eu.chargetime.ocpp.SOAPCommunicator;
import eu.chargetime.ocpp.Transmitter;
import eu.chargetime.ocpp.model.core.BootNotificationRequest;
import eu.chargetime.ocpp.utilities.TestUtilities;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.w3c.dom.Document;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

/*
    ChargeTime.eu - Java-OCA-OCPP
    
    MIT License

    Copyright (C) 2016 Thomas Volden <tv@chargetime.eu>

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
public class SOAPCommunicatorTest extends TestUtilities {

    private SOAPCommunicator communicator;
    private String chargeBoxIdentity = "testIdentity";
    private String fromUrl = "http://localhost";

    @Mock
    Transmitter transmitter = mock(Transmitter.class);

    @Before
    public void setup() {
        communicator = new SOAPCommunicator(chargeBoxIdentity, fromUrl, transmitter);
    }

    @Test
    public void pack_bootNotificationRequest_returnsBootNotificationRequestPayload() {
        // Given
        String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><bootNotificationRequest xmlns=\"urn://Ocpp/Cp/2015/10\"><chargePointModel>SingleSocketCharger</chargePointModel><chargePointVendor>VendorX</chargePointVendor></bootNotificationRequest>";

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


}