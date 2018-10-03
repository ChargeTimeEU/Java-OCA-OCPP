package eu.chargetime.ocpp.model.test;

import eu.chargetime.ocpp.model.core.IdTagInfo;
import eu.chargetime.ocpp.model.core.StopTransactionConfirmation;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
public class StopTransactionConfirmationTest {
    StopTransactionConfirmation confirmation;

    @Before
    public void setUp() throws Exception {
        confirmation = new StopTransactionConfirmation();
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
    public void validate_returnTrue() {
        // When
        boolean isValid = confirmation.validate();

        // Then
        assertThat(isValid, is(true));
    }

    @Test
    public void validate_idTagInfoIsSet_idTagInfoIsValidated() {
        // Given
        IdTagInfo idTagInfo = mock(IdTagInfo.class);
        confirmation.setIdTagInfo(idTagInfo);

        // When
        confirmation.validate();

        // Then
        verify(idTagInfo, times(1)).validate();
    }

    @Test
    public void validate_idTagInfoNotValid_returnFalse() {
        // Given
        IdTagInfo idTagInfo = mock(IdTagInfo.class);
        confirmation.setIdTagInfo(idTagInfo);

        // When
        when(idTagInfo.validate()).thenReturn(false);
        boolean isValid = confirmation.validate();

        // Then
        assertThat(isValid, is(false));
    }
}