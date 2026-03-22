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

/** ChargingLimitType */
public final class ChargingLimit {
  /**
   * The source of the charging limit. Values defined in appendix as
   * ChargingLimitSourceEnumStringType.
   */
  private String chargingLimitSource;

  /**
   * True when the reported limit concerns local generation that is providing extra capacity,
   * instead of a limitation.
   */
  @Nullable private Boolean isLocalGeneration;

  /** Whether the charging limit is critical for the grid. */
  @Nullable private Boolean isGridCritical;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the ChargingLimit class
   *
   * @param chargingLimitSource The source of the charging limit. Values defined in appendix as
   *     ChargingLimitSourceEnumStringType.
   */
  public ChargingLimit(String chargingLimitSource) {
    setChargingLimitSource(chargingLimitSource);
  }

  /**
   * Gets the source of the charging limit. Values defined in appendix as
   * ChargingLimitSourceEnumStringType.
   *
   * @return The source of the charging limit
   */
  public String getChargingLimitSource() {
    return chargingLimitSource;
  }

  /**
   * Sets the source of the charging limit. Values defined in appendix as
   * ChargingLimitSourceEnumStringType.
   *
   * @param chargingLimitSource The source of the charging limit
   */
  public void setChargingLimitSource(String chargingLimitSource) {
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
  private boolean isValidChargingLimitSource(String chargingLimitSource) {
    return chargingLimitSource != null && chargingLimitSource.length() <= 20;
  }

  /**
   * Gets true when the reported limit concerns local generation that is providing extra capacity,
   * instead of a limitation.
   *
   * @return True when the reported limit concerns local generation that is providing extra
   *     capacity, instead of a limitation
   */
  @Nullable
  public Boolean getIsLocalGeneration() {
    return isLocalGeneration;
  }

  /**
   * Sets true when the reported limit concerns local generation that is providing extra capacity,
   * instead of a limitation.
   *
   * @param isLocalGeneration True when the reported limit concerns local generation that is
   *     providing extra capacity, instead of a limitation
   */
  public void setIsLocalGeneration(@Nullable Boolean isLocalGeneration) {
    this.isLocalGeneration = isLocalGeneration;
  }

  /**
   * Adds true when the reported limit concerns local generation that is providing extra capacity,
   * instead of a limitation.
   *
   * @param isLocalGeneration True when the reported limit concerns local generation that is
   *     providing extra capacity, instead of a limitation
   * @return this
   */
  public ChargingLimit withIsLocalGeneration(@Nullable Boolean isLocalGeneration) {
    setIsLocalGeneration(isLocalGeneration);
    return this;
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

  public boolean validate() {
    return isValidChargingLimitSource(chargingLimitSource) && isValidCustomData(customData);
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
    return Objects.equals(chargingLimitSource, that.chargingLimitSource)
        && Objects.equals(isLocalGeneration, that.isLocalGeneration)
        && Objects.equals(isGridCritical, that.isGridCritical)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(chargingLimitSource, isLocalGeneration, isGridCritical, customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("chargingLimitSource", chargingLimitSource)
        .add("isLocalGeneration", isLocalGeneration)
        .add("isGridCritical", isGridCritical)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
