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
import eu.chargetime.ocpp.model.dataTypes.GetVariableResultType;

import static eu.chargetime.ocpp.util.EnumUtil.findByField;

/** GetVariableStatusEnumType is used by {@link GetVariableResultType} */
public enum GetVariableStatusEnumType {
  /** Variable successfully set. */
  ACCEPTED("Accepted"),

  /** Request is rejected. */
  REJECTED("Rejected"),
  /** Component is not known. */
  UNKNOWN_COMPONENT("UnknownComponent"),

  /** Variable is not known. */
  UNKNOWN_VARIABLE("UnknownVariable"),

  /** The AttributeType is not supported. */
  NOT_SUPPORTED_ATTRIBUTE_TYPE("NotSupportedAttributeType");

  private final String value;

  GetVariableStatusEnumType(String value) {
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
  public static GetVariableStatusEnumType fromValue(String value) {
    return findByField(
            GetVariableStatusEnumType.class,
            GetVariableStatusEnumType::value,
            value
    );
  }
}
