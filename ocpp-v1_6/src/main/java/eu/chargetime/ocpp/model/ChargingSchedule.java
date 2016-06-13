package eu.chargetime.ocpp.model;

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
public class ChargingSchedule implements Validatable {
    private Integer duration;
    private Calendar startSchedule;
    private ChargingRateUnitType chargingRateUnit;
    private ChargingSchedulePeriod[] chargingSchedulePeriod;
    private Double minChargingRate;

    @Override
    public boolean validate() {
        boolean valid = true;
        valid &= chargingRateUnit != null;
        if (valid &= chargingSchedulePeriod != null) {
            for (ChargingSchedulePeriod period : chargingSchedulePeriod)
                valid &= period.validate();
        }
        return valid;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setStartSchedule(Calendar startSchedule) {
        this.startSchedule = startSchedule;
    }

    public String getStartSchedule() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        formatter.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        return formatter.format(startSchedule.getTime());
    }

    public Calendar objStartSchedule() {
        return startSchedule;
    }

    public void setChargingRateUnit(ChargingRateUnitType chargingRateUnit) {
        this.chargingRateUnit = chargingRateUnit;
    }

    public String getChargingRateUnit() {
        return chargingRateUnit.toString();
    }

    public ChargingRateUnitType objChargingRateUnit() {
        return chargingRateUnit;
    }

    public void setChargingSchedulePeriod(ChargingSchedulePeriod[] chargingSchedulePeriod) {
        this.chargingSchedulePeriod = chargingSchedulePeriod;
    }

    public ChargingSchedulePeriod[] getChargingSchedulePeriod() {
        return chargingSchedulePeriod;
    }

    public void setMinChargingRate(Double minChargingRate) {
        this.minChargingRate = minChargingRate;
    }

    public Double getMinChargingRate() {
        return minChargingRate;
    }
}
