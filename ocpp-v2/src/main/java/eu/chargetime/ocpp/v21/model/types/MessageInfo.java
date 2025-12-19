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

package eu.chargetime.ocpp.v21.model.types;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.utilities.MoreObjects;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Objects;
import javax.annotation.Nullable;

/** Message details, for a message to be displayed on a Charging Station. */
public final class MessageInfo {
  /** A physical or logical component */
  @Nullable private Component display;

  /**
   * Unique id within an exchange context. It is defined within the OCPP context as a positive
   * Integer value (greater or equal to zero).
   */
  private Integer id;

  /** With what priority should this message be shown */
  private MessagePriorityEnum priority;

  /**
   * During what state should this message be shown. When omitted this message should be shown in
   * any state of the Charging Station.
   */
  @Nullable private MessageStateEnum state;

  /** From what date-time should this message be shown. If omitted: directly. */
  @Nullable private ZonedDateTime startDateTime;

  /**
   * Until what date-time should this message be shown, after this date/time this message SHALL be
   * removed.
   */
  @Nullable private ZonedDateTime endDateTime;

  /**
   * During which transaction shall this message be shown. Message SHALL be removed by the Charging
   * Station after transaction has ended.
   */
  @Nullable private String transactionId;

  /** Message details, for a message to be displayed on a Charging Station. */
  private MessageContent message;

  /** Message details, for a message to be displayed on a Charging Station. */
  @Nullable private MessageContent[] messageExtra;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the MessageInfo class
   *
   * @param id Unique id within an exchange context. It is defined within the OCPP context as a
   *     positive Integer value (greater or equal to zero).
   * @param priority With what priority should this message be shown
   * @param message Message details, for a message to be displayed on a Charging Station.
   */
  public MessageInfo(Integer id, MessagePriorityEnum priority, MessageContent message) {
    setId(id);
    setPriority(priority);
    setMessage(message);
  }

  /**
   * Gets a physical or logical component
   *
   * @return A physical or logical component
   */
  @Nullable
  public Component getDisplay() {
    return display;
  }

  /**
   * Sets a physical or logical component
   *
   * @param display A physical or logical component
   */
  public void setDisplay(@Nullable Component display) {
    if (!isValidDisplay(display)) {
      throw new PropertyConstraintException(display, "display is invalid");
    }
    this.display = display;
  }

  /**
   * Returns whether the given display is valid
   *
   * @param display the display to check the validity of
   * @return {@code true} if display is valid, {@code false} if not
   */
  private boolean isValidDisplay(@Nullable Component display) {
    return display == null || display.validate();
  }

  /**
   * Adds a physical or logical component
   *
   * @param display A physical or logical component
   * @return this
   */
  public MessageInfo withDisplay(@Nullable Component display) {
    setDisplay(display);
    return this;
  }

  /**
   * Gets unique id within an exchange context. It is defined within the OCPP context as a positive
   * Integer value (greater or equal to zero).
   *
   * @return Unique id within an exchange context
   */
  public Integer getId() {
    return id;
  }

  /**
   * Sets unique id within an exchange context. It is defined within the OCPP context as a positive
   * Integer value (greater or equal to zero).
   *
   * @param id Unique id within an exchange context
   */
  public void setId(Integer id) {
    if (!isValidId(id)) {
      throw new PropertyConstraintException(id, "id is invalid");
    }
    this.id = id;
  }

  /**
   * Returns whether the given id is valid
   *
   * @param id the id to check the validity of
   * @return {@code true} if id is valid, {@code false} if not
   */
  private boolean isValidId(Integer id) {
    return id != null && id >= 0;
  }

  /**
   * Gets with what priority should this message be shown
   *
   * @return With what priority should this message be shown
   */
  public MessagePriorityEnum getPriority() {
    return priority;
  }

  /**
   * Sets with what priority should this message be shown
   *
   * @param priority With what priority should this message be shown
   */
  public void setPriority(MessagePriorityEnum priority) {
    if (!isValidPriority(priority)) {
      throw new PropertyConstraintException(priority, "priority is invalid");
    }
    this.priority = priority;
  }

  /**
   * Returns whether the given priority is valid
   *
   * @param priority the priority to check the validity of
   * @return {@code true} if priority is valid, {@code false} if not
   */
  private boolean isValidPriority(MessagePriorityEnum priority) {
    return priority != null;
  }

  /**
   * Gets during what state should this message be shown. When omitted this message should be shown
   * in any state of the Charging Station.
   *
   * @return During what state should this message be shown
   */
  @Nullable
  public MessageStateEnum getState() {
    return state;
  }

  /**
   * Sets during what state should this message be shown. When omitted this message should be shown
   * in any state of the Charging Station.
   *
   * @param state During what state should this message be shown
   */
  public void setState(@Nullable MessageStateEnum state) {
    this.state = state;
  }

  /**
   * Adds during what state should this message be shown. When omitted this message should be shown
   * in any state of the Charging Station.
   *
   * @param state During what state should this message be shown
   * @return this
   */
  public MessageInfo withState(@Nullable MessageStateEnum state) {
    setState(state);
    return this;
  }

  /**
   * Gets from what date-time should this message be shown. If omitted: directly.
   *
   * @return From what date-time should this message be shown
   */
  @Nullable
  public ZonedDateTime getStartDateTime() {
    return startDateTime;
  }

  /**
   * Sets from what date-time should this message be shown. If omitted: directly.
   *
   * @param startDateTime From what date-time should this message be shown
   */
  public void setStartDateTime(@Nullable ZonedDateTime startDateTime) {
    this.startDateTime = startDateTime;
  }

  /**
   * Adds from what date-time should this message be shown. If omitted: directly.
   *
   * @param startDateTime From what date-time should this message be shown
   * @return this
   */
  public MessageInfo withStartDateTime(@Nullable ZonedDateTime startDateTime) {
    setStartDateTime(startDateTime);
    return this;
  }

  /**
   * Gets until what date-time should this message be shown, after this date/time this message SHALL
   * be removed.
   *
   * @return Until what date-time should this message be shown, after this date/time this message
   *     SHALL be removed
   */
  @Nullable
  public ZonedDateTime getEndDateTime() {
    return endDateTime;
  }

  /**
   * Sets until what date-time should this message be shown, after this date/time this message SHALL
   * be removed.
   *
   * @param endDateTime Until what date-time should this message be shown, after this date/time this
   *     message SHALL be removed
   */
  public void setEndDateTime(@Nullable ZonedDateTime endDateTime) {
    this.endDateTime = endDateTime;
  }

  /**
   * Adds until what date-time should this message be shown, after this date/time this message SHALL
   * be removed.
   *
   * @param endDateTime Until what date-time should this message be shown, after this date/time this
   *     message SHALL be removed
   * @return this
   */
  public MessageInfo withEndDateTime(@Nullable ZonedDateTime endDateTime) {
    setEndDateTime(endDateTime);
    return this;
  }

  /**
   * Gets during which transaction shall this message be shown. Message SHALL be removed by the
   * Charging Station after transaction has ended.
   *
   * @return During which transaction shall this message be shown
   */
  @Nullable
  public String getTransactionId() {
    return transactionId;
  }

  /**
   * Sets during which transaction shall this message be shown. Message SHALL be removed by the
   * Charging Station after transaction has ended.
   *
   * @param transactionId During which transaction shall this message be shown
   */
  public void setTransactionId(@Nullable String transactionId) {
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
  private boolean isValidTransactionId(@Nullable String transactionId) {
    return transactionId == null || transactionId.length() <= 36;
  }

  /**
   * Adds during which transaction shall this message be shown. Message SHALL be removed by the
   * Charging Station after transaction has ended.
   *
   * @param transactionId During which transaction shall this message be shown
   * @return this
   */
  public MessageInfo withTransactionId(@Nullable String transactionId) {
    setTransactionId(transactionId);
    return this;
  }

  /**
   * Gets message details, for a message to be displayed on a Charging Station.
   *
   * @return Message details, for a message to be displayed on a Charging Station
   */
  public MessageContent getMessage() {
    return message;
  }

  /**
   * Sets message details, for a message to be displayed on a Charging Station.
   *
   * @param message Message details, for a message to be displayed on a Charging Station
   */
  public void setMessage(MessageContent message) {
    if (!isValidMessage(message)) {
      throw new PropertyConstraintException(message, "message is invalid");
    }
    this.message = message;
  }

  /**
   * Returns whether the given message is valid
   *
   * @param message the message to check the validity of
   * @return {@code true} if message is valid, {@code false} if not
   */
  private boolean isValidMessage(MessageContent message) {
    return message != null && message.validate();
  }

  /**
   * Gets message details, for a message to be displayed on a Charging Station.
   *
   * @return Message details, for a message to be displayed on a Charging Station
   */
  @Nullable
  public MessageContent[] getMessageExtra() {
    return messageExtra;
  }

  /**
   * Sets message details, for a message to be displayed on a Charging Station.
   *
   * @param messageExtra Message details, for a message to be displayed on a Charging Station
   */
  public void setMessageExtra(@Nullable MessageContent[] messageExtra) {
    if (!isValidMessageExtra(messageExtra)) {
      throw new PropertyConstraintException(messageExtra, "messageExtra is invalid");
    }
    this.messageExtra = messageExtra;
  }

  /**
   * Returns whether the given messageExtra is valid
   *
   * @param messageExtra the messageExtra to check the validity of
   * @return {@code true} if messageExtra is valid, {@code false} if not
   */
  private boolean isValidMessageExtra(@Nullable MessageContent[] messageExtra) {
    return messageExtra == null
        || (messageExtra.length >= 1
            && messageExtra.length <= 4
            && Arrays.stream(messageExtra).allMatch(item -> item.validate()));
  }

  /**
   * Adds message details, for a message to be displayed on a Charging Station.
   *
   * @param messageExtra Message details, for a message to be displayed on a Charging Station
   * @return this
   */
  public MessageInfo withMessageExtra(@Nullable MessageContent[] messageExtra) {
    setMessageExtra(messageExtra);
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
  public MessageInfo withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  public boolean validate() {
    return isValidDisplay(display)
        && isValidId(id)
        && isValidPriority(priority)
        && isValidTransactionId(transactionId)
        && isValidMessage(message)
        && isValidMessageExtra(messageExtra)
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
    MessageInfo that = (MessageInfo) o;
    return Objects.equals(display, that.display)
        && Objects.equals(id, that.id)
        && Objects.equals(priority, that.priority)
        && Objects.equals(state, that.state)
        && Objects.equals(startDateTime, that.startDateTime)
        && Objects.equals(endDateTime, that.endDateTime)
        && Objects.equals(transactionId, that.transactionId)
        && Objects.equals(message, that.message)
        && Arrays.equals(messageExtra, that.messageExtra)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        display,
        id,
        priority,
        state,
        startDateTime,
        endDateTime,
        transactionId,
        message,
        Arrays.hashCode(messageExtra),
        customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("display", display)
        .add("id", id)
        .add("priority", priority)
        .add("state", state)
        .add("startDateTime", startDateTime)
        .add("endDateTime", endDateTime)
        .add("transactionId", transactionId)
        .add("message", message)
        .add("messageExtra", messageExtra)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
