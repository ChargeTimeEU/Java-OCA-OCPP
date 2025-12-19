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

package eu.chargetime.ocpp.v21.model.messages;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.model.RequestWithId;
import eu.chargetime.ocpp.utilities.MoreObjects;
import eu.chargetime.ocpp.v21.model.types.CustomData;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * NotifyDERStartStopRequest
 *
 * <p>OCPP 2.1 Edition 1 (c) OCA, Creative Commons Attribution-NoDerivatives 4.0 International
 * Public License
 */
public final class NotifyDERStartStopRequest extends RequestWithId {
  /**
   * Id of the started or stopped DER control. Corresponds to the controlId of the
   * SetDERControlRequest.
   */
  private String controlId;

  /** True if DER control has started. False if it has ended. */
  private Boolean started;

  /** Time of start or end of event. */
  private ZonedDateTime timestamp;

  /** List of controlIds that are superseded as a result of this control starting. */
  @Nullable private String[] supersededIds;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the NotifyDERStartStopRequest class
   *
   * @param controlId Id of the started or stopped DER control. Corresponds to the controlId of the
   *     SetDERControlRequest.
   * @param started True if DER control has started. False if it has ended.
   * @param timestamp Time of start or end of event.
   */
  public NotifyDERStartStopRequest(String controlId, Boolean started, ZonedDateTime timestamp) {
    setControlId(controlId);
    setStarted(started);
    setTimestamp(timestamp);
  }

  /**
   * Gets id of the started or stopped DER control. Corresponds to the controlId of the
   * SetDERControlRequest.
   *
   * @return Id of the started or stopped DER control
   */
  public String getControlId() {
    return controlId;
  }

  /**
   * Sets id of the started or stopped DER control. Corresponds to the controlId of the
   * SetDERControlRequest.
   *
   * @param controlId Id of the started or stopped DER control
   */
  public void setControlId(String controlId) {
    if (!isValidControlId(controlId)) {
      throw new PropertyConstraintException(controlId, "controlId is invalid");
    }
    this.controlId = controlId;
  }

  /**
   * Returns whether the given controlId is valid
   *
   * @param controlId the controlId to check the validity of
   * @return {@code true} if controlId is valid, {@code false} if not
   */
  private boolean isValidControlId(String controlId) {
    return controlId != null && controlId.length() <= 36;
  }

  /**
   * Gets true if DER control has started. False if it has ended.
   *
   * @return True if DER control has started
   */
  public Boolean getStarted() {
    return started;
  }

  /**
   * Sets true if DER control has started. False if it has ended.
   *
   * @param started True if DER control has started
   */
  public void setStarted(Boolean started) {
    if (!isValidStarted(started)) {
      throw new PropertyConstraintException(started, "started is invalid");
    }
    this.started = started;
  }

  /**
   * Returns whether the given started is valid
   *
   * @param started the started to check the validity of
   * @return {@code true} if started is valid, {@code false} if not
   */
  private boolean isValidStarted(Boolean started) {
    return started != null;
  }

  /**
   * Gets time of start or end of event.
   *
   * @return Time of start or end of event
   */
  public ZonedDateTime getTimestamp() {
    return timestamp;
  }

  /**
   * Sets time of start or end of event.
   *
   * @param timestamp Time of start or end of event
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
   * Gets list of controlIds that are superseded as a result of this control starting.
   *
   * @return List of controlIds that are superseded as a result of this control starting
   */
  @Nullable
  public String[] getSupersededIds() {
    return supersededIds;
  }

  /**
   * Sets list of controlIds that are superseded as a result of this control starting.
   *
   * @param supersededIds List of controlIds that are superseded as a result of this control
   *     starting
   */
  public void setSupersededIds(@Nullable String[] supersededIds) {
    if (!isValidSupersededIds(supersededIds)) {
      throw new PropertyConstraintException(supersededIds, "supersededIds is invalid");
    }
    this.supersededIds = supersededIds;
  }

  /**
   * Returns whether the given supersededIds is valid
   *
   * @param supersededIds the supersededIds to check the validity of
   * @return {@code true} if supersededIds is valid, {@code false} if not
   */
  private boolean isValidSupersededIds(@Nullable String[] supersededIds) {
    return supersededIds == null
        || (supersededIds.length >= 1
            && supersededIds.length <= 24
            && Arrays.stream(supersededIds).allMatch(item -> item.length() <= 36));
  }

  /**
   * Adds list of controlIds that are superseded as a result of this control starting.
   *
   * @param supersededIds List of controlIds that are superseded as a result of this control
   *     starting
   * @return this
   */
  public NotifyDERStartStopRequest withSupersededIds(@Nullable String[] supersededIds) {
    setSupersededIds(supersededIds);
    return this;
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
  public NotifyDERStartStopRequest withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  @Override
  public boolean validate() {
    return isValidControlId(controlId)
        && isValidStarted(started)
        && isValidTimestamp(timestamp)
        && isValidSupersededIds(supersededIds)
        && isValidCustomData(customData);
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
    NotifyDERStartStopRequest that = (NotifyDERStartStopRequest) o;
    return Objects.equals(controlId, that.controlId)
        && Objects.equals(started, that.started)
        && Objects.equals(timestamp, that.timestamp)
        && Arrays.equals(supersededIds, that.supersededIds)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(controlId, started, timestamp, Arrays.hashCode(supersededIds), customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("controlId", controlId)
        .add("started", started)
        .add("timestamp", timestamp)
        .add("supersededIds", supersededIds)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
