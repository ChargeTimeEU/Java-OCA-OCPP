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

package eu.chargetime.ocpp.v201.model.messages;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.utilities.MoreObjects;
import eu.chargetime.ocpp.v201.model.types.CustomData;
import eu.chargetime.ocpp.v201.model.types.SetMonitoringResult;
import java.util.Arrays;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * SetVariableMonitoringResponse
 *
 * <p>OCPP 2.0.1 FINAL
 */
public final class SetVariableMonitoringResponse extends Confirmation {
  /** Custom data */
  @Nullable private CustomData customData;

  /** Class to hold result of SetVariableMonitoring request. */
  private SetMonitoringResult[] setMonitoringResult;

  /**
   * Constructor for the SetVariableMonitoringResponse class
   *
   * @param setMonitoringResult Class to hold result of SetVariableMonitoring request.
   */
  public SetVariableMonitoringResponse(SetMonitoringResult[] setMonitoringResult) {
    setSetMonitoringResult(setMonitoringResult);
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
  public SetVariableMonitoringResponse withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  /**
   * Gets class to hold result of SetVariableMonitoring request.
   *
   * @return Class to hold result of SetVariableMonitoring request
   */
  public SetMonitoringResult[] getSetMonitoringResult() {
    return setMonitoringResult;
  }

  /**
   * Sets class to hold result of SetVariableMonitoring request.
   *
   * @param setMonitoringResult Class to hold result of SetVariableMonitoring request
   */
  public void setSetMonitoringResult(SetMonitoringResult[] setMonitoringResult) {
    if (!isValidSetMonitoringResult(setMonitoringResult)) {
      throw new PropertyConstraintException(setMonitoringResult, "setMonitoringResult is invalid");
    }
    this.setMonitoringResult = setMonitoringResult;
  }

  /**
   * Returns whether the given setMonitoringResult is valid
   *
   * @param setMonitoringResult the setMonitoringResult to check the validity of
   * @return {@code true} if setMonitoringResult is valid, {@code false} if not
   */
  private boolean isValidSetMonitoringResult(SetMonitoringResult[] setMonitoringResult) {
    return setMonitoringResult != null
        && setMonitoringResult.length >= 1
        && Arrays.stream(setMonitoringResult).allMatch(item -> item.validate());
  }

  @Override
  public boolean validate() {
    return isValidCustomData(customData) && isValidSetMonitoringResult(setMonitoringResult);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SetVariableMonitoringResponse that = (SetVariableMonitoringResponse) o;
    return Objects.equals(customData, that.customData)
        && Arrays.equals(setMonitoringResult, that.setMonitoringResult);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customData, Arrays.hashCode(setMonitoringResult));
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("setMonitoringResult", setMonitoringResult)
        .add("isValid", validate())
        .toString();
  }
}
