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
import eu.chargetime.ocpp.v21.model.types.CertificateActionEnum;
import eu.chargetime.ocpp.v21.model.types.CustomData;
import java.util.Arrays;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * Get15118EVCertificateRequest
 *
 * <p>OCPP 2.1 Edition 1 (c) OCA, Creative Commons Attribution-NoDerivatives 4.0 International
 * Public License
 */
public final class Get15118EVCertificateRequest extends RequestWithId {
  /**
   * Schema version currently used for the 15118 session between EV and Charging Station. Needed for
   * parsing of the EXI stream by the CSMS.
   */
  private String iso15118SchemaVersion;

  /** Whether certificate needs to be installed or updated. */
  private CertificateActionEnum action;

  /**
   * Raw CertificateInstallationReq request from EV, Base64 encoded.
   *
   * <p>Extended to support ISO 15118-20 certificates. The minimum supported length is 11000. If a
   * longer exiRequest is supported, then the supported length must be communicated in variable
   * OCPPCommCtrlr.FieldLength[ "Get15118EVCertificateRequest.exiRequest" ].
   */
  private String exiRequest;

  /**
   * Absent during ISO 15118-2 session. Required during ISO 15118-20 session.
   *
   * <p>Maximum number of contracts that EV wants to install.
   */
  @Nullable private Integer maximumContractCertificateChains;

  /**
   * Absent during ISO 15118-2 session. Optional during ISO 15118-20 session. List of EMAIDs for
   * which contract certificates must be requested first, in case there are more certificates than
   * allowed by maximumContractCertificateChains.
   */
  @Nullable private String[] prioritizedEMAIDs;

  /** Custom data */
  @Nullable private CustomData customData;

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
    return exiRequest != null && exiRequest.length() <= 11000;
  }

  /**
   * Gets absent during ISO 15118-2 session. Required during ISO 15118-20 session.
   *
   * @return Absent during ISO 15118-2 session
   */
  @Nullable
  public Integer getMaximumContractCertificateChains() {
    return maximumContractCertificateChains;
  }

  /**
   * Sets absent during ISO 15118-2 session. Required during ISO 15118-20 session.
   *
   * @param maximumContractCertificateChains Absent during ISO 15118-2 session
   */
  public void setMaximumContractCertificateChains(
      @Nullable Integer maximumContractCertificateChains) {
    if (!isValidMaximumContractCertificateChains(maximumContractCertificateChains)) {
      throw new PropertyConstraintException(
          maximumContractCertificateChains, "maximumContractCertificateChains is invalid");
    }
    this.maximumContractCertificateChains = maximumContractCertificateChains;
  }

  /**
   * Returns whether the given maximumContractCertificateChains is valid
   *
   * @param maximumContractCertificateChains the maximumContractCertificateChains to check the
   *     validity of
   * @return {@code true} if maximumContractCertificateChains is valid, {@code false} if not
   */
  private boolean isValidMaximumContractCertificateChains(
      @Nullable Integer maximumContractCertificateChains) {
    return maximumContractCertificateChains == null || (maximumContractCertificateChains >= 0);
  }

  /**
   * Adds absent during ISO 15118-2 session. Required during ISO 15118-20 session.
   *
   * @param maximumContractCertificateChains Absent during ISO 15118-2 session
   * @return this
   */
  public Get15118EVCertificateRequest withMaximumContractCertificateChains(
      @Nullable Integer maximumContractCertificateChains) {
    setMaximumContractCertificateChains(maximumContractCertificateChains);
    return this;
  }

  /**
   * Gets absent during ISO 15118-2 session. Optional during ISO 15118-20 session. List of EMAIDs
   * for which contract certificates must be requested first, in case there are more certificates
   * than allowed by maximumContractCertificateChains.
   *
   * @return Absent during ISO 15118-2 session
   */
  @Nullable
  public String[] getPrioritizedEMAIDs() {
    return prioritizedEMAIDs;
  }

  /**
   * Sets absent during ISO 15118-2 session. Optional during ISO 15118-20 session. List of EMAIDs
   * for which contract certificates must be requested first, in case there are more certificates
   * than allowed by maximumContractCertificateChains.
   *
   * @param prioritizedEMAIDs Absent during ISO 15118-2 session
   */
  public void setPrioritizedEMAIDs(@Nullable String[] prioritizedEMAIDs) {
    if (!isValidPrioritizedEMAIDs(prioritizedEMAIDs)) {
      throw new PropertyConstraintException(prioritizedEMAIDs, "prioritizedEMAIDs is invalid");
    }
    this.prioritizedEMAIDs = prioritizedEMAIDs;
  }

  /**
   * Returns whether the given prioritizedEMAIDs is valid
   *
   * @param prioritizedEMAIDs the prioritizedEMAIDs to check the validity of
   * @return {@code true} if prioritizedEMAIDs is valid, {@code false} if not
   */
  private boolean isValidPrioritizedEMAIDs(@Nullable String[] prioritizedEMAIDs) {
    return prioritizedEMAIDs == null
        || (prioritizedEMAIDs.length >= 1
            && prioritizedEMAIDs.length <= 8
            && Arrays.stream(prioritizedEMAIDs).allMatch(item -> item.length() <= 255));
  }

  /**
   * Adds absent during ISO 15118-2 session. Optional during ISO 15118-20 session. List of EMAIDs
   * for which contract certificates must be requested first, in case there are more certificates
   * than allowed by maximumContractCertificateChains.
   *
   * @param prioritizedEMAIDs Absent during ISO 15118-2 session
   * @return this
   */
  public Get15118EVCertificateRequest withPrioritizedEMAIDs(@Nullable String[] prioritizedEMAIDs) {
    setPrioritizedEMAIDs(prioritizedEMAIDs);
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
  public Get15118EVCertificateRequest withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  @Override
  public boolean validate() {
    return isValidIso15118SchemaVersion(iso15118SchemaVersion)
        && isValidAction(action)
        && isValidExiRequest(exiRequest)
        && isValidMaximumContractCertificateChains(maximumContractCertificateChains)
        && isValidPrioritizedEMAIDs(prioritizedEMAIDs)
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
    Get15118EVCertificateRequest that = (Get15118EVCertificateRequest) o;
    return Objects.equals(iso15118SchemaVersion, that.iso15118SchemaVersion)
        && Objects.equals(action, that.action)
        && Objects.equals(exiRequest, that.exiRequest)
        && Objects.equals(maximumContractCertificateChains, that.maximumContractCertificateChains)
        && Arrays.equals(prioritizedEMAIDs, that.prioritizedEMAIDs)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        iso15118SchemaVersion,
        action,
        exiRequest,
        maximumContractCertificateChains,
        Arrays.hashCode(prioritizedEMAIDs),
        customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("iso15118SchemaVersion", iso15118SchemaVersion)
        .add("action", action)
        .add("exiRequest", exiRequest)
        .add("maximumContractCertificateChains", maximumContractCertificateChains)
        .add("prioritizedEMAIDs", prioritizedEMAIDs)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
