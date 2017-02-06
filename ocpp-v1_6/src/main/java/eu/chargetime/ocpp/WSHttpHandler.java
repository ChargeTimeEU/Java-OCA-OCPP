package eu.chargetime.ocpp;
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

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class WSHttpHandler implements HttpHandler {
    private String wsdlResourceName;
    private WSHttpHandlerEvents events;

    public WSHttpHandler(String wsdlResourceName, WSHttpHandlerEvents events) {
        this.wsdlResourceName = wsdlResourceName;
        this.events = events;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        if ("wsdl".equals(httpExchange.getRequestURI().getQuery())) {
            sendWSDL(httpExchange);
        } else {
            SOAPMessage request = parse(httpExchange.getRequestBody());
            SOAPMessage confirmation = events.incomingRequest(request);
            OutputStream responseStream = httpExchange.getResponseBody();
            try {
                httpExchange.getResponseHeaders().add("Content-Type", "text/xml; charset=utf-8");
                httpExchange.sendResponseHeaders(200, 0);
                confirmation.writeTo(responseStream);
            } catch (SOAPException e) {
                httpExchange.sendResponseHeaders(500, 0);
                e.printStackTrace();
            }
            responseStream.close();
        }
    }

    private SOAPMessage parse(InputStream request) throws IOException {
        SOAPMessage message = null;
        try {
            MessageFactory messageFactory = MessageFactory.newInstance();
            message = messageFactory.createMessage(new MimeHeaders(), request);
        } catch (SOAPException e) {
            e.printStackTrace();
        }
        return message;
    }

    private void sendWSDL(HttpExchange httpExchange) throws IOException {
        InputStream wsdl = getClass().getClassLoader().getResourceAsStream(wsdlResourceName);
        httpExchange.sendResponseHeaders(200, 0);
        OutputStream os = httpExchange.getResponseBody();

        byte[] buffer = new byte[2048];
        int read;
        while ((read = wsdl.read(buffer)) != -1)
            os.write(buffer, 0, read);

        os.flush();
        os.close();
    }
}