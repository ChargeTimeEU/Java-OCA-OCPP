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

package eu.chargetime.ocpp.v21.model.messages;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.model.RequestWithId;
import eu.chargetime.ocpp.utilities.MoreObjects;
import eu.chargetime.ocpp.v21.model.types.CustomData;
import eu.chargetime.ocpp.v21.model.types.Tariff;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * ChangeTransactionTariffRequest
 *
 * <p>OCPP 2.1 Edition 1 (c) OCA, Creative Commons Attribution-NoDerivatives 4.0 International
 * Public License
 */
public final class ChangeTransactionTariffRequest extends RequestWithId {
  /**
   * A tariff is described by fields with prices for: energy, charging time, idle time, fixed fee,
   * reservation time,
   *
   * <pre>
   * reservation fixed fee.
   * Each of these fields may have (optional) conditions that specify when a price is applicable.
   * The description contains a human-readable explanation of the tariff to be shown to the user.
   * The other fields are parameters that define the tariff. These are used by the charging station
   * to calculate the price.
   * </pre>
   */
  private Tariff tariff;

  /** Transaction id for new tariff. */
  private String transactionId;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the ChangeTransactionTariffRequest class
   *
   * @param tariff A tariff is described by fields with prices for: energy, charging time, idle
   *     time, fixed fee, reservation time,
   * @param transactionId Transaction id for new tariff.
   */
  public ChangeTransactionTariffRequest(Tariff tariff, String transactionId) {
    setTariff(tariff);
    setTransactionId(transactionId);
  }

  /**
   * Gets a tariff is described by fields with prices for: energy, charging time, idle time, fixed
   * fee, reservation time,
   *
   * @return A tariff is described by fields with prices for: energy, charging time, idle time,
   *     fixed fee, reservation time,
   */
  public Tariff getTariff() {
    return tariff;
  }

  /**
   * Sets a tariff is described by fields with prices for: energy, charging time, idle time, fixed
   * fee, reservation time,
   *
   * @param tariff A tariff is described by fields with prices for: energy, charging time, idle
   *     time, fixed fee, reservation time,
   */
  public void setTariff(Tariff tariff) {
    if (!isValidTariff(tariff)) {
      throw new PropertyConstraintException(tariff, "tariff is invalid");
    }
    this.tariff = tariff;
  }

  /**
   * Returns whether the given tariff is valid
   *
   * @param tariff the tariff to check the validity of
   * @return {@code true} if tariff is valid, {@code false} if not
   */
  private boolean isValidTariff(Tariff tariff) {
    return tariff != null && tariff.validate();
  }

  /**
   * Gets transaction id for new tariff.
   *
   * @return Transaction id for new tariff
   */
  public String getTransactionId() {
    return transactionId;
  }

  /**
   * Sets transaction id for new tariff.
   *
   * @param transactionId Transaction id for new tariff
   */
  public void setTransactionId(String transactionId) {
    if (!isValidTransactionId(transactionId)) {
      throw new PropertyConstraintException(transactionId, "transactionId is invalid");
    }
    this.transactionId = transactionId;
  }

  /**
   * Returns whether the given transactionId is valid
   *
   * @param transactionId the transactionId to check the validity of
   * @return {@code true} if transactionId is valid, {@code false} if not
   */
  private boolean isValidTransactionId(String transactionId) {
    return transactionId != null && transactionId.length() <= 36;
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
  public ChangeTransactionTariffRequest withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  @Override
  public boolean validate() {
    return isValidTariff(tariff)
        && isValidTransactionId(transactionId)
        && isValidCustomData(customData);
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
    ChangeTransactionTariffRequest that = (ChangeTransactionTariffRequest) o;
    return Objects.equals(tariff, that.tariff)
        && Objects.equals(transactionId, that.transactionId)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(tariff, transactionId, customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("tariff", tariff)
        .add("transactionId", transactionId)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
