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
import eu.chargetime.ocpp.v21.model.types.DERControlEnum;
import eu.chargetime.ocpp.v21.model.types.GridEventFaultEnum;
import java.time.ZonedDateTime;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * NotifyDERAlarmRequest
 *
 * <p>OCPP 2.1 Edition 1 (c) OCA, Creative Commons Attribution-NoDerivatives 4.0 International
 * Public License
 */
public final class NotifyDERAlarmRequest extends RequestWithId {
  /** Name of DER control, e.g. LFMustTrip */
  private DERControlEnum controlType;

  /** Type of grid event that caused this */
  @Nullable private GridEventFaultEnum gridEventFault;

  /** True when error condition has ended. Absent or false when alarm has started. */
  @Nullable private Boolean alarmEnded;

  /** Time of start or end of alarm. */
  private ZonedDateTime timestamp;

  /** Optional info provided by EV. */
  @Nullable private String extraInfo;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the NotifyDERAlarmRequest class
   *
   * @param controlType Name of DER control, e.g. LFMustTrip
   * @param timestamp Time of start or end of alarm.
   */
  public NotifyDERAlarmRequest(DERControlEnum controlType, ZonedDateTime timestamp) {
    setControlType(controlType);
    setTimestamp(timestamp);
  }

  /**
   * Gets name of DER control, e.g. LFMustTrip
   *
   * @return Name of DER control, e.g. LFMustTrip
   */
  public DERControlEnum getControlType() {
    return controlType;
  }

  /**
   * Sets name of DER control, e.g. LFMustTrip
   *
   * @param controlType Name of DER control, e.g. LFMustTrip
   */
  public void setControlType(DERControlEnum controlType) {
    if (!isValidControlType(controlType)) {
      throw new PropertyConstraintException(controlType, "controlType is invalid");
    }
    this.controlType = controlType;
  }

  /**
   * Returns whether the given controlType is valid
   *
   * @param controlType the controlType to check the validity of
   * @return {@code true} if controlType is valid, {@code false} if not
   */
  private boolean isValidControlType(DERControlEnum controlType) {
    return controlType != null;
  }

  /**
   * Gets type of grid event that caused this
   *
   * @return Type of grid event that caused this
   */
  @Nullable
  public GridEventFaultEnum getGridEventFault() {
    return gridEventFault;
  }

  /**
   * Sets type of grid event that caused this
   *
   * @param gridEventFault Type of grid event that caused this
   */
  public void setGridEventFault(@Nullable GridEventFaultEnum gridEventFault) {
    this.gridEventFault = gridEventFault;
  }

  /**
   * Adds type of grid event that caused this
   *
   * @param gridEventFault Type of grid event that caused this
   * @return this
   */
  public NotifyDERAlarmRequest withGridEventFault(@Nullable GridEventFaultEnum gridEventFault) {
    setGridEventFault(gridEventFault);
    return this;
  }

  /**
   * Gets true when error condition has ended. Absent or false when alarm has started.
   *
   * @return True when error condition has ended
   */
  @Nullable
  public Boolean getAlarmEnded() {
    return alarmEnded;
  }

  /**
   * Sets true when error condition has ended. Absent or false when alarm has started.
   *
   * @param alarmEnded True when error condition has ended
   */
  public void setAlarmEnded(@Nullable Boolean alarmEnded) {
    this.alarmEnded = alarmEnded;
  }

  /**
   * Adds true when error condition has ended. Absent or false when alarm has started.
   *
   * @param alarmEnded True when error condition has ended
   * @return this
   */
  public NotifyDERAlarmRequest withAlarmEnded(@Nullable Boolean alarmEnded) {
    setAlarmEnded(alarmEnded);
    return this;
  }

  /**
   * Gets time of start or end of alarm.
   *
   * @return Time of start or end of alarm
   */
  public ZonedDateTime getTimestamp() {
    return timestamp;
  }

  /**
   * Sets time of start or end of alarm.
   *
   * @param timestamp Time of start or end of alarm
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
   * Gets optional info provided by EV.
   *
   * @return Optional info provided by EV
   */
  @Nullable
  public String getExtraInfo() {
    return extraInfo;
  }

  /**
   * Sets optional info provided by EV.
   *
   * @param extraInfo Optional info provided by EV
   */
  public void setExtraInfo(@Nullable String extraInfo) {
    if (!isValidExtraInfo(extraInfo)) {
      throw new PropertyConstraintException(extraInfo, "extraInfo is invalid");
    }
    this.extraInfo = extraInfo;
  }

  /**
   * Returns whether the given extraInfo is valid
   *
   * @param extraInfo the extraInfo to check the validity of
   * @return {@code true} if extraInfo is valid, {@code false} if not
   */
  private boolean isValidExtraInfo(@Nullable String extraInfo) {
    return extraInfo == null || extraInfo.length() <= 200;
  }

  /**
   * Adds optional info provided by EV.
   *
   * @param extraInfo Optional info provided by EV
   * @return this
   */
  public NotifyDERAlarmRequest withExtraInfo(@Nullable String extraInfo) {
    setExtraInfo(extraInfo);
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
  public NotifyDERAlarmRequest withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  @Override
  public boolean validate() {
    return isValidControlType(controlType)
        && isValidTimestamp(timestamp)
        && isValidExtraInfo(extraInfo)
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
    NotifyDERAlarmRequest that = (NotifyDERAlarmRequest) o;
    return Objects.equals(controlType, that.controlType)
        && Objects.equals(gridEventFault, that.gridEventFault)
        && Objects.equals(alarmEnded, that.alarmEnded)
        && Objects.equals(timestamp, that.timestamp)
        && Objects.equals(extraInfo, that.extraInfo)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(controlType, gridEventFault, alarmEnded, timestamp, extraInfo, customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("controlType", controlType)
        .add("gridEventFault", gridEventFault)
        .add("alarmEnded", alarmEnded)
        .add("timestamp", timestamp)
        .add("extraInfo", extraInfo)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
