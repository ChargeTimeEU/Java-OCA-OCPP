package eu.chargetime.ocpp.model.basic;
/*
   ChargeTime.eu - Java-OCA-OCPP

   MIT License

   Copyright (C) 2018 Thomas Volden <tv@chargetime.eu>

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

import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.basic.types.GetVariableResultType;
import eu.chargetime.ocpp.utilities.MoreObjects;
import java.util.Arrays;
import java.util.Objects;

public class GetVariablesConfirmation implements Confirmation {

  private GetVariableResultType[] getVariableResult;

  /**
   * List of requested variables and their values.
   *
   * @return {@link GetVariableResultType}
   */
  public GetVariableResultType[] getGetVariableResult() {
    return getVariableResult;
  }

  /**
   * Required. List of requested variables and their values.
   *
   * @param getVariableResult {@link GetVariableResultType}
   */
  public void setGetVariableResult(GetVariableResultType[] getVariableResult) {
    this.getVariableResult = getVariableResult;
  }

  @Override
  public boolean validate() {
    return getVariableResult != null
        && getVariableResult.length > 0
        && Arrays.stream(getVariableResult)
            .allMatch(getVariableResult -> getVariableResult.validate());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    GetVariablesConfirmation that = (GetVariablesConfirmation) o;
    return Arrays.equals(getVariableResult, that.getVariableResult);
  }

  @Override
  public int hashCode() {
    return Objects.hash(getVariableResult);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this).add("getVariableResult", getVariableResult).toString();
  }
}
