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
import eu.chargetime.ocpp.model.RequestWithId;
import eu.chargetime.ocpp.utilities.MoreObjects;
import eu.chargetime.ocpp.v201.model.types.CustomData;
import eu.chargetime.ocpp.v201.model.types.EVSE;
import eu.chargetime.ocpp.v201.model.types.MessageTriggerEnum;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * TriggerMessageRequest
 *
 * <p>OCPP 2.0.1 FINAL
 */
public final class TriggerMessageRequest extends RequestWithId {
  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * EVSE
   *
   * <p>Electric Vehicle Supply Equipment
   */
  @Nullable private EVSE evse;

  /** Type of message to be triggered. */
  private MessageTriggerEnum requestedMessage;

  /**
   * Constructor for the TriggerMessageRequest class
   *
   * @param requestedMessage Type of message to be triggered.
   */
  public TriggerMessageRequest(MessageTriggerEnum requestedMessage) {
    setRequestedMessage(requestedMessage);
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
  public TriggerMessageRequest withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  /**
   * Gets electric Vehicle Supply Equipment
   *
   * @return Electric Vehicle Supply Equipment
   */
  @Nullable
  public EVSE getEvse() {
    return evse;
  }

  /**
   * Sets electric Vehicle Supply Equipment
   *
   * @param evse Electric Vehicle Supply Equipment
   */
  public void setEvse(@Nullable EVSE evse) {
    if (!isValidEvse(evse)) {
      throw new PropertyConstraintException(evse, "evse is invalid");
    }
    this.evse = evse;
  }

  /**
   * Returns whether the given evse is valid
   *
   * @param evse the evse to check the validity of
   * @return {@code true} if evse is valid, {@code false} if not
   */
  private boolean isValidEvse(@Nullable EVSE evse) {
    return evse == null || evse.validate();
  }

  /**
   * Adds electric Vehicle Supply Equipment
   *
   * @param evse Electric Vehicle Supply Equipment
   * @return this
   */
  public TriggerMessageRequest withEvse(@Nullable EVSE evse) {
    setEvse(evse);
    return this;
  }

  /**
   * Gets type of message to be triggered.
   *
   * @return Type of message to be triggered
   */
  public MessageTriggerEnum getRequestedMessage() {
    return requestedMessage;
  }

  /**
   * Sets type of message to be triggered.
   *
   * @param requestedMessage Type of message to be triggered
   */
  public void setRequestedMessage(MessageTriggerEnum requestedMessage) {
    if (!isValidRequestedMessage(requestedMessage)) {
      throw new PropertyConstraintException(requestedMessage, "requestedMessage is invalid");
    }
    this.requestedMessage = requestedMessage;
  }

  /**
   * Returns whether the given requestedMessage is valid
   *
   * @param requestedMessage the requestedMessage to check the validity of
   * @return {@code true} if requestedMessage is valid, {@code false} if not
   */
  private boolean isValidRequestedMessage(MessageTriggerEnum requestedMessage) {
    return requestedMessage != null;
  }

  @Override
  public boolean validate() {
    return isValidCustomData(customData)
        && isValidEvse(evse)
        && isValidRequestedMessage(requestedMessage);
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
    TriggerMessageRequest that = (TriggerMessageRequest) o;
    return Objects.equals(customData, that.customData)
        && Objects.equals(evse, that.evse)
        && Objects.equals(requestedMessage, that.requestedMessage);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customData, evse, requestedMessage);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("evse", evse)
        .add("requestedMessage", requestedMessage)
        .add("isValid", validate())
        .toString();
  }
}
