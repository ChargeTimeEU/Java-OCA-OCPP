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

/** ReactivePowerParamsType */
public final class ReactivePowerParams {
  /**
   * Only for VoltVar curve: The nominal ac voltage (rms) adjustment to the voltage curve points for
   * Volt-Var curves (percentage).
   */
  @Nullable private Double vRef;

  /** Only for VoltVar: Enable/disable autonomous VRef adjustment */
  @Nullable private Boolean autonomousVRefEnable;

  /** Only for VoltVar: Adjustment range for VRef time constant */
  @Nullable private Double autonomousVRefTimeConstant;

  /** Custom data */
  @Nullable private CustomData customData;

  /** Constructor for the ReactivePowerParams class */
  public ReactivePowerParams() {}

  /**
   * Gets only for VoltVar curve: The nominal ac voltage (rms) adjustment to the voltage curve
   * points for Volt-Var curves (percentage).
   *
   * @return Only for VoltVar curve: The nominal ac voltage (rms) adjustment to the voltage curve
   *     points for Volt-Var curves (percentage)
   */
  @Nullable
  public Double getVRef() {
    return vRef;
  }

  /**
   * Sets only for VoltVar curve: The nominal ac voltage (rms) adjustment to the voltage curve
   * points for Volt-Var curves (percentage).
   *
   * @param vRef Only for VoltVar curve: The nominal ac voltage (rms) adjustment to the voltage
   *     curve points for Volt-Var curves (percentage)
   */
  public void setVRef(@Nullable Double vRef) {
    this.vRef = vRef;
  }

  /**
   * Adds only for VoltVar curve: The nominal ac voltage (rms) adjustment to the voltage curve
   * points for Volt-Var curves (percentage).
   *
   * @param vRef Only for VoltVar curve: The nominal ac voltage (rms) adjustment to the voltage
   *     curve points for Volt-Var curves (percentage)
   * @return this
   */
  public ReactivePowerParams withVRef(@Nullable Double vRef) {
    setVRef(vRef);
    return this;
  }

  /**
   * Gets only for VoltVar: Enable/disable autonomous VRef adjustment
   *
   * @return Only for VoltVar: Enable/disable autonomous VRef adjustment
   */
  @Nullable
  public Boolean getAutonomousVRefEnable() {
    return autonomousVRefEnable;
  }

  /**
   * Sets only for VoltVar: Enable/disable autonomous VRef adjustment
   *
   * @param autonomousVRefEnable Only for VoltVar: Enable/disable autonomous VRef adjustment
   */
  public void setAutonomousVRefEnable(@Nullable Boolean autonomousVRefEnable) {
    this.autonomousVRefEnable = autonomousVRefEnable;
  }

  /**
   * Adds only for VoltVar: Enable/disable autonomous VRef adjustment
   *
   * @param autonomousVRefEnable Only for VoltVar: Enable/disable autonomous VRef adjustment
   * @return this
   */
  public ReactivePowerParams withAutonomousVRefEnable(@Nullable Boolean autonomousVRefEnable) {
    setAutonomousVRefEnable(autonomousVRefEnable);
    return this;
  }

  /**
   * Gets only for VoltVar: Adjustment range for VRef time constant
   *
   * @return Only for VoltVar: Adjustment range for VRef time constant
   */
  @Nullable
  public Double getAutonomousVRefTimeConstant() {
    return autonomousVRefTimeConstant;
  }

  /**
   * Sets only for VoltVar: Adjustment range for VRef time constant
   *
   * @param autonomousVRefTimeConstant Only for VoltVar: Adjustment range for VRef time constant
   */
  public void setAutonomousVRefTimeConstant(@Nullable Double autonomousVRefTimeConstant) {
    this.autonomousVRefTimeConstant = autonomousVRefTimeConstant;
  }

  /**
   * Adds only for VoltVar: Adjustment range for VRef time constant
   *
   * @param autonomousVRefTimeConstant Only for VoltVar: Adjustment range for VRef time constant
   * @return this
   */
  public ReactivePowerParams withAutonomousVRefTimeConstant(
      @Nullable Double autonomousVRefTimeConstant) {
    setAutonomousVRefTimeConstant(autonomousVRefTimeConstant);
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
  public ReactivePowerParams withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  public boolean validate() {
    return isValidCustomData(customData);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ReactivePowerParams that = (ReactivePowerParams) o;
    return Objects.equals(vRef, that.vRef)
        && Objects.equals(autonomousVRefEnable, that.autonomousVRefEnable)
        && Objects.equals(autonomousVRefTimeConstant, that.autonomousVRefTimeConstant)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vRef, autonomousVRefEnable, autonomousVRefTimeConstant, customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("vRef", vRef)
        .add("autonomousVRefEnable", autonomousVRefEnable)
        .add("autonomousVRefTimeConstant", autonomousVRefTimeConstant)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
