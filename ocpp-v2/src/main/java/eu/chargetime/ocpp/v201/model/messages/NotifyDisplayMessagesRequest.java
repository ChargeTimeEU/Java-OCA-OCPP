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
import eu.chargetime.ocpp.v201.model.types.MessageInfo;
import java.util.Arrays;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * NotifyDisplayMessagesRequest
 *
 * <p>OCPP 2.0.1 FINAL
 */
public final class NotifyDisplayMessagesRequest extends RequestWithId {
  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Message Info
   *
   * <p>Message details, for a message to be displayed on a Charging Station.
   */
  @Nullable private MessageInfo[] messageInfo;

  /** The id of the GetDisplayMessagesRequest that requested this message. */
  private Integer requestId;

  /**
   * "to be continued" indicator. Indicates whether another part of the report follows in an
   * upcoming NotifyDisplayMessagesRequest message. Default value when omitted is false.
   */
  @Nullable private Boolean tbc;

  /**
   * Constructor for the NotifyDisplayMessagesRequest class
   *
   * @param requestId The id of the GetDisplayMessagesRequest that requested this message.
   */
  public NotifyDisplayMessagesRequest(Integer requestId) {
    setRequestId(requestId);
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
  public NotifyDisplayMessagesRequest withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  /**
   * Gets message details, for a message to be displayed on a Charging Station.
   *
   * @return Message details, for a message to be displayed on a Charging Station
   */
  @Nullable
  public MessageInfo[] getMessageInfo() {
    return messageInfo;
  }

  /**
   * Sets message details, for a message to be displayed on a Charging Station.
   *
   * @param messageInfo Message details, for a message to be displayed on a Charging Station
   */
  public void setMessageInfo(@Nullable MessageInfo[] messageInfo) {
    if (!isValidMessageInfo(messageInfo)) {
      throw new PropertyConstraintException(messageInfo, "messageInfo is invalid");
    }
    this.messageInfo = messageInfo;
  }

  /**
   * Returns whether the given messageInfo is valid
   *
   * @param messageInfo the messageInfo to check the validity of
   * @return {@code true} if messageInfo is valid, {@code false} if not
   */
  private boolean isValidMessageInfo(@Nullable MessageInfo[] messageInfo) {
    return messageInfo == null
        || (messageInfo.length >= 1
            && Arrays.stream(messageInfo).allMatch(item -> item.validate()));
  }

  /**
   * Adds message details, for a message to be displayed on a Charging Station.
   *
   * @param messageInfo Message details, for a message to be displayed on a Charging Station
   * @return this
   */
  public NotifyDisplayMessagesRequest withMessageInfo(@Nullable MessageInfo[] messageInfo) {
    setMessageInfo(messageInfo);
    return this;
  }

  /**
   * Gets the id of the GetDisplayMessagesRequest that requested this message.
   *
   * @return The id of the GetDisplayMessagesRequest that requested this message
   */
  public Integer getRequestId() {
    return requestId;
  }

  /**
   * Sets the id of the GetDisplayMessagesRequest that requested this message.
   *
   * @param requestId The id of the GetDisplayMessagesRequest that requested this message
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
   * Gets "to be continued" indicator. Indicates whether another part of the report follows in an
   * upcoming NotifyDisplayMessagesRequest message. Default value when omitted is false.
   *
   * @return "to be continued" indicator
   */
  public Boolean getTbc() {
    return tbc != null ? tbc : false;
  }

  /**
   * Sets "to be continued" indicator. Indicates whether another part of the report follows in an
   * upcoming NotifyDisplayMessagesRequest message. Default value when omitted is false.
   *
   * @param tbc "to be continued" indicator
   */
  public void setTbc(@Nullable Boolean tbc) {
    this.tbc = tbc;
  }

  /**
   * Adds "to be continued" indicator. Indicates whether another part of the report follows in an
   * upcoming NotifyDisplayMessagesRequest message. Default value when omitted is false.
   *
   * @param tbc "to be continued" indicator
   * @return this
   */
  public NotifyDisplayMessagesRequest withTbc(@Nullable Boolean tbc) {
    setTbc(tbc);
    return this;
  }

  @Override
  public boolean validate() {
    return isValidCustomData(customData)
        && isValidMessageInfo(messageInfo)
        && isValidRequestId(requestId);
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
    NotifyDisplayMessagesRequest that = (NotifyDisplayMessagesRequest) o;
    return Objects.equals(customData, that.customData)
        && Arrays.equals(messageInfo, that.messageInfo)
        && Objects.equals(requestId, that.requestId)
        && Objects.equals(tbc, that.tbc);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customData, Arrays.hashCode(messageInfo), requestId, tbc);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("messageInfo", messageInfo)
        .add("requestId", requestId)
        .add("tbc", tbc)
        .add("isValid", validate())
        .toString();
  }
}
