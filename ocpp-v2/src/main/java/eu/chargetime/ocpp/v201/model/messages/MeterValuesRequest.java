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

package eu.chargetime.ocpp.v201.model.messages;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.model.RequestWithId;
import eu.chargetime.ocpp.utilities.MoreObjects;
import eu.chargetime.ocpp.v201.model.types.CustomData;
import eu.chargetime.ocpp.v201.model.types.MeterValue;
import java.util.Arrays;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * Request Body
 *
 * <p>OCPP 2.0.1 FINAL
 */
public final class MeterValuesRequest extends RequestWithId {
  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Request Body. EVSEID. Numeric Identifier
   *
   * <p>A number (greater than 0) designating an EVSE of the Charging Station. ‘0’ (zero) is used to
   * designate the main power meter.
   */
  private Integer evseId;

  /**
   * Meter Value
   *
   * <p>Collection of one or more sampled values in MeterValuesRequest and TransactionEvent. All
   * sampled values in a MeterValue are sampled at the same point in time.
   */
  private MeterValue[] meterValue;

  /**
   * Constructor for the MeterValuesRequest class
   *
   * @param evseId A number (greater than 0) designating an EVSE of the Charging Station. ‘0’ (zero)
   *     is used to designate the main power meter.
   * @param meterValue Collection of one or more sampled values in MeterValuesRequest and
   *     TransactionEvent. All sampled values in a MeterValue are sampled at the same point in time.
   */
  public MeterValuesRequest(Integer evseId, MeterValue[] meterValue) {
    setEvseId(evseId);
    setMeterValue(meterValue);
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
  public MeterValuesRequest withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  /**
   * Gets a number (greater than 0) designating an EVSE of the Charging Station. ‘0’ (zero) is used
   * to designate the main power meter.
   *
   * @return A number (greater than 0) designating an EVSE of the Charging Station
   */
  public Integer getEvseId() {
    return evseId;
  }

  /**
   * Sets a number (greater than 0) designating an EVSE of the Charging Station. ‘0’ (zero) is used
   * to designate the main power meter.
   *
   * @param evseId A number (greater than 0) designating an EVSE of the Charging Station
   */
  public void setEvseId(Integer evseId) {
    if (!isValidEvseId(evseId)) {
      throw new PropertyConstraintException(evseId, "evseId is invalid");
    }
    this.evseId = evseId;
  }

  /**
   * Returns whether the given evseId is valid
   *
   * @param evseId the evseId to check the validity of
   * @return {@code true} if evseId is valid, {@code false} if not
   */
  private boolean isValidEvseId(Integer evseId) {
    return evseId != null;
  }

  /**
   * Gets collection of one or more sampled values in MeterValuesRequest and TransactionEvent. All
   * sampled values in a MeterValue are sampled at the same point in time.
   *
   * @return Collection of one or more sampled values in MeterValuesRequest and TransactionEvent
   */
  public MeterValue[] getMeterValue() {
    return meterValue;
  }

  /**
   * Sets collection of one or more sampled values in MeterValuesRequest and TransactionEvent. All
   * sampled values in a MeterValue are sampled at the same point in time.
   *
   * @param meterValue Collection of one or more sampled values in MeterValuesRequest and
   *     TransactionEvent
   */
  public void setMeterValue(MeterValue[] meterValue) {
    if (!isValidMeterValue(meterValue)) {
      throw new PropertyConstraintException(meterValue, "meterValue is invalid");
    }
    this.meterValue = meterValue;
  }

  /**
   * Returns whether the given meterValue is valid
   *
   * @param meterValue the meterValue to check the validity of
   * @return {@code true} if meterValue is valid, {@code false} if not
   */
  private boolean isValidMeterValue(MeterValue[] meterValue) {
    return meterValue != null
        && meterValue.length >= 1
        && Arrays.stream(meterValue).allMatch(item -> item.validate());
  }

  @Override
  public boolean validate() {
    return isValidCustomData(customData) && isValidEvseId(evseId) && isValidMeterValue(meterValue);
  }

  @Override
  public boolean transactionRelated() {
    return false;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MeterValuesRequest that = (MeterValuesRequest) o;
    return Objects.equals(customData, that.customData)
        && Objects.equals(evseId, that.evseId)
        && Arrays.equals(meterValue, that.meterValue);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customData, evseId, Arrays.hashCode(meterValue));
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("evseId", evseId)
        .add("meterValue", meterValue)
        .add("isValid", validate())
        .toString();
  }
}
