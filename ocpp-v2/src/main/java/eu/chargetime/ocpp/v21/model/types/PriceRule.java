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

/** Part of ISO 15118-20 price schedule. */
public final class PriceRule {
  /**
   * The duration of the parking fee period (in seconds). When the time enters into a
   * ParkingFeePeriod, the ParkingFee will apply to the session. .
   */
  @Nullable private Integer parkingFeePeriod;

  /** Number of grams of CO2 per kWh. */
  @Nullable private Integer carbonDioxideEmission;

  /** Percentage of the power that is created by renewable resources. */
  @Nullable private Integer renewableGenerationPercentage;

  /** Part of ISO 15118-20 price schedule. */
  private RationalNumber energyFee;

  /** Part of ISO 15118-20 price schedule. */
  @Nullable private RationalNumber parkingFee;

  /** Part of ISO 15118-20 price schedule. */
  private RationalNumber powerRangeStart;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the PriceRule class
   *
   * @param energyFee Part of ISO 15118-20 price schedule.
   * @param powerRangeStart Part of ISO 15118-20 price schedule.
   */
  public PriceRule(RationalNumber energyFee, RationalNumber powerRangeStart) {
    setEnergyFee(energyFee);
    setPowerRangeStart(powerRangeStart);
  }

  /**
   * Gets the duration of the parking fee period (in seconds). When the time enters into a
   * ParkingFeePeriod, the ParkingFee will apply to the session. .
   *
   * @return The duration of the parking fee period (in seconds)
   */
  @Nullable
  public Integer getParkingFeePeriod() {
    return parkingFeePeriod;
  }

  /**
   * Sets the duration of the parking fee period (in seconds). When the time enters into a
   * ParkingFeePeriod, the ParkingFee will apply to the session. .
   *
   * @param parkingFeePeriod The duration of the parking fee period (in seconds)
   */
  public void setParkingFeePeriod(@Nullable Integer parkingFeePeriod) {
    this.parkingFeePeriod = parkingFeePeriod;
  }

  /**
   * Adds the duration of the parking fee period (in seconds). When the time enters into a
   * ParkingFeePeriod, the ParkingFee will apply to the session. .
   *
   * @param parkingFeePeriod The duration of the parking fee period (in seconds)
   * @return this
   */
  public PriceRule withParkingFeePeriod(@Nullable Integer parkingFeePeriod) {
    setParkingFeePeriod(parkingFeePeriod);
    return this;
  }

  /**
   * Gets number of grams of CO2 per kWh.
   *
   * @return Number of grams of CO2 per kWh
   */
  @Nullable
  public Integer getCarbonDioxideEmission() {
    return carbonDioxideEmission;
  }

  /**
   * Sets number of grams of CO2 per kWh.
   *
   * @param carbonDioxideEmission Number of grams of CO2 per kWh
   */
  public void setCarbonDioxideEmission(@Nullable Integer carbonDioxideEmission) {
    if (!isValidCarbonDioxideEmission(carbonDioxideEmission)) {
      throw new PropertyConstraintException(
          carbonDioxideEmission, "carbonDioxideEmission is invalid");
    }
    this.carbonDioxideEmission = carbonDioxideEmission;
  }

  /**
   * Returns whether the given carbonDioxideEmission is valid
   *
   * @param carbonDioxideEmission the carbonDioxideEmission to check the validity of
   * @return {@code true} if carbonDioxideEmission is valid, {@code false} if not
   */
  private boolean isValidCarbonDioxideEmission(@Nullable Integer carbonDioxideEmission) {
    return carbonDioxideEmission == null || (carbonDioxideEmission >= 0);
  }

  /**
   * Adds number of grams of CO2 per kWh.
   *
   * @param carbonDioxideEmission Number of grams of CO2 per kWh
   * @return this
   */
  public PriceRule withCarbonDioxideEmission(@Nullable Integer carbonDioxideEmission) {
    setCarbonDioxideEmission(carbonDioxideEmission);
    return this;
  }

  /**
   * Gets percentage of the power that is created by renewable resources.
   *
   * @return Percentage of the power that is created by renewable resources
   */
  @Nullable
  public Integer getRenewableGenerationPercentage() {
    return renewableGenerationPercentage;
  }

  /**
   * Sets percentage of the power that is created by renewable resources.
   *
   * @param renewableGenerationPercentage Percentage of the power that is created by renewable
   *     resources
   */
  public void setRenewableGenerationPercentage(@Nullable Integer renewableGenerationPercentage) {
    if (!isValidRenewableGenerationPercentage(renewableGenerationPercentage)) {
      throw new PropertyConstraintException(
          renewableGenerationPercentage, "renewableGenerationPercentage is invalid");
    }
    this.renewableGenerationPercentage = renewableGenerationPercentage;
  }

  /**
   * Returns whether the given renewableGenerationPercentage is valid
   *
   * @param renewableGenerationPercentage the renewableGenerationPercentage to check the validity of
   * @return {@code true} if renewableGenerationPercentage is valid, {@code false} if not
   */
  private boolean isValidRenewableGenerationPercentage(
      @Nullable Integer renewableGenerationPercentage) {
    return renewableGenerationPercentage == null
        || (renewableGenerationPercentage >= 0 && renewableGenerationPercentage <= 100);
  }

  /**
   * Adds percentage of the power that is created by renewable resources.
   *
   * @param renewableGenerationPercentage Percentage of the power that is created by renewable
   *     resources
   * @return this
   */
  public PriceRule withRenewableGenerationPercentage(
      @Nullable Integer renewableGenerationPercentage) {
    setRenewableGenerationPercentage(renewableGenerationPercentage);
    return this;
  }

  /**
   * Gets part of ISO 15118-20 price schedule.
   *
   * @return Part of ISO 15118-20 price schedule
   */
  public RationalNumber getEnergyFee() {
    return energyFee;
  }

  /**
   * Sets part of ISO 15118-20 price schedule.
   *
   * @param energyFee Part of ISO 15118-20 price schedule
   */
  public void setEnergyFee(RationalNumber energyFee) {
    if (!isValidEnergyFee(energyFee)) {
      throw new PropertyConstraintException(energyFee, "energyFee is invalid");
    }
    this.energyFee = energyFee;
  }

  /**
   * Returns whether the given energyFee is valid
   *
   * @param energyFee the energyFee to check the validity of
   * @return {@code true} if energyFee is valid, {@code false} if not
   */
  private boolean isValidEnergyFee(RationalNumber energyFee) {
    return energyFee != null && energyFee.validate();
  }

  /**
   * Gets part of ISO 15118-20 price schedule.
   *
   * @return Part of ISO 15118-20 price schedule
   */
  @Nullable
  public RationalNumber getParkingFee() {
    return parkingFee;
  }

  /**
   * Sets part of ISO 15118-20 price schedule.
   *
   * @param parkingFee Part of ISO 15118-20 price schedule
   */
  public void setParkingFee(@Nullable RationalNumber parkingFee) {
    if (!isValidParkingFee(parkingFee)) {
      throw new PropertyConstraintException(parkingFee, "parkingFee is invalid");
    }
    this.parkingFee = parkingFee;
  }

  /**
   * Returns whether the given parkingFee is valid
   *
   * @param parkingFee the parkingFee to check the validity of
   * @return {@code true} if parkingFee is valid, {@code false} if not
   */
  private boolean isValidParkingFee(@Nullable RationalNumber parkingFee) {
    return parkingFee == null || parkingFee.validate();
  }

  /**
   * Adds part of ISO 15118-20 price schedule.
   *
   * @param parkingFee Part of ISO 15118-20 price schedule
   * @return this
   */
  public PriceRule withParkingFee(@Nullable RationalNumber parkingFee) {
    setParkingFee(parkingFee);
    return this;
  }

  /**
   * Gets part of ISO 15118-20 price schedule.
   *
   * @return Part of ISO 15118-20 price schedule
   */
  public RationalNumber getPowerRangeStart() {
    return powerRangeStart;
  }

  /**
   * Sets part of ISO 15118-20 price schedule.
   *
   * @param powerRangeStart Part of ISO 15118-20 price schedule
   */
  public void setPowerRangeStart(RationalNumber powerRangeStart) {
    if (!isValidPowerRangeStart(powerRangeStart)) {
      throw new PropertyConstraintException(powerRangeStart, "powerRangeStart is invalid");
    }
    this.powerRangeStart = powerRangeStart;
  }

  /**
   * Returns whether the given powerRangeStart is valid
   *
   * @param powerRangeStart the powerRangeStart to check the validity of
   * @return {@code true} if powerRangeStart is valid, {@code false} if not
   */
  private boolean isValidPowerRangeStart(RationalNumber powerRangeStart) {
    return powerRangeStart != null && powerRangeStart.validate();
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
  public PriceRule withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  public boolean validate() {
    return isValidCarbonDioxideEmission(carbonDioxideEmission)
        && isValidRenewableGenerationPercentage(renewableGenerationPercentage)
        && isValidEnergyFee(energyFee)
        && isValidParkingFee(parkingFee)
        && isValidPowerRangeStart(powerRangeStart)
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
    PriceRule that = (PriceRule) o;
    return Objects.equals(parkingFeePeriod, that.parkingFeePeriod)
        && Objects.equals(carbonDioxideEmission, that.carbonDioxideEmission)
        && Objects.equals(renewableGenerationPercentage, that.renewableGenerationPercentage)
        && Objects.equals(energyFee, that.energyFee)
        && Objects.equals(parkingFee, that.parkingFee)
        && Objects.equals(powerRangeStart, that.powerRangeStart)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        parkingFeePeriod,
        carbonDioxideEmission,
        renewableGenerationPercentage,
        energyFee,
        parkingFee,
        powerRangeStart,
        customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("parkingFeePeriod", parkingFeePeriod)
        .add("carbonDioxideEmission", carbonDioxideEmission)
        .add("renewableGenerationPercentage", renewableGenerationPercentage)
        .add("energyFee", energyFee)
        .add("parkingFee", parkingFee)
        .add("powerRangeStart", powerRangeStart)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
