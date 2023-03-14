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

package eu.chargetime.ocpp.v201.model.types;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.utilities.MoreObjects;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * Charging Schedule Period
 *
 * <p>Charging schedule period structure defines a time period in a charging schedule.
 */
public final class ChargingSchedulePeriod {
  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Charging Schedule Period. Start Period. Elapsed Time
   *
   * <p>Start of the period, in seconds from the start of schedule. The value of StartPeriod also
   * defines the stop time of the previous period.
   */
  private Integer startPeriod;

  /**
   * Charging Schedule Period. Limit. Measure
   *
   * <p>Charging rate limit during the schedule period, in the applicable chargingRateUnit, for
   * example in Amperes (A) or Watts (W). Accepts at most one digit fraction (e.g. 8.1).
   */
  private Double limit;

  /**
   * Charging Schedule Period. Number Phases. Counter
   *
   * <p>The number of phases that can be used for charging. If a number of phases is needed,
   * numberPhases=3 will be assumed unless another number is given.
   */
  @Nullable private Integer numberPhases;

  /**
   * Values: 1..3, Used if numberPhases=1 and if the EVSE is capable of switching the phase
   * connected to the EV, i.e. ACPhaseSwitchingSupported is defined and true. It’s not allowed
   * unless both conditions above are true. If both conditions are true, and phaseToUse is omitted,
   * the Charging Station / EVSE will make the selection on its own.
   */
  @Nullable private Integer phaseToUse;

  /**
   * Constructor for the ChargingSchedulePeriod class
   *
   * @param startPeriod Start of the period, in seconds from the start of schedule. The value of
   *     StartPeriod also defines the stop time of the previous period.
   * @param limit Charging rate limit during the schedule period, in the applicable
   *     chargingRateUnit, for example in Amperes (A) or Watts (W). Accepts at most one digit
   *     fraction (e.g. 8.1).
   */
  public ChargingSchedulePeriod(Integer startPeriod, Double limit) {
    setStartPeriod(startPeriod);
    setLimit(limit);
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
  public ChargingSchedulePeriod withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  /**
   * Gets start of the period, in seconds from the start of schedule. The value of StartPeriod also
   * defines the stop time of the previous period.
   *
   * @return Start of the period, in seconds from the start of schedule
   */
  public Integer getStartPeriod() {
    return startPeriod;
  }

  /**
   * Sets start of the period, in seconds from the start of schedule. The value of StartPeriod also
   * defines the stop time of the previous period.
   *
   * @param startPeriod Start of the period, in seconds from the start of schedule
   */
  public void setStartPeriod(Integer startPeriod) {
    if (!isValidStartPeriod(startPeriod)) {
      throw new PropertyConstraintException(startPeriod, "startPeriod is invalid");
    }
    this.startPeriod = startPeriod;
  }

  /**
   * Returns whether the given startPeriod is valid
   *
   * @param startPeriod the startPeriod to check the validity of
   * @return {@code true} if startPeriod is valid, {@code false} if not
   */
  private boolean isValidStartPeriod(Integer startPeriod) {
    return startPeriod != null;
  }

  /**
   * Gets charging rate limit during the schedule period, in the applicable chargingRateUnit, for
   * example in Amperes (A) or Watts (W). Accepts at most one digit fraction (e.g. 8.1).
   *
   * @return Charging rate limit during the schedule period, in the applicable chargingRateUnit, for
   *     example in Amperes (A) or Watts (W)
   */
  public Double getLimit() {
    return limit;
  }

  /**
   * Sets charging rate limit during the schedule period, in the applicable chargingRateUnit, for
   * example in Amperes (A) or Watts (W). Accepts at most one digit fraction (e.g. 8.1).
   *
   * @param limit Charging rate limit during the schedule period, in the applicable
   *     chargingRateUnit, for example in Amperes (A) or Watts (W)
   */
  public void setLimit(Double limit) {
    if (!isValidLimit(limit)) {
      throw new PropertyConstraintException(limit, "limit is invalid");
    }
    this.limit = limit;
  }

  /**
   * Returns whether the given limit is valid
   *
   * @param limit the limit to check the validity of
   * @return {@code true} if limit is valid, {@code false} if not
   */
  private boolean isValidLimit(Double limit) {
    return limit != null;
  }

  /**
   * Gets the number of phases that can be used for charging. If a number of phases is needed,
   * numberPhases=3 will be assumed unless another number is given.
   *
   * @return The number of phases that can be used for charging
   */
  @Nullable
  public Integer getNumberPhases() {
    return numberPhases;
  }

  /**
   * Sets the number of phases that can be used for charging. If a number of phases is needed,
   * numberPhases=3 will be assumed unless another number is given.
   *
   * @param numberPhases The number of phases that can be used for charging
   */
  public void setNumberPhases(@Nullable Integer numberPhases) {
    this.numberPhases = numberPhases;
  }

  /**
   * Adds the number of phases that can be used for charging. If a number of phases is needed,
   * numberPhases=3 will be assumed unless another number is given.
   *
   * @param numberPhases The number of phases that can be used for charging
   * @return this
   */
  public ChargingSchedulePeriod withNumberPhases(@Nullable Integer numberPhases) {
    setNumberPhases(numberPhases);
    return this;
  }

  /**
   * Gets values: 1..3, Used if numberPhases=1 and if the EVSE is capable of switching the phase
   * connected to the EV, i.e. ACPhaseSwitchingSupported is defined and true. It’s not allowed
   * unless both conditions above are true. If both conditions are true, and phaseToUse is omitted,
   * the Charging Station / EVSE will make the selection on its own.
   *
   * @return Values: 1..3, Used if numberPhases=1 and if the EVSE is capable of switching the phase
   *     connected to the EV, i.e. ACPhaseSwitchingSupported is defined and true
   */
  @Nullable
  public Integer getPhaseToUse() {
    return phaseToUse;
  }

  /**
   * Sets values: 1..3, Used if numberPhases=1 and if the EVSE is capable of switching the phase
   * connected to the EV, i.e. ACPhaseSwitchingSupported is defined and true. It’s not allowed
   * unless both conditions above are true. If both conditions are true, and phaseToUse is omitted,
   * the Charging Station / EVSE will make the selection on its own.
   *
   * @param phaseToUse Values: 1..3, Used if numberPhases=1 and if the EVSE is capable of switching
   *     the phase connected to the EV, i.e. ACPhaseSwitchingSupported is defined and true
   */
  public void setPhaseToUse(@Nullable Integer phaseToUse) {
    this.phaseToUse = phaseToUse;
  }

  /**
   * Adds values: 1..3, Used if numberPhases=1 and if the EVSE is capable of switching the phase
   * connected to the EV, i.e. ACPhaseSwitchingSupported is defined and true. It’s not allowed
   * unless both conditions above are true. If both conditions are true, and phaseToUse is omitted,
   * the Charging Station / EVSE will make the selection on its own.
   *
   * @param phaseToUse Values: 1..3, Used if numberPhases=1 and if the EVSE is capable of switching
   *     the phase connected to the EV, i.e. ACPhaseSwitchingSupported is defined and true
   * @return this
   */
  public ChargingSchedulePeriod withPhaseToUse(@Nullable Integer phaseToUse) {
    setPhaseToUse(phaseToUse);
    return this;
  }

  public boolean validate() {
    return isValidCustomData(customData) && isValidStartPeriod(startPeriod) && isValidLimit(limit);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ChargingSchedulePeriod that = (ChargingSchedulePeriod) o;
    return Objects.equals(customData, that.customData)
        && Objects.equals(startPeriod, that.startPeriod)
        && Objects.equals(limit, that.limit)
        && Objects.equals(numberPhases, that.numberPhases)
        && Objects.equals(phaseToUse, that.phaseToUse);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customData, startPeriod, limit, numberPhases, phaseToUse);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("startPeriod", startPeriod)
        .add("limit", limit)
        .add("numberPhases", numberPhases)
        .add("phaseToUse", phaseToUse)
        .add("isValid", validate())
        .toString();
  }
}
