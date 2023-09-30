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
import java.util.Objects;
import javax.annotation.Nullable;

/** More information about the status. */
public final class StatusInfo {
  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * A predefined code for the reason why the status is returned in this response. The string is
   * case-insensitive.
   */
  private String reasonCode;

  /** Additional text to provide detailed information. */
  @Nullable private String additionalInfo;

  /**
   * Constructor for the StatusInfo class
   *
   * @param reasonCode A predefined code for the reason why the status is returned in this response.
   *     The string is case-insensitive.
   */
  public StatusInfo(String reasonCode) {
    setReasonCode(reasonCode);
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
  public StatusInfo withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  /**
   * Gets a predefined code for the reason why the status is returned in this response. The string
   * is case-insensitive.
   *
   * @return A predefined code for the reason why the status is returned in this response
   */
  public String getReasonCode() {
    return reasonCode;
  }

  /**
   * Sets a predefined code for the reason why the status is returned in this response. The string
   * is case-insensitive.
   *
   * @param reasonCode A predefined code for the reason why the status is returned in this response
   */
  public void setReasonCode(String reasonCode) {
    if (!isValidReasonCode(reasonCode)) {
      throw new PropertyConstraintException(reasonCode, "reasonCode is invalid");
    }
    this.reasonCode = reasonCode;
  }

  /**
   * Returns whether the given reasonCode is valid
   *
   * @param reasonCode the reasonCode to check the validity of
   * @return {@code true} if reasonCode is valid, {@code false} if not
   */
  private boolean isValidReasonCode(String reasonCode) {
    return reasonCode != null && reasonCode.length() <= 20;
  }

  /**
   * Gets additional text to provide detailed information.
   *
   * @return Additional text to provide detailed information
   */
  @Nullable
  public String getAdditionalInfo() {
    return additionalInfo;
  }

  /**
   * Sets additional text to provide detailed information.
   *
   * @param additionalInfo Additional text to provide detailed information
   */
  public void setAdditionalInfo(@Nullable String additionalInfo) {
    if (!isValidAdditionalInfo(additionalInfo)) {
      throw new PropertyConstraintException(additionalInfo, "additionalInfo is invalid");
    }
    this.additionalInfo = additionalInfo;
  }

  /**
   * Returns whether the given additionalInfo is valid
   *
   * @param additionalInfo the additionalInfo to check the validity of
   * @return {@code true} if additionalInfo is valid, {@code false} if not
   */
  private boolean isValidAdditionalInfo(@Nullable String additionalInfo) {
    return additionalInfo == null || additionalInfo.length() <= 512;
  }

  /**
   * Adds additional text to provide detailed information.
   *
   * @param additionalInfo Additional text to provide detailed information
   * @return this
   */
  public StatusInfo withAdditionalInfo(@Nullable String additionalInfo) {
    setAdditionalInfo(additionalInfo);
    return this;
  }

  public boolean validate() {
    return isValidCustomData(customData)
        && isValidReasonCode(reasonCode)
        && isValidAdditionalInfo(additionalInfo);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StatusInfo that = (StatusInfo) o;
    return Objects.equals(customData, that.customData)
        && Objects.equals(reasonCode, that.reasonCode)
        && Objects.equals(additionalInfo, that.additionalInfo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customData, reasonCode, additionalInfo);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("reasonCode", reasonCode)
        .add("additionalInfo", additionalInfo)
        .add("isValid", validate())
        .toString();
  }
}
