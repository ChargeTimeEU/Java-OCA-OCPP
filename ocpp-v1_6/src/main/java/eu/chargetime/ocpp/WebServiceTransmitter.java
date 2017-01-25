package eu.chargetime.ocpp;

import org.w3c.dom.NodeList;

import javax.xml.soap.*;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

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

public class WebServiceTransmitter implements Transmitter {
    SOAPConnection soapConnection;
    private String url;
    private RadioEvents events;
    private HashMap<String, CompletableFuture<SOAPMessage>> promises;

    public WebServiceTransmitter() {
        promises = new HashMap<>();
    }

    @Override
    public void disconnect() {
        try {
            soapConnection.close();
        } catch (SOAPException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void connect(String uri, RadioEvents events) {
        url = uri;
        this.events = events;
        try {
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            soapConnection = soapConnectionFactory.createConnection();
        } catch (SOAPException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void send(Object message) throws NotConnectedException {
        SOAPMessage soapMessage = (SOAPMessage) message;

        String relatesTo = getHeaderValue(soapMessage, "RelatesTo");
        if (relatesTo != null && promises.containsKey(relatesTo)) {
            promises.get(relatesTo).complete(soapMessage);
        } else {
            sendRequest(soapMessage);
        }
    }

    private void sendRequest(SOAPMessage request) {
        try {
            events.receivedMessage(soapConnection.call(request, url));
        } catch (SOAPException e) {
            e.printStackTrace();
        }

    }

    public CompletableFuture<SOAPMessage> relay(SOAPMessage message) {
        events.receivedMessage(message);

        CompletableFuture<SOAPMessage> promise = null;
        String uniqueID = getHeaderValue(message, "MessageID");
        if (uniqueID != null) {
            promise = new CompletableFuture<>();
            promises.put(uniqueID, promise);
        }

        return promise;
    }

    private String getHeaderValue(SOAPMessage message, String tagName) {
        String value = null;
        try {
            SOAPHeader header = message.getSOAPHeader();
            NodeList elements = header.getElementsByTagName(tagName);
            if (elements.getLength() > 0)
                value = elements.item(0).getNodeValue();
        } catch (SOAPException e) {
            e.printStackTrace();
        }
        return value;
    }

}
