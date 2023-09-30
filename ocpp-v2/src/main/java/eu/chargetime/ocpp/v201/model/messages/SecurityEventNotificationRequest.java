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
import eu.chargetime.ocpp.v201.model.types.CustomData;
import java.time.ZonedDateTime;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * SecurityEventNotificationRequest
 *
 * <p>OCPP 2.0.1 FINAL
 */
public final class SecurityEventNotificationRequest extends RequestWithId {
  /** Custom data */
  @Nullable private CustomData customData;

  /** Type of the security event. This value should be taken from the Security events list. */
  private String type;

  /** Date and time at which the event occurred. */
  private ZonedDateTime timestamp;

  /** Additional information about the occurred security event. */
  @Nullable private String techInfo;

  /**
   * Constructor for the SecurityEventNotificationRequest class
   *
   * @param type Type of the security event. This value should be taken from the Security events
   *     list.
   * @param timestamp Date and time at which the event occurred.
   */
  public SecurityEventNotificationRequest(String type, ZonedDateTime timestamp) {
    setType(type);
    setTimestamp(timestamp);
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
  public SecurityEventNotificationRequest withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  /**
   * Gets type of the security event. This value should be taken from the Security events list.
   *
   * @return Type of the security event
   */
  public String getType() {
    return type;
  }

  /**
   * Sets type of the security event. This value should be taken from the Security events list.
   *
   * @param type Type of the security event
   */
  public void setType(String type) {
    if (!isValidType(type)) {
      throw new PropertyConstraintException(type, "type is invalid");
    }
    this.type = type;
  }

  /**
   * Returns whether the given type is valid
   *
   * @param type the type to check the validity of
   * @return {@code true} if type is valid, {@code false} if not
   */
  private boolean isValidType(String type) {
    return type != null && type.length() <= 50;
  }

  /**
   * Gets date and time at which the event occurred.
   *
   * @return Date and time at which the event occurred
   */
  public ZonedDateTime getTimestamp() {
    return timestamp;
  }

  /**
   * Sets date and time at which the event occurred.
   *
   * @param timestamp Date and time at which the event occurred
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
   * Gets additional information about the occurred security event.
   *
   * @return Additional information about the occurred security event
   */
  @Nullable
  public String getTechInfo() {
    return techInfo;
  }

  /**
   * Sets additional information about the occurred security event.
   *
   * @param techInfo Additional information about the occurred security event
   */
  public void setTechInfo(@Nullable String techInfo) {
    if (!isValidTechInfo(techInfo)) {
      throw new PropertyConstraintException(techInfo, "techInfo is invalid");
    }
    this.techInfo = techInfo;
  }

  /**
   * Returns whether the given techInfo is valid
   *
   * @param techInfo the techInfo to check the validity of
   * @return {@code true} if techInfo is valid, {@code false} if not
   */
  private boolean isValidTechInfo(@Nullable String techInfo) {
    return techInfo == null || techInfo.length() <= 255;
  }

  /**
   * Adds additional information about the occurred security event.
   *
   * @param techInfo Additional information about the occurred security event
   * @return this
   */
  public SecurityEventNotificationRequest withTechInfo(@Nullable String techInfo) {
    setTechInfo(techInfo);
    return this;
  }

  @Override
  public boolean validate() {
    return isValidCustomData(customData)
        && isValidType(type)
        && isValidTimestamp(timestamp)
        && isValidTechInfo(techInfo);
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
    SecurityEventNotificationRequest that = (SecurityEventNotificationRequest) o;
    return Objects.equals(customData, that.customData)
        && Objects.equals(type, that.type)
        && Objects.equals(timestamp, that.timestamp)
        && Objects.equals(techInfo, that.techInfo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customData, type, timestamp, techInfo);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("type", type)
        .add("timestamp", timestamp)
        .add("techInfo", techInfo)
        .add("isValid", validate())
        .toString();
  }
}
