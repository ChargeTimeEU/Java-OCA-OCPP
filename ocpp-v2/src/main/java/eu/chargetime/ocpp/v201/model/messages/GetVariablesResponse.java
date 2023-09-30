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
import eu.chargetime.ocpp.v201.model.types.GetVariableResult;
import java.util.Arrays;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * GetVariablesResponse
 *
 * <p>OCPP 2.0.1 FINAL
 */
public final class GetVariablesResponse extends Confirmation {
  /** Custom data */
  @Nullable private CustomData customData;

  /** Class to hold results of GetVariables request. */
  private GetVariableResult[] getVariableResult;

  /**
   * Constructor for the GetVariablesResponse class
   *
   * @param getVariableResult Class to hold results of GetVariables request.
   */
  public GetVariablesResponse(GetVariableResult[] getVariableResult) {
    setGetVariableResult(getVariableResult);
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
  public GetVariablesResponse withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  /**
   * Gets class to hold results of GetVariables request.
   *
   * @return Class to hold results of GetVariables request
   */
  public GetVariableResult[] getGetVariableResult() {
    return getVariableResult;
  }

  /**
   * Sets class to hold results of GetVariables request.
   *
   * @param getVariableResult Class to hold results of GetVariables request
   */
  public void setGetVariableResult(GetVariableResult[] getVariableResult) {
    if (!isValidGetVariableResult(getVariableResult)) {
      throw new PropertyConstraintException(getVariableResult, "getVariableResult is invalid");
    }
    this.getVariableResult = getVariableResult;
  }

  /**
   * Returns whether the given getVariableResult is valid
   *
   * @param getVariableResult the getVariableResult to check the validity of
   * @return {@code true} if getVariableResult is valid, {@code false} if not
   */
  private boolean isValidGetVariableResult(GetVariableResult[] getVariableResult) {
    return getVariableResult != null
        && getVariableResult.length >= 1
        && Arrays.stream(getVariableResult).allMatch(item -> item.validate());
  }

  @Override
  public boolean validate() {
    return isValidCustomData(customData) && isValidGetVariableResult(getVariableResult);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetVariablesResponse that = (GetVariablesResponse) o;
    return Objects.equals(customData, that.customData)
        && Arrays.equals(getVariableResult, that.getVariableResult);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customData, Arrays.hashCode(getVariableResult));
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("getVariableResult", getVariableResult)
        .add("isValid", validate())
        .toString();
  }
}
