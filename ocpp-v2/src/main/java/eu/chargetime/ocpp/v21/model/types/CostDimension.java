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

/** Volume consumed of cost dimension. */
public final class CostDimension {
  /** Type of cost dimension: energy, power, time, etc. */
  private CostDimensionEnum type;

  /** Volume of the dimension consumed, measured according to the dimension type. */
  private Double volume;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the CostDimension class
   *
   * @param type Type of cost dimension: energy, power, time, etc.
   * @param volume Volume of the dimension consumed, measured according to the dimension type.
   */
  public CostDimension(CostDimensionEnum type, Double volume) {
    setType(type);
    setVolume(volume);
  }

  /**
   * Gets type of cost dimension: energy, power, time, etc.
   *
   * @return Type of cost dimension: energy, power, time, etc
   */
  public CostDimensionEnum getType() {
    return type;
  }

  /**
   * Sets type of cost dimension: energy, power, time, etc.
   *
   * @param type Type of cost dimension: energy, power, time, etc
   */
  public void setType(CostDimensionEnum type) {
    if (!isValidType(type)) {
      throw new PropertyConstraintException(type, "type is invalid");
    }
    this.type = type;
  }

  /**
   * Returns whether the given type is valid
   *
   * @param type the type to check the validity of
   * @return {@code true} if type is valid, {@code false} if not
   */
  private boolean isValidType(CostDimensionEnum type) {
    return type != null;
  }

  /**
   * Gets volume of the dimension consumed, measured according to the dimension type.
   *
   * @return Volume of the dimension consumed, measured according to the dimension type
   */
  public Double getVolume() {
    return volume;
  }

  /**
   * Sets volume of the dimension consumed, measured according to the dimension type.
   *
   * @param volume Volume of the dimension consumed, measured according to the dimension type
   */
  public void setVolume(Double volume) {
    if (!isValidVolume(volume)) {
      throw new PropertyConstraintException(volume, "volume is invalid");
    }
    this.volume = volume;
  }

  /**
   * Returns whether the given volume is valid
   *
   * @param volume the volume to check the validity of
   * @return {@code true} if volume is valid, {@code false} if not
   */
  private boolean isValidVolume(Double volume) {
    return volume != null;
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
  public CostDimension withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  public boolean validate() {
    return isValidType(type) && isValidVolume(volume) && isValidCustomData(customData);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CostDimension that = (CostDimension) o;
    return Objects.equals(type, that.type)
        && Objects.equals(volume, that.volume)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, volume, customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("type", type)
        .add("volume", volume)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
