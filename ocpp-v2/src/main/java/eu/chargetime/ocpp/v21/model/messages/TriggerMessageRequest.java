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
import eu.chargetime.ocpp.v21.model.types.EVSE;
import eu.chargetime.ocpp.v21.model.types.MessageTriggerEnum;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * TriggerMessageRequest
 *
 * <p>OCPP 2.1 Edition 1 (c) OCA, Creative Commons Attribution-NoDerivatives 4.0 International
 * Public License
 */
public final class TriggerMessageRequest extends RequestWithId {
  /** Electric Vehicle Supply Equipment */
  @Nullable private EVSE evse;

  /** Type of message to be triggered. */
  private MessageTriggerEnum requestedMessage;

  /**
   * When requestedMessage = `CustomTrigger` this will trigger sending the corresponding message in
   * field customTrigger, if supported by Charging Station.
   */
  @Nullable private String customTrigger;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the TriggerMessageRequest class
   *
   * @param requestedMessage Type of message to be triggered.
   */
  public TriggerMessageRequest(MessageTriggerEnum requestedMessage) {
    setRequestedMessage(requestedMessage);
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

  /**
   * Gets when requestedMessage = `CustomTrigger` this will trigger sending the corresponding
   * message in field customTrigger, if supported by Charging Station.
   *
   * @return When requestedMessage = `CustomTrigger` this will trigger sending the corresponding
   *     message in field customTrigger, if supported by Charging Station
   */
  @Nullable
  public String getCustomTrigger() {
    return customTrigger;
  }

  /**
   * Sets when requestedMessage = `CustomTrigger` this will trigger sending the corresponding
   * message in field customTrigger, if supported by Charging Station.
   *
   * @param customTrigger When requestedMessage = `CustomTrigger` this will trigger sending the
   *     corresponding message in field customTrigger, if supported by Charging Station
   */
  public void setCustomTrigger(@Nullable String customTrigger) {
    if (!isValidCustomTrigger(customTrigger)) {
      throw new PropertyConstraintException(customTrigger, "customTrigger is invalid");
    }
    this.customTrigger = customTrigger;
  }

  /**
   * Returns whether the given customTrigger is valid
   *
   * @param customTrigger the customTrigger to check the validity of
   * @return {@code true} if customTrigger is valid, {@code false} if not
   */
  private boolean isValidCustomTrigger(@Nullable String customTrigger) {
    return customTrigger == null || customTrigger.length() <= 50;
  }

  /**
   * Adds when requestedMessage = `CustomTrigger` this will trigger sending the corresponding
   * message in field customTrigger, if supported by Charging Station.
   *
   * @param customTrigger When requestedMessage = `CustomTrigger` this will trigger sending the
   *     corresponding message in field customTrigger, if supported by Charging Station
   * @return this
   */
  public TriggerMessageRequest withCustomTrigger(@Nullable String customTrigger) {
    setCustomTrigger(customTrigger);
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
  public TriggerMessageRequest withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  @Override
  public boolean validate() {
    return isValidEvse(evse)
        && isValidRequestedMessage(requestedMessage)
        && isValidCustomTrigger(customTrigger)
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
    TriggerMessageRequest that = (TriggerMessageRequest) o;
    return Objects.equals(evse, that.evse)
        && Objects.equals(requestedMessage, that.requestedMessage)
        && Objects.equals(customTrigger, that.customTrigger)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(evse, requestedMessage, customTrigger, customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("evse", evse)
        .add("requestedMessage", requestedMessage)
        .add("customTrigger", customTrigger)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
