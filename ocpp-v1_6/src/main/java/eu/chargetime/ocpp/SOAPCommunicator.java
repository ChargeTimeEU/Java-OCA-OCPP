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

import eu.chargetime.ocpp.model.CallMessage;
import eu.chargetime.ocpp.model.CallResultMessage;
import eu.chargetime.ocpp.model.Message;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.*;

public class SOAPCommunicator extends Communicator {


    private static final String HEADER_ACTION = "Action";
    private static final String HEADER_MESSAGEID = "MessageID";
    private static final String HEADER_RELATESTO = "RelatesTo";
    private static final String HEADER_FROM = "From";
    private static final String HEADER_REPLYTO = "ReplyTo";
    private static final String HEADER_REPLYTO_ADDRESS = "Address";
    private static final String HEADER_TO = "To";
    private static final String HEADER_CHARGEBOXIDENTITY = "chargeBoxIdentity";


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
        return createMessage(uniqueId, String.format("%sResponse", action), (Document) payload, true);
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
            SOAPHeaderElement chargeBoxIdentityHeader = soapHeader.addHeaderElement(soapFactory.createName(HEADER_CHARGEBOXIDENTITY, "cs", "urn://Ocpp/Cs/2015/10/"));
            chargeBoxIdentityHeader.setMustUnderstand(true);
            chargeBoxIdentityHeader.setValue(chargeBoxIdentity);

            // Set Action
            SOAPHeaderElement actionHeader = soapHeader.addHeaderElement(soapFactory.createName(HEADER_ACTION, prefix, namespace));
            actionHeader.setMustUnderstand(true);
            actionHeader.setValue(String.format("/%s", action));

            // Set MessageID
            SOAPHeaderElement messageIDHeader = soapHeader.addHeaderElement(soapFactory.createName(HEADER_MESSAGEID, prefix, namespace));
            messageIDHeader.setMustUnderstand(true);
            messageIDHeader.setValue(uniqueId);

            // Set RelatesTo
            if (isResponse) {
                SOAPHeaderElement relatesToHeader = soapHeader.addHeaderElement(soapFactory.createName(HEADER_RELATESTO, prefix, namespace));
                relatesToHeader.setValue(uniqueId);
            }

            // Set From
            SOAPHeaderElement fromHeader = soapHeader.addHeaderElement(soapFactory.createName(HEADER_FROM, prefix, namespace));
            fromHeader.setValue(fromUrl);

            // Set ReplyTo
            SOAPHeaderElement replyToHeader = soapHeader.addHeaderElement(soapFactory.createName(HEADER_REPLYTO, prefix, namespace));
            replyToHeader.setMustUnderstand(true);
            SOAPElement addressElement = replyToHeader.addChildElement(soapFactory.createName(HEADER_REPLYTO_ADDRESS, prefix, namespace));
            addressElement.setValue("http://www.w3.org/2005/08/addressing/anonymous");

            // Set To
            SOAPHeaderElement toHeader = soapHeader.addHeaderElement(soapFactory.createName(HEADER_TO, prefix, namespace));
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
        Message output = null;
        SOAPParser soapParser = new SOAPParser((SOAPMessage) message);

        if (soapParser.isAddressedToMe())
            output = soapParser.parseMessage();

        return output;
    }

    private class SOAPParser {

        private SOAPHeader soapHeader;
        private SOAPMessage soapMessage;

        public SOAPParser(SOAPMessage message) {
            try {
                soapMessage = message;
                soapHeader = message.getSOAPPart().getEnvelope().getHeader();
            } catch (SOAPException e) {
                e.printStackTrace();
            }
        }

        public Message parseMessage() {
            Message output = null;
            try {

                String relatesTo = getElementValue(HEADER_RELATESTO);
                String action = getElementValue(HEADER_ACTION);
                if (relatesTo != null && !"".equals(relatesTo) && action.endsWith("Response")) {
                    output = parseResult();
                } else {
                    output = parseCall();
                }
                output.setPayload(soapMessage.getSOAPBody().extractContentAsDocument());

            } catch (SOAPException e) {
                e.printStackTrace();
            }
            return output;
        }

        public boolean isAddressedToMe() {
            String to = getElementValue(HEADER_TO);
            String cbIdentity = getElementValue(HEADER_CHARGEBOXIDENTITY);
            return fromUrl.equals(to) && chargeBoxIdentity.equals(cbIdentity);
        }

        private CallResultMessage parseResult() {
            CallResultMessage message = new CallResultMessage();

            String id = getElementValue(HEADER_RELATESTO);
            message.setId(id);

            return message;
        }

        private CallMessage parseCall() {
            CallMessage message = new CallMessage();

            String action = getElementValue(HEADER_ACTION);
            if (action != null && !"".equals(action))
                message.setAction(action.substring(1));

            String id = getElementValue(HEADER_MESSAGEID);
            message.setId(id);

            return message;
        }

        private String getElementValue(String tagName) {
            String value = null;
            NodeList elements = soapHeader.getElementsByTagNameNS("*", tagName);

            if (elements.getLength() > 0)
                value = elements.item(0).getChildNodes().item(0).getNodeValue();

            return value;
        }
    }

    public String getToUrl() {
        return toUrl;
    }

    public void setToUrl(String toUrl) {
        this.toUrl = toUrl;
    }
}
