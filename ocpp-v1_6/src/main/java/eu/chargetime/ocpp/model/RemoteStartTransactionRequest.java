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
public class RemoteStartTransactionRequest implements Request {

    private Integer connectorId;
    private IdToken idTag;
    private ChargingProfile chargingProfile;

    @Override
    public boolean validate() {
        boolean valid = true;
        if (valid &= idTag != null)
            valid &= idTag.validate();
        if (chargingProfile != null)
            valid &= chargingProfile.validate();
        return valid;
    }

    public void setConnectorId(Integer connectorId) throws PropertyConstraintException {
        if (connectorId <= 0)
            throw new PropertyConstraintException("connectorId", connectorId);

        this.connectorId = connectorId;
    }

    public Integer getConnectorId() {
        return connectorId;
    }

    public void setIdTag(IdToken idTag) throws PropertyConstraintException {
        if (idTag == null)
            throw new PropertyConstraintException("idTag", idTag);

        this.idTag = idTag;
    }

    public IdToken getIdTag() {
        return idTag;
    }

    public void setChargingProfile(ChargingProfile chargingProfile) {
        this.chargingProfile = chargingProfile;
    }

    public ChargingProfile getChargingProfile() {
        return chargingProfile;
    }
}
