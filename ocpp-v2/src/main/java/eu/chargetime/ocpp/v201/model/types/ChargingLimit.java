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

/** Charging Limit */
public final class ChargingLimit {
  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Charging Limit. Charging Limit Source. Charging Limit Source Code
   *
   * <p>The source of the charging limit.
   */
  private ChargingLimitSourceEnum chargingLimitSource;

  /**
   * Charging Limit. Is Grid Critical. Indicator
   *
   * <p>Whether the charging limit is critical for the grid.
   */
  @Nullable private Boolean isGridCritical;

  /**
   * Constructor for the ChargingLimit class
   *
   * @param chargingLimitSource The source of the charging limit.
   */
  public ChargingLimit(ChargingLimitSourceEnum chargingLimitSource) {
    setChargingLimitSource(chargingLimitSource);
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
  public ChargingLimit withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  /**
   * Gets the source of the charging limit.
   *
   * @return The source of the charging limit
   */
  public ChargingLimitSourceEnum getChargingLimitSource() {
    return chargingLimitSource;
  }

  /**
   * Sets the source of the charging limit.
   *
   * @param chargingLimitSource The source of the charging limit
   */
  public void setChargingLimitSource(ChargingLimitSourceEnum chargingLimitSource) {
    if (!isValidChargingLimitSource(chargingLimitSource)) {
      throw new PropertyConstraintException(chargingLimitSource, "chargingLimitSource is invalid");
    }
    this.chargingLimitSource = chargingLimitSource;
  }

  /**
   * Returns whether the given chargingLimitSource is valid
   *
   * @param chargingLimitSource the chargingLimitSource to check the validity of
   * @return {@code true} if chargingLimitSource is valid, {@code false} if not
   */
  private boolean isValidChargingLimitSource(ChargingLimitSourceEnum chargingLimitSource) {
    return chargingLimitSource != null;
  }

  /**
   * Gets whether the charging limit is critical for the grid.
   *
   * @return Whether the charging limit is critical for the grid
   */
  @Nullable
  public Boolean getIsGridCritical() {
    return isGridCritical;
  }

  /**
   * Sets whether the charging limit is critical for the grid.
   *
   * @param isGridCritical Whether the charging limit is critical for the grid
   */
  public void setIsGridCritical(@Nullable Boolean isGridCritical) {
    this.isGridCritical = isGridCritical;
  }

  /**
   * Adds whether the charging limit is critical for the grid.
   *
   * @param isGridCritical Whether the charging limit is critical for the grid
   * @return this
   */
  public ChargingLimit withIsGridCritical(@Nullable Boolean isGridCritical) {
    setIsGridCritical(isGridCritical);
    return this;
  }

  public boolean validate() {
    return isValidCustomData(customData) && isValidChargingLimitSource(chargingLimitSource);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ChargingLimit that = (ChargingLimit) o;
    return Objects.equals(customData, that.customData)
        && Objects.equals(chargingLimitSource, that.chargingLimitSource)
        && Objects.equals(isGridCritical, that.isGridCritical);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customData, chargingLimitSource, isGridCritical);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("chargingLimitSource", chargingLimitSource)
        .add("isGridCritical", isGridCritical)
        .add("isValid", validate())
        .toString();
  }
}
