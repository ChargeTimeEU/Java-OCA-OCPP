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
import eu.chargetime.ocpp.v201.model.types.EventData;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * NotifyEventRequest
 *
 * <p>OCPP 2.0.1 FINAL
 */
public final class NotifyEventRequest extends RequestWithId {
  /** Custom data */
  @Nullable private CustomData customData;

  /** Timestamp of the moment this message was generated at the Charging Station. */
  private ZonedDateTime generatedAt;

  /**
   * “to be continued” indicator. Indicates whether another part of the report follows in an
   * upcoming notifyEventRequest message. Default value when omitted is false.
   */
  @Nullable private Boolean tbc;

  /** Sequence number of this message. First message starts at 0. */
  private Integer seqNo;

  /** Class to report an event notification for a component-variable. */
  private EventData[] eventData;

  /**
   * Constructor for the NotifyEventRequest class
   *
   * @param generatedAt Timestamp of the moment this message was generated at the Charging Station.
   * @param seqNo Sequence number of this message. First message starts at 0.
   * @param eventData Class to report an event notification for a component-variable.
   */
  public NotifyEventRequest(ZonedDateTime generatedAt, Integer seqNo, EventData[] eventData) {
    setGeneratedAt(generatedAt);
    setSeqNo(seqNo);
    setEventData(eventData);
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
  public NotifyEventRequest withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  /**
   * Gets timestamp of the moment this message was generated at the Charging Station.
   *
   * @return Timestamp of the moment this message was generated at the Charging Station
   */
  public ZonedDateTime getGeneratedAt() {
    return generatedAt;
  }

  /**
   * Sets timestamp of the moment this message was generated at the Charging Station.
   *
   * @param generatedAt Timestamp of the moment this message was generated at the Charging Station
   */
  public void setGeneratedAt(ZonedDateTime generatedAt) {
    if (!isValidGeneratedAt(generatedAt)) {
      throw new PropertyConstraintException(generatedAt, "generatedAt is invalid");
    }
    this.generatedAt = generatedAt;
  }

  /**
   * Returns whether the given generatedAt is valid
   *
   * @param generatedAt the generatedAt to check the validity of
   * @return {@code true} if generatedAt is valid, {@code false} if not
   */
  private boolean isValidGeneratedAt(ZonedDateTime generatedAt) {
    return generatedAt != null;
  }

  /**
   * Gets “to be continued” indicator. Indicates whether another part of the report follows in an
   * upcoming notifyEventRequest message. Default value when omitted is false.
   *
   * @return “to be continued” indicator
   */
  public Boolean getTbc() {
    return tbc != null ? tbc : false;
  }

  /**
   * Sets “to be continued” indicator. Indicates whether another part of the report follows in an
   * upcoming notifyEventRequest message. Default value when omitted is false.
   *
   * @param tbc “to be continued” indicator
   */
  public void setTbc(@Nullable Boolean tbc) {
    this.tbc = tbc;
  }

  /**
   * Adds “to be continued” indicator. Indicates whether another part of the report follows in an
   * upcoming notifyEventRequest message. Default value when omitted is false.
   *
   * @param tbc “to be continued” indicator
   * @return this
   */
  public NotifyEventRequest withTbc(@Nullable Boolean tbc) {
    setTbc(tbc);
    return this;
  }

  /**
   * Gets sequence number of this message. First message starts at 0.
   *
   * @return Sequence number of this message
   */
  public Integer getSeqNo() {
    return seqNo;
  }

  /**
   * Sets sequence number of this message. First message starts at 0.
   *
   * @param seqNo Sequence number of this message
   */
  public void setSeqNo(Integer seqNo) {
    if (!isValidSeqNo(seqNo)) {
      throw new PropertyConstraintException(seqNo, "seqNo is invalid");
    }
    this.seqNo = seqNo;
  }

  /**
   * Returns whether the given seqNo is valid
   *
   * @param seqNo the seqNo to check the validity of
   * @return {@code true} if seqNo is valid, {@code false} if not
   */
  private boolean isValidSeqNo(Integer seqNo) {
    return seqNo != null;
  }

  /**
   * Gets class to report an event notification for a component-variable.
   *
   * @return Class to report an event notification for a component-variable
   */
  public EventData[] getEventData() {
    return eventData;
  }

  /**
   * Sets class to report an event notification for a component-variable.
   *
   * @param eventData Class to report an event notification for a component-variable
   */
  public void setEventData(EventData[] eventData) {
    if (!isValidEventData(eventData)) {
      throw new PropertyConstraintException(eventData, "eventData is invalid");
    }
    this.eventData = eventData;
  }

  /**
   * Returns whether the given eventData is valid
   *
   * @param eventData the eventData to check the validity of
   * @return {@code true} if eventData is valid, {@code false} if not
   */
  private boolean isValidEventData(EventData[] eventData) {
    return eventData != null
        && eventData.length >= 1
        && Arrays.stream(eventData).allMatch(item -> item.validate());
  }

  @Override
  public boolean validate() {
    return isValidCustomData(customData)
        && isValidGeneratedAt(generatedAt)
        && isValidSeqNo(seqNo)
        && isValidEventData(eventData);
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
    NotifyEventRequest that = (NotifyEventRequest) o;
    return Objects.equals(customData, that.customData)
        && Objects.equals(generatedAt, that.generatedAt)
        && Objects.equals(tbc, that.tbc)
        && Objects.equals(seqNo, that.seqNo)
        && Arrays.equals(eventData, that.eventData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customData, generatedAt, tbc, seqNo, Arrays.hashCode(eventData));
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("generatedAt", generatedAt)
        .add("tbc", tbc)
        .add("seqNo", seqNo)
        .add("eventData", eventData)
        .add("isValid", validate())
        .toString();
  }
}
