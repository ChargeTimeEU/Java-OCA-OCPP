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
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.utilities.MoreObjects;
import eu.chargetime.ocpp.v21.model.types.CustomData;
import eu.chargetime.ocpp.v21.model.types.IdTokenInfo;
import eu.chargetime.ocpp.v21.model.types.MessageContent;
import eu.chargetime.ocpp.v21.model.types.TransactionLimit;
import java.util.Arrays;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * TransactionEventResponse
 *
 * <p>OCPP 2.1 Edition 1 (c) OCA, Creative Commons Attribution-NoDerivatives 4.0 International
 * Public License
 */
public final class TransactionEventResponse extends Confirmation {
  /**
   * When eventType of TransactionEventRequest is Updated, then this value contains the running
   * cost. When eventType of TransactionEventRequest is Ended, then this contains the final total
   * cost of this transaction, including taxes, in the currency configured with the Configuration
   * Variable: Currency. Absence of this value does not imply that the transaction was free. To
   * indicate a free transaction, the CSMS SHALL send a value of 0.00.
   */
  @Nullable private Double totalCost;

  /**
   * Priority from a business point of view. Default priority is 0, The range is from -9 to 9.
   * Higher values indicate a higher priority. The chargingPriority in TransactionEventResponse is
   * temporarily, so it may not be set in the IdTokenInfoType afterwards. Also the chargingPriority
   * in TransactionEventResponse has a higher priority than the one in IdTokenInfoType.
   */
  @Nullable private Integer chargingPriority;

  /**
   * Status information about an identifier. It is advised to not stop charging for a token that
   * expires during charging, as ExpiryDate is only used for caching purposes. If ExpiryDate is not
   * given, the status has no end date.
   */
  @Nullable private IdTokenInfo idTokenInfo;

  /** Cost, energy, time or SoC limit for a transaction. */
  @Nullable private TransactionLimit transactionLimit;

  /** Message details, for a message to be displayed on a Charging Station. */
  @Nullable private MessageContent updatedPersonalMessage;

  /** Message details, for a message to be displayed on a Charging Station. */
  @Nullable private MessageContent[] updatedPersonalMessageExtra;

  /** Custom data */
  @Nullable private CustomData customData;

  /** Constructor for the TransactionEventResponse class */
  public TransactionEventResponse() {}

  /**
   * Gets when eventType of TransactionEventRequest is Updated, then this value contains the running
   * cost. When eventType of TransactionEventRequest is Ended, then this contains the final total
   * cost of this transaction, including taxes, in the currency configured with the Configuration
   * Variable: Currency. Absence of this value does not imply that the transaction was free. To
   * indicate a free transaction, the CSMS SHALL send a value of 0.00.
   *
   * @return When eventType of TransactionEventRequest is Updated, then this value contains the
   *     running cost
   */
  @Nullable
  public Double getTotalCost() {
    return totalCost;
  }

  /**
   * Sets when eventType of TransactionEventRequest is Updated, then this value contains the running
   * cost. When eventType of TransactionEventRequest is Ended, then this contains the final total
   * cost of this transaction, including taxes, in the currency configured with the Configuration
   * Variable: Currency. Absence of this value does not imply that the transaction was free. To
   * indicate a free transaction, the CSMS SHALL send a value of 0.00.
   *
   * @param totalCost When eventType of TransactionEventRequest is Updated, then this value contains
   *     the running cost
   */
  public void setTotalCost(@Nullable Double totalCost) {
    this.totalCost = totalCost;
  }

  /**
   * Adds when eventType of TransactionEventRequest is Updated, then this value contains the running
   * cost. When eventType of TransactionEventRequest is Ended, then this contains the final total
   * cost of this transaction, including taxes, in the currency configured with the Configuration
   * Variable: Currency. Absence of this value does not imply that the transaction was free. To
   * indicate a free transaction, the CSMS SHALL send a value of 0.00.
   *
   * @param totalCost When eventType of TransactionEventRequest is Updated, then this value contains
   *     the running cost
   * @return this
   */
  public TransactionEventResponse withTotalCost(@Nullable Double totalCost) {
    setTotalCost(totalCost);
    return this;
  }

  /**
   * Gets priority from a business point of view. Default priority is 0, The range is from -9 to 9.
   * Higher values indicate a higher priority. The chargingPriority in TransactionEventResponse is
   * temporarily, so it may not be set in the IdTokenInfoType afterwards. Also the chargingPriority
   * in TransactionEventResponse has a higher priority than the one in IdTokenInfoType.
   *
   * @return Priority from a business point of view
   */
  @Nullable
  public Integer getChargingPriority() {
    return chargingPriority;
  }

  /**
   * Sets priority from a business point of view. Default priority is 0, The range is from -9 to 9.
   * Higher values indicate a higher priority. The chargingPriority in TransactionEventResponse is
   * temporarily, so it may not be set in the IdTokenInfoType afterwards. Also the chargingPriority
   * in TransactionEventResponse has a higher priority than the one in IdTokenInfoType.
   *
   * @param chargingPriority Priority from a business point of view
   */
  public void setChargingPriority(@Nullable Integer chargingPriority) {
    this.chargingPriority = chargingPriority;
  }

  /**
   * Adds priority from a business point of view. Default priority is 0, The range is from -9 to 9.
   * Higher values indicate a higher priority. The chargingPriority in TransactionEventResponse is
   * temporarily, so it may not be set in the IdTokenInfoType afterwards. Also the chargingPriority
   * in TransactionEventResponse has a higher priority than the one in IdTokenInfoType.
   *
   * @param chargingPriority Priority from a business point of view
   * @return this
   */
  public TransactionEventResponse withChargingPriority(@Nullable Integer chargingPriority) {
    setChargingPriority(chargingPriority);
    return this;
  }

  /**
   * Gets status information about an identifier. It is advised to not stop charging for a token
   * that expires during charging, as ExpiryDate is only used for caching purposes. If ExpiryDate is
   * not given, the status has no end date.
   *
   * @return Status information about an identifier
   */
  @Nullable
  public IdTokenInfo getIdTokenInfo() {
    return idTokenInfo;
  }

  /**
   * Sets status information about an identifier. It is advised to not stop charging for a token
   * that expires during charging, as ExpiryDate is only used for caching purposes. If ExpiryDate is
   * not given, the status has no end date.
   *
   * @param idTokenInfo Status information about an identifier
   */
  public void setIdTokenInfo(@Nullable IdTokenInfo idTokenInfo) {
    if (!isValidIdTokenInfo(idTokenInfo)) {
      throw new PropertyConstraintException(idTokenInfo, "idTokenInfo is invalid");
    }
    this.idTokenInfo = idTokenInfo;
  }

  /**
   * Returns whether the given idTokenInfo is valid
   *
   * @param idTokenInfo the idTokenInfo to check the validity of
   * @return {@code true} if idTokenInfo is valid, {@code false} if not
   */
  private boolean isValidIdTokenInfo(@Nullable IdTokenInfo idTokenInfo) {
    return idTokenInfo == null || idTokenInfo.validate();
  }

  /**
   * Adds status information about an identifier. It is advised to not stop charging for a token
   * that expires during charging, as ExpiryDate is only used for caching purposes. If ExpiryDate is
   * not given, the status has no end date.
   *
   * @param idTokenInfo Status information about an identifier
   * @return this
   */
  public TransactionEventResponse withIdTokenInfo(@Nullable IdTokenInfo idTokenInfo) {
    setIdTokenInfo(idTokenInfo);
    return this;
  }

  /**
   * Gets cost, energy, time or SoC limit for a transaction.
   *
   * @return Cost, energy, time or SoC limit for a transaction
   */
  @Nullable
  public TransactionLimit getTransactionLimit() {
    return transactionLimit;
  }

  /**
   * Sets cost, energy, time or SoC limit for a transaction.
   *
   * @param transactionLimit Cost, energy, time or SoC limit for a transaction
   */
  public void setTransactionLimit(@Nullable TransactionLimit transactionLimit) {
    if (!isValidTransactionLimit(transactionLimit)) {
      throw new PropertyConstraintException(transactionLimit, "transactionLimit is invalid");
    }
    this.transactionLimit = transactionLimit;
  }

  /**
   * Returns whether the given transactionLimit is valid
   *
   * @param transactionLimit the transactionLimit to check the validity of
   * @return {@code true} if transactionLimit is valid, {@code false} if not
   */
  private boolean isValidTransactionLimit(@Nullable TransactionLimit transactionLimit) {
    return transactionLimit == null || transactionLimit.validate();
  }

  /**
   * Adds cost, energy, time or SoC limit for a transaction.
   *
   * @param transactionLimit Cost, energy, time or SoC limit for a transaction
   * @return this
   */
  public TransactionEventResponse withTransactionLimit(
      @Nullable TransactionLimit transactionLimit) {
    setTransactionLimit(transactionLimit);
    return this;
  }

  /**
   * Gets message details, for a message to be displayed on a Charging Station.
   *
   * @return Message details, for a message to be displayed on a Charging Station
   */
  @Nullable
  public MessageContent getUpdatedPersonalMessage() {
    return updatedPersonalMessage;
  }

  /**
   * Sets message details, for a message to be displayed on a Charging Station.
   *
   * @param updatedPersonalMessage Message details, for a message to be displayed on a Charging
   *     Station
   */
  public void setUpdatedPersonalMessage(@Nullable MessageContent updatedPersonalMessage) {
    if (!isValidUpdatedPersonalMessage(updatedPersonalMessage)) {
      throw new PropertyConstraintException(
          updatedPersonalMessage, "updatedPersonalMessage is invalid");
    }
    this.updatedPersonalMessage = updatedPersonalMessage;
  }

  /**
   * Returns whether the given updatedPersonalMessage is valid
   *
   * @param updatedPersonalMessage the updatedPersonalMessage to check the validity of
   * @return {@code true} if updatedPersonalMessage is valid, {@code false} if not
   */
  private boolean isValidUpdatedPersonalMessage(@Nullable MessageContent updatedPersonalMessage) {
    return updatedPersonalMessage == null || updatedPersonalMessage.validate();
  }

  /**
   * Adds message details, for a message to be displayed on a Charging Station.
   *
   * @param updatedPersonalMessage Message details, for a message to be displayed on a Charging
   *     Station
   * @return this
   */
  public TransactionEventResponse withUpdatedPersonalMessage(
      @Nullable MessageContent updatedPersonalMessage) {
    setUpdatedPersonalMessage(updatedPersonalMessage);
    return this;
  }

  /**
   * Gets message details, for a message to be displayed on a Charging Station.
   *
   * @return Message details, for a message to be displayed on a Charging Station
   */
  @Nullable
  public MessageContent[] getUpdatedPersonalMessageExtra() {
    return updatedPersonalMessageExtra;
  }

  /**
   * Sets message details, for a message to be displayed on a Charging Station.
   *
   * @param updatedPersonalMessageExtra Message details, for a message to be displayed on a Charging
   *     Station
   */
  public void setUpdatedPersonalMessageExtra(
      @Nullable MessageContent[] updatedPersonalMessageExtra) {
    if (!isValidUpdatedPersonalMessageExtra(updatedPersonalMessageExtra)) {
      throw new PropertyConstraintException(
          updatedPersonalMessageExtra, "updatedPersonalMessageExtra is invalid");
    }
    this.updatedPersonalMessageExtra = updatedPersonalMessageExtra;
  }

  /**
   * Returns whether the given updatedPersonalMessageExtra is valid
   *
   * @param updatedPersonalMessageExtra the updatedPersonalMessageExtra to check the validity of
   * @return {@code true} if updatedPersonalMessageExtra is valid, {@code false} if not
   */
  private boolean isValidUpdatedPersonalMessageExtra(
      @Nullable MessageContent[] updatedPersonalMessageExtra) {
    return updatedPersonalMessageExtra == null
        || (updatedPersonalMessageExtra.length >= 1
            && updatedPersonalMessageExtra.length <= 4
            && Arrays.stream(updatedPersonalMessageExtra).allMatch(item -> item.validate()));
  }

  /**
   * Adds message details, for a message to be displayed on a Charging Station.
   *
   * @param updatedPersonalMessageExtra Message details, for a message to be displayed on a Charging
   *     Station
   * @return this
   */
  public TransactionEventResponse withUpdatedPersonalMessageExtra(
      @Nullable MessageContent[] updatedPersonalMessageExtra) {
    setUpdatedPersonalMessageExtra(updatedPersonalMessageExtra);
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
  public TransactionEventResponse withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  @Override
  public boolean validate() {
    return isValidIdTokenInfo(idTokenInfo)
        && isValidTransactionLimit(transactionLimit)
        && isValidUpdatedPersonalMessage(updatedPersonalMessage)
        && isValidUpdatedPersonalMessageExtra(updatedPersonalMessageExtra)
        && isValidCustomData(customData);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TransactionEventResponse that = (TransactionEventResponse) o;
    return Objects.equals(totalCost, that.totalCost)
        && Objects.equals(chargingPriority, that.chargingPriority)
        && Objects.equals(idTokenInfo, that.idTokenInfo)
        && Objects.equals(transactionLimit, that.transactionLimit)
        && Objects.equals(updatedPersonalMessage, that.updatedPersonalMessage)
        && Arrays.equals(updatedPersonalMessageExtra, that.updatedPersonalMessageExtra)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        totalCost,
        chargingPriority,
        idTokenInfo,
        transactionLimit,
        updatedPersonalMessage,
        Arrays.hashCode(updatedPersonalMessageExtra),
        customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("totalCost", totalCost)
        .add("chargingPriority", chargingPriority)
        .add("idTokenInfo", idTokenInfo)
        .add("transactionLimit", transactionLimit)
        .add("updatedPersonalMessage", updatedPersonalMessage)
        .add("updatedPersonalMessageExtra", updatedPersonalMessageExtra)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
