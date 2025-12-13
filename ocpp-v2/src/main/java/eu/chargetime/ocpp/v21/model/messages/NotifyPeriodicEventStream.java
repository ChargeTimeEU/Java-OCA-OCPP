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
import eu.chargetime.ocpp.utilities.MoreObjects;
import eu.chargetime.ocpp.v21.model.types.CustomData;
import eu.chargetime.ocpp.v21.model.types.StreamDataElement;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * NotifyPeriodicEventStream
 *
 * <p>OCPP 2.1 Edition 1 (c) OCA, Creative Commons Attribution-NoDerivatives 4.0 International
 * Public License
 */
public final class NotifyPeriodicEventStream {
  /** data */
  private StreamDataElement[] data;

  /** Id of stream. */
  private Integer id;

  /** Number of data elements still pending to be sent. */
  private Integer pending;

  /** Base timestamp to add to time offset of values. */
  private ZonedDateTime basetime;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the NotifyPeriodicEventStream class
   *
   * @param data data
   * @param id Id of stream.
   * @param pending Number of data elements still pending to be sent.
   * @param basetime Base timestamp to add to time offset of values.
   */
  public NotifyPeriodicEventStream(
      StreamDataElement[] data, Integer id, Integer pending, ZonedDateTime basetime) {
    setData(data);
    setId(id);
    setPending(pending);
    setBasetime(basetime);
  }

  /**
   * Gets data
   *
   * @return data
   */
  public StreamDataElement[] getData() {
    return data;
  }

  /**
   * Sets data
   *
   * @param data data
   */
  public void setData(StreamDataElement[] data) {
    if (!isValidData(data)) {
      throw new PropertyConstraintException(data, "data is invalid");
    }
    this.data = data;
  }

  /**
   * Returns whether the given data is valid
   *
   * @param data the data to check the validity of
   * @return {@code true} if data is valid, {@code false} if not
   */
  private boolean isValidData(StreamDataElement[] data) {
    return data != null
        && data.length >= 1
        && Arrays.stream(data).allMatch(item -> item.validate());
  }

  /**
   * Gets id of stream.
   *
   * @return Id of stream
   */
  public Integer getId() {
    return id;
  }

  /**
   * Sets id of stream.
   *
   * @param id Id of stream
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
   * Gets number of data elements still pending to be sent.
   *
   * @return Number of data elements still pending to be sent
   */
  public Integer getPending() {
    return pending;
  }

  /**
   * Sets number of data elements still pending to be sent.
   *
   * @param pending Number of data elements still pending to be sent
   */
  public void setPending(Integer pending) {
    if (!isValidPending(pending)) {
      throw new PropertyConstraintException(pending, "pending is invalid");
    }
    this.pending = pending;
  }

  /**
   * Returns whether the given pending is valid
   *
   * @param pending the pending to check the validity of
   * @return {@code true} if pending is valid, {@code false} if not
   */
  private boolean isValidPending(Integer pending) {
    return pending != null && pending >= 0;
  }

  /**
   * Gets base timestamp to add to time offset of values.
   *
   * @return Base timestamp to add to time offset of values
   */
  public ZonedDateTime getBasetime() {
    return basetime;
  }

  /**
   * Sets base timestamp to add to time offset of values.
   *
   * @param basetime Base timestamp to add to time offset of values
   */
  public void setBasetime(ZonedDateTime basetime) {
    if (!isValidBasetime(basetime)) {
      throw new PropertyConstraintException(basetime, "basetime is invalid");
    }
    this.basetime = basetime;
  }

  /**
   * Returns whether the given basetime is valid
   *
   * @param basetime the basetime to check the validity of
   * @return {@code true} if basetime is valid, {@code false} if not
   */
  private boolean isValidBasetime(ZonedDateTime basetime) {
    return basetime != null;
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
  public NotifyPeriodicEventStream withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  public boolean validate() {
    return isValidData(data)
        && isValidId(id)
        && isValidPending(pending)
        && isValidBasetime(basetime)
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
    NotifyPeriodicEventStream that = (NotifyPeriodicEventStream) o;
    return Arrays.equals(data, that.data)
        && Objects.equals(id, that.id)
        && Objects.equals(pending, that.pending)
        && Objects.equals(basetime, that.basetime)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(Arrays.hashCode(data), id, pending, basetime, customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("data", data)
        .add("id", id)
        .add("pending", pending)
        .add("basetime", basetime)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
