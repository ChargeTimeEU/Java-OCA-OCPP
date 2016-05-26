package eu.chargetime.ocpp.model;

import eu.chargetime.ocpp.PropertyConstraintException;

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
public class MeterValuesRequest implements Request {

    private int connectorId;
    private int transactionId;
    private MeterValue[] meterValue;

    @Override
    public boolean validate() {
        boolean valid = true;
        valid &= this.connectorId >= 0;
        if (valid &= this.meterValue != null) {
            for (MeterValue current: this.meterValue)
                valid &= current != null && current.validate();
        }
        return valid;
    }

    public void setConnectorId(int connectorId) throws PropertyConstraintException {
        if (connectorId < 0)
            throw new PropertyConstraintException("connectorId", connectorId);

        this.connectorId = connectorId;
    }

    public int getConnectorId() {
        return connectorId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setMeterValue(MeterValue[] meterValue) throws PropertyConstraintException {
        if (meterValue == null)
            throw new PropertyConstraintException("meterValue", meterValue);

        this.meterValue = meterValue;
    }

    public MeterValue[] getMeterValue() {
        return meterValue;
    }
}
