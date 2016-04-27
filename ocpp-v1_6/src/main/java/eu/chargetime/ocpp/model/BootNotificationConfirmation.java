package eu.chargetime.ocpp.model;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Thomas Volden on 26-Apr-16.
 */
public class BootNotificationConfirmation implements Confirmation
{
    private Calendar currentTime;
    private int interval;
    private String status;

    public Calendar getCurrentTime()
    {
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

    public void setInterval(int interval)
    {
        this.interval = interval;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }
}
