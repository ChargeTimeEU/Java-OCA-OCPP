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
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * GetLocalListVersionResponse
 *
 * <p>OCPP 2.0.1 FINAL
 */
public final class GetLocalListVersionResponse extends Confirmation {
  /** Custom data */
  @Nullable private CustomData customData;

  /** The current version number of the local authorization list in the Charging Station. */
  private Integer versionNumber;

  /**
   * Constructor for the GetLocalListVersionResponse class
   *
   * @param versionNumber The current version number of the local authorization list in the Charging
   *     Station.
   */
  public GetLocalListVersionResponse(Integer versionNumber) {
    setVersionNumber(versionNumber);
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
  public GetLocalListVersionResponse withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  /**
   * Gets the current version number of the local authorization list in the Charging Station.
   *
   * @return The current version number of the local authorization list in the Charging Station
   */
  public Integer getVersionNumber() {
    return versionNumber;
  }

  /**
   * Sets the current version number of the local authorization list in the Charging Station.
   *
   * @param versionNumber The current version number of the local authorization list in the Charging
   *     Station
   */
  public void setVersionNumber(Integer versionNumber) {
    if (!isValidVersionNumber(versionNumber)) {
      throw new PropertyConstraintException(versionNumber, "versionNumber is invalid");
    }
    this.versionNumber = versionNumber;
  }

  /**
   * Returns whether the given versionNumber is valid
   *
   * @param versionNumber the versionNumber to check the validity of
   * @return {@code true} if versionNumber is valid, {@code false} if not
   */
  private boolean isValidVersionNumber(Integer versionNumber) {
    return versionNumber != null;
  }

  @Override
  public boolean validate() {
    return isValidCustomData(customData) && isValidVersionNumber(versionNumber);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetLocalListVersionResponse that = (GetLocalListVersionResponse) o;
    return Objects.equals(customData, that.customData)
        && Objects.equals(versionNumber, that.versionNumber);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customData, versionNumber);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("versionNumber", versionNumber)
        .add("isValid", validate())
        .toString();
  }
}
