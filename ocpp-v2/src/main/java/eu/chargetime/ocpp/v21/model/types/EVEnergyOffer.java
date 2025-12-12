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
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * A schedule of the energy amount over time that EV is willing to discharge. A negative value
 * indicates the willingness to discharge under specific conditions, a positive value indicates that
 * the EV currently is not able to offer energy to discharge.
 */
public final class EVEnergyOffer {
  /** Price schedule of EV energy offer. */
  @Nullable private EVAbsolutePriceSchedule evAbsolutePriceSchedule;

  /** Schedule of EV energy offer. */
  private EVPowerSchedule evPowerSchedule;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the EVEnergyOffer class
   *
   * @param evPowerSchedule Schedule of EV energy offer.
   */
  public EVEnergyOffer(EVPowerSchedule evPowerSchedule) {
    setEvPowerSchedule(evPowerSchedule);
  }

  /**
   * Gets price schedule of EV energy offer.
   *
   * @return Price schedule of EV energy offer
   */
  @Nullable
  public EVAbsolutePriceSchedule getEvAbsolutePriceSchedule() {
    return evAbsolutePriceSchedule;
  }

  /**
   * Sets price schedule of EV energy offer.
   *
   * @param evAbsolutePriceSchedule Price schedule of EV energy offer
   */
  public void setEvAbsolutePriceSchedule(
      @Nullable EVAbsolutePriceSchedule evAbsolutePriceSchedule) {
    if (!isValidEvAbsolutePriceSchedule(evAbsolutePriceSchedule)) {
      throw new PropertyConstraintException(
          evAbsolutePriceSchedule, "evAbsolutePriceSchedule is invalid");
    }
    this.evAbsolutePriceSchedule = evAbsolutePriceSchedule;
  }

  /**
   * Returns whether the given evAbsolutePriceSchedule is valid
   *
   * @param evAbsolutePriceSchedule the evAbsolutePriceSchedule to check the validity of
   * @return {@code true} if evAbsolutePriceSchedule is valid, {@code false} if not
   */
  private boolean isValidEvAbsolutePriceSchedule(
      @Nullable EVAbsolutePriceSchedule evAbsolutePriceSchedule) {
    return evAbsolutePriceSchedule == null || evAbsolutePriceSchedule.validate();
  }

  /**
   * Adds price schedule of EV energy offer.
   *
   * @param evAbsolutePriceSchedule Price schedule of EV energy offer
   * @return this
   */
  public EVEnergyOffer withEvAbsolutePriceSchedule(
      @Nullable EVAbsolutePriceSchedule evAbsolutePriceSchedule) {
    setEvAbsolutePriceSchedule(evAbsolutePriceSchedule);
    return this;
  }

  /**
   * Gets schedule of EV energy offer.
   *
   * @return Schedule of EV energy offer
   */
  public EVPowerSchedule getEvPowerSchedule() {
    return evPowerSchedule;
  }

  /**
   * Sets schedule of EV energy offer.
   *
   * @param evPowerSchedule Schedule of EV energy offer
   */
  public void setEvPowerSchedule(EVPowerSchedule evPowerSchedule) {
    if (!isValidEvPowerSchedule(evPowerSchedule)) {
      throw new PropertyConstraintException(evPowerSchedule, "evPowerSchedule is invalid");
    }
    this.evPowerSchedule = evPowerSchedule;
  }

  /**
   * Returns whether the given evPowerSchedule is valid
   *
   * @param evPowerSchedule the evPowerSchedule to check the validity of
   * @return {@code true} if evPowerSchedule is valid, {@code false} if not
   */
  private boolean isValidEvPowerSchedule(EVPowerSchedule evPowerSchedule) {
    return evPowerSchedule != null && evPowerSchedule.validate();
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
  public EVEnergyOffer withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  public boolean validate() {
    return isValidEvAbsolutePriceSchedule(evAbsolutePriceSchedule)
        && isValidEvPowerSchedule(evPowerSchedule)
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
    EVEnergyOffer that = (EVEnergyOffer) o;
    return Objects.equals(evAbsolutePriceSchedule, that.evAbsolutePriceSchedule)
        && Objects.equals(evPowerSchedule, that.evPowerSchedule)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(evAbsolutePriceSchedule, evPowerSchedule, customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("evAbsolutePriceSchedule", evAbsolutePriceSchedule)
        .add("evPowerSchedule", evPowerSchedule)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
