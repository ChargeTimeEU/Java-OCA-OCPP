/*
   ChargeTime.eu - Java-OCA-OCPP

   MIT License

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

package eu.chargetime.ocpp.v201.model.messages;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.model.RequestWithId;
import eu.chargetime.ocpp.utilities.MoreObjects;
import eu.chargetime.ocpp.v201.model.types.ConnectorStatusEnum;
import eu.chargetime.ocpp.v201.model.types.CustomData;
import java.time.ZonedDateTime;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * StatusNotificationRequest
 *
 * <p>OCPP 2.0.1 FINAL
 */
public final class StatusNotificationRequest extends RequestWithId {
  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * The time for which the status is reported. If absent time of receipt of the message will be
   * assumed.
   */
  private ZonedDateTime timestamp;

  /** The current status of the Connector. */
  private ConnectorStatusEnum connectorStatus;

  /** The id of the EVSE to which the connector belongs for which the the status is reported. */
  private Integer evseId;

  /** The id of the connector within the EVSE for which the status is reported. */
  private Integer connectorId;

  /**
   * Constructor for the StatusNotificationRequest class
   *
   * @param timestamp The time for which the status is reported. If absent time of receipt of the
   *     message will be assumed.
   * @param connectorStatus The current status of the Connector.
   * @param evseId The id of the EVSE to which the connector belongs for which the the status is
   *     reported.
   * @param connectorId The id of the connector within the EVSE for which the status is reported.
   */
  public StatusNotificationRequest(
      ZonedDateTime timestamp,
      ConnectorStatusEnum connectorStatus,
      Integer evseId,
      Integer connectorId) {
    setTimestamp(timestamp);
    setConnectorStatus(connectorStatus);
    setEvseId(evseId);
    setConnectorId(connectorId);
  }

  /**
   * Gets custom data
   *
   * @return Custom data
   */
  @Nullable
  public CustomData getCustomData() {
    return customData;
  }

  /**
   * Sets custom data
   *
   * @param customData Custom data
   */
  public void setCustomData(@Nullable CustomData customData) {
    if (!isValidCustomData(customData)) {
      throw new PropertyConstraintException(customData, "customData is invalid");
    }
    this.customData = customData;
  }

  /**
   * Returns whether the given customData is valid
   *
   * @param customData the customData to check the validity of
   * @return {@code true} if customData is valid, {@code false} if not
   */
  private boolean isValidCustomData(@Nullable CustomData customData) {
    return customData == null || customData.validate();
  }

  /**
   * Adds custom data
   *
   * @param customData Custom data
   * @return this
   */
  public StatusNotificationRequest withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  /**
   * Gets the time for which the status is reported. If absent time of receipt of the message will
   * be assumed.
   *
   * @return The time for which the status is reported
   */
  public ZonedDateTime getTimestamp() {
    return timestamp;
  }

  /**
   * Sets the time for which the status is reported. If absent time of receipt of the message will
   * be assumed.
   *
   * @param timestamp The time for which the status is reported
   */
  public void setTimestamp(ZonedDateTime timestamp) {
    if (!isValidTimestamp(timestamp)) {
      throw new PropertyConstraintException(timestamp, "timestamp is invalid");
    }
    this.timestamp = timestamp;
  }

  /**
   * Returns whether the given timestamp is valid
   *
   * @param timestamp the timestamp to check the validity of
   * @return {@code true} if timestamp is valid, {@code false} if not
   */
  private boolean isValidTimestamp(ZonedDateTime timestamp) {
    return timestamp != null;
  }

  /**
   * Gets the current status of the Connector.
   *
   * @return The current status of the Connector
   */
  public ConnectorStatusEnum getConnectorStatus() {
    return connectorStatus;
  }

  /**
   * Sets the current status of the Connector.
   *
   * @param connectorStatus The current status of the Connector
   */
  public void setConnectorStatus(ConnectorStatusEnum connectorStatus) {
    if (!isValidConnectorStatus(connectorStatus)) {
      throw new PropertyConstraintException(connectorStatus, "connectorStatus is invalid");
    }
    this.connectorStatus = connectorStatus;
  }

  /**
   * Returns whether the given connectorStatus is valid
   *
   * @param connectorStatus the connectorStatus to check the validity of
   * @return {@code true} if connectorStatus is valid, {@code false} if not
   */
  private boolean isValidConnectorStatus(ConnectorStatusEnum connectorStatus) {
    return connectorStatus != null;
  }

  /**
   * Gets the id of the EVSE to which the connector belongs for which the the status is reported.
   *
   * @return The id of the EVSE to which the connector belongs for which the the status is reported
   */
  public Integer getEvseId() {
    return evseId;
  }

  /**
   * Sets the id of the EVSE to which the connector belongs for which the the status is reported.
   *
   * @param evseId The id of the EVSE to which the connector belongs for which the the status is
   *     reported
   */
  public void setEvseId(Integer evseId) {
    if (!isValidEvseId(evseId)) {
      throw new PropertyConstraintException(evseId, "evseId is invalid");
    }
    this.evseId = evseId;
  }

  /**
   * Returns whether the given evseId is valid
   *
   * @param evseId the evseId to check the validity of
   * @return {@code true} if evseId is valid, {@code false} if not
   */
  private boolean isValidEvseId(Integer evseId) {
    return evseId != null;
  }

  /**
   * Gets the id of the connector within the EVSE for which the status is reported.
   *
   * @return The id of the connector within the EVSE for which the status is reported
   */
  public Integer getConnectorId() {
    return connectorId;
  }

  /**
   * Sets the id of the connector within the EVSE for which the status is reported.
   *
   * @param connectorId The id of the connector within the EVSE for which the status is reported
   */
  public void setConnectorId(Integer connectorId) {
    if (!isValidConnectorId(connectorId)) {
      throw new PropertyConstraintException(connectorId, "connectorId is invalid");
    }
    this.connectorId = connectorId;
  }

  /**
   * Returns whether the given connectorId is valid
   *
   * @param connectorId the connectorId to check the validity of
   * @return {@code true} if connectorId is valid, {@code false} if not
   */
  private boolean isValidConnectorId(Integer connectorId) {
    return connectorId != null;
  }

  @Override
  public boolean validate() {
    return isValidCustomData(customData)
        && isValidTimestamp(timestamp)
        && isValidConnectorStatus(connectorStatus)
        && isValidEvseId(evseId)
        && isValidConnectorId(connectorId);
  }

  @Override
  public boolean transactionRelated() {
    return false;
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
    return Objects.equals(customData, that.customData)
        && Objects.equals(timestamp, that.timestamp)
        && Objects.equals(connectorStatus, that.connectorStatus)
        && Objects.equals(evseId, that.evseId)
        && Objects.equals(connectorId, that.connectorId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customData, timestamp, connectorStatus, evseId, connectorId);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("timestamp", timestamp)
        .add("connectorStatus", connectorStatus)
        .add("evseId", evseId)
        .add("connectorId", connectorId)
        .add("isValid", validate())
        .toString();
  }
}
