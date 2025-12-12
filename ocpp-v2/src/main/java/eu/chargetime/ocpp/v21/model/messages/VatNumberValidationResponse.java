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
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.utilities.MoreObjects;
import eu.chargetime.ocpp.v21.model.types.Address;
import eu.chargetime.ocpp.v21.model.types.CustomData;
import eu.chargetime.ocpp.v21.model.types.GenericStatusEnum;
import eu.chargetime.ocpp.v21.model.types.StatusInfo;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * VatNumberValidationResponse
 *
 * <p>OCPP 2.1 Edition 1 (c) OCA, Creative Commons Attribution-NoDerivatives 4.0 International
 * Public License
 */
public final class VatNumberValidationResponse extends Confirmation {
  /** A generic address format. */
  @Nullable private Address company;

  /** More information about the status. */
  @Nullable private StatusInfo statusInfo;

  /** VAT number that was requested. */
  private String vatNumber;

  /** EVSE id for which check was requested. */
  @Nullable private Integer evseId;

  /** Result of operation. */
  private GenericStatusEnum status;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the VatNumberValidationResponse class
   *
   * @param vatNumber VAT number that was requested.
   * @param status Result of operation.
   */
  public VatNumberValidationResponse(String vatNumber, GenericStatusEnum status) {
    setVatNumber(vatNumber);
    setStatus(status);
  }

  /**
   * Gets a generic address format.
   *
   * @return A generic address format
   */
  @Nullable
  public Address getCompany() {
    return company;
  }

  /**
   * Sets a generic address format.
   *
   * @param company A generic address format
   */
  public void setCompany(@Nullable Address company) {
    if (!isValidCompany(company)) {
      throw new PropertyConstraintException(company, "company is invalid");
    }
    this.company = company;
  }

  /**
   * Returns whether the given company is valid
   *
   * @param company the company to check the validity of
   * @return {@code true} if company is valid, {@code false} if not
   */
  private boolean isValidCompany(@Nullable Address company) {
    return company == null || company.validate();
  }

  /**
   * Adds a generic address format.
   *
   * @param company A generic address format
   * @return this
   */
  public VatNumberValidationResponse withCompany(@Nullable Address company) {
    setCompany(company);
    return this;
  }

  /**
   * Gets more information about the status.
   *
   * @return More information about the status
   */
  @Nullable
  public StatusInfo getStatusInfo() {
    return statusInfo;
  }

  /**
   * Sets more information about the status.
   *
   * @param statusInfo More information about the status
   */
  public void setStatusInfo(@Nullable StatusInfo statusInfo) {
    if (!isValidStatusInfo(statusInfo)) {
      throw new PropertyConstraintException(statusInfo, "statusInfo is invalid");
    }
    this.statusInfo = statusInfo;
  }

  /**
   * Returns whether the given statusInfo is valid
   *
   * @param statusInfo the statusInfo to check the validity of
   * @return {@code true} if statusInfo is valid, {@code false} if not
   */
  private boolean isValidStatusInfo(@Nullable StatusInfo statusInfo) {
    return statusInfo == null || statusInfo.validate();
  }

  /**
   * Adds more information about the status.
   *
   * @param statusInfo More information about the status
   * @return this
   */
  public VatNumberValidationResponse withStatusInfo(@Nullable StatusInfo statusInfo) {
    setStatusInfo(statusInfo);
    return this;
  }

  /**
   * Gets VAT number that was requested.
   *
   * @return VAT number that was requested
   */
  public String getVatNumber() {
    return vatNumber;
  }

  /**
   * Sets VAT number that was requested.
   *
   * @param vatNumber VAT number that was requested
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
   * Gets EVSE id for which check was requested.
   *
   * @return EVSE id for which check was requested
   */
  @Nullable
  public Integer getEvseId() {
    return evseId;
  }

  /**
   * Sets EVSE id for which check was requested.
   *
   * @param evseId EVSE id for which check was requested
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
   * Adds EVSE id for which check was requested.
   *
   * @param evseId EVSE id for which check was requested
   * @return this
   */
  public VatNumberValidationResponse withEvseId(@Nullable Integer evseId) {
    setEvseId(evseId);
    return this;
  }

  /**
   * Gets result of operation.
   *
   * @return Result of operation
   */
  public GenericStatusEnum getStatus() {
    return status;
  }

  /**
   * Sets result of operation.
   *
   * @param status Result of operation
   */
  public void setStatus(GenericStatusEnum status) {
    if (!isValidStatus(status)) {
      throw new PropertyConstraintException(status, "status is invalid");
    }
    this.status = status;
  }

  /**
   * Returns whether the given status is valid
   *
   * @param status the status to check the validity of
   * @return {@code true} if status is valid, {@code false} if not
   */
  private boolean isValidStatus(GenericStatusEnum status) {
    return status != null;
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
  public VatNumberValidationResponse withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  @Override
  public boolean validate() {
    return isValidCompany(company)
        && isValidStatusInfo(statusInfo)
        && isValidVatNumber(vatNumber)
        && isValidEvseId(evseId)
        && isValidStatus(status)
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
    VatNumberValidationResponse that = (VatNumberValidationResponse) o;
    return Objects.equals(company, that.company)
        && Objects.equals(statusInfo, that.statusInfo)
        && Objects.equals(vatNumber, that.vatNumber)
        && Objects.equals(evseId, that.evseId)
        && Objects.equals(status, that.status)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(company, statusInfo, vatNumber, evseId, status, customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("company", company)
        .add("statusInfo", statusInfo)
        .add("vatNumber", vatNumber)
        .add("evseId", evseId)
        .add("status", status)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
