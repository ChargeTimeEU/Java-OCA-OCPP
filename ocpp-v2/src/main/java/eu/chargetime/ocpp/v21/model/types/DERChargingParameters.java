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
 * DERChargingParametersType is used in ChargingNeedsType during an ISO 15118-20 session for
 * ACBPTDER to report the inverter settings related to DER control that were agreed between EVSE and
 * EV.
 *
 * <p>Fields starting with "ev" contain values from the EV. Other fields contain a value that is
 * supported by both EV and EVSE.
 *
 * <p>DERChargingParametersType type is only relevant in case of an ISO 15118-20 ACBPTDER/ACDER
 * charging session.
 *
 * <p>NOTE: All these fields have values greater or equal to zero (i.e. are non-negative)
 */
public final class DERChargingParameters {
  /**
   * DER control functions supported by EV.
   *
   * <p>*ISO 15118-20*: DERBPTACCPDReqEnergyTransferModeType:DERControlFunctions (bitmap)
   */
  @Nullable private DERControlEnum[] evSupportedDERControl;

  /**
   * Rated maximum injected active power by EV, at specified over-excited power factor
   * (overExcitedPowerFactor).
   *
   * <pre>
   * It can also be defined as the rated maximum discharge power at the rated minimum injected
   * reactive power value. This means that if the EV is providing reactive power support, and it is
   * requested to discharge at max power (e.g. to satisfy an EMS request), the EV may override the
   * request and discharge up to overExcitedMaximumDischargePower to meet the minimum reactive power
   * requirements.
   * Corresponds to the WOvPF attribute in IEC 61850.
   * *ISO 15118-20*: DERBPTACCPDReqEnergyTransferModeType: EVOverExcitedMaximumDischargePower
   * </pre>
   */
  @Nullable private Double evOverExcitedMaxDischargePower;

  /**
   * EV power factor when injecting (over excited) the minimum reactive power.
   *
   * <pre>
   * Corresponds to the OvPF attribute in IEC 61850.
   * *ISO 15118-20*: DERBPTACCPDReqEnergyTransferModeType: EVOverExcitedPowerFactor
   * </pre>
   */
  @Nullable private Double evOverExcitedPowerFactor;

  /**
   * Rated maximum injected active power by EV supported at specified under-excited power factor
   * (EVUnderExcitedPowerFactor).
   *
   * <p>It can also be defined as the rated maximum dischargePower at the rated minimum absorbed
   * reactive power value.
   *
   * <pre>
   * This means that if the EV is providing reactive power support, and it is requested to discharge
   * at max power (e.g. to satisfy an EMS request), the EV may override the request and discharge up
   * to underExcitedMaximumDischargePower to meet the minimum reactive power requirements.
   * This corresponds to the WUnPF attribute in the IEC 61850.
   * *ISO 15118-20*: DERBPTACCPDReqEnergyTransferModeType: EVUnderExcitedMaximumDischargePower
   * </pre>
   */
  @Nullable private Double evUnderExcitedMaxDischargePower;

  /**
   * EV power factor when injecting (under excited) the minimum reactive power.
   *
   * <pre>
   * Corresponds to the OvPF attribute in IEC 61850.
   * *ISO 15118-20*: DERBPTACCPDReqEnergyTransferModeType: EVUnderExcitedPowerFactor
   * </pre>
   */
  @Nullable private Double evUnderExcitedPowerFactor;

  /**
   * Rated maximum total apparent power, defined by min(EV, EVSE) in va.
   *
   * <pre>
   * Corresponds to the VAMaxRtg in IEC 61850.
   * *ISO 15118-20*: DERBPTACCPDReqEnergyTransferModeType: EVMaximumApparentPower
   * </pre>
   */
  @Nullable private Double maxApparentPower;

  /**
   * Rated maximum absorbed apparent power, defined by min(EV, EVSE) in va.
   *
   * <p>This field represents the sum of all phases, unless values are provided for L2 and L3,
   *
   * <pre>
   * in which case this field represents phase L1.
   * Corresponds to the ChaVAMaxRtg in IEC 61850.
   * *ISO 15118-20*: DERBPTACCPDReqEnergyTransferModeType: EVMaximumChargeApparentPower
   * </pre>
   */
  @Nullable private Double maxChargeApparentPower;

  /**
   * Rated maximum absorbed apparent power on phase L2, defined by min(EV, EVSE) in va.
   *
   * <pre>
   * Corresponds to the ChaVAMaxRtg in IEC 61850.
   * *ISO 15118-20*: DERBPTACCPDReqEnergyTransferModeType: EVMaximumChargeApparentPowerL2
   * </pre>
   */
  @Nullable private Double maxChargeApparentPower_L2;

  /**
   * Rated maximum absorbed apparent power on phase L3, defined by min(EV, EVSE) in va.
   *
   * <pre>
   * Corresponds to the ChaVAMaxRtg in IEC 61850.
   * *ISO 15118-20*: DERBPTACCPDReqEnergyTransferModeType: EVMaximumChargeApparentPowerL3
   * </pre>
   */
  @Nullable private Double maxChargeApparentPower_L3;

  /**
   * Rated maximum injected apparent power, defined by min(EV, EVSE) in va.
   *
   * <p>This field represents the sum of all phases, unless values are provided for L2 and L3,
   *
   * <pre>
   * in which case this field represents phase L1.
   * Corresponds to the DisVAMaxRtg in IEC 61850.
   * *ISO 15118-20*: DERBPTACCPDReqEnergyTransferModeType: EVMaximumDischargeApparentPower
   * </pre>
   */
  @Nullable private Double maxDischargeApparentPower;

  /**
   * Rated maximum injected apparent power on phase L2, defined by min(EV, EVSE) in va.
   *
   * <pre>
   * Corresponds to the DisVAMaxRtg in IEC 61850.
   * *ISO 15118-20*: DERBPTACCPDReqEnergyTransferModeType: EVMaximumDischargeApparentPowerL2
   * </pre>
   */
  @Nullable private Double maxDischargeApparentPower_L2;

  /**
   * Rated maximum injected apparent power on phase L3, defined by min(EV, EVSE) in va.
   *
   * <pre>
   * Corresponds to the DisVAMaxRtg in IEC 61850.
   * *ISO 15118-20*: DERBPTACCPDReqEnergyTransferModeType: EVMaximumDischargeApparentPowerL3
   * </pre>
   */
  @Nullable private Double maxDischargeApparentPower_L3;

  /**
   * Rated maximum absorbed reactive power, defined by min(EV, EVSE), in vars.
   *
   * <p>This field represents the sum of all phases, unless values are provided for L2 and L3,
   *
   * <pre>
   * in which case this field represents phase L1.
   * Corresponds to the AvarMax attribute in the IEC 61850.
   * *ISO 15118-20*: DERBPTACCPDReqEnergyTransferModeType: EVMaximumChargeReactivePower
   * </pre>
   */
  @Nullable private Double maxChargeReactivePower;

  /**
   * Rated maximum absorbed reactive power, defined by min(EV, EVSE), in vars on phase L2.
   *
   * <pre>
   * Corresponds to the AvarMax attribute in the IEC 61850.
   * *ISO 15118-20*: DERBPTACCPDReqEnergyTransferModeType: EVMaximumChargeReactivePowerL2
   * </pre>
   */
  @Nullable private Double maxChargeReactivePower_L2;

  /**
   * Rated maximum absorbed reactive power, defined by min(EV, EVSE), in vars on phase L3.
   *
   * <pre>
   * Corresponds to the AvarMax attribute in the IEC 61850.
   * *ISO 15118-20*: DERBPTACCPDReqEnergyTransferModeType: EVMaximumChargeReactivePowerL3
   * </pre>
   */
  @Nullable private Double maxChargeReactivePower_L3;

  /**
   * Rated minimum absorbed reactive power, defined by max(EV, EVSE), in vars.
   *
   * <p>This field represents the sum of all phases, unless values are provided for L2 and L3,
   *
   * <pre>
   * in which case this field represents phase L1.
   * *ISO 15118-20*: DERBPTACCPDReqEnergyTransferModeType: EVMinimumChargeReactivePower
   * </pre>
   */
  @Nullable private Double minChargeReactivePower;

  /**
   * Rated minimum absorbed reactive power, defined by max(EV, EVSE), in vars on phase L2.
   *
   * <p>*ISO 15118-20*: DERBPTACCPDReqEnergyTransferModeType: EVMinimumChargeReactivePowerL2
   */
  @Nullable private Double minChargeReactivePower_L2;

  /**
   * Rated minimum absorbed reactive power, defined by max(EV, EVSE), in vars on phase L3.
   *
   * <p>*ISO 15118-20*: DERBPTACCPDReqEnergyTransferModeType: EVMinimumChargeReactivePowerL3
   */
  @Nullable private Double minChargeReactivePower_L3;

  /**
   * Rated maximum injected reactive power, defined by min(EV, EVSE), in vars.
   *
   * <p>This field represents the sum of all phases, unless values are provided for L2 and L3,
   *
   * <pre>
   * in which case this field represents phase L1.
   * Corresponds to the IvarMax attribute in the IEC 61850.
   * *ISO 15118-20*: DERBPTACCPDReqEnergyTransferModeType: EVMaximumDischargeReactivePower
   * </pre>
   */
  @Nullable private Double maxDischargeReactivePower;

  /**
   * Rated maximum injected reactive power, defined by min(EV, EVSE), in vars on phase L2.
   *
   * <pre>
   * Corresponds to the IvarMax attribute in the IEC 61850.
   * *ISO 15118-20*: DERBPTACCPDReqEnergyTransferModeType: EVMaximumDischargeReactivePowerL2
   * </pre>
   */
  @Nullable private Double maxDischargeReactivePower_L2;

  /**
   * Rated maximum injected reactive power, defined by min(EV, EVSE), in vars on phase L3.
   *
   * <pre>
   * Corresponds to the IvarMax attribute in the IEC 61850.
   * *ISO 15118-20*: DERBPTACCPDReqEnergyTransferModeType: EVMaximumDischargeReactivePowerL3
   * </pre>
   */
  @Nullable private Double maxDischargeReactivePower_L3;

  /**
   * Rated minimum injected reactive power, defined by max(EV, EVSE), in vars.
   *
   * <p>This field represents the sum of all phases, unless values are provided for L2 and L3,
   *
   * <pre>
   * in which case this field represents phase L1.
   * *ISO 15118-20*: DERBPTACCPDReqEnergyTransferModeType: EVMinimumDischargeReactivePower
   * </pre>
   */
  @Nullable private Double minDischargeReactivePower;

  /**
   * Rated minimum injected reactive power, defined by max(EV, EVSE), in var on phase L2.
   *
   * <p>*ISO 15118-20*: DERBPTACCPDReqEnergyTransferModeType: EVMinimumDischargeReactivePowerL2
   */
  @Nullable private Double minDischargeReactivePower_L2;

  /**
   * Rated minimum injected reactive power, defined by max(EV, EVSE), in var on phase L3.
   *
   * <p>*ISO 15118-20*: DERBPTACCPDReqEnergyTransferModeType: EVMinimumDischargeReactivePowerL3
   */
  @Nullable private Double minDischargeReactivePower_L3;

  /**
   * Line voltage supported by EVSE and EV. *ISO 15118-20*: DERBPTACCPDReqEnergyTransferModeType:
   * EVNominalVoltage
   */
  @Nullable private Double nominalVoltage;

  /**
   * The nominal AC voltage (rms) offset between the Charging Station's electrical connection point
   * and the utility’s point of common coupling.
   *
   * <p>*ISO 15118-20*: DERBPTACCPDReqEnergyTransferModeType: EVNominalVoltageOffset
   */
  @Nullable private Double nominalVoltageOffset;

  /**
   * Maximum AC rms voltage, as defined by min(EV, EVSE) to operate with.
   *
   * <p>*ISO 15118-20*: DERBPTACCPDReqEnergyTransferModeType: EVMaximumNominalVoltage
   */
  @Nullable private Double maxNominalVoltage;

  /**
   * Minimum AC rms voltage, as defined by max(EV, EVSE) to operate with.
   *
   * <p>*ISO 15118-20*: DERBPTACCPDReqEnergyTransferModeType: EVMinimumNominalVoltage
   */
  @Nullable private Double minNominalVoltage;

  /**
   * Manufacturer of the EV inverter.
   *
   * <p>*ISO 15118-20*: DERBPTACCPDReqEnergyTransferModeType: EVInverterManufacturer
   */
  @Nullable private String evInverterManufacturer;

  /**
   * Model name of the EV inverter.
   *
   * <p>*ISO 15118-20*: DERBPTACCPDReqEnergyTransferModeType: EVInverterModel
   */
  @Nullable private String evInverterModel;

  /**
   * Serial number of the EV inverter.
   *
   * <p>*ISO 15118-20*: DERBPTACCPDReqEnergyTransferModeType: EVInverterSerialNumber
   */
  @Nullable private String evInverterSerialNumber;

  /**
   * Software version of EV inverter.
   *
   * <p>*ISO 15118-20*: DERBPTACCPDReqEnergyTransferModeType: EVInverterSwVersion
   */
  @Nullable private String evInverterSwVersion;

  /**
   * Hardware version of EV inverter.
   *
   * <p>*ISO 15118-20*: DERBPTACCPDReqEnergyTransferModeType: EVInverterHwVersion
   */
  @Nullable private String evInverterHwVersion;

  /**
   * Type of islanding detection method. Only mandatory when islanding detection is required at the
   * site, as set in the ISO 15118 Service Details configuration.
   *
   * <p>*ISO 15118-20*: DERBPTACCPDReqEnergyTransferModeType: EVIslandingDetectionMethod
   */
  @Nullable private IslandingDetectionEnum[] evIslandingDetectionMethod;

  /**
   * Time after which EV will trip if an island has been detected.
   *
   * <p>*ISO 15118-20*: DERBPTACCPDReqEnergyTransferModeType: EVIslandingTripTime
   */
  @Nullable private Double evIslandingTripTime;

  /**
   * Maximum injected DC current allowed at level 1 charging.
   *
   * <p>*ISO 15118-20*: DERBPTACCPDReqEnergyTransferModeType: EVMaximumLevel1DCInjection
   */
  @Nullable private Double evMaximumLevel1DCInjection;

  /**
   * Maximum allowed duration of DC injection at level 1 charging.
   *
   * <p>*ISO 15118-20*: DERBPTACCPDReqEnergyTransferModeType: EVDurationLevel1DCInjection
   */
  @Nullable private Double evDurationLevel1DCInjection;

  /**
   * Maximum injected DC current allowed at level 2 charging.
   *
   * <p>*ISO 15118-20*: DERBPTACCPDReqEnergyTransferModeType: EVMaximumLevel2DCInjection
   */
  @Nullable private Double evMaximumLevel2DCInjection;

  /**
   * Maximum allowed duration of DC injection at level 2 charging.
   *
   * <p>*ISO 15118-20*: DERBPTACCPDReqEnergyTransferModeType: EVDurationLevel2DCInjection
   */
  @Nullable private Double evDurationLevel2DCInjection;

  /**
   * Measure of the susceptibility of the circuit to reactance, in Siemens (S).
   *
   * <p>*ISO 15118-20*: DERBPTACCPDReqEnergyTransferModeType: EVReactiveSusceptance
   */
  @Nullable private Double evReactiveSusceptance;

  /**
   * Total energy value, in Wh, that EV is allowed to provide during the entire V2G session. The
   * value is independent of the V2X Cycling area. Once this value reaches the value of 0, the EV
   * may block any attempt to discharge in order to protect the battery health. *ISO 15118-20*:
   * DERBPTACCPDReqEnergyTransferModeType: EVSessionTotalDischargeEnergyAvailable
   */
  @Nullable private Double evSessionTotalDischargeEnergyAvailable;

  /** Custom data */
  @Nullable private CustomData customData;

  /** Constructor for the DERChargingParameters class */
  public DERChargingParameters() {}

  /**
   * Gets DER control functions supported by EV.
   *
   * @return DER control functions supported by EV
   */
  @Nullable
  public DERControlEnum[] getEvSupportedDERControl() {
    return evSupportedDERControl;
  }

  /**
   * Sets DER control functions supported by EV.
   *
   * @param evSupportedDERControl DER control functions supported by EV
   */
  public void setEvSupportedDERControl(@Nullable DERControlEnum[] evSupportedDERControl) {
    if (!isValidEvSupportedDERControl(evSupportedDERControl)) {
      throw new PropertyConstraintException(
          evSupportedDERControl, "evSupportedDERControl is invalid");
    }
    this.evSupportedDERControl = evSupportedDERControl;
  }

  /**
   * Returns whether the given evSupportedDERControl is valid
   *
   * @param evSupportedDERControl the evSupportedDERControl to check the validity of
   * @return {@code true} if evSupportedDERControl is valid, {@code false} if not
   */
  private boolean isValidEvSupportedDERControl(@Nullable DERControlEnum[] evSupportedDERControl) {
    return evSupportedDERControl == null || (evSupportedDERControl.length >= 1);
  }

  /**
   * Adds DER control functions supported by EV.
   *
   * @param evSupportedDERControl DER control functions supported by EV
   * @return this
   */
  public DERChargingParameters withEvSupportedDERControl(
      @Nullable DERControlEnum[] evSupportedDERControl) {
    setEvSupportedDERControl(evSupportedDERControl);
    return this;
  }

  /**
   * Gets rated maximum injected active power by EV, at specified over-excited power factor
   * (overExcitedPowerFactor).
   *
   * @return Rated maximum injected active power by EV, at specified over-excited power factor
   *     (overExcitedPowerFactor)
   */
  @Nullable
  public Double getEvOverExcitedMaxDischargePower() {
    return evOverExcitedMaxDischargePower;
  }

  /**
   * Sets rated maximum injected active power by EV, at specified over-excited power factor
   * (overExcitedPowerFactor).
   *
   * @param evOverExcitedMaxDischargePower Rated maximum injected active power by EV, at specified
   *     over-excited power factor (overExcitedPowerFactor)
   */
  public void setEvOverExcitedMaxDischargePower(@Nullable Double evOverExcitedMaxDischargePower) {
    this.evOverExcitedMaxDischargePower = evOverExcitedMaxDischargePower;
  }

  /**
   * Adds rated maximum injected active power by EV, at specified over-excited power factor
   * (overExcitedPowerFactor).
   *
   * @param evOverExcitedMaxDischargePower Rated maximum injected active power by EV, at specified
   *     over-excited power factor (overExcitedPowerFactor)
   * @return this
   */
  public DERChargingParameters withEvOverExcitedMaxDischargePower(
      @Nullable Double evOverExcitedMaxDischargePower) {
    setEvOverExcitedMaxDischargePower(evOverExcitedMaxDischargePower);
    return this;
  }

  /**
   * Gets EV power factor when injecting (over excited) the minimum reactive power.
   *
   * @return EV power factor when injecting (over excited) the minimum reactive power
   */
  @Nullable
  public Double getEvOverExcitedPowerFactor() {
    return evOverExcitedPowerFactor;
  }

  /**
   * Sets EV power factor when injecting (over excited) the minimum reactive power.
   *
   * @param evOverExcitedPowerFactor EV power factor when injecting (over excited) the minimum
   *     reactive power
   */
  public void setEvOverExcitedPowerFactor(@Nullable Double evOverExcitedPowerFactor) {
    this.evOverExcitedPowerFactor = evOverExcitedPowerFactor;
  }

  /**
   * Adds EV power factor when injecting (over excited) the minimum reactive power.
   *
   * @param evOverExcitedPowerFactor EV power factor when injecting (over excited) the minimum
   *     reactive power
   * @return this
   */
  public DERChargingParameters withEvOverExcitedPowerFactor(
      @Nullable Double evOverExcitedPowerFactor) {
    setEvOverExcitedPowerFactor(evOverExcitedPowerFactor);
    return this;
  }

  /**
   * Gets rated maximum injected active power by EV supported at specified under-excited power
   * factor (EVUnderExcitedPowerFactor).
   *
   * @return Rated maximum injected active power by EV supported at specified under-excited power
   *     factor (EVUnderExcitedPowerFactor)
   */
  @Nullable
  public Double getEvUnderExcitedMaxDischargePower() {
    return evUnderExcitedMaxDischargePower;
  }

  /**
   * Sets rated maximum injected active power by EV supported at specified under-excited power
   * factor (EVUnderExcitedPowerFactor).
   *
   * @param evUnderExcitedMaxDischargePower Rated maximum injected active power by EV supported at
   *     specified under-excited power factor (EVUnderExcitedPowerFactor)
   */
  public void setEvUnderExcitedMaxDischargePower(@Nullable Double evUnderExcitedMaxDischargePower) {
    this.evUnderExcitedMaxDischargePower = evUnderExcitedMaxDischargePower;
  }

  /**
   * Adds rated maximum injected active power by EV supported at specified under-excited power
   * factor (EVUnderExcitedPowerFactor).
   *
   * @param evUnderExcitedMaxDischargePower Rated maximum injected active power by EV supported at
   *     specified under-excited power factor (EVUnderExcitedPowerFactor)
   * @return this
   */
  public DERChargingParameters withEvUnderExcitedMaxDischargePower(
      @Nullable Double evUnderExcitedMaxDischargePower) {
    setEvUnderExcitedMaxDischargePower(evUnderExcitedMaxDischargePower);
    return this;
  }

  /**
   * Gets EV power factor when injecting (under excited) the minimum reactive power.
   *
   * @return EV power factor when injecting (under excited) the minimum reactive power
   */
  @Nullable
  public Double getEvUnderExcitedPowerFactor() {
    return evUnderExcitedPowerFactor;
  }

  /**
   * Sets EV power factor when injecting (under excited) the minimum reactive power.
   *
   * @param evUnderExcitedPowerFactor EV power factor when injecting (under excited) the minimum
   *     reactive power
   */
  public void setEvUnderExcitedPowerFactor(@Nullable Double evUnderExcitedPowerFactor) {
    this.evUnderExcitedPowerFactor = evUnderExcitedPowerFactor;
  }

  /**
   * Adds EV power factor when injecting (under excited) the minimum reactive power.
   *
   * @param evUnderExcitedPowerFactor EV power factor when injecting (under excited) the minimum
   *     reactive power
   * @return this
   */
  public DERChargingParameters withEvUnderExcitedPowerFactor(
      @Nullable Double evUnderExcitedPowerFactor) {
    setEvUnderExcitedPowerFactor(evUnderExcitedPowerFactor);
    return this;
  }

  /**
   * Gets rated maximum total apparent power, defined by min(EV, EVSE) in va.
   *
   * @return Rated maximum total apparent power, defined by min(EV, EVSE) in va
   */
  @Nullable
  public Double getMaxApparentPower() {
    return maxApparentPower;
  }

  /**
   * Sets rated maximum total apparent power, defined by min(EV, EVSE) in va.
   *
   * @param maxApparentPower Rated maximum total apparent power, defined by min(EV, EVSE) in va
   */
  public void setMaxApparentPower(@Nullable Double maxApparentPower) {
    this.maxApparentPower = maxApparentPower;
  }

  /**
   * Adds rated maximum total apparent power, defined by min(EV, EVSE) in va.
   *
   * @param maxApparentPower Rated maximum total apparent power, defined by min(EV, EVSE) in va
   * @return this
   */
  public DERChargingParameters withMaxApparentPower(@Nullable Double maxApparentPower) {
    setMaxApparentPower(maxApparentPower);
    return this;
  }

  /**
   * Gets rated maximum absorbed apparent power, defined by min(EV, EVSE) in va.
   *
   * @return Rated maximum absorbed apparent power, defined by min(EV, EVSE) in va
   */
  @Nullable
  public Double getMaxChargeApparentPower() {
    return maxChargeApparentPower;
  }

  /**
   * Sets rated maximum absorbed apparent power, defined by min(EV, EVSE) in va.
   *
   * @param maxChargeApparentPower Rated maximum absorbed apparent power, defined by min(EV, EVSE)
   *     in va
   */
  public void setMaxChargeApparentPower(@Nullable Double maxChargeApparentPower) {
    this.maxChargeApparentPower = maxChargeApparentPower;
  }

  /**
   * Adds rated maximum absorbed apparent power, defined by min(EV, EVSE) in va.
   *
   * @param maxChargeApparentPower Rated maximum absorbed apparent power, defined by min(EV, EVSE)
   *     in va
   * @return this
   */
  public DERChargingParameters withMaxChargeApparentPower(@Nullable Double maxChargeApparentPower) {
    setMaxChargeApparentPower(maxChargeApparentPower);
    return this;
  }

  /**
   * Gets rated maximum absorbed apparent power on phase L2, defined by min(EV, EVSE) in va.
   *
   * @return Rated maximum absorbed apparent power on phase L2, defined by min(EV, EVSE) in va
   */
  @Nullable
  public Double getMaxChargeApparentPower_L2() {
    return maxChargeApparentPower_L2;
  }

  /**
   * Sets rated maximum absorbed apparent power on phase L2, defined by min(EV, EVSE) in va.
   *
   * @param maxChargeApparentPower_L2 Rated maximum absorbed apparent power on phase L2, defined by
   *     min(EV, EVSE) in va
   */
  public void setMaxChargeApparentPower_L2(@Nullable Double maxChargeApparentPower_L2) {
    this.maxChargeApparentPower_L2 = maxChargeApparentPower_L2;
  }

  /**
   * Adds rated maximum absorbed apparent power on phase L2, defined by min(EV, EVSE) in va.
   *
   * @param maxChargeApparentPower_L2 Rated maximum absorbed apparent power on phase L2, defined by
   *     min(EV, EVSE) in va
   * @return this
   */
  public DERChargingParameters withMaxChargeApparentPower_L2(
      @Nullable Double maxChargeApparentPower_L2) {
    setMaxChargeApparentPower_L2(maxChargeApparentPower_L2);
    return this;
  }

  /**
   * Gets rated maximum absorbed apparent power on phase L3, defined by min(EV, EVSE) in va.
   *
   * @return Rated maximum absorbed apparent power on phase L3, defined by min(EV, EVSE) in va
   */
  @Nullable
  public Double getMaxChargeApparentPower_L3() {
    return maxChargeApparentPower_L3;
  }

  /**
   * Sets rated maximum absorbed apparent power on phase L3, defined by min(EV, EVSE) in va.
   *
   * @param maxChargeApparentPower_L3 Rated maximum absorbed apparent power on phase L3, defined by
   *     min(EV, EVSE) in va
   */
  public void setMaxChargeApparentPower_L3(@Nullable Double maxChargeApparentPower_L3) {
    this.maxChargeApparentPower_L3 = maxChargeApparentPower_L3;
  }

  /**
   * Adds rated maximum absorbed apparent power on phase L3, defined by min(EV, EVSE) in va.
   *
   * @param maxChargeApparentPower_L3 Rated maximum absorbed apparent power on phase L3, defined by
   *     min(EV, EVSE) in va
   * @return this
   */
  public DERChargingParameters withMaxChargeApparentPower_L3(
      @Nullable Double maxChargeApparentPower_L3) {
    setMaxChargeApparentPower_L3(maxChargeApparentPower_L3);
    return this;
  }

  /**
   * Gets rated maximum injected apparent power, defined by min(EV, EVSE) in va.
   *
   * @return Rated maximum injected apparent power, defined by min(EV, EVSE) in va
   */
  @Nullable
  public Double getMaxDischargeApparentPower() {
    return maxDischargeApparentPower;
  }

  /**
   * Sets rated maximum injected apparent power, defined by min(EV, EVSE) in va.
   *
   * @param maxDischargeApparentPower Rated maximum injected apparent power, defined by min(EV,
   *     EVSE) in va
   */
  public void setMaxDischargeApparentPower(@Nullable Double maxDischargeApparentPower) {
    this.maxDischargeApparentPower = maxDischargeApparentPower;
  }

  /**
   * Adds rated maximum injected apparent power, defined by min(EV, EVSE) in va.
   *
   * @param maxDischargeApparentPower Rated maximum injected apparent power, defined by min(EV,
   *     EVSE) in va
   * @return this
   */
  public DERChargingParameters withMaxDischargeApparentPower(
      @Nullable Double maxDischargeApparentPower) {
    setMaxDischargeApparentPower(maxDischargeApparentPower);
    return this;
  }

  /**
   * Gets rated maximum injected apparent power on phase L2, defined by min(EV, EVSE) in va.
   *
   * @return Rated maximum injected apparent power on phase L2, defined by min(EV, EVSE) in va
   */
  @Nullable
  public Double getMaxDischargeApparentPower_L2() {
    return maxDischargeApparentPower_L2;
  }

  /**
   * Sets rated maximum injected apparent power on phase L2, defined by min(EV, EVSE) in va.
   *
   * @param maxDischargeApparentPower_L2 Rated maximum injected apparent power on phase L2, defined
   *     by min(EV, EVSE) in va
   */
  public void setMaxDischargeApparentPower_L2(@Nullable Double maxDischargeApparentPower_L2) {
    this.maxDischargeApparentPower_L2 = maxDischargeApparentPower_L2;
  }

  /**
   * Adds rated maximum injected apparent power on phase L2, defined by min(EV, EVSE) in va.
   *
   * @param maxDischargeApparentPower_L2 Rated maximum injected apparent power on phase L2, defined
   *     by min(EV, EVSE) in va
   * @return this
   */
  public DERChargingParameters withMaxDischargeApparentPower_L2(
      @Nullable Double maxDischargeApparentPower_L2) {
    setMaxDischargeApparentPower_L2(maxDischargeApparentPower_L2);
    return this;
  }

  /**
   * Gets rated maximum injected apparent power on phase L3, defined by min(EV, EVSE) in va.
   *
   * @return Rated maximum injected apparent power on phase L3, defined by min(EV, EVSE) in va
   */
  @Nullable
  public Double getMaxDischargeApparentPower_L3() {
    return maxDischargeApparentPower_L3;
  }

  /**
   * Sets rated maximum injected apparent power on phase L3, defined by min(EV, EVSE) in va.
   *
   * @param maxDischargeApparentPower_L3 Rated maximum injected apparent power on phase L3, defined
   *     by min(EV, EVSE) in va
   */
  public void setMaxDischargeApparentPower_L3(@Nullable Double maxDischargeApparentPower_L3) {
    this.maxDischargeApparentPower_L3 = maxDischargeApparentPower_L3;
  }

  /**
   * Adds rated maximum injected apparent power on phase L3, defined by min(EV, EVSE) in va.
   *
   * @param maxDischargeApparentPower_L3 Rated maximum injected apparent power on phase L3, defined
   *     by min(EV, EVSE) in va
   * @return this
   */
  public DERChargingParameters withMaxDischargeApparentPower_L3(
      @Nullable Double maxDischargeApparentPower_L3) {
    setMaxDischargeApparentPower_L3(maxDischargeApparentPower_L3);
    return this;
  }

  /**
   * Gets rated maximum absorbed reactive power, defined by min(EV, EVSE), in vars.
   *
   * @return Rated maximum absorbed reactive power, defined by min(EV, EVSE), in vars
   */
  @Nullable
  public Double getMaxChargeReactivePower() {
    return maxChargeReactivePower;
  }

  /**
   * Sets rated maximum absorbed reactive power, defined by min(EV, EVSE), in vars.
   *
   * @param maxChargeReactivePower Rated maximum absorbed reactive power, defined by min(EV, EVSE),
   *     in vars
   */
  public void setMaxChargeReactivePower(@Nullable Double maxChargeReactivePower) {
    this.maxChargeReactivePower = maxChargeReactivePower;
  }

  /**
   * Adds rated maximum absorbed reactive power, defined by min(EV, EVSE), in vars.
   *
   * @param maxChargeReactivePower Rated maximum absorbed reactive power, defined by min(EV, EVSE),
   *     in vars
   * @return this
   */
  public DERChargingParameters withMaxChargeReactivePower(@Nullable Double maxChargeReactivePower) {
    setMaxChargeReactivePower(maxChargeReactivePower);
    return this;
  }

  /**
   * Gets rated maximum absorbed reactive power, defined by min(EV, EVSE), in vars on phase L2.
   *
   * @return Rated maximum absorbed reactive power, defined by min(EV, EVSE), in vars on phase L2
   */
  @Nullable
  public Double getMaxChargeReactivePower_L2() {
    return maxChargeReactivePower_L2;
  }

  /**
   * Sets rated maximum absorbed reactive power, defined by min(EV, EVSE), in vars on phase L2.
   *
   * @param maxChargeReactivePower_L2 Rated maximum absorbed reactive power, defined by min(EV,
   *     EVSE), in vars on phase L2
   */
  public void setMaxChargeReactivePower_L2(@Nullable Double maxChargeReactivePower_L2) {
    this.maxChargeReactivePower_L2 = maxChargeReactivePower_L2;
  }

  /**
   * Adds rated maximum absorbed reactive power, defined by min(EV, EVSE), in vars on phase L2.
   *
   * @param maxChargeReactivePower_L2 Rated maximum absorbed reactive power, defined by min(EV,
   *     EVSE), in vars on phase L2
   * @return this
   */
  public DERChargingParameters withMaxChargeReactivePower_L2(
      @Nullable Double maxChargeReactivePower_L2) {
    setMaxChargeReactivePower_L2(maxChargeReactivePower_L2);
    return this;
  }

  /**
   * Gets rated maximum absorbed reactive power, defined by min(EV, EVSE), in vars on phase L3.
   *
   * @return Rated maximum absorbed reactive power, defined by min(EV, EVSE), in vars on phase L3
   */
  @Nullable
  public Double getMaxChargeReactivePower_L3() {
    return maxChargeReactivePower_L3;
  }

  /**
   * Sets rated maximum absorbed reactive power, defined by min(EV, EVSE), in vars on phase L3.
   *
   * @param maxChargeReactivePower_L3 Rated maximum absorbed reactive power, defined by min(EV,
   *     EVSE), in vars on phase L3
   */
  public void setMaxChargeReactivePower_L3(@Nullable Double maxChargeReactivePower_L3) {
    this.maxChargeReactivePower_L3 = maxChargeReactivePower_L3;
  }

  /**
   * Adds rated maximum absorbed reactive power, defined by min(EV, EVSE), in vars on phase L3.
   *
   * @param maxChargeReactivePower_L3 Rated maximum absorbed reactive power, defined by min(EV,
   *     EVSE), in vars on phase L3
   * @return this
   */
  public DERChargingParameters withMaxChargeReactivePower_L3(
      @Nullable Double maxChargeReactivePower_L3) {
    setMaxChargeReactivePower_L3(maxChargeReactivePower_L3);
    return this;
  }

  /**
   * Gets rated minimum absorbed reactive power, defined by max(EV, EVSE), in vars.
   *
   * @return Rated minimum absorbed reactive power, defined by max(EV, EVSE), in vars
   */
  @Nullable
  public Double getMinChargeReactivePower() {
    return minChargeReactivePower;
  }

  /**
   * Sets rated minimum absorbed reactive power, defined by max(EV, EVSE), in vars.
   *
   * @param minChargeReactivePower Rated minimum absorbed reactive power, defined by max(EV, EVSE),
   *     in vars
   */
  public void setMinChargeReactivePower(@Nullable Double minChargeReactivePower) {
    this.minChargeReactivePower = minChargeReactivePower;
  }

  /**
   * Adds rated minimum absorbed reactive power, defined by max(EV, EVSE), in vars.
   *
   * @param minChargeReactivePower Rated minimum absorbed reactive power, defined by max(EV, EVSE),
   *     in vars
   * @return this
   */
  public DERChargingParameters withMinChargeReactivePower(@Nullable Double minChargeReactivePower) {
    setMinChargeReactivePower(minChargeReactivePower);
    return this;
  }

  /**
   * Gets rated minimum absorbed reactive power, defined by max(EV, EVSE), in vars on phase L2.
   *
   * @return Rated minimum absorbed reactive power, defined by max(EV, EVSE), in vars on phase L2
   */
  @Nullable
  public Double getMinChargeReactivePower_L2() {
    return minChargeReactivePower_L2;
  }

  /**
   * Sets rated minimum absorbed reactive power, defined by max(EV, EVSE), in vars on phase L2.
   *
   * @param minChargeReactivePower_L2 Rated minimum absorbed reactive power, defined by max(EV,
   *     EVSE), in vars on phase L2
   */
  public void setMinChargeReactivePower_L2(@Nullable Double minChargeReactivePower_L2) {
    this.minChargeReactivePower_L2 = minChargeReactivePower_L2;
  }

  /**
   * Adds rated minimum absorbed reactive power, defined by max(EV, EVSE), in vars on phase L2.
   *
   * @param minChargeReactivePower_L2 Rated minimum absorbed reactive power, defined by max(EV,
   *     EVSE), in vars on phase L2
   * @return this
   */
  public DERChargingParameters withMinChargeReactivePower_L2(
      @Nullable Double minChargeReactivePower_L2) {
    setMinChargeReactivePower_L2(minChargeReactivePower_L2);
    return this;
  }

  /**
   * Gets rated minimum absorbed reactive power, defined by max(EV, EVSE), in vars on phase L3.
   *
   * @return Rated minimum absorbed reactive power, defined by max(EV, EVSE), in vars on phase L3
   */
  @Nullable
  public Double getMinChargeReactivePower_L3() {
    return minChargeReactivePower_L3;
  }

  /**
   * Sets rated minimum absorbed reactive power, defined by max(EV, EVSE), in vars on phase L3.
   *
   * @param minChargeReactivePower_L3 Rated minimum absorbed reactive power, defined by max(EV,
   *     EVSE), in vars on phase L3
   */
  public void setMinChargeReactivePower_L3(@Nullable Double minChargeReactivePower_L3) {
    this.minChargeReactivePower_L3 = minChargeReactivePower_L3;
  }

  /**
   * Adds rated minimum absorbed reactive power, defined by max(EV, EVSE), in vars on phase L3.
   *
   * @param minChargeReactivePower_L3 Rated minimum absorbed reactive power, defined by max(EV,
   *     EVSE), in vars on phase L3
   * @return this
   */
  public DERChargingParameters withMinChargeReactivePower_L3(
      @Nullable Double minChargeReactivePower_L3) {
    setMinChargeReactivePower_L3(minChargeReactivePower_L3);
    return this;
  }

  /**
   * Gets rated maximum injected reactive power, defined by min(EV, EVSE), in vars.
   *
   * @return Rated maximum injected reactive power, defined by min(EV, EVSE), in vars
   */
  @Nullable
  public Double getMaxDischargeReactivePower() {
    return maxDischargeReactivePower;
  }

  /**
   * Sets rated maximum injected reactive power, defined by min(EV, EVSE), in vars.
   *
   * @param maxDischargeReactivePower Rated maximum injected reactive power, defined by min(EV,
   *     EVSE), in vars
   */
  public void setMaxDischargeReactivePower(@Nullable Double maxDischargeReactivePower) {
    this.maxDischargeReactivePower = maxDischargeReactivePower;
  }

  /**
   * Adds rated maximum injected reactive power, defined by min(EV, EVSE), in vars.
   *
   * @param maxDischargeReactivePower Rated maximum injected reactive power, defined by min(EV,
   *     EVSE), in vars
   * @return this
   */
  public DERChargingParameters withMaxDischargeReactivePower(
      @Nullable Double maxDischargeReactivePower) {
    setMaxDischargeReactivePower(maxDischargeReactivePower);
    return this;
  }

  /**
   * Gets rated maximum injected reactive power, defined by min(EV, EVSE), in vars on phase L2.
   *
   * @return Rated maximum injected reactive power, defined by min(EV, EVSE), in vars on phase L2
   */
  @Nullable
  public Double getMaxDischargeReactivePower_L2() {
    return maxDischargeReactivePower_L2;
  }

  /**
   * Sets rated maximum injected reactive power, defined by min(EV, EVSE), in vars on phase L2.
   *
   * @param maxDischargeReactivePower_L2 Rated maximum injected reactive power, defined by min(EV,
   *     EVSE), in vars on phase L2
   */
  public void setMaxDischargeReactivePower_L2(@Nullable Double maxDischargeReactivePower_L2) {
    this.maxDischargeReactivePower_L2 = maxDischargeReactivePower_L2;
  }

  /**
   * Adds rated maximum injected reactive power, defined by min(EV, EVSE), in vars on phase L2.
   *
   * @param maxDischargeReactivePower_L2 Rated maximum injected reactive power, defined by min(EV,
   *     EVSE), in vars on phase L2
   * @return this
   */
  public DERChargingParameters withMaxDischargeReactivePower_L2(
      @Nullable Double maxDischargeReactivePower_L2) {
    setMaxDischargeReactivePower_L2(maxDischargeReactivePower_L2);
    return this;
  }

  /**
   * Gets rated maximum injected reactive power, defined by min(EV, EVSE), in vars on phase L3.
   *
   * @return Rated maximum injected reactive power, defined by min(EV, EVSE), in vars on phase L3
   */
  @Nullable
  public Double getMaxDischargeReactivePower_L3() {
    return maxDischargeReactivePower_L3;
  }

  /**
   * Sets rated maximum injected reactive power, defined by min(EV, EVSE), in vars on phase L3.
   *
   * @param maxDischargeReactivePower_L3 Rated maximum injected reactive power, defined by min(EV,
   *     EVSE), in vars on phase L3
   */
  public void setMaxDischargeReactivePower_L3(@Nullable Double maxDischargeReactivePower_L3) {
    this.maxDischargeReactivePower_L3 = maxDischargeReactivePower_L3;
  }

  /**
   * Adds rated maximum injected reactive power, defined by min(EV, EVSE), in vars on phase L3.
   *
   * @param maxDischargeReactivePower_L3 Rated maximum injected reactive power, defined by min(EV,
   *     EVSE), in vars on phase L3
   * @return this
   */
  public DERChargingParameters withMaxDischargeReactivePower_L3(
      @Nullable Double maxDischargeReactivePower_L3) {
    setMaxDischargeReactivePower_L3(maxDischargeReactivePower_L3);
    return this;
  }

  /**
   * Gets rated minimum injected reactive power, defined by max(EV, EVSE), in vars.
   *
   * @return Rated minimum injected reactive power, defined by max(EV, EVSE), in vars
   */
  @Nullable
  public Double getMinDischargeReactivePower() {
    return minDischargeReactivePower;
  }

  /**
   * Sets rated minimum injected reactive power, defined by max(EV, EVSE), in vars.
   *
   * @param minDischargeReactivePower Rated minimum injected reactive power, defined by max(EV,
   *     EVSE), in vars
   */
  public void setMinDischargeReactivePower(@Nullable Double minDischargeReactivePower) {
    this.minDischargeReactivePower = minDischargeReactivePower;
  }

  /**
   * Adds rated minimum injected reactive power, defined by max(EV, EVSE), in vars.
   *
   * @param minDischargeReactivePower Rated minimum injected reactive power, defined by max(EV,
   *     EVSE), in vars
   * @return this
   */
  public DERChargingParameters withMinDischargeReactivePower(
      @Nullable Double minDischargeReactivePower) {
    setMinDischargeReactivePower(minDischargeReactivePower);
    return this;
  }

  /**
   * Gets rated minimum injected reactive power, defined by max(EV, EVSE), in var on phase L2.
   *
   * @return Rated minimum injected reactive power, defined by max(EV, EVSE), in var on phase L2
   */
  @Nullable
  public Double getMinDischargeReactivePower_L2() {
    return minDischargeReactivePower_L2;
  }

  /**
   * Sets rated minimum injected reactive power, defined by max(EV, EVSE), in var on phase L2.
   *
   * @param minDischargeReactivePower_L2 Rated minimum injected reactive power, defined by max(EV,
   *     EVSE), in var on phase L2
   */
  public void setMinDischargeReactivePower_L2(@Nullable Double minDischargeReactivePower_L2) {
    this.minDischargeReactivePower_L2 = minDischargeReactivePower_L2;
  }

  /**
   * Adds rated minimum injected reactive power, defined by max(EV, EVSE), in var on phase L2.
   *
   * @param minDischargeReactivePower_L2 Rated minimum injected reactive power, defined by max(EV,
   *     EVSE), in var on phase L2
   * @return this
   */
  public DERChargingParameters withMinDischargeReactivePower_L2(
      @Nullable Double minDischargeReactivePower_L2) {
    setMinDischargeReactivePower_L2(minDischargeReactivePower_L2);
    return this;
  }

  /**
   * Gets rated minimum injected reactive power, defined by max(EV, EVSE), in var on phase L3.
   *
   * @return Rated minimum injected reactive power, defined by max(EV, EVSE), in var on phase L3
   */
  @Nullable
  public Double getMinDischargeReactivePower_L3() {
    return minDischargeReactivePower_L3;
  }

  /**
   * Sets rated minimum injected reactive power, defined by max(EV, EVSE), in var on phase L3.
   *
   * @param minDischargeReactivePower_L3 Rated minimum injected reactive power, defined by max(EV,
   *     EVSE), in var on phase L3
   */
  public void setMinDischargeReactivePower_L3(@Nullable Double minDischargeReactivePower_L3) {
    this.minDischargeReactivePower_L3 = minDischargeReactivePower_L3;
  }

  /**
   * Adds rated minimum injected reactive power, defined by max(EV, EVSE), in var on phase L3.
   *
   * @param minDischargeReactivePower_L3 Rated minimum injected reactive power, defined by max(EV,
   *     EVSE), in var on phase L3
   * @return this
   */
  public DERChargingParameters withMinDischargeReactivePower_L3(
      @Nullable Double minDischargeReactivePower_L3) {
    setMinDischargeReactivePower_L3(minDischargeReactivePower_L3);
    return this;
  }

  /**
   * Gets line voltage supported by EVSE and EV. *ISO 15118-20*:
   * DERBPTACCPDReqEnergyTransferModeType: EVNominalVoltage
   *
   * @return Line voltage supported by EVSE and EV
   */
  @Nullable
  public Double getNominalVoltage() {
    return nominalVoltage;
  }

  /**
   * Sets line voltage supported by EVSE and EV. *ISO 15118-20*:
   * DERBPTACCPDReqEnergyTransferModeType: EVNominalVoltage
   *
   * @param nominalVoltage Line voltage supported by EVSE and EV
   */
  public void setNominalVoltage(@Nullable Double nominalVoltage) {
    this.nominalVoltage = nominalVoltage;
  }

  /**
   * Adds line voltage supported by EVSE and EV. *ISO 15118-20*:
   * DERBPTACCPDReqEnergyTransferModeType: EVNominalVoltage
   *
   * @param nominalVoltage Line voltage supported by EVSE and EV
   * @return this
   */
  public DERChargingParameters withNominalVoltage(@Nullable Double nominalVoltage) {
    setNominalVoltage(nominalVoltage);
    return this;
  }

  /**
   * Gets the nominal AC voltage (rms) offset between the Charging Station's electrical connection
   * point and the utility’s point of common coupling.
   *
   * @return The nominal AC voltage (rms) offset between the Charging Station's electrical
   *     connection point and the utility’s point of common coupling
   */
  @Nullable
  public Double getNominalVoltageOffset() {
    return nominalVoltageOffset;
  }

  /**
   * Sets the nominal AC voltage (rms) offset between the Charging Station's electrical connection
   * point and the utility’s point of common coupling.
   *
   * @param nominalVoltageOffset The nominal AC voltage (rms) offset between the Charging Station's
   *     electrical connection point and the utility’s point of common coupling
   */
  public void setNominalVoltageOffset(@Nullable Double nominalVoltageOffset) {
    this.nominalVoltageOffset = nominalVoltageOffset;
  }

  /**
   * Adds the nominal AC voltage (rms) offset between the Charging Station's electrical connection
   * point and the utility’s point of common coupling.
   *
   * @param nominalVoltageOffset The nominal AC voltage (rms) offset between the Charging Station's
   *     electrical connection point and the utility’s point of common coupling
   * @return this
   */
  public DERChargingParameters withNominalVoltageOffset(@Nullable Double nominalVoltageOffset) {
    setNominalVoltageOffset(nominalVoltageOffset);
    return this;
  }

  /**
   * Gets maximum AC rms voltage, as defined by min(EV, EVSE) to operate with.
   *
   * @return Maximum AC rms voltage, as defined by min(EV, EVSE) to operate with
   */
  @Nullable
  public Double getMaxNominalVoltage() {
    return maxNominalVoltage;
  }

  /**
   * Sets maximum AC rms voltage, as defined by min(EV, EVSE) to operate with.
   *
   * @param maxNominalVoltage Maximum AC rms voltage, as defined by min(EV, EVSE) to operate with
   */
  public void setMaxNominalVoltage(@Nullable Double maxNominalVoltage) {
    this.maxNominalVoltage = maxNominalVoltage;
  }

  /**
   * Adds maximum AC rms voltage, as defined by min(EV, EVSE) to operate with.
   *
   * @param maxNominalVoltage Maximum AC rms voltage, as defined by min(EV, EVSE) to operate with
   * @return this
   */
  public DERChargingParameters withMaxNominalVoltage(@Nullable Double maxNominalVoltage) {
    setMaxNominalVoltage(maxNominalVoltage);
    return this;
  }

  /**
   * Gets minimum AC rms voltage, as defined by max(EV, EVSE) to operate with.
   *
   * @return Minimum AC rms voltage, as defined by max(EV, EVSE) to operate with
   */
  @Nullable
  public Double getMinNominalVoltage() {
    return minNominalVoltage;
  }

  /**
   * Sets minimum AC rms voltage, as defined by max(EV, EVSE) to operate with.
   *
   * @param minNominalVoltage Minimum AC rms voltage, as defined by max(EV, EVSE) to operate with
   */
  public void setMinNominalVoltage(@Nullable Double minNominalVoltage) {
    this.minNominalVoltage = minNominalVoltage;
  }

  /**
   * Adds minimum AC rms voltage, as defined by max(EV, EVSE) to operate with.
   *
   * @param minNominalVoltage Minimum AC rms voltage, as defined by max(EV, EVSE) to operate with
   * @return this
   */
  public DERChargingParameters withMinNominalVoltage(@Nullable Double minNominalVoltage) {
    setMinNominalVoltage(minNominalVoltage);
    return this;
  }

  /**
   * Gets manufacturer of the EV inverter.
   *
   * @return Manufacturer of the EV inverter
   */
  @Nullable
  public String getEvInverterManufacturer() {
    return evInverterManufacturer;
  }

  /**
   * Sets manufacturer of the EV inverter.
   *
   * @param evInverterManufacturer Manufacturer of the EV inverter
   */
  public void setEvInverterManufacturer(@Nullable String evInverterManufacturer) {
    if (!isValidEvInverterManufacturer(evInverterManufacturer)) {
      throw new PropertyConstraintException(
          evInverterManufacturer, "evInverterManufacturer is invalid");
    }
    this.evInverterManufacturer = evInverterManufacturer;
  }

  /**
   * Returns whether the given evInverterManufacturer is valid
   *
   * @param evInverterManufacturer the evInverterManufacturer to check the validity of
   * @return {@code true} if evInverterManufacturer is valid, {@code false} if not
   */
  private boolean isValidEvInverterManufacturer(@Nullable String evInverterManufacturer) {
    return evInverterManufacturer == null || evInverterManufacturer.length() <= 50;
  }

  /**
   * Adds manufacturer of the EV inverter.
   *
   * @param evInverterManufacturer Manufacturer of the EV inverter
   * @return this
   */
  public DERChargingParameters withEvInverterManufacturer(@Nullable String evInverterManufacturer) {
    setEvInverterManufacturer(evInverterManufacturer);
    return this;
  }

  /**
   * Gets model name of the EV inverter.
   *
   * @return Model name of the EV inverter
   */
  @Nullable
  public String getEvInverterModel() {
    return evInverterModel;
  }

  /**
   * Sets model name of the EV inverter.
   *
   * @param evInverterModel Model name of the EV inverter
   */
  public void setEvInverterModel(@Nullable String evInverterModel) {
    if (!isValidEvInverterModel(evInverterModel)) {
      throw new PropertyConstraintException(evInverterModel, "evInverterModel is invalid");
    }
    this.evInverterModel = evInverterModel;
  }

  /**
   * Returns whether the given evInverterModel is valid
   *
   * @param evInverterModel the evInverterModel to check the validity of
   * @return {@code true} if evInverterModel is valid, {@code false} if not
   */
  private boolean isValidEvInverterModel(@Nullable String evInverterModel) {
    return evInverterModel == null || evInverterModel.length() <= 50;
  }

  /**
   * Adds model name of the EV inverter.
   *
   * @param evInverterModel Model name of the EV inverter
   * @return this
   */
  public DERChargingParameters withEvInverterModel(@Nullable String evInverterModel) {
    setEvInverterModel(evInverterModel);
    return this;
  }

  /**
   * Gets serial number of the EV inverter.
   *
   * @return Serial number of the EV inverter
   */
  @Nullable
  public String getEvInverterSerialNumber() {
    return evInverterSerialNumber;
  }

  /**
   * Sets serial number of the EV inverter.
   *
   * @param evInverterSerialNumber Serial number of the EV inverter
   */
  public void setEvInverterSerialNumber(@Nullable String evInverterSerialNumber) {
    if (!isValidEvInverterSerialNumber(evInverterSerialNumber)) {
      throw new PropertyConstraintException(
          evInverterSerialNumber, "evInverterSerialNumber is invalid");
    }
    this.evInverterSerialNumber = evInverterSerialNumber;
  }

  /**
   * Returns whether the given evInverterSerialNumber is valid
   *
   * @param evInverterSerialNumber the evInverterSerialNumber to check the validity of
   * @return {@code true} if evInverterSerialNumber is valid, {@code false} if not
   */
  private boolean isValidEvInverterSerialNumber(@Nullable String evInverterSerialNumber) {
    return evInverterSerialNumber == null || evInverterSerialNumber.length() <= 50;
  }

  /**
   * Adds serial number of the EV inverter.
   *
   * @param evInverterSerialNumber Serial number of the EV inverter
   * @return this
   */
  public DERChargingParameters withEvInverterSerialNumber(@Nullable String evInverterSerialNumber) {
    setEvInverterSerialNumber(evInverterSerialNumber);
    return this;
  }

  /**
   * Gets software version of EV inverter.
   *
   * @return Software version of EV inverter
   */
  @Nullable
  public String getEvInverterSwVersion() {
    return evInverterSwVersion;
  }

  /**
   * Sets software version of EV inverter.
   *
   * @param evInverterSwVersion Software version of EV inverter
   */
  public void setEvInverterSwVersion(@Nullable String evInverterSwVersion) {
    if (!isValidEvInverterSwVersion(evInverterSwVersion)) {
      throw new PropertyConstraintException(evInverterSwVersion, "evInverterSwVersion is invalid");
    }
    this.evInverterSwVersion = evInverterSwVersion;
  }

  /**
   * Returns whether the given evInverterSwVersion is valid
   *
   * @param evInverterSwVersion the evInverterSwVersion to check the validity of
   * @return {@code true} if evInverterSwVersion is valid, {@code false} if not
   */
  private boolean isValidEvInverterSwVersion(@Nullable String evInverterSwVersion) {
    return evInverterSwVersion == null || evInverterSwVersion.length() <= 50;
  }

  /**
   * Adds software version of EV inverter.
   *
   * @param evInverterSwVersion Software version of EV inverter
   * @return this
   */
  public DERChargingParameters withEvInverterSwVersion(@Nullable String evInverterSwVersion) {
    setEvInverterSwVersion(evInverterSwVersion);
    return this;
  }

  /**
   * Gets hardware version of EV inverter.
   *
   * @return Hardware version of EV inverter
   */
  @Nullable
  public String getEvInverterHwVersion() {
    return evInverterHwVersion;
  }

  /**
   * Sets hardware version of EV inverter.
   *
   * @param evInverterHwVersion Hardware version of EV inverter
   */
  public void setEvInverterHwVersion(@Nullable String evInverterHwVersion) {
    if (!isValidEvInverterHwVersion(evInverterHwVersion)) {
      throw new PropertyConstraintException(evInverterHwVersion, "evInverterHwVersion is invalid");
    }
    this.evInverterHwVersion = evInverterHwVersion;
  }

  /**
   * Returns whether the given evInverterHwVersion is valid
   *
   * @param evInverterHwVersion the evInverterHwVersion to check the validity of
   * @return {@code true} if evInverterHwVersion is valid, {@code false} if not
   */
  private boolean isValidEvInverterHwVersion(@Nullable String evInverterHwVersion) {
    return evInverterHwVersion == null || evInverterHwVersion.length() <= 50;
  }

  /**
   * Adds hardware version of EV inverter.
   *
   * @param evInverterHwVersion Hardware version of EV inverter
   * @return this
   */
  public DERChargingParameters withEvInverterHwVersion(@Nullable String evInverterHwVersion) {
    setEvInverterHwVersion(evInverterHwVersion);
    return this;
  }

  /**
   * Gets type of islanding detection method. Only mandatory when islanding detection is required at
   * the site, as set in the ISO 15118 Service Details configuration.
   *
   * @return Type of islanding detection method
   */
  @Nullable
  public IslandingDetectionEnum[] getEvIslandingDetectionMethod() {
    return evIslandingDetectionMethod;
  }

  /**
   * Sets type of islanding detection method. Only mandatory when islanding detection is required at
   * the site, as set in the ISO 15118 Service Details configuration.
   *
   * @param evIslandingDetectionMethod Type of islanding detection method
   */
  public void setEvIslandingDetectionMethod(
      @Nullable IslandingDetectionEnum[] evIslandingDetectionMethod) {
    if (!isValidEvIslandingDetectionMethod(evIslandingDetectionMethod)) {
      throw new PropertyConstraintException(
          evIslandingDetectionMethod, "evIslandingDetectionMethod is invalid");
    }
    this.evIslandingDetectionMethod = evIslandingDetectionMethod;
  }

  /**
   * Returns whether the given evIslandingDetectionMethod is valid
   *
   * @param evIslandingDetectionMethod the evIslandingDetectionMethod to check the validity of
   * @return {@code true} if evIslandingDetectionMethod is valid, {@code false} if not
   */
  private boolean isValidEvIslandingDetectionMethod(
      @Nullable IslandingDetectionEnum[] evIslandingDetectionMethod) {
    return evIslandingDetectionMethod == null || (evIslandingDetectionMethod.length >= 1);
  }

  /**
   * Adds type of islanding detection method. Only mandatory when islanding detection is required at
   * the site, as set in the ISO 15118 Service Details configuration.
   *
   * @param evIslandingDetectionMethod Type of islanding detection method
   * @return this
   */
  public DERChargingParameters withEvIslandingDetectionMethod(
      @Nullable IslandingDetectionEnum[] evIslandingDetectionMethod) {
    setEvIslandingDetectionMethod(evIslandingDetectionMethod);
    return this;
  }

  /**
   * Gets time after which EV will trip if an island has been detected.
   *
   * @return Time after which EV will trip if an island has been detected
   */
  @Nullable
  public Double getEvIslandingTripTime() {
    return evIslandingTripTime;
  }

  /**
   * Sets time after which EV will trip if an island has been detected.
   *
   * @param evIslandingTripTime Time after which EV will trip if an island has been detected
   */
  public void setEvIslandingTripTime(@Nullable Double evIslandingTripTime) {
    this.evIslandingTripTime = evIslandingTripTime;
  }

  /**
   * Adds time after which EV will trip if an island has been detected.
   *
   * @param evIslandingTripTime Time after which EV will trip if an island has been detected
   * @return this
   */
  public DERChargingParameters withEvIslandingTripTime(@Nullable Double evIslandingTripTime) {
    setEvIslandingTripTime(evIslandingTripTime);
    return this;
  }

  /**
   * Gets maximum injected DC current allowed at level 1 charging.
   *
   * @return Maximum injected DC current allowed at level 1 charging
   */
  @Nullable
  public Double getEvMaximumLevel1DCInjection() {
    return evMaximumLevel1DCInjection;
  }

  /**
   * Sets maximum injected DC current allowed at level 1 charging.
   *
   * @param evMaximumLevel1DCInjection Maximum injected DC current allowed at level 1 charging
   */
  public void setEvMaximumLevel1DCInjection(@Nullable Double evMaximumLevel1DCInjection) {
    this.evMaximumLevel1DCInjection = evMaximumLevel1DCInjection;
  }

  /**
   * Adds maximum injected DC current allowed at level 1 charging.
   *
   * @param evMaximumLevel1DCInjection Maximum injected DC current allowed at level 1 charging
   * @return this
   */
  public DERChargingParameters withEvMaximumLevel1DCInjection(
      @Nullable Double evMaximumLevel1DCInjection) {
    setEvMaximumLevel1DCInjection(evMaximumLevel1DCInjection);
    return this;
  }

  /**
   * Gets maximum allowed duration of DC injection at level 1 charging.
   *
   * @return Maximum allowed duration of DC injection at level 1 charging
   */
  @Nullable
  public Double getEvDurationLevel1DCInjection() {
    return evDurationLevel1DCInjection;
  }

  /**
   * Sets maximum allowed duration of DC injection at level 1 charging.
   *
   * @param evDurationLevel1DCInjection Maximum allowed duration of DC injection at level 1 charging
   */
  public void setEvDurationLevel1DCInjection(@Nullable Double evDurationLevel1DCInjection) {
    this.evDurationLevel1DCInjection = evDurationLevel1DCInjection;
  }

  /**
   * Adds maximum allowed duration of DC injection at level 1 charging.
   *
   * @param evDurationLevel1DCInjection Maximum allowed duration of DC injection at level 1 charging
   * @return this
   */
  public DERChargingParameters withEvDurationLevel1DCInjection(
      @Nullable Double evDurationLevel1DCInjection) {
    setEvDurationLevel1DCInjection(evDurationLevel1DCInjection);
    return this;
  }

  /**
   * Gets maximum injected DC current allowed at level 2 charging.
   *
   * @return Maximum injected DC current allowed at level 2 charging
   */
  @Nullable
  public Double getEvMaximumLevel2DCInjection() {
    return evMaximumLevel2DCInjection;
  }

  /**
   * Sets maximum injected DC current allowed at level 2 charging.
   *
   * @param evMaximumLevel2DCInjection Maximum injected DC current allowed at level 2 charging
   */
  public void setEvMaximumLevel2DCInjection(@Nullable Double evMaximumLevel2DCInjection) {
    this.evMaximumLevel2DCInjection = evMaximumLevel2DCInjection;
  }

  /**
   * Adds maximum injected DC current allowed at level 2 charging.
   *
   * @param evMaximumLevel2DCInjection Maximum injected DC current allowed at level 2 charging
   * @return this
   */
  public DERChargingParameters withEvMaximumLevel2DCInjection(
      @Nullable Double evMaximumLevel2DCInjection) {
    setEvMaximumLevel2DCInjection(evMaximumLevel2DCInjection);
    return this;
  }

  /**
   * Gets maximum allowed duration of DC injection at level 2 charging.
   *
   * @return Maximum allowed duration of DC injection at level 2 charging
   */
  @Nullable
  public Double getEvDurationLevel2DCInjection() {
    return evDurationLevel2DCInjection;
  }

  /**
   * Sets maximum allowed duration of DC injection at level 2 charging.
   *
   * @param evDurationLevel2DCInjection Maximum allowed duration of DC injection at level 2 charging
   */
  public void setEvDurationLevel2DCInjection(@Nullable Double evDurationLevel2DCInjection) {
    this.evDurationLevel2DCInjection = evDurationLevel2DCInjection;
  }

  /**
   * Adds maximum allowed duration of DC injection at level 2 charging.
   *
   * @param evDurationLevel2DCInjection Maximum allowed duration of DC injection at level 2 charging
   * @return this
   */
  public DERChargingParameters withEvDurationLevel2DCInjection(
      @Nullable Double evDurationLevel2DCInjection) {
    setEvDurationLevel2DCInjection(evDurationLevel2DCInjection);
    return this;
  }

  /**
   * Gets measure of the susceptibility of the circuit to reactance, in Siemens (S).
   *
   * @return Measure of the susceptibility of the circuit to reactance, in Siemens (S)
   */
  @Nullable
  public Double getEvReactiveSusceptance() {
    return evReactiveSusceptance;
  }

  /**
   * Sets measure of the susceptibility of the circuit to reactance, in Siemens (S).
   *
   * @param evReactiveSusceptance Measure of the susceptibility of the circuit to reactance, in
   *     Siemens (S)
   */
  public void setEvReactiveSusceptance(@Nullable Double evReactiveSusceptance) {
    this.evReactiveSusceptance = evReactiveSusceptance;
  }

  /**
   * Adds measure of the susceptibility of the circuit to reactance, in Siemens (S).
   *
   * @param evReactiveSusceptance Measure of the susceptibility of the circuit to reactance, in
   *     Siemens (S)
   * @return this
   */
  public DERChargingParameters withEvReactiveSusceptance(@Nullable Double evReactiveSusceptance) {
    setEvReactiveSusceptance(evReactiveSusceptance);
    return this;
  }

  /**
   * Gets total energy value, in Wh, that EV is allowed to provide during the entire V2G session.
   * The value is independent of the V2X Cycling area. Once this value reaches the value of 0, the
   * EV may block any attempt to discharge in order to protect the battery health. *ISO 15118-20*:
   * DERBPTACCPDReqEnergyTransferModeType: EVSessionTotalDischargeEnergyAvailable
   *
   * @return Total energy value, in Wh, that EV is allowed to provide during the entire V2G session
   */
  @Nullable
  public Double getEvSessionTotalDischargeEnergyAvailable() {
    return evSessionTotalDischargeEnergyAvailable;
  }

  /**
   * Sets total energy value, in Wh, that EV is allowed to provide during the entire V2G session.
   * The value is independent of the V2X Cycling area. Once this value reaches the value of 0, the
   * EV may block any attempt to discharge in order to protect the battery health. *ISO 15118-20*:
   * DERBPTACCPDReqEnergyTransferModeType: EVSessionTotalDischargeEnergyAvailable
   *
   * @param evSessionTotalDischargeEnergyAvailable Total energy value, in Wh, that EV is allowed to
   *     provide during the entire V2G session
   */
  public void setEvSessionTotalDischargeEnergyAvailable(
      @Nullable Double evSessionTotalDischargeEnergyAvailable) {
    this.evSessionTotalDischargeEnergyAvailable = evSessionTotalDischargeEnergyAvailable;
  }

  /**
   * Adds total energy value, in Wh, that EV is allowed to provide during the entire V2G session.
   * The value is independent of the V2X Cycling area. Once this value reaches the value of 0, the
   * EV may block any attempt to discharge in order to protect the battery health. *ISO 15118-20*:
   * DERBPTACCPDReqEnergyTransferModeType: EVSessionTotalDischargeEnergyAvailable
   *
   * @param evSessionTotalDischargeEnergyAvailable Total energy value, in Wh, that EV is allowed to
   *     provide during the entire V2G session
   * @return this
   */
  public DERChargingParameters withEvSessionTotalDischargeEnergyAvailable(
      @Nullable Double evSessionTotalDischargeEnergyAvailable) {
    setEvSessionTotalDischargeEnergyAvailable(evSessionTotalDischargeEnergyAvailable);
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
  public DERChargingParameters withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  public boolean validate() {
    return isValidEvSupportedDERControl(evSupportedDERControl)
        && isValidEvInverterManufacturer(evInverterManufacturer)
        && isValidEvInverterModel(evInverterModel)
        && isValidEvInverterSerialNumber(evInverterSerialNumber)
        && isValidEvInverterSwVersion(evInverterSwVersion)
        && isValidEvInverterHwVersion(evInverterHwVersion)
        && isValidEvIslandingDetectionMethod(evIslandingDetectionMethod)
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
    DERChargingParameters that = (DERChargingParameters) o;
    return Arrays.equals(evSupportedDERControl, that.evSupportedDERControl)
        && Objects.equals(evOverExcitedMaxDischargePower, that.evOverExcitedMaxDischargePower)
        && Objects.equals(evOverExcitedPowerFactor, that.evOverExcitedPowerFactor)
        && Objects.equals(evUnderExcitedMaxDischargePower, that.evUnderExcitedMaxDischargePower)
        && Objects.equals(evUnderExcitedPowerFactor, that.evUnderExcitedPowerFactor)
        && Objects.equals(maxApparentPower, that.maxApparentPower)
        && Objects.equals(maxChargeApparentPower, that.maxChargeApparentPower)
        && Objects.equals(maxChargeApparentPower_L2, that.maxChargeApparentPower_L2)
        && Objects.equals(maxChargeApparentPower_L3, that.maxChargeApparentPower_L3)
        && Objects.equals(maxDischargeApparentPower, that.maxDischargeApparentPower)
        && Objects.equals(maxDischargeApparentPower_L2, that.maxDischargeApparentPower_L2)
        && Objects.equals(maxDischargeApparentPower_L3, that.maxDischargeApparentPower_L3)
        && Objects.equals(maxChargeReactivePower, that.maxChargeReactivePower)
        && Objects.equals(maxChargeReactivePower_L2, that.maxChargeReactivePower_L2)
        && Objects.equals(maxChargeReactivePower_L3, that.maxChargeReactivePower_L3)
        && Objects.equals(minChargeReactivePower, that.minChargeReactivePower)
        && Objects.equals(minChargeReactivePower_L2, that.minChargeReactivePower_L2)
        && Objects.equals(minChargeReactivePower_L3, that.minChargeReactivePower_L3)
        && Objects.equals(maxDischargeReactivePower, that.maxDischargeReactivePower)
        && Objects.equals(maxDischargeReactivePower_L2, that.maxDischargeReactivePower_L2)
        && Objects.equals(maxDischargeReactivePower_L3, that.maxDischargeReactivePower_L3)
        && Objects.equals(minDischargeReactivePower, that.minDischargeReactivePower)
        && Objects.equals(minDischargeReactivePower_L2, that.minDischargeReactivePower_L2)
        && Objects.equals(minDischargeReactivePower_L3, that.minDischargeReactivePower_L3)
        && Objects.equals(nominalVoltage, that.nominalVoltage)
        && Objects.equals(nominalVoltageOffset, that.nominalVoltageOffset)
        && Objects.equals(maxNominalVoltage, that.maxNominalVoltage)
        && Objects.equals(minNominalVoltage, that.minNominalVoltage)
        && Objects.equals(evInverterManufacturer, that.evInverterManufacturer)
        && Objects.equals(evInverterModel, that.evInverterModel)
        && Objects.equals(evInverterSerialNumber, that.evInverterSerialNumber)
        && Objects.equals(evInverterSwVersion, that.evInverterSwVersion)
        && Objects.equals(evInverterHwVersion, that.evInverterHwVersion)
        && Arrays.equals(evIslandingDetectionMethod, that.evIslandingDetectionMethod)
        && Objects.equals(evIslandingTripTime, that.evIslandingTripTime)
        && Objects.equals(evMaximumLevel1DCInjection, that.evMaximumLevel1DCInjection)
        && Objects.equals(evDurationLevel1DCInjection, that.evDurationLevel1DCInjection)
        && Objects.equals(evMaximumLevel2DCInjection, that.evMaximumLevel2DCInjection)
        && Objects.equals(evDurationLevel2DCInjection, that.evDurationLevel2DCInjection)
        && Objects.equals(evReactiveSusceptance, that.evReactiveSusceptance)
        && Objects.equals(
            evSessionTotalDischargeEnergyAvailable, that.evSessionTotalDischargeEnergyAvailable)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        Arrays.hashCode(evSupportedDERControl),
        evOverExcitedMaxDischargePower,
        evOverExcitedPowerFactor,
        evUnderExcitedMaxDischargePower,
        evUnderExcitedPowerFactor,
        maxApparentPower,
        maxChargeApparentPower,
        maxChargeApparentPower_L2,
        maxChargeApparentPower_L3,
        maxDischargeApparentPower,
        maxDischargeApparentPower_L2,
        maxDischargeApparentPower_L3,
        maxChargeReactivePower,
        maxChargeReactivePower_L2,
        maxChargeReactivePower_L3,
        minChargeReactivePower,
        minChargeReactivePower_L2,
        minChargeReactivePower_L3,
        maxDischargeReactivePower,
        maxDischargeReactivePower_L2,
        maxDischargeReactivePower_L3,
        minDischargeReactivePower,
        minDischargeReactivePower_L2,
        minDischargeReactivePower_L3,
        nominalVoltage,
        nominalVoltageOffset,
        maxNominalVoltage,
        minNominalVoltage,
        evInverterManufacturer,
        evInverterModel,
        evInverterSerialNumber,
        evInverterSwVersion,
        evInverterHwVersion,
        Arrays.hashCode(evIslandingDetectionMethod),
        evIslandingTripTime,
        evMaximumLevel1DCInjection,
        evDurationLevel1DCInjection,
        evMaximumLevel2DCInjection,
        evDurationLevel2DCInjection,
        evReactiveSusceptance,
        evSessionTotalDischargeEnergyAvailable,
        customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("evSupportedDERControl", evSupportedDERControl)
        .add("evOverExcitedMaxDischargePower", evOverExcitedMaxDischargePower)
        .add("evOverExcitedPowerFactor", evOverExcitedPowerFactor)
        .add("evUnderExcitedMaxDischargePower", evUnderExcitedMaxDischargePower)
        .add("evUnderExcitedPowerFactor", evUnderExcitedPowerFactor)
        .add("maxApparentPower", maxApparentPower)
        .add("maxChargeApparentPower", maxChargeApparentPower)
        .add("maxChargeApparentPower_L2", maxChargeApparentPower_L2)
        .add("maxChargeApparentPower_L3", maxChargeApparentPower_L3)
        .add("maxDischargeApparentPower", maxDischargeApparentPower)
        .add("maxDischargeApparentPower_L2", maxDischargeApparentPower_L2)
        .add("maxDischargeApparentPower_L3", maxDischargeApparentPower_L3)
        .add("maxChargeReactivePower", maxChargeReactivePower)
        .add("maxChargeReactivePower_L2", maxChargeReactivePower_L2)
        .add("maxChargeReactivePower_L3", maxChargeReactivePower_L3)
        .add("minChargeReactivePower", minChargeReactivePower)
        .add("minChargeReactivePower_L2", minChargeReactivePower_L2)
        .add("minChargeReactivePower_L3", minChargeReactivePower_L3)
        .add("maxDischargeReactivePower", maxDischargeReactivePower)
        .add("maxDischargeReactivePower_L2", maxDischargeReactivePower_L2)
        .add("maxDischargeReactivePower_L3", maxDischargeReactivePower_L3)
        .add("minDischargeReactivePower", minDischargeReactivePower)
        .add("minDischargeReactivePower_L2", minDischargeReactivePower_L2)
        .add("minDischargeReactivePower_L3", minDischargeReactivePower_L3)
        .add("nominalVoltage", nominalVoltage)
        .add("nominalVoltageOffset", nominalVoltageOffset)
        .add("maxNominalVoltage", maxNominalVoltage)
        .add("minNominalVoltage", minNominalVoltage)
        .add("evInverterManufacturer", evInverterManufacturer)
        .add("evInverterModel", evInverterModel)
        .add("evInverterSerialNumber", evInverterSerialNumber)
        .add("evInverterSwVersion", evInverterSwVersion)
        .add("evInverterHwVersion", evInverterHwVersion)
        .add("evIslandingDetectionMethod", evIslandingDetectionMethod)
        .add("evIslandingTripTime", evIslandingTripTime)
        .add("evMaximumLevel1DCInjection", evMaximumLevel1DCInjection)
        .add("evDurationLevel1DCInjection", evDurationLevel1DCInjection)
        .add("evMaximumLevel2DCInjection", evMaximumLevel2DCInjection)
        .add("evDurationLevel2DCInjection", evDurationLevel2DCInjection)
        .add("evReactiveSusceptance", evReactiveSusceptance)
        .add("evSessionTotalDischargeEnergyAvailable", evSessionTotalDischargeEnergyAvailable)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
