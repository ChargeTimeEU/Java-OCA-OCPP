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
import eu.chargetime.ocpp.v201.model.types.CertificateActionEnum;
import eu.chargetime.ocpp.v201.model.types.CustomData;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * Get15118EVCertificateRequest
 *
 * <p>OCPP 2.0.1 FINAL
 */
public final class Get15118EVCertificateRequest extends RequestWithId {
  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Schema version currently used for the 15118 session between EV and Charging Station. Needed for
   * parsing of the EXI stream by the CSMS.
   */
  private String iso15118SchemaVersion;

  /** Whether certificate needs to be installed or updated. */
  private CertificateActionEnum action;

  /** Raw CertificateInstallationReq request from EV, Base64 encoded. */
  private String exiRequest;

  /**
   * Constructor for the Get15118EVCertificateRequest class
   *
   * @param iso15118SchemaVersion Schema version currently used for the 15118 session between EV and
   *     Charging Station. Needed for parsing of the EXI stream by the CSMS.
   * @param action Whether certificate needs to be installed or updated.
   * @param exiRequest Raw CertificateInstallationReq request from EV, Base64 encoded.
   */
  public Get15118EVCertificateRequest(
      String iso15118SchemaVersion, CertificateActionEnum action, String exiRequest) {
    setIso15118SchemaVersion(iso15118SchemaVersion);
    setAction(action);
    setExiRequest(exiRequest);
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
  public Get15118EVCertificateRequest withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  /**
   * Gets schema version currently used for the 15118 session between EV and Charging Station.
   * Needed for parsing of the EXI stream by the CSMS.
   *
   * @return Schema version currently used for the 15118 session between EV and Charging Station
   */
  public String getIso15118SchemaVersion() {
    return iso15118SchemaVersion;
  }

  /**
   * Sets schema version currently used for the 15118 session between EV and Charging Station.
   * Needed for parsing of the EXI stream by the CSMS.
   *
   * @param iso15118SchemaVersion Schema version currently used for the 15118 session between EV and
   *     Charging Station
   */
  public void setIso15118SchemaVersion(String iso15118SchemaVersion) {
    if (!isValidIso15118SchemaVersion(iso15118SchemaVersion)) {
      throw new PropertyConstraintException(
          iso15118SchemaVersion, "iso15118SchemaVersion is invalid");
    }
    this.iso15118SchemaVersion = iso15118SchemaVersion;
  }

  /**
   * Returns whether the given iso15118SchemaVersion is valid
   *
   * @param iso15118SchemaVersion the iso15118SchemaVersion to check the validity of
   * @return {@code true} if iso15118SchemaVersion is valid, {@code false} if not
   */
  private boolean isValidIso15118SchemaVersion(String iso15118SchemaVersion) {
    return iso15118SchemaVersion != null && iso15118SchemaVersion.length() <= 50;
  }

  /**
   * Gets whether certificate needs to be installed or updated.
   *
   * @return Whether certificate needs to be installed or updated
   */
  public CertificateActionEnum getAction() {
    return action;
  }

  /**
   * Sets whether certificate needs to be installed or updated.
   *
   * @param action Whether certificate needs to be installed or updated
   */
  public void setAction(CertificateActionEnum action) {
    if (!isValidAction(action)) {
      throw new PropertyConstraintException(action, "action is invalid");
    }
    this.action = action;
  }

  /**
   * Returns whether the given action is valid
   *
   * @param action the action to check the validity of
   * @return {@code true} if action is valid, {@code false} if not
   */
  private boolean isValidAction(CertificateActionEnum action) {
    return action != null;
  }

  /**
   * Gets raw CertificateInstallationReq request from EV, Base64 encoded.
   *
   * @return Raw CertificateInstallationReq request from EV, Base64 encoded
   */
  public String getExiRequest() {
    return exiRequest;
  }

  /**
   * Sets raw CertificateInstallationReq request from EV, Base64 encoded.
   *
   * @param exiRequest Raw CertificateInstallationReq request from EV, Base64 encoded
   */
  public void setExiRequest(String exiRequest) {
    if (!isValidExiRequest(exiRequest)) {
      throw new PropertyConstraintException(exiRequest, "exiRequest is invalid");
    }
    this.exiRequest = exiRequest;
  }

  /**
   * Returns whether the given exiRequest is valid
   *
   * @param exiRequest the exiRequest to check the validity of
   * @return {@code true} if exiRequest is valid, {@code false} if not
   */
  private boolean isValidExiRequest(String exiRequest) {
    return exiRequest != null && exiRequest.length() <= 5600;
  }

  @Override
  public boolean validate() {
    return isValidCustomData(customData)
        && isValidIso15118SchemaVersion(iso15118SchemaVersion)
        && isValidAction(action)
        && isValidExiRequest(exiRequest);
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
    Get15118EVCertificateRequest that = (Get15118EVCertificateRequest) o;
    return Objects.equals(customData, that.customData)
        && Objects.equals(iso15118SchemaVersion, that.iso15118SchemaVersion)
        && Objects.equals(action, that.action)
        && Objects.equals(exiRequest, that.exiRequest);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customData, iso15118SchemaVersion, action, exiRequest);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("iso15118SchemaVersion", iso15118SchemaVersion)
        .add("action", action)
        .add("exiRequest", exiRequest)
        .add("isValid", validate())
        .toString();
  }
}
