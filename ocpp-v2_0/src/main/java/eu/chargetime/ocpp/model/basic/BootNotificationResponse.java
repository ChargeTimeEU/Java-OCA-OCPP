package eu.chargetime.ocpp.model.basic;
/*
    ChargeTime.eu - Java-OCA-OCPP
    
    MIT License

    Copyright (C) 2018 Thomas Volden <tv@chargetime.eu>

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

import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.basic.types.RegistrationStatusEnumType;
import eu.chargetime.ocpp.model.validation.RequiredValidator;
import eu.chargetime.ocpp.model.validation.Validator;

import java.util.Calendar;

/**
 * sent by the CSMS to the Charging Station in response to a {@link BootNotificationRequest}.
 */
public class BootNotificationResponse implements Confirmation {
    private Validator<Object> validator = new RequiredValidator();

    private Calendar currentTime;
    private int interval;
    private RegistrationStatusEnumType status;

    /**
     * This contains the CSMS’s current time.
     *
     * @return {@link Calendar}
     */
    public Calendar getCurrentTime() {
        return currentTime;
    }

    /**
     * Required. This contains the CSMS’s current time.
     *
     * @param currentTime {@link Calendar}
     */
    public void setCurrentTime(Calendar currentTime) {
        validator.validate(currentTime);
        this.currentTime = currentTime;
    }

    /**
     * When Status is Accepted, this contains the heartbeat interval in seconds.
     * If the CSMS returns something other than Accepted, the value of the interval field
     * indicates the minimum wait time before sending a next BootNotification request.
     *
     * @return integer
     */
    public int getInterval() {
        return interval;
    }

    /**
     * Required. When Status is Accepted, this contains the heartbeat interval in seconds.
     * If the CSMS returns something other than Accepted, the value of the interval field
     * indicates the minimum wait time before sending a next BootNotification request.
     *
     * @param interval integer
     */
    public void setInterval(int interval) {
        this.interval = interval;
    }

    /**
     * This contains whether the Charging Station has been registered within the CSMS.
     *
     * @return {@link RegistrationStatusEnumType}.
     */
    public RegistrationStatusEnumType getStatus() {
        return status;
    }

    /**
     * Required. This contains whether the Charging Station has been registered within the CSMS.
     *
     * @param status {@link RegistrationStatusEnumType}.
     */
    public void setStatus(RegistrationStatusEnumType status) {
        validator.validate(status);
        this.status = status;
    }

    @Override
    public boolean validate() {
        return false;
    }
}
