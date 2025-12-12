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
public final class RationalNumber {
  /** The exponent to base 10 (dec) */
  private Integer exponent;

  /** Value which shall be multiplied. */
  private Integer value;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the RationalNumber class
   *
   * @param exponent The exponent to base 10 (dec)
   * @param value Value which shall be multiplied.
   */
  public RationalNumber(Integer exponent, Integer value) {
    setExponent(exponent);
    setValue(value);
  }

  /**
   * Gets the exponent to base 10 (dec)
   *
   * @return The exponent to base 10 (dec)
   */
  public Integer getExponent() {
    return exponent;
  }

  /**
   * Sets the exponent to base 10 (dec)
   *
   * @param exponent The exponent to base 10 (dec)
   */
  public void setExponent(Integer exponent) {
    if (!isValidExponent(exponent)) {
      throw new PropertyConstraintException(exponent, "exponent is invalid");
    }
    this.exponent = exponent;
  }

  /**
   * Returns whether the given exponent is valid
   *
   * @param exponent the exponent to check the validity of
   * @return {@code true} if exponent is valid, {@code false} if not
   */
  private boolean isValidExponent(Integer exponent) {
    return exponent != null;
  }

  /**
   * Gets value which shall be multiplied.
   *
   * @return Value which shall be multiplied
   */
  public Integer getValue() {
    return value;
  }

  /**
   * Sets value which shall be multiplied.
   *
   * @param value Value which shall be multiplied
   */
  public void setValue(Integer value) {
    if (!isValidValue(value)) {
      throw new PropertyConstraintException(value, "value is invalid");
    }
    this.value = value;
  }

  /**
   * Returns whether the given value is valid
   *
   * @param value the value to check the validity of
   * @return {@code true} if value is valid, {@code false} if not
   */
  private boolean isValidValue(Integer value) {
    return value != null;
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
  public RationalNumber withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  public boolean validate() {
    return isValidExponent(exponent) && isValidValue(value) && isValidCustomData(customData);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RationalNumber that = (RationalNumber) o;
    return Objects.equals(exponent, that.exponent)
        && Objects.equals(value, that.value)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(exponent, value, customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("exponent", exponent)
        .add("value", value)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
