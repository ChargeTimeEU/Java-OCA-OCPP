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

/** A signed version of the meter value. */
public final class SignedMeterValue {
  /**
   * Base64 encoded, contains the signed data from the meter in the format specified in
   * encodingMethod, which might contain more then just the meter value. It can contain information
   * like timestamps, reference to a customer etc.
   */
  private String signedMeterData;

  /**
   * Method used to create the digital signature. Optional, if already included in signedMeterData.
   * Standard values for this are defined in Appendix as SigningMethodEnumStringType.
   */
  @Nullable private String signingMethod;

  /** Format used by the energy meter to encode the meter data. For example: OCMF or EDL. */
  private String encodingMethod;

  /** Base64 encoded, sending depends on configuration variable PublicKeyWithSignedMeterValue. */
  @Nullable private String publicKey;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the SignedMeterValue class
   *
   * @param signedMeterData Base64 encoded, contains the signed data from the meter in the format
   *     specified in encodingMethod, which might contain more then just the meter value. It can
   *     contain information like timestamps, reference to a customer etc.
   * @param encodingMethod Format used by the energy meter to encode the meter data. For example:
   *     OCMF or EDL.
   */
  public SignedMeterValue(String signedMeterData, String encodingMethod) {
    setSignedMeterData(signedMeterData);
    setEncodingMethod(encodingMethod);
  }

  /**
   * Gets base64 encoded, contains the signed data from the meter in the format specified in
   * encodingMethod, which might contain more then just the meter value. It can contain information
   * like timestamps, reference to a customer etc.
   *
   * @return Base64 encoded, contains the signed data from the meter in the format specified in
   *     encodingMethod, which might contain more then just the meter value
   */
  public String getSignedMeterData() {
    return signedMeterData;
  }

  /**
   * Sets base64 encoded, contains the signed data from the meter in the format specified in
   * encodingMethod, which might contain more then just the meter value. It can contain information
   * like timestamps, reference to a customer etc.
   *
   * @param signedMeterData Base64 encoded, contains the signed data from the meter in the format
   *     specified in encodingMethod, which might contain more then just the meter value
   */
  public void setSignedMeterData(String signedMeterData) {
    if (!isValidSignedMeterData(signedMeterData)) {
      throw new PropertyConstraintException(signedMeterData, "signedMeterData is invalid");
    }
    this.signedMeterData = signedMeterData;
  }

  /**
   * Returns whether the given signedMeterData is valid
   *
   * @param signedMeterData the signedMeterData to check the validity of
   * @return {@code true} if signedMeterData is valid, {@code false} if not
   */
  private boolean isValidSignedMeterData(String signedMeterData) {
    return signedMeterData != null && signedMeterData.length() <= 32768;
  }

  /**
   * Gets method used to create the digital signature. Optional, if already included in
   * signedMeterData. Standard values for this are defined in Appendix as
   * SigningMethodEnumStringType.
   *
   * @return Method used to create the digital signature
   */
  @Nullable
  public String getSigningMethod() {
    return signingMethod;
  }

  /**
   * Sets method used to create the digital signature. Optional, if already included in
   * signedMeterData. Standard values for this are defined in Appendix as
   * SigningMethodEnumStringType.
   *
   * @param signingMethod Method used to create the digital signature
   */
  public void setSigningMethod(@Nullable String signingMethod) {
    if (!isValidSigningMethod(signingMethod)) {
      throw new PropertyConstraintException(signingMethod, "signingMethod is invalid");
    }
    this.signingMethod = signingMethod;
  }

  /**
   * Returns whether the given signingMethod is valid
   *
   * @param signingMethod the signingMethod to check the validity of
   * @return {@code true} if signingMethod is valid, {@code false} if not
   */
  private boolean isValidSigningMethod(@Nullable String signingMethod) {
    return signingMethod == null || signingMethod.length() <= 50;
  }

  /**
   * Adds method used to create the digital signature. Optional, if already included in
   * signedMeterData. Standard values for this are defined in Appendix as
   * SigningMethodEnumStringType.
   *
   * @param signingMethod Method used to create the digital signature
   * @return this
   */
  public SignedMeterValue withSigningMethod(@Nullable String signingMethod) {
    setSigningMethod(signingMethod);
    return this;
  }

  /**
   * Gets format used by the energy meter to encode the meter data. For example: OCMF or EDL.
   *
   * @return Format used by the energy meter to encode the meter data
   */
  public String getEncodingMethod() {
    return encodingMethod;
  }

  /**
   * Sets format used by the energy meter to encode the meter data. For example: OCMF or EDL.
   *
   * @param encodingMethod Format used by the energy meter to encode the meter data
   */
  public void setEncodingMethod(String encodingMethod) {
    if (!isValidEncodingMethod(encodingMethod)) {
      throw new PropertyConstraintException(encodingMethod, "encodingMethod is invalid");
    }
    this.encodingMethod = encodingMethod;
  }

  /**
   * Returns whether the given encodingMethod is valid
   *
   * @param encodingMethod the encodingMethod to check the validity of
   * @return {@code true} if encodingMethod is valid, {@code false} if not
   */
  private boolean isValidEncodingMethod(String encodingMethod) {
    return encodingMethod != null && encodingMethod.length() <= 50;
  }

  /**
   * Gets base64 encoded, sending depends on configuration variable PublicKeyWithSignedMeterValue.
   *
   * @return Base64 encoded, sending depends on configuration variable PublicKeyWithSignedMeterValue
   */
  @Nullable
  public String getPublicKey() {
    return publicKey;
  }

  /**
   * Sets base64 encoded, sending depends on configuration variable PublicKeyWithSignedMeterValue.
   *
   * @param publicKey Base64 encoded, sending depends on configuration variable
   *     PublicKeyWithSignedMeterValue
   */
  public void setPublicKey(@Nullable String publicKey) {
    if (!isValidPublicKey(publicKey)) {
      throw new PropertyConstraintException(publicKey, "publicKey is invalid");
    }
    this.publicKey = publicKey;
  }

  /**
   * Returns whether the given publicKey is valid
   *
   * @param publicKey the publicKey to check the validity of
   * @return {@code true} if publicKey is valid, {@code false} if not
   */
  private boolean isValidPublicKey(@Nullable String publicKey) {
    return publicKey == null || publicKey.length() <= 2500;
  }

  /**
   * Adds base64 encoded, sending depends on configuration variable PublicKeyWithSignedMeterValue.
   *
   * @param publicKey Base64 encoded, sending depends on configuration variable
   *     PublicKeyWithSignedMeterValue
   * @return this
   */
  public SignedMeterValue withPublicKey(@Nullable String publicKey) {
    setPublicKey(publicKey);
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
  public SignedMeterValue withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  public boolean validate() {
    return isValidSignedMeterData(signedMeterData)
        && isValidSigningMethod(signingMethod)
        && isValidEncodingMethod(encodingMethod)
        && isValidPublicKey(publicKey)
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
    SignedMeterValue that = (SignedMeterValue) o;
    return Objects.equals(signedMeterData, that.signedMeterData)
        && Objects.equals(signingMethod, that.signingMethod)
        && Objects.equals(encodingMethod, that.encodingMethod)
        && Objects.equals(publicKey, that.publicKey)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(signedMeterData, signingMethod, encodingMethod, publicKey, customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("signedMeterData", signedMeterData)
        .add("signingMethod", signingMethod)
        .add("encodingMethod", encodingMethod)
        .add("publicKey", publicKey)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
