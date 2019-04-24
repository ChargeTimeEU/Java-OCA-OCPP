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
import eu.chargetime.ocpp.model.basic.types.SetVariableResultType;
import eu.chargetime.ocpp.model.validation.RequiredValidator;
import eu.chargetime.ocpp.model.validation.Validator;
import eu.chargetime.ocpp.utilities.MoreObjects;
import java.util.Arrays;
import java.util.Objects;

/**
 * This contains the field definition of the SetVariablesResponse PDU sent by the Charging Station
 * to the CSMS in response to a SetVariablesRequest.
 */
public class SetVariablesConfirmation implements Confirmation {
  private transient Validator<Object> requiredValidator = new RequiredValidator();

  private SetVariableResultType[] setVariableResult;

  /**
   * List of result statuses per Component-Variable.
   *
   * @return SetVariableResultType[]
   */
  public SetVariableResultType[] getSetVariableResult() {
    return setVariableResult;
  }

  /**
   * Required. List of result statuses per Component-Variable.
   *
   * @param setVariableResult SetVariableResultType[]
   */
  public void setSetVariableResult(SetVariableResultType[] setVariableResult) {
    requiredValidator.validate(setVariableResult);
    this.setVariableResult = setVariableResult;
  }

  @Override
  public boolean validate() {
    return setVariableResult != null
        && setVariableResult.length > 0
        && Arrays.stream(setVariableResult)
            .allMatch(setVariableResult -> setVariableResult.validate());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SetVariablesConfirmation that = (SetVariablesConfirmation) o;
    return Arrays.equals(setVariableResult, that.setVariableResult);
  }

  @Override
  public int hashCode() {
    return Objects.hash(setVariableResult);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this).add("setVariableResult", setVariableResult).toString();
  }
}
