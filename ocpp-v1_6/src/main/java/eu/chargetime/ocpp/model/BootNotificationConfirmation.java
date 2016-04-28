package eu.chargetime.ocpp.model;

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
