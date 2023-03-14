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
 * GetTransactionStatusResponse
 *
 * <p>OCPP 2.0.1 FINAL
 */
public final class GetTransactionStatusResponse extends Confirmation {
  /** Custom data */
  @Nullable private CustomData customData;

  /** Whether the transaction is still ongoing. */
  @Nullable private Boolean ongoingIndicator;

  /** Whether there are still message to be delivered. */
  private Boolean messagesInQueue;

  /**
   * Constructor for the GetTransactionStatusResponse class
   *
   * @param messagesInQueue Whether there are still message to be delivered.
   */
  public GetTransactionStatusResponse(Boolean messagesInQueue) {
    setMessagesInQueue(messagesInQueue);
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
  public GetTransactionStatusResponse withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  /**
   * Gets whether the transaction is still ongoing.
   *
   * @return Whether the transaction is still ongoing
   */
  @Nullable
  public Boolean getOngoingIndicator() {
    return ongoingIndicator;
  }

  /**
   * Sets whether the transaction is still ongoing.
   *
   * @param ongoingIndicator Whether the transaction is still ongoing
   */
  public void setOngoingIndicator(@Nullable Boolean ongoingIndicator) {
    this.ongoingIndicator = ongoingIndicator;
  }

  /**
   * Adds whether the transaction is still ongoing.
   *
   * @param ongoingIndicator Whether the transaction is still ongoing
   * @return this
   */
  public GetTransactionStatusResponse withOngoingIndicator(@Nullable Boolean ongoingIndicator) {
    setOngoingIndicator(ongoingIndicator);
    return this;
  }

  /**
   * Gets whether there are still message to be delivered.
   *
   * @return Whether there are still message to be delivered
   */
  public Boolean getMessagesInQueue() {
    return messagesInQueue;
  }

  /**
   * Sets whether there are still message to be delivered.
   *
   * @param messagesInQueue Whether there are still message to be delivered
   */
  public void setMessagesInQueue(Boolean messagesInQueue) {
    if (!isValidMessagesInQueue(messagesInQueue)) {
      throw new PropertyConstraintException(messagesInQueue, "messagesInQueue is invalid");
    }
    this.messagesInQueue = messagesInQueue;
  }

  /**
   * Returns whether the given messagesInQueue is valid
   *
   * @param messagesInQueue the messagesInQueue to check the validity of
   * @return {@code true} if messagesInQueue is valid, {@code false} if not
   */
  private boolean isValidMessagesInQueue(Boolean messagesInQueue) {
    return messagesInQueue != null;
  }

  @Override
  public boolean validate() {
    return isValidCustomData(customData) && isValidMessagesInQueue(messagesInQueue);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetTransactionStatusResponse that = (GetTransactionStatusResponse) o;
    return Objects.equals(customData, that.customData)
        && Objects.equals(ongoingIndicator, that.ongoingIndicator)
        && Objects.equals(messagesInQueue, that.messagesInQueue);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customData, ongoingIndicator, messagesInQueue);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("ongoingIndicator", ongoingIndicator)
        .add("messagesInQueue", messagesInQueue)
        .add("isValid", validate())
        .toString();
  }
}
