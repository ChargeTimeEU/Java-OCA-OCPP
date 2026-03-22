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

/** Updates to a ChargingSchedulePeriodType for dynamic charging profiles. */
public final class ChargingScheduleUpdate {
  /**
   * Optional only when not required by the operationMode, as in CentralSetpoint, ExternalSetpoint,
   * ExternalLimits, LocalFrequency, LocalLoadBalancing.
   *
   * <p>Charging rate limit during the schedule period, in the applicable chargingRateUnit. This
   * SHOULD be a non-negative value; a negative value is only supported for backwards compatibility
   * with older systems that use a negative value to specify a discharging limit. For AC this field
   * represents the sum of all phases, unless values are provided for L2 and L3, in which case this
   * field represents phase L1.
   */
  @Nullable private Double limit;

  /** Charging rate limit on phase L2 in the applicable chargingRateUnit. */
  @Nullable private Double limit_L2;

  /** Charging rate limit on phase L3 in the applicable chargingRateUnit. */
  @Nullable private Double limit_L3;

  /**
   * Limit in chargingRateUnit that the EV is allowed to discharge with. Note, these are negative
   * values in order to be consistent with setpoint, which can be positive and negative.
   *
   * <p>For AC this field represents the sum of all phases, unless values are provided for L2 and
   * L3, in which case this field represents phase L1.
   */
  @Nullable private Double dischargeLimit;

  /** Limit in chargingRateUnit on phase L2 that the EV is allowed to discharge with. */
  @Nullable private Double dischargeLimit_L2;

  /** Limit in chargingRateUnit on phase L3 that the EV is allowed to discharge with. */
  @Nullable private Double dischargeLimit_L3;

  /**
   * Setpoint in chargingRateUnit that the EV should follow as close as possible. Use negative
   * values for discharging.
   *
   * <p>When a limit and/or dischargeLimit are given the overshoot when following setpoint must
   * remain within these values. This field represents the sum of all phases, unless values are
   * provided for L2 and L3, in which case this field represents phase L1.
   */
  @Nullable private Double setpoint;

  /** Setpoint in chargingRateUnit that the EV should follow on phase L2 as close as possible. */
  @Nullable private Double setpoint_L2;

  /** Setpoint in chargingRateUnit that the EV should follow on phase L3 as close as possible. */
  @Nullable private Double setpoint_L3;

  /**
   * Setpoint for reactive power (or current) in chargingRateUnit that the EV should follow as
   * closely as possible. Positive values for inductive, negative for capacitive reactive power or
   * current.
   *
   * <p>This field represents the sum of all phases, unless values are provided for L2 and L3, in
   * which case this field represents phase L1.
   */
  @Nullable private Double setpointReactive;

  /**
   * Setpoint for reactive power (or current) in chargingRateUnit that the EV should follow on phase
   * L2 as closely as possible.
   */
  @Nullable private Double setpointReactive_L2;

  /**
   * Setpoint for reactive power (or current) in chargingRateUnit that the EV should follow on phase
   * L3 as closely as possible.
   */
  @Nullable private Double setpointReactive_L3;

  /** Custom data */
  @Nullable private CustomData customData;

  /** Constructor for the ChargingScheduleUpdate class */
  public ChargingScheduleUpdate() {}

  /**
   * Gets optional only when not required by the operationMode, as in CentralSetpoint,
   * ExternalSetpoint, ExternalLimits, LocalFrequency, LocalLoadBalancing.
   *
   * @return Optional only when not required by the operationMode, as in CentralSetpoint,
   *     ExternalSetpoint, ExternalLimits, LocalFrequency, LocalLoadBalancing
   */
  @Nullable
  public Double getLimit() {
    return limit;
  }

  /**
   * Sets optional only when not required by the operationMode, as in CentralSetpoint,
   * ExternalSetpoint, ExternalLimits, LocalFrequency, LocalLoadBalancing.
   *
   * @param limit Optional only when not required by the operationMode, as in CentralSetpoint,
   *     ExternalSetpoint, ExternalLimits, LocalFrequency, LocalLoadBalancing
   */
  public void setLimit(@Nullable Double limit) {
    this.limit = limit;
  }

  /**
   * Adds optional only when not required by the operationMode, as in CentralSetpoint,
   * ExternalSetpoint, ExternalLimits, LocalFrequency, LocalLoadBalancing.
   *
   * @param limit Optional only when not required by the operationMode, as in CentralSetpoint,
   *     ExternalSetpoint, ExternalLimits, LocalFrequency, LocalLoadBalancing
   * @return this
   */
  public ChargingScheduleUpdate withLimit(@Nullable Double limit) {
    setLimit(limit);
    return this;
  }

  /**
   * Gets charging rate limit on phase L2 in the applicable chargingRateUnit.
   *
   * @return Charging rate limit on phase L2 in the applicable chargingRateUnit
   */
  @Nullable
  public Double getLimit_L2() {
    return limit_L2;
  }

  /**
   * Sets charging rate limit on phase L2 in the applicable chargingRateUnit.
   *
   * @param limit_L2 Charging rate limit on phase L2 in the applicable chargingRateUnit
   */
  public void setLimit_L2(@Nullable Double limit_L2) {
    this.limit_L2 = limit_L2;
  }

  /**
   * Adds charging rate limit on phase L2 in the applicable chargingRateUnit.
   *
   * @param limit_L2 Charging rate limit on phase L2 in the applicable chargingRateUnit
   * @return this
   */
  public ChargingScheduleUpdate withLimit_L2(@Nullable Double limit_L2) {
    setLimit_L2(limit_L2);
    return this;
  }

  /**
   * Gets charging rate limit on phase L3 in the applicable chargingRateUnit.
   *
   * @return Charging rate limit on phase L3 in the applicable chargingRateUnit
   */
  @Nullable
  public Double getLimit_L3() {
    return limit_L3;
  }

  /**
   * Sets charging rate limit on phase L3 in the applicable chargingRateUnit.
   *
   * @param limit_L3 Charging rate limit on phase L3 in the applicable chargingRateUnit
   */
  public void setLimit_L3(@Nullable Double limit_L3) {
    this.limit_L3 = limit_L3;
  }

  /**
   * Adds charging rate limit on phase L3 in the applicable chargingRateUnit.
   *
   * @param limit_L3 Charging rate limit on phase L3 in the applicable chargingRateUnit
   * @return this
   */
  public ChargingScheduleUpdate withLimit_L3(@Nullable Double limit_L3) {
    setLimit_L3(limit_L3);
    return this;
  }

  /**
   * Gets limit in chargingRateUnit that the EV is allowed to discharge with. Note, these are
   * negative values in order to be consistent with setpoint, which can be positive and negative.
   *
   * @return Limit in chargingRateUnit that the EV is allowed to discharge with
   */
  @Nullable
  public Double getDischargeLimit() {
    return dischargeLimit;
  }

  /**
   * Sets limit in chargingRateUnit that the EV is allowed to discharge with. Note, these are
   * negative values in order to be consistent with setpoint, which can be positive and negative.
   *
   * @param dischargeLimit Limit in chargingRateUnit that the EV is allowed to discharge with
   */
  public void setDischargeLimit(@Nullable Double dischargeLimit) {
    if (!isValidDischargeLimit(dischargeLimit)) {
      throw new PropertyConstraintException(dischargeLimit, "dischargeLimit is invalid");
    }
    this.dischargeLimit = dischargeLimit;
  }

  /**
   * Returns whether the given dischargeLimit is valid
   *
   * @param dischargeLimit the dischargeLimit to check the validity of
   * @return {@code true} if dischargeLimit is valid, {@code false} if not
   */
  private boolean isValidDischargeLimit(@Nullable Double dischargeLimit) {
    return dischargeLimit == null || (dischargeLimit <= 0.0d);
  }

  /**
   * Adds limit in chargingRateUnit that the EV is allowed to discharge with. Note, these are
   * negative values in order to be consistent with setpoint, which can be positive and negative.
   *
   * @param dischargeLimit Limit in chargingRateUnit that the EV is allowed to discharge with
   * @return this
   */
  public ChargingScheduleUpdate withDischargeLimit(@Nullable Double dischargeLimit) {
    setDischargeLimit(dischargeLimit);
    return this;
  }

  /**
   * Gets limit in chargingRateUnit on phase L2 that the EV is allowed to discharge with.
   *
   * @return Limit in chargingRateUnit on phase L2 that the EV is allowed to discharge with
   */
  @Nullable
  public Double getDischargeLimit_L2() {
    return dischargeLimit_L2;
  }

  /**
   * Sets limit in chargingRateUnit on phase L2 that the EV is allowed to discharge with.
   *
   * @param dischargeLimit_L2 Limit in chargingRateUnit on phase L2 that the EV is allowed to
   *     discharge with
   */
  public void setDischargeLimit_L2(@Nullable Double dischargeLimit_L2) {
    if (!isValidDischargeLimit_L2(dischargeLimit_L2)) {
      throw new PropertyConstraintException(dischargeLimit_L2, "dischargeLimit_L2 is invalid");
    }
    this.dischargeLimit_L2 = dischargeLimit_L2;
  }

  /**
   * Returns whether the given dischargeLimit_L2 is valid
   *
   * @param dischargeLimit_L2 the dischargeLimit_L2 to check the validity of
   * @return {@code true} if dischargeLimit_L2 is valid, {@code false} if not
   */
  private boolean isValidDischargeLimit_L2(@Nullable Double dischargeLimit_L2) {
    return dischargeLimit_L2 == null || (dischargeLimit_L2 <= 0.0d);
  }

  /**
   * Adds limit in chargingRateUnit on phase L2 that the EV is allowed to discharge with.
   *
   * @param dischargeLimit_L2 Limit in chargingRateUnit on phase L2 that the EV is allowed to
   *     discharge with
   * @return this
   */
  public ChargingScheduleUpdate withDischargeLimit_L2(@Nullable Double dischargeLimit_L2) {
    setDischargeLimit_L2(dischargeLimit_L2);
    return this;
  }

  /**
   * Gets limit in chargingRateUnit on phase L3 that the EV is allowed to discharge with.
   *
   * @return Limit in chargingRateUnit on phase L3 that the EV is allowed to discharge with
   */
  @Nullable
  public Double getDischargeLimit_L3() {
    return dischargeLimit_L3;
  }

  /**
   * Sets limit in chargingRateUnit on phase L3 that the EV is allowed to discharge with.
   *
   * @param dischargeLimit_L3 Limit in chargingRateUnit on phase L3 that the EV is allowed to
   *     discharge with
   */
  public void setDischargeLimit_L3(@Nullable Double dischargeLimit_L3) {
    if (!isValidDischargeLimit_L3(dischargeLimit_L3)) {
      throw new PropertyConstraintException(dischargeLimit_L3, "dischargeLimit_L3 is invalid");
    }
    this.dischargeLimit_L3 = dischargeLimit_L3;
  }

  /**
   * Returns whether the given dischargeLimit_L3 is valid
   *
   * @param dischargeLimit_L3 the dischargeLimit_L3 to check the validity of
   * @return {@code true} if dischargeLimit_L3 is valid, {@code false} if not
   */
  private boolean isValidDischargeLimit_L3(@Nullable Double dischargeLimit_L3) {
    return dischargeLimit_L3 == null || (dischargeLimit_L3 <= 0.0d);
  }

  /**
   * Adds limit in chargingRateUnit on phase L3 that the EV is allowed to discharge with.
   *
   * @param dischargeLimit_L3 Limit in chargingRateUnit on phase L3 that the EV is allowed to
   *     discharge with
   * @return this
   */
  public ChargingScheduleUpdate withDischargeLimit_L3(@Nullable Double dischargeLimit_L3) {
    setDischargeLimit_L3(dischargeLimit_L3);
    return this;
  }

  /**
   * Gets setpoint in chargingRateUnit that the EV should follow as close as possible. Use negative
   * values for discharging.
   *
   * @return Setpoint in chargingRateUnit that the EV should follow as close as possible
   */
  @Nullable
  public Double getSetpoint() {
    return setpoint;
  }

  /**
   * Sets setpoint in chargingRateUnit that the EV should follow as close as possible. Use negative
   * values for discharging.
   *
   * @param setpoint Setpoint in chargingRateUnit that the EV should follow as close as possible
   */
  public void setSetpoint(@Nullable Double setpoint) {
    this.setpoint = setpoint;
  }

  /**
   * Adds setpoint in chargingRateUnit that the EV should follow as close as possible. Use negative
   * values for discharging.
   *
   * @param setpoint Setpoint in chargingRateUnit that the EV should follow as close as possible
   * @return this
   */
  public ChargingScheduleUpdate withSetpoint(@Nullable Double setpoint) {
    setSetpoint(setpoint);
    return this;
  }

  /**
   * Gets setpoint in chargingRateUnit that the EV should follow on phase L2 as close as possible.
   *
   * @return Setpoint in chargingRateUnit that the EV should follow on phase L2 as close as possible
   */
  @Nullable
  public Double getSetpoint_L2() {
    return setpoint_L2;
  }

  /**
   * Sets setpoint in chargingRateUnit that the EV should follow on phase L2 as close as possible.
   *
   * @param setpoint_L2 Setpoint in chargingRateUnit that the EV should follow on phase L2 as close
   *     as possible
   */
  public void setSetpoint_L2(@Nullable Double setpoint_L2) {
    this.setpoint_L2 = setpoint_L2;
  }

  /**
   * Adds setpoint in chargingRateUnit that the EV should follow on phase L2 as close as possible.
   *
   * @param setpoint_L2 Setpoint in chargingRateUnit that the EV should follow on phase L2 as close
   *     as possible
   * @return this
   */
  public ChargingScheduleUpdate withSetpoint_L2(@Nullable Double setpoint_L2) {
    setSetpoint_L2(setpoint_L2);
    return this;
  }

  /**
   * Gets setpoint in chargingRateUnit that the EV should follow on phase L3 as close as possible.
   *
   * @return Setpoint in chargingRateUnit that the EV should follow on phase L3 as close as possible
   */
  @Nullable
  public Double getSetpoint_L3() {
    return setpoint_L3;
  }

  /**
   * Sets setpoint in chargingRateUnit that the EV should follow on phase L3 as close as possible.
   *
   * @param setpoint_L3 Setpoint in chargingRateUnit that the EV should follow on phase L3 as close
   *     as possible
   */
  public void setSetpoint_L3(@Nullable Double setpoint_L3) {
    this.setpoint_L3 = setpoint_L3;
  }

  /**
   * Adds setpoint in chargingRateUnit that the EV should follow on phase L3 as close as possible.
   *
   * @param setpoint_L3 Setpoint in chargingRateUnit that the EV should follow on phase L3 as close
   *     as possible
   * @return this
   */
  public ChargingScheduleUpdate withSetpoint_L3(@Nullable Double setpoint_L3) {
    setSetpoint_L3(setpoint_L3);
    return this;
  }

  /**
   * Gets setpoint for reactive power (or current) in chargingRateUnit that the EV should follow as
   * closely as possible. Positive values for inductive, negative for capacitive reactive power or
   * current.
   *
   * @return Setpoint for reactive power (or current) in chargingRateUnit that the EV should follow
   *     as closely as possible
   */
  @Nullable
  public Double getSetpointReactive() {
    return setpointReactive;
  }

  /**
   * Sets setpoint for reactive power (or current) in chargingRateUnit that the EV should follow as
   * closely as possible. Positive values for inductive, negative for capacitive reactive power or
   * current.
   *
   * @param setpointReactive Setpoint for reactive power (or current) in chargingRateUnit that the
   *     EV should follow as closely as possible
   */
  public void setSetpointReactive(@Nullable Double setpointReactive) {
    this.setpointReactive = setpointReactive;
  }

  /**
   * Adds setpoint for reactive power (or current) in chargingRateUnit that the EV should follow as
   * closely as possible. Positive values for inductive, negative for capacitive reactive power or
   * current.
   *
   * @param setpointReactive Setpoint for reactive power (or current) in chargingRateUnit that the
   *     EV should follow as closely as possible
   * @return this
   */
  public ChargingScheduleUpdate withSetpointReactive(@Nullable Double setpointReactive) {
    setSetpointReactive(setpointReactive);
    return this;
  }

  /**
   * Gets setpoint for reactive power (or current) in chargingRateUnit that the EV should follow on
   * phase L2 as closely as possible.
   *
   * @return Setpoint for reactive power (or current) in chargingRateUnit that the EV should follow
   *     on phase L2 as closely as possible
   */
  @Nullable
  public Double getSetpointReactive_L2() {
    return setpointReactive_L2;
  }

  /**
   * Sets setpoint for reactive power (or current) in chargingRateUnit that the EV should follow on
   * phase L2 as closely as possible.
   *
   * @param setpointReactive_L2 Setpoint for reactive power (or current) in chargingRateUnit that
   *     the EV should follow on phase L2 as closely as possible
   */
  public void setSetpointReactive_L2(@Nullable Double setpointReactive_L2) {
    this.setpointReactive_L2 = setpointReactive_L2;
  }

  /**
   * Adds setpoint for reactive power (or current) in chargingRateUnit that the EV should follow on
   * phase L2 as closely as possible.
   *
   * @param setpointReactive_L2 Setpoint for reactive power (or current) in chargingRateUnit that
   *     the EV should follow on phase L2 as closely as possible
   * @return this
   */
  public ChargingScheduleUpdate withSetpointReactive_L2(@Nullable Double setpointReactive_L2) {
    setSetpointReactive_L2(setpointReactive_L2);
    return this;
  }

  /**
   * Gets setpoint for reactive power (or current) in chargingRateUnit that the EV should follow on
   * phase L3 as closely as possible.
   *
   * @return Setpoint for reactive power (or current) in chargingRateUnit that the EV should follow
   *     on phase L3 as closely as possible
   */
  @Nullable
  public Double getSetpointReactive_L3() {
    return setpointReactive_L3;
  }

  /**
   * Sets setpoint for reactive power (or current) in chargingRateUnit that the EV should follow on
   * phase L3 as closely as possible.
   *
   * @param setpointReactive_L3 Setpoint for reactive power (or current) in chargingRateUnit that
   *     the EV should follow on phase L3 as closely as possible
   */
  public void setSetpointReactive_L3(@Nullable Double setpointReactive_L3) {
    this.setpointReactive_L3 = setpointReactive_L3;
  }

  /**
   * Adds setpoint for reactive power (or current) in chargingRateUnit that the EV should follow on
   * phase L3 as closely as possible.
   *
   * @param setpointReactive_L3 Setpoint for reactive power (or current) in chargingRateUnit that
   *     the EV should follow on phase L3 as closely as possible
   * @return this
   */
  public ChargingScheduleUpdate withSetpointReactive_L3(@Nullable Double setpointReactive_L3) {
    setSetpointReactive_L3(setpointReactive_L3);
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
  public ChargingScheduleUpdate withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  public boolean validate() {
    return isValidDischargeLimit(dischargeLimit)
        && isValidDischargeLimit_L2(dischargeLimit_L2)
        && isValidDischargeLimit_L3(dischargeLimit_L3)
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
    ChargingScheduleUpdate that = (ChargingScheduleUpdate) o;
    return Objects.equals(limit, that.limit)
        && Objects.equals(limit_L2, that.limit_L2)
        && Objects.equals(limit_L3, that.limit_L3)
        && Objects.equals(dischargeLimit, that.dischargeLimit)
        && Objects.equals(dischargeLimit_L2, that.dischargeLimit_L2)
        && Objects.equals(dischargeLimit_L3, that.dischargeLimit_L3)
        && Objects.equals(setpoint, that.setpoint)
        && Objects.equals(setpoint_L2, that.setpoint_L2)
        && Objects.equals(setpoint_L3, that.setpoint_L3)
        && Objects.equals(setpointReactive, that.setpointReactive)
        && Objects.equals(setpointReactive_L2, that.setpointReactive_L2)
        && Objects.equals(setpointReactive_L3, that.setpointReactive_L3)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        limit,
        limit_L2,
        limit_L3,
        dischargeLimit,
        dischargeLimit_L2,
        dischargeLimit_L3,
        setpoint,
        setpoint_L2,
        setpoint_L3,
        setpointReactive,
        setpointReactive_L2,
        setpointReactive_L3,
        customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("limit", limit)
        .add("limit_L2", limit_L2)
        .add("limit_L3", limit_L3)
        .add("dischargeLimit", dischargeLimit)
        .add("dischargeLimit_L2", dischargeLimit_L2)
        .add("dischargeLimit_L3", dischargeLimit_L3)
        .add("setpoint", setpoint)
        .add("setpoint_L2", setpoint_L2)
        .add("setpoint_L3", setpoint_L3)
        .add("setpointReactive", setpointReactive)
        .add("setpointReactive_L2", setpointReactive_L2)
        .add("setpointReactive_L3", setpointReactive_L3)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
