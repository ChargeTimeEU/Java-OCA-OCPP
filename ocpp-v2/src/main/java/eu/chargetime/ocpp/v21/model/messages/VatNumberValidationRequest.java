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
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * VatNumberValidationRequest
 *
 * <p>OCPP 2.1 Edition 1 (c) OCA, Creative Commons Attribution-NoDerivatives 4.0 International
 * Public License
 */
public final class VatNumberValidationRequest extends RequestWithId {
  /** VAT number to check. */
  private String vatNumber;

  /** EVSE id for which check is done */
  @Nullable private Integer evseId;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the VatNumberValidationRequest class
   *
   * @param vatNumber VAT number to check.
   */
  public VatNumberValidationRequest(String vatNumber) {
    setVatNumber(vatNumber);
  }

  /**
   * Gets VAT number to check.
   *
   * @return VAT number to check
   */
  public String getVatNumber() {
    return vatNumber;
  }

  /**
   * Sets VAT number to check.
   *
   * @param vatNumber VAT number to check
   */
  public void setVatNumber(String vatNumber) {
    if (!isValidVatNumber(vatNumber)) {
      throw new PropertyConstraintException(vatNumber, "vatNumber is invalid");
    }
    this.vatNumber = vatNumber;
  }

  /**
   * Returns whether the given vatNumber is valid
   *
   * @param vatNumber the vatNumber to check the validity of
   * @return {@code true} if vatNumber is valid, {@code false} if not
   */
  private boolean isValidVatNumber(String vatNumber) {
    return vatNumber != null && vatNumber.length() <= 20;
  }

  /**
   * Gets EVSE id for which check is done
   *
   * @return EVSE id for which check is done
   */
  @Nullable
  public Integer getEvseId() {
    return evseId;
  }

  /**
   * Sets EVSE id for which check is done
   *
   * @param evseId EVSE id for which check is done
   */
  public void setEvseId(@Nullable Integer evseId) {
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
  private boolean isValidEvseId(@Nullable Integer evseId) {
    return evseId == null || (evseId >= 0);
  }

  /**
   * Adds EVSE id for which check is done
   *
   * @param evseId EVSE id for which check is done
   * @return this
   */
  public VatNumberValidationRequest withEvseId(@Nullable Integer evseId) {
    setEvseId(evseId);
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
  public VatNumberValidationRequest withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  @Override
  public boolean validate() {
    return isValidVatNumber(vatNumber) && isValidEvseId(evseId) && isValidCustomData(customData);
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
    VatNumberValidationRequest that = (VatNumberValidationRequest) o;
    return Objects.equals(vatNumber, that.vatNumber)
        && Objects.equals(evseId, that.evseId)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vatNumber, evseId, customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("vatNumber", vatNumber)
        .add("evseId", evseId)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
