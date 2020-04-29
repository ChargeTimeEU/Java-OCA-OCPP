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
import java.time.ZonedDateTime;
import java.util.Objects;
import javax.xml.bind.annotation.XmlElement;

public class GetCompositeScheduleConfirmation implements Confirmation {

  private GetCompositeScheduleStatus status;
  private Integer connectorId;
  private ZonedDateTime scheduleStart;
  private ChargingSchedule chargingSchedule;

  /**
   * @deprecated use {@link #GetCompositeScheduleConfirmation(GetCompositeScheduleStatus)} to be
   *     sure to set required fields
   */
  @Deprecated
  public GetCompositeScheduleConfirmation() {}

  /**
   * Handle required fields.
   *
   * @param status {@link GetCompositeScheduleStatus}, see {@link
   *     #setStatus(GetCompositeScheduleStatus)}
   */
  public GetCompositeScheduleConfirmation(GetCompositeScheduleStatus status) {
    setStatus(status);
  }

  @Override
  public boolean validate() {
    return this.status != null;
  }

  /**
   * Status of the request. The Charge Point will indicate if it was able to process the request
   *
   * @return status of the request
   */
  public GetCompositeScheduleStatus getStatus() {
    return status;
  }

  /**
   * Required. Status of the request. The Charge Point will indicate if it was able to process the
   * request
   *
   * @param status {@link GetCompositeScheduleStatus}
   */
  @XmlElement
  public void setStatus(final GetCompositeScheduleStatus status) {
    this.status = status;
  }

  /**
   * The charging schedule contained in this notification applies to a Connector.
   *
   * @return Integer ID of the connector
   */
  public Integer getConnectorId() {
    return connectorId;
  }

  /**
   * Optional. The charging schedule contained in this notification applies to a Connector.
   *
   * @param connectorId Integer
   */
  @XmlElement
  public void setConnectorId(final Integer connectorId) {
    this.connectorId = connectorId;
  }

  /**
   * Time. Periods contained in the charging profile are relative to this point in time. If status
   * is "Rejected", this field may be absent.
   *
   * @return {@link ZonedDateTime} start of the schedule
   */
  public ZonedDateTime getScheduleStart() {
    return scheduleStart;
  }

  /**
   * Optional. Time. Periods contained in the charging profile are relative to this point in time.
   * If status is "Rejected", this field may be absent.
   *
   * @param scheduleStart {@link ZonedDateTime}
   */
  @XmlElement
  public void setScheduleStart(final ZonedDateTime scheduleStart) {
    this.scheduleStart = scheduleStart;
  }

  /**
   * Planned Composite Charging Schedule, the energy consumption over time. Always relative to
   * ScheduleStart. If status is "Rejected", this field may be absent.
   *
   * @return {@link ChargingSchedule} planned charging schedule
   */
  public ChargingSchedule getChargingSchedule() {
    return chargingSchedule;
  }

  /**
   * Optional. Planned Composite Charging Schedule, the energy consumption over time. Always
   * relative to ScheduleStart. If status is "Rejected", this field may be absent.
   *
   * @param chargingSchedule {@link ChargingSchedule}
   */
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
