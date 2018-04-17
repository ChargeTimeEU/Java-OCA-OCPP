package eu.chargetime.ocpp.model.firmware.test;
/*
    ChargeTime.eu - Java-OCA-OCPP

    MIT License

    Copyright (C) 2016-2018 Thomas Volden <tv@chargetime.eu>
    Copyright (C) 2018 Mikhail Kladkevich <kladmv@ecp-share.com>

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

import eu.chargetime.ocpp.model.firmware.UpdateFirmwareRequest;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class UpdateFirmwareRequestTest {

    private UpdateFirmwareRequest request;

    @Before
    public void setup() {
        request = new UpdateFirmwareRequest();
    }

    @Test
    public void validate_returnFalse() {
        // When
        boolean result = request.validate();

        // Then
        assertThat(result, is(false));
    }

    @Test
    public void validate_locationOnlyIsSet_returnsFalse() {
        // Given
        String aLocation = "/";
        request.setLocation(aLocation);

        // When
        boolean result = request.validate();

        // Then
        assertThat(result, is(false));
    }

    @Test
    public void validate_retrieveDateOnlyIsSet_returnsFalse() {
        // Given
        Calendar aRetrieveDate = Calendar.getInstance();
        request.setRetrieveDate(aRetrieveDate);

        // When
        boolean result = request.validate();

        // Then
        assertThat(result, is(false));
    }

    @Test
    public void validate_locationAndRetrieveDateIsSet_returnsTrue() {
        // Given
        String aLocation = "/";
        Calendar aRetrieveDate = Calendar.getInstance();
        request.setLocation(aLocation);
        request.setRetrieveDate(aRetrieveDate);

        // When
        boolean result = request.validate();

        // Then
        assertThat(result, is(true));
    }

}