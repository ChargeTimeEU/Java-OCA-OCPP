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
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * UsePriorityChargingRequest
 *
 * <p>OCPP 2.1 Edition 1 (c) OCA, Creative Commons Attribution-NoDerivatives 4.0 International
 * Public License
 */
public final class UsePriorityChargingRequest extends RequestWithId {
  /** The transaction for which priority charging is requested. */
  private String transactionId;

  /** True to request priority charging. False to request stopping priority charging. */
  private Boolean activate;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the UsePriorityChargingRequest class
   *
   * @param transactionId The transaction for which priority charging is requested.
   * @param activate True to request priority charging. False to request stopping priority charging.
   */
  public UsePriorityChargingRequest(String transactionId, Boolean activate) {
    setTransactionId(transactionId);
    setActivate(activate);
  }

  /**
   * Gets the transaction for which priority charging is requested.
   *
   * @return The transaction for which priority charging is requested
   */
  public String getTransactionId() {
    return transactionId;
  }

  /**
   * Sets the transaction for which priority charging is requested.
   *
   * @param transactionId The transaction for which priority charging is requested
   */
  public void setTransactionId(String transactionId) {
    if (!isValidTransactionId(transactionId)) {
      throw new PropertyConstraintException(transactionId, "transactionId is invalid");
    }
    this.transactionId = transactionId;
  }

  /**
   * Returns whether the given transactionId is valid
   *
   * @param transactionId the transactionId to check the validity of
   * @return {@code true} if transactionId is valid, {@code false} if not
   */
  private boolean isValidTransactionId(String transactionId) {
    return transactionId != null && transactionId.length() <= 36;
  }

  /**
   * Gets true to request priority charging. False to request stopping priority charging.
   *
   * @return True to request priority charging
   */
  public Boolean getActivate() {
    return activate;
  }

  /**
   * Sets true to request priority charging. False to request stopping priority charging.
   *
   * @param activate True to request priority charging
   */
  public void setActivate(Boolean activate) {
    if (!isValidActivate(activate)) {
      throw new PropertyConstraintException(activate, "activate is invalid");
    }
    this.activate = activate;
  }

  /**
   * Returns whether the given activate is valid
   *
   * @param activate the activate to check the validity of
   * @return {@code true} if activate is valid, {@code false} if not
   */
  private boolean isValidActivate(Boolean activate) {
    return activate != null;
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
  public UsePriorityChargingRequest withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  @Override
  public boolean validate() {
    return isValidTransactionId(transactionId)
        && isValidActivate(activate)
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
    UsePriorityChargingRequest that = (UsePriorityChargingRequest) o;
    return Objects.equals(transactionId, that.transactionId)
        && Objects.equals(activate, that.activate)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(transactionId, activate, customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("transactionId", transactionId)
        .add("activate", activate)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
