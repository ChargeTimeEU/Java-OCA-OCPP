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
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * CostUpdatedRequest
 *
 * <p>OCPP 2.0.1 FINAL
 */
public final class CostUpdatedRequest extends RequestWithId {
  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Current total cost, based on the information known by the CSMS, of the transaction including
   * taxes. In the currency configured with the configuration Variable: [Currency]
   */
  private Double totalCost;

  /** Transaction Id of the transaction the current cost are asked for. */
  private String transactionId;

  /**
   * Constructor for the CostUpdatedRequest class
   *
   * @param totalCost Current total cost, based on the information known by the CSMS, of the
   *     transaction including taxes. In the currency configured with the configuration Variable:
   *     [Currency]
   * @param transactionId Transaction Id of the transaction the current cost are asked for.
   */
  public CostUpdatedRequest(Double totalCost, String transactionId) {
    setTotalCost(totalCost);
    setTransactionId(transactionId);
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
  public CostUpdatedRequest withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  /**
   * Gets current total cost, based on the information known by the CSMS, of the transaction
   * including taxes. In the currency configured with the configuration Variable: [Currency]
   *
   * @return Current total cost, based on the information known by the CSMS, of the transaction
   *     including taxes
   */
  public Double getTotalCost() {
    return totalCost;
  }

  /**
   * Sets current total cost, based on the information known by the CSMS, of the transaction
   * including taxes. In the currency configured with the configuration Variable: [Currency]
   *
   * @param totalCost Current total cost, based on the information known by the CSMS, of the
   *     transaction including taxes
   */
  public void setTotalCost(Double totalCost) {
    if (!isValidTotalCost(totalCost)) {
      throw new PropertyConstraintException(totalCost, "totalCost is invalid");
    }
    this.totalCost = totalCost;
  }

  /**
   * Returns whether the given totalCost is valid
   *
   * @param totalCost the totalCost to check the validity of
   * @return {@code true} if totalCost is valid, {@code false} if not
   */
  private boolean isValidTotalCost(Double totalCost) {
    return totalCost != null;
  }

  /**
   * Gets transaction Id of the transaction the current cost are asked for.
   *
   * @return Transaction Id of the transaction the current cost are asked for
   */
  public String getTransactionId() {
    return transactionId;
  }

  /**
   * Sets transaction Id of the transaction the current cost are asked for.
   *
   * @param transactionId Transaction Id of the transaction the current cost are asked for
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

  @Override
  public boolean validate() {
    return isValidCustomData(customData)
        && isValidTotalCost(totalCost)
        && isValidTransactionId(transactionId);
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
    CostUpdatedRequest that = (CostUpdatedRequest) o;
    return Objects.equals(customData, that.customData)
        && Objects.equals(totalCost, that.totalCost)
        && Objects.equals(transactionId, that.transactionId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customData, totalCost, transactionId);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("totalCost", totalCost)
        .add("transactionId", transactionId)
        .add("isValid", validate())
        .toString();
  }
}
