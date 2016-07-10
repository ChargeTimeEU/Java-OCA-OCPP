package eu.chargetime.ocpp.model;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.utilities.ModelUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

/*
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

/**
 * Sent by the Charge Point to the Central System.
 */
public class StatusNotificationRequest implements Request {
    private Integer connectorId;
    private ChargePointErrorCode errorCode;
    private String info;
    private ChargePointStatus status;
    private Calendar timestamp;
    private String vendorId;
    private String vendorErrorCode;

    @Override
    public boolean validate() {
        boolean valid = true;
        valid &= isValidConnectorId(connectorId);
        valid &= errorCode != null;
        valid &= status != null;
        return valid;
    }

    /**
     * The id of the connector for which the status is reported.
     * Id '0' (zero) is used if the status is for the Charge Point main controller.
     *
     * @return connector id. 0 = main controller.
     */
    public Integer getConnectorId() {
        return connectorId;
    }

    /**
     * Required. The id of the connector for which the status is reported.
     * Id '0' (zero) is used if the status is for the Charge Point main controller.
     *
     * @param connectorId integer, connector id. 0 = main controller.
     * @throws PropertyConstraintException Value was negative.
     */
    public void setConnectorId(Integer connectorId) throws PropertyConstraintException {
        if (!isValidConnectorId(connectorId))
            throw new PropertyConstraintException("connectorId", connectorId);

        this.connectorId = connectorId;
    }

    private boolean isValidConnectorId(Integer connectorId) {
        return connectorId != null && connectorId >= 0;
    }

    /**
     * This contains the error code reported by the Charge Point.
     *
     * @return String, the {@link ChargePointErrorCode}.
     */
    public String getErrorCode() {
        return errorCode.toString();
    }

    /**
     * This contains the error code reported by the Charge Point.
     *
     * @return the {@link ChargePointErrorCode}.
     */
    public ChargePointErrorCode objErrorCode() {
        return errorCode;
    }

    /**
     * Required. This contains the error code reported by the Charge Point.
     *
     * @param errorCode the {@link ChargePointErrorCode}.
     */
    public void setErrorCode(ChargePointErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * Additional free format information related to the error.
     *
     * @return Additional information.
     */
    public String getInfo() {
        return info;
    }

    /**
     * Optional. Additional free format information related to the error.
     *
     * @param info                          String, max 50 characters, case insensitive.
     * @throws PropertyConstraintException  Value exceeds 50 characters.
     */
    public void setInfo(String info) throws PropertyConstraintException {
        if (!ModelUtil.validate(info, 50))
            throw new PropertyConstraintException("info", info);

        this.info = info;
    }

    /**
     * This contains the current status of the Charge Point.
     *
     * @return String, the {@link ChargePointStatus}.
     */
    public String getStatus() {
        return status.toString();
    }

    /**
     * This contains the current status of the Charge Point.
     *
     * @return the {@link ChargePointStatus}.
     */
    public ChargePointStatus objStatus() {
        return status;
    }

    /**
     * Required. This contains the current status of the Charge Point.
     *
     * @param status the {@link ChargePointStatus}.
     */
    public void setStatus(ChargePointStatus status) {
        this.status = status;
    }

    /**
     * The time for which the status is reported.
     * If absent time of receipt of the message will be assumed.
     *
     * @return String, formatted status time.
     */
    public String getTimestamp() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        formatter.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        return formatter.format(timestamp.getTime());
    }

    /**
     * The time for which the status is reported.
     * If absent time of receipt of the message will be assumed.
     *
     * @return status time.
     */
    public Calendar objTimestamp() {
        return timestamp;
    }

    /**
     * Optional. The time for which the status is reported.
     * If absent time of receipt of the message will be assumed.
     *
     * @param timestamp    Calendar, status time.
     */
    public void setTimestamp(Calendar timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * This identifies the vendor-specific implementation.
     *
     * @return Identification of the vendor.
     */
    public String getVendorId() {
        return vendorId;
    }

    /**
     * Optional. This identifies the vendor-specific implementation.
     *
     * @param vendorId String, max 255 characters, case insensitive.
     * @throws PropertyConstraintException Value exceeds 255 characters.
     */
    public void setVendorId(String vendorId) throws PropertyConstraintException {
        if (!ModelUtil.validate(vendorId, 255))
            throw new PropertyConstraintException("vendorId", vendorId);

        this.vendorId = vendorId;
    }

    /**
     * This contains the vendor-specific error code.
     *
     * @return Custom vendor error code.
     */
    public String getVendorErrorCode() {
        return vendorErrorCode;
    }

    /**
     * Optional. This contains the vendor-specific error code.
     *
     * @param vendorErrorCode               String, max 50 characters, case insensitive.
     * @throws PropertyConstraintException  Value excceds 50 characters.
     */
    public void setVendorErrorCode(String vendorErrorCode) throws PropertyConstraintException {
        if (!ModelUtil.validate(vendorErrorCode, 50))
            throw new PropertyConstraintException("vendorErrorCode", vendorErrorCode);

        this.vendorErrorCode = vendorErrorCode;
    }
}
