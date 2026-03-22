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

/**
 * Charging parameters for ISO 15118-20, also supporting V2X charging/discharging.+ All values are
 * greater or equal to zero, with the exception of EVMinEnergyRequest, EVMaxEnergyRequest,
 * EVTargetEnergyRequest, EVMinV2XEnergyRequest and EVMaxV2XEnergyRequest.
 */
public final class V2XChargingParameters {
  /**
   * Minimum charge power in W, defined by max(EV, EVSE). This field represents the sum of all
   * phases, unless values are provided for L2 and L3, in which case this field represents phase L1.
   * Relates to: *ISO 15118-20*: BPTAC/DCCPDReqEnergyTransferModeType: EVMinimumChargePower
   */
  @Nullable private Double minChargePower;

  /**
   * Minimum charge power on phase L2 in W, defined by max(EV, EVSE). Relates to: *ISO 15118-20*:
   * BPTAC/DCCPDReqEnergyTransferModeType: EVMinimumChargePowerL2
   */
  @Nullable private Double minChargePower_L2;

  /**
   * Minimum charge power on phase L3 in W, defined by max(EV, EVSE). Relates to: *ISO 15118-20*:
   * BPTAC/DCCPDReqEnergyTransferModeType: EVMinimumChargePowerL3
   */
  @Nullable private Double minChargePower_L3;

  /**
   * Maximum charge (absorbed) power in W, defined by min(EV, EVSE) at unity power factor.
   *
   * <p>This field represents the sum of all phases, unless values are provided for L2 and L3, in
   * which case this field represents phase L1. It corresponds to the ChaWMax attribute in the IEC
   * 61850.
   *
   * <pre>
   * It is usually equivalent to the rated apparent power of the EV when discharging (ChaVAMax) in
   * IEC 61850.
   *
   * </pre>
   *
   * Relates to: *ISO 15118-20*: BPTAC/DCCPDReqEnergyTransferModeType: EVMaximumChargePower
   */
  @Nullable private Double maxChargePower;

  /**
   * Maximum charge power on phase L2 in W, defined by min(EV, EVSE) Relates to: *ISO 15118-20*:
   * BPTAC/DCCPDReqEnergyTransferModeType: EVMaximumChargePowerL2
   */
  @Nullable private Double maxChargePower_L2;

  /**
   * Maximum charge power on phase L3 in W, defined by min(EV, EVSE) Relates to: *ISO 15118-20*:
   * BPTAC/DCCPDReqEnergyTransferModeType: EVMaximumChargePowerL3
   */
  @Nullable private Double maxChargePower_L3;

  /**
   * Minimum discharge (injected) power in W, defined by max(EV, EVSE) at unity power factor. Value
   * non-negative.
   *
   * <pre>
   * This field represents the sum of all phases, unless values are provided for L2 and L3, in which
   * case this field represents phase L1.
   * It corresponds to the WMax attribute in the IEC 61850.
   * </pre>
   *
   * It is usually equivalent to the rated apparent power of the EV when discharging (VAMax
   * attribute in the IEC 61850).
   *
   * <p>Relates to: *ISO 15118-20*: BPTAC/DCCPDReqEnergyTransferModeType: EVMinimumDischargePower
   */
  @Nullable private Double minDischargePower;

  /**
   * Minimum discharge power on phase L2 in W, defined by max(EV, EVSE). Value non-negative. Relates
   * to: *ISO 15118-20*: BPTAC/DCCPDReqEnergyTransferModeType: EVMinimumDischargePowerL2
   */
  @Nullable private Double minDischargePower_L2;

  /**
   * Minimum discharge power on phase L3 in W, defined by max(EV, EVSE). Value non-negative. Relates
   * to: *ISO 15118-20*: BPTAC/DCCPDReqEnergyTransferModeType: EVMinimumDischargePowerL3
   */
  @Nullable private Double minDischargePower_L3;

  /**
   * Maximum discharge (injected) power in W, defined by min(EV, EVSE) at unity power factor. Value
   * non-negative. This field represents the sum of all phases, unless values are provided for L2
   * and L3, in which case this field represents phase L1. Relates to: *ISO 15118-20*:
   * BPTAC/DCCPDReqEnergyTransferModeType: EVMaximumDischargePower
   */
  @Nullable private Double maxDischargePower;

  /**
   * Maximum discharge power on phase L2 in W, defined by min(EV, EVSE). Value non-negative. Relates
   * to: *ISO 15118-20*: BPTAC/DCCPDReqEnergyTransferModeType: EVMaximumDischargePoweL2
   */
  @Nullable private Double maxDischargePower_L2;

  /**
   * Maximum discharge power on phase L3 in W, defined by min(EV, EVSE). Value non-negative. Relates
   * to: *ISO 15118-20*: BPTAC/DCCPDReqEnergyTransferModeType: EVMaximumDischargePowerL3
   */
  @Nullable private Double maxDischargePower_L3;

  /**
   * Minimum charge current in A, defined by max(EV, EVSE) Relates to: *ISO 15118-20*:
   * BPTDCCPDReqEnergyTransferModeType: EVMinimumChargeCurrent
   */
  @Nullable private Double minChargeCurrent;

  /**
   * Maximum charge current in A, defined by min(EV, EVSE) Relates to: *ISO 15118-20*:
   * BPTDCCPDReqEnergyTransferModeType: EVMaximumChargeCurrent
   */
  @Nullable private Double maxChargeCurrent;

  /**
   * Minimum discharge current in A, defined by max(EV, EVSE). Value non-negative. Relates to: *ISO
   * 15118-20*: BPTDCCPDReqEnergyTransferModeType: EVMinimumDischargeCurrent
   */
  @Nullable private Double minDischargeCurrent;

  /**
   * Maximum discharge current in A, defined by min(EV, EVSE). Value non-negative. Relates to: *ISO
   * 15118-20*: BPTDCCPDReqEnergyTransferModeType: EVMaximumDischargeCurrent
   */
  @Nullable private Double maxDischargeCurrent;

  /**
   * Minimum voltage in V, defined by max(EV, EVSE) Relates to: *ISO 15118-20*:
   * BPTDCCPDReqEnergyTransferModeType: EVMinimumVoltage
   */
  @Nullable private Double minVoltage;

  /**
   * Maximum voltage in V, defined by min(EV, EVSE) Relates to: *ISO 15118-20*:
   * BPTDCCPDReqEnergyTransferModeType: EVMaximumVoltage
   */
  @Nullable private Double maxVoltage;

  /**
   * Energy to requested state of charge in Wh Relates to: *ISO 15118-20*:
   * Dynamic/ScheduledSEReqControlModeType: EVTargetEnergyRequest
   */
  @Nullable private Double evTargetEnergyRequest;

  /**
   * Energy to minimum allowed state of charge in Wh Relates to: *ISO 15118-20*:
   * Dynamic/ScheduledSEReqControlModeType: EVMinimumEnergyRequest
   */
  @Nullable private Double evMinEnergyRequest;

  /**
   * Energy to maximum state of charge in Wh Relates to: *ISO 15118-20*:
   * Dynamic/ScheduledSEReqControlModeType: EVMaximumEnergyRequest
   */
  @Nullable private Double evMaxEnergyRequest;

  /**
   * Energy (in Wh) to minimum state of charge for cycling (V2X) activity. Positive value means that
   * current state of charge is below V2X range. Relates to: *ISO 15118-20*:
   * DynamicSEReqControlModeType: EVMinimumV2XEnergyRequest
   */
  @Nullable private Double evMinV2XEnergyRequest;

  /**
   * Energy (in Wh) to maximum state of charge for cycling (V2X) activity. Negative value indicates
   * that current state of charge is above V2X range. Relates to: *ISO 15118-20*:
   * DynamicSEReqControlModeType: EVMaximumV2XEnergyRequest
   */
  @Nullable private Double evMaxV2XEnergyRequest;

  /**
   * Target state of charge at departure as percentage. Relates to: *ISO 15118-20*:
   * BPTDCCPDReqEnergyTransferModeType: TargetSOC
   */
  @Nullable private Integer targetSoC;

  /** Custom data */
  @Nullable private CustomData customData;

  /** Constructor for the V2XChargingParameters class */
  public V2XChargingParameters() {}

  /**
   * Gets minimum charge power in W, defined by max(EV, EVSE). This field represents the sum of all
   * phases, unless values are provided for L2 and L3, in which case this field represents phase L1.
   * Relates to: *ISO 15118-20*: BPTAC/DCCPDReqEnergyTransferModeType: EVMinimumChargePower
   *
   * @return Minimum charge power in W, defined by max(EV, EVSE)
   */
  @Nullable
  public Double getMinChargePower() {
    return minChargePower;
  }

  /**
   * Sets minimum charge power in W, defined by max(EV, EVSE). This field represents the sum of all
   * phases, unless values are provided for L2 and L3, in which case this field represents phase L1.
   * Relates to: *ISO 15118-20*: BPTAC/DCCPDReqEnergyTransferModeType: EVMinimumChargePower
   *
   * @param minChargePower Minimum charge power in W, defined by max(EV, EVSE)
   */
  public void setMinChargePower(@Nullable Double minChargePower) {
    this.minChargePower = minChargePower;
  }

  /**
   * Adds minimum charge power in W, defined by max(EV, EVSE). This field represents the sum of all
   * phases, unless values are provided for L2 and L3, in which case this field represents phase L1.
   * Relates to: *ISO 15118-20*: BPTAC/DCCPDReqEnergyTransferModeType: EVMinimumChargePower
   *
   * @param minChargePower Minimum charge power in W, defined by max(EV, EVSE)
   * @return this
   */
  public V2XChargingParameters withMinChargePower(@Nullable Double minChargePower) {
    setMinChargePower(minChargePower);
    return this;
  }

  /**
   * Gets minimum charge power on phase L2 in W, defined by max(EV, EVSE). Relates to: *ISO
   * 15118-20*: BPTAC/DCCPDReqEnergyTransferModeType: EVMinimumChargePowerL2
   *
   * @return Minimum charge power on phase L2 in W, defined by max(EV, EVSE)
   */
  @Nullable
  public Double getMinChargePower_L2() {
    return minChargePower_L2;
  }

  /**
   * Sets minimum charge power on phase L2 in W, defined by max(EV, EVSE). Relates to: *ISO
   * 15118-20*: BPTAC/DCCPDReqEnergyTransferModeType: EVMinimumChargePowerL2
   *
   * @param minChargePower_L2 Minimum charge power on phase L2 in W, defined by max(EV, EVSE)
   */
  public void setMinChargePower_L2(@Nullable Double minChargePower_L2) {
    this.minChargePower_L2 = minChargePower_L2;
  }

  /**
   * Adds minimum charge power on phase L2 in W, defined by max(EV, EVSE). Relates to: *ISO
   * 15118-20*: BPTAC/DCCPDReqEnergyTransferModeType: EVMinimumChargePowerL2
   *
   * @param minChargePower_L2 Minimum charge power on phase L2 in W, defined by max(EV, EVSE)
   * @return this
   */
  public V2XChargingParameters withMinChargePower_L2(@Nullable Double minChargePower_L2) {
    setMinChargePower_L2(minChargePower_L2);
    return this;
  }

  /**
   * Gets minimum charge power on phase L3 in W, defined by max(EV, EVSE). Relates to: *ISO
   * 15118-20*: BPTAC/DCCPDReqEnergyTransferModeType: EVMinimumChargePowerL3
   *
   * @return Minimum charge power on phase L3 in W, defined by max(EV, EVSE)
   */
  @Nullable
  public Double getMinChargePower_L3() {
    return minChargePower_L3;
  }

  /**
   * Sets minimum charge power on phase L3 in W, defined by max(EV, EVSE). Relates to: *ISO
   * 15118-20*: BPTAC/DCCPDReqEnergyTransferModeType: EVMinimumChargePowerL3
   *
   * @param minChargePower_L3 Minimum charge power on phase L3 in W, defined by max(EV, EVSE)
   */
  public void setMinChargePower_L3(@Nullable Double minChargePower_L3) {
    this.minChargePower_L3 = minChargePower_L3;
  }

  /**
   * Adds minimum charge power on phase L3 in W, defined by max(EV, EVSE). Relates to: *ISO
   * 15118-20*: BPTAC/DCCPDReqEnergyTransferModeType: EVMinimumChargePowerL3
   *
   * @param minChargePower_L3 Minimum charge power on phase L3 in W, defined by max(EV, EVSE)
   * @return this
   */
  public V2XChargingParameters withMinChargePower_L3(@Nullable Double minChargePower_L3) {
    setMinChargePower_L3(minChargePower_L3);
    return this;
  }

  /**
   * Gets maximum charge (absorbed) power in W, defined by min(EV, EVSE) at unity power factor.
   *
   * @return Maximum charge (absorbed) power in W, defined by min(EV, EVSE) at unity power factor
   */
  @Nullable
  public Double getMaxChargePower() {
    return maxChargePower;
  }

  /**
   * Sets maximum charge (absorbed) power in W, defined by min(EV, EVSE) at unity power factor.
   *
   * @param maxChargePower Maximum charge (absorbed) power in W, defined by min(EV, EVSE) at unity
   *     power factor
   */
  public void setMaxChargePower(@Nullable Double maxChargePower) {
    this.maxChargePower = maxChargePower;
  }

  /**
   * Adds maximum charge (absorbed) power in W, defined by min(EV, EVSE) at unity power factor.
   *
   * @param maxChargePower Maximum charge (absorbed) power in W, defined by min(EV, EVSE) at unity
   *     power factor
   * @return this
   */
  public V2XChargingParameters withMaxChargePower(@Nullable Double maxChargePower) {
    setMaxChargePower(maxChargePower);
    return this;
  }

  /**
   * Gets maximum charge power on phase L2 in W, defined by min(EV, EVSE) Relates to: *ISO
   * 15118-20*: BPTAC/DCCPDReqEnergyTransferModeType: EVMaximumChargePowerL2
   *
   * @return Maximum charge power on phase L2 in W, defined by min(EV, EVSE) Relates to: *ISO
   *     15118-20*: BPTAC/DCCPDReqEnergyTransferModeType: EVMaximumChargePowerL2
   */
  @Nullable
  public Double getMaxChargePower_L2() {
    return maxChargePower_L2;
  }

  /**
   * Sets maximum charge power on phase L2 in W, defined by min(EV, EVSE) Relates to: *ISO
   * 15118-20*: BPTAC/DCCPDReqEnergyTransferModeType: EVMaximumChargePowerL2
   *
   * @param maxChargePower_L2 Maximum charge power on phase L2 in W, defined by min(EV, EVSE)
   *     Relates to: *ISO 15118-20*: BPTAC/DCCPDReqEnergyTransferModeType: EVMaximumChargePowerL2
   */
  public void setMaxChargePower_L2(@Nullable Double maxChargePower_L2) {
    this.maxChargePower_L2 = maxChargePower_L2;
  }

  /**
   * Adds maximum charge power on phase L2 in W, defined by min(EV, EVSE) Relates to: *ISO
   * 15118-20*: BPTAC/DCCPDReqEnergyTransferModeType: EVMaximumChargePowerL2
   *
   * @param maxChargePower_L2 Maximum charge power on phase L2 in W, defined by min(EV, EVSE)
   *     Relates to: *ISO 15118-20*: BPTAC/DCCPDReqEnergyTransferModeType: EVMaximumChargePowerL2
   * @return this
   */
  public V2XChargingParameters withMaxChargePower_L2(@Nullable Double maxChargePower_L2) {
    setMaxChargePower_L2(maxChargePower_L2);
    return this;
  }

  /**
   * Gets maximum charge power on phase L3 in W, defined by min(EV, EVSE) Relates to: *ISO
   * 15118-20*: BPTAC/DCCPDReqEnergyTransferModeType: EVMaximumChargePowerL3
   *
   * @return Maximum charge power on phase L3 in W, defined by min(EV, EVSE) Relates to: *ISO
   *     15118-20*: BPTAC/DCCPDReqEnergyTransferModeType: EVMaximumChargePowerL3
   */
  @Nullable
  public Double getMaxChargePower_L3() {
    return maxChargePower_L3;
  }

  /**
   * Sets maximum charge power on phase L3 in W, defined by min(EV, EVSE) Relates to: *ISO
   * 15118-20*: BPTAC/DCCPDReqEnergyTransferModeType: EVMaximumChargePowerL3
   *
   * @param maxChargePower_L3 Maximum charge power on phase L3 in W, defined by min(EV, EVSE)
   *     Relates to: *ISO 15118-20*: BPTAC/DCCPDReqEnergyTransferModeType: EVMaximumChargePowerL3
   */
  public void setMaxChargePower_L3(@Nullable Double maxChargePower_L3) {
    this.maxChargePower_L3 = maxChargePower_L3;
  }

  /**
   * Adds maximum charge power on phase L3 in W, defined by min(EV, EVSE) Relates to: *ISO
   * 15118-20*: BPTAC/DCCPDReqEnergyTransferModeType: EVMaximumChargePowerL3
   *
   * @param maxChargePower_L3 Maximum charge power on phase L3 in W, defined by min(EV, EVSE)
   *     Relates to: *ISO 15118-20*: BPTAC/DCCPDReqEnergyTransferModeType: EVMaximumChargePowerL3
   * @return this
   */
  public V2XChargingParameters withMaxChargePower_L3(@Nullable Double maxChargePower_L3) {
    setMaxChargePower_L3(maxChargePower_L3);
    return this;
  }

  /**
   * Gets minimum discharge (injected) power in W, defined by max(EV, EVSE) at unity power factor.
   * Value non-negative.
   *
   * @return Minimum discharge (injected) power in W, defined by max(EV, EVSE) at unity power factor
   */
  @Nullable
  public Double getMinDischargePower() {
    return minDischargePower;
  }

  /**
   * Sets minimum discharge (injected) power in W, defined by max(EV, EVSE) at unity power factor.
   * Value non-negative.
   *
   * @param minDischargePower Minimum discharge (injected) power in W, defined by max(EV, EVSE) at
   *     unity power factor
   */
  public void setMinDischargePower(@Nullable Double minDischargePower) {
    this.minDischargePower = minDischargePower;
  }

  /**
   * Adds minimum discharge (injected) power in W, defined by max(EV, EVSE) at unity power factor.
   * Value non-negative.
   *
   * @param minDischargePower Minimum discharge (injected) power in W, defined by max(EV, EVSE) at
   *     unity power factor
   * @return this
   */
  public V2XChargingParameters withMinDischargePower(@Nullable Double minDischargePower) {
    setMinDischargePower(minDischargePower);
    return this;
  }

  /**
   * Gets minimum discharge power on phase L2 in W, defined by max(EV, EVSE). Value non-negative.
   * Relates to: *ISO 15118-20*: BPTAC/DCCPDReqEnergyTransferModeType: EVMinimumDischargePowerL2
   *
   * @return Minimum discharge power on phase L2 in W, defined by max(EV, EVSE)
   */
  @Nullable
  public Double getMinDischargePower_L2() {
    return minDischargePower_L2;
  }

  /**
   * Sets minimum discharge power on phase L2 in W, defined by max(EV, EVSE). Value non-negative.
   * Relates to: *ISO 15118-20*: BPTAC/DCCPDReqEnergyTransferModeType: EVMinimumDischargePowerL2
   *
   * @param minDischargePower_L2 Minimum discharge power on phase L2 in W, defined by max(EV, EVSE)
   */
  public void setMinDischargePower_L2(@Nullable Double minDischargePower_L2) {
    this.minDischargePower_L2 = minDischargePower_L2;
  }

  /**
   * Adds minimum discharge power on phase L2 in W, defined by max(EV, EVSE). Value non-negative.
   * Relates to: *ISO 15118-20*: BPTAC/DCCPDReqEnergyTransferModeType: EVMinimumDischargePowerL2
   *
   * @param minDischargePower_L2 Minimum discharge power on phase L2 in W, defined by max(EV, EVSE)
   * @return this
   */
  public V2XChargingParameters withMinDischargePower_L2(@Nullable Double minDischargePower_L2) {
    setMinDischargePower_L2(minDischargePower_L2);
    return this;
  }

  /**
   * Gets minimum discharge power on phase L3 in W, defined by max(EV, EVSE). Value non-negative.
   * Relates to: *ISO 15118-20*: BPTAC/DCCPDReqEnergyTransferModeType: EVMinimumDischargePowerL3
   *
   * @return Minimum discharge power on phase L3 in W, defined by max(EV, EVSE)
   */
  @Nullable
  public Double getMinDischargePower_L3() {
    return minDischargePower_L3;
  }

  /**
   * Sets minimum discharge power on phase L3 in W, defined by max(EV, EVSE). Value non-negative.
   * Relates to: *ISO 15118-20*: BPTAC/DCCPDReqEnergyTransferModeType: EVMinimumDischargePowerL3
   *
   * @param minDischargePower_L3 Minimum discharge power on phase L3 in W, defined by max(EV, EVSE)
   */
  public void setMinDischargePower_L3(@Nullable Double minDischargePower_L3) {
    this.minDischargePower_L3 = minDischargePower_L3;
  }

  /**
   * Adds minimum discharge power on phase L3 in W, defined by max(EV, EVSE). Value non-negative.
   * Relates to: *ISO 15118-20*: BPTAC/DCCPDReqEnergyTransferModeType: EVMinimumDischargePowerL3
   *
   * @param minDischargePower_L3 Minimum discharge power on phase L3 in W, defined by max(EV, EVSE)
   * @return this
   */
  public V2XChargingParameters withMinDischargePower_L3(@Nullable Double minDischargePower_L3) {
    setMinDischargePower_L3(minDischargePower_L3);
    return this;
  }

  /**
   * Gets maximum discharge (injected) power in W, defined by min(EV, EVSE) at unity power factor.
   * Value non-negative. This field represents the sum of all phases, unless values are provided for
   * L2 and L3, in which case this field represents phase L1. Relates to: *ISO 15118-20*:
   * BPTAC/DCCPDReqEnergyTransferModeType: EVMaximumDischargePower
   *
   * @return Maximum discharge (injected) power in W, defined by min(EV, EVSE) at unity power factor
   */
  @Nullable
  public Double getMaxDischargePower() {
    return maxDischargePower;
  }

  /**
   * Sets maximum discharge (injected) power in W, defined by min(EV, EVSE) at unity power factor.
   * Value non-negative. This field represents the sum of all phases, unless values are provided for
   * L2 and L3, in which case this field represents phase L1. Relates to: *ISO 15118-20*:
   * BPTAC/DCCPDReqEnergyTransferModeType: EVMaximumDischargePower
   *
   * @param maxDischargePower Maximum discharge (injected) power in W, defined by min(EV, EVSE) at
   *     unity power factor
   */
  public void setMaxDischargePower(@Nullable Double maxDischargePower) {
    this.maxDischargePower = maxDischargePower;
  }

  /**
   * Adds maximum discharge (injected) power in W, defined by min(EV, EVSE) at unity power factor.
   * Value non-negative. This field represents the sum of all phases, unless values are provided for
   * L2 and L3, in which case this field represents phase L1. Relates to: *ISO 15118-20*:
   * BPTAC/DCCPDReqEnergyTransferModeType: EVMaximumDischargePower
   *
   * @param maxDischargePower Maximum discharge (injected) power in W, defined by min(EV, EVSE) at
   *     unity power factor
   * @return this
   */
  public V2XChargingParameters withMaxDischargePower(@Nullable Double maxDischargePower) {
    setMaxDischargePower(maxDischargePower);
    return this;
  }

  /**
   * Gets maximum discharge power on phase L2 in W, defined by min(EV, EVSE). Value non-negative.
   * Relates to: *ISO 15118-20*: BPTAC/DCCPDReqEnergyTransferModeType: EVMaximumDischargePoweL2
   *
   * @return Maximum discharge power on phase L2 in W, defined by min(EV, EVSE)
   */
  @Nullable
  public Double getMaxDischargePower_L2() {
    return maxDischargePower_L2;
  }

  /**
   * Sets maximum discharge power on phase L2 in W, defined by min(EV, EVSE). Value non-negative.
   * Relates to: *ISO 15118-20*: BPTAC/DCCPDReqEnergyTransferModeType: EVMaximumDischargePoweL2
   *
   * @param maxDischargePower_L2 Maximum discharge power on phase L2 in W, defined by min(EV, EVSE)
   */
  public void setMaxDischargePower_L2(@Nullable Double maxDischargePower_L2) {
    this.maxDischargePower_L2 = maxDischargePower_L2;
  }

  /**
   * Adds maximum discharge power on phase L2 in W, defined by min(EV, EVSE). Value non-negative.
   * Relates to: *ISO 15118-20*: BPTAC/DCCPDReqEnergyTransferModeType: EVMaximumDischargePoweL2
   *
   * @param maxDischargePower_L2 Maximum discharge power on phase L2 in W, defined by min(EV, EVSE)
   * @return this
   */
  public V2XChargingParameters withMaxDischargePower_L2(@Nullable Double maxDischargePower_L2) {
    setMaxDischargePower_L2(maxDischargePower_L2);
    return this;
  }

  /**
   * Gets maximum discharge power on phase L3 in W, defined by min(EV, EVSE). Value non-negative.
   * Relates to: *ISO 15118-20*: BPTAC/DCCPDReqEnergyTransferModeType: EVMaximumDischargePowerL3
   *
   * @return Maximum discharge power on phase L3 in W, defined by min(EV, EVSE)
   */
  @Nullable
  public Double getMaxDischargePower_L3() {
    return maxDischargePower_L3;
  }

  /**
   * Sets maximum discharge power on phase L3 in W, defined by min(EV, EVSE). Value non-negative.
   * Relates to: *ISO 15118-20*: BPTAC/DCCPDReqEnergyTransferModeType: EVMaximumDischargePowerL3
   *
   * @param maxDischargePower_L3 Maximum discharge power on phase L3 in W, defined by min(EV, EVSE)
   */
  public void setMaxDischargePower_L3(@Nullable Double maxDischargePower_L3) {
    this.maxDischargePower_L3 = maxDischargePower_L3;
  }

  /**
   * Adds maximum discharge power on phase L3 in W, defined by min(EV, EVSE). Value non-negative.
   * Relates to: *ISO 15118-20*: BPTAC/DCCPDReqEnergyTransferModeType: EVMaximumDischargePowerL3
   *
   * @param maxDischargePower_L3 Maximum discharge power on phase L3 in W, defined by min(EV, EVSE)
   * @return this
   */
  public V2XChargingParameters withMaxDischargePower_L3(@Nullable Double maxDischargePower_L3) {
    setMaxDischargePower_L3(maxDischargePower_L3);
    return this;
  }

  /**
   * Gets minimum charge current in A, defined by max(EV, EVSE) Relates to: *ISO 15118-20*:
   * BPTDCCPDReqEnergyTransferModeType: EVMinimumChargeCurrent
   *
   * @return Minimum charge current in A, defined by max(EV, EVSE) Relates to: *ISO 15118-20*:
   *     BPTDCCPDReqEnergyTransferModeType: EVMinimumChargeCurrent
   */
  @Nullable
  public Double getMinChargeCurrent() {
    return minChargeCurrent;
  }

  /**
   * Sets minimum charge current in A, defined by max(EV, EVSE) Relates to: *ISO 15118-20*:
   * BPTDCCPDReqEnergyTransferModeType: EVMinimumChargeCurrent
   *
   * @param minChargeCurrent Minimum charge current in A, defined by max(EV, EVSE) Relates to: *ISO
   *     15118-20*: BPTDCCPDReqEnergyTransferModeType: EVMinimumChargeCurrent
   */
  public void setMinChargeCurrent(@Nullable Double minChargeCurrent) {
    this.minChargeCurrent = minChargeCurrent;
  }

  /**
   * Adds minimum charge current in A, defined by max(EV, EVSE) Relates to: *ISO 15118-20*:
   * BPTDCCPDReqEnergyTransferModeType: EVMinimumChargeCurrent
   *
   * @param minChargeCurrent Minimum charge current in A, defined by max(EV, EVSE) Relates to: *ISO
   *     15118-20*: BPTDCCPDReqEnergyTransferModeType: EVMinimumChargeCurrent
   * @return this
   */
  public V2XChargingParameters withMinChargeCurrent(@Nullable Double minChargeCurrent) {
    setMinChargeCurrent(minChargeCurrent);
    return this;
  }

  /**
   * Gets maximum charge current in A, defined by min(EV, EVSE) Relates to: *ISO 15118-20*:
   * BPTDCCPDReqEnergyTransferModeType: EVMaximumChargeCurrent
   *
   * @return Maximum charge current in A, defined by min(EV, EVSE) Relates to: *ISO 15118-20*:
   *     BPTDCCPDReqEnergyTransferModeType: EVMaximumChargeCurrent
   */
  @Nullable
  public Double getMaxChargeCurrent() {
    return maxChargeCurrent;
  }

  /**
   * Sets maximum charge current in A, defined by min(EV, EVSE) Relates to: *ISO 15118-20*:
   * BPTDCCPDReqEnergyTransferModeType: EVMaximumChargeCurrent
   *
   * @param maxChargeCurrent Maximum charge current in A, defined by min(EV, EVSE) Relates to: *ISO
   *     15118-20*: BPTDCCPDReqEnergyTransferModeType: EVMaximumChargeCurrent
   */
  public void setMaxChargeCurrent(@Nullable Double maxChargeCurrent) {
    this.maxChargeCurrent = maxChargeCurrent;
  }

  /**
   * Adds maximum charge current in A, defined by min(EV, EVSE) Relates to: *ISO 15118-20*:
   * BPTDCCPDReqEnergyTransferModeType: EVMaximumChargeCurrent
   *
   * @param maxChargeCurrent Maximum charge current in A, defined by min(EV, EVSE) Relates to: *ISO
   *     15118-20*: BPTDCCPDReqEnergyTransferModeType: EVMaximumChargeCurrent
   * @return this
   */
  public V2XChargingParameters withMaxChargeCurrent(@Nullable Double maxChargeCurrent) {
    setMaxChargeCurrent(maxChargeCurrent);
    return this;
  }

  /**
   * Gets minimum discharge current in A, defined by max(EV, EVSE). Value non-negative. Relates to:
   * *ISO 15118-20*: BPTDCCPDReqEnergyTransferModeType: EVMinimumDischargeCurrent
   *
   * @return Minimum discharge current in A, defined by max(EV, EVSE)
   */
  @Nullable
  public Double getMinDischargeCurrent() {
    return minDischargeCurrent;
  }

  /**
   * Sets minimum discharge current in A, defined by max(EV, EVSE). Value non-negative. Relates to:
   * *ISO 15118-20*: BPTDCCPDReqEnergyTransferModeType: EVMinimumDischargeCurrent
   *
   * @param minDischargeCurrent Minimum discharge current in A, defined by max(EV, EVSE)
   */
  public void setMinDischargeCurrent(@Nullable Double minDischargeCurrent) {
    this.minDischargeCurrent = minDischargeCurrent;
  }

  /**
   * Adds minimum discharge current in A, defined by max(EV, EVSE). Value non-negative. Relates to:
   * *ISO 15118-20*: BPTDCCPDReqEnergyTransferModeType: EVMinimumDischargeCurrent
   *
   * @param minDischargeCurrent Minimum discharge current in A, defined by max(EV, EVSE)
   * @return this
   */
  public V2XChargingParameters withMinDischargeCurrent(@Nullable Double minDischargeCurrent) {
    setMinDischargeCurrent(minDischargeCurrent);
    return this;
  }

  /**
   * Gets maximum discharge current in A, defined by min(EV, EVSE). Value non-negative. Relates to:
   * *ISO 15118-20*: BPTDCCPDReqEnergyTransferModeType: EVMaximumDischargeCurrent
   *
   * @return Maximum discharge current in A, defined by min(EV, EVSE)
   */
  @Nullable
  public Double getMaxDischargeCurrent() {
    return maxDischargeCurrent;
  }

  /**
   * Sets maximum discharge current in A, defined by min(EV, EVSE). Value non-negative. Relates to:
   * *ISO 15118-20*: BPTDCCPDReqEnergyTransferModeType: EVMaximumDischargeCurrent
   *
   * @param maxDischargeCurrent Maximum discharge current in A, defined by min(EV, EVSE)
   */
  public void setMaxDischargeCurrent(@Nullable Double maxDischargeCurrent) {
    this.maxDischargeCurrent = maxDischargeCurrent;
  }

  /**
   * Adds maximum discharge current in A, defined by min(EV, EVSE). Value non-negative. Relates to:
   * *ISO 15118-20*: BPTDCCPDReqEnergyTransferModeType: EVMaximumDischargeCurrent
   *
   * @param maxDischargeCurrent Maximum discharge current in A, defined by min(EV, EVSE)
   * @return this
   */
  public V2XChargingParameters withMaxDischargeCurrent(@Nullable Double maxDischargeCurrent) {
    setMaxDischargeCurrent(maxDischargeCurrent);
    return this;
  }

  /**
   * Gets minimum voltage in V, defined by max(EV, EVSE) Relates to: *ISO 15118-20*:
   * BPTDCCPDReqEnergyTransferModeType: EVMinimumVoltage
   *
   * @return Minimum voltage in V, defined by max(EV, EVSE) Relates to: *ISO 15118-20*:
   *     BPTDCCPDReqEnergyTransferModeType: EVMinimumVoltage
   */
  @Nullable
  public Double getMinVoltage() {
    return minVoltage;
  }

  /**
   * Sets minimum voltage in V, defined by max(EV, EVSE) Relates to: *ISO 15118-20*:
   * BPTDCCPDReqEnergyTransferModeType: EVMinimumVoltage
   *
   * @param minVoltage Minimum voltage in V, defined by max(EV, EVSE) Relates to: *ISO 15118-20*:
   *     BPTDCCPDReqEnergyTransferModeType: EVMinimumVoltage
   */
  public void setMinVoltage(@Nullable Double minVoltage) {
    this.minVoltage = minVoltage;
  }

  /**
   * Adds minimum voltage in V, defined by max(EV, EVSE) Relates to: *ISO 15118-20*:
   * BPTDCCPDReqEnergyTransferModeType: EVMinimumVoltage
   *
   * @param minVoltage Minimum voltage in V, defined by max(EV, EVSE) Relates to: *ISO 15118-20*:
   *     BPTDCCPDReqEnergyTransferModeType: EVMinimumVoltage
   * @return this
   */
  public V2XChargingParameters withMinVoltage(@Nullable Double minVoltage) {
    setMinVoltage(minVoltage);
    return this;
  }

  /**
   * Gets maximum voltage in V, defined by min(EV, EVSE) Relates to: *ISO 15118-20*:
   * BPTDCCPDReqEnergyTransferModeType: EVMaximumVoltage
   *
   * @return Maximum voltage in V, defined by min(EV, EVSE) Relates to: *ISO 15118-20*:
   *     BPTDCCPDReqEnergyTransferModeType: EVMaximumVoltage
   */
  @Nullable
  public Double getMaxVoltage() {
    return maxVoltage;
  }

  /**
   * Sets maximum voltage in V, defined by min(EV, EVSE) Relates to: *ISO 15118-20*:
   * BPTDCCPDReqEnergyTransferModeType: EVMaximumVoltage
   *
   * @param maxVoltage Maximum voltage in V, defined by min(EV, EVSE) Relates to: *ISO 15118-20*:
   *     BPTDCCPDReqEnergyTransferModeType: EVMaximumVoltage
   */
  public void setMaxVoltage(@Nullable Double maxVoltage) {
    this.maxVoltage = maxVoltage;
  }

  /**
   * Adds maximum voltage in V, defined by min(EV, EVSE) Relates to: *ISO 15118-20*:
   * BPTDCCPDReqEnergyTransferModeType: EVMaximumVoltage
   *
   * @param maxVoltage Maximum voltage in V, defined by min(EV, EVSE) Relates to: *ISO 15118-20*:
   *     BPTDCCPDReqEnergyTransferModeType: EVMaximumVoltage
   * @return this
   */
  public V2XChargingParameters withMaxVoltage(@Nullable Double maxVoltage) {
    setMaxVoltage(maxVoltage);
    return this;
  }

  /**
   * Gets energy to requested state of charge in Wh Relates to: *ISO 15118-20*:
   * Dynamic/ScheduledSEReqControlModeType: EVTargetEnergyRequest
   *
   * @return Energy to requested state of charge in Wh Relates to: *ISO 15118-20*:
   *     Dynamic/ScheduledSEReqControlModeType: EVTargetEnergyRequest
   */
  @Nullable
  public Double getEvTargetEnergyRequest() {
    return evTargetEnergyRequest;
  }

  /**
   * Sets energy to requested state of charge in Wh Relates to: *ISO 15118-20*:
   * Dynamic/ScheduledSEReqControlModeType: EVTargetEnergyRequest
   *
   * @param evTargetEnergyRequest Energy to requested state of charge in Wh Relates to: *ISO
   *     15118-20*: Dynamic/ScheduledSEReqControlModeType: EVTargetEnergyRequest
   */
  public void setEvTargetEnergyRequest(@Nullable Double evTargetEnergyRequest) {
    this.evTargetEnergyRequest = evTargetEnergyRequest;
  }

  /**
   * Adds energy to requested state of charge in Wh Relates to: *ISO 15118-20*:
   * Dynamic/ScheduledSEReqControlModeType: EVTargetEnergyRequest
   *
   * @param evTargetEnergyRequest Energy to requested state of charge in Wh Relates to: *ISO
   *     15118-20*: Dynamic/ScheduledSEReqControlModeType: EVTargetEnergyRequest
   * @return this
   */
  public V2XChargingParameters withEvTargetEnergyRequest(@Nullable Double evTargetEnergyRequest) {
    setEvTargetEnergyRequest(evTargetEnergyRequest);
    return this;
  }

  /**
   * Gets energy to minimum allowed state of charge in Wh Relates to: *ISO 15118-20*:
   * Dynamic/ScheduledSEReqControlModeType: EVMinimumEnergyRequest
   *
   * @return Energy to minimum allowed state of charge in Wh Relates to: *ISO 15118-20*:
   *     Dynamic/ScheduledSEReqControlModeType: EVMinimumEnergyRequest
   */
  @Nullable
  public Double getEvMinEnergyRequest() {
    return evMinEnergyRequest;
  }

  /**
   * Sets energy to minimum allowed state of charge in Wh Relates to: *ISO 15118-20*:
   * Dynamic/ScheduledSEReqControlModeType: EVMinimumEnergyRequest
   *
   * @param evMinEnergyRequest Energy to minimum allowed state of charge in Wh Relates to: *ISO
   *     15118-20*: Dynamic/ScheduledSEReqControlModeType: EVMinimumEnergyRequest
   */
  public void setEvMinEnergyRequest(@Nullable Double evMinEnergyRequest) {
    this.evMinEnergyRequest = evMinEnergyRequest;
  }

  /**
   * Adds energy to minimum allowed state of charge in Wh Relates to: *ISO 15118-20*:
   * Dynamic/ScheduledSEReqControlModeType: EVMinimumEnergyRequest
   *
   * @param evMinEnergyRequest Energy to minimum allowed state of charge in Wh Relates to: *ISO
   *     15118-20*: Dynamic/ScheduledSEReqControlModeType: EVMinimumEnergyRequest
   * @return this
   */
  public V2XChargingParameters withEvMinEnergyRequest(@Nullable Double evMinEnergyRequest) {
    setEvMinEnergyRequest(evMinEnergyRequest);
    return this;
  }

  /**
   * Gets energy to maximum state of charge in Wh Relates to: *ISO 15118-20*:
   * Dynamic/ScheduledSEReqControlModeType: EVMaximumEnergyRequest
   *
   * @return Energy to maximum state of charge in Wh Relates to: *ISO 15118-20*:
   *     Dynamic/ScheduledSEReqControlModeType: EVMaximumEnergyRequest
   */
  @Nullable
  public Double getEvMaxEnergyRequest() {
    return evMaxEnergyRequest;
  }

  /**
   * Sets energy to maximum state of charge in Wh Relates to: *ISO 15118-20*:
   * Dynamic/ScheduledSEReqControlModeType: EVMaximumEnergyRequest
   *
   * @param evMaxEnergyRequest Energy to maximum state of charge in Wh Relates to: *ISO 15118-20*:
   *     Dynamic/ScheduledSEReqControlModeType: EVMaximumEnergyRequest
   */
  public void setEvMaxEnergyRequest(@Nullable Double evMaxEnergyRequest) {
    this.evMaxEnergyRequest = evMaxEnergyRequest;
  }

  /**
   * Adds energy to maximum state of charge in Wh Relates to: *ISO 15118-20*:
   * Dynamic/ScheduledSEReqControlModeType: EVMaximumEnergyRequest
   *
   * @param evMaxEnergyRequest Energy to maximum state of charge in Wh Relates to: *ISO 15118-20*:
   *     Dynamic/ScheduledSEReqControlModeType: EVMaximumEnergyRequest
   * @return this
   */
  public V2XChargingParameters withEvMaxEnergyRequest(@Nullable Double evMaxEnergyRequest) {
    setEvMaxEnergyRequest(evMaxEnergyRequest);
    return this;
  }

  /**
   * Gets energy (in Wh) to minimum state of charge for cycling (V2X) activity. Positive value means
   * that current state of charge is below V2X range. Relates to: *ISO 15118-20*:
   * DynamicSEReqControlModeType: EVMinimumV2XEnergyRequest
   *
   * @return Energy (in Wh) to minimum state of charge for cycling (V2X) activity
   */
  @Nullable
  public Double getEvMinV2XEnergyRequest() {
    return evMinV2XEnergyRequest;
  }

  /**
   * Sets energy (in Wh) to minimum state of charge for cycling (V2X) activity. Positive value means
   * that current state of charge is below V2X range. Relates to: *ISO 15118-20*:
   * DynamicSEReqControlModeType: EVMinimumV2XEnergyRequest
   *
   * @param evMinV2XEnergyRequest Energy (in Wh) to minimum state of charge for cycling (V2X)
   *     activity
   */
  public void setEvMinV2XEnergyRequest(@Nullable Double evMinV2XEnergyRequest) {
    this.evMinV2XEnergyRequest = evMinV2XEnergyRequest;
  }

  /**
   * Adds energy (in Wh) to minimum state of charge for cycling (V2X) activity. Positive value means
   * that current state of charge is below V2X range. Relates to: *ISO 15118-20*:
   * DynamicSEReqControlModeType: EVMinimumV2XEnergyRequest
   *
   * @param evMinV2XEnergyRequest Energy (in Wh) to minimum state of charge for cycling (V2X)
   *     activity
   * @return this
   */
  public V2XChargingParameters withEvMinV2XEnergyRequest(@Nullable Double evMinV2XEnergyRequest) {
    setEvMinV2XEnergyRequest(evMinV2XEnergyRequest);
    return this;
  }

  /**
   * Gets energy (in Wh) to maximum state of charge for cycling (V2X) activity. Negative value
   * indicates that current state of charge is above V2X range. Relates to: *ISO 15118-20*:
   * DynamicSEReqControlModeType: EVMaximumV2XEnergyRequest
   *
   * @return Energy (in Wh) to maximum state of charge for cycling (V2X) activity
   */
  @Nullable
  public Double getEvMaxV2XEnergyRequest() {
    return evMaxV2XEnergyRequest;
  }

  /**
   * Sets energy (in Wh) to maximum state of charge for cycling (V2X) activity. Negative value
   * indicates that current state of charge is above V2X range. Relates to: *ISO 15118-20*:
   * DynamicSEReqControlModeType: EVMaximumV2XEnergyRequest
   *
   * @param evMaxV2XEnergyRequest Energy (in Wh) to maximum state of charge for cycling (V2X)
   *     activity
   */
  public void setEvMaxV2XEnergyRequest(@Nullable Double evMaxV2XEnergyRequest) {
    this.evMaxV2XEnergyRequest = evMaxV2XEnergyRequest;
  }

  /**
   * Adds energy (in Wh) to maximum state of charge for cycling (V2X) activity. Negative value
   * indicates that current state of charge is above V2X range. Relates to: *ISO 15118-20*:
   * DynamicSEReqControlModeType: EVMaximumV2XEnergyRequest
   *
   * @param evMaxV2XEnergyRequest Energy (in Wh) to maximum state of charge for cycling (V2X)
   *     activity
   * @return this
   */
  public V2XChargingParameters withEvMaxV2XEnergyRequest(@Nullable Double evMaxV2XEnergyRequest) {
    setEvMaxV2XEnergyRequest(evMaxV2XEnergyRequest);
    return this;
  }

  /**
   * Gets target state of charge at departure as percentage. Relates to: *ISO 15118-20*:
   * BPTDCCPDReqEnergyTransferModeType: TargetSOC
   *
   * @return Target state of charge at departure as percentage
   */
  @Nullable
  public Integer getTargetSoC() {
    return targetSoC;
  }

  /**
   * Sets target state of charge at departure as percentage. Relates to: *ISO 15118-20*:
   * BPTDCCPDReqEnergyTransferModeType: TargetSOC
   *
   * @param targetSoC Target state of charge at departure as percentage
   */
  public void setTargetSoC(@Nullable Integer targetSoC) {
    if (!isValidTargetSoC(targetSoC)) {
      throw new PropertyConstraintException(targetSoC, "targetSoC is invalid");
    }
    this.targetSoC = targetSoC;
  }

  /**
   * Returns whether the given targetSoC is valid
   *
   * @param targetSoC the targetSoC to check the validity of
   * @return {@code true} if targetSoC is valid, {@code false} if not
   */
  private boolean isValidTargetSoC(@Nullable Integer targetSoC) {
    return targetSoC == null || (targetSoC >= 0 && targetSoC <= 100);
  }

  /**
   * Adds target state of charge at departure as percentage. Relates to: *ISO 15118-20*:
   * BPTDCCPDReqEnergyTransferModeType: TargetSOC
   *
   * @param targetSoC Target state of charge at departure as percentage
   * @return this
   */
  public V2XChargingParameters withTargetSoC(@Nullable Integer targetSoC) {
    setTargetSoC(targetSoC);
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
  public V2XChargingParameters withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  public boolean validate() {
    return isValidTargetSoC(targetSoC) && isValidCustomData(customData);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    V2XChargingParameters that = (V2XChargingParameters) o;
    return Objects.equals(minChargePower, that.minChargePower)
        && Objects.equals(minChargePower_L2, that.minChargePower_L2)
        && Objects.equals(minChargePower_L3, that.minChargePower_L3)
        && Objects.equals(maxChargePower, that.maxChargePower)
        && Objects.equals(maxChargePower_L2, that.maxChargePower_L2)
        && Objects.equals(maxChargePower_L3, that.maxChargePower_L3)
        && Objects.equals(minDischargePower, that.minDischargePower)
        && Objects.equals(minDischargePower_L2, that.minDischargePower_L2)
        && Objects.equals(minDischargePower_L3, that.minDischargePower_L3)
        && Objects.equals(maxDischargePower, that.maxDischargePower)
        && Objects.equals(maxDischargePower_L2, that.maxDischargePower_L2)
        && Objects.equals(maxDischargePower_L3, that.maxDischargePower_L3)
        && Objects.equals(minChargeCurrent, that.minChargeCurrent)
        && Objects.equals(maxChargeCurrent, that.maxChargeCurrent)
        && Objects.equals(minDischargeCurrent, that.minDischargeCurrent)
        && Objects.equals(maxDischargeCurrent, that.maxDischargeCurrent)
        && Objects.equals(minVoltage, that.minVoltage)
        && Objects.equals(maxVoltage, that.maxVoltage)
        && Objects.equals(evTargetEnergyRequest, that.evTargetEnergyRequest)
        && Objects.equals(evMinEnergyRequest, that.evMinEnergyRequest)
        && Objects.equals(evMaxEnergyRequest, that.evMaxEnergyRequest)
        && Objects.equals(evMinV2XEnergyRequest, that.evMinV2XEnergyRequest)
        && Objects.equals(evMaxV2XEnergyRequest, that.evMaxV2XEnergyRequest)
        && Objects.equals(targetSoC, that.targetSoC)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        minChargePower,
        minChargePower_L2,
        minChargePower_L3,
        maxChargePower,
        maxChargePower_L2,
        maxChargePower_L3,
        minDischargePower,
        minDischargePower_L2,
        minDischargePower_L3,
        maxDischargePower,
        maxDischargePower_L2,
        maxDischargePower_L3,
        minChargeCurrent,
        maxChargeCurrent,
        minDischargeCurrent,
        maxDischargeCurrent,
        minVoltage,
        maxVoltage,
        evTargetEnergyRequest,
        evMinEnergyRequest,
        evMaxEnergyRequest,
        evMinV2XEnergyRequest,
        evMaxV2XEnergyRequest,
        targetSoC,
        customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("minChargePower", minChargePower)
        .add("minChargePower_L2", minChargePower_L2)
        .add("minChargePower_L3", minChargePower_L3)
        .add("maxChargePower", maxChargePower)
        .add("maxChargePower_L2", maxChargePower_L2)
        .add("maxChargePower_L3", maxChargePower_L3)
        .add("minDischargePower", minDischargePower)
        .add("minDischargePower_L2", minDischargePower_L2)
        .add("minDischargePower_L3", minDischargePower_L3)
        .add("maxDischargePower", maxDischargePower)
        .add("maxDischargePower_L2", maxDischargePower_L2)
        .add("maxDischargePower_L3", maxDischargePower_L3)
        .add("minChargeCurrent", minChargeCurrent)
        .add("maxChargeCurrent", maxChargeCurrent)
        .add("minDischargeCurrent", minDischargeCurrent)
        .add("maxDischargeCurrent", maxDischargeCurrent)
        .add("minVoltage", minVoltage)
        .add("maxVoltage", maxVoltage)
        .add("evTargetEnergyRequest", evTargetEnergyRequest)
        .add("evMinEnergyRequest", evMinEnergyRequest)
        .add("evMaxEnergyRequest", evMaxEnergyRequest)
        .add("evMinV2XEnergyRequest", evMinV2XEnergyRequest)
        .add("evMaxV2XEnergyRequest", evMaxV2XEnergyRequest)
        .add("targetSoC", targetSoC)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
