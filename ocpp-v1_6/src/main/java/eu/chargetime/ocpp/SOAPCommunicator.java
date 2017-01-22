package eu.chargetime.ocpp;/*
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

import eu.chargetime.ocpp.model.Message;
import org.w3c.dom.Document;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.*;

public class SOAPCommunicator extends Communicator {


    private final String chargeBoxIdentity;
    private final String fromUrl;
    private String toUrl;

    public SOAPCommunicator(String chargeBoxIdentity, String fromUrl, Radio radio) {
        super(radio);

        this.chargeBoxIdentity = chargeBoxIdentity;
        this.fromUrl = fromUrl;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T unpackPayload(Object payload, Class<T> type) {
        T output = null;
        try {
            Document input = (Document) payload;
            Unmarshaller unmarshaller = JAXBContext.newInstance(type).createUnmarshaller();
            output = (T) unmarshaller.unmarshal(input);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return output;
    }

    @Override
    public Object packPayload(Object payload) {
        Document document = null;
        try {
            Marshaller marshaller = JAXBContext.newInstance(payload.getClass()).createMarshaller();
            document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            marshaller.marshal(payload, document);
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return document;
    }

    @Override
    protected Object makeCallResult(String uniqueId, String action, Object payload) {
        return createMessage(uniqueId, action, (Document) payload, true);
    }

    @Override
    protected Object makeCall(String uniqueId, String action, Object payload) {
        return createMessage(uniqueId, action, (Document) payload, false);
    }

    private Object createMessage(String uniqueId, String action, Document payload, boolean isResponse) {
        SOAPMessage message = null;

        try {
            MessageFactory messageFactory = MessageFactory.newInstance();
            SOAPFactory soapFactory = SOAPFactory.newInstance();

            message = messageFactory.createMessage();
            message.setProperty(SOAPMessage.WRITE_XML_DECLARATION, "true");

            SOAPHeader soapHeader = message.getSOAPHeader();

            String prefix = "wsa";
            String namespace = "http://schemas.xmlsoap.org/ws/2004/08/addressing";

            // Set chargeBoxIdentity
            SOAPHeaderElement chargeBoxIdentityHeader = soapHeader.addHeaderElement(soapFactory.createName("chargeBoxIdentity", "cs", "urn://Ocpp/Cs/2015/10/"));
            chargeBoxIdentityHeader.setMustUnderstand(true);
            chargeBoxIdentityHeader.setValue(chargeBoxIdentity);

            // Set Action
            SOAPHeaderElement actionHeader = soapHeader.addHeaderElement(soapFactory.createName("Action", prefix, namespace));
            actionHeader.setMustUnderstand(true);
            actionHeader.setValue(String.format("/%s", action));

            // Set MessageID
            SOAPHeaderElement messageIDHeader = soapHeader.addHeaderElement(soapFactory.createName("MessageID", prefix, namespace));
            messageIDHeader.setMustUnderstand(true);
            messageIDHeader.setValue(uniqueId);

            // Set RelatesTo
            if (isResponse) {
                SOAPHeaderElement relatesToHeader = soapHeader.addHeaderElement(soapFactory.createName("RelatesTo", prefix, namespace));
                relatesToHeader.setValue(uniqueId);
            }

            // Set From
            SOAPHeaderElement fromHeader = soapHeader.addHeaderElement(soapFactory.createName("From", prefix, namespace));
            fromHeader.setValue(fromUrl);

            // Set ReplyTo
            SOAPHeaderElement replyToHeader = soapHeader.addHeaderElement(soapFactory.createName("ReplyTo", prefix, namespace));
            replyToHeader.setMustUnderstand(true);
            SOAPElement addressElement = replyToHeader.addChildElement(soapFactory.createName("Address", prefix, namespace));
            addressElement.setValue("http://www.w3.org/2005/08/addressing/anonymous");

            // Set To
            SOAPHeaderElement toHeader = soapHeader.addHeaderElement(soapFactory.createName("To", prefix, namespace));
            toHeader.setMustUnderstand(true);
            toHeader.setValue(toUrl);

            message.getSOAPBody().addDocument(payload);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return message;
    }

    @Override
    protected Object makeCallError(String uniqueId, String errorCode, String errorDescription) {
        return null;
    }

    @Override
    protected Message parse(Object message) {
        return null;
    }

    public String getToUrl() {
        return toUrl;
    }

    public void setToUrl(String toUrl) {
        this.toUrl = toUrl;
    }
}
