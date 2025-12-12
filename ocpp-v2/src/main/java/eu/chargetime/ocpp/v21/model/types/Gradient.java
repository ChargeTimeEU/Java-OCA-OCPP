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

/** GradientType */
public final class Gradient {
  /** Id of setting */
  private Integer priority;

  /** Default ramp rate in seconds (0 if not applicable) */
  private Double gradient;

  /** Soft-start ramp rate in seconds (0 if not applicable) */
  private Double softGradient;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the Gradient class
   *
   * @param priority Id of setting
   * @param gradient Default ramp rate in seconds (0 if not applicable)
   * @param softGradient Soft-start ramp rate in seconds (0 if not applicable)
   */
  public Gradient(Integer priority, Double gradient, Double softGradient) {
    setPriority(priority);
    setGradient(gradient);
    setSoftGradient(softGradient);
  }

  /**
   * Gets id of setting
   *
   * @return Id of setting
   */
  public Integer getPriority() {
    return priority;
  }

  /**
   * Sets id of setting
   *
   * @param priority Id of setting
   */
  public void setPriority(Integer priority) {
    if (!isValidPriority(priority)) {
      throw new PropertyConstraintException(priority, "priority is invalid");
    }
    this.priority = priority;
  }

  /**
   * Returns whether the given priority is valid
   *
   * @param priority the priority to check the validity of
   * @return {@code true} if priority is valid, {@code false} if not
   */
  private boolean isValidPriority(Integer priority) {
    return priority != null && priority >= 0;
  }

  /**
   * Gets default ramp rate in seconds (0 if not applicable)
   *
   * @return Default ramp rate in seconds (0 if not applicable)
   */
  public Double getGradient() {
    return gradient;
  }

  /**
   * Sets default ramp rate in seconds (0 if not applicable)
   *
   * @param gradient Default ramp rate in seconds (0 if not applicable)
   */
  public void setGradient(Double gradient) {
    if (!isValidGradient(gradient)) {
      throw new PropertyConstraintException(gradient, "gradient is invalid");
    }
    this.gradient = gradient;
  }

  /**
   * Returns whether the given gradient is valid
   *
   * @param gradient the gradient to check the validity of
   * @return {@code true} if gradient is valid, {@code false} if not
   */
  private boolean isValidGradient(Double gradient) {
    return gradient != null;
  }

  /**
   * Gets soft-start ramp rate in seconds (0 if not applicable)
   *
   * @return Soft-start ramp rate in seconds (0 if not applicable)
   */
  public Double getSoftGradient() {
    return softGradient;
  }

  /**
   * Sets soft-start ramp rate in seconds (0 if not applicable)
   *
   * @param softGradient Soft-start ramp rate in seconds (0 if not applicable)
   */
  public void setSoftGradient(Double softGradient) {
    if (!isValidSoftGradient(softGradient)) {
      throw new PropertyConstraintException(softGradient, "softGradient is invalid");
    }
    this.softGradient = softGradient;
  }

  /**
   * Returns whether the given softGradient is valid
   *
   * @param softGradient the softGradient to check the validity of
   * @return {@code true} if softGradient is valid, {@code false} if not
   */
  private boolean isValidSoftGradient(Double softGradient) {
    return softGradient != null;
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
  public Gradient withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  public boolean validate() {
    return isValidPriority(priority)
        && isValidGradient(gradient)
        && isValidSoftGradient(softGradient)
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
    Gradient that = (Gradient) o;
    return Objects.equals(priority, that.priority)
        && Objects.equals(gradient, that.gradient)
        && Objects.equals(softGradient, that.softGradient)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(priority, gradient, softGradient, customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("priority", priority)
        .add("gradient", gradient)
        .add("softGradient", softGradient)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
