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
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * ClearDERControlRequest
 *
 * <p>OCPP 2.1 Edition 1 (c) OCA, Creative Commons Attribution-NoDerivatives 4.0 International
 * Public License
 */
public final class ClearDERControlRequest extends RequestWithId {
  /** True: clearing default DER controls. False: clearing scheduled controls. */
  private Boolean isDefault;

  /** Name of control settings to clear. Not used when controlId is provided. */
  @Nullable private DERControlEnum controlType;

  /** Id of control setting to clear. When omitted all settings for controlType are cleared. */
  @Nullable private String controlId;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the ClearDERControlRequest class
   *
   * @param isDefault True: clearing default DER controls. False: clearing scheduled controls.
   */
  public ClearDERControlRequest(Boolean isDefault) {
    setIsDefault(isDefault);
  }

  /**
   * Gets true: clearing default DER controls. False: clearing scheduled controls.
   *
   * @return True: clearing default DER controls
   */
  public Boolean getIsDefault() {
    return isDefault;
  }

  /**
   * Sets true: clearing default DER controls. False: clearing scheduled controls.
   *
   * @param isDefault True: clearing default DER controls
   */
  public void setIsDefault(Boolean isDefault) {
    if (!isValidIsDefault(isDefault)) {
      throw new PropertyConstraintException(isDefault, "isDefault is invalid");
    }
    this.isDefault = isDefault;
  }

  /**
   * Returns whether the given isDefault is valid
   *
   * @param isDefault the isDefault to check the validity of
   * @return {@code true} if isDefault is valid, {@code false} if not
   */
  private boolean isValidIsDefault(Boolean isDefault) {
    return isDefault != null;
  }

  /**
   * Gets name of control settings to clear. Not used when controlId is provided.
   *
   * @return Name of control settings to clear
   */
  @Nullable
  public DERControlEnum getControlType() {
    return controlType;
  }

  /**
   * Sets name of control settings to clear. Not used when controlId is provided.
   *
   * @param controlType Name of control settings to clear
   */
  public void setControlType(@Nullable DERControlEnum controlType) {
    this.controlType = controlType;
  }

  /**
   * Adds name of control settings to clear. Not used when controlId is provided.
   *
   * @param controlType Name of control settings to clear
   * @return this
   */
  public ClearDERControlRequest withControlType(@Nullable DERControlEnum controlType) {
    setControlType(controlType);
    return this;
  }

  /**
   * Gets id of control setting to clear. When omitted all settings for controlType are cleared.
   *
   * @return Id of control setting to clear
   */
  @Nullable
  public String getControlId() {
    return controlId;
  }

  /**
   * Sets id of control setting to clear. When omitted all settings for controlType are cleared.
   *
   * @param controlId Id of control setting to clear
   */
  public void setControlId(@Nullable String controlId) {
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
  private boolean isValidControlId(@Nullable String controlId) {
    return controlId == null || controlId.length() <= 36;
  }

  /**
   * Adds id of control setting to clear. When omitted all settings for controlType are cleared.
   *
   * @param controlId Id of control setting to clear
   * @return this
   */
  public ClearDERControlRequest withControlId(@Nullable String controlId) {
    setControlId(controlId);
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
  public ClearDERControlRequest withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  @Override
  public boolean validate() {
    return isValidIsDefault(isDefault)
        && isValidControlId(controlId)
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
    ClearDERControlRequest that = (ClearDERControlRequest) o;
    return Objects.equals(isDefault, that.isDefault)
        && Objects.equals(controlType, that.controlType)
        && Objects.equals(controlId, that.controlId)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(isDefault, controlType, controlId, customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("isDefault", isDefault)
        .add("controlType", controlType)
        .add("controlId", controlId)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
