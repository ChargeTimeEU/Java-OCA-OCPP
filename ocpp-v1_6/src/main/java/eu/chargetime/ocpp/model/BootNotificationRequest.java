package eu.chargetime.ocpp.model;

/**
 * Created by Thomas Volden on 25-Apr-16.
 */
public class BootNotificationRequest implements Request
{
    private String chargePointVendor;
    private String chargePointModel;

    public BootNotificationRequest(String vendor, String model) {
        chargePointVendor = vendor;
        chargePointModel = model;
    }

    public String getChargePointVendor() {
        return chargePointVendor;
    }

    public String getChargePointModel()
    {
        return chargePointModel;
    }

    @Override
    public String action()
    {
        return "BootNotification";
    }
}