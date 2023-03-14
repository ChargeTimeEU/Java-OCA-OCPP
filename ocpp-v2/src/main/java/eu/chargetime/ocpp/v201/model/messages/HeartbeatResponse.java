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
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.utilities.MoreObjects;
import eu.chargetime.ocpp.v201.model.types.CustomData;
import java.time.ZonedDateTime;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * HeartbeatResponse
 *
 * <p>OCPP 2.0.1 FINAL
 */
public final class HeartbeatResponse extends Confirmation {
  /** Custom data */
  @Nullable private CustomData customData;

  /** The current time of the CSMS. */
  private ZonedDateTime currentTime;

  /**
   * Constructor for the HeartbeatResponse class
   *
   * @param currentTime The current time of the CSMS.
   */
  public HeartbeatResponse(ZonedDateTime currentTime) {
    setCurrentTime(currentTime);
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
  public HeartbeatResponse withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  /**
   * Gets the current time of the CSMS.
   *
   * @return The current time of the CSMS
   */
  public ZonedDateTime getCurrentTime() {
    return currentTime;
  }

  /**
   * Sets the current time of the CSMS.
   *
   * @param currentTime The current time of the CSMS
   */
  public void setCurrentTime(ZonedDateTime currentTime) {
    if (!isValidCurrentTime(currentTime)) {
      throw new PropertyConstraintException(currentTime, "currentTime is invalid");
    }
    this.currentTime = currentTime;
  }

  /**
   * Returns whether the given currentTime is valid
   *
   * @param currentTime the currentTime to check the validity of
   * @return {@code true} if currentTime is valid, {@code false} if not
   */
  private boolean isValidCurrentTime(ZonedDateTime currentTime) {
    return currentTime != null;
  }

  @Override
  public boolean validate() {
    return isValidCustomData(customData) && isValidCurrentTime(currentTime);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    HeartbeatResponse that = (HeartbeatResponse) o;
    return Objects.equals(customData, that.customData)
        && Objects.equals(currentTime, that.currentTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customData, currentTime);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("currentTime", currentTime)
        .add("isValid", validate())
        .toString();
  }
}
