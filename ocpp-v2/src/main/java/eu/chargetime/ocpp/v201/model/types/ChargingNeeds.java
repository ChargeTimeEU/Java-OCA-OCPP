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

package eu.chargetime.ocpp.v201.model.types;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.utilities.MoreObjects;
import java.time.ZonedDateTime;
import java.util.Objects;
import javax.annotation.Nullable;

/** Charging Needs */
public final class ChargingNeeds {
  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * AC Charging Parameters
   *
   * <p>EV AC charging parameters.
   */
  @Nullable private ACChargingParameters acChargingParameters;

  /**
   * DC Charging Parameters
   *
   * <p>EV DC charging parameters
   */
  @Nullable private DCChargingParameters dcChargingParameters;

  /**
   * Charging Needs. Requested. Energy Transfer Mode Code
   *
   * <p>Mode of energy transfer requested by the EV.
   */
  private EnergyTransferModeEnum requestedEnergyTransfer;

  /**
   * Charging Needs. Departure Time. Date Time
   *
   * <p>Estimated departure time of the EV.
   */
  @Nullable private ZonedDateTime departureTime;

  /**
   * Constructor for the ChargingNeeds class
   *
   * @param requestedEnergyTransfer Mode of energy transfer requested by the EV.
   */
  public ChargingNeeds(EnergyTransferModeEnum requestedEnergyTransfer) {
    setRequestedEnergyTransfer(requestedEnergyTransfer);
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

  /**
   * Gets EV AC charging parameters.
   *
   * @return EV AC charging parameters
   */
  @Nullable
  public ACChargingParameters getAcChargingParameters() {
    return acChargingParameters;
  }

  /**
   * Sets EV AC charging parameters.
   *
   * @param acChargingParameters EV AC charging parameters
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
   * Adds EV AC charging parameters.
   *
   * @param acChargingParameters EV AC charging parameters
   * @return this
   */
  public ChargingNeeds withAcChargingParameters(
      @Nullable ACChargingParameters acChargingParameters) {
    setAcChargingParameters(acChargingParameters);
    return this;
  }

  /**
   * Gets EV DC charging parameters
   *
   * @return EV DC charging parameters
   */
  @Nullable
  public DCChargingParameters getDcChargingParameters() {
    return dcChargingParameters;
  }

  /**
   * Sets EV DC charging parameters
   *
   * @param dcChargingParameters EV DC charging parameters
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
   * Adds EV DC charging parameters
   *
   * @param dcChargingParameters EV DC charging parameters
   * @return this
   */
  public ChargingNeeds withDcChargingParameters(
      @Nullable DCChargingParameters dcChargingParameters) {
    setDcChargingParameters(dcChargingParameters);
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

  public boolean validate() {
    return isValidCustomData(customData)
        && isValidAcChargingParameters(acChargingParameters)
        && isValidDcChargingParameters(dcChargingParameters)
        && isValidRequestedEnergyTransfer(requestedEnergyTransfer);
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
    return Objects.equals(customData, that.customData)
        && Objects.equals(acChargingParameters, that.acChargingParameters)
        && Objects.equals(dcChargingParameters, that.dcChargingParameters)
        && Objects.equals(requestedEnergyTransfer, that.requestedEnergyTransfer)
        && Objects.equals(departureTime, that.departureTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        customData,
        acChargingParameters,
        dcChargingParameters,
        requestedEnergyTransfer,
        departureTime);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("acChargingParameters", acChargingParameters)
        .add("dcChargingParameters", dcChargingParameters)
        .add("requestedEnergyTransfer", requestedEnergyTransfer)
        .add("departureTime", departureTime)
        .add("isValid", validate())
        .toString();
  }
}
