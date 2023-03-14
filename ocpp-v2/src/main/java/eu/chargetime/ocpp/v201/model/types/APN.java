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
 * APN
 *
 * <p>Collection of configuration data needed to make a data-connection over a cellular network.
 *
 * <p>NOTE: When asking a GSM modem to dial in, it is possible to specify which mobile operator
 * should be used. This can be done with the mobile country code (MCC) in combination with a mobile
 * network code (MNC). Example: If your preferred network is Vodafone Netherlands, the MCC=204 and
 * the MNC=04 which means the key PreferredNetwork = 20404 Some modems allows to specify a preferred
 * network, which means, if this network is not available, a different network is used. If you
 * specify UseOnlyPreferredNetwork and this network is not available, the modem will not dial in.
 */
public final class APN {
  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * APN. APN. URI
   *
   * <p>The Access Point Name as an URL.
   */
  private String apn;

  /**
   * APN. APN. User Name
   *
   * <p>APN username.
   */
  @Nullable private String apnUserName;

  /**
   * APN. APN. Password
   *
   * <p>APN Password.
   */
  @Nullable private String apnPassword;

  /**
   * APN. SIMPIN. PIN Code
   *
   * <p>SIM card pin code.
   */
  @Nullable private Integer simPin;

  /**
   * APN. Preferred Network. Mobile Network ID
   *
   * <p>Preferred network, written as MCC and MNC concatenated. See note.
   */
  @Nullable private String preferredNetwork;

  /**
   * APN. Use Only Preferred Network. Indicator
   *
   * <p>Default: false. Use only the preferred Network, do not dial in when not available. See Note.
   */
  @Nullable private Boolean useOnlyPreferredNetwork;

  /**
   * APN. APN Authentication. APN Authentication Code
   *
   * <p>Authentication method.
   */
  private APNAuthenticationEnum apnAuthentication;

  /**
   * Constructor for the APN class
   *
   * @param apn The Access Point Name as an URL.
   * @param apnAuthentication Authentication method.
   */
  public APN(String apn, APNAuthenticationEnum apnAuthentication) {
    setApn(apn);
    setApnAuthentication(apnAuthentication);
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
  public APN withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  /**
   * Gets the Access Point Name as an URL.
   *
   * @return The Access Point Name as an URL
   */
  public String getApn() {
    return apn;
  }

  /**
   * Sets the Access Point Name as an URL.
   *
   * @param apn The Access Point Name as an URL
   */
  public void setApn(String apn) {
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
  private boolean isValidApn(String apn) {
    return apn != null && apn.length() <= 512;
  }

  /**
   * Gets APN username.
   *
   * @return APN username
   */
  @Nullable
  public String getApnUserName() {
    return apnUserName;
  }

  /**
   * Sets APN username.
   *
   * @param apnUserName APN username
   */
  public void setApnUserName(@Nullable String apnUserName) {
    if (!isValidApnUserName(apnUserName)) {
      throw new PropertyConstraintException(apnUserName, "apnUserName is invalid");
    }
    this.apnUserName = apnUserName;
  }

  /**
   * Returns whether the given apnUserName is valid
   *
   * @param apnUserName the apnUserName to check the validity of
   * @return {@code true} if apnUserName is valid, {@code false} if not
   */
  private boolean isValidApnUserName(@Nullable String apnUserName) {
    return apnUserName == null || apnUserName.length() <= 20;
  }

  /**
   * Adds APN username.
   *
   * @param apnUserName APN username
   * @return this
   */
  public APN withApnUserName(@Nullable String apnUserName) {
    setApnUserName(apnUserName);
    return this;
  }

  /**
   * Gets APN Password.
   *
   * @return APN Password
   */
  @Nullable
  public String getApnPassword() {
    return apnPassword;
  }

  /**
   * Sets APN Password.
   *
   * @param apnPassword APN Password
   */
  public void setApnPassword(@Nullable String apnPassword) {
    if (!isValidApnPassword(apnPassword)) {
      throw new PropertyConstraintException(apnPassword, "apnPassword is invalid");
    }
    this.apnPassword = apnPassword;
  }

  /**
   * Returns whether the given apnPassword is valid
   *
   * @param apnPassword the apnPassword to check the validity of
   * @return {@code true} if apnPassword is valid, {@code false} if not
   */
  private boolean isValidApnPassword(@Nullable String apnPassword) {
    return apnPassword == null || apnPassword.length() <= 20;
  }

  /**
   * Adds APN Password.
   *
   * @param apnPassword APN Password
   * @return this
   */
  public APN withApnPassword(@Nullable String apnPassword) {
    setApnPassword(apnPassword);
    return this;
  }

  /**
   * Gets SIM card pin code.
   *
   * @return SIM card pin code
   */
  @Nullable
  public Integer getSimPin() {
    return simPin;
  }

  /**
   * Sets SIM card pin code.
   *
   * @param simPin SIM card pin code
   */
  public void setSimPin(@Nullable Integer simPin) {
    this.simPin = simPin;
  }

  /**
   * Adds SIM card pin code.
   *
   * @param simPin SIM card pin code
   * @return this
   */
  public APN withSimPin(@Nullable Integer simPin) {
    setSimPin(simPin);
    return this;
  }

  /**
   * Gets preferred network, written as MCC and MNC concatenated. See note.
   *
   * @return Preferred network, written as MCC and MNC concatenated
   */
  @Nullable
  public String getPreferredNetwork() {
    return preferredNetwork;
  }

  /**
   * Sets preferred network, written as MCC and MNC concatenated. See note.
   *
   * @param preferredNetwork Preferred network, written as MCC and MNC concatenated
   */
  public void setPreferredNetwork(@Nullable String preferredNetwork) {
    if (!isValidPreferredNetwork(preferredNetwork)) {
      throw new PropertyConstraintException(preferredNetwork, "preferredNetwork is invalid");
    }
    this.preferredNetwork = preferredNetwork;
  }

  /**
   * Returns whether the given preferredNetwork is valid
   *
   * @param preferredNetwork the preferredNetwork to check the validity of
   * @return {@code true} if preferredNetwork is valid, {@code false} if not
   */
  private boolean isValidPreferredNetwork(@Nullable String preferredNetwork) {
    return preferredNetwork == null || preferredNetwork.length() <= 6;
  }

  /**
   * Adds preferred network, written as MCC and MNC concatenated. See note.
   *
   * @param preferredNetwork Preferred network, written as MCC and MNC concatenated
   * @return this
   */
  public APN withPreferredNetwork(@Nullable String preferredNetwork) {
    setPreferredNetwork(preferredNetwork);
    return this;
  }

  /**
   * Gets default: false. Use only the preferred Network, do not dial in when not available. See
   * Note.
   *
   * @return Default: false
   */
  public Boolean getUseOnlyPreferredNetwork() {
    return useOnlyPreferredNetwork != null ? useOnlyPreferredNetwork : false;
  }

  /**
   * Sets default: false. Use only the preferred Network, do not dial in when not available. See
   * Note.
   *
   * @param useOnlyPreferredNetwork Default: false
   */
  public void setUseOnlyPreferredNetwork(@Nullable Boolean useOnlyPreferredNetwork) {
    this.useOnlyPreferredNetwork = useOnlyPreferredNetwork;
  }

  /**
   * Adds default: false. Use only the preferred Network, do not dial in when not available. See
   * Note.
   *
   * @param useOnlyPreferredNetwork Default: false
   * @return this
   */
  public APN withUseOnlyPreferredNetwork(@Nullable Boolean useOnlyPreferredNetwork) {
    setUseOnlyPreferredNetwork(useOnlyPreferredNetwork);
    return this;
  }

  /**
   * Gets authentication method.
   *
   * @return Authentication method
   */
  public APNAuthenticationEnum getApnAuthentication() {
    return apnAuthentication;
  }

  /**
   * Sets authentication method.
   *
   * @param apnAuthentication Authentication method
   */
  public void setApnAuthentication(APNAuthenticationEnum apnAuthentication) {
    if (!isValidApnAuthentication(apnAuthentication)) {
      throw new PropertyConstraintException(apnAuthentication, "apnAuthentication is invalid");
    }
    this.apnAuthentication = apnAuthentication;
  }

  /**
   * Returns whether the given apnAuthentication is valid
   *
   * @param apnAuthentication the apnAuthentication to check the validity of
   * @return {@code true} if apnAuthentication is valid, {@code false} if not
   */
  private boolean isValidApnAuthentication(APNAuthenticationEnum apnAuthentication) {
    return apnAuthentication != null;
  }

  public boolean validate() {
    return isValidCustomData(customData)
        && isValidApn(apn)
        && isValidApnUserName(apnUserName)
        && isValidApnPassword(apnPassword)
        && isValidPreferredNetwork(preferredNetwork)
        && isValidApnAuthentication(apnAuthentication);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    APN that = (APN) o;
    return Objects.equals(customData, that.customData)
        && Objects.equals(apn, that.apn)
        && Objects.equals(apnUserName, that.apnUserName)
        && Objects.equals(apnPassword, that.apnPassword)
        && Objects.equals(simPin, that.simPin)
        && Objects.equals(preferredNetwork, that.preferredNetwork)
        && Objects.equals(useOnlyPreferredNetwork, that.useOnlyPreferredNetwork)
        && Objects.equals(apnAuthentication, that.apnAuthentication);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        customData,
        apn,
        apnUserName,
        apnPassword,
        simPin,
        preferredNetwork,
        useOnlyPreferredNetwork,
        apnAuthentication);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("apn", apn)
        .add("apnUserName", apnUserName)
        .add("apnPassword", apnPassword)
        .add("simPin", simPin)
        .add("preferredNetwork", preferredNetwork)
        .add("useOnlyPreferredNetwork", useOnlyPreferredNetwork)
        .add("apnAuthentication", apnAuthentication)
        .add("isValid", validate())
        .toString();
  }
}
