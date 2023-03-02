package eu.chargetime.ocpp.model.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.model.core.BootNotificationRequest;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/*
ChargeTime.eu - Java-OCA-OCPP
Copyright (C) 2015-2016 Thomas Volden <tv@chargetime.eu>

MIT License

Copyright (C) 2016-2018 Thomas Volden

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
public class BootNotificationRequestTest {

  private static final String EXCEPTION_MESSAGE_TEMPLATE =
      "Validation failed: [Exceeded limit of %s chars]. Current Value: [%s]";

  @Rule public ExpectedException thrownException = ExpectedException.none();

  private BootNotificationRequest request;

  private String stringLength20 = "12345678901234567890";
  private String stringLength21 = "123456789012345678901";
  private String stringLength25 = "1234567890123456789012345";
  private String stringLength26 = "12345678901234567890123456";

  @Before
  public void setUp() {

    request = new BootNotificationRequest();
  }

  @Deprecated
  @Test
  public void setChargeBoxSerialNumber_stringLength25_noExceptions() {
    // When
    request.setChargeBoxSerialNumber(stringLength25);

    // Then
    assertThat(request.getChargeBoxSerialNumber(), equalTo(stringLength25));
  }

  @Deprecated
  @Test
  public void setChargeBoxSerialNumber_stringLength26_throwsPropertyConstraintException() {
    setExpectedPropertyConstraintException(createExpectedExceptionMessage(25, 26));

    request.setChargeBoxSerialNumber(stringLength26);
  }

  private void setExpectedPropertyConstraintException(String expectedExceptionMessage) {
    thrownException.expect(instanceOf(PropertyConstraintException.class));
    thrownException.expectMessage(equalTo(expectedExceptionMessage));
  }

  @Test
  public void setChargePointModel_stringLength20_noExceptions() {
    // When
    request.setChargePointModel(stringLength20);

    // Then
    assertThat(request.getChargePointModel(), equalTo(stringLength20));
  }

  @Test
  public void setChargePointModel_stringLength21_throwsPropertyConstraintException() {
    setExpectedPropertyConstraintException(createExpectedExceptionMessage(20, 21));

    request.setChargePointModel(stringLength21);
  }

  @Test
  public void setChargePointSerialNumber_stringLength25_noExceptions() {
    // When
    request.setChargePointSerialNumber(stringLength25);

    // Then
    assertThat(request.getChargePointSerialNumber(), equalTo(stringLength25));
  }

  @Test
  public void setChargePointSerialNumber_stringLength26_throwsPropertyConstraintException() {
    setExpectedPropertyConstraintException(createExpectedExceptionMessage(25, 26));

    request.setChargePointSerialNumber(stringLength26);
  }

  @Test
  public void setChargePointVendor_stringLength20_noExceptions() {
    // When
    request.setChargePointVendor(stringLength20);

    // Then
    assertThat(request.getChargePointVendor(), equalTo(stringLength20));
  }

  @Test
  public void setChargePointVendor_stringLength21_throwsPropertyConstraintException() {
    setExpectedPropertyConstraintException(createExpectedExceptionMessage(20, 21));

    request.setChargePointVendor(stringLength21);
  }

  @Test
  public void setFirmwareVersion_stringLength50_noException() {
    // Given
    String stringLength50 = "12345678901234567890123456789012345678901234567890";

    // When
    request.setFirmwareVersion(stringLength50);

    // Then
    assertThat(request.getFirmwareVersion(), equalTo(stringLength50));
  }

  @Test
  public void setFirmwareVersion_stringLength51_throwsPropertyConstraintException() {
    setExpectedPropertyConstraintException(createExpectedExceptionMessage(50, 51));

    String stringLength51 = "123456789012345678901234567890123456789012345678901";

    request.setFirmwareVersion(stringLength51);
  }

  @Test
  public void setIccid_stringLength20_noExceptions() {
    // When
    request.setIccid(stringLength20);

    // Then
    assertThat(request.getIccid(), equalTo(stringLength20));
  }

  @Test
  public void setIccid_stringLength21_throwsPropertyConstraintException() {
    setExpectedPropertyConstraintException(createExpectedExceptionMessage(20, 21));

    request.setIccid(stringLength21);
  }

  @Test
  public void setImsi_stringLength20_noExceptions() {
    // When
    request.setImsi(stringLength20);

    // Then
    assertThat(request.getImsi(), equalTo(stringLength20));
  }

  @Test
  public void setImsi_stringLength21_throwsPropertyConstraintException() {
    setExpectedPropertyConstraintException(createExpectedExceptionMessage(20, 21));

    request.setImsi(stringLength21);
  }

  @Test
  public void setMeterSerialNumber_stringLength25_noExceptions() {
    // When
    request.setMeterSerialNumber(stringLength25);

    // Then
    assertThat(request.getMeterSerialNumber(), equalTo(stringLength25));
  }

  @Test
  public void setMeterSerialNumber_stringLength26_throwsPropertyConstraintException() {
    setExpectedPropertyConstraintException(createExpectedExceptionMessage(25, 26));

    request.setMeterSerialNumber(stringLength26);
  }

  @Test
  public void setMeterType_stringLength25_noExceptions() {
    // When
    request.setMeterType(stringLength25);

    // Then
    assertThat(request.getMeterType(), equalTo(stringLength25));
  }

  @Test
  public void setMeterType_stringLength26_throwsPropertyConstraintException() {
    setExpectedPropertyConstraintException(createExpectedExceptionMessage(25, 26));

    request.setMeterType(stringLength26);
  }

  @Test
  public void validate_chargePointVendorAndChargePointModelIsSet_returnsTrue() {
    // Given
    request.setChargePointModel(stringLength20);
    request.setChargePointVendor(stringLength20);

    // When
    boolean isValid = request.validate();

    // Then
    assertThat(isValid, is(true));
  }

  @Test
  public void validate_nothingIsSet_returnsFalse() {
    // When
    boolean isValid = request.validate();

    // Then
    assertThat(isValid, is(false));
  }

  @Test
  public void isTransactionRelated_returnsFalse() {
    // When
    boolean isTransactionRelated = request.transactionRelated();

    // Then
    assertThat(isTransactionRelated, is(false));
  }

  private static String createExpectedExceptionMessage(int maxAllowedLength, int currentLength) {
    return String.format(EXCEPTION_MESSAGE_TEMPLATE, maxAllowedLength, currentLength);
  }
}
