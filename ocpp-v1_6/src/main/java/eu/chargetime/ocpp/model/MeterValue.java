package eu.chargetime.ocpp.model;

import eu.chargetime.ocpp.PropertyConstraintException;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * ChargeTime.eu - Java-OCA-OCPP
 *
 * MIT License
 *
 * Copyright (C) 2016 Thomas Volden <tv@chargetime.eu>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
public class MeterValue implements validatable {

    private Calendar timestamp;
    private SampledValue[] sampledValue;

    @Override
    public boolean validate() {
        boolean valid = true;
        if (valid &= sampledValue != null) {
            for (SampledValue value : sampledValue)
                valid &= value.validate();
        }
        return valid;
    }

    public void setTimestamp(Calendar timestamp) throws PropertyConstraintException {
        if (timestamp == null)
            throw new PropertyConstraintException("timestamp", timestamp);

        this.timestamp = timestamp;
    }

    public String getTimestamp() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        formatter.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        return formatter.format(timestamp.getTime());
    }

    public Calendar objTimestamp() {
        return timestamp;
    }

    public void setSampledValue(SampledValue[] sampledValue) throws PropertyConstraintException {
        if (sampledValue == null)
            throw new PropertyConstraintException("sampledValue", sampledValue);

        this.sampledValue = sampledValue;
    }

    public SampledValue[] getSampledValue() {
        return sampledValue;
    }
}
