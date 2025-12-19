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
 * GetDERControlRequest
 *
 * <p>OCPP 2.1 Edition 1 (c) OCA, Creative Commons Attribution-NoDerivatives 4.0 International
 * Public License
 */
public final class GetDERControlRequest extends RequestWithId {
  /** RequestId to be used in ReportDERControlRequest. */
  private Integer requestId;

  /** True: get a default DER control. False: get a scheduled control. */
  @Nullable private Boolean isDefault;

  /** Type of control settings to retrieve. Not used when controlId is provided. */
  @Nullable private DERControlEnum controlType;

  /** Id of setting to get. When omitted all settings for controlType are retrieved. */
  @Nullable private String controlId;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the GetDERControlRequest class
   *
   * @param requestId RequestId to be used in ReportDERControlRequest.
   */
  public GetDERControlRequest(Integer requestId) {
    setRequestId(requestId);
  }

  /**
   * Gets requestId to be used in ReportDERControlRequest.
   *
   * @return RequestId to be used in ReportDERControlRequest
   */
  public Integer getRequestId() {
    return requestId;
  }

  /**
   * Sets requestId to be used in ReportDERControlRequest.
   *
   * @param requestId RequestId to be used in ReportDERControlRequest
   */
  public void setRequestId(Integer requestId) {
    if (!isValidRequestId(requestId)) {
      throw new PropertyConstraintException(requestId, "requestId is invalid");
    }
    this.requestId = requestId;
  }

  /**
   * Returns whether the given requestId is valid
   *
   * @param requestId the requestId to check the validity of
   * @return {@code true} if requestId is valid, {@code false} if not
   */
  private boolean isValidRequestId(Integer requestId) {
    return requestId != null;
  }

  /**
   * Gets true: get a default DER control. False: get a scheduled control.
   *
   * @return True: get a default DER control
   */
  @Nullable
  public Boolean getIsDefault() {
    return isDefault;
  }

  /**
   * Sets true: get a default DER control. False: get a scheduled control.
   *
   * @param isDefault True: get a default DER control
   */
  public void setIsDefault(@Nullable Boolean isDefault) {
    this.isDefault = isDefault;
  }

  /**
   * Adds true: get a default DER control. False: get a scheduled control.
   *
   * @param isDefault True: get a default DER control
   * @return this
   */
  public GetDERControlRequest withIsDefault(@Nullable Boolean isDefault) {
    setIsDefault(isDefault);
    return this;
  }

  /**
   * Gets type of control settings to retrieve. Not used when controlId is provided.
   *
   * @return Type of control settings to retrieve
   */
  @Nullable
  public DERControlEnum getControlType() {
    return controlType;
  }

  /**
   * Sets type of control settings to retrieve. Not used when controlId is provided.
   *
   * @param controlType Type of control settings to retrieve
   */
  public void setControlType(@Nullable DERControlEnum controlType) {
    this.controlType = controlType;
  }

  /**
   * Adds type of control settings to retrieve. Not used when controlId is provided.
   *
   * @param controlType Type of control settings to retrieve
   * @return this
   */
  public GetDERControlRequest withControlType(@Nullable DERControlEnum controlType) {
    setControlType(controlType);
    return this;
  }

  /**
   * Gets id of setting to get. When omitted all settings for controlType are retrieved.
   *
   * @return Id of setting to get
   */
  @Nullable
  public String getControlId() {
    return controlId;
  }

  /**
   * Sets id of setting to get. When omitted all settings for controlType are retrieved.
   *
   * @param controlId Id of setting to get
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
   * Adds id of setting to get. When omitted all settings for controlType are retrieved.
   *
   * @param controlId Id of setting to get
   * @return this
   */
  public GetDERControlRequest withControlId(@Nullable String controlId) {
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
  public GetDERControlRequest withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  @Override
  public boolean validate() {
    return isValidRequestId(requestId)
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
    GetDERControlRequest that = (GetDERControlRequest) o;
    return Objects.equals(requestId, that.requestId)
        && Objects.equals(isDefault, that.isDefault)
        && Objects.equals(controlType, that.controlType)
        && Objects.equals(controlId, that.controlId)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(requestId, isDefault, controlType, controlId, customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("requestId", requestId)
        .add("isDefault", isDefault)
        .add("controlType", controlType)
        .add("controlId", controlId)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
