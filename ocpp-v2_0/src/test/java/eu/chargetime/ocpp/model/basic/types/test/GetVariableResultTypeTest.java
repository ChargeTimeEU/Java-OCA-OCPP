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

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import eu.chargetime.ocpp.model.basic.types.*;
import org.junit.Assert;
import org.junit.Test;

public class GetVariableResultTypeTest {

  @Test
  public void validate_default_returnsFalse() {
    boolean expected = false;
    GetVariableResultType sut = new GetVariableResultType();

    boolean result = sut.validate();

    Assert.assertEquals(expected, result);
  }

  @Test
  public void validate_requiredSetAndStatusNotAccepted_returnTrue() {
    boolean expected = true;
    GetVariableResultType sut = createSut();
    sut.setAttributeStatus(GetVariableStatusEnumType.Rejected);

    boolean result = sut.validate();

    Assert.assertEquals(expected, result);
  }

  @Test
  public void validate_StatusAcceptedValueNull_returnFalse() {
    boolean expected = false;
    GetVariableResultType sut = createSut();
    sut.setAttributeStatus(GetVariableStatusEnumType.Accepted);

    boolean result = sut.validate();

    Assert.assertEquals(expected, result);
  }

  @Test
  public void validate_StatusAcceptedValueSet_returnTrue() {
    boolean expected = true;
    GetVariableResultType sut = createSut();
    sut.setAttributeStatus(GetVariableStatusEnumType.Accepted);
    sut.setAttributeValue("Some value");

    boolean result = sut.validate();

    Assert.assertEquals(expected, result);
  }

  private GetVariableResultType createSut() {
    ComponentType componentTypeStub = mock(ComponentType.class);
    when(componentTypeStub.validate()).thenReturn(true);
    VariableType variableTypeStub = mock(VariableType.class);
    when(variableTypeStub.validate()).thenReturn(true);

    GetVariableResultType sut = new GetVariableResultType();
    sut.setComponent(componentTypeStub);
    sut.setVariable(variableTypeStub);

    return sut;
  }
}
