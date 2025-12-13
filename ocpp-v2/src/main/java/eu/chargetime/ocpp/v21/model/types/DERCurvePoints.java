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

/** DERCurvePointsType */
public final class DERCurvePoints {
  /** The data value of the X-axis (independent) variable, depending on the curve type. */
  private Double x;

  /**
   * The data value of the Y-axis (dependent) variable, depending on the DERUnitEnumType of the
   * curve. If y is power factor, then a positive value means DER is absorbing reactive power
   * (under-excited), a negative value when DER is injecting reactive power (over-excited).
   */
  private Double y;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the DERCurvePoints class
   *
   * @param x The data value of the X-axis (independent) variable, depending on the curve type.
   * @param y The data value of the Y-axis (dependent) variable, depending on the DERUnitEnumType of
   *     the curve. If y is power factor, then a positive value means DER is absorbing reactive
   *     power (under-excited), a negative value when DER is injecting reactive power
   *     (over-excited).
   */
  public DERCurvePoints(Double x, Double y) {
    setX(x);
    setY(y);
  }

  /**
   * Gets the data value of the X-axis (independent) variable, depending on the curve type.
   *
   * @return The data value of the X-axis (independent) variable, depending on the curve type
   */
  public Double getX() {
    return x;
  }

  /**
   * Sets the data value of the X-axis (independent) variable, depending on the curve type.
   *
   * @param x The data value of the X-axis (independent) variable, depending on the curve type
   */
  public void setX(Double x) {
    if (!isValidX(x)) {
      throw new PropertyConstraintException(x, "x is invalid");
    }
    this.x = x;
  }

  /**
   * Returns whether the given x is valid
   *
   * @param x the x to check the validity of
   * @return {@code true} if x is valid, {@code false} if not
   */
  private boolean isValidX(Double x) {
    return x != null;
  }

  /**
   * Gets the data value of the Y-axis (dependent) variable, depending on the DERUnitEnumType of the
   * curve. If y is power factor, then a positive value means DER is absorbing reactive power
   * (under-excited), a negative value when DER is injecting reactive power (over-excited).
   *
   * @return The data value of the Y-axis (dependent) variable, depending on the DERUnitEnumType of
   *     the curve
   */
  public Double getY() {
    return y;
  }

  /**
   * Sets the data value of the Y-axis (dependent) variable, depending on the DERUnitEnumType of the
   * curve. If y is power factor, then a positive value means DER is absorbing reactive power
   * (under-excited), a negative value when DER is injecting reactive power (over-excited).
   *
   * @param y The data value of the Y-axis (dependent) variable, depending on the DERUnitEnumType of
   *     the curve
   */
  public void setY(Double y) {
    if (!isValidY(y)) {
      throw new PropertyConstraintException(y, "y is invalid");
    }
    this.y = y;
  }

  /**
   * Returns whether the given y is valid
   *
   * @param y the y to check the validity of
   * @return {@code true} if y is valid, {@code false} if not
   */
  private boolean isValidY(Double y) {
    return y != null;
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
  public DERCurvePoints withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  public boolean validate() {
    return isValidX(x) && isValidY(y) && isValidCustomData(customData);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DERCurvePoints that = (DERCurvePoints) o;
    return Objects.equals(x, that.x)
        && Objects.equals(y, that.y)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y, customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("x", x)
        .add("y", y)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
