package eu.chargetime.ocpp.model;

/**
 * Created by Thomas Volden on 25-Apr-16.
 */
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

    public BootNotificationRequest(String vendor, String model) {
        chargePointVendor = vendor;
        chargePointModel = model;
    }

    public String getChargePointVendor()
    {
        return chargePointVendor;
    }

    public void setChargePointVendor(String chargePointVendor)
    {
        this.chargePointVendor = chargePointVendor;
    }

    public String getChargePointModel()
    {
        return chargePointModel;
    }

    public void setChargePointModel(String chargePointModel)
    {
        this.chargePointModel = chargePointModel;
    }

    public String getChargeBoxSerialNumber()
    {
        return chargeBoxSerialNumber;
    }

    public void setChargeBoxSerialNumber(String chargeBoxSerialNumber)
    {
        this.chargeBoxSerialNumber = chargeBoxSerialNumber;
    }

    public String getChargePointSerialNumber()
    {
        return chargePointSerialNumber;
    }

    public void setChargePointSerialNumber(String chargePointSerialNumber)
    {
        this.chargePointSerialNumber = chargePointSerialNumber;
    }

    public String getFirmwareVersion()
    {
        return firmwareVersion;
    }

    public void setFirmwareVersion(String firmwareVersion)
    {
        this.firmwareVersion = firmwareVersion;
    }

    public String getIccid()
    {
        return iccid;
    }

    public void setIccid(String iccid)
    {
        this.iccid = iccid;
    }

    public String getImsi()
    {
        return imsi;
    }

    public void setImsi(String imsi)
    {
        this.imsi = imsi;
    }

    public String getMeterSerialNumber()
    {
        return meterSerialNumber;
    }

    public void setMeterSerialNumber(String meterSerialNumber)
    {
        this.meterSerialNumber = meterSerialNumber;
    }

    public String getMeterType()
    {
        return meterType;
    }

    public void setMeterType(String meterType)
    {
        this.meterType = meterType;
    }
}