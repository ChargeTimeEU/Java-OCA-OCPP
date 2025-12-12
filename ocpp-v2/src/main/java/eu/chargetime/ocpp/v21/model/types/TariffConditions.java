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
import java.util.Arrays;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * These conditions describe if and when a TariffEnergyType or TariffTimeType applies during a
 * transaction.
 *
 * <p>When more than one restriction is set, they are to be treated as a logical AND. All need to be
 * valid before this price is active.
 *
 * <p>For reverse energy flow (discharging) negative values of energy, power and current are used.
 *
 * <p>NOTE: minXXX (where XXX = Kwh/A/Kw) must be read as "closest to zero", and maxXXX as "furthest
 * from zero". For example, a *charging* power range from 10 kW to 50 kWh is given by minPower =
 * 10000 and maxPower = 50000, and a *discharging* power range from -10 kW to -50 kW is given by
 * minPower = -10 and maxPower = -50.
 *
 * <p>NOTE: startTimeOfDay and endTimeOfDay are in local time, because it is the time in the tariff
 * as it is shown to the EV driver at the Charging Station. A Charging Station will convert this to
 * the internal time zone that it uses (which is recommended to be UTC, see section Generic chapter
 * 3.1) when performing cost calculation.
 */
public final class TariffConditions {
  /**
   * Start time of day in local time.
   *
   * <pre>
   * Format as per RFC 3339: time-hour ":" time-minute
   * Must be in 24h format with leading zeros. Hour/Minute separator: ":"
   * </pre>
   *
   * Regex: ([0-1][0-9]\|2[0-3]):[0-5][0-9]
   */
  @Nullable private String startTimeOfDay;

  /**
   * End time of day in local time. Same syntax as startTimeOfDay.
   *
   * <pre>
   * If end time is before start time then the period wraps around to the next day.
   * To stop at end of the day use: 00:00.
   * </pre>
   */
  @Nullable private String endTimeOfDay;

  /** Day(s) of the week this is tariff applies. */
  @Nullable private DayOfWeekEnum[] dayOfWeek;

  /**
   * Start date in local time, for example: 2015-12-24.
   *
   * <pre>
   * Valid from this day (inclusive).
   * Format as per RFC 3339: full-date
   *
   * </pre>
   *
   * Regex: ([12][0-9]{3})-(0[1-9]\|1[0-2])-(0[1-9]\|[12][0-9]\|3[01])
   */
  @Nullable private String validFromDate;

  /**
   * End date in local time, for example: 2015-12-27. Valid until this day (exclusive). Same syntax
   * as validFromDate.
   */
  @Nullable private String validToDate;

  /** Type of EVSE (AC, DC) this tariff applies to. */
  @Nullable private EvseKindEnum evseKind;

  /**
   * Minimum consumed energy in Wh, for example 20000 Wh. Valid from this amount of energy
   * (inclusive) being used.
   */
  @Nullable private Double minEnergy;

  /**
   * Maximum consumed energy in Wh, for example 50000 Wh. Valid until this amount of energy
   * (exclusive) being used.
   */
  @Nullable private Double maxEnergy;

  /**
   * Sum of the minimum current (in Amperes) over all phases, for example 5 A.
   *
   * <pre>
   * When the EV is charging with more than, or equal to, the defined amount of current, this price
   * is/becomes active. If the charging current is or becomes lower, this price is not or no longer
   * valid and becomes inactive.
   * This is NOT about the minimum current over the entire transaction.
   * </pre>
   */
  @Nullable private Double minCurrent;

  /**
   * Sum of the maximum current (in Amperes) over all phases, for example 20 A. When the EV is
   * charging with less than the defined amount of current, this price becomes/is active. If the
   * charging current is or becomes higher, this price is not or no longer valid and becomes
   * inactive. This is NOT about the maximum current over the entire transaction.
   */
  @Nullable private Double maxCurrent;

  /**
   * Minimum power in W, for example 5000 W. When the EV is charging with more than, or equal to,
   * the defined amount of power, this price is/becomes active. If the charging power is or becomes
   * lower, this price is not or no longer valid and becomes inactive. This is NOT about the minimum
   * power over the entire transaction.
   */
  @Nullable private Double minPower;

  /**
   * Maximum power in W, for example 20000 W. When the EV is charging with less than the defined
   * amount of power, this price becomes/is active. If the charging power is or becomes higher, this
   * price is not or no longer valid and becomes inactive. This is NOT about the maximum power over
   * the entire transaction.
   */
  @Nullable private Double maxPower;

  /**
   * Minimum duration in seconds the transaction (charging and idle) MUST last (inclusive). When the
   * duration of a transaction is longer than the defined value, this price is or becomes active.
   * Before that moment, this price is not yet active.
   */
  @Nullable private Integer minTime;

  /**
   * Maximum duration in seconds the transaction (charging and idle) MUST last (exclusive). When the
   * duration of a transaction is shorter than the defined value, this price is or becomes active.
   * After that moment, this price is no longer active.
   */
  @Nullable private Integer maxTime;

  /**
   * Minimum duration in seconds the charging MUST last (inclusive). When the duration of a charging
   * is longer than the defined value, this price is or becomes active. Before that moment, this
   * price is not yet active.
   */
  @Nullable private Integer minChargingTime;

  /**
   * Maximum duration in seconds the charging MUST last (exclusive). When the duration of a charging
   * is shorter than the defined value, this price is or becomes active. After that moment, this
   * price is no longer active.
   */
  @Nullable private Integer maxChargingTime;

  /**
   * Minimum duration in seconds the idle period (i.e. not charging) MUST last (inclusive). When the
   * duration of the idle time is longer than the defined value, this price is or becomes active.
   * Before that moment, this price is not yet active.
   */
  @Nullable private Integer minIdleTime;

  /**
   * Maximum duration in seconds the idle period (i.e. not charging) MUST last (exclusive). When the
   * duration of idle time is shorter than the defined value, this price is or becomes active. After
   * that moment, this price is no longer active.
   */
  @Nullable private Integer maxIdleTime;

  /** Custom data */
  @Nullable private CustomData customData;

  /** Constructor for the TariffConditions class */
  public TariffConditions() {}

  /**
   * Gets start time of day in local time.
   *
   * @return Start time of day in local time
   */
  @Nullable
  public String getStartTimeOfDay() {
    return startTimeOfDay;
  }

  /**
   * Sets start time of day in local time.
   *
   * @param startTimeOfDay Start time of day in local time
   */
  public void setStartTimeOfDay(@Nullable String startTimeOfDay) {
    this.startTimeOfDay = startTimeOfDay;
  }

  /**
   * Adds start time of day in local time.
   *
   * @param startTimeOfDay Start time of day in local time
   * @return this
   */
  public TariffConditions withStartTimeOfDay(@Nullable String startTimeOfDay) {
    setStartTimeOfDay(startTimeOfDay);
    return this;
  }

  /**
   * Gets end time of day in local time. Same syntax as startTimeOfDay.
   *
   * @return End time of day in local time
   */
  @Nullable
  public String getEndTimeOfDay() {
    return endTimeOfDay;
  }

  /**
   * Sets end time of day in local time. Same syntax as startTimeOfDay.
   *
   * @param endTimeOfDay End time of day in local time
   */
  public void setEndTimeOfDay(@Nullable String endTimeOfDay) {
    this.endTimeOfDay = endTimeOfDay;
  }

  /**
   * Adds end time of day in local time. Same syntax as startTimeOfDay.
   *
   * @param endTimeOfDay End time of day in local time
   * @return this
   */
  public TariffConditions withEndTimeOfDay(@Nullable String endTimeOfDay) {
    setEndTimeOfDay(endTimeOfDay);
    return this;
  }

  /**
   * Gets day(s) of the week this is tariff applies.
   *
   * @return Day(s) of the week this is tariff applies
   */
  @Nullable
  public DayOfWeekEnum[] getDayOfWeek() {
    return dayOfWeek;
  }

  /**
   * Sets day(s) of the week this is tariff applies.
   *
   * @param dayOfWeek Day(s) of the week this is tariff applies
   */
  public void setDayOfWeek(@Nullable DayOfWeekEnum[] dayOfWeek) {
    if (!isValidDayOfWeek(dayOfWeek)) {
      throw new PropertyConstraintException(dayOfWeek, "dayOfWeek is invalid");
    }
    this.dayOfWeek = dayOfWeek;
  }

  /**
   * Returns whether the given dayOfWeek is valid
   *
   * @param dayOfWeek the dayOfWeek to check the validity of
   * @return {@code true} if dayOfWeek is valid, {@code false} if not
   */
  private boolean isValidDayOfWeek(@Nullable DayOfWeekEnum[] dayOfWeek) {
    return dayOfWeek == null || (dayOfWeek.length >= 1 && dayOfWeek.length <= 7);
  }

  /**
   * Adds day(s) of the week this is tariff applies.
   *
   * @param dayOfWeek Day(s) of the week this is tariff applies
   * @return this
   */
  public TariffConditions withDayOfWeek(@Nullable DayOfWeekEnum[] dayOfWeek) {
    setDayOfWeek(dayOfWeek);
    return this;
  }

  /**
   * Gets start date in local time, for example: 2015-12-24.
   *
   * @return Start date in local time, for example: 2015-12-24
   */
  @Nullable
  public String getValidFromDate() {
    return validFromDate;
  }

  /**
   * Sets start date in local time, for example: 2015-12-24.
   *
   * @param validFromDate Start date in local time, for example: 2015-12-24
   */
  public void setValidFromDate(@Nullable String validFromDate) {
    this.validFromDate = validFromDate;
  }

  /**
   * Adds start date in local time, for example: 2015-12-24.
   *
   * @param validFromDate Start date in local time, for example: 2015-12-24
   * @return this
   */
  public TariffConditions withValidFromDate(@Nullable String validFromDate) {
    setValidFromDate(validFromDate);
    return this;
  }

  /**
   * Gets end date in local time, for example: 2015-12-27. Valid until this day (exclusive). Same
   * syntax as validFromDate.
   *
   * @return End date in local time, for example: 2015-12-27
   */
  @Nullable
  public String getValidToDate() {
    return validToDate;
  }

  /**
   * Sets end date in local time, for example: 2015-12-27. Valid until this day (exclusive). Same
   * syntax as validFromDate.
   *
   * @param validToDate End date in local time, for example: 2015-12-27
   */
  public void setValidToDate(@Nullable String validToDate) {
    this.validToDate = validToDate;
  }

  /**
   * Adds end date in local time, for example: 2015-12-27. Valid until this day (exclusive). Same
   * syntax as validFromDate.
   *
   * @param validToDate End date in local time, for example: 2015-12-27
   * @return this
   */
  public TariffConditions withValidToDate(@Nullable String validToDate) {
    setValidToDate(validToDate);
    return this;
  }

  /**
   * Gets type of EVSE (AC, DC) this tariff applies to.
   *
   * @return Type of EVSE (AC, DC) this tariff applies to
   */
  @Nullable
  public EvseKindEnum getEvseKind() {
    return evseKind;
  }

  /**
   * Sets type of EVSE (AC, DC) this tariff applies to.
   *
   * @param evseKind Type of EVSE (AC, DC) this tariff applies to
   */
  public void setEvseKind(@Nullable EvseKindEnum evseKind) {
    this.evseKind = evseKind;
  }

  /**
   * Adds type of EVSE (AC, DC) this tariff applies to.
   *
   * @param evseKind Type of EVSE (AC, DC) this tariff applies to
   * @return this
   */
  public TariffConditions withEvseKind(@Nullable EvseKindEnum evseKind) {
    setEvseKind(evseKind);
    return this;
  }

  /**
   * Gets minimum consumed energy in Wh, for example 20000 Wh. Valid from this amount of energy
   * (inclusive) being used.
   *
   * @return Minimum consumed energy in Wh, for example 20000 Wh
   */
  @Nullable
  public Double getMinEnergy() {
    return minEnergy;
  }

  /**
   * Sets minimum consumed energy in Wh, for example 20000 Wh. Valid from this amount of energy
   * (inclusive) being used.
   *
   * @param minEnergy Minimum consumed energy in Wh, for example 20000 Wh
   */
  public void setMinEnergy(@Nullable Double minEnergy) {
    this.minEnergy = minEnergy;
  }

  /**
   * Adds minimum consumed energy in Wh, for example 20000 Wh. Valid from this amount of energy
   * (inclusive) being used.
   *
   * @param minEnergy Minimum consumed energy in Wh, for example 20000 Wh
   * @return this
   */
  public TariffConditions withMinEnergy(@Nullable Double minEnergy) {
    setMinEnergy(minEnergy);
    return this;
  }

  /**
   * Gets maximum consumed energy in Wh, for example 50000 Wh. Valid until this amount of energy
   * (exclusive) being used.
   *
   * @return Maximum consumed energy in Wh, for example 50000 Wh
   */
  @Nullable
  public Double getMaxEnergy() {
    return maxEnergy;
  }

  /**
   * Sets maximum consumed energy in Wh, for example 50000 Wh. Valid until this amount of energy
   * (exclusive) being used.
   *
   * @param maxEnergy Maximum consumed energy in Wh, for example 50000 Wh
   */
  public void setMaxEnergy(@Nullable Double maxEnergy) {
    this.maxEnergy = maxEnergy;
  }

  /**
   * Adds maximum consumed energy in Wh, for example 50000 Wh. Valid until this amount of energy
   * (exclusive) being used.
   *
   * @param maxEnergy Maximum consumed energy in Wh, for example 50000 Wh
   * @return this
   */
  public TariffConditions withMaxEnergy(@Nullable Double maxEnergy) {
    setMaxEnergy(maxEnergy);
    return this;
  }

  /**
   * Gets sum of the minimum current (in Amperes) over all phases, for example 5 A.
   *
   * @return Sum of the minimum current (in Amperes) over all phases, for example 5 A
   */
  @Nullable
  public Double getMinCurrent() {
    return minCurrent;
  }

  /**
   * Sets sum of the minimum current (in Amperes) over all phases, for example 5 A.
   *
   * @param minCurrent Sum of the minimum current (in Amperes) over all phases, for example 5 A
   */
  public void setMinCurrent(@Nullable Double minCurrent) {
    this.minCurrent = minCurrent;
  }

  /**
   * Adds sum of the minimum current (in Amperes) over all phases, for example 5 A.
   *
   * @param minCurrent Sum of the minimum current (in Amperes) over all phases, for example 5 A
   * @return this
   */
  public TariffConditions withMinCurrent(@Nullable Double minCurrent) {
    setMinCurrent(minCurrent);
    return this;
  }

  /**
   * Gets sum of the maximum current (in Amperes) over all phases, for example 20 A. When the EV is
   * charging with less than the defined amount of current, this price becomes/is active. If the
   * charging current is or becomes higher, this price is not or no longer valid and becomes
   * inactive. This is NOT about the maximum current over the entire transaction.
   *
   * @return Sum of the maximum current (in Amperes) over all phases, for example 20 A
   */
  @Nullable
  public Double getMaxCurrent() {
    return maxCurrent;
  }

  /**
   * Sets sum of the maximum current (in Amperes) over all phases, for example 20 A. When the EV is
   * charging with less than the defined amount of current, this price becomes/is active. If the
   * charging current is or becomes higher, this price is not or no longer valid and becomes
   * inactive. This is NOT about the maximum current over the entire transaction.
   *
   * @param maxCurrent Sum of the maximum current (in Amperes) over all phases, for example 20 A
   */
  public void setMaxCurrent(@Nullable Double maxCurrent) {
    this.maxCurrent = maxCurrent;
  }

  /**
   * Adds sum of the maximum current (in Amperes) over all phases, for example 20 A. When the EV is
   * charging with less than the defined amount of current, this price becomes/is active. If the
   * charging current is or becomes higher, this price is not or no longer valid and becomes
   * inactive. This is NOT about the maximum current over the entire transaction.
   *
   * @param maxCurrent Sum of the maximum current (in Amperes) over all phases, for example 20 A
   * @return this
   */
  public TariffConditions withMaxCurrent(@Nullable Double maxCurrent) {
    setMaxCurrent(maxCurrent);
    return this;
  }

  /**
   * Gets minimum power in W, for example 5000 W. When the EV is charging with more than, or equal
   * to, the defined amount of power, this price is/becomes active. If the charging power is or
   * becomes lower, this price is not or no longer valid and becomes inactive. This is NOT about the
   * minimum power over the entire transaction.
   *
   * @return Minimum power in W, for example 5000 W
   */
  @Nullable
  public Double getMinPower() {
    return minPower;
  }

  /**
   * Sets minimum power in W, for example 5000 W. When the EV is charging with more than, or equal
   * to, the defined amount of power, this price is/becomes active. If the charging power is or
   * becomes lower, this price is not or no longer valid and becomes inactive. This is NOT about the
   * minimum power over the entire transaction.
   *
   * @param minPower Minimum power in W, for example 5000 W
   */
  public void setMinPower(@Nullable Double minPower) {
    this.minPower = minPower;
  }

  /**
   * Adds minimum power in W, for example 5000 W. When the EV is charging with more than, or equal
   * to, the defined amount of power, this price is/becomes active. If the charging power is or
   * becomes lower, this price is not or no longer valid and becomes inactive. This is NOT about the
   * minimum power over the entire transaction.
   *
   * @param minPower Minimum power in W, for example 5000 W
   * @return this
   */
  public TariffConditions withMinPower(@Nullable Double minPower) {
    setMinPower(minPower);
    return this;
  }

  /**
   * Gets maximum power in W, for example 20000 W. When the EV is charging with less than the
   * defined amount of power, this price becomes/is active. If the charging power is or becomes
   * higher, this price is not or no longer valid and becomes inactive. This is NOT about the
   * maximum power over the entire transaction.
   *
   * @return Maximum power in W, for example 20000 W
   */
  @Nullable
  public Double getMaxPower() {
    return maxPower;
  }

  /**
   * Sets maximum power in W, for example 20000 W. When the EV is charging with less than the
   * defined amount of power, this price becomes/is active. If the charging power is or becomes
   * higher, this price is not or no longer valid and becomes inactive. This is NOT about the
   * maximum power over the entire transaction.
   *
   * @param maxPower Maximum power in W, for example 20000 W
   */
  public void setMaxPower(@Nullable Double maxPower) {
    this.maxPower = maxPower;
  }

  /**
   * Adds maximum power in W, for example 20000 W. When the EV is charging with less than the
   * defined amount of power, this price becomes/is active. If the charging power is or becomes
   * higher, this price is not or no longer valid and becomes inactive. This is NOT about the
   * maximum power over the entire transaction.
   *
   * @param maxPower Maximum power in W, for example 20000 W
   * @return this
   */
  public TariffConditions withMaxPower(@Nullable Double maxPower) {
    setMaxPower(maxPower);
    return this;
  }

  /**
   * Gets minimum duration in seconds the transaction (charging and idle) MUST last (inclusive).
   * When the duration of a transaction is longer than the defined value, this price is or becomes
   * active. Before that moment, this price is not yet active.
   *
   * @return Minimum duration in seconds the transaction (charging and idle) MUST last (inclusive)
   */
  @Nullable
  public Integer getMinTime() {
    return minTime;
  }

  /**
   * Sets minimum duration in seconds the transaction (charging and idle) MUST last (inclusive).
   * When the duration of a transaction is longer than the defined value, this price is or becomes
   * active. Before that moment, this price is not yet active.
   *
   * @param minTime Minimum duration in seconds the transaction (charging and idle) MUST last
   *     (inclusive)
   */
  public void setMinTime(@Nullable Integer minTime) {
    this.minTime = minTime;
  }

  /**
   * Adds minimum duration in seconds the transaction (charging and idle) MUST last (inclusive).
   * When the duration of a transaction is longer than the defined value, this price is or becomes
   * active. Before that moment, this price is not yet active.
   *
   * @param minTime Minimum duration in seconds the transaction (charging and idle) MUST last
   *     (inclusive)
   * @return this
   */
  public TariffConditions withMinTime(@Nullable Integer minTime) {
    setMinTime(minTime);
    return this;
  }

  /**
   * Gets maximum duration in seconds the transaction (charging and idle) MUST last (exclusive).
   * When the duration of a transaction is shorter than the defined value, this price is or becomes
   * active. After that moment, this price is no longer active.
   *
   * @return Maximum duration in seconds the transaction (charging and idle) MUST last (exclusive)
   */
  @Nullable
  public Integer getMaxTime() {
    return maxTime;
  }

  /**
   * Sets maximum duration in seconds the transaction (charging and idle) MUST last (exclusive).
   * When the duration of a transaction is shorter than the defined value, this price is or becomes
   * active. After that moment, this price is no longer active.
   *
   * @param maxTime Maximum duration in seconds the transaction (charging and idle) MUST last
   *     (exclusive)
   */
  public void setMaxTime(@Nullable Integer maxTime) {
    this.maxTime = maxTime;
  }

  /**
   * Adds maximum duration in seconds the transaction (charging and idle) MUST last (exclusive).
   * When the duration of a transaction is shorter than the defined value, this price is or becomes
   * active. After that moment, this price is no longer active.
   *
   * @param maxTime Maximum duration in seconds the transaction (charging and idle) MUST last
   *     (exclusive)
   * @return this
   */
  public TariffConditions withMaxTime(@Nullable Integer maxTime) {
    setMaxTime(maxTime);
    return this;
  }

  /**
   * Gets minimum duration in seconds the charging MUST last (inclusive). When the duration of a
   * charging is longer than the defined value, this price is or becomes active. Before that moment,
   * this price is not yet active.
   *
   * @return Minimum duration in seconds the charging MUST last (inclusive)
   */
  @Nullable
  public Integer getMinChargingTime() {
    return minChargingTime;
  }

  /**
   * Sets minimum duration in seconds the charging MUST last (inclusive). When the duration of a
   * charging is longer than the defined value, this price is or becomes active. Before that moment,
   * this price is not yet active.
   *
   * @param minChargingTime Minimum duration in seconds the charging MUST last (inclusive)
   */
  public void setMinChargingTime(@Nullable Integer minChargingTime) {
    this.minChargingTime = minChargingTime;
  }

  /**
   * Adds minimum duration in seconds the charging MUST last (inclusive). When the duration of a
   * charging is longer than the defined value, this price is or becomes active. Before that moment,
   * this price is not yet active.
   *
   * @param minChargingTime Minimum duration in seconds the charging MUST last (inclusive)
   * @return this
   */
  public TariffConditions withMinChargingTime(@Nullable Integer minChargingTime) {
    setMinChargingTime(minChargingTime);
    return this;
  }

  /**
   * Gets maximum duration in seconds the charging MUST last (exclusive). When the duration of a
   * charging is shorter than the defined value, this price is or becomes active. After that moment,
   * this price is no longer active.
   *
   * @return Maximum duration in seconds the charging MUST last (exclusive)
   */
  @Nullable
  public Integer getMaxChargingTime() {
    return maxChargingTime;
  }

  /**
   * Sets maximum duration in seconds the charging MUST last (exclusive). When the duration of a
   * charging is shorter than the defined value, this price is or becomes active. After that moment,
   * this price is no longer active.
   *
   * @param maxChargingTime Maximum duration in seconds the charging MUST last (exclusive)
   */
  public void setMaxChargingTime(@Nullable Integer maxChargingTime) {
    this.maxChargingTime = maxChargingTime;
  }

  /**
   * Adds maximum duration in seconds the charging MUST last (exclusive). When the duration of a
   * charging is shorter than the defined value, this price is or becomes active. After that moment,
   * this price is no longer active.
   *
   * @param maxChargingTime Maximum duration in seconds the charging MUST last (exclusive)
   * @return this
   */
  public TariffConditions withMaxChargingTime(@Nullable Integer maxChargingTime) {
    setMaxChargingTime(maxChargingTime);
    return this;
  }

  /**
   * Gets minimum duration in seconds the idle period (i.e. not charging) MUST last (inclusive).
   * When the duration of the idle time is longer than the defined value, this price is or becomes
   * active. Before that moment, this price is not yet active.
   *
   * @return Minimum duration in seconds the idle period (i.e. not charging) MUST last (inclusive)
   */
  @Nullable
  public Integer getMinIdleTime() {
    return minIdleTime;
  }

  /**
   * Sets minimum duration in seconds the idle period (i.e. not charging) MUST last (inclusive).
   * When the duration of the idle time is longer than the defined value, this price is or becomes
   * active. Before that moment, this price is not yet active.
   *
   * @param minIdleTime Minimum duration in seconds the idle period (i.e. not charging) MUST last
   *     (inclusive)
   */
  public void setMinIdleTime(@Nullable Integer minIdleTime) {
    this.minIdleTime = minIdleTime;
  }

  /**
   * Adds minimum duration in seconds the idle period (i.e. not charging) MUST last (inclusive).
   * When the duration of the idle time is longer than the defined value, this price is or becomes
   * active. Before that moment, this price is not yet active.
   *
   * @param minIdleTime Minimum duration in seconds the idle period (i.e. not charging) MUST last
   *     (inclusive)
   * @return this
   */
  public TariffConditions withMinIdleTime(@Nullable Integer minIdleTime) {
    setMinIdleTime(minIdleTime);
    return this;
  }

  /**
   * Gets maximum duration in seconds the idle period (i.e. not charging) MUST last (exclusive).
   * When the duration of idle time is shorter than the defined value, this price is or becomes
   * active. After that moment, this price is no longer active.
   *
   * @return Maximum duration in seconds the idle period (i.e. not charging) MUST last (exclusive)
   */
  @Nullable
  public Integer getMaxIdleTime() {
    return maxIdleTime;
  }

  /**
   * Sets maximum duration in seconds the idle period (i.e. not charging) MUST last (exclusive).
   * When the duration of idle time is shorter than the defined value, this price is or becomes
   * active. After that moment, this price is no longer active.
   *
   * @param maxIdleTime Maximum duration in seconds the idle period (i.e. not charging) MUST last
   *     (exclusive)
   */
  public void setMaxIdleTime(@Nullable Integer maxIdleTime) {
    this.maxIdleTime = maxIdleTime;
  }

  /**
   * Adds maximum duration in seconds the idle period (i.e. not charging) MUST last (exclusive).
   * When the duration of idle time is shorter than the defined value, this price is or becomes
   * active. After that moment, this price is no longer active.
   *
   * @param maxIdleTime Maximum duration in seconds the idle period (i.e. not charging) MUST last
   *     (exclusive)
   * @return this
   */
  public TariffConditions withMaxIdleTime(@Nullable Integer maxIdleTime) {
    setMaxIdleTime(maxIdleTime);
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
  public TariffConditions withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  public boolean validate() {
    return isValidDayOfWeek(dayOfWeek) && isValidCustomData(customData);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TariffConditions that = (TariffConditions) o;
    return Objects.equals(startTimeOfDay, that.startTimeOfDay)
        && Objects.equals(endTimeOfDay, that.endTimeOfDay)
        && Arrays.equals(dayOfWeek, that.dayOfWeek)
        && Objects.equals(validFromDate, that.validFromDate)
        && Objects.equals(validToDate, that.validToDate)
        && Objects.equals(evseKind, that.evseKind)
        && Objects.equals(minEnergy, that.minEnergy)
        && Objects.equals(maxEnergy, that.maxEnergy)
        && Objects.equals(minCurrent, that.minCurrent)
        && Objects.equals(maxCurrent, that.maxCurrent)
        && Objects.equals(minPower, that.minPower)
        && Objects.equals(maxPower, that.maxPower)
        && Objects.equals(minTime, that.minTime)
        && Objects.equals(maxTime, that.maxTime)
        && Objects.equals(minChargingTime, that.minChargingTime)
        && Objects.equals(maxChargingTime, that.maxChargingTime)
        && Objects.equals(minIdleTime, that.minIdleTime)
        && Objects.equals(maxIdleTime, that.maxIdleTime)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        startTimeOfDay,
        endTimeOfDay,
        Arrays.hashCode(dayOfWeek),
        validFromDate,
        validToDate,
        evseKind,
        minEnergy,
        maxEnergy,
        minCurrent,
        maxCurrent,
        minPower,
        maxPower,
        minTime,
        maxTime,
        minChargingTime,
        maxChargingTime,
        minIdleTime,
        maxIdleTime,
        customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("startTimeOfDay", startTimeOfDay)
        .add("endTimeOfDay", endTimeOfDay)
        .add("dayOfWeek", dayOfWeek)
        .add("validFromDate", validFromDate)
        .add("validToDate", validToDate)
        .add("evseKind", evseKind)
        .add("minEnergy", minEnergy)
        .add("maxEnergy", maxEnergy)
        .add("minCurrent", minCurrent)
        .add("maxCurrent", maxCurrent)
        .add("minPower", minPower)
        .add("maxPower", maxPower)
        .add("minTime", minTime)
        .add("maxTime", maxTime)
        .add("minChargingTime", minChargingTime)
        .add("maxChargingTime", maxChargingTime)
        .add("minIdleTime", minIdleTime)
        .add("maxIdleTime", maxIdleTime)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
