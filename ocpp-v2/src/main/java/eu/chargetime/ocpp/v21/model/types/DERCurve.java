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
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Objects;
import javax.annotation.Nullable;

/** DERCurveType */
public final class DERCurve {
  /** curveData */
  private DERCurvePoints[] curveData;

  /** hysteresis */
  @Nullable private Hysteresis hysteresis;

  /** Priority of curve (0=highest) */
  private Integer priority;

  /** reactivePowerParams */
  @Nullable private ReactivePowerParams reactivePowerParams;

  /** voltageParams */
  @Nullable private VoltageParams voltageParams;

  /** Unit of the Y-axis of DER curve */
  private DERUnitEnum yUnit;

  /**
   * Open loop response time, the time to ramp up to 90% of the new target in response to the change
   * in voltage, in seconds. A value of 0 is used to mean no limit. When not present, the device
   * should follow its default behavior.
   */
  @Nullable private Double responseTime;

  /** Point in time when this curve will become activated. Only absent when default is true. */
  @Nullable private ZonedDateTime startTime;

  /** Duration in seconds that this curve will be active. Only absent when default is true. */
  @Nullable private Double duration;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the DERCurve class
   *
   * @param curveData curveData
   * @param priority Priority of curve (0=highest)
   * @param yUnit Unit of the Y-axis of DER curve
   */
  public DERCurve(DERCurvePoints[] curveData, Integer priority, DERUnitEnum yUnit) {
    setCurveData(curveData);
    setPriority(priority);
    setYUnit(yUnit);
  }

  /**
   * Gets curveData
   *
   * @return curveData
   */
  public DERCurvePoints[] getCurveData() {
    return curveData;
  }

  /**
   * Sets curveData
   *
   * @param curveData curveData
   */
  public void setCurveData(DERCurvePoints[] curveData) {
    if (!isValidCurveData(curveData)) {
      throw new PropertyConstraintException(curveData, "curveData is invalid");
    }
    this.curveData = curveData;
  }

  /**
   * Returns whether the given curveData is valid
   *
   * @param curveData the curveData to check the validity of
   * @return {@code true} if curveData is valid, {@code false} if not
   */
  private boolean isValidCurveData(DERCurvePoints[] curveData) {
    return curveData != null
        && curveData.length >= 1
        && curveData.length <= 10
        && Arrays.stream(curveData).allMatch(item -> item.validate());
  }

  /**
   * Gets hysteresis
   *
   * @return hysteresis
   */
  @Nullable
  public Hysteresis getHysteresis() {
    return hysteresis;
  }

  /**
   * Sets hysteresis
   *
   * @param hysteresis hysteresis
   */
  public void setHysteresis(@Nullable Hysteresis hysteresis) {
    if (!isValidHysteresis(hysteresis)) {
      throw new PropertyConstraintException(hysteresis, "hysteresis is invalid");
    }
    this.hysteresis = hysteresis;
  }

  /**
   * Returns whether the given hysteresis is valid
   *
   * @param hysteresis the hysteresis to check the validity of
   * @return {@code true} if hysteresis is valid, {@code false} if not
   */
  private boolean isValidHysteresis(@Nullable Hysteresis hysteresis) {
    return hysteresis == null || hysteresis.validate();
  }

  /**
   * Adds hysteresis
   *
   * @param hysteresis hysteresis
   * @return this
   */
  public DERCurve withHysteresis(@Nullable Hysteresis hysteresis) {
    setHysteresis(hysteresis);
    return this;
  }

  /**
   * Gets priority of curve (0=highest)
   *
   * @return Priority of curve (0=highest)
   */
  public Integer getPriority() {
    return priority;
  }

  /**
   * Sets priority of curve (0=highest)
   *
   * @param priority Priority of curve (0=highest)
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
   * Gets reactivePowerParams
   *
   * @return reactivePowerParams
   */
  @Nullable
  public ReactivePowerParams getReactivePowerParams() {
    return reactivePowerParams;
  }

  /**
   * Sets reactivePowerParams
   *
   * @param reactivePowerParams reactivePowerParams
   */
  public void setReactivePowerParams(@Nullable ReactivePowerParams reactivePowerParams) {
    if (!isValidReactivePowerParams(reactivePowerParams)) {
      throw new PropertyConstraintException(reactivePowerParams, "reactivePowerParams is invalid");
    }
    this.reactivePowerParams = reactivePowerParams;
  }

  /**
   * Returns whether the given reactivePowerParams is valid
   *
   * @param reactivePowerParams the reactivePowerParams to check the validity of
   * @return {@code true} if reactivePowerParams is valid, {@code false} if not
   */
  private boolean isValidReactivePowerParams(@Nullable ReactivePowerParams reactivePowerParams) {
    return reactivePowerParams == null || reactivePowerParams.validate();
  }

  /**
   * Adds reactivePowerParams
   *
   * @param reactivePowerParams reactivePowerParams
   * @return this
   */
  public DERCurve withReactivePowerParams(@Nullable ReactivePowerParams reactivePowerParams) {
    setReactivePowerParams(reactivePowerParams);
    return this;
  }

  /**
   * Gets voltageParams
   *
   * @return voltageParams
   */
  @Nullable
  public VoltageParams getVoltageParams() {
    return voltageParams;
  }

  /**
   * Sets voltageParams
   *
   * @param voltageParams voltageParams
   */
  public void setVoltageParams(@Nullable VoltageParams voltageParams) {
    if (!isValidVoltageParams(voltageParams)) {
      throw new PropertyConstraintException(voltageParams, "voltageParams is invalid");
    }
    this.voltageParams = voltageParams;
  }

  /**
   * Returns whether the given voltageParams is valid
   *
   * @param voltageParams the voltageParams to check the validity of
   * @return {@code true} if voltageParams is valid, {@code false} if not
   */
  private boolean isValidVoltageParams(@Nullable VoltageParams voltageParams) {
    return voltageParams == null || voltageParams.validate();
  }

  /**
   * Adds voltageParams
   *
   * @param voltageParams voltageParams
   * @return this
   */
  public DERCurve withVoltageParams(@Nullable VoltageParams voltageParams) {
    setVoltageParams(voltageParams);
    return this;
  }

  /**
   * Gets unit of the Y-axis of DER curve
   *
   * @return Unit of the Y-axis of DER curve
   */
  public DERUnitEnum getYUnit() {
    return yUnit;
  }

  /**
   * Sets unit of the Y-axis of DER curve
   *
   * @param yUnit Unit of the Y-axis of DER curve
   */
  public void setYUnit(DERUnitEnum yUnit) {
    if (!isValidYUnit(yUnit)) {
      throw new PropertyConstraintException(yUnit, "yUnit is invalid");
    }
    this.yUnit = yUnit;
  }

  /**
   * Returns whether the given yUnit is valid
   *
   * @param yUnit the yUnit to check the validity of
   * @return {@code true} if yUnit is valid, {@code false} if not
   */
  private boolean isValidYUnit(DERUnitEnum yUnit) {
    return yUnit != null;
  }

  /**
   * Gets open loop response time, the time to ramp up to 90% of the new target in response to the
   * change in voltage, in seconds. A value of 0 is used to mean no limit. When not present, the
   * device should follow its default behavior.
   *
   * @return Open loop response time, the time to ramp up to 90% of the new target in response to
   *     the change in voltage, in seconds
   */
  @Nullable
  public Double getResponseTime() {
    return responseTime;
  }

  /**
   * Sets open loop response time, the time to ramp up to 90% of the new target in response to the
   * change in voltage, in seconds. A value of 0 is used to mean no limit. When not present, the
   * device should follow its default behavior.
   *
   * @param responseTime Open loop response time, the time to ramp up to 90% of the new target in
   *     response to the change in voltage, in seconds
   */
  public void setResponseTime(@Nullable Double responseTime) {
    this.responseTime = responseTime;
  }

  /**
   * Adds open loop response time, the time to ramp up to 90% of the new target in response to the
   * change in voltage, in seconds. A value of 0 is used to mean no limit. When not present, the
   * device should follow its default behavior.
   *
   * @param responseTime Open loop response time, the time to ramp up to 90% of the new target in
   *     response to the change in voltage, in seconds
   * @return this
   */
  public DERCurve withResponseTime(@Nullable Double responseTime) {
    setResponseTime(responseTime);
    return this;
  }

  /**
   * Gets point in time when this curve will become activated. Only absent when default is true.
   *
   * @return Point in time when this curve will become activated
   */
  @Nullable
  public ZonedDateTime getStartTime() {
    return startTime;
  }

  /**
   * Sets point in time when this curve will become activated. Only absent when default is true.
   *
   * @param startTime Point in time when this curve will become activated
   */
  public void setStartTime(@Nullable ZonedDateTime startTime) {
    this.startTime = startTime;
  }

  /**
   * Adds point in time when this curve will become activated. Only absent when default is true.
   *
   * @param startTime Point in time when this curve will become activated
   * @return this
   */
  public DERCurve withStartTime(@Nullable ZonedDateTime startTime) {
    setStartTime(startTime);
    return this;
  }

  /**
   * Gets duration in seconds that this curve will be active. Only absent when default is true.
   *
   * @return Duration in seconds that this curve will be active
   */
  @Nullable
  public Double getDuration() {
    return duration;
  }

  /**
   * Sets duration in seconds that this curve will be active. Only absent when default is true.
   *
   * @param duration Duration in seconds that this curve will be active
   */
  public void setDuration(@Nullable Double duration) {
    this.duration = duration;
  }

  /**
   * Adds duration in seconds that this curve will be active. Only absent when default is true.
   *
   * @param duration Duration in seconds that this curve will be active
   * @return this
   */
  public DERCurve withDuration(@Nullable Double duration) {
    setDuration(duration);
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
  public DERCurve withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  public boolean validate() {
    return isValidCurveData(curveData)
        && isValidHysteresis(hysteresis)
        && isValidPriority(priority)
        && isValidReactivePowerParams(reactivePowerParams)
        && isValidVoltageParams(voltageParams)
        && isValidYUnit(yUnit)
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
    DERCurve that = (DERCurve) o;
    return Arrays.equals(curveData, that.curveData)
        && Objects.equals(hysteresis, that.hysteresis)
        && Objects.equals(priority, that.priority)
        && Objects.equals(reactivePowerParams, that.reactivePowerParams)
        && Objects.equals(voltageParams, that.voltageParams)
        && Objects.equals(yUnit, that.yUnit)
        && Objects.equals(responseTime, that.responseTime)
        && Objects.equals(startTime, that.startTime)
        && Objects.equals(duration, that.duration)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        Arrays.hashCode(curveData),
        hysteresis,
        priority,
        reactivePowerParams,
        voltageParams,
        yUnit,
        responseTime,
        startTime,
        duration,
        customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("curveData", curveData)
        .add("hysteresis", hysteresis)
        .add("priority", priority)
        .add("reactivePowerParams", reactivePowerParams)
        .add("voltageParams", voltageParams)
        .add("yUnit", yUnit)
        .add("responseTime", responseTime)
        .add("startTime", startTime)
        .add("duration", duration)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
