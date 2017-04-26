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
import eu.chargetime.ocpp.utilities.TimeoutTimer;

public class TimeoutSession extends Session {

    private TimeoutTimer timeoutTimer;

    /**
     * Handles required injections.
     *
     * @param communicator  send and receive messages.
     * @param queue         store and restore requests based on unique ids.
     * @param handleRequestAsync    toggle if requests are handled async or not.
     */
    public TimeoutSession(Communicator communicator, Queue queue, boolean handleRequestAsync) {
        super(communicator, queue, handleRequestAsync);
    }

    public void setTimeoutTimer(TimeoutTimer timeoutTimer) {
        this.timeoutTimer = timeoutTimer;
    }

    private void resetTimer(int timeoutInSec) {
        if (timeoutTimer != null)
            timeoutTimer.setTimeout(timeoutInSec * 1000);
        resetTimer();
    }

    private void resetTimer() {
        if (timeoutTimer != null)
            timeoutTimer.reset();
    }

    private void stopTimer() {
        if (timeoutTimer != null)
            timeoutTimer.end();
    }

    private void startTimer() {
        if (timeoutTimer != null)
            timeoutTimer.begin();
    }

    @Override
    public void open(String uri, SessionEvents eventHandler) {
        SessionEvents events = createEventHandler(eventHandler);
        super.open(uri, events);
    }

    @Override
    public void accept(SessionEvents eventHandler) {
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
                resetTimer();
                eventHandler.handleConfirmation(uniqueId, confirmation);
            }

            @Override
            synchronized public Confirmation handleRequest(Request request) {
                resetTimer();
                Confirmation confirmation = eventHandler.handleRequest(request);

                if (confirmation instanceof BootNotificationConfirmation) {
                    BootNotificationConfirmation bootNotification = (BootNotificationConfirmation) confirmation;
                    if (bootNotification.getStatus() == RegistrationStatus.Accepted) {
                        resetTimer(bootNotification.getInterval());
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
                stopTimer();
            }

            @Override
            public void handleConnectionOpened() {
                eventHandler.handleConnectionOpened();
                startTimer();
            }
        };
    }
}

