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

import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.core.BootNotificationConfirmation;
import eu.chargetime.ocpp.model.core.RegistrationStatus;

import java.util.Timer;
import java.util.TimerTask;

public class TimeoutSession extends Session {

    private static final long INITIAL_TIMEOUT = 1000 * 60 * 5;
    private SessionEvents eventHandler;
    private TimeoutTimer timeoutTimer;
    private Communicator communicator;

    /**
     * Handles required injections.
     *
     * @param communicator send and receive messages.
     * @param queue        store and restore requests based on unique ids.
     */
    public TimeoutSession(Communicator communicator, Queue queue) {
        super(communicator, queue);
        this.communicator = communicator;
        timeoutTimer = new TimeoutTimer(INITIAL_TIMEOUT);
    }

    @Override
    public void open(String uri, SessionEvents eventHandler) {
        this.eventHandler = eventHandler;
        SessionEvents events = createEventHandler(eventHandler);
        super.open(uri, events);
    }

    @Override
    public void accept(SessionEvents eventHandler) {
        this.eventHandler = eventHandler;
        SessionEvents events = createEventHandler(eventHandler);
        super.accept(events);
    }

    private SessionEvents createEventHandler(SessionEvents eventHandler) {
        return new SessionEvents() {
            @Override
            public Feature findFeatureByAction(String action) {
                return eventHandler.findFeatureByAction(action);
            }

            @Override
            public Feature findFeatureByRequest(Request request) {
                return eventHandler.findFeatureByRequest(request);
            }

            @Override
            public void handleConfirmation(String uniqueId, Confirmation confirmation) {
                timeoutTimer.reset();
                eventHandler.handleConfirmation(uniqueId, confirmation);
            }

            @Override
            public Confirmation handleRequest(Request request) {
                timeoutTimer.reset();
                Confirmation confirmation = eventHandler.handleRequest(request);

                if (confirmation instanceof BootNotificationConfirmation) {
                    BootNotificationConfirmation bootNotificationConfirmation = (BootNotificationConfirmation) confirmation;
                    if (bootNotificationConfirmation.getStatus() == RegistrationStatus.Accepted) {
                        timeoutTimer.setTimeout(bootNotificationConfirmation.getInterval() * 1000);
                        timeoutTimer.reset();
                    }
                }
                return confirmation;
            }

            @Override
            public void handleError(String uniqueId, String errorCode, String errorDescription, Object payload) {
                eventHandler.handleError(uniqueId, errorCode, errorDescription, payload);
            }

            @Override
            public void handleConnectionClosed() {
                eventHandler.handleConnectionClosed();
                timeoutTimer.end();
            }

            @Override
            public void handleConnectionOpened() {
                eventHandler.handleConnectionOpened();
                timeoutTimer.begin();
            }
        };
    }

    private void timeout() {
        communicator.disconnect();
        eventHandler.handleConnectionClosed();
    }

    private class TimeoutTimer extends Timer {

        private TimerTask timerTask;
        private long timeout;

        public TimeoutTimer(long timeout) {
            this.timeout = timeout;
        }

        public void setTimeout(long timeout) {
            this.timeout = timeout;
        }

        public void begin() {
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    timeout();
                }
            };
            this.schedule(timerTask, timeout);
        }

        public void end()
        {
            timerTask.cancel();
        }

        public void reset() {
            end();
            begin();
        }
    }
}
