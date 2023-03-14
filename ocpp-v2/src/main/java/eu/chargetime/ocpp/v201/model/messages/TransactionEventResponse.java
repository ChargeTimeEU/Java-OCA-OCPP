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
import eu.chargetime.ocpp.v201.model.types.IdTokenInfo;
import eu.chargetime.ocpp.v201.model.types.MessageContent;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * TransactionEventResponse
 *
 * <p>OCPP 2.0.1 FINAL
 */
public final class TransactionEventResponse extends Confirmation {
  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * SHALL only be sent when charging has ended. Final total cost of this transaction, including
   * taxes. In the currency configured with the Configuration Variable: `Currency`. When omitted,
   * the transaction was NOT free. To indicate a free transaction, the CSMS SHALL send 0.00.
   */
  @Nullable private Double totalCost;

  /**
   * Priority from a business point of view. Default priority is 0, The range is from -9 to 9.
   * Higher values indicate a higher priority. The chargingPriority in TransactionEventResponse is
   * temporarily, so it may not be set in the IdTokenInfoType afterwards. Also the chargingPriority
   * in TransactionEventResponse overrules the one in IdTokenInfoType.
   */
  @Nullable private Integer chargingPriority;

  /**
   * ID Token
   *
   * <p>Status information about an identifier. It is advised to not stop charging for a token that
   * expires during charging, as ExpiryDate is only used for caching purposes. If ExpiryDate is not
   * given, the status has no end date.
   */
  @Nullable private IdTokenInfo idTokenInfo;

  /**
   * Message Content
   *
   * <p>Message details, for a message to be displayed on a Charging Station.
   */
  @Nullable private MessageContent updatedPersonalMessage;

  /** Constructor for the TransactionEventResponse class */
  public TransactionEventResponse() {}

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

  /**
   * Gets SHALL only be sent when charging has ended. Final total cost of this transaction,
   * including taxes. In the currency configured with the Configuration Variable: `Currency`. When
   * omitted, the transaction was NOT free. To indicate a free transaction, the CSMS SHALL send
   * 0.00.
   *
   * @return SHALL only be sent when charging has ended
   */
  @Nullable
  public Double getTotalCost() {
    return totalCost;
  }

  /**
   * Sets SHALL only be sent when charging has ended. Final total cost of this transaction,
   * including taxes. In the currency configured with the Configuration Variable: `Currency`. When
   * omitted, the transaction was NOT free. To indicate a free transaction, the CSMS SHALL send
   * 0.00.
   *
   * @param totalCost SHALL only be sent when charging has ended
   */
  public void setTotalCost(@Nullable Double totalCost) {
    this.totalCost = totalCost;
  }

  /**
   * Adds SHALL only be sent when charging has ended. Final total cost of this transaction,
   * including taxes. In the currency configured with the Configuration Variable: `Currency`. When
   * omitted, the transaction was NOT free. To indicate a free transaction, the CSMS SHALL send
   * 0.00.
   *
   * @param totalCost SHALL only be sent when charging has ended
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
   * in TransactionEventResponse overrules the one in IdTokenInfoType.
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
   * in TransactionEventResponse overrules the one in IdTokenInfoType.
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
   * in TransactionEventResponse overrules the one in IdTokenInfoType.
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

  @Override
  public boolean validate() {
    return isValidCustomData(customData)
        && isValidIdTokenInfo(idTokenInfo)
        && isValidUpdatedPersonalMessage(updatedPersonalMessage);
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
    return Objects.equals(customData, that.customData)
        && Objects.equals(totalCost, that.totalCost)
        && Objects.equals(chargingPriority, that.chargingPriority)
        && Objects.equals(idTokenInfo, that.idTokenInfo)
        && Objects.equals(updatedPersonalMessage, that.updatedPersonalMessage);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        customData, totalCost, chargingPriority, idTokenInfo, updatedPersonalMessage);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("totalCost", totalCost)
        .add("chargingPriority", chargingPriority)
        .add("idTokenInfo", idTokenInfo)
        .add("updatedPersonalMessage", updatedPersonalMessage)
        .add("isValid", validate())
        .toString();
  }
}
