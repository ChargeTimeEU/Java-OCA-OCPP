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
import eu.chargetime.ocpp.v201.model.types.MessagePriorityEnum;
import eu.chargetime.ocpp.v201.model.types.MessageStateEnum;
import java.util.Arrays;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * GetDisplayMessagesRequest
 *
 * <p>OCPP 2.0.1 FINAL
 */
public final class GetDisplayMessagesRequest extends RequestWithId {
  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * If provided the Charging Station shall return Display Messages of the given ids. This field
   * SHALL NOT contain more ids than set in NumberOfDisplayMessages.maxLimit
   */
  @Nullable private Integer[] id;

  /** The Id of this request. */
  private Integer requestId;

  /**
   * If provided the Charging Station shall return Display Messages with the given priority only.
   */
  @Nullable private MessagePriorityEnum priority;

  /** If provided the Charging Station shall return Display Messages with the given state only. */
  @Nullable private MessageStateEnum state;

  /**
   * Constructor for the GetDisplayMessagesRequest class
   *
   * @param requestId The Id of this request.
   */
  public GetDisplayMessagesRequest(Integer requestId) {
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
  public GetDisplayMessagesRequest withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  /**
   * Gets if provided the Charging Station shall return Display Messages of the given ids. This
   * field SHALL NOT contain more ids than set in NumberOfDisplayMessages.maxLimit
   *
   * @return If provided the Charging Station shall return Display Messages of the given ids
   */
  @Nullable
  public Integer[] getId() {
    return id;
  }

  /**
   * Sets if provided the Charging Station shall return Display Messages of the given ids. This
   * field SHALL NOT contain more ids than set in NumberOfDisplayMessages.maxLimit
   *
   * @param id If provided the Charging Station shall return Display Messages of the given ids
   */
  public void setId(@Nullable Integer[] id) {
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
  private boolean isValidId(@Nullable Integer[] id) {
    return id == null || (id.length >= 1);
  }

  /**
   * Adds if provided the Charging Station shall return Display Messages of the given ids. This
   * field SHALL NOT contain more ids than set in NumberOfDisplayMessages.maxLimit
   *
   * @param id If provided the Charging Station shall return Display Messages of the given ids
   * @return this
   */
  public GetDisplayMessagesRequest withId(@Nullable Integer[] id) {
    setId(id);
    return this;
  }

  /**
   * Gets the Id of this request.
   *
   * @return The Id of this request
   */
  public Integer getRequestId() {
    return requestId;
  }

  /**
   * Sets the Id of this request.
   *
   * @param requestId The Id of this request
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
   * Gets if provided the Charging Station shall return Display Messages with the given priority
   * only.
   *
   * @return If provided the Charging Station shall return Display Messages with the given priority
   *     only
   */
  @Nullable
  public MessagePriorityEnum getPriority() {
    return priority;
  }

  /**
   * Sets if provided the Charging Station shall return Display Messages with the given priority
   * only.
   *
   * @param priority If provided the Charging Station shall return Display Messages with the given
   *     priority only
   */
  public void setPriority(@Nullable MessagePriorityEnum priority) {
    this.priority = priority;
  }

  /**
   * Adds if provided the Charging Station shall return Display Messages with the given priority
   * only.
   *
   * @param priority If provided the Charging Station shall return Display Messages with the given
   *     priority only
   * @return this
   */
  public GetDisplayMessagesRequest withPriority(@Nullable MessagePriorityEnum priority) {
    setPriority(priority);
    return this;
  }

  /**
   * Gets if provided the Charging Station shall return Display Messages with the given state only.
   *
   * @return If provided the Charging Station shall return Display Messages with the given state
   *     only
   */
  @Nullable
  public MessageStateEnum getState() {
    return state;
  }

  /**
   * Sets if provided the Charging Station shall return Display Messages with the given state only.
   *
   * @param state If provided the Charging Station shall return Display Messages with the given
   *     state only
   */
  public void setState(@Nullable MessageStateEnum state) {
    this.state = state;
  }

  /**
   * Adds if provided the Charging Station shall return Display Messages with the given state only.
   *
   * @param state If provided the Charging Station shall return Display Messages with the given
   *     state only
   * @return this
   */
  public GetDisplayMessagesRequest withState(@Nullable MessageStateEnum state) {
    setState(state);
    return this;
  }

  @Override
  public boolean validate() {
    return isValidCustomData(customData) && isValidId(id) && isValidRequestId(requestId);
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
    GetDisplayMessagesRequest that = (GetDisplayMessagesRequest) o;
    return Objects.equals(customData, that.customData)
        && Arrays.equals(id, that.id)
        && Objects.equals(requestId, that.requestId)
        && Objects.equals(priority, that.priority)
        && Objects.equals(state, that.state);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customData, Arrays.hashCode(id), requestId, priority, state);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("id", id)
        .add("requestId", requestId)
        .add("priority", priority)
        .add("state", state)
        .add("isValid", validate())
        .toString();
  }
}
