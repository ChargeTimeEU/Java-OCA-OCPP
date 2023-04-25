package extrawest.ocpp.model.basic;
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

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import extrawest.ocpp.PropertyConstraintException;
import extrawest.ocpp.model.response.SetVariablesResponse;
import extrawest.ocpp.model.dataTypes.SetVariableResultType;
import extrawest.ocpp.utilities.TestUtilities;
import org.junit.Assert;
import org.junit.Test;

public class SetVariablesResponseTest {

  @Test(expected = PropertyConstraintException.class)
  public void setSetVariableResult_Null_ThrowsPropertyConstraintException() {
    SetVariablesResponse sut = new SetVariablesResponse();

    sut.setSetVariableResult(null);
  }

  @Test
  public void validate_default_returnsFalse() {
    boolean expected = false;
    SetVariablesResponse sut = new SetVariablesResponse();

    boolean result = sut.validate();

    Assert.assertEquals(expected, result);
  }

  @Test
  public void validate_SetVariableResultIsSet_returnsTrue() {
    boolean expected = true;
    SetVariableResultType setVariableResultTypeStub = mock(SetVariableResultType.class);
    when(setVariableResultTypeStub.validate()).thenReturn(true);
    SetVariablesResponse sut = new SetVariablesResponse();
    sut.setSetVariableResult(TestUtilities.aList(setVariableResultTypeStub));

    boolean result = sut.validate();

    Assert.assertEquals(expected, result);
  }
}
