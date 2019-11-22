/*
 * GetCompositeScheduleConfirmation.java
 *
 * Created on 2019-11-21
 *
 * Copyright (C) 2019 Volkswagen AG, All rights reserved.
 */

package eu.chargetime.ocpp.model.smartcharging;

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

    @XmlElement
    public void setStatus(final GetCompositeScheduleStatus status) {
        this.status = status;
    }

    public GetCompositeScheduleStatus getStatus() {
        return status;
    }

    @XmlElement
    public void setConnectorId(final Integer connectorId) {
        this.connectorId = connectorId;
    }

    public Integer getConnectorId() {
        return connectorId;
    }

    @XmlElement
    public void setScheduleStart(final LocalDateTime scheduleStart) {
        this.scheduleStart = scheduleStart;
    }

    public LocalDateTime getScheduleStart() {
        return scheduleStart;
    }

    @XmlElement
    public void setChargingSchedule(final ChargingSchedule chargingSchedule) {
        this.chargingSchedule = chargingSchedule;
    }

    public ChargingSchedule getChargingSchedule() {
        return chargingSchedule;
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
