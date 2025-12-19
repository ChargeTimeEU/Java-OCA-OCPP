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

/** ChargingNeedsType */
public final class ChargingNeeds {
  /** EV AC charging parameters for ISO 15118-2 */
  @Nullable private ACChargingParameters acChargingParameters;

  /**
   * DERChargingParametersType is used in ChargingNeedsType during an ISO 15118-20 session for
   * ACBPTDER to report the inverter settings related to DER control that were agreed between EVSE
   * and EV.
   *
   * <p>Fields starting with "ev" contain values from the EV. Other fields contain a value that is
   * supported by both EV and EVSE.
   *
   * <p>DERChargingParametersType type is only relevant in case of an ISO 15118-20 ACBPTDER/ACDER
   * charging session.
   *
   * <p>NOTE: All these fields have values greater or equal to zero (i.e. are non-negative)
   */
  @Nullable private DERChargingParameters derChargingParameters;

  /**
   * A schedule of the energy amount over time that EV is willing to discharge. A negative value
   * indicates the willingness to discharge under specific conditions, a positive value indicates
   * that the EV currently is not able to offer energy to discharge.
   */
  @Nullable private EVEnergyOffer evEnergyOffer;

  /** Mode of energy transfer requested by the EV. */
  private EnergyTransferModeEnum requestedEnergyTransfer;

  /** EV DC charging parameters for ISO 15118-2 */
  @Nullable private DCChargingParameters dcChargingParameters;

  /**
   * Charging parameters for ISO 15118-20, also supporting V2X charging/discharging.+ All values are
   * greater or equal to zero, with the exception of EVMinEnergyRequest, EVMaxEnergyRequest,
   * EVTargetEnergyRequest, EVMinV2XEnergyRequest and EVMaxV2XEnergyRequest.
   */
  @Nullable private V2XChargingParameters v2xChargingParameters;

  /** Modes of energy transfer that are marked as available by EV. */
  @Nullable private EnergyTransferModeEnum[] availableEnergyTransfer;

  /**
   * Whether EV wants to operate in Dynamic or Scheduled mode. When absent, Scheduled mode is
   * assumed for backwards compatibility.
   *
   * <pre>
   * *ISO 15118-20:*
   * ServiceSelectionReq(SelectedEnergyTransferService)
   * </pre>
   */
  @Nullable private ControlModeEnum controlMode;

  /**
   * Value of EVCC indicates that EV determines min/target SOC and departure time.
   *
   * <pre>
   * A value of EVCCSECC indicates that charging station or CSMS may also update min/target SOC and
   * departure time.
   * *ISO 15118-20:*
   * ServiceSelectionReq(SelectedEnergyTransferService)
   * </pre>
   */
  @Nullable private MobilityNeedsModeEnum mobilityNeedsMode;

  /**
   * Estimated departure time of the EV.
   *
   * <pre>
   * *ISO 15118-2:* AC/DCEVChargeParameterType: DepartureTime
   * *ISO 15118-20:* Dynamic/ScheduledSEReqControlModeType: DepartureTIme
   * </pre>
   */
  @Nullable private ZonedDateTime departureTime;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the ChargingNeeds class
   *
   * @param requestedEnergyTransfer Mode of energy transfer requested by the EV.
   */
  public ChargingNeeds(EnergyTransferModeEnum requestedEnergyTransfer) {
    setRequestedEnergyTransfer(requestedEnergyTransfer);
  }

  /**
   * Gets EV AC charging parameters for ISO 15118-2
   *
   * @return EV AC charging parameters for ISO 15118-2
   */
  @Nullable
  public ACChargingParameters getAcChargingParameters() {
    return acChargingParameters;
  }

  /**
   * Sets EV AC charging parameters for ISO 15118-2
   *
   * @param acChargingParameters EV AC charging parameters for ISO 15118-2
   */
  public void setAcChargingParameters(@Nullable ACChargingParameters acChargingParameters) {
    if (!isValidAcChargingParameters(acChargingParameters)) {
      throw new PropertyConstraintException(
          acChargingParameters, "acChargingParameters is invalid");
    }
    this.acChargingParameters = acChargingParameters;
  }

  /**
   * Returns whether the given acChargingParameters is valid
   *
   * @param acChargingParameters the acChargingParameters to check the validity of
   * @return {@code true} if acChargingParameters is valid, {@code false} if not
   */
  private boolean isValidAcChargingParameters(@Nullable ACChargingParameters acChargingParameters) {
    return acChargingParameters == null || acChargingParameters.validate();
  }

  /**
   * Adds EV AC charging parameters for ISO 15118-2
   *
   * @param acChargingParameters EV AC charging parameters for ISO 15118-2
   * @return this
   */
  public ChargingNeeds withAcChargingParameters(
      @Nullable ACChargingParameters acChargingParameters) {
    setAcChargingParameters(acChargingParameters);
    return this;
  }

  /**
   * Gets DERChargingParametersType is used in ChargingNeedsType during an ISO 15118-20 session for
   * ACBPTDER to report the inverter settings related to DER control that were agreed between EVSE
   * and EV.
   *
   * @return DERChargingParametersType is used in ChargingNeedsType during an ISO 15118-20 session
   *     for ACBPTDER to report the inverter settings related to DER control that were agreed
   *     between EVSE and EV
   */
  @Nullable
  public DERChargingParameters getDerChargingParameters() {
    return derChargingParameters;
  }

  /**
   * Sets DERChargingParametersType is used in ChargingNeedsType during an ISO 15118-20 session for
   * ACBPTDER to report the inverter settings related to DER control that were agreed between EVSE
   * and EV.
   *
   * @param derChargingParameters DERChargingParametersType is used in ChargingNeedsType during an
   *     ISO 15118-20 session for ACBPTDER to report the inverter settings related to DER control
   *     that were agreed between EVSE and EV
   */
  public void setDerChargingParameters(@Nullable DERChargingParameters derChargingParameters) {
    if (!isValidDerChargingParameters(derChargingParameters)) {
      throw new PropertyConstraintException(
          derChargingParameters, "derChargingParameters is invalid");
    }
    this.derChargingParameters = derChargingParameters;
  }

  /**
   * Returns whether the given derChargingParameters is valid
   *
   * @param derChargingParameters the derChargingParameters to check the validity of
   * @return {@code true} if derChargingParameters is valid, {@code false} if not
   */
  private boolean isValidDerChargingParameters(
      @Nullable DERChargingParameters derChargingParameters) {
    return derChargingParameters == null || derChargingParameters.validate();
  }

  /**
   * Adds DERChargingParametersType is used in ChargingNeedsType during an ISO 15118-20 session for
   * ACBPTDER to report the inverter settings related to DER control that were agreed between EVSE
   * and EV.
   *
   * @param derChargingParameters DERChargingParametersType is used in ChargingNeedsType during an
   *     ISO 15118-20 session for ACBPTDER to report the inverter settings related to DER control
   *     that were agreed between EVSE and EV
   * @return this
   */
  public ChargingNeeds withDerChargingParameters(
      @Nullable DERChargingParameters derChargingParameters) {
    setDerChargingParameters(derChargingParameters);
    return this;
  }

  /**
   * Gets a schedule of the energy amount over time that EV is willing to discharge. A negative
   * value indicates the willingness to discharge under specific conditions, a positive value
   * indicates that the EV currently is not able to offer energy to discharge.
   *
   * @return A schedule of the energy amount over time that EV is willing to discharge
   */
  @Nullable
  public EVEnergyOffer getEvEnergyOffer() {
    return evEnergyOffer;
  }

  /**
   * Sets a schedule of the energy amount over time that EV is willing to discharge. A negative
   * value indicates the willingness to discharge under specific conditions, a positive value
   * indicates that the EV currently is not able to offer energy to discharge.
   *
   * @param evEnergyOffer A schedule of the energy amount over time that EV is willing to discharge
   */
  public void setEvEnergyOffer(@Nullable EVEnergyOffer evEnergyOffer) {
    if (!isValidEvEnergyOffer(evEnergyOffer)) {
      throw new PropertyConstraintException(evEnergyOffer, "evEnergyOffer is invalid");
    }
    this.evEnergyOffer = evEnergyOffer;
  }

  /**
   * Returns whether the given evEnergyOffer is valid
   *
   * @param evEnergyOffer the evEnergyOffer to check the validity of
   * @return {@code true} if evEnergyOffer is valid, {@code false} if not
   */
  private boolean isValidEvEnergyOffer(@Nullable EVEnergyOffer evEnergyOffer) {
    return evEnergyOffer == null || evEnergyOffer.validate();
  }

  /**
   * Adds a schedule of the energy amount over time that EV is willing to discharge. A negative
   * value indicates the willingness to discharge under specific conditions, a positive value
   * indicates that the EV currently is not able to offer energy to discharge.
   *
   * @param evEnergyOffer A schedule of the energy amount over time that EV is willing to discharge
   * @return this
   */
  public ChargingNeeds withEvEnergyOffer(@Nullable EVEnergyOffer evEnergyOffer) {
    setEvEnergyOffer(evEnergyOffer);
    return this;
  }

  /**
   * Gets mode of energy transfer requested by the EV.
   *
   * @return Mode of energy transfer requested by the EV
   */
  public EnergyTransferModeEnum getRequestedEnergyTransfer() {
    return requestedEnergyTransfer;
  }

  /**
   * Sets mode of energy transfer requested by the EV.
   *
   * @param requestedEnergyTransfer Mode of energy transfer requested by the EV
   */
  public void setRequestedEnergyTransfer(EnergyTransferModeEnum requestedEnergyTransfer) {
    if (!isValidRequestedEnergyTransfer(requestedEnergyTransfer)) {
      throw new PropertyConstraintException(
          requestedEnergyTransfer, "requestedEnergyTransfer is invalid");
    }
    this.requestedEnergyTransfer = requestedEnergyTransfer;
  }

  /**
   * Returns whether the given requestedEnergyTransfer is valid
   *
   * @param requestedEnergyTransfer the requestedEnergyTransfer to check the validity of
   * @return {@code true} if requestedEnergyTransfer is valid, {@code false} if not
   */
  private boolean isValidRequestedEnergyTransfer(EnergyTransferModeEnum requestedEnergyTransfer) {
    return requestedEnergyTransfer != null;
  }

  /**
   * Gets EV DC charging parameters for ISO 15118-2
   *
   * @return EV DC charging parameters for ISO 15118-2
   */
  @Nullable
  public DCChargingParameters getDcChargingParameters() {
    return dcChargingParameters;
  }

  /**
   * Sets EV DC charging parameters for ISO 15118-2
   *
   * @param dcChargingParameters EV DC charging parameters for ISO 15118-2
   */
  public void setDcChargingParameters(@Nullable DCChargingParameters dcChargingParameters) {
    if (!isValidDcChargingParameters(dcChargingParameters)) {
      throw new PropertyConstraintException(
          dcChargingParameters, "dcChargingParameters is invalid");
    }
    this.dcChargingParameters = dcChargingParameters;
  }

  /**
   * Returns whether the given dcChargingParameters is valid
   *
   * @param dcChargingParameters the dcChargingParameters to check the validity of
   * @return {@code true} if dcChargingParameters is valid, {@code false} if not
   */
  private boolean isValidDcChargingParameters(@Nullable DCChargingParameters dcChargingParameters) {
    return dcChargingParameters == null || dcChargingParameters.validate();
  }

  /**
   * Adds EV DC charging parameters for ISO 15118-2
   *
   * @param dcChargingParameters EV DC charging parameters for ISO 15118-2
   * @return this
   */
  public ChargingNeeds withDcChargingParameters(
      @Nullable DCChargingParameters dcChargingParameters) {
    setDcChargingParameters(dcChargingParameters);
    return this;
  }

  /**
   * Gets charging parameters for ISO 15118-20, also supporting V2X charging/discharging.+ All
   * values are greater or equal to zero, with the exception of EVMinEnergyRequest,
   * EVMaxEnergyRequest, EVTargetEnergyRequest, EVMinV2XEnergyRequest and EVMaxV2XEnergyRequest.
   *
   * @return Charging parameters for ISO 15118-20, also supporting V2X charging/discharging.+ All
   *     values are greater or equal to zero, with the exception of EVMinEnergyRequest,
   *     EVMaxEnergyRequest, EVTargetEnergyRequest, EVMinV2XEnergyRequest and EVMaxV2XEnergyRequest
   */
  @Nullable
  public V2XChargingParameters getV2xChargingParameters() {
    return v2xChargingParameters;
  }

  /**
   * Sets charging parameters for ISO 15118-20, also supporting V2X charging/discharging.+ All
   * values are greater or equal to zero, with the exception of EVMinEnergyRequest,
   * EVMaxEnergyRequest, EVTargetEnergyRequest, EVMinV2XEnergyRequest and EVMaxV2XEnergyRequest.
   *
   * @param v2xChargingParameters Charging parameters for ISO 15118-20, also supporting V2X
   *     charging/discharging.+ All values are greater or equal to zero, with the exception of
   *     EVMinEnergyRequest, EVMaxEnergyRequest, EVTargetEnergyRequest, EVMinV2XEnergyRequest and
   *     EVMaxV2XEnergyRequest
   */
  public void setV2xChargingParameters(@Nullable V2XChargingParameters v2xChargingParameters) {
    if (!isValidV2xChargingParameters(v2xChargingParameters)) {
      throw new PropertyConstraintException(
          v2xChargingParameters, "v2xChargingParameters is invalid");
    }
    this.v2xChargingParameters = v2xChargingParameters;
  }

  /**
   * Returns whether the given v2xChargingParameters is valid
   *
   * @param v2xChargingParameters the v2xChargingParameters to check the validity of
   * @return {@code true} if v2xChargingParameters is valid, {@code false} if not
   */
  private boolean isValidV2xChargingParameters(
      @Nullable V2XChargingParameters v2xChargingParameters) {
    return v2xChargingParameters == null || v2xChargingParameters.validate();
  }

  /**
   * Adds charging parameters for ISO 15118-20, also supporting V2X charging/discharging.+ All
   * values are greater or equal to zero, with the exception of EVMinEnergyRequest,
   * EVMaxEnergyRequest, EVTargetEnergyRequest, EVMinV2XEnergyRequest and EVMaxV2XEnergyRequest.
   *
   * @param v2xChargingParameters Charging parameters for ISO 15118-20, also supporting V2X
   *     charging/discharging.+ All values are greater or equal to zero, with the exception of
   *     EVMinEnergyRequest, EVMaxEnergyRequest, EVTargetEnergyRequest, EVMinV2XEnergyRequest and
   *     EVMaxV2XEnergyRequest
   * @return this
   */
  public ChargingNeeds withV2xChargingParameters(
      @Nullable V2XChargingParameters v2xChargingParameters) {
    setV2xChargingParameters(v2xChargingParameters);
    return this;
  }

  /**
   * Gets modes of energy transfer that are marked as available by EV.
   *
   * @return Modes of energy transfer that are marked as available by EV
   */
  @Nullable
  public EnergyTransferModeEnum[] getAvailableEnergyTransfer() {
    return availableEnergyTransfer;
  }

  /**
   * Sets modes of energy transfer that are marked as available by EV.
   *
   * @param availableEnergyTransfer Modes of energy transfer that are marked as available by EV
   */
  public void setAvailableEnergyTransfer(
      @Nullable EnergyTransferModeEnum[] availableEnergyTransfer) {
    if (!isValidAvailableEnergyTransfer(availableEnergyTransfer)) {
      throw new PropertyConstraintException(
          availableEnergyTransfer, "availableEnergyTransfer is invalid");
    }
    this.availableEnergyTransfer = availableEnergyTransfer;
  }

  /**
   * Returns whether the given availableEnergyTransfer is valid
   *
   * @param availableEnergyTransfer the availableEnergyTransfer to check the validity of
   * @return {@code true} if availableEnergyTransfer is valid, {@code false} if not
   */
  private boolean isValidAvailableEnergyTransfer(
      @Nullable EnergyTransferModeEnum[] availableEnergyTransfer) {
    return availableEnergyTransfer == null || (availableEnergyTransfer.length >= 1);
  }

  /**
   * Adds modes of energy transfer that are marked as available by EV.
   *
   * @param availableEnergyTransfer Modes of energy transfer that are marked as available by EV
   * @return this
   */
  public ChargingNeeds withAvailableEnergyTransfer(
      @Nullable EnergyTransferModeEnum[] availableEnergyTransfer) {
    setAvailableEnergyTransfer(availableEnergyTransfer);
    return this;
  }

  /**
   * Gets whether EV wants to operate in Dynamic or Scheduled mode. When absent, Scheduled mode is
   * assumed for backwards compatibility.
   *
   * @return Whether EV wants to operate in Dynamic or Scheduled mode
   */
  @Nullable
  public ControlModeEnum getControlMode() {
    return controlMode;
  }

  /**
   * Sets whether EV wants to operate in Dynamic or Scheduled mode. When absent, Scheduled mode is
   * assumed for backwards compatibility.
   *
   * @param controlMode Whether EV wants to operate in Dynamic or Scheduled mode
   */
  public void setControlMode(@Nullable ControlModeEnum controlMode) {
    this.controlMode = controlMode;
  }

  /**
   * Adds whether EV wants to operate in Dynamic or Scheduled mode. When absent, Scheduled mode is
   * assumed for backwards compatibility.
   *
   * @param controlMode Whether EV wants to operate in Dynamic or Scheduled mode
   * @return this
   */
  public ChargingNeeds withControlMode(@Nullable ControlModeEnum controlMode) {
    setControlMode(controlMode);
    return this;
  }

  /**
   * Gets value of EVCC indicates that EV determines min/target SOC and departure time.
   *
   * @return Value of EVCC indicates that EV determines min/target SOC and departure time
   */
  @Nullable
  public MobilityNeedsModeEnum getMobilityNeedsMode() {
    return mobilityNeedsMode;
  }

  /**
   * Sets value of EVCC indicates that EV determines min/target SOC and departure time.
   *
   * @param mobilityNeedsMode Value of EVCC indicates that EV determines min/target SOC and
   *     departure time
   */
  public void setMobilityNeedsMode(@Nullable MobilityNeedsModeEnum mobilityNeedsMode) {
    this.mobilityNeedsMode = mobilityNeedsMode;
  }

  /**
   * Adds value of EVCC indicates that EV determines min/target SOC and departure time.
   *
   * @param mobilityNeedsMode Value of EVCC indicates that EV determines min/target SOC and
   *     departure time
   * @return this
   */
  public ChargingNeeds withMobilityNeedsMode(@Nullable MobilityNeedsModeEnum mobilityNeedsMode) {
    setMobilityNeedsMode(mobilityNeedsMode);
    return this;
  }

  /**
   * Gets estimated departure time of the EV.
   *
   * @return Estimated departure time of the EV
   */
  @Nullable
  public ZonedDateTime getDepartureTime() {
    return departureTime;
  }

  /**
   * Sets estimated departure time of the EV.
   *
   * @param departureTime Estimated departure time of the EV
   */
  public void setDepartureTime(@Nullable ZonedDateTime departureTime) {
    this.departureTime = departureTime;
  }

  /**
   * Adds estimated departure time of the EV.
   *
   * @param departureTime Estimated departure time of the EV
   * @return this
   */
  public ChargingNeeds withDepartureTime(@Nullable ZonedDateTime departureTime) {
    setDepartureTime(departureTime);
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
  public ChargingNeeds withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  public boolean validate() {
    return isValidAcChargingParameters(acChargingParameters)
        && isValidDerChargingParameters(derChargingParameters)
        && isValidEvEnergyOffer(evEnergyOffer)
        && isValidRequestedEnergyTransfer(requestedEnergyTransfer)
        && isValidDcChargingParameters(dcChargingParameters)
        && isValidV2xChargingParameters(v2xChargingParameters)
        && isValidAvailableEnergyTransfer(availableEnergyTransfer)
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
    ChargingNeeds that = (ChargingNeeds) o;
    return Objects.equals(acChargingParameters, that.acChargingParameters)
        && Objects.equals(derChargingParameters, that.derChargingParameters)
        && Objects.equals(evEnergyOffer, that.evEnergyOffer)
        && Objects.equals(requestedEnergyTransfer, that.requestedEnergyTransfer)
        && Objects.equals(dcChargingParameters, that.dcChargingParameters)
        && Objects.equals(v2xChargingParameters, that.v2xChargingParameters)
        && Arrays.equals(availableEnergyTransfer, that.availableEnergyTransfer)
        && Objects.equals(controlMode, that.controlMode)
        && Objects.equals(mobilityNeedsMode, that.mobilityNeedsMode)
        && Objects.equals(departureTime, that.departureTime)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        acChargingParameters,
        derChargingParameters,
        evEnergyOffer,
        requestedEnergyTransfer,
        dcChargingParameters,
        v2xChargingParameters,
        Arrays.hashCode(availableEnergyTransfer),
        controlMode,
        mobilityNeedsMode,
        departureTime,
        customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("acChargingParameters", acChargingParameters)
        .add("derChargingParameters", derChargingParameters)
        .add("evEnergyOffer", evEnergyOffer)
        .add("requestedEnergyTransfer", requestedEnergyTransfer)
        .add("dcChargingParameters", dcChargingParameters)
        .add("v2xChargingParameters", v2xChargingParameters)
        .add("availableEnergyTransfer", availableEnergyTransfer)
        .add("controlMode", controlMode)
        .add("mobilityNeedsMode", mobilityNeedsMode)
        .add("departureTime", departureTime)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
