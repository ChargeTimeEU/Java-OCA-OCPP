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
import eu.chargetime.ocpp.v201.model.types.CertificateHashData;
import eu.chargetime.ocpp.v201.model.types.CustomData;
import eu.chargetime.ocpp.v201.model.types.IdToken;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * CustomerInformationRequest
 *
 * <p>OCPP 2.0.1 FINAL
 */
public final class CustomerInformationRequest extends RequestWithId {
  /** Custom data */
  @Nullable private CustomData customData;

  /** customerCertificate */
  @Nullable private CertificateHashData customerCertificate;

  /**
   * A case insensitive identifier to use for the authorization and the type of authorization to
   * support multiple forms of identifiers.
   */
  @Nullable private IdToken idToken;

  /** The Id of the request. */
  private Integer requestId;

  /**
   * Flag indicating whether the Charging Station should return NotifyCustomerInformationRequest
   * messages containing information about the customer referred to.
   */
  private Boolean report;

  /**
   * Flag indicating whether the Charging Station should clear all information about the customer
   * referred to.
   */
  private Boolean clear;

  /**
   * A (e.g. vendor specific) identifier of the customer this request refers to. This field contains
   * a custom identifier other than IdToken and Certificate. One of the possible identifiers
   * (customerIdentifier, customerIdToken or customerCertificate) should be in the request message.
   */
  @Nullable private String customerIdentifier;

  /**
   * Constructor for the CustomerInformationRequest class
   *
   * @param requestId The Id of the request.
   * @param report Flag indicating whether the Charging Station should return
   *     NotifyCustomerInformationRequest messages containing information about the customer
   *     referred to.
   * @param clear Flag indicating whether the Charging Station should clear all information about
   *     the customer referred to.
   */
  public CustomerInformationRequest(Integer requestId, Boolean report, Boolean clear) {
    setRequestId(requestId);
    setReport(report);
    setClear(clear);
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
  public CustomerInformationRequest withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  /**
   * Gets customerCertificate
   *
   * @return customerCertificate
   */
  @Nullable
  public CertificateHashData getCustomerCertificate() {
    return customerCertificate;
  }

  /**
   * Sets customerCertificate
   *
   * @param customerCertificate customerCertificate
   */
  public void setCustomerCertificate(@Nullable CertificateHashData customerCertificate) {
    if (!isValidCustomerCertificate(customerCertificate)) {
      throw new PropertyConstraintException(customerCertificate, "customerCertificate is invalid");
    }
    this.customerCertificate = customerCertificate;
  }

  /**
   * Returns whether the given customerCertificate is valid
   *
   * @param customerCertificate the customerCertificate to check the validity of
   * @return {@code true} if customerCertificate is valid, {@code false} if not
   */
  private boolean isValidCustomerCertificate(@Nullable CertificateHashData customerCertificate) {
    return customerCertificate == null || customerCertificate.validate();
  }

  /**
   * Adds customerCertificate
   *
   * @param customerCertificate customerCertificate
   * @return this
   */
  public CustomerInformationRequest withCustomerCertificate(
      @Nullable CertificateHashData customerCertificate) {
    setCustomerCertificate(customerCertificate);
    return this;
  }

  /**
   * Gets a case insensitive identifier to use for the authorization and the type of authorization
   * to support multiple forms of identifiers.
   *
   * @return A case insensitive identifier to use for the authorization and the type of
   *     authorization to support multiple forms of identifiers
   */
  @Nullable
  public IdToken getIdToken() {
    return idToken;
  }

  /**
   * Sets a case insensitive identifier to use for the authorization and the type of authorization
   * to support multiple forms of identifiers.
   *
   * @param idToken A case insensitive identifier to use for the authorization and the type of
   *     authorization to support multiple forms of identifiers
   */
  public void setIdToken(@Nullable IdToken idToken) {
    if (!isValidIdToken(idToken)) {
      throw new PropertyConstraintException(idToken, "idToken is invalid");
    }
    this.idToken = idToken;
  }

  /**
   * Returns whether the given idToken is valid
   *
   * @param idToken the idToken to check the validity of
   * @return {@code true} if idToken is valid, {@code false} if not
   */
  private boolean isValidIdToken(@Nullable IdToken idToken) {
    return idToken == null || idToken.validate();
  }

  /**
   * Adds a case insensitive identifier to use for the authorization and the type of authorization
   * to support multiple forms of identifiers.
   *
   * @param idToken A case insensitive identifier to use for the authorization and the type of
   *     authorization to support multiple forms of identifiers
   * @return this
   */
  public CustomerInformationRequest withIdToken(@Nullable IdToken idToken) {
    setIdToken(idToken);
    return this;
  }

  /**
   * Gets the Id of the request.
   *
   * @return The Id of the request
   */
  public Integer getRequestId() {
    return requestId;
  }

  /**
   * Sets the Id of the request.
   *
   * @param requestId The Id of the request
   */
  public void setRequestId(Integer requestId) {
    if (!isValidRequestId(requestId)) {
      throw new PropertyConstraintException(requestId, "requestId is invalid");
    }
    this.requestId = requestId;
  }

  /**
   * Returns whether the given requestId is valid
   *
   * @param requestId the requestId to check the validity of
   * @return {@code true} if requestId is valid, {@code false} if not
   */
  private boolean isValidRequestId(Integer requestId) {
    return requestId != null;
  }

  /**
   * Gets flag indicating whether the Charging Station should return
   * NotifyCustomerInformationRequest messages containing information about the customer referred
   * to.
   *
   * @return Flag indicating whether the Charging Station should return
   *     NotifyCustomerInformationRequest messages containing information about the customer
   *     referred to
   */
  public Boolean getReport() {
    return report;
  }

  /**
   * Sets flag indicating whether the Charging Station should return
   * NotifyCustomerInformationRequest messages containing information about the customer referred
   * to.
   *
   * @param report Flag indicating whether the Charging Station should return
   *     NotifyCustomerInformationRequest messages containing information about the customer
   *     referred to
   */
  public void setReport(Boolean report) {
    if (!isValidReport(report)) {
      throw new PropertyConstraintException(report, "report is invalid");
    }
    this.report = report;
  }

  /**
   * Returns whether the given report is valid
   *
   * @param report the report to check the validity of
   * @return {@code true} if report is valid, {@code false} if not
   */
  private boolean isValidReport(Boolean report) {
    return report != null;
  }

  /**
   * Gets flag indicating whether the Charging Station should clear all information about the
   * customer referred to.
   *
   * @return Flag indicating whether the Charging Station should clear all information about the
   *     customer referred to
   */
  public Boolean getClear() {
    return clear;
  }

  /**
   * Sets flag indicating whether the Charging Station should clear all information about the
   * customer referred to.
   *
   * @param clear Flag indicating whether the Charging Station should clear all information about
   *     the customer referred to
   */
  public void setClear(Boolean clear) {
    if (!isValidClear(clear)) {
      throw new PropertyConstraintException(clear, "clear is invalid");
    }
    this.clear = clear;
  }

  /**
   * Returns whether the given clear is valid
   *
   * @param clear the clear to check the validity of
   * @return {@code true} if clear is valid, {@code false} if not
   */
  private boolean isValidClear(Boolean clear) {
    return clear != null;
  }

  /**
   * Gets a (e.g. vendor specific) identifier of the customer this request refers to. This field
   * contains a custom identifier other than IdToken and Certificate. One of the possible
   * identifiers (customerIdentifier, customerIdToken or customerCertificate) should be in the
   * request message.
   *
   * @return A (e.g. vendor specific) identifier of the customer this request refers to
   */
  @Nullable
  public String getCustomerIdentifier() {
    return customerIdentifier;
  }

  /**
   * Sets a (e.g. vendor specific) identifier of the customer this request refers to. This field
   * contains a custom identifier other than IdToken and Certificate. One of the possible
   * identifiers (customerIdentifier, customerIdToken or customerCertificate) should be in the
   * request message.
   *
   * @param customerIdentifier A (e.g. vendor specific) identifier of the customer this request
   *     refers to
   */
  public void setCustomerIdentifier(@Nullable String customerIdentifier) {
    if (!isValidCustomerIdentifier(customerIdentifier)) {
      throw new PropertyConstraintException(customerIdentifier, "customerIdentifier is invalid");
    }
    this.customerIdentifier = customerIdentifier;
  }

  /**
   * Returns whether the given customerIdentifier is valid
   *
   * @param customerIdentifier the customerIdentifier to check the validity of
   * @return {@code true} if customerIdentifier is valid, {@code false} if not
   */
  private boolean isValidCustomerIdentifier(@Nullable String customerIdentifier) {
    return customerIdentifier == null || customerIdentifier.length() <= 64;
  }

  /**
   * Adds a (e.g. vendor specific) identifier of the customer this request refers to. This field
   * contains a custom identifier other than IdToken and Certificate. One of the possible
   * identifiers (customerIdentifier, customerIdToken or customerCertificate) should be in the
   * request message.
   *
   * @param customerIdentifier A (e.g. vendor specific) identifier of the customer this request
   *     refers to
   * @return this
   */
  public CustomerInformationRequest withCustomerIdentifier(@Nullable String customerIdentifier) {
    setCustomerIdentifier(customerIdentifier);
    return this;
  }

  @Override
  public boolean validate() {
    return isValidCustomData(customData)
        && isValidCustomerCertificate(customerCertificate)
        && isValidIdToken(idToken)
        && isValidRequestId(requestId)
        && isValidReport(report)
        && isValidClear(clear)
        && isValidCustomerIdentifier(customerIdentifier);
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
    CustomerInformationRequest that = (CustomerInformationRequest) o;
    return Objects.equals(customData, that.customData)
        && Objects.equals(customerCertificate, that.customerCertificate)
        && Objects.equals(idToken, that.idToken)
        && Objects.equals(requestId, that.requestId)
        && Objects.equals(report, that.report)
        && Objects.equals(clear, that.clear)
        && Objects.equals(customerIdentifier, that.customerIdentifier);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        customData, customerCertificate, idToken, requestId, report, clear, customerIdentifier);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("customerCertificate", customerCertificate)
        .add("idToken", idToken)
        .add("requestId", requestId)
        .add("report", report)
        .add("clear", clear)
        .add("customerIdentifier", customerIdentifier)
        .add("isValid", validate())
        .toString();
  }
}
