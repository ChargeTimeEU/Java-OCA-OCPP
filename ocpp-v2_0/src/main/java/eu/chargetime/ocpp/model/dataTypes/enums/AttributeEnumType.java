package eu.chargetime.ocpp.model.dataTypes.enums;
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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import eu.chargetime.ocpp.model.dataTypes.GetVariableDataType;
import eu.chargetime.ocpp.model.dataTypes.SetVariableDataType;

import static eu.chargetime.ocpp.util.EnumUtil.findByField;

/** AttributeEnumType is used by {@link SetVariableDataType}, {@link GetVariableDataType} */
public enum AttributeEnumType {
  /** The actual value of the variable. */
  ACTUAL("Actual"),

  /** The target value for this variable. */
  TARGET("Target"),

  /** The minimal allowed value for this variable. */
  MIN_SET("MinSet"),

  /** The maximum allowed value for this variable. */
  MAX_SET("MaxSet");

  private final String value;

  AttributeEnumType(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return this.value;
  }

  @JsonValue
  public String value() {
    return this.value;
  }

  @JsonCreator
  public static AttributeEnumType fromValue(String value) {
    return findByField(
            AttributeEnumType.class,
            AttributeEnumType::value,
            value
    );
  }
}
