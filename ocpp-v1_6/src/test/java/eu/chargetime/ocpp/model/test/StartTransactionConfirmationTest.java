package eu.chargetime.ocpp.model.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

import eu.chargetime.ocpp.model.core.IdTagInfo;
import eu.chargetime.ocpp.model.core.StartTransactionConfirmation;
import org.junit.Before;
import org.junit.Test;

/*
 * ChargeTime.eu - Java-OCA-OCPP
 *
 * MIT License
 *
 * Copyright (C) 2016-2018 Thomas Volden <tv@chargetime.eu>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
public class StartTransactionConfirmationTest {
  StartTransactionConfirmation confirmation;

  @Before
  public void setUp() throws Exception {
    confirmation = new StartTransactionConfirmation();
  }

  @Test
  public void setIdTagInfo_anIdTagInfo_idTagInfoIsSet() {
    // Given
    IdTagInfo idTagInfo = mock(IdTagInfo.class);

    // When
    confirmation.setIdTagInfo(idTagInfo);

    // Then
    assertThat(confirmation.getIdTagInfo(), equalTo(idTagInfo));
  }

  @Test
  public void setTransactionId_anInteger_transactionIdIsSet() {
    // Given
    Integer anInteger = 42;

    // When
    confirmation.setTransactionId(anInteger);

    // Then
    assertThat(confirmation.getTransactionId(), equalTo(anInteger));
  }

  @Test
  public void validate_returnFalse() {
    // When
    boolean isValid = confirmation.validate();

    // Then
    assertThat(isValid, is(false));
  }

  @Test
  public void validate_idTagInfoIsNotSetAndTransactionIdIsSet_returnFalse() {
    // Given
    confirmation.setTransactionId(42);

    // When
    boolean isValid = confirmation.validate();

    // Then
    assertThat(isValid, is(false));
  }

  @Test
  public void validate_idTagInfoIsSetAndTransactionIdIsNotSet_returnFalse() {
    // Given
    IdTagInfo idTagInfo = mock(IdTagInfo.class);
    when(idTagInfo.validate()).thenReturn(true);
    confirmation.setIdTagInfo(idTagInfo);

    // When
    boolean isValid = confirmation.validate();

    // Then
    assertThat(isValid, is(false));
  }

  @Test
  public void validate_idTagInfoAndTransactionIdIsSet_idTagInfoIsValidated() {
    // Given
    confirmation.setTransactionId(42);
    IdTagInfo idTagInfo = mock(IdTagInfo.class);
    confirmation.setIdTagInfo(idTagInfo);

    // When
    confirmation.validate();

    // Then
    verify(idTagInfo, times(1)).validate();
  }

  @Test
  public void validate_idTagInfoAndTransactionIdIsSet_returnTrue() {
    // Given
    confirmation.setTransactionId(42);
    IdTagInfo idTagInfo = mock(IdTagInfo.class);
    when(idTagInfo.validate()).thenReturn(true);
    confirmation.setIdTagInfo(idTagInfo);

    // When
    boolean isValid = confirmation.validate();

    // Then
    assertThat(isValid, is(true));
  }
}
