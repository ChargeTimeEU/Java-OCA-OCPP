package eu.chargetime.ocpp.model;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.utilities.ModelUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by Thomas Volden on 26-Apr-16.
 */
public class BootNotificationConfirmation implements Confirmation
{
    private Calendar currentTime;
    private int interval;
    private String status;

    public String getCurrentTime()
    {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        formatter.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        return formatter.format(currentTime.getTime());
    }

    public Calendar objCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Calendar currentTime)
    {
        this.currentTime = currentTime;
    }

    public int getInterval()
    {
        return interval;
    }

    public void setInterval(int interval) throws PropertyConstraintException {
        if (interval <= 0)
            throw new PropertyConstraintException("interval", interval, "Must be a positive value");

        this.interval = interval;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status) throws PropertyConstraintException {
        if (!isValidStatus(status))
            throw new PropertyConstraintException("status", status);

        this.status = status;
    }

    private boolean isValidStatus(String status) {
        return ModelUtil.isAmoung(status, "Accepted", "Pending", "Rejected");
    }

    @Override
    public boolean validate() {
        boolean valid = true;
        valid &= isValidStatus(this.status);
        valid &= currentTime != null;
        valid &= interval > 0;
        return valid;
    }
}
