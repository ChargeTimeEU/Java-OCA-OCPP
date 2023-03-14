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
import eu.chargetime.ocpp.v201.model.types.ChargingNeeds;
import eu.chargetime.ocpp.v201.model.types.CustomData;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * NotifyEVChargingNeedsRequest
 *
 * <p>OCPP 2.0.1 FINAL
 */
public final class NotifyEVChargingNeedsRequest extends RequestWithId {
  /** Custom data */
  @Nullable private CustomData customData;

  /** The maximum schedule tuples the car supports per schedule. */
  @Nullable private Integer maxScheduleTuples;

  /** Charging Needs */
  private ChargingNeeds chargingNeeds;

  /** The EVSE and connector to which the EV is connected. EvseId may not be 0. */
  private Integer evseId;

  /**
   * Constructor for the NotifyEVChargingNeedsRequest class
   *
   * @param chargingNeeds Charging Needs
   * @param evseId The EVSE and connector to which the EV is connected. EvseId may not be 0.
   */
  public NotifyEVChargingNeedsRequest(ChargingNeeds chargingNeeds, Integer evseId) {
    setChargingNeeds(chargingNeeds);
    setEvseId(evseId);
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

  /**
   * Gets the maximum schedule tuples the car supports per schedule.
   *
   * @return The maximum schedule tuples the car supports per schedule
   */
  @Nullable
  public Integer getMaxScheduleTuples() {
    return maxScheduleTuples;
  }

  /**
   * Sets the maximum schedule tuples the car supports per schedule.
   *
   * @param maxScheduleTuples The maximum schedule tuples the car supports per schedule
   */
  public void setMaxScheduleTuples(@Nullable Integer maxScheduleTuples) {
    this.maxScheduleTuples = maxScheduleTuples;
  }

  /**
   * Adds the maximum schedule tuples the car supports per schedule.
   *
   * @param maxScheduleTuples The maximum schedule tuples the car supports per schedule
   * @return this
   */
  public NotifyEVChargingNeedsRequest withMaxScheduleTuples(@Nullable Integer maxScheduleTuples) {
    setMaxScheduleTuples(maxScheduleTuples);
    return this;
  }

  /**
   * Gets charging Needs
   *
   * @return Charging Needs
   */
  public ChargingNeeds getChargingNeeds() {
    return chargingNeeds;
  }

  /**
   * Sets charging Needs
   *
   * @param chargingNeeds Charging Needs
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
    return evseId != null;
  }

  @Override
  public boolean validate() {
    return isValidCustomData(customData)
        && isValidChargingNeeds(chargingNeeds)
        && isValidEvseId(evseId);
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
    return Objects.equals(customData, that.customData)
        && Objects.equals(maxScheduleTuples, that.maxScheduleTuples)
        && Objects.equals(chargingNeeds, that.chargingNeeds)
        && Objects.equals(evseId, that.evseId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customData, maxScheduleTuples, chargingNeeds, evseId);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("maxScheduleTuples", maxScheduleTuples)
        .add("chargingNeeds", chargingNeeds)
        .add("evseId", evseId)
        .add("isValid", validate())
        .toString();
  }
}
