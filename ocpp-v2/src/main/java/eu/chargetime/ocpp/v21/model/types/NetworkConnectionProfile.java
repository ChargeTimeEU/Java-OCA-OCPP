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

package eu.chargetime.ocpp.v21.model.types;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.utilities.MoreObjects;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * The NetworkConnectionProfile defines the functional and technical parameters of a communication
 * link.
 */
public final class NetworkConnectionProfile {
  /**
   * Collection of configuration data needed to make a data-connection over a cellular network.
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
   * This field is ignored, since the OCPP version to use is determined during the websocket
   * handshake. The field is only kept for backwards compatibility with the OCPP 2.0.1 JSON schema.
   */
  @Nullable private OCPPVersionEnum ocppVersion;

  /**
   * Applicable Network Interface. Charging Station is allowed to use a different network interface
   * to connect if the given one does not work.
   */
  private OCPPInterfaceEnum ocppInterface;

  /**
   * The transport protocol (e.g. SOAP or JSON). Note: SOAP is not supported in OCPP 2.x, but is
   * supported by earlier versions of OCPP.
   */
  private OCPPTransportEnum ocppTransport;

  /**
   * Duration in seconds before a message send by the Charging Station via this network connection
   * times-out. The best setting depends on the underlying network and response times of the CSMS.
   * If you are looking for a some guideline: use 30 seconds as a starting point.
   */
  private Integer messageTimeout;

  /**
   * URL of the CSMS(s) that this Charging Station communicates with, without the Charging Station
   * identity part.
   *
   * <p>The SecurityCtrlr.Identity field is appended to ocppCsmsUrl to provide the full websocket
   * URL.
   */
  private String ocppCsmsUrl;

  /** The security profile used when connecting to the CSMS with this NetworkConnectionProfile. */
  private Integer securityProfile;

  /** Charging Station identity to be used as the basic authentication username. */
  @Nullable private String identity;

  /** BasicAuthPassword to use for security profile 1 or 2. */
  @Nullable private String basicAuthPassword;

  /** VPN Configuration settings */
  @Nullable private VPN vpn;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the NetworkConnectionProfile class
   *
   * @param ocppInterface Applicable Network Interface. Charging Station is allowed to use a
   *     different network interface to connect if the given one does not work.
   * @param ocppTransport The transport protocol (e.g. SOAP or JSON). Note: SOAP is not supported in
   *     OCPP 2.x, but is supported by earlier versions of OCPP.
   * @param messageTimeout Duration in seconds before a message send by the Charging Station via
   *     this network connection times-out. The best setting depends on the underlying network and
   *     response times of the CSMS. If you are looking for a some guideline: use 30 seconds as a
   *     starting point.
   * @param ocppCsmsUrl URL of the CSMS(s) that this Charging Station communicates with, without the
   *     Charging Station identity part.
   * @param securityProfile The security profile used when connecting to the CSMS with this
   *     NetworkConnectionProfile.
   */
  public NetworkConnectionProfile(
      OCPPInterfaceEnum ocppInterface,
      OCPPTransportEnum ocppTransport,
      Integer messageTimeout,
      String ocppCsmsUrl,
      Integer securityProfile) {
    setOcppInterface(ocppInterface);
    setOcppTransport(ocppTransport);
    setMessageTimeout(messageTimeout);
    setOcppCsmsUrl(ocppCsmsUrl);
    setSecurityProfile(securityProfile);
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
   * Gets this field is ignored, since the OCPP version to use is determined during the websocket
   * handshake. The field is only kept for backwards compatibility with the OCPP 2.0.1 JSON schema.
   *
   * @return This field is ignored, since the OCPP version to use is determined during the websocket
   *     handshake
   */
  @Nullable
  public OCPPVersionEnum getOcppVersion() {
    return ocppVersion;
  }

  /**
   * Sets this field is ignored, since the OCPP version to use is determined during the websocket
   * handshake. The field is only kept for backwards compatibility with the OCPP 2.0.1 JSON schema.
   *
   * @param ocppVersion This field is ignored, since the OCPP version to use is determined during
   *     the websocket handshake
   */
  public void setOcppVersion(@Nullable OCPPVersionEnum ocppVersion) {
    this.ocppVersion = ocppVersion;
  }

  /**
   * Adds this field is ignored, since the OCPP version to use is determined during the websocket
   * handshake. The field is only kept for backwards compatibility with the OCPP 2.0.1 JSON schema.
   *
   * @param ocppVersion This field is ignored, since the OCPP version to use is determined during
   *     the websocket handshake
   * @return this
   */
  public NetworkConnectionProfile withOcppVersion(@Nullable OCPPVersionEnum ocppVersion) {
    setOcppVersion(ocppVersion);
    return this;
  }

  /**
   * Gets applicable Network Interface. Charging Station is allowed to use a different network
   * interface to connect if the given one does not work.
   *
   * @return Applicable Network Interface
   */
  public OCPPInterfaceEnum getOcppInterface() {
    return ocppInterface;
  }

  /**
   * Sets applicable Network Interface. Charging Station is allowed to use a different network
   * interface to connect if the given one does not work.
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
   * Gets the transport protocol (e.g. SOAP or JSON). Note: SOAP is not supported in OCPP 2.x, but
   * is supported by earlier versions of OCPP.
   *
   * @return The transport protocol (e.g. SOAP or JSON)
   */
  public OCPPTransportEnum getOcppTransport() {
    return ocppTransport;
  }

  /**
   * Sets the transport protocol (e.g. SOAP or JSON). Note: SOAP is not supported in OCPP 2.x, but
   * is supported by earlier versions of OCPP.
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
   * Gets URL of the CSMS(s) that this Charging Station communicates with, without the Charging
   * Station identity part.
   *
   * @return URL of the CSMS(s) that this Charging Station communicates with, without the Charging
   *     Station identity part
   */
  public String getOcppCsmsUrl() {
    return ocppCsmsUrl;
  }

  /**
   * Sets URL of the CSMS(s) that this Charging Station communicates with, without the Charging
   * Station identity part.
   *
   * @param ocppCsmsUrl URL of the CSMS(s) that this Charging Station communicates with, without the
   *     Charging Station identity part
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
    return ocppCsmsUrl != null && ocppCsmsUrl.length() <= 2000;
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
    return securityProfile != null && securityProfile >= 0;
  }

  /**
   * Gets charging Station identity to be used as the basic authentication username.
   *
   * @return Charging Station identity to be used as the basic authentication username
   */
  @Nullable
  public String getIdentity() {
    return identity;
  }

  /**
   * Sets charging Station identity to be used as the basic authentication username.
   *
   * @param identity Charging Station identity to be used as the basic authentication username
   */
  public void setIdentity(@Nullable String identity) {
    if (!isValidIdentity(identity)) {
      throw new PropertyConstraintException(identity, "identity is invalid");
    }
    this.identity = identity;
  }

  /**
   * Returns whether the given identity is valid
   *
   * @param identity the identity to check the validity of
   * @return {@code true} if identity is valid, {@code false} if not
   */
  private boolean isValidIdentity(@Nullable String identity) {
    return identity == null || identity.length() <= 48;
  }

  /**
   * Adds charging Station identity to be used as the basic authentication username.
   *
   * @param identity Charging Station identity to be used as the basic authentication username
   * @return this
   */
  public NetworkConnectionProfile withIdentity(@Nullable String identity) {
    setIdentity(identity);
    return this;
  }

  /**
   * Gets basicAuthPassword to use for security profile 1 or 2.
   *
   * @return BasicAuthPassword to use for security profile 1 or 2
   */
  @Nullable
  public String getBasicAuthPassword() {
    return basicAuthPassword;
  }

  /**
   * Sets basicAuthPassword to use for security profile 1 or 2.
   *
   * @param basicAuthPassword BasicAuthPassword to use for security profile 1 or 2
   */
  public void setBasicAuthPassword(@Nullable String basicAuthPassword) {
    if (!isValidBasicAuthPassword(basicAuthPassword)) {
      throw new PropertyConstraintException(basicAuthPassword, "basicAuthPassword is invalid");
    }
    this.basicAuthPassword = basicAuthPassword;
  }

  /**
   * Returns whether the given basicAuthPassword is valid
   *
   * @param basicAuthPassword the basicAuthPassword to check the validity of
   * @return {@code true} if basicAuthPassword is valid, {@code false} if not
   */
  private boolean isValidBasicAuthPassword(@Nullable String basicAuthPassword) {
    return basicAuthPassword == null || basicAuthPassword.length() <= 64;
  }

  /**
   * Adds basicAuthPassword to use for security profile 1 or 2.
   *
   * @param basicAuthPassword BasicAuthPassword to use for security profile 1 or 2
   * @return this
   */
  public NetworkConnectionProfile withBasicAuthPassword(@Nullable String basicAuthPassword) {
    setBasicAuthPassword(basicAuthPassword);
    return this;
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

  public boolean validate() {
    return isValidApn(apn)
        && isValidOcppInterface(ocppInterface)
        && isValidOcppTransport(ocppTransport)
        && isValidMessageTimeout(messageTimeout)
        && isValidOcppCsmsUrl(ocppCsmsUrl)
        && isValidSecurityProfile(securityProfile)
        && isValidIdentity(identity)
        && isValidBasicAuthPassword(basicAuthPassword)
        && isValidVpn(vpn)
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
    NetworkConnectionProfile that = (NetworkConnectionProfile) o;
    return Objects.equals(apn, that.apn)
        && Objects.equals(ocppVersion, that.ocppVersion)
        && Objects.equals(ocppInterface, that.ocppInterface)
        && Objects.equals(ocppTransport, that.ocppTransport)
        && Objects.equals(messageTimeout, that.messageTimeout)
        && Objects.equals(ocppCsmsUrl, that.ocppCsmsUrl)
        && Objects.equals(securityProfile, that.securityProfile)
        && Objects.equals(identity, that.identity)
        && Objects.equals(basicAuthPassword, that.basicAuthPassword)
        && Objects.equals(vpn, that.vpn)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        apn,
        ocppVersion,
        ocppInterface,
        ocppTransport,
        messageTimeout,
        ocppCsmsUrl,
        securityProfile,
        identity,
        basicAuthPassword,
        vpn,
        customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("apn", apn)
        .add("ocppVersion", ocppVersion)
        .add("ocppInterface", ocppInterface)
        .add("ocppTransport", ocppTransport)
        .add("messageTimeout", messageTimeout)
        .add("ocppCsmsUrl", ocppCsmsUrl)
        .add("securityProfile", securityProfile)
        .add("identity", identity)
        .add("basicAuthPassword", basicAuthPassword)
        .add("vpn", vpn)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
