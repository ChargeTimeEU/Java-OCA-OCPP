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

package eu.chargetime.ocpp.v201.model.types;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.utilities.MoreObjects;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * Communication Function
 *
 * <p>The NetworkConnectionProfile defines the functional and technical parameters of a
 * communication link.
 */
public final class NetworkConnectionProfile {
  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * APN
   *
   * <p>Collection of configuration data needed to make a data-connection over a cellular network.
   *
   * <p>NOTE: When asking a GSM modem to dial in, it is possible to specify which mobile operator
   * should be used. This can be done with the mobile country code (MCC) in combination with a
   * mobile network code (MNC). Example: If your preferred network is Vodafone Netherlands, the
   * MCC=204 and the MNC=04 which means the key PreferredNetwork = 20404 Some modems allows to
   * specify a preferred network, which means, if this network is not available, a different network
   * is used. If you specify UseOnlyPreferredNetwork and this network is not available, the modem
   * will not dial in.
   */
  @Nullable private APN apn;

  /**
   * Communication Function. OCPP Version. OCPP Version Code
   *
   * <p>The OCPP version used for this communication function.
   */
  private OCPPVersionEnum ocppVersion;

  /**
   * Communication Function. OCPP Transport. OCPP Transport Code
   *
   * <p>The transport protocol (e.g. SOAP or JSON). Note: SOAP is not supported in OCPP 2.0, but is
   * supported by other versions of OCPP.
   */
  private OCPPTransportEnum ocppTransport;

  /**
   * Communication Function. OCPP Central System URL. URI
   *
   * <p>URL of the CSMS(s) that this Charging Station communicates with.
   */
  private String ocppCsmsUrl;

  /**
   * Duration in seconds before a message send by the Charging Station via this network connection
   * times-out. The best setting depends on the underlying network and response times of the CSMS.
   * If you are looking for a some guideline: use 30 seconds as a starting point.
   */
  private Integer messageTimeout;

  /** The security profile used when connecting to the CSMS with this NetworkConnectionProfile. */
  private Integer securityProfile;

  /** Applicable Network Interface. */
  private OCPPInterfaceEnum ocppInterface;

  /**
   * VPN
   *
   * <p>VPN Configuration settings
   */
  @Nullable private VPN vpn;

  /**
   * Constructor for the NetworkConnectionProfile class
   *
   * @param ocppVersion The OCPP version used for this communication function.
   * @param ocppTransport The transport protocol (e.g. SOAP or JSON). Note: SOAP is not supported in
   *     OCPP 2.0, but is supported by other versions of OCPP.
   * @param ocppCsmsUrl URL of the CSMS(s) that this Charging Station communicates with.
   * @param messageTimeout Duration in seconds before a message send by the Charging Station via
   *     this network connection times-out. The best setting depends on the underlying network and
   *     response times of the CSMS. If you are looking for a some guideline: use 30 seconds as a
   *     starting point.
   * @param securityProfile The security profile used when connecting to the CSMS with this
   *     NetworkConnectionProfile.
   * @param ocppInterface Applicable Network Interface.
   */
  public NetworkConnectionProfile(
      OCPPVersionEnum ocppVersion,
      OCPPTransportEnum ocppTransport,
      String ocppCsmsUrl,
      Integer messageTimeout,
      Integer securityProfile,
      OCPPInterfaceEnum ocppInterface) {
    setOcppVersion(ocppVersion);
    setOcppTransport(ocppTransport);
    setOcppCsmsUrl(ocppCsmsUrl);
    setMessageTimeout(messageTimeout);
    setSecurityProfile(securityProfile);
    setOcppInterface(ocppInterface);
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
  public NetworkConnectionProfile withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  /**
   * Gets collection of configuration data needed to make a data-connection over a cellular network.
   *
   * @return Collection of configuration data needed to make a data-connection over a cellular
   *     network
   */
  @Nullable
  public APN getApn() {
    return apn;
  }

  /**
   * Sets collection of configuration data needed to make a data-connection over a cellular network.
   *
   * @param apn Collection of configuration data needed to make a data-connection over a cellular
   *     network
   */
  public void setApn(@Nullable APN apn) {
    if (!isValidApn(apn)) {
      throw new PropertyConstraintException(apn, "apn is invalid");
    }
    this.apn = apn;
  }

  /**
   * Returns whether the given apn is valid
   *
   * @param apn the apn to check the validity of
   * @return {@code true} if apn is valid, {@code false} if not
   */
  private boolean isValidApn(@Nullable APN apn) {
    return apn == null || apn.validate();
  }

  /**
   * Adds collection of configuration data needed to make a data-connection over a cellular network.
   *
   * @param apn Collection of configuration data needed to make a data-connection over a cellular
   *     network
   * @return this
   */
  public NetworkConnectionProfile withApn(@Nullable APN apn) {
    setApn(apn);
    return this;
  }

  /**
   * Gets the OCPP version used for this communication function.
   *
   * @return The OCPP version used for this communication function
   */
  public OCPPVersionEnum getOcppVersion() {
    return ocppVersion;
  }

  /**
   * Sets the OCPP version used for this communication function.
   *
   * @param ocppVersion The OCPP version used for this communication function
   */
  public void setOcppVersion(OCPPVersionEnum ocppVersion) {
    if (!isValidOcppVersion(ocppVersion)) {
      throw new PropertyConstraintException(ocppVersion, "ocppVersion is invalid");
    }
    this.ocppVersion = ocppVersion;
  }

  /**
   * Returns whether the given ocppVersion is valid
   *
   * @param ocppVersion the ocppVersion to check the validity of
   * @return {@code true} if ocppVersion is valid, {@code false} if not
   */
  private boolean isValidOcppVersion(OCPPVersionEnum ocppVersion) {
    return ocppVersion != null;
  }

  /**
   * Gets the transport protocol (e.g. SOAP or JSON). Note: SOAP is not supported in OCPP 2.0, but
   * is supported by other versions of OCPP.
   *
   * @return The transport protocol (e.g. SOAP or JSON)
   */
  public OCPPTransportEnum getOcppTransport() {
    return ocppTransport;
  }

  /**
   * Sets the transport protocol (e.g. SOAP or JSON). Note: SOAP is not supported in OCPP 2.0, but
   * is supported by other versions of OCPP.
   *
   * @param ocppTransport The transport protocol (e.g. SOAP or JSON)
   */
  public void setOcppTransport(OCPPTransportEnum ocppTransport) {
    if (!isValidOcppTransport(ocppTransport)) {
      throw new PropertyConstraintException(ocppTransport, "ocppTransport is invalid");
    }
    this.ocppTransport = ocppTransport;
  }

  /**
   * Returns whether the given ocppTransport is valid
   *
   * @param ocppTransport the ocppTransport to check the validity of
   * @return {@code true} if ocppTransport is valid, {@code false} if not
   */
  private boolean isValidOcppTransport(OCPPTransportEnum ocppTransport) {
    return ocppTransport != null;
  }

  /**
   * Gets URL of the CSMS(s) that this Charging Station communicates with.
   *
   * @return URL of the CSMS(s) that this Charging Station communicates with
   */
  public String getOcppCsmsUrl() {
    return ocppCsmsUrl;
  }

  /**
   * Sets URL of the CSMS(s) that this Charging Station communicates with.
   *
   * @param ocppCsmsUrl URL of the CSMS(s) that this Charging Station communicates with
   */
  public void setOcppCsmsUrl(String ocppCsmsUrl) {
    if (!isValidOcppCsmsUrl(ocppCsmsUrl)) {
      throw new PropertyConstraintException(ocppCsmsUrl, "ocppCsmsUrl is invalid");
    }
    this.ocppCsmsUrl = ocppCsmsUrl;
  }

  /**
   * Returns whether the given ocppCsmsUrl is valid
   *
   * @param ocppCsmsUrl the ocppCsmsUrl to check the validity of
   * @return {@code true} if ocppCsmsUrl is valid, {@code false} if not
   */
  private boolean isValidOcppCsmsUrl(String ocppCsmsUrl) {
    return ocppCsmsUrl != null && ocppCsmsUrl.length() <= 512;
  }

  /**
   * Gets duration in seconds before a message send by the Charging Station via this network
   * connection times-out. The best setting depends on the underlying network and response times of
   * the CSMS. If you are looking for a some guideline: use 30 seconds as a starting point.
   *
   * @return Duration in seconds before a message send by the Charging Station via this network
   *     connection times-out
   */
  public Integer getMessageTimeout() {
    return messageTimeout;
  }

  /**
   * Sets duration in seconds before a message send by the Charging Station via this network
   * connection times-out. The best setting depends on the underlying network and response times of
   * the CSMS. If you are looking for a some guideline: use 30 seconds as a starting point.
   *
   * @param messageTimeout Duration in seconds before a message send by the Charging Station via
   *     this network connection times-out
   */
  public void setMessageTimeout(Integer messageTimeout) {
    if (!isValidMessageTimeout(messageTimeout)) {
      throw new PropertyConstraintException(messageTimeout, "messageTimeout is invalid");
    }
    this.messageTimeout = messageTimeout;
  }

  /**
   * Returns whether the given messageTimeout is valid
   *
   * @param messageTimeout the messageTimeout to check the validity of
   * @return {@code true} if messageTimeout is valid, {@code false} if not
   */
  private boolean isValidMessageTimeout(Integer messageTimeout) {
    return messageTimeout != null;
  }

  /**
   * Gets the security profile used when connecting to the CSMS with this NetworkConnectionProfile.
   *
   * @return The security profile used when connecting to the CSMS with this
   *     NetworkConnectionProfile
   */
  public Integer getSecurityProfile() {
    return securityProfile;
  }

  /**
   * Sets the security profile used when connecting to the CSMS with this NetworkConnectionProfile.
   *
   * @param securityProfile The security profile used when connecting to the CSMS with this
   *     NetworkConnectionProfile
   */
  public void setSecurityProfile(Integer securityProfile) {
    if (!isValidSecurityProfile(securityProfile)) {
      throw new PropertyConstraintException(securityProfile, "securityProfile is invalid");
    }
    this.securityProfile = securityProfile;
  }

  /**
   * Returns whether the given securityProfile is valid
   *
   * @param securityProfile the securityProfile to check the validity of
   * @return {@code true} if securityProfile is valid, {@code false} if not
   */
  private boolean isValidSecurityProfile(Integer securityProfile) {
    return securityProfile != null;
  }

  /**
   * Gets applicable Network Interface.
   *
   * @return Applicable Network Interface
   */
  public OCPPInterfaceEnum getOcppInterface() {
    return ocppInterface;
  }

  /**
   * Sets applicable Network Interface.
   *
   * @param ocppInterface Applicable Network Interface
   */
  public void setOcppInterface(OCPPInterfaceEnum ocppInterface) {
    if (!isValidOcppInterface(ocppInterface)) {
      throw new PropertyConstraintException(ocppInterface, "ocppInterface is invalid");
    }
    this.ocppInterface = ocppInterface;
  }

  /**
   * Returns whether the given ocppInterface is valid
   *
   * @param ocppInterface the ocppInterface to check the validity of
   * @return {@code true} if ocppInterface is valid, {@code false} if not
   */
  private boolean isValidOcppInterface(OCPPInterfaceEnum ocppInterface) {
    return ocppInterface != null;
  }

  /**
   * Gets VPN Configuration settings
   *
   * @return VPN Configuration settings
   */
  @Nullable
  public VPN getVpn() {
    return vpn;
  }

  /**
   * Sets VPN Configuration settings
   *
   * @param vpn VPN Configuration settings
   */
  public void setVpn(@Nullable VPN vpn) {
    if (!isValidVpn(vpn)) {
      throw new PropertyConstraintException(vpn, "vpn is invalid");
    }
    this.vpn = vpn;
  }

  /**
   * Returns whether the given vpn is valid
   *
   * @param vpn the vpn to check the validity of
   * @return {@code true} if vpn is valid, {@code false} if not
   */
  private boolean isValidVpn(@Nullable VPN vpn) {
    return vpn == null || vpn.validate();
  }

  /**
   * Adds VPN Configuration settings
   *
   * @param vpn VPN Configuration settings
   * @return this
   */
  public NetworkConnectionProfile withVpn(@Nullable VPN vpn) {
    setVpn(vpn);
    return this;
  }

  public boolean validate() {
    return isValidCustomData(customData)
        && isValidApn(apn)
        && isValidOcppVersion(ocppVersion)
        && isValidOcppTransport(ocppTransport)
        && isValidOcppCsmsUrl(ocppCsmsUrl)
        && isValidMessageTimeout(messageTimeout)
        && isValidSecurityProfile(securityProfile)
        && isValidOcppInterface(ocppInterface)
        && isValidVpn(vpn);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NetworkConnectionProfile that = (NetworkConnectionProfile) o;
    return Objects.equals(customData, that.customData)
        && Objects.equals(apn, that.apn)
        && Objects.equals(ocppVersion, that.ocppVersion)
        && Objects.equals(ocppTransport, that.ocppTransport)
        && Objects.equals(ocppCsmsUrl, that.ocppCsmsUrl)
        && Objects.equals(messageTimeout, that.messageTimeout)
        && Objects.equals(securityProfile, that.securityProfile)
        && Objects.equals(ocppInterface, that.ocppInterface)
        && Objects.equals(vpn, that.vpn);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        customData,
        apn,
        ocppVersion,
        ocppTransport,
        ocppCsmsUrl,
        messageTimeout,
        securityProfile,
        ocppInterface,
        vpn);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("apn", apn)
        .add("ocppVersion", ocppVersion)
        .add("ocppTransport", ocppTransport)
        .add("ocppCsmsUrl", ocppCsmsUrl)
        .add("messageTimeout", messageTimeout)
        .add("securityProfile", securityProfile)
        .add("ocppInterface", ocppInterface)
        .add("vpn", vpn)
        .add("isValid", validate())
        .toString();
  }
}
