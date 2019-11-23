package eu.chargetime.ocpp.model.smartcharging;

/*
   ChargeTime.eu - Java-OCA-OCPP

   MIT License

   Copyright (C) 2019 Kevin Raddatz <kevin.raddatz@valtech-mobility.com>

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

import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.core.ChargingSchedule;
import eu.chargetime.ocpp.utilities.MoreObjects;

import javax.xml.bind.annotation.XmlElement;
import java.time.LocalDateTime;
import java.util.Objects;

public class GetCompositeScheduleConfirmation implements Confirmation {

    GetCompositeScheduleStatus status;
    Integer connectorId;
    LocalDateTime scheduleStart;
    ChargingSchedule chargingSchedule;

    @Override
    public boolean validate() {
        return this.status != null;
    }

    public GetCompositeScheduleStatus getStatus() {
        return status;
    }

    @XmlElement
    public void setStatus(final GetCompositeScheduleStatus status) {
        this.status = status;
    }

    public Integer getConnectorId() {
        return connectorId;
    }

    @XmlElement
    public void setConnectorId(final Integer connectorId) {
        this.connectorId = connectorId;
    }

    public LocalDateTime getScheduleStart() {
        return scheduleStart;
    }

    @XmlElement
    public void setScheduleStart(final LocalDateTime scheduleStart) {
        this.scheduleStart = scheduleStart;
    }

    public ChargingSchedule getChargingSchedule() {
        return chargingSchedule;
    }

    @XmlElement
    public void setChargingSchedule(final ChargingSchedule chargingSchedule) {
        this.chargingSchedule = chargingSchedule;
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, connectorId, scheduleStart, chargingSchedule);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("compositeScheduleStatus", status)
                .add("connectorId", connectorId)
                .add("scheduleStart", scheduleStart)
                .add("chargingSchedule", chargingSchedule)
                .add("isValid", validate())
                .toString();
    }
}
