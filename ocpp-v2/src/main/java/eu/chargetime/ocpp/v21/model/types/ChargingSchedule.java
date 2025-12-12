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
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * Charging schedule structure defines a list of charging periods, as used in:
 * NotifyEVChargingScheduleRequest and ChargingProfileType. When used in a
 * NotifyEVChargingScheduleRequest only duration and chargingSchedulePeriod are relevant and
 * chargingRateUnit must be 'W'.
 *
 * <p>An ISO 15118-20 session may provide either an absolutePriceSchedule or a priceLevelSchedule.
 * An ISO 15118-2 session can only provide asalesTariff element. The field digestValue is used when
 * price schedule or sales tariff are signed.
 *
 * <p>image::images/ChargingSchedule-Simple.png[]
 */
public final class ChargingSchedule {
  /** id */
  private Integer id;

  /** limitAtSoC */
  @Nullable private LimitAtSoC limitAtSoC;

  /** Starting point of an absolute schedule or recurring schedule. */
  @Nullable private ZonedDateTime startSchedule;

  /**
   * Duration of the charging schedule in seconds. If the duration is left empty, the last period
   * will continue indefinitely or until end of the transaction in case startSchedule is absent.
   */
  @Nullable private Integer duration;

  /** The unit of measure in which limits and setpoints are expressed. */
  private ChargingRateUnitEnum chargingRateUnit;

  /**
   * Minimum charging rate supported by the EV. The unit of measure is defined by the
   * chargingRateUnit. This parameter is intended to be used by a local smart charging algorithm to
   * optimize the power allocation for in the case a charging process is inefficient at lower
   * charging rates.
   */
  @Nullable private Double minChargingRate;

  /** Power tolerance when following EVPowerProfile. */
  @Nullable private Double powerTolerance;

  /** Id of this element for referencing in a signature. */
  @Nullable private Integer signatureId;

  /**
   * Base64 encoded hash (SHA256 for ISO 15118-2, SHA512 for ISO 15118-20) of the EXI price schedule
   * element. Used in signature.
   */
  @Nullable private String digestValue;

  /**
   * Defaults to false. When true, disregard time zone offset in dateTime fields of
   * ChargingScheduleType and use unqualified local time at Charging Station instead. This allows
   * the same `Absolute` or `Recurring` charging profile to be used in both summer and winter time.
   */
  @Nullable private Boolean useLocalTime;

  /**
   * Charging schedule period structure defines a time period in a charging schedule. It is used in:
   * CompositeScheduleType and in ChargingScheduleType. When used in a
   * NotifyEVChargingScheduleRequest only startPeriod, limit, limitL2, limitL3 are relevant.
   */
  private ChargingSchedulePeriod[] chargingSchedulePeriod;

  /**
   * Defaults to 0. When randomizedDelay not equals zero, then the start of each
   * ChargingSchedulePeriodType is delayed by a randomly chosen number of seconds between 0 and
   * randomizedDelay. Only allowed for TxProfile and TxDefaultProfile.
   */
  @Nullable private Integer randomizedDelay;

  /**
   * A SalesTariff provided by a Mobility Operator (EMSP) . NOTE: This dataType is based on
   * dataTypes from ISO 15118-2.
   */
  @Nullable private SalesTariff salesTariff;

  /**
   * The AbsolutePriceScheduleType is modeled after the same type that is defined in ISO 15118-20,
   * such that if it is supplied by an EMSP as a signed EXI message, the conversion from EXI to JSON
   * (in OCPP) and back to EXI (for ISO 15118-20) does not change the digest and therefore does not
   * invalidate the signature.
   *
   * <p>image::images/AbsolutePriceSchedule-Simple.png[]
   */
  @Nullable private AbsolutePriceSchedule absolutePriceSchedule;

  /**
   * The PriceLevelScheduleType is modeled after the same type that is defined in ISO 15118-20, such
   * that if it is supplied by an EMSP as a signed EXI message, the conversion from EXI to JSON (in
   * OCPP) and back to EXI (for ISO 15118-20) does not change the digest and therefore does not
   * invalidate the signature.
   */
  @Nullable private PriceLevelSchedule priceLevelSchedule;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the ChargingSchedule class
   *
   * @param id id
   * @param chargingRateUnit The unit of measure in which limits and setpoints are expressed.
   * @param chargingSchedulePeriod Charging schedule period structure defines a time period in a
   *     charging schedule. It is used in: CompositeScheduleType and in ChargingScheduleType. When
   *     used in a NotifyEVChargingScheduleRequest only startPeriod, limit, limitL2, limitL3 are
   *     relevant.
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
   * Gets id
   *
   * @return id
   */
  public Integer getId() {
    return id;
  }

  /**
   * Sets id
   *
   * @param id id
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
   * Gets limitAtSoC
   *
   * @return limitAtSoC
   */
  @Nullable
  public LimitAtSoC getLimitAtSoC() {
    return limitAtSoC;
  }

  /**
   * Sets limitAtSoC
   *
   * @param limitAtSoC limitAtSoC
   */
  public void setLimitAtSoC(@Nullable LimitAtSoC limitAtSoC) {
    if (!isValidLimitAtSoC(limitAtSoC)) {
      throw new PropertyConstraintException(limitAtSoC, "limitAtSoC is invalid");
    }
    this.limitAtSoC = limitAtSoC;
  }

  /**
   * Returns whether the given limitAtSoC is valid
   *
   * @param limitAtSoC the limitAtSoC to check the validity of
   * @return {@code true} if limitAtSoC is valid, {@code false} if not
   */
  private boolean isValidLimitAtSoC(@Nullable LimitAtSoC limitAtSoC) {
    return limitAtSoC == null || limitAtSoC.validate();
  }

  /**
   * Adds limitAtSoC
   *
   * @param limitAtSoC limitAtSoC
   * @return this
   */
  public ChargingSchedule withLimitAtSoC(@Nullable LimitAtSoC limitAtSoC) {
    setLimitAtSoC(limitAtSoC);
    return this;
  }

  /**
   * Gets starting point of an absolute schedule or recurring schedule.
   *
   * @return Starting point of an absolute schedule or recurring schedule
   */
  @Nullable
  public ZonedDateTime getStartSchedule() {
    return startSchedule;
  }

  /**
   * Sets starting point of an absolute schedule or recurring schedule.
   *
   * @param startSchedule Starting point of an absolute schedule or recurring schedule
   */
  public void setStartSchedule(@Nullable ZonedDateTime startSchedule) {
    this.startSchedule = startSchedule;
  }

  /**
   * Adds starting point of an absolute schedule or recurring schedule.
   *
   * @param startSchedule Starting point of an absolute schedule or recurring schedule
   * @return this
   */
  public ChargingSchedule withStartSchedule(@Nullable ZonedDateTime startSchedule) {
    setStartSchedule(startSchedule);
    return this;
  }

  /**
   * Gets duration of the charging schedule in seconds. If the duration is left empty, the last
   * period will continue indefinitely or until end of the transaction in case startSchedule is
   * absent.
   *
   * @return Duration of the charging schedule in seconds
   */
  @Nullable
  public Integer getDuration() {
    return duration;
  }

  /**
   * Sets duration of the charging schedule in seconds. If the duration is left empty, the last
   * period will continue indefinitely or until end of the transaction in case startSchedule is
   * absent.
   *
   * @param duration Duration of the charging schedule in seconds
   */
  public void setDuration(@Nullable Integer duration) {
    this.duration = duration;
  }

  /**
   * Adds duration of the charging schedule in seconds. If the duration is left empty, the last
   * period will continue indefinitely or until end of the transaction in case startSchedule is
   * absent.
   *
   * @param duration Duration of the charging schedule in seconds
   * @return this
   */
  public ChargingSchedule withDuration(@Nullable Integer duration) {
    setDuration(duration);
    return this;
  }

  /**
   * Gets the unit of measure in which limits and setpoints are expressed.
   *
   * @return The unit of measure in which limits and setpoints are expressed
   */
  public ChargingRateUnitEnum getChargingRateUnit() {
    return chargingRateUnit;
  }

  /**
   * Sets the unit of measure in which limits and setpoints are expressed.
   *
   * @param chargingRateUnit The unit of measure in which limits and setpoints are expressed
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
   * Gets minimum charging rate supported by the EV. The unit of measure is defined by the
   * chargingRateUnit. This parameter is intended to be used by a local smart charging algorithm to
   * optimize the power allocation for in the case a charging process is inefficient at lower
   * charging rates.
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
   * charging rates.
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
   * charging rates.
   *
   * @param minChargingRate Minimum charging rate supported by the EV
   * @return this
   */
  public ChargingSchedule withMinChargingRate(@Nullable Double minChargingRate) {
    setMinChargingRate(minChargingRate);
    return this;
  }

  /**
   * Gets power tolerance when following EVPowerProfile.
   *
   * @return Power tolerance when following EVPowerProfile
   */
  @Nullable
  public Double getPowerTolerance() {
    return powerTolerance;
  }

  /**
   * Sets power tolerance when following EVPowerProfile.
   *
   * @param powerTolerance Power tolerance when following EVPowerProfile
   */
  public void setPowerTolerance(@Nullable Double powerTolerance) {
    this.powerTolerance = powerTolerance;
  }

  /**
   * Adds power tolerance when following EVPowerProfile.
   *
   * @param powerTolerance Power tolerance when following EVPowerProfile
   * @return this
   */
  public ChargingSchedule withPowerTolerance(@Nullable Double powerTolerance) {
    setPowerTolerance(powerTolerance);
    return this;
  }

  /**
   * Gets id of this element for referencing in a signature.
   *
   * @return Id of this element for referencing in a signature
   */
  @Nullable
  public Integer getSignatureId() {
    return signatureId;
  }

  /**
   * Sets id of this element for referencing in a signature.
   *
   * @param signatureId Id of this element for referencing in a signature
   */
  public void setSignatureId(@Nullable Integer signatureId) {
    if (!isValidSignatureId(signatureId)) {
      throw new PropertyConstraintException(signatureId, "signatureId is invalid");
    }
    this.signatureId = signatureId;
  }

  /**
   * Returns whether the given signatureId is valid
   *
   * @param signatureId the signatureId to check the validity of
   * @return {@code true} if signatureId is valid, {@code false} if not
   */
  private boolean isValidSignatureId(@Nullable Integer signatureId) {
    return signatureId == null || (signatureId >= 0);
  }

  /**
   * Adds id of this element for referencing in a signature.
   *
   * @param signatureId Id of this element for referencing in a signature
   * @return this
   */
  public ChargingSchedule withSignatureId(@Nullable Integer signatureId) {
    setSignatureId(signatureId);
    return this;
  }

  /**
   * Gets base64 encoded hash (SHA256 for ISO 15118-2, SHA512 for ISO 15118-20) of the EXI price
   * schedule element. Used in signature.
   *
   * @return Base64 encoded hash (SHA256 for ISO 15118-2, SHA512 for ISO 15118-20) of the EXI price
   *     schedule element
   */
  @Nullable
  public String getDigestValue() {
    return digestValue;
  }

  /**
   * Sets base64 encoded hash (SHA256 for ISO 15118-2, SHA512 for ISO 15118-20) of the EXI price
   * schedule element. Used in signature.
   *
   * @param digestValue Base64 encoded hash (SHA256 for ISO 15118-2, SHA512 for ISO 15118-20) of the
   *     EXI price schedule element
   */
  public void setDigestValue(@Nullable String digestValue) {
    if (!isValidDigestValue(digestValue)) {
      throw new PropertyConstraintException(digestValue, "digestValue is invalid");
    }
    this.digestValue = digestValue;
  }

  /**
   * Returns whether the given digestValue is valid
   *
   * @param digestValue the digestValue to check the validity of
   * @return {@code true} if digestValue is valid, {@code false} if not
   */
  private boolean isValidDigestValue(@Nullable String digestValue) {
    return digestValue == null || digestValue.length() <= 88;
  }

  /**
   * Adds base64 encoded hash (SHA256 for ISO 15118-2, SHA512 for ISO 15118-20) of the EXI price
   * schedule element. Used in signature.
   *
   * @param digestValue Base64 encoded hash (SHA256 for ISO 15118-2, SHA512 for ISO 15118-20) of the
   *     EXI price schedule element
   * @return this
   */
  public ChargingSchedule withDigestValue(@Nullable String digestValue) {
    setDigestValue(digestValue);
    return this;
  }

  /**
   * Gets defaults to false. When true, disregard time zone offset in dateTime fields of
   * ChargingScheduleType and use unqualified local time at Charging Station instead. This allows
   * the same `Absolute` or `Recurring` charging profile to be used in both summer and winter time.
   *
   * @return Defaults to false
   */
  @Nullable
  public Boolean getUseLocalTime() {
    return useLocalTime;
  }

  /**
   * Sets defaults to false. When true, disregard time zone offset in dateTime fields of
   * ChargingScheduleType and use unqualified local time at Charging Station instead. This allows
   * the same `Absolute` or `Recurring` charging profile to be used in both summer and winter time.
   *
   * @param useLocalTime Defaults to false
   */
  public void setUseLocalTime(@Nullable Boolean useLocalTime) {
    this.useLocalTime = useLocalTime;
  }

  /**
   * Adds defaults to false. When true, disregard time zone offset in dateTime fields of
   * ChargingScheduleType and use unqualified local time at Charging Station instead. This allows
   * the same `Absolute` or `Recurring` charging profile to be used in both summer and winter time.
   *
   * @param useLocalTime Defaults to false
   * @return this
   */
  public ChargingSchedule withUseLocalTime(@Nullable Boolean useLocalTime) {
    setUseLocalTime(useLocalTime);
    return this;
  }

  /**
   * Gets charging schedule period structure defines a time period in a charging schedule. It is
   * used in: CompositeScheduleType and in ChargingScheduleType. When used in a
   * NotifyEVChargingScheduleRequest only startPeriod, limit, limitL2, limitL3 are relevant.
   *
   * @return Charging schedule period structure defines a time period in a charging schedule
   */
  public ChargingSchedulePeriod[] getChargingSchedulePeriod() {
    return chargingSchedulePeriod;
  }

  /**
   * Sets charging schedule period structure defines a time period in a charging schedule. It is
   * used in: CompositeScheduleType and in ChargingScheduleType. When used in a
   * NotifyEVChargingScheduleRequest only startPeriod, limit, limitL2, limitL3 are relevant.
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
   * Gets defaults to 0. When randomizedDelay not equals zero, then the start of each
   * ChargingSchedulePeriodType is delayed by a randomly chosen number of seconds between 0 and
   * randomizedDelay. Only allowed for TxProfile and TxDefaultProfile.
   *
   * @return Defaults to 0
   */
  @Nullable
  public Integer getRandomizedDelay() {
    return randomizedDelay;
  }

  /**
   * Sets defaults to 0. When randomizedDelay not equals zero, then the start of each
   * ChargingSchedulePeriodType is delayed by a randomly chosen number of seconds between 0 and
   * randomizedDelay. Only allowed for TxProfile and TxDefaultProfile.
   *
   * @param randomizedDelay Defaults to 0
   */
  public void setRandomizedDelay(@Nullable Integer randomizedDelay) {
    if (!isValidRandomizedDelay(randomizedDelay)) {
      throw new PropertyConstraintException(randomizedDelay, "randomizedDelay is invalid");
    }
    this.randomizedDelay = randomizedDelay;
  }

  /**
   * Returns whether the given randomizedDelay is valid
   *
   * @param randomizedDelay the randomizedDelay to check the validity of
   * @return {@code true} if randomizedDelay is valid, {@code false} if not
   */
  private boolean isValidRandomizedDelay(@Nullable Integer randomizedDelay) {
    return randomizedDelay == null || (randomizedDelay >= 0);
  }

  /**
   * Adds defaults to 0. When randomizedDelay not equals zero, then the start of each
   * ChargingSchedulePeriodType is delayed by a randomly chosen number of seconds between 0 and
   * randomizedDelay. Only allowed for TxProfile and TxDefaultProfile.
   *
   * @param randomizedDelay Defaults to 0
   * @return this
   */
  public ChargingSchedule withRandomizedDelay(@Nullable Integer randomizedDelay) {
    setRandomizedDelay(randomizedDelay);
    return this;
  }

  /**
   * Gets a SalesTariff provided by a Mobility Operator (EMSP) . NOTE: This dataType is based on
   * dataTypes from ISO 15118-2.
   *
   * @return A SalesTariff provided by a Mobility Operator (EMSP)
   */
  @Nullable
  public SalesTariff getSalesTariff() {
    return salesTariff;
  }

  /**
   * Sets a SalesTariff provided by a Mobility Operator (EMSP) . NOTE: This dataType is based on
   * dataTypes from ISO 15118-2.
   *
   * @param salesTariff A SalesTariff provided by a Mobility Operator (EMSP)
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
   * Adds a SalesTariff provided by a Mobility Operator (EMSP) . NOTE: This dataType is based on
   * dataTypes from ISO 15118-2.
   *
   * @param salesTariff A SalesTariff provided by a Mobility Operator (EMSP)
   * @return this
   */
  public ChargingSchedule withSalesTariff(@Nullable SalesTariff salesTariff) {
    setSalesTariff(salesTariff);
    return this;
  }

  /**
   * Gets the AbsolutePriceScheduleType is modeled after the same type that is defined in ISO
   * 15118-20, such that if it is supplied by an EMSP as a signed EXI message, the conversion from
   * EXI to JSON (in OCPP) and back to EXI (for ISO 15118-20) does not change the digest and
   * therefore does not invalidate the signature.
   *
   * @return The AbsolutePriceScheduleType is modeled after the same type that is defined in ISO
   *     15118-20, such that if it is supplied by an EMSP as a signed EXI message, the conversion
   *     from EXI to JSON (in OCPP) and back to EXI (for ISO 15118-20) does not change the digest
   *     and therefore does not invalidate the signature
   */
  @Nullable
  public AbsolutePriceSchedule getAbsolutePriceSchedule() {
    return absolutePriceSchedule;
  }

  /**
   * Sets the AbsolutePriceScheduleType is modeled after the same type that is defined in ISO
   * 15118-20, such that if it is supplied by an EMSP as a signed EXI message, the conversion from
   * EXI to JSON (in OCPP) and back to EXI (for ISO 15118-20) does not change the digest and
   * therefore does not invalidate the signature.
   *
   * @param absolutePriceSchedule The AbsolutePriceScheduleType is modeled after the same type that
   *     is defined in ISO 15118-20, such that if it is supplied by an EMSP as a signed EXI message,
   *     the conversion from EXI to JSON (in OCPP) and back to EXI (for ISO 15118-20) does not
   *     change the digest and therefore does not invalidate the signature
   */
  public void setAbsolutePriceSchedule(@Nullable AbsolutePriceSchedule absolutePriceSchedule) {
    if (!isValidAbsolutePriceSchedule(absolutePriceSchedule)) {
      throw new PropertyConstraintException(
          absolutePriceSchedule, "absolutePriceSchedule is invalid");
    }
    this.absolutePriceSchedule = absolutePriceSchedule;
  }

  /**
   * Returns whether the given absolutePriceSchedule is valid
   *
   * @param absolutePriceSchedule the absolutePriceSchedule to check the validity of
   * @return {@code true} if absolutePriceSchedule is valid, {@code false} if not
   */
  private boolean isValidAbsolutePriceSchedule(
      @Nullable AbsolutePriceSchedule absolutePriceSchedule) {
    return absolutePriceSchedule == null || absolutePriceSchedule.validate();
  }

  /**
   * Adds the AbsolutePriceScheduleType is modeled after the same type that is defined in ISO
   * 15118-20, such that if it is supplied by an EMSP as a signed EXI message, the conversion from
   * EXI to JSON (in OCPP) and back to EXI (for ISO 15118-20) does not change the digest and
   * therefore does not invalidate the signature.
   *
   * @param absolutePriceSchedule The AbsolutePriceScheduleType is modeled after the same type that
   *     is defined in ISO 15118-20, such that if it is supplied by an EMSP as a signed EXI message,
   *     the conversion from EXI to JSON (in OCPP) and back to EXI (for ISO 15118-20) does not
   *     change the digest and therefore does not invalidate the signature
   * @return this
   */
  public ChargingSchedule withAbsolutePriceSchedule(
      @Nullable AbsolutePriceSchedule absolutePriceSchedule) {
    setAbsolutePriceSchedule(absolutePriceSchedule);
    return this;
  }

  /**
   * Gets the PriceLevelScheduleType is modeled after the same type that is defined in ISO 15118-20,
   * such that if it is supplied by an EMSP as a signed EXI message, the conversion from EXI to JSON
   * (in OCPP) and back to EXI (for ISO 15118-20) does not change the digest and therefore does not
   * invalidate the signature.
   *
   * @return The PriceLevelScheduleType is modeled after the same type that is defined in ISO
   *     15118-20, such that if it is supplied by an EMSP as a signed EXI message, the conversion
   *     from EXI to JSON (in OCPP) and back to EXI (for ISO 15118-20) does not change the digest
   *     and therefore does not invalidate the signature
   */
  @Nullable
  public PriceLevelSchedule getPriceLevelSchedule() {
    return priceLevelSchedule;
  }

  /**
   * Sets the PriceLevelScheduleType is modeled after the same type that is defined in ISO 15118-20,
   * such that if it is supplied by an EMSP as a signed EXI message, the conversion from EXI to JSON
   * (in OCPP) and back to EXI (for ISO 15118-20) does not change the digest and therefore does not
   * invalidate the signature.
   *
   * @param priceLevelSchedule The PriceLevelScheduleType is modeled after the same type that is
   *     defined in ISO 15118-20, such that if it is supplied by an EMSP as a signed EXI message,
   *     the conversion from EXI to JSON (in OCPP) and back to EXI (for ISO 15118-20) does not
   *     change the digest and therefore does not invalidate the signature
   */
  public void setPriceLevelSchedule(@Nullable PriceLevelSchedule priceLevelSchedule) {
    if (!isValidPriceLevelSchedule(priceLevelSchedule)) {
      throw new PropertyConstraintException(priceLevelSchedule, "priceLevelSchedule is invalid");
    }
    this.priceLevelSchedule = priceLevelSchedule;
  }

  /**
   * Returns whether the given priceLevelSchedule is valid
   *
   * @param priceLevelSchedule the priceLevelSchedule to check the validity of
   * @return {@code true} if priceLevelSchedule is valid, {@code false} if not
   */
  private boolean isValidPriceLevelSchedule(@Nullable PriceLevelSchedule priceLevelSchedule) {
    return priceLevelSchedule == null || priceLevelSchedule.validate();
  }

  /**
   * Adds the PriceLevelScheduleType is modeled after the same type that is defined in ISO 15118-20,
   * such that if it is supplied by an EMSP as a signed EXI message, the conversion from EXI to JSON
   * (in OCPP) and back to EXI (for ISO 15118-20) does not change the digest and therefore does not
   * invalidate the signature.
   *
   * @param priceLevelSchedule The PriceLevelScheduleType is modeled after the same type that is
   *     defined in ISO 15118-20, such that if it is supplied by an EMSP as a signed EXI message,
   *     the conversion from EXI to JSON (in OCPP) and back to EXI (for ISO 15118-20) does not
   *     change the digest and therefore does not invalidate the signature
   * @return this
   */
  public ChargingSchedule withPriceLevelSchedule(@Nullable PriceLevelSchedule priceLevelSchedule) {
    setPriceLevelSchedule(priceLevelSchedule);
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
  public ChargingSchedule withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  public boolean validate() {
    return isValidId(id)
        && isValidLimitAtSoC(limitAtSoC)
        && isValidChargingRateUnit(chargingRateUnit)
        && isValidSignatureId(signatureId)
        && isValidDigestValue(digestValue)
        && isValidChargingSchedulePeriod(chargingSchedulePeriod)
        && isValidRandomizedDelay(randomizedDelay)
        && isValidSalesTariff(salesTariff)
        && isValidAbsolutePriceSchedule(absolutePriceSchedule)
        && isValidPriceLevelSchedule(priceLevelSchedule)
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
    ChargingSchedule that = (ChargingSchedule) o;
    return Objects.equals(id, that.id)
        && Objects.equals(limitAtSoC, that.limitAtSoC)
        && Objects.equals(startSchedule, that.startSchedule)
        && Objects.equals(duration, that.duration)
        && Objects.equals(chargingRateUnit, that.chargingRateUnit)
        && Objects.equals(minChargingRate, that.minChargingRate)
        && Objects.equals(powerTolerance, that.powerTolerance)
        && Objects.equals(signatureId, that.signatureId)
        && Objects.equals(digestValue, that.digestValue)
        && Objects.equals(useLocalTime, that.useLocalTime)
        && Arrays.equals(chargingSchedulePeriod, that.chargingSchedulePeriod)
        && Objects.equals(randomizedDelay, that.randomizedDelay)
        && Objects.equals(salesTariff, that.salesTariff)
        && Objects.equals(absolutePriceSchedule, that.absolutePriceSchedule)
        && Objects.equals(priceLevelSchedule, that.priceLevelSchedule)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        id,
        limitAtSoC,
        startSchedule,
        duration,
        chargingRateUnit,
        minChargingRate,
        powerTolerance,
        signatureId,
        digestValue,
        useLocalTime,
        Arrays.hashCode(chargingSchedulePeriod),
        randomizedDelay,
        salesTariff,
        absolutePriceSchedule,
        priceLevelSchedule,
        customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("id", id)
        .add("limitAtSoC", limitAtSoC)
        .add("startSchedule", startSchedule)
        .add("duration", duration)
        .add("chargingRateUnit", chargingRateUnit)
        .add("minChargingRate", minChargingRate)
        .add("powerTolerance", powerTolerance)
        .add("signatureId", signatureId)
        .add("digestValue", digestValue)
        .add("useLocalTime", useLocalTime)
        .add("chargingSchedulePeriod", chargingSchedulePeriod)
        .add("randomizedDelay", randomizedDelay)
        .add("salesTariff", salesTariff)
        .add("absolutePriceSchedule", absolutePriceSchedule)
        .add("priceLevelSchedule", priceLevelSchedule)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
