package eu.chargetime.ocpp.model.validation.test;
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

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.model.validation.IdentifierStringValidationRule;
import org.junit.Test;

public class IdentifierStringValidationRuleTest {

  private IdentifierStringValidationRule createSut() {
    return new IdentifierStringValidationRule();
  }

  @Test
  public void validate_legalString_nothingHappens() {
    String legalString = "abc123ABC_";
    IdentifierStringValidationRule sut = createSut();
    sut.validate(legalString);
  }

  @Test(expected = PropertyConstraintException.class)
  public void validate_illegalString_throwsException() {
    String legalString = "a%c1$3A!C";
    IdentifierStringValidationRule sut = createSut();
    sut.validate(legalString);
  }
}
