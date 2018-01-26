package eu.chargetime.ocpp;

import eu.chargetime.ocpp.model.Request;
import org.apache.logging.log4j.LogManager;

import java.util.HashMap;
import java.util.UUID;

/*
 ChargeTime.eu - Java-OCA-OCPP
 Copyright (C) 2015-2016 Thomas Volden <tv@chargetime.eu>

 MIT License

 Copyright (c) 2016-2018 Thomas Volden

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

/**
 * Class to store and restore requests based on a unique id.
 */
public class Queue
{
	private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(Queue.class);

	private HashMap<String, Request> requestQueue;

    public Queue () {
        requestQueue = new HashMap<>();
    }

    /**
     * Store a {@link Request} and get a unique identifier to fetch it later on.
     *
     * @param request the {@link Request}.
     * @return a unique identifier used to fetch the request.
     */
    public String store(Request request) {
        String ticket = UUID.randomUUID().toString();
        requestQueue.put(ticket, request);
        return ticket;
    }

    /**
     * Restore a {@link Request} using a unique identifier.
     * The identifier can only be used once.
     * If no Request was found, null is returned.
     * 
     * FIXME: use optional instead
     *
     * @param ticket    unique identifier returned when {@link Request} was initially stored.
     * @return the stored {@link Request}
     */
    public Request restoreRequest(String ticket) {
        Request request = null;
        try {
            request = requestQueue.get(ticket);
            requestQueue.remove(ticket);
        } catch (Exception ex) {
            logger.warn("restoreRequest({}) failed", ticket, ex);
        }
        return request;
    }
}
