package eu.chargetime.ocpp.model;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.utilities.ModelUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * ChargeTime.eu - Java-OCA-OCPP
 * <p>
 * MIT License
 * <p>
 * Copyright (C) 2016 Thomas Volden <tv@chargetime.eu>
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
public class StopTransactionRequest implements Request {
    private IdToken idTag;
    private Integer meterStop;
    private Calendar timestamp;
    private Integer transactionId;
    private String reason;
    private MeterValue[] transactionData;

    @Override
    public boolean validate() {
        boolean valid = true;
        valid &= meterStop != null;
        valid &= timestamp != null;
        valid &= transactionId != null;
        if (transactionData != null) {
            for (MeterValue meterValue : transactionData) {
                valid &= meterValue.validate();
            }
        }
        return valid;
    }

    public void setIdTag(IdToken idTag) {
        this.idTag = idTag;
    }

    public IdToken getIdTag() {
        return idTag;
    }

    public void setMeterStop(Integer meterStop) {
        this.meterStop = meterStop;
    }

    public Integer getMeterStop() {
        return meterStop;
    }

    public void setTimestamp(Calendar timestamp) {
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

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setReason(String reason) throws PropertyConstraintException {
        if (!isValidReason(reason))
            throw new PropertyConstraintException("reason", reason);

        this.reason = reason;
    }

    private boolean isValidReason(String reason) {
        String[] reasons = {"EmergencyStop", "EVDisconnected", "HardReset", "Local", "Other", "PowerLoss", "Reboot", "Remote", "SoftReset", "UnlockCommand", "DeAuthorized"};
        return ModelUtil.isAmong(reason, reasons);
    }

    public String getReason() {
        return reason;
    }

    public void setTransactionData(MeterValue[] transactionData) {
        this.transactionData = transactionData;
    }

    public MeterValue[] getTransactionData() {
        return transactionData;
    }
}
