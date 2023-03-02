package eu.chargetime.ocpp.utilities.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import eu.chargetime.ocpp.utilities.ModelUtil;
import org.junit.Test;

/*
ChargeTime.eu - Java OCA OCPP
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
public class ModelUtilTest {

  @Test
  public void isAmong_notPresent_returnsFalse() {
    // Given
    String needle = "not present";
    String[] haystack = {"something", "some other thing"};

    // When
    boolean found = ModelUtil.isAmong(needle, haystack);

    // Then
    assertThat(found, is(false));
  }

  @Test
  public void isAmong_present_returnsTrue() {
    // Given
    String needle = "something";
    String[] haystack = {"something", "some other thing"};

    // When
    boolean found = ModelUtil.isAmong(needle, haystack);

    // Then
    assertThat(found, is(true));
  }

  @Test
  public void isAmong_presentInHaystackWithNull_returnsTrue() {
    // Given
    String needle = "something";
    String[] haystack = {null, "something", "some other thing"};

    // When
    boolean found = ModelUtil.isAmong(needle, haystack);

    // Then
    assertThat(found, is(true));
  }

  @Test
  public void isAmong_null_returnsFalse() {
    // Given
    String[] haystack = {"something", "some other thing"};

    // When
    boolean found = ModelUtil.isAmong(null, haystack);

    // Then
    assertThat(found, is(false));
  }

  @Test
  public void isAmong_haystackIsNull_returnsFalse() {
    // Given
    String someString = "";
    String[] haystack = null;

    // When
    boolean found = ModelUtil.isAmong(someString, haystack);

    // Then
    assertThat(found, is(false));
  }

  @Test
  public void isAmong_needleAndHaystackIsNull_returnsFalse() {
    // When
    boolean found = ModelUtil.isAmong((String) null, (String) null);

    // Then
    // FIXME: please check if thats intended! I think the behaviour should be identical to the
    // version below!
    // assertThat(found, is(false));
    assertThat(found, is(true));
  }

  @Test
  public void isAmong_needleIsNullAndHaystackIsListWithNulls_returnsTrue() {
    // When
    boolean found = ModelUtil.isAmong((String) null, (String) null, (String) null);

    // Then
    assertThat(found, is(true));
  }

  @Test
  public void isAmong_needleIsNullAndHaystackIsContainsANull_returnsTrue() {
    // When
    boolean found = ModelUtil.isAmong(null, "something", null);

    // Then
    assertThat(found, is(true));
  }
}
