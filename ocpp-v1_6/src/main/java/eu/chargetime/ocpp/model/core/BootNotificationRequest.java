package eu.chargetime.ocpp.model.core;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.utilities.ModelUtil;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/*
 ChargeTime.eu - Java-OCA-OCPP
 Copyright (C) 2015-2016 Thomas Volden <tv@chargetime.eu>

 MIT License

 Copyright (C) 2016-2018 Thomas Volden

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
 * Sent by the Charge Point to the Central System.
 */
@XmlRootElement
@XmlType(propOrder = {"chargePointVendor", "chargePointModel", "chargePointSerialNumber", "chargeBoxSerialNumber", "firmwareVersion", "iccid", "imsi", "meterType", "meterSerialNumber"})
public class BootNotificationRequest implements Request
{
    private String chargePointVendor;
    private String chargePointModel;
    private String chargeBoxSerialNumber;
    private String chargePointSerialNumber;
    private String firmwareVersion;
    private String iccid;
    private String imsi;
    private String meterSerialNumber;
    private String meterType;

    public BootNotificationRequest() { }

    /**
     * Handle required fields.
     *
     * @param vendor Charge Point vendor, see {@link #setChargePointVendor(String)}.
     * @param model  Charge Point model, see {@link #setChargePointModel(String)}.
     */
    public BootNotificationRequest(String vendor, String model) {
        chargePointVendor = vendor;
        chargePointModel = model;
    }

    /**
     * This contains a value that identifies the vendor of the ChargePoint.
     *
     * @return Vendor of the Charge Point.
     */
    public String getChargePointVendor()
    {
        return chargePointVendor;
    }

    /**
     * Required. This contains a value that identifies the vendor of the ChargePoint.
     *
     * @param chargePointVendor             String, max 20 characters, case insensitive.
     * @throws PropertyConstraintException  Value exceeds 20 characters.
     */
    @XmlElement
    public void setChargePointVendor(String chargePointVendor) throws PropertyConstraintException {
        if (!ModelUtil.validate(chargePointVendor, 20))
            throw new PropertyConstraintException("chargePointVendor", chargePointVendor);

        this.chargePointVendor = chargePointVendor;
    }

    /**
     * This contains a value that identifies the model of the ChargePoint.
     *
     * @return Model of the Charge Point.
     */
    public String getChargePointModel()
    {
        return chargePointModel;
    }

    /**
     * Required. This contains a value that identifies the model of the ChargePoint.
     *
     * @param chargePointModel              String, max 20 characters, case insensitive.
     * @throws PropertyConstraintException  Value exceeds 20 characters.
     */
    @XmlElement
    public void setChargePointModel(String chargePointModel) throws PropertyConstraintException
    {
        if (!ModelUtil.validate(chargePointModel, 20))
            throw new PropertyConstraintException("chargePointModel", chargePointModel);

        this.chargePointModel = chargePointModel;
    }

    /**
     * This contains a value that identifies the serial number of the Charge Box inside the Charge Point.
     *
     * @return Serial Number of the Charge Point.
     * @deprecated will be removed in future version. See {@link #getChargePointSerialNumber()}.
     */
    @Deprecated()
    public String getChargeBoxSerialNumber()
    {
        return chargeBoxSerialNumber;
    }

    /**
     * Optional. This contains a value that identifies the serial number of the Charge Box inside the Charge Point.
     *
     * @param chargeBoxSerialNumber String, max 25 characters, case insensitive.
     * @throws PropertyConstraintException Value exceeds 25 characters.
     * @deprecated will be removed in future version. See {@link #setChargePointSerialNumber(String)}.
     */
    @Deprecated()
    public void setChargeBoxSerialNumber(String chargeBoxSerialNumber) throws PropertyConstraintException
    {
        if (!ModelUtil.validate(chargeBoxSerialNumber, 25))
            throw new PropertyConstraintException("chargeBoxSerialNumber", chargeBoxSerialNumber);

        this.chargeBoxSerialNumber = chargeBoxSerialNumber;
    }

    /**
     * This contains a value that identifies the serial number of the Charge Point.
     *
     * @return Serial Number of the Charge Point.
     */
    public String getChargePointSerialNumber()
    {
        return chargePointSerialNumber;
    }

    /**
     * Optional. This contains a value that identifies the serial number of the Charge Point.
     *
     * @param chargePointSerialNumber       String, max 25 characters, case insensitive.
     * @throws PropertyConstraintException  Value exceeds 25 characters.
     */
    @XmlElement
    public void setChargePointSerialNumber(String chargePointSerialNumber) throws PropertyConstraintException {
        if (!ModelUtil.validate(chargePointSerialNumber, 25))
            throw new PropertyConstraintException("chargePointSerialNumber", chargePointSerialNumber);

        this.chargePointSerialNumber = chargePointSerialNumber;
    }

    /**
     * This contains the firmware version of the Charge Point.
     *
     * @return Firmware version of Charge Point.
     */
    public String getFirmwareVersion()
    {
        return firmwareVersion;
    }

    /**
     * Optional. This contains the firmware version of the Charge Point.
     *
     * @param firmwareVersion               String, max 50 characters, case insensitive.
     * @throws PropertyConstraintException  Value exceeds 50 characters.
     */
    @XmlElement
    public void setFirmwareVersion(String firmwareVersion) throws PropertyConstraintException {
        if (!ModelUtil.validate(firmwareVersion, 50))
            throw new PropertyConstraintException("firmwareVersion", firmwareVersion);

        this.firmwareVersion = firmwareVersion;
    }

    /**
     * This contains the ICCID of the modem’s SIM card.
     *
     * @return ICCID of SIM card.
     */
    public String getIccid()
    {
        return iccid;
    }

    /**
     * Optional. This contains the ICCID of the modem’s SIM card.
     *
     * @param iccid                         String, max 20 characters, case insensitive.
     * @throws PropertyConstraintException  Value exceeds 20 characters.
     */
    @XmlElement
    public void setIccid(String iccid) throws PropertyConstraintException {
        if (!ModelUtil.validate(iccid, 20))
            throw new PropertyConstraintException("iccid", iccid);

        this.iccid = iccid;
    }

    /**
     * This contains the IMSI of the modem’s SIM card.
     *
     * @return IMSI of SIM card.
     */
    public String getImsi()
    {
        return imsi;
    }

    /**
     * Optional. This contains the IMSI of the modem’s SIM card.
     *
     * @param imsi                          String, max 20 characters, case insensitive.
     * @throws PropertyConstraintException Value exceeds 20 characters.
     */
    @XmlElement
    public void setImsi(String imsi) throws PropertyConstraintException {
        if (!ModelUtil.validate(imsi, 20))
            throw new PropertyConstraintException("imsi", imsi);

        this.imsi = imsi;
    }

    /**
     * This contains the serial number of the main power meter of the Charge Point.
     *
     * @return Serial number of the meter.
     */
    public String getMeterSerialNumber()
    {
        return meterSerialNumber;
    }

    /**
     * Optional. This contains the serial number of the main power meter of the Charge Point.
     *
     * @param meterSerialNumber             String, max 25 characters, case insensitive.
     * @throws PropertyConstraintException  Value exceeds 25 characters.
     */
    @XmlElement
    public void setMeterSerialNumber(String meterSerialNumber) throws PropertyConstraintException {
        if (!ModelUtil.validate(meterSerialNumber, 25))
            throw new PropertyConstraintException("meterSerialNumber", meterSerialNumber);

        this.meterSerialNumber = meterSerialNumber;
    }

    /**
     * This contains the type of the main power meter of the Charge Point.
     *
     * @return Type of main power meter.
     */
    public String getMeterType()
    {
        return meterType;
    }

    /**
     * Optional. This contains the type of the main power meter of the Charge Point.
     *
     * @param meterType                     String, max 25 characters, case insensitive.
     * @throws PropertyConstraintException  Value exceeds 25 characters.
     */
    @XmlElement
    public void setMeterType(String meterType) throws PropertyConstraintException {
        if (!ModelUtil.validate(meterType, 25))
            throw new PropertyConstraintException("meterType", meterType);

        this.meterType = meterType;
    }

    @Override
    public boolean validate() {
        boolean valid = true;
        valid &= ModelUtil.validate(chargePointModel, 20);
        valid &= ModelUtil.validate(chargePointVendor, 20);
        return valid;
    }

    @Override
    public boolean transactionRelated() {
        return false;
    }
}