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
import eu.chargetime.ocpp.v21.model.types.EnergyTransferModeEnum;
import java.util.Arrays;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * NotifyAllowedEnergyTransferRequest
 *
 * <p>OCPP 2.1 Edition 1 (c) OCA, Creative Commons Attribution-NoDerivatives 4.0 International
 * Public License
 */
public final class NotifyAllowedEnergyTransferRequest extends RequestWithId {
  /** The transaction for which the allowed energy transfer is allowed. */
  private String transactionId;

  /** Modes of energy transfer that are accepted by CSMS. */
  private EnergyTransferModeEnum[] allowedEnergyTransfer;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the NotifyAllowedEnergyTransferRequest class
   *
   * @param transactionId The transaction for which the allowed energy transfer is allowed.
   * @param allowedEnergyTransfer Modes of energy transfer that are accepted by CSMS.
   */
  public NotifyAllowedEnergyTransferRequest(
      String transactionId, EnergyTransferModeEnum[] allowedEnergyTransfer) {
    setTransactionId(transactionId);
    setAllowedEnergyTransfer(allowedEnergyTransfer);
  }

  /**
   * Gets the transaction for which the allowed energy transfer is allowed.
   *
   * @return The transaction for which the allowed energy transfer is allowed
   */
  public String getTransactionId() {
    return transactionId;
  }

  /**
   * Sets the transaction for which the allowed energy transfer is allowed.
   *
   * @param transactionId The transaction for which the allowed energy transfer is allowed
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
   * Gets modes of energy transfer that are accepted by CSMS.
   *
   * @return Modes of energy transfer that are accepted by CSMS
   */
  public EnergyTransferModeEnum[] getAllowedEnergyTransfer() {
    return allowedEnergyTransfer;
  }

  /**
   * Sets modes of energy transfer that are accepted by CSMS.
   *
   * @param allowedEnergyTransfer Modes of energy transfer that are accepted by CSMS
   */
  public void setAllowedEnergyTransfer(EnergyTransferModeEnum[] allowedEnergyTransfer) {
    if (!isValidAllowedEnergyTransfer(allowedEnergyTransfer)) {
      throw new PropertyConstraintException(
          allowedEnergyTransfer, "allowedEnergyTransfer is invalid");
    }
    this.allowedEnergyTransfer = allowedEnergyTransfer;
  }

  /**
   * Returns whether the given allowedEnergyTransfer is valid
   *
   * @param allowedEnergyTransfer the allowedEnergyTransfer to check the validity of
   * @return {@code true} if allowedEnergyTransfer is valid, {@code false} if not
   */
  private boolean isValidAllowedEnergyTransfer(EnergyTransferModeEnum[] allowedEnergyTransfer) {
    return allowedEnergyTransfer != null && allowedEnergyTransfer.length >= 1;
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
  public NotifyAllowedEnergyTransferRequest withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  @Override
  public boolean validate() {
    return isValidTransactionId(transactionId)
        && isValidAllowedEnergyTransfer(allowedEnergyTransfer)
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
    NotifyAllowedEnergyTransferRequest that = (NotifyAllowedEnergyTransferRequest) o;
    return Objects.equals(transactionId, that.transactionId)
        && Arrays.equals(allowedEnergyTransfer, that.allowedEnergyTransfer)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(transactionId, Arrays.hashCode(allowedEnergyTransfer), customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("transactionId", transactionId)
        .add("allowedEnergyTransfer", allowedEnergyTransfer)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
