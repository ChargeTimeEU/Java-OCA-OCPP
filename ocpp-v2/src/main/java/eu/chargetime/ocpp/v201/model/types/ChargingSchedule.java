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
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * Charging Schedule
 *
 * <p>Charging schedule structure defines a list of charging periods, as used in:
 * GetCompositeSchedule.conf and ChargingProfile.
 */
public final class ChargingSchedule {
  /** Custom data */
  @Nullable private CustomData customData;

  /** The identifier of the ChargingSchedule. */
  private Integer id;

  /**
   * Charging Schedule. Start Schedule. Date Time
   *
   * <p>Starting point of an absolute schedule. If absent the schedule will be relative to start of
   * charging.
   */
  @Nullable private ZonedDateTime startSchedule;

  /**
   * Charging Schedule. Duration. Elapsed Time
   *
   * <p>Duration of the charging schedule in seconds. If the duration is left empty, the last period
   * will continue indefinitely or until end of the transaction if chargingProfilePurpose =
   * TxProfile.
   */
  @Nullable private Integer duration;

  /**
   * Charging Schedule. Charging Rate Unit. Charging Rate Unit Code
   *
   * <p>The unit of measure Limit is expressed in.
   */
  private ChargingRateUnitEnum chargingRateUnit;

  /**
   * Charging Schedule Period
   *
   * <p>Charging schedule period structure defines a time period in a charging schedule.
   */
  private ChargingSchedulePeriod[] chargingSchedulePeriod;

  /**
   * Charging Schedule. Min Charging Rate. Numeric
   *
   * <p>Minimum charging rate supported by the EV. The unit of measure is defined by the
   * chargingRateUnit. This parameter is intended to be used by a local smart charging algorithm to
   * optimize the power allocation for in the case a charging process is inefficient at lower
   * charging rates. Accepts at most one digit fraction (e.g. 8.1)
   */
  @Nullable private Double minChargingRate;

  /**
   * Sales Tariff
   *
   * <p>NOTE: This dataType is based on dataTypes from ISO 15118-2.
   */
  @Nullable private SalesTariff salesTariff;

  /**
   * Constructor for the ChargingSchedule class
   *
   * @param id The identifier of the ChargingSchedule.
   * @param chargingRateUnit The unit of measure Limit is expressed in.
   * @param chargingSchedulePeriod Charging schedule period structure defines a time period in a
   *     charging schedule.
   */
  public ChargingSchedule(
      Integer id,
      ChargingRateUnitEnum chargingRateUnit,
      ChargingSchedulePeriod[] chargingSchedulePeriod) {
    setId(id);
    setChargingRateUnit(chargingRateUnit);
    setChargingSchedulePeriod(chargingSchedulePeriod);
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
  public ChargingSchedule withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  /**
   * Gets the identifier of the ChargingSchedule.
   *
   * @return The identifier of the ChargingSchedule
   */
  public Integer getId() {
    return id;
  }

  /**
   * Sets the identifier of the ChargingSchedule.
   *
   * @param id The identifier of the ChargingSchedule
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
    return id != null;
  }

  /**
   * Gets starting point of an absolute schedule. If absent the schedule will be relative to start
   * of charging.
   *
   * @return Starting point of an absolute schedule
   */
  @Nullable
  public ZonedDateTime getStartSchedule() {
    return startSchedule;
  }

  /**
   * Sets starting point of an absolute schedule. If absent the schedule will be relative to start
   * of charging.
   *
   * @param startSchedule Starting point of an absolute schedule
   */
  public void setStartSchedule(@Nullable ZonedDateTime startSchedule) {
    this.startSchedule = startSchedule;
  }

  /**
   * Adds starting point of an absolute schedule. If absent the schedule will be relative to start
   * of charging.
   *
   * @param startSchedule Starting point of an absolute schedule
   * @return this
   */
  public ChargingSchedule withStartSchedule(@Nullable ZonedDateTime startSchedule) {
    setStartSchedule(startSchedule);
    return this;
  }

  /**
   * Gets duration of the charging schedule in seconds. If the duration is left empty, the last
   * period will continue indefinitely or until end of the transaction if chargingProfilePurpose =
   * TxProfile.
   *
   * @return Duration of the charging schedule in seconds
   */
  @Nullable
  public Integer getDuration() {
    return duration;
  }

  /**
   * Sets duration of the charging schedule in seconds. If the duration is left empty, the last
   * period will continue indefinitely or until end of the transaction if chargingProfilePurpose =
   * TxProfile.
   *
   * @param duration Duration of the charging schedule in seconds
   */
  public void setDuration(@Nullable Integer duration) {
    this.duration = duration;
  }

  /**
   * Adds duration of the charging schedule in seconds. If the duration is left empty, the last
   * period will continue indefinitely or until end of the transaction if chargingProfilePurpose =
   * TxProfile.
   *
   * @param duration Duration of the charging schedule in seconds
   * @return this
   */
  public ChargingSchedule withDuration(@Nullable Integer duration) {
    setDuration(duration);
    return this;
  }

  /**
   * Gets the unit of measure Limit is expressed in.
   *
   * @return The unit of measure Limit is expressed in
   */
  public ChargingRateUnitEnum getChargingRateUnit() {
    return chargingRateUnit;
  }

  /**
   * Sets the unit of measure Limit is expressed in.
   *
   * @param chargingRateUnit The unit of measure Limit is expressed in
   */
  public void setChargingRateUnit(ChargingRateUnitEnum chargingRateUnit) {
    if (!isValidChargingRateUnit(chargingRateUnit)) {
      throw new PropertyConstraintException(chargingRateUnit, "chargingRateUnit is invalid");
    }
    this.chargingRateUnit = chargingRateUnit;
  }

  /**
   * Returns whether the given chargingRateUnit is valid
   *
   * @param chargingRateUnit the chargingRateUnit to check the validity of
   * @return {@code true} if chargingRateUnit is valid, {@code false} if not
   */
  private boolean isValidChargingRateUnit(ChargingRateUnitEnum chargingRateUnit) {
    return chargingRateUnit != null;
  }

  /**
   * Gets charging schedule period structure defines a time period in a charging schedule.
   *
   * @return Charging schedule period structure defines a time period in a charging schedule
   */
  public ChargingSchedulePeriod[] getChargingSchedulePeriod() {
    return chargingSchedulePeriod;
  }

  /**
   * Sets charging schedule period structure defines a time period in a charging schedule.
   *
   * @param chargingSchedulePeriod Charging schedule period structure defines a time period in a
   *     charging schedule
   */
  public void setChargingSchedulePeriod(ChargingSchedulePeriod[] chargingSchedulePeriod) {
    if (!isValidChargingSchedulePeriod(chargingSchedulePeriod)) {
      throw new PropertyConstraintException(
          chargingSchedulePeriod, "chargingSchedulePeriod is invalid");
    }
    this.chargingSchedulePeriod = chargingSchedulePeriod;
  }

  /**
   * Returns whether the given chargingSchedulePeriod is valid
   *
   * @param chargingSchedulePeriod the chargingSchedulePeriod to check the validity of
   * @return {@code true} if chargingSchedulePeriod is valid, {@code false} if not
   */
  private boolean isValidChargingSchedulePeriod(ChargingSchedulePeriod[] chargingSchedulePeriod) {
    return chargingSchedulePeriod != null
        && chargingSchedulePeriod.length >= 1
        && chargingSchedulePeriod.length <= 1024
        && Arrays.stream(chargingSchedulePeriod).allMatch(item -> item.validate());
  }

  /**
   * Gets minimum charging rate supported by the EV. The unit of measure is defined by the
   * chargingRateUnit. This parameter is intended to be used by a local smart charging algorithm to
   * optimize the power allocation for in the case a charging process is inefficient at lower
   * charging rates. Accepts at most one digit fraction (e.g. 8.1)
   *
   * @return Minimum charging rate supported by the EV
   */
  @Nullable
  public Double getMinChargingRate() {
    return minChargingRate;
  }

  /**
   * Sets minimum charging rate supported by the EV. The unit of measure is defined by the
   * chargingRateUnit. This parameter is intended to be used by a local smart charging algorithm to
   * optimize the power allocation for in the case a charging process is inefficient at lower
   * charging rates. Accepts at most one digit fraction (e.g. 8.1)
   *
   * @param minChargingRate Minimum charging rate supported by the EV
   */
  public void setMinChargingRate(@Nullable Double minChargingRate) {
    this.minChargingRate = minChargingRate;
  }

  /**
   * Adds minimum charging rate supported by the EV. The unit of measure is defined by the
   * chargingRateUnit. This parameter is intended to be used by a local smart charging algorithm to
   * optimize the power allocation for in the case a charging process is inefficient at lower
   * charging rates. Accepts at most one digit fraction (e.g. 8.1)
   *
   * @param minChargingRate Minimum charging rate supported by the EV
   * @return this
   */
  public ChargingSchedule withMinChargingRate(@Nullable Double minChargingRate) {
    setMinChargingRate(minChargingRate);
    return this;
  }

  /**
   * Gets NOTE: This dataType is based on dataTypes from ISO 15118-2.
   *
   * @return NOTE: This dataType is based on dataTypes from ISO 15118-2
   */
  @Nullable
  public SalesTariff getSalesTariff() {
    return salesTariff;
  }

  /**
   * Sets NOTE: This dataType is based on dataTypes from ISO 15118-2.
   *
   * @param salesTariff NOTE: This dataType is based on dataTypes from ISO 15118-2
   */
  public void setSalesTariff(@Nullable SalesTariff salesTariff) {
    if (!isValidSalesTariff(salesTariff)) {
      throw new PropertyConstraintException(salesTariff, "salesTariff is invalid");
    }
    this.salesTariff = salesTariff;
  }

  /**
   * Returns whether the given salesTariff is valid
   *
   * @param salesTariff the salesTariff to check the validity of
   * @return {@code true} if salesTariff is valid, {@code false} if not
   */
  private boolean isValidSalesTariff(@Nullable SalesTariff salesTariff) {
    return salesTariff == null || salesTariff.validate();
  }

  /**
   * Adds NOTE: This dataType is based on dataTypes from ISO 15118-2.
   *
   * @param salesTariff NOTE: This dataType is based on dataTypes from ISO 15118-2
   * @return this
   */
  public ChargingSchedule withSalesTariff(@Nullable SalesTariff salesTariff) {
    setSalesTariff(salesTariff);
    return this;
  }

  public boolean validate() {
    return isValidCustomData(customData)
        && isValidId(id)
        && isValidChargingRateUnit(chargingRateUnit)
        && isValidChargingSchedulePeriod(chargingSchedulePeriod)
        && isValidSalesTariff(salesTariff);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ChargingSchedule that = (ChargingSchedule) o;
    return Objects.equals(customData, that.customData)
        && Objects.equals(id, that.id)
        && Objects.equals(startSchedule, that.startSchedule)
        && Objects.equals(duration, that.duration)
        && Objects.equals(chargingRateUnit, that.chargingRateUnit)
        && Arrays.equals(chargingSchedulePeriod, that.chargingSchedulePeriod)
        && Objects.equals(minChargingRate, that.minChargingRate)
        && Objects.equals(salesTariff, that.salesTariff);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        customData,
        id,
        startSchedule,
        duration,
        chargingRateUnit,
        Arrays.hashCode(chargingSchedulePeriod),
        minChargingRate,
        salesTariff);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("id", id)
        .add("startSchedule", startSchedule)
        .add("duration", duration)
        .add("chargingRateUnit", chargingRateUnit)
        .add("chargingSchedulePeriod", chargingSchedulePeriod)
        .add("minChargingRate", minChargingRate)
        .add("salesTariff", salesTariff)
        .add("isValid", validate())
        .toString();
  }
}
