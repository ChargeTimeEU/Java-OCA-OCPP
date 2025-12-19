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
import java.util.Arrays;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * Charging schedule period structure defines a time period in a charging schedule. It is used in:
 * CompositeScheduleType and in ChargingScheduleType. When used in a NotifyEVChargingScheduleRequest
 * only startPeriod, limit, limitL2, limitL3 are relevant.
 */
public final class ChargingSchedulePeriod {
  /**
   * Start of the period, in seconds from the start of schedule. The value of StartPeriod also
   * defines the stop time of the previous period.
   */
  private Integer startPeriod;

  /**
   * Optional only when not required by the operationMode, as in CentralSetpoint, ExternalSetpoint,
   * ExternalLimits, LocalFrequency, LocalLoadBalancing.
   *
   * <p>Charging rate limit during the schedule period, in the applicable chargingRateUnit. This
   * SHOULD be a non-negative value; a negative value is only supported for backwards compatibility
   * with older systems that use a negative value to specify a discharging limit. When using
   * chargingRateUnit = `W`, this field represents the sum of the power of all phases, unless values
   * are provided for L2 and L3, in which case this field represents phase L1.
   */
  @Nullable private Double limit;

  /** Charging rate limit on phase L2 in the applicable chargingRateUnit. */
  @Nullable private Double limit_L2;

  /** Charging rate limit on phase L3 in the applicable chargingRateUnit. */
  @Nullable private Double limit_L3;

  /**
   * The number of phases that can be used for charging.
   *
   * <pre>
   * For a DC EVSE this field should be omitted.
   * For an AC EVSE a default value of numberPhases = 3 will be assumed if the field is absent.
   * </pre>
   */
  @Nullable private Integer numberPhases;

  /**
   * Values: 1..3, Used if numberPhases=1 and if the EVSE is capable of switching the phase
   * connected to the EV, i.e. ACPhaseSwitchingSupported is defined and true. It’s not allowed
   * unless both conditions above are true. If both conditions are true, and phaseToUse is omitted,
   * the Charging Station / EVSE will make the selection on its own.
   */
  @Nullable private Integer phaseToUse;

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

  /** If true, the EV should attempt to keep the BMS preconditioned for this time interval. */
  @Nullable private Boolean preconditioningRequest;

  /**
   * Whether the EVSE must turn off power electronics/modules associated with this transaction.
   * Default value when absent is false.
   */
  @Nullable private Boolean evseSleep;

  /**
   * Power value that, when present, is used as a baseline on top of which values from
   * v2xFreqWattCurve and v2xSignalWattCurve are added.
   */
  @Nullable private Double v2xBaseline;

  /**
   * Charging operation mode to use during this time interval. When absent defaults to
   * `ChargingOnly`.
   */
  @Nullable private OperationModeEnum operationMode;

  /** A point of a frequency-watt curve. */
  @Nullable private V2XFreqWattPoint[] v2xFreqWattCurve;

  /** A point of a signal-watt curve. */
  @Nullable private V2XSignalWattPoint[] v2xSignalWattCurve;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the ChargingSchedulePeriod class
   *
   * @param startPeriod Start of the period, in seconds from the start of schedule. The value of
   *     StartPeriod also defines the stop time of the previous period.
   */
  public ChargingSchedulePeriod(Integer startPeriod) {
    setStartPeriod(startPeriod);
  }

  /**
   * Gets start of the period, in seconds from the start of schedule. The value of StartPeriod also
   * defines the stop time of the previous period.
   *
   * @return Start of the period, in seconds from the start of schedule
   */
  public Integer getStartPeriod() {
    return startPeriod;
  }

  /**
   * Sets start of the period, in seconds from the start of schedule. The value of StartPeriod also
   * defines the stop time of the previous period.
   *
   * @param startPeriod Start of the period, in seconds from the start of schedule
   */
  public void setStartPeriod(Integer startPeriod) {
    if (!isValidStartPeriod(startPeriod)) {
      throw new PropertyConstraintException(startPeriod, "startPeriod is invalid");
    }
    this.startPeriod = startPeriod;
  }

  /**
   * Returns whether the given startPeriod is valid
   *
   * @param startPeriod the startPeriod to check the validity of
   * @return {@code true} if startPeriod is valid, {@code false} if not
   */
  private boolean isValidStartPeriod(Integer startPeriod) {
    return startPeriod != null;
  }

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
  public ChargingSchedulePeriod withLimit(@Nullable Double limit) {
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
  public ChargingSchedulePeriod withLimit_L2(@Nullable Double limit_L2) {
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
  public ChargingSchedulePeriod withLimit_L3(@Nullable Double limit_L3) {
    setLimit_L3(limit_L3);
    return this;
  }

  /**
   * Gets the number of phases that can be used for charging.
   *
   * @return The number of phases that can be used for charging
   */
  @Nullable
  public Integer getNumberPhases() {
    return numberPhases;
  }

  /**
   * Sets the number of phases that can be used for charging.
   *
   * @param numberPhases The number of phases that can be used for charging
   */
  public void setNumberPhases(@Nullable Integer numberPhases) {
    if (!isValidNumberPhases(numberPhases)) {
      throw new PropertyConstraintException(numberPhases, "numberPhases is invalid");
    }
    this.numberPhases = numberPhases;
  }

  /**
   * Returns whether the given numberPhases is valid
   *
   * @param numberPhases the numberPhases to check the validity of
   * @return {@code true} if numberPhases is valid, {@code false} if not
   */
  private boolean isValidNumberPhases(@Nullable Integer numberPhases) {
    return numberPhases == null || (numberPhases >= 0 && numberPhases <= 3);
  }

  /**
   * Adds the number of phases that can be used for charging.
   *
   * @param numberPhases The number of phases that can be used for charging
   * @return this
   */
  public ChargingSchedulePeriod withNumberPhases(@Nullable Integer numberPhases) {
    setNumberPhases(numberPhases);
    return this;
  }

  /**
   * Gets values: 1..3, Used if numberPhases=1 and if the EVSE is capable of switching the phase
   * connected to the EV, i.e. ACPhaseSwitchingSupported is defined and true. It’s not allowed
   * unless both conditions above are true. If both conditions are true, and phaseToUse is omitted,
   * the Charging Station / EVSE will make the selection on its own.
   *
   * @return Values: 1..3, Used if numberPhases=1 and if the EVSE is capable of switching the phase
   *     connected to the EV, i.e. ACPhaseSwitchingSupported is defined and true
   */
  @Nullable
  public Integer getPhaseToUse() {
    return phaseToUse;
  }

  /**
   * Sets values: 1..3, Used if numberPhases=1 and if the EVSE is capable of switching the phase
   * connected to the EV, i.e. ACPhaseSwitchingSupported is defined and true. It’s not allowed
   * unless both conditions above are true. If both conditions are true, and phaseToUse is omitted,
   * the Charging Station / EVSE will make the selection on its own.
   *
   * @param phaseToUse Values: 1..3, Used if numberPhases=1 and if the EVSE is capable of switching
   *     the phase connected to the EV, i.e. ACPhaseSwitchingSupported is defined and true
   */
  public void setPhaseToUse(@Nullable Integer phaseToUse) {
    if (!isValidPhaseToUse(phaseToUse)) {
      throw new PropertyConstraintException(phaseToUse, "phaseToUse is invalid");
    }
    this.phaseToUse = phaseToUse;
  }

  /**
   * Returns whether the given phaseToUse is valid
   *
   * @param phaseToUse the phaseToUse to check the validity of
   * @return {@code true} if phaseToUse is valid, {@code false} if not
   */
  private boolean isValidPhaseToUse(@Nullable Integer phaseToUse) {
    return phaseToUse == null || (phaseToUse >= 0 && phaseToUse <= 3);
  }

  /**
   * Adds values: 1..3, Used if numberPhases=1 and if the EVSE is capable of switching the phase
   * connected to the EV, i.e. ACPhaseSwitchingSupported is defined and true. It’s not allowed
   * unless both conditions above are true. If both conditions are true, and phaseToUse is omitted,
   * the Charging Station / EVSE will make the selection on its own.
   *
   * @param phaseToUse Values: 1..3, Used if numberPhases=1 and if the EVSE is capable of switching
   *     the phase connected to the EV, i.e. ACPhaseSwitchingSupported is defined and true
   * @return this
   */
  public ChargingSchedulePeriod withPhaseToUse(@Nullable Integer phaseToUse) {
    setPhaseToUse(phaseToUse);
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
  public ChargingSchedulePeriod withDischargeLimit(@Nullable Double dischargeLimit) {
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
  public ChargingSchedulePeriod withDischargeLimit_L2(@Nullable Double dischargeLimit_L2) {
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
  public ChargingSchedulePeriod withDischargeLimit_L3(@Nullable Double dischargeLimit_L3) {
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
  public ChargingSchedulePeriod withSetpoint(@Nullable Double setpoint) {
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
  public ChargingSchedulePeriod withSetpoint_L2(@Nullable Double setpoint_L2) {
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
  public ChargingSchedulePeriod withSetpoint_L3(@Nullable Double setpoint_L3) {
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
  public ChargingSchedulePeriod withSetpointReactive(@Nullable Double setpointReactive) {
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
  public ChargingSchedulePeriod withSetpointReactive_L2(@Nullable Double setpointReactive_L2) {
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
  public ChargingSchedulePeriod withSetpointReactive_L3(@Nullable Double setpointReactive_L3) {
    setSetpointReactive_L3(setpointReactive_L3);
    return this;
  }

  /**
   * Gets if true, the EV should attempt to keep the BMS preconditioned for this time interval.
   *
   * @return If true, the EV should attempt to keep the BMS preconditioned for this time interval
   */
  @Nullable
  public Boolean getPreconditioningRequest() {
    return preconditioningRequest;
  }

  /**
   * Sets if true, the EV should attempt to keep the BMS preconditioned for this time interval.
   *
   * @param preconditioningRequest If true, the EV should attempt to keep the BMS preconditioned for
   *     this time interval
   */
  public void setPreconditioningRequest(@Nullable Boolean preconditioningRequest) {
    this.preconditioningRequest = preconditioningRequest;
  }

  /**
   * Adds if true, the EV should attempt to keep the BMS preconditioned for this time interval.
   *
   * @param preconditioningRequest If true, the EV should attempt to keep the BMS preconditioned for
   *     this time interval
   * @return this
   */
  public ChargingSchedulePeriod withPreconditioningRequest(
      @Nullable Boolean preconditioningRequest) {
    setPreconditioningRequest(preconditioningRequest);
    return this;
  }

  /**
   * Gets whether the EVSE must turn off power electronics/modules associated with this transaction.
   * Default value when absent is false.
   *
   * @return Whether the EVSE must turn off power electronics/modules associated with this
   *     transaction
   */
  @Nullable
  public Boolean getEvseSleep() {
    return evseSleep;
  }

  /**
   * Sets whether the EVSE must turn off power electronics/modules associated with this transaction.
   * Default value when absent is false.
   *
   * @param evseSleep Whether the EVSE must turn off power electronics/modules associated with this
   *     transaction
   */
  public void setEvseSleep(@Nullable Boolean evseSleep) {
    this.evseSleep = evseSleep;
  }

  /**
   * Adds whether the EVSE must turn off power electronics/modules associated with this transaction.
   * Default value when absent is false.
   *
   * @param evseSleep Whether the EVSE must turn off power electronics/modules associated with this
   *     transaction
   * @return this
   */
  public ChargingSchedulePeriod withEvseSleep(@Nullable Boolean evseSleep) {
    setEvseSleep(evseSleep);
    return this;
  }

  /**
   * Gets power value that, when present, is used as a baseline on top of which values from
   * v2xFreqWattCurve and v2xSignalWattCurve are added.
   *
   * @return Power value that, when present, is used as a baseline on top of which values from
   *     v2xFreqWattCurve and v2xSignalWattCurve are added
   */
  @Nullable
  public Double getV2xBaseline() {
    return v2xBaseline;
  }

  /**
   * Sets power value that, when present, is used as a baseline on top of which values from
   * v2xFreqWattCurve and v2xSignalWattCurve are added.
   *
   * @param v2xBaseline Power value that, when present, is used as a baseline on top of which values
   *     from v2xFreqWattCurve and v2xSignalWattCurve are added
   */
  public void setV2xBaseline(@Nullable Double v2xBaseline) {
    this.v2xBaseline = v2xBaseline;
  }

  /**
   * Adds power value that, when present, is used as a baseline on top of which values from
   * v2xFreqWattCurve and v2xSignalWattCurve are added.
   *
   * @param v2xBaseline Power value that, when present, is used as a baseline on top of which values
   *     from v2xFreqWattCurve and v2xSignalWattCurve are added
   * @return this
   */
  public ChargingSchedulePeriod withV2xBaseline(@Nullable Double v2xBaseline) {
    setV2xBaseline(v2xBaseline);
    return this;
  }

  /**
   * Gets charging operation mode to use during this time interval. When absent defaults to
   * `ChargingOnly`.
   *
   * @return Charging operation mode to use during this time interval
   */
  @Nullable
  public OperationModeEnum getOperationMode() {
    return operationMode;
  }

  /**
   * Sets charging operation mode to use during this time interval. When absent defaults to
   * `ChargingOnly`.
   *
   * @param operationMode Charging operation mode to use during this time interval
   */
  public void setOperationMode(@Nullable OperationModeEnum operationMode) {
    this.operationMode = operationMode;
  }

  /**
   * Adds charging operation mode to use during this time interval. When absent defaults to
   * `ChargingOnly`.
   *
   * @param operationMode Charging operation mode to use during this time interval
   * @return this
   */
  public ChargingSchedulePeriod withOperationMode(@Nullable OperationModeEnum operationMode) {
    setOperationMode(operationMode);
    return this;
  }

  /**
   * Gets a point of a frequency-watt curve.
   *
   * @return A point of a frequency-watt curve
   */
  @Nullable
  public V2XFreqWattPoint[] getV2xFreqWattCurve() {
    return v2xFreqWattCurve;
  }

  /**
   * Sets a point of a frequency-watt curve.
   *
   * @param v2xFreqWattCurve A point of a frequency-watt curve
   */
  public void setV2xFreqWattCurve(@Nullable V2XFreqWattPoint[] v2xFreqWattCurve) {
    if (!isValidV2xFreqWattCurve(v2xFreqWattCurve)) {
      throw new PropertyConstraintException(v2xFreqWattCurve, "v2xFreqWattCurve is invalid");
    }
    this.v2xFreqWattCurve = v2xFreqWattCurve;
  }

  /**
   * Returns whether the given v2xFreqWattCurve is valid
   *
   * @param v2xFreqWattCurve the v2xFreqWattCurve to check the validity of
   * @return {@code true} if v2xFreqWattCurve is valid, {@code false} if not
   */
  private boolean isValidV2xFreqWattCurve(@Nullable V2XFreqWattPoint[] v2xFreqWattCurve) {
    return v2xFreqWattCurve == null
        || (v2xFreqWattCurve.length >= 1
            && v2xFreqWattCurve.length <= 20
            && Arrays.stream(v2xFreqWattCurve).allMatch(item -> item.validate()));
  }

  /**
   * Adds a point of a frequency-watt curve.
   *
   * @param v2xFreqWattCurve A point of a frequency-watt curve
   * @return this
   */
  public ChargingSchedulePeriod withV2xFreqWattCurve(
      @Nullable V2XFreqWattPoint[] v2xFreqWattCurve) {
    setV2xFreqWattCurve(v2xFreqWattCurve);
    return this;
  }

  /**
   * Gets a point of a signal-watt curve.
   *
   * @return A point of a signal-watt curve
   */
  @Nullable
  public V2XSignalWattPoint[] getV2xSignalWattCurve() {
    return v2xSignalWattCurve;
  }

  /**
   * Sets a point of a signal-watt curve.
   *
   * @param v2xSignalWattCurve A point of a signal-watt curve
   */
  public void setV2xSignalWattCurve(@Nullable V2XSignalWattPoint[] v2xSignalWattCurve) {
    if (!isValidV2xSignalWattCurve(v2xSignalWattCurve)) {
      throw new PropertyConstraintException(v2xSignalWattCurve, "v2xSignalWattCurve is invalid");
    }
    this.v2xSignalWattCurve = v2xSignalWattCurve;
  }

  /**
   * Returns whether the given v2xSignalWattCurve is valid
   *
   * @param v2xSignalWattCurve the v2xSignalWattCurve to check the validity of
   * @return {@code true} if v2xSignalWattCurve is valid, {@code false} if not
   */
  private boolean isValidV2xSignalWattCurve(@Nullable V2XSignalWattPoint[] v2xSignalWattCurve) {
    return v2xSignalWattCurve == null
        || (v2xSignalWattCurve.length >= 1
            && v2xSignalWattCurve.length <= 20
            && Arrays.stream(v2xSignalWattCurve).allMatch(item -> item.validate()));
  }

  /**
   * Adds a point of a signal-watt curve.
   *
   * @param v2xSignalWattCurve A point of a signal-watt curve
   * @return this
   */
  public ChargingSchedulePeriod withV2xSignalWattCurve(
      @Nullable V2XSignalWattPoint[] v2xSignalWattCurve) {
    setV2xSignalWattCurve(v2xSignalWattCurve);
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
  public ChargingSchedulePeriod withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  public boolean validate() {
    return isValidStartPeriod(startPeriod)
        && isValidNumberPhases(numberPhases)
        && isValidPhaseToUse(phaseToUse)
        && isValidDischargeLimit(dischargeLimit)
        && isValidDischargeLimit_L2(dischargeLimit_L2)
        && isValidDischargeLimit_L3(dischargeLimit_L3)
        && isValidV2xFreqWattCurve(v2xFreqWattCurve)
        && isValidV2xSignalWattCurve(v2xSignalWattCurve)
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
    ChargingSchedulePeriod that = (ChargingSchedulePeriod) o;
    return Objects.equals(startPeriod, that.startPeriod)
        && Objects.equals(limit, that.limit)
        && Objects.equals(limit_L2, that.limit_L2)
        && Objects.equals(limit_L3, that.limit_L3)
        && Objects.equals(numberPhases, that.numberPhases)
        && Objects.equals(phaseToUse, that.phaseToUse)
        && Objects.equals(dischargeLimit, that.dischargeLimit)
        && Objects.equals(dischargeLimit_L2, that.dischargeLimit_L2)
        && Objects.equals(dischargeLimit_L3, that.dischargeLimit_L3)
        && Objects.equals(setpoint, that.setpoint)
        && Objects.equals(setpoint_L2, that.setpoint_L2)
        && Objects.equals(setpoint_L3, that.setpoint_L3)
        && Objects.equals(setpointReactive, that.setpointReactive)
        && Objects.equals(setpointReactive_L2, that.setpointReactive_L2)
        && Objects.equals(setpointReactive_L3, that.setpointReactive_L3)
        && Objects.equals(preconditioningRequest, that.preconditioningRequest)
        && Objects.equals(evseSleep, that.evseSleep)
        && Objects.equals(v2xBaseline, that.v2xBaseline)
        && Objects.equals(operationMode, that.operationMode)
        && Arrays.equals(v2xFreqWattCurve, that.v2xFreqWattCurve)
        && Arrays.equals(v2xSignalWattCurve, that.v2xSignalWattCurve)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        startPeriod,
        limit,
        limit_L2,
        limit_L3,
        numberPhases,
        phaseToUse,
        dischargeLimit,
        dischargeLimit_L2,
        dischargeLimit_L3,
        setpoint,
        setpoint_L2,
        setpoint_L3,
        setpointReactive,
        setpointReactive_L2,
        setpointReactive_L3,
        preconditioningRequest,
        evseSleep,
        v2xBaseline,
        operationMode,
        Arrays.hashCode(v2xFreqWattCurve),
        Arrays.hashCode(v2xSignalWattCurve),
        customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("startPeriod", startPeriod)
        .add("limit", limit)
        .add("limit_L2", limit_L2)
        .add("limit_L3", limit_L3)
        .add("numberPhases", numberPhases)
        .add("phaseToUse", phaseToUse)
        .add("dischargeLimit", dischargeLimit)
        .add("dischargeLimit_L2", dischargeLimit_L2)
        .add("dischargeLimit_L3", dischargeLimit_L3)
        .add("setpoint", setpoint)
        .add("setpoint_L2", setpoint_L2)
        .add("setpoint_L3", setpoint_L3)
        .add("setpointReactive", setpointReactive)
        .add("setpointReactive_L2", setpointReactive_L2)
        .add("setpointReactive_L3", setpointReactive_L3)
        .add("preconditioningRequest", preconditioningRequest)
        .add("evseSleep", evseSleep)
        .add("v2xBaseline", v2xBaseline)
        .add("operationMode", operationMode)
        .add("v2xFreqWattCurve", v2xFreqWattCurve)
        .add("v2xSignalWattCurve", v2xSignalWattCurve)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
