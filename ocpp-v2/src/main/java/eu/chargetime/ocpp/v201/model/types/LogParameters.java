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

package eu.chargetime.ocpp.v201.model.types;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.utilities.MoreObjects;
import java.time.ZonedDateTime;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * Log
 *
 * <p>Generic class for the configuration of logging entries.
 */
public final class LogParameters {
  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Log. Remote Location. URI
   *
   * <p>The URL of the location at the remote system where the log should be stored.
   */
  private String remoteLocation;

  /**
   * Log. Oldest Timestamp. Date Time
   *
   * <p>The date and time of the oldest logging information to include in the diagnostics.
   */
  @Nullable private ZonedDateTime oldestTimestamp;

  /**
   * Log. Latest Timestamp. Date Time
   *
   * <p>The date and time of the latest logging information to include in the diagnostics.
   */
  @Nullable private ZonedDateTime latestTimestamp;

  /**
   * Constructor for the LogParameters class
   *
   * @param remoteLocation The URL of the location at the remote system where the log should be
   *     stored.
   */
  public LogParameters(String remoteLocation) {
    setRemoteLocation(remoteLocation);
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
  public LogParameters withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  /**
   * Gets the URL of the location at the remote system where the log should be stored.
   *
   * @return The URL of the location at the remote system where the log should be stored
   */
  public String getRemoteLocation() {
    return remoteLocation;
  }

  /**
   * Sets the URL of the location at the remote system where the log should be stored.
   *
   * @param remoteLocation The URL of the location at the remote system where the log should be
   *     stored
   */
  public void setRemoteLocation(String remoteLocation) {
    if (!isValidRemoteLocation(remoteLocation)) {
      throw new PropertyConstraintException(remoteLocation, "remoteLocation is invalid");
    }
    this.remoteLocation = remoteLocation;
  }

  /**
   * Returns whether the given remoteLocation is valid
   *
   * @param remoteLocation the remoteLocation to check the validity of
   * @return {@code true} if remoteLocation is valid, {@code false} if not
   */
  private boolean isValidRemoteLocation(String remoteLocation) {
    return remoteLocation != null && remoteLocation.length() <= 512;
  }

  /**
   * Gets the date and time of the oldest logging information to include in the diagnostics.
   *
   * @return The date and time of the oldest logging information to include in the diagnostics
   */
  @Nullable
  public ZonedDateTime getOldestTimestamp() {
    return oldestTimestamp;
  }

  /**
   * Sets the date and time of the oldest logging information to include in the diagnostics.
   *
   * @param oldestTimestamp The date and time of the oldest logging information to include in the
   *     diagnostics
   */
  public void setOldestTimestamp(@Nullable ZonedDateTime oldestTimestamp) {
    this.oldestTimestamp = oldestTimestamp;
  }

  /**
   * Adds the date and time of the oldest logging information to include in the diagnostics.
   *
   * @param oldestTimestamp The date and time of the oldest logging information to include in the
   *     diagnostics
   * @return this
   */
  public LogParameters withOldestTimestamp(@Nullable ZonedDateTime oldestTimestamp) {
    setOldestTimestamp(oldestTimestamp);
    return this;
  }

  /**
   * Gets the date and time of the latest logging information to include in the diagnostics.
   *
   * @return The date and time of the latest logging information to include in the diagnostics
   */
  @Nullable
  public ZonedDateTime getLatestTimestamp() {
    return latestTimestamp;
  }

  /**
   * Sets the date and time of the latest logging information to include in the diagnostics.
   *
   * @param latestTimestamp The date and time of the latest logging information to include in the
   *     diagnostics
   */
  public void setLatestTimestamp(@Nullable ZonedDateTime latestTimestamp) {
    this.latestTimestamp = latestTimestamp;
  }

  /**
   * Adds the date and time of the latest logging information to include in the diagnostics.
   *
   * @param latestTimestamp The date and time of the latest logging information to include in the
   *     diagnostics
   * @return this
   */
  public LogParameters withLatestTimestamp(@Nullable ZonedDateTime latestTimestamp) {
    setLatestTimestamp(latestTimestamp);
    return this;
  }

  public boolean validate() {
    return isValidCustomData(customData) && isValidRemoteLocation(remoteLocation);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LogParameters that = (LogParameters) o;
    return Objects.equals(customData, that.customData)
        && Objects.equals(remoteLocation, that.remoteLocation)
        && Objects.equals(oldestTimestamp, that.oldestTimestamp)
        && Objects.equals(latestTimestamp, that.latestTimestamp);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customData, remoteLocation, oldestTimestamp, latestTimestamp);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("remoteLocation", remoteLocation)
        .add("oldestTimestamp", oldestTimestamp)
        .add("latestTimestamp", latestTimestamp)
        .add("isValid", validate())
        .toString();
  }
}
