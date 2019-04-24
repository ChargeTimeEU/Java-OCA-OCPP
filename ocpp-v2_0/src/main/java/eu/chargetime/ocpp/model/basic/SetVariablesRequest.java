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

import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.basic.types.SetVariableDataType;
import eu.chargetime.ocpp.model.validation.RequiredValidator;
import eu.chargetime.ocpp.model.validation.Validator;
import eu.chargetime.ocpp.utilities.MoreObjects;
import java.util.Arrays;
import java.util.Objects;

/**
 * This contains the field definition of the SetVariablesRequest PDU sent by the CSMS to the
 * Charging Station.
 */
public class SetVariablesRequest implements Request {
  private transient Validator<Object> requiredValidator = new RequiredValidator();
  private SetVariableDataType[] setVariableData;

  @Override
  public boolean transactionRelated() {
    return false;
  }

  @Override
  public boolean validate() {
    return setVariableData != null
        && setVariableData.length > 0
        && Arrays.stream(setVariableData)
            .allMatch(setVariableDataType -> setVariableDataType.validate());
  }

  /**
   * List of Component-Variable pairs and attribute values to set.
   *
   * @param setVariableData {@link SetVariableDataType}
   */
  public void setSetVariableData(SetVariableDataType[] setVariableData) {
    requiredValidator.validate(setVariableData);
    this.setVariableData = setVariableData;
  }

  /**
   * Required. List of Component-Variable pairs and attribute values to set.
   *
   * @return {@link SetVariableDataType}
   */
  public SetVariableDataType[] getSetVariableData() {
    return this.setVariableData;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SetVariablesRequest that = (SetVariablesRequest) o;
    return Arrays.equals(setVariableData, that.setVariableData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(setVariableData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this).add("setVariableData", setVariableData).toString();
  }
}
