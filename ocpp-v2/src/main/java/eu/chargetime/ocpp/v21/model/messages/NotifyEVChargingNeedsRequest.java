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
import eu.chargetime.ocpp.v21.model.types.ChargingNeeds;
import eu.chargetime.ocpp.v21.model.types.CustomData;
import java.time.ZonedDateTime;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * NotifyEVChargingNeedsRequest
 *
 * <p>OCPP 2.1 Edition 1 (c) OCA, Creative Commons Attribution-NoDerivatives 4.0 International
 * Public License
 */
public final class NotifyEVChargingNeedsRequest extends RequestWithId {
  /** The EVSE and connector to which the EV is connected. EvseId may not be 0. */
  private Integer evseId;

  /**
   * The maximum elements the EV supports for:
   *
   * <pre>
   * - ISO 15118-2: schedule tuples in SASchedule (both Pmax and Tariff).
   * - ISO 15118-20: PowerScheduleEntry, PriceRule and PriceLevelScheduleEntries.
   * </pre>
   */
  @Nullable private Integer maxScheduleTuples;

  /** chargingNeeds */
  private ChargingNeeds chargingNeeds;

  /**
   * Time when EV charging needs were received.
   *
   * <p>Field can be added when charging station was offline when charging needs were received.
   */
  @Nullable private ZonedDateTime timestamp;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the NotifyEVChargingNeedsRequest class
   *
   * @param evseId The EVSE and connector to which the EV is connected. EvseId may not be 0.
   * @param chargingNeeds chargingNeeds
   */
  public NotifyEVChargingNeedsRequest(Integer evseId, ChargingNeeds chargingNeeds) {
    setEvseId(evseId);
    setChargingNeeds(chargingNeeds);
  }

  /**
   * Gets the EVSE and connector to which the EV is connected. EvseId may not be 0.
   *
   * @return The EVSE and connector to which the EV is connected
   */
  public Integer getEvseId() {
    return evseId;
  }

  /**
   * Sets the EVSE and connector to which the EV is connected. EvseId may not be 0.
   *
   * @param evseId The EVSE and connector to which the EV is connected
   */
  public void setEvseId(Integer evseId) {
    if (!isValidEvseId(evseId)) {
      throw new PropertyConstraintException(evseId, "evseId is invalid");
    }
    this.evseId = evseId;
  }

  /**
   * Returns whether the given evseId is valid
   *
   * @param evseId the evseId to check the validity of
   * @return {@code true} if evseId is valid, {@code false} if not
   */
  private boolean isValidEvseId(Integer evseId) {
    return evseId != null && evseId >= 1;
  }

  /**
   * Gets the maximum elements the EV supports for:
   *
   * @return The maximum elements the EV supports for:
   */
  @Nullable
  public Integer getMaxScheduleTuples() {
    return maxScheduleTuples;
  }

  /**
   * Sets the maximum elements the EV supports for:
   *
   * @param maxScheduleTuples The maximum elements the EV supports for:
   */
  public void setMaxScheduleTuples(@Nullable Integer maxScheduleTuples) {
    if (!isValidMaxScheduleTuples(maxScheduleTuples)) {
      throw new PropertyConstraintException(maxScheduleTuples, "maxScheduleTuples is invalid");
    }
    this.maxScheduleTuples = maxScheduleTuples;
  }

  /**
   * Returns whether the given maxScheduleTuples is valid
   *
   * @param maxScheduleTuples the maxScheduleTuples to check the validity of
   * @return {@code true} if maxScheduleTuples is valid, {@code false} if not
   */
  private boolean isValidMaxScheduleTuples(@Nullable Integer maxScheduleTuples) {
    return maxScheduleTuples == null || (maxScheduleTuples >= 0);
  }

  /**
   * Adds the maximum elements the EV supports for:
   *
   * @param maxScheduleTuples The maximum elements the EV supports for:
   * @return this
   */
  public NotifyEVChargingNeedsRequest withMaxScheduleTuples(@Nullable Integer maxScheduleTuples) {
    setMaxScheduleTuples(maxScheduleTuples);
    return this;
  }

  /**
   * Gets chargingNeeds
   *
   * @return chargingNeeds
   */
  public ChargingNeeds getChargingNeeds() {
    return chargingNeeds;
  }

  /**
   * Sets chargingNeeds
   *
   * @param chargingNeeds chargingNeeds
   */
  public void setChargingNeeds(ChargingNeeds chargingNeeds) {
    if (!isValidChargingNeeds(chargingNeeds)) {
      throw new PropertyConstraintException(chargingNeeds, "chargingNeeds is invalid");
    }
    this.chargingNeeds = chargingNeeds;
  }

  /**
   * Returns whether the given chargingNeeds is valid
   *
   * @param chargingNeeds the chargingNeeds to check the validity of
   * @return {@code true} if chargingNeeds is valid, {@code false} if not
   */
  private boolean isValidChargingNeeds(ChargingNeeds chargingNeeds) {
    return chargingNeeds != null && chargingNeeds.validate();
  }

  /**
   * Gets time when EV charging needs were received.
   *
   * @return Time when EV charging needs were received
   */
  @Nullable
  public ZonedDateTime getTimestamp() {
    return timestamp;
  }

  /**
   * Sets time when EV charging needs were received.
   *
   * @param timestamp Time when EV charging needs were received
   */
  public void setTimestamp(@Nullable ZonedDateTime timestamp) {
    this.timestamp = timestamp;
  }

  /**
   * Adds time when EV charging needs were received.
   *
   * @param timestamp Time when EV charging needs were received
   * @return this
   */
  public NotifyEVChargingNeedsRequest withTimestamp(@Nullable ZonedDateTime timestamp) {
    setTimestamp(timestamp);
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
  public NotifyEVChargingNeedsRequest withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  @Override
  public boolean validate() {
    return isValidEvseId(evseId)
        && isValidMaxScheduleTuples(maxScheduleTuples)
        && isValidChargingNeeds(chargingNeeds)
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
    NotifyEVChargingNeedsRequest that = (NotifyEVChargingNeedsRequest) o;
    return Objects.equals(evseId, that.evseId)
        && Objects.equals(maxScheduleTuples, that.maxScheduleTuples)
        && Objects.equals(chargingNeeds, that.chargingNeeds)
        && Objects.equals(timestamp, that.timestamp)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(evseId, maxScheduleTuples, chargingNeeds, timestamp, customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("evseId", evseId)
        .add("maxScheduleTuples", maxScheduleTuples)
        .add("chargingNeeds", chargingNeeds)
        .add("timestamp", timestamp)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
