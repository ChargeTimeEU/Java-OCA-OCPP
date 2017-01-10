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

    @Override
    public <T> T unpackPayload(Object payload, Class<T> type) throws Exception {
        return null;
    }

    @Override
    public Object packPayload(Object payload) {
        return null;
    }

    @Override
    protected Object makeCallResult(String uniqueId, Object payload) {
        return null;
    }

    @Override
    protected Object makeCall(String uniqueId, String action, Object payload) {
        SOAPMessage message = null;
        try {
            MessageFactory messageFactory = MessageFactory.newInstance();
            SOAPFactory soapFactory = SOAPFactory.newInstance();

            message = messageFactory.createMessage();

            SOAPHeader soapHeader = message.getSOAPHeader();

            // Set chargeBoxIdentity
            SOAPHeaderElement chargeBoxIdentityHeader = soapHeader.addHeaderElement(soapFactory.createName("chargeBoxIdentity", "cs", "urn://Ocpp/Cs/2015/10/"));
            chargeBoxIdentityHeader.setMustUnderstand(true);
            chargeBoxIdentityHeader.setValue(chargeBoxIdentity);

            // Set Action
            SOAPHeaderElement actionHeader = soapHeader.addHeaderElement(soapFactory.createName("Action", "wsa5", ""));
            actionHeader.setMustUnderstand(true);
            actionHeader.setValue(String.format("/%s", action));

            // Set MessageID
            SOAPHeaderElement messageIDHeader = soapHeader.addHeaderElement(soapFactory.createName("MessageID", "wsa5", ""));
            messageIDHeader.setMustUnderstand(true);
            messageIDHeader.setValue(uniqueId);

            /*
            // Set RelatesTo
            SOAPHeaderElement relatesToHeader = soapHeader.addHeaderElement(soapFactory.createName("RelatesTo", "wsa5", ""));
            relatesToHeader.setValue(uniqueId);
            */

            // Set From
            SOAPHeaderElement fromHeader = soapHeader.addHeaderElement(soapFactory.createName("From", "wsa5", ""));
            fromHeader.setValue(fromUrl);

            // Set ReplyTo
            SOAPHeaderElement replyToHeader = soapHeader.addHeaderElement(soapFactory.createName("ReplyTo", "wsa5", ""));
            replyToHeader.setMustUnderstand(true);
            SOAPElement addressElement = replyToHeader.addChildElement(soapFactory.createName("Address", "wsa5", ""));
            addressElement.setValue("http://www.w3.org/2005/08/addressing/anonymous");

            // Set To
            SOAPHeaderElement toHeader = soapHeader.addHeaderElement(soapFactory.createName("To", "wsa5", ""));
            toHeader.setMustUnderstand(true);
            toHeader.setValue(toUrl);

        } catch (SOAPException e) {
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
