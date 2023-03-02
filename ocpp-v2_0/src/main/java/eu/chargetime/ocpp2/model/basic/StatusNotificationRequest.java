package eu.chargetime.ocpp2.model.basic;
/*
   ChargeTime.eu - Java-OCA-OCPP

   MIT License

   Copyright (C) 2021 John Michael Luy <johnmichael.luy@gmail.com>

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

import eu.chargetime.ocpp.model.RequestWithId;
import eu.chargetime.ocpp2.model.basic.types.ConnectorStatusEnumType;
import eu.chargetime.ocpp.model.validation.RequiredValidator;
import eu.chargetime.ocpp.utilities.MoreObjects;
import java.time.ZonedDateTime;
import java.util.Objects;

public class StatusNotificationRequest extends RequestWithId {

  private transient RequiredValidator validator = new RequiredValidator();

  private ZonedDateTime timestamp;

  private ConnectorStatusEnumType connectorStatus;

  private Integer evseId;

  private Integer connectorId;

  /**
   * This is the time for which the status is reported
   *
   * @return {@link ZonedDateTime}
   */
  public ZonedDateTime getTimestamp() {
    return timestamp;
  }

  /**
   * Required. Time the status is reported.
   *
   * @param timestamp {@link ZonedDateTime}
   */
  public void setTimestamp(ZonedDateTime timestamp) {
    validator.validate(timestamp);
    this.timestamp = timestamp;
  }

  /**
   * This contains the current status of the Connector.
   *
   * @return {@link ConnectorStatusEnumType}
   */
  public ConnectorStatusEnumType getConnectorStatus() {
    return connectorStatus;
  }

  /**
   * Required. The current status of the Connector.
   *
   * @param connectorStatus {@link ConnectorStatusEnumType}
   */
  public void setConnectorStatus(ConnectorStatusEnumType connectorStatus) {
    validator.validate(connectorStatus);
    this.connectorStatus = connectorStatus;
  }

  /**
   * This is the id of the EVSE to which the connector belongs for which the status is reported.
   *
   * @return {@link ConnectorStatusEnumType}
   */
  public Integer getEvseId() {
    return evseId;
  }

  /**
   * Required. The id of the EVSE to which the connector belongs for which the status is reported.
   *
   * @param evseId integer, evse id
   */
  public void setEvseId(Integer evseId) {
    validator.validate(evseId);
    this.evseId = evseId;
  }

  /**
   * This is the id of the connector within the EVSE for which the status is reported.
   *
   * @return {@link ConnectorStatusEnumType}
   */
  public Integer getConnectorId() {
    return connectorId;
  }

  /**
   * Required. The id of the connector within the EVSE for which the status is reported.
   *
   * @param connectorId integer, connector id
   */
  public void setConnectorId(Integer connectorId) {
    validator.validate(connectorId);
    this.connectorId = connectorId;
  }

  @Override
  public boolean transactionRelated() {
    return false;
  }

  @Override
  public boolean validate() {
    return validator.safeValidate(timestamp)
        && validator.safeValidate(connectorStatus)
        && validator.safeValidate(evseId)
        && validator.safeValidate(connectorId);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StatusNotificationRequest that = (StatusNotificationRequest) o;
    return Objects.equals(connectorStatus, that.connectorStatus)
        && Objects.equals(evseId, that.evseId)
        && Objects.equals(connectorId, that.connectorId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(timestamp, connectorStatus, evseId, connectorId);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("timestamp", timestamp)
        .add("connectorStatus", connectorStatus)
        .add("evseId", evseId)
        .add("connectorId", connectorId)
        .toString();
  }
}
