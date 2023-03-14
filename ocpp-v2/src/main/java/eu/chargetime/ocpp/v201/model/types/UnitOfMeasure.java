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

/** A UnitOfMeasure with a multiplier */
public final class UnitOfMeasure {
  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Unit of the value. Default = "Wh" if the (default) measurand is an "Energy" type. This field
   * SHALL use a value from the list Standardized Units of Measurements in Part 2 Appendices. If an
   * applicable unit is available in that list, otherwise a "custom" unit might be used.
   */
  @Nullable private String unit;

  /**
   * Multiplier, this value represents the exponent to base 10. I.e. multiplier 3 means 10 raised to
   * the 3rd power. Default is 0.
   */
  @Nullable private Integer multiplier;

  /** Constructor for the UnitOfMeasure class */
  public UnitOfMeasure() {}

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
  public UnitOfMeasure withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  /**
   * Gets unit of the value. Default = "Wh" if the (default) measurand is an "Energy" type. This
   * field SHALL use a value from the list Standardized Units of Measurements in Part 2 Appendices.
   * If an applicable unit is available in that list, otherwise a "custom" unit might be used.
   *
   * @return Unit of the value
   */
  public String getUnit() {
    return unit != null ? unit : "Wh";
  }

  /**
   * Sets unit of the value. Default = "Wh" if the (default) measurand is an "Energy" type. This
   * field SHALL use a value from the list Standardized Units of Measurements in Part 2 Appendices.
   * If an applicable unit is available in that list, otherwise a "custom" unit might be used.
   *
   * @param unit Unit of the value
   */
  public void setUnit(@Nullable String unit) {
    if (!isValidUnit(unit)) {
      throw new PropertyConstraintException(unit, "unit is invalid");
    }
    this.unit = unit;
  }

  /**
   * Returns whether the given unit is valid
   *
   * @param unit the unit to check the validity of
   * @return {@code true} if unit is valid, {@code false} if not
   */
  private boolean isValidUnit(@Nullable String unit) {
    return unit == null || unit.length() <= 20;
  }

  /**
   * Adds unit of the value. Default = "Wh" if the (default) measurand is an "Energy" type. This
   * field SHALL use a value from the list Standardized Units of Measurements in Part 2 Appendices.
   * If an applicable unit is available in that list, otherwise a "custom" unit might be used.
   *
   * @param unit Unit of the value
   * @return this
   */
  public UnitOfMeasure withUnit(@Nullable String unit) {
    setUnit(unit);
    return this;
  }

  /**
   * Gets multiplier, this value represents the exponent to base 10. I.e. multiplier 3 means 10
   * raised to the 3rd power. Default is 0.
   *
   * @return Multiplier, this value represents the exponent to base 10
   */
  public Integer getMultiplier() {
    return multiplier != null ? multiplier : 0;
  }

  /**
   * Sets multiplier, this value represents the exponent to base 10. I.e. multiplier 3 means 10
   * raised to the 3rd power. Default is 0.
   *
   * @param multiplier Multiplier, this value represents the exponent to base 10
   */
  public void setMultiplier(@Nullable Integer multiplier) {
    this.multiplier = multiplier;
  }

  /**
   * Adds multiplier, this value represents the exponent to base 10. I.e. multiplier 3 means 10
   * raised to the 3rd power. Default is 0.
   *
   * @param multiplier Multiplier, this value represents the exponent to base 10
   * @return this
   */
  public UnitOfMeasure withMultiplier(@Nullable Integer multiplier) {
    setMultiplier(multiplier);
    return this;
  }

  public boolean validate() {
    return isValidCustomData(customData) && isValidUnit(unit);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UnitOfMeasure that = (UnitOfMeasure) o;
    return Objects.equals(customData, that.customData)
        && Objects.equals(unit, that.unit)
        && Objects.equals(multiplier, that.multiplier);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customData, unit, multiplier);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("unit", unit)
        .add("multiplier", multiplier)
        .add("isValid", validate())
        .toString();
  }
}
