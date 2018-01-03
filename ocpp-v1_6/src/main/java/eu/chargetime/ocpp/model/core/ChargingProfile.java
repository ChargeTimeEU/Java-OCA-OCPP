package eu.chargetime.ocpp.model.core;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.model.Validatable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Calendar;

/*
 * ChargeTime.eu - Java-OCA-OCPP
 *
 * MIT License
 *
 * Copyright (C) 2016-2018 Thomas Volden <tv@chargetime.eu>
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
@XmlRootElement
@XmlType(propOrder = {"chargingProfileId", "transactionId", "stackLevel", "chargingProfilePurpose", "chargingProfileKind", "recurrencyKind", "validFrom", "validTo", "chargingSchedule"})
public class ChargingProfile implements Validatable {
    private Integer chargingProfileId;
    private Integer transactionId;
    private Integer stackLevel;
    private ChargingProfilePurposeType chargingProfilePurpose;
    private ChargingProfileKindType chargingProfileKind;
    private RecurrencyKindType recurrencyKind;
    private Calendar validFrom;
    private Calendar validTo;
    private ChargingSchedule chargingSchedule;

    @Override
    public boolean validate() {
        boolean valid = true;
        valid &= chargingProfileId != null;
        valid &= stackLevel >= 0;
        valid &= chargingProfilePurpose != null;
        valid &= transactionId == null || chargingProfilePurpose == ChargingProfilePurposeType.TxProfile;
        valid &= chargingProfileKind != null;
        valid &= chargingSchedule != null && chargingSchedule.validate();
        return valid;
    }

    @XmlElement
    public void setChargingProfileId(Integer chargingProfileId) throws PropertyConstraintException {
        if (chargingProfileId == null)
            throw new PropertyConstraintException("chargingProfileId", chargingProfileId);

        this.chargingProfileId = chargingProfileId;
    }

    public Integer getChargingProfileId() {
        return chargingProfileId;
    }

    @XmlElement
    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    @XmlElement
    public void setStackLevel(Integer stackLevel) throws PropertyConstraintException {
        if (stackLevel < 0)
            throw new PropertyConstraintException("stackLevel", stackLevel);

        this.stackLevel = stackLevel;
    }

    public Integer getStackLevel() {
        return stackLevel;
    }

    @XmlElement
    public void setChargingProfilePurpose(ChargingProfilePurposeType chargingProfilePurpose) throws PropertyConstraintException {
        this.chargingProfilePurpose = chargingProfilePurpose;
    }

    public ChargingProfilePurposeType getChargingProfilePurpose() {
        return chargingProfilePurpose;
    }

    @Deprecated
    public ChargingProfilePurposeType objChargingProfilePurpose() {
        return chargingProfilePurpose;
    }

    @XmlElement
    public void setChargingProfileKind(ChargingProfileKindType chargingProfileKind) throws PropertyConstraintException {
        this.chargingProfileKind = chargingProfileKind;
    }

    public ChargingProfileKindType getChargingProfileKind() {
        return chargingProfileKind;
    }

    @Deprecated
    public ChargingProfileKindType objChargingProfileKind() {
        return chargingProfileKind;
    }

    @XmlElement
    public void setRecurrencyKind(RecurrencyKindType recurrencyKind) throws PropertyConstraintException {
        this.recurrencyKind = recurrencyKind;
    }

    public RecurrencyKindType getRecurrencyKind() {
        return recurrencyKind;
    }

    @Deprecated
    public RecurrencyKindType objRecurrencyKind() {
        return recurrencyKind;
    }

    @XmlElement
    public void setValidFrom(Calendar validFrom) {
        this.validFrom = validFrom;
    }

    public Calendar getValidFrom() {
        return validFrom;
    }

    @Deprecated
    public Calendar objValidFrom() {
        return this.validFrom;
    }

    @XmlElement
    public void setValidTo(Calendar validTo) {
        this.validTo = validTo;
    }

    public Calendar getValidTo() {
        return validTo;
    }

    @Deprecated
    public Calendar objValidTo() {
        return validTo;
    }

    @XmlElement
    public void setChargingSchedule(ChargingSchedule chargingSchedule) {
        this.chargingSchedule = chargingSchedule;
    }

    public ChargingSchedule getChargingSchedule() {
        return chargingSchedule;
    }
}
