package eu.chargetime.ocpp.model.basic.types;
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
import org.junit.Assert;
import org.junit.Test;

public class ComponentTypeTest {

  @Test(expected = PropertyConstraintException.class)
  public void setName_Null_throwsPropertyConstraintException() {
    VariableType sut = new VariableType();

    sut.setName(null);
  }

  @Test(expected = PropertyConstraintException.class)
  public void setName_StringExceeds50_throwsPropertyConstraintException() {
    String bitString = aString(51);
    VariableType sut = new VariableType();

    sut.setName(bitString);
  }

  @Test
  public void setName_StringIs50_nameIsSaved() {
    String aString = aString(50);
    VariableType sut = new VariableType();

    sut.setName(aString);

    Assert.assertEquals(aString, sut.getName());
  }

  @Test(expected = PropertyConstraintException.class)
  public void setInstance_StringExceeds50_throwsPropertyConstraintException() {
    String bitString = aString(51);
    VariableType sut = new VariableType();

    sut.setInstance(bitString);
  }

  @Test
  public void setInstance_StringIs50_nameIsSaved() {
    String aString = aString(50);
    VariableType sut = new VariableType();

    sut.setInstance(aString);

    Assert.assertEquals(aString, sut.getInstance());
  }
}
