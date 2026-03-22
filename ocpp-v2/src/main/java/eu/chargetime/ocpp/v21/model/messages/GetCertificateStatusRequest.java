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
import eu.chargetime.ocpp.v21.model.types.OCSPRequestData;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * GetCertificateStatusRequest
 *
 * <p>OCPP 2.1 Edition 1 (c) OCA, Creative Commons Attribution-NoDerivatives 4.0 International
 * Public License
 */
public final class GetCertificateStatusRequest extends RequestWithId {
  /** Information about a certificate for an OCSP check. */
  private OCSPRequestData ocspRequestData;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the GetCertificateStatusRequest class
   *
   * @param ocspRequestData Information about a certificate for an OCSP check.
   */
  public GetCertificateStatusRequest(OCSPRequestData ocspRequestData) {
    setOcspRequestData(ocspRequestData);
  }

  /**
   * Gets information about a certificate for an OCSP check.
   *
   * @return Information about a certificate for an OCSP check
   */
  public OCSPRequestData getOcspRequestData() {
    return ocspRequestData;
  }

  /**
   * Sets information about a certificate for an OCSP check.
   *
   * @param ocspRequestData Information about a certificate for an OCSP check
   */
  public void setOcspRequestData(OCSPRequestData ocspRequestData) {
    if (!isValidOcspRequestData(ocspRequestData)) {
      throw new PropertyConstraintException(ocspRequestData, "ocspRequestData is invalid");
    }
    this.ocspRequestData = ocspRequestData;
  }

  /**
   * Returns whether the given ocspRequestData is valid
   *
   * @param ocspRequestData the ocspRequestData to check the validity of
   * @return {@code true} if ocspRequestData is valid, {@code false} if not
   */
  private boolean isValidOcspRequestData(OCSPRequestData ocspRequestData) {
    return ocspRequestData != null && ocspRequestData.validate();
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
  public GetCertificateStatusRequest withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  @Override
  public boolean validate() {
    return isValidOcspRequestData(ocspRequestData) && isValidCustomData(customData);
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
    GetCertificateStatusRequest that = (GetCertificateStatusRequest) o;
    return Objects.equals(ocspRequestData, that.ocspRequestData)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ocspRequestData, customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("ocspRequestData", ocspRequestData)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
