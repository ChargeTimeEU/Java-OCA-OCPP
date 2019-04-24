package eu.chargetime.ocpp.model.basic.types.test;
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

import static eu.chargetime.ocpp.utilities.TestUtilities.aString;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.model.basic.types.SetVariableDataType;
import org.junit.Assert;
import org.junit.Test;

public class SetVariableDataTypeTest {

  @Test(expected = PropertyConstraintException.class)
  public void setAttributeValue_Null_throwsPropertyConstraintException() {
    SetVariableDataType sut = new SetVariableDataType();

    sut.setAttributeValue(null);
  }

  @Test(expected = PropertyConstraintException.class)
  public void setAttributeValue_StringExceeds1000_throwsPropertyConstraintException() {
    String bigString = aString(1001);
    SetVariableDataType sut = new SetVariableDataType();

    sut.setAttributeValue(bigString);
  }

  @Test
  public void setAttributeValue_StringSubceeds1000_safesAttributeValue() {
    String string = aString(1000);
    SetVariableDataType sut = new SetVariableDataType();

    sut.setAttributeValue(string);

    Assert.assertEquals(string, sut.getAttributeValue());
  }

  @Test(expected = PropertyConstraintException.class)
  public void setComponent_Null_throwsPropertyConstraintException() {
    SetVariableDataType sut = new SetVariableDataType();

    sut.setComponent(null);
  }

  @Test(expected = PropertyConstraintException.class)
  public void setVariable_Null_throwsPropertyConstraintException() {
    SetVariableDataType sut = new SetVariableDataType();

    sut.setVariable(null);
  }
}
