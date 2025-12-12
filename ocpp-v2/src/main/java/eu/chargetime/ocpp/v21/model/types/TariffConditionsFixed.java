/*
   ChargeTime.eu - Java-OCA-OCPP

   MIT License

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

package eu.chargetime.ocpp.v21.model.types;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.utilities.MoreObjects;
import java.util.Arrays;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * These conditions describe if a FixedPrice applies at start of the transaction.
 *
 * <p>When more than one restriction is set, they are to be treated as a logical AND. All need to be
 * valid before this price is active.
 *
 * <p>NOTE: startTimeOfDay and endTimeOfDay are in local time, because it is the time in the tariff
 * as it is shown to the EV driver at the Charging Station. A Charging Station will convert this to
 * the internal time zone that it uses (which is recommended to be UTC, see section Generic chapter
 * 3.1) when performing cost calculation.
 */
public final class TariffConditionsFixed {
  /**
   * Start time of day in local time.
   *
   * <pre>
   * Format as per RFC 3339: time-hour ":" time-minute
   * Must be in 24h format with leading zeros. Hour/Minute separator: ":"
   * </pre>
   *
   * Regex: ([0-1][0-9]\|2[0-3]):[0-5][0-9]
   */
  @Nullable private String startTimeOfDay;

  /**
   * End time of day in local time. Same syntax as startTimeOfDay.
   *
   * <pre>
   * If end time is before start time then the period wraps around to the next day.
   * To stop at end of the day use: 00:00.
   * </pre>
   */
  @Nullable private String endTimeOfDay;

  /** Day(s) of the week this is tariff applies. */
  @Nullable private DayOfWeekEnum[] dayOfWeek;

  /**
   * Start date in local time, for example: 2015-12-24.
   *
   * <pre>
   * Valid from this day (inclusive).
   * Format as per RFC 3339: full-date
   *
   * </pre>
   *
   * Regex: ([12][0-9]{3})-(0[1-9]\|1[0-2])-(0[1-9]\|[12][0-9]\|3[01])
   */
  @Nullable private String validFromDate;

  /**
   * End date in local time, for example: 2015-12-27. Valid until this day (exclusive). Same syntax
   * as validFromDate.
   */
  @Nullable private String validToDate;

  /** Type of EVSE (AC, DC) this tariff applies to. */
  @Nullable private EvseKindEnum evseKind;

  /**
   * For which payment brand this (adhoc) tariff applies. Can be used to add a surcharge for certain
   * payment brands. Based on value of additionalIdToken from idToken.additionalInfo.type =
   * "PaymentBrand".
   */
  @Nullable private String paymentBrand;

  /**
   * Type of adhoc payment, e.g. CC, Debit. Based on value of additionalIdToken from
   * idToken.additionalInfo.type = "PaymentRecognition".
   */
  @Nullable private String paymentRecognition;

  /** Custom data */
  @Nullable private CustomData customData;

  /** Constructor for the TariffConditionsFixed class */
  public TariffConditionsFixed() {}

  /**
   * Gets start time of day in local time.
   *
   * @return Start time of day in local time
   */
  @Nullable
  public String getStartTimeOfDay() {
    return startTimeOfDay;
  }

  /**
   * Sets start time of day in local time.
   *
   * @param startTimeOfDay Start time of day in local time
   */
  public void setStartTimeOfDay(@Nullable String startTimeOfDay) {
    this.startTimeOfDay = startTimeOfDay;
  }

  /**
   * Adds start time of day in local time.
   *
   * @param startTimeOfDay Start time of day in local time
   * @return this
   */
  public TariffConditionsFixed withStartTimeOfDay(@Nullable String startTimeOfDay) {
    setStartTimeOfDay(startTimeOfDay);
    return this;
  }

  /**
   * Gets end time of day in local time. Same syntax as startTimeOfDay.
   *
   * @return End time of day in local time
   */
  @Nullable
  public String getEndTimeOfDay() {
    return endTimeOfDay;
  }

  /**
   * Sets end time of day in local time. Same syntax as startTimeOfDay.
   *
   * @param endTimeOfDay End time of day in local time
   */
  public void setEndTimeOfDay(@Nullable String endTimeOfDay) {
    this.endTimeOfDay = endTimeOfDay;
  }

  /**
   * Adds end time of day in local time. Same syntax as startTimeOfDay.
   *
   * @param endTimeOfDay End time of day in local time
   * @return this
   */
  public TariffConditionsFixed withEndTimeOfDay(@Nullable String endTimeOfDay) {
    setEndTimeOfDay(endTimeOfDay);
    return this;
  }

  /**
   * Gets day(s) of the week this is tariff applies.
   *
   * @return Day(s) of the week this is tariff applies
   */
  @Nullable
  public DayOfWeekEnum[] getDayOfWeek() {
    return dayOfWeek;
  }

  /**
   * Sets day(s) of the week this is tariff applies.
   *
   * @param dayOfWeek Day(s) of the week this is tariff applies
   */
  public void setDayOfWeek(@Nullable DayOfWeekEnum[] dayOfWeek) {
    if (!isValidDayOfWeek(dayOfWeek)) {
      throw new PropertyConstraintException(dayOfWeek, "dayOfWeek is invalid");
    }
    this.dayOfWeek = dayOfWeek;
  }

  /**
   * Returns whether the given dayOfWeek is valid
   *
   * @param dayOfWeek the dayOfWeek to check the validity of
   * @return {@code true} if dayOfWeek is valid, {@code false} if not
   */
  private boolean isValidDayOfWeek(@Nullable DayOfWeekEnum[] dayOfWeek) {
    return dayOfWeek == null || (dayOfWeek.length >= 1 && dayOfWeek.length <= 7);
  }

  /**
   * Adds day(s) of the week this is tariff applies.
   *
   * @param dayOfWeek Day(s) of the week this is tariff applies
   * @return this
   */
  public TariffConditionsFixed withDayOfWeek(@Nullable DayOfWeekEnum[] dayOfWeek) {
    setDayOfWeek(dayOfWeek);
    return this;
  }

  /**
   * Gets start date in local time, for example: 2015-12-24.
   *
   * @return Start date in local time, for example: 2015-12-24
   */
  @Nullable
  public String getValidFromDate() {
    return validFromDate;
  }

  /**
   * Sets start date in local time, for example: 2015-12-24.
   *
   * @param validFromDate Start date in local time, for example: 2015-12-24
   */
  public void setValidFromDate(@Nullable String validFromDate) {
    this.validFromDate = validFromDate;
  }

  /**
   * Adds start date in local time, for example: 2015-12-24.
   *
   * @param validFromDate Start date in local time, for example: 2015-12-24
   * @return this
   */
  public TariffConditionsFixed withValidFromDate(@Nullable String validFromDate) {
    setValidFromDate(validFromDate);
    return this;
  }

  /**
   * Gets end date in local time, for example: 2015-12-27. Valid until this day (exclusive). Same
   * syntax as validFromDate.
   *
   * @return End date in local time, for example: 2015-12-27
   */
  @Nullable
  public String getValidToDate() {
    return validToDate;
  }

  /**
   * Sets end date in local time, for example: 2015-12-27. Valid until this day (exclusive). Same
   * syntax as validFromDate.
   *
   * @param validToDate End date in local time, for example: 2015-12-27
   */
  public void setValidToDate(@Nullable String validToDate) {
    this.validToDate = validToDate;
  }

  /**
   * Adds end date in local time, for example: 2015-12-27. Valid until this day (exclusive). Same
   * syntax as validFromDate.
   *
   * @param validToDate End date in local time, for example: 2015-12-27
   * @return this
   */
  public TariffConditionsFixed withValidToDate(@Nullable String validToDate) {
    setValidToDate(validToDate);
    return this;
  }

  /**
   * Gets type of EVSE (AC, DC) this tariff applies to.
   *
   * @return Type of EVSE (AC, DC) this tariff applies to
   */
  @Nullable
  public EvseKindEnum getEvseKind() {
    return evseKind;
  }

  /**
   * Sets type of EVSE (AC, DC) this tariff applies to.
   *
   * @param evseKind Type of EVSE (AC, DC) this tariff applies to
   */
  public void setEvseKind(@Nullable EvseKindEnum evseKind) {
    this.evseKind = evseKind;
  }

  /**
   * Adds type of EVSE (AC, DC) this tariff applies to.
   *
   * @param evseKind Type of EVSE (AC, DC) this tariff applies to
   * @return this
   */
  public TariffConditionsFixed withEvseKind(@Nullable EvseKindEnum evseKind) {
    setEvseKind(evseKind);
    return this;
  }

  /**
   * Gets for which payment brand this (adhoc) tariff applies. Can be used to add a surcharge for
   * certain payment brands. Based on value of additionalIdToken from idToken.additionalInfo.type =
   * "PaymentBrand".
   *
   * @return For which payment brand this (adhoc) tariff applies
   */
  @Nullable
  public String getPaymentBrand() {
    return paymentBrand;
  }

  /**
   * Sets for which payment brand this (adhoc) tariff applies. Can be used to add a surcharge for
   * certain payment brands. Based on value of additionalIdToken from idToken.additionalInfo.type =
   * "PaymentBrand".
   *
   * @param paymentBrand For which payment brand this (adhoc) tariff applies
   */
  public void setPaymentBrand(@Nullable String paymentBrand) {
    if (!isValidPaymentBrand(paymentBrand)) {
      throw new PropertyConstraintException(paymentBrand, "paymentBrand is invalid");
    }
    this.paymentBrand = paymentBrand;
  }

  /**
   * Returns whether the given paymentBrand is valid
   *
   * @param paymentBrand the paymentBrand to check the validity of
   * @return {@code true} if paymentBrand is valid, {@code false} if not
   */
  private boolean isValidPaymentBrand(@Nullable String paymentBrand) {
    return paymentBrand == null || paymentBrand.length() <= 20;
  }

  /**
   * Adds for which payment brand this (adhoc) tariff applies. Can be used to add a surcharge for
   * certain payment brands. Based on value of additionalIdToken from idToken.additionalInfo.type =
   * "PaymentBrand".
   *
   * @param paymentBrand For which payment brand this (adhoc) tariff applies
   * @return this
   */
  public TariffConditionsFixed withPaymentBrand(@Nullable String paymentBrand) {
    setPaymentBrand(paymentBrand);
    return this;
  }

  /**
   * Gets type of adhoc payment, e.g. CC, Debit. Based on value of additionalIdToken from
   * idToken.additionalInfo.type = "PaymentRecognition".
   *
   * @return Type of adhoc payment, e.g. CC, Debit
   */
  @Nullable
  public String getPaymentRecognition() {
    return paymentRecognition;
  }

  /**
   * Sets type of adhoc payment, e.g. CC, Debit. Based on value of additionalIdToken from
   * idToken.additionalInfo.type = "PaymentRecognition".
   *
   * @param paymentRecognition Type of adhoc payment, e.g. CC, Debit
   */
  public void setPaymentRecognition(@Nullable String paymentRecognition) {
    if (!isValidPaymentRecognition(paymentRecognition)) {
      throw new PropertyConstraintException(paymentRecognition, "paymentRecognition is invalid");
    }
    this.paymentRecognition = paymentRecognition;
  }

  /**
   * Returns whether the given paymentRecognition is valid
   *
   * @param paymentRecognition the paymentRecognition to check the validity of
   * @return {@code true} if paymentRecognition is valid, {@code false} if not
   */
  private boolean isValidPaymentRecognition(@Nullable String paymentRecognition) {
    return paymentRecognition == null || paymentRecognition.length() <= 20;
  }

  /**
   * Adds type of adhoc payment, e.g. CC, Debit. Based on value of additionalIdToken from
   * idToken.additionalInfo.type = "PaymentRecognition".
   *
   * @param paymentRecognition Type of adhoc payment, e.g. CC, Debit
   * @return this
   */
  public TariffConditionsFixed withPaymentRecognition(@Nullable String paymentRecognition) {
    setPaymentRecognition(paymentRecognition);
    return this;
  }

  /**
   * Gets custom data
   *
   * @return Custom data
   */
  @Nullable
  public CustomData getCustomData() {
    return customData;
  }

  /**
   * Sets custom data
   *
   * @param customData Custom data
   */
  public void setCustomData(@Nullable CustomData customData) {
    if (!isValidCustomData(customData)) {
      throw new PropertyConstraintException(customData, "customData is invalid");
    }
    this.customData = customData;
  }

  /**
   * Returns whether the given customData is valid
   *
   * @param customData the customData to check the validity of
   * @return {@code true} if customData is valid, {@code false} if not
   */
  private boolean isValidCustomData(@Nullable CustomData customData) {
    return customData == null || customData.validate();
  }

  /**
   * Adds custom data
   *
   * @param customData Custom data
   * @return this
   */
  public TariffConditionsFixed withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  public boolean validate() {
    return isValidDayOfWeek(dayOfWeek)
        && isValidPaymentBrand(paymentBrand)
        && isValidPaymentRecognition(paymentRecognition)
        && isValidCustomData(customData);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TariffConditionsFixed that = (TariffConditionsFixed) o;
    return Objects.equals(startTimeOfDay, that.startTimeOfDay)
        && Objects.equals(endTimeOfDay, that.endTimeOfDay)
        && Arrays.equals(dayOfWeek, that.dayOfWeek)
        && Objects.equals(validFromDate, that.validFromDate)
        && Objects.equals(validToDate, that.validToDate)
        && Objects.equals(evseKind, that.evseKind)
        && Objects.equals(paymentBrand, that.paymentBrand)
        && Objects.equals(paymentRecognition, that.paymentRecognition)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        startTimeOfDay,
        endTimeOfDay,
        Arrays.hashCode(dayOfWeek),
        validFromDate,
        validToDate,
        evseKind,
        paymentBrand,
        paymentRecognition,
        customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("startTimeOfDay", startTimeOfDay)
        .add("endTimeOfDay", endTimeOfDay)
        .add("dayOfWeek", dayOfWeek)
        .add("validFromDate", validFromDate)
        .add("validToDate", validToDate)
        .add("evseKind", evseKind)
        .add("paymentBrand", paymentBrand)
        .add("paymentRecognition", paymentRecognition)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
