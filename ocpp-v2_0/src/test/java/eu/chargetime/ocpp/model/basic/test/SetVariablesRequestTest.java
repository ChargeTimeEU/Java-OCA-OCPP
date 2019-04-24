package eu.chargetime.ocpp.model.basic.test;
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

import static eu.chargetime.ocpp.utilities.TestUtilities.aList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.model.basic.SetVariablesRequest;
import eu.chargetime.ocpp.model.basic.types.SetVariableDataType;
import org.junit.Assert;
import org.junit.Test;

public class SetVariablesRequestTest {

  @Test(expected = PropertyConstraintException.class)
  public void setSetVariableData_Null_ThrowsPropertyConstraintException() {
    SetVariablesRequest sut = new SetVariablesRequest();

    sut.setSetVariableData(null);
  }

  @Test
  public void validate_default_returnsFalse() {
    boolean expected = false;
    SetVariablesRequest sut = new SetVariablesRequest();

    boolean result = sut.validate();

    Assert.assertEquals(expected, result);
  }

  @Test
  public void validate_SetVariableDataIsSet_returnsTrue() {
    boolean expected = true;
    SetVariableDataType setVariableDataTypeStub = mock(SetVariableDataType.class);
    when(setVariableDataTypeStub.validate()).thenReturn(true);

    SetVariablesRequest sut = new SetVariablesRequest();
    sut.setSetVariableData(aList(setVariableDataTypeStub));

    boolean result = sut.validate();

    Assert.assertEquals(expected, result);
  }
}
