package eu.chargetime.ocpp.model;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.utilities.ModelUtil;

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
public class ChargingProfile implements Validatable {
    private Integer chargingProfileId;
    private Integer transactionId;
    private Integer stackLevel;
    private String chargingProfilePurpose;
    private String chargingProfileKind;
    private String recurrencyKind;
    private Calendar validFrom;
    private Calendar validTo;
    private ChargingSchedule chargingSchedule;

    @Override
    public boolean validate() {
        boolean valid = true;
        valid &= chargingProfileId != null;
        valid &= stackLevel >= 0;
        valid &= isValidChargingProfilePurpose(chargingProfilePurpose);
        valid &= transactionId == null || "TxProfile".equals(chargingProfilePurpose);
        valid &= isValidChargingProfileKind(chargingProfileKind);
        valid &= chargingSchedule != null && chargingSchedule.validate();
        return valid;
    }

    public void setChargingProfileId(Integer chargingProfileId) throws PropertyConstraintException {
        if (chargingProfileId == null)
            throw new PropertyConstraintException("chargingProfileId", chargingProfileId);

        this.chargingProfileId = chargingProfileId;
    }

    public Integer getChargingProfileId() {
        return chargingProfileId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setStackLevel(Integer stackLevel) throws PropertyConstraintException {
        if (stackLevel < 0)
            throw new PropertyConstraintException("stackLevel", stackLevel);

        this.stackLevel = stackLevel;
    }

    public Integer getStackLevel() {
        return stackLevel;
    }

    public void setChargingProfilePurpose(String chargingProfilePurpose) throws PropertyConstraintException {
        if (!isValidChargingProfilePurpose(chargingProfilePurpose))
            throw new PropertyConstraintException("chargingProfilePurpose", chargingProfilePurpose);

        this.chargingProfilePurpose = chargingProfilePurpose;
    }

    private boolean isValidChargingProfilePurpose(String chargingProfilePurpose) {
        return ModelUtil.isAmong(chargingProfilePurpose, "ChargePointMaxProfile", "TxDefaultProfile", "TxProfile");
    }

    public String getChargingProfilePurpose() {
        return chargingProfilePurpose;
    }

    public void setChargingProfileKind(String chargingProfileKind) throws PropertyConstraintException {
        if (!isValidChargingProfileKind(chargingProfileKind))
            throw new PropertyConstraintException("chargingProfileKind", chargingProfileKind);

        this.chargingProfileKind = chargingProfileKind;
    }

    private boolean isValidChargingProfileKind(String chargingProfileKind) {
        return ModelUtil.isAmong(chargingProfileKind, "Absolute", "Recurring", "Relative");
    }

    public String getChargingProfileKind() {
        return chargingProfileKind;
    }

    public void setRecurrencyKind(String recurrencyKind) throws PropertyConstraintException {
        if (!isValidRecurrencyKind(recurrencyKind))
            throw new PropertyConstraintException("recurrencyKind", recurrencyKind);

        this.recurrencyKind = recurrencyKind;
    }

    private boolean isValidRecurrencyKind(String recurrencyKind) {
        return ModelUtil.isAmong(recurrencyKind, "Daily", "Weekly");
    }

    public String getRecurrencyKind() {
        return recurrencyKind;
    }

    public void setValidFrom(Calendar validFrom) {
        this.validFrom = validFrom;
    }

    public String getValidFrom() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        formatter.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        return formatter.format(validFrom.getTime());
    }

    public Calendar objValidFrom() {
        return this.validFrom;
    }

    public void setValidTo(Calendar validTo) {
        this.validTo = validTo;
    }

    public String getValidTo() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        formatter.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        return formatter.format(validTo.getTime());
    }

    public Calendar objValidTo() {
        return validTo;
    }

    public void setChargingSchedule(ChargingSchedule chargingSchedule) {
        this.chargingSchedule = chargingSchedule;
    }

    public ChargingSchedule getChargingSchedule() {
        return chargingSchedule;
    }
}
