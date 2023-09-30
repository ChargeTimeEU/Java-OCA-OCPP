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

/** A signed version of the meter value. */
public final class SignedMeterValue {
  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Base64 encoded, contains the signed data which might contain more then just the meter value. It
   * can contain information like timestamps, reference to a customer etc.
   */
  private String signedMeterData;

  /** Method used to create the digital signature. */
  private String signingMethod;

  /** Method used to encode the meter values before applying the digital signature algorithm. */
  private String encodingMethod;

  /** Base64 encoded, sending depends on configuration variable PublicKeyWithSignedMeterValue. */
  private String publicKey;

  /**
   * Constructor for the SignedMeterValue class
   *
   * @param signedMeterData Base64 encoded, contains the signed data which might contain more then
   *     just the meter value. It can contain information like timestamps, reference to a customer
   *     etc.
   * @param signingMethod Method used to create the digital signature.
   * @param encodingMethod Method used to encode the meter values before applying the digital
   *     signature algorithm.
   * @param publicKey Base64 encoded, sending depends on configuration variable
   *     PublicKeyWithSignedMeterValue.
   */
  public SignedMeterValue(
      String signedMeterData, String signingMethod, String encodingMethod, String publicKey) {
    setSignedMeterData(signedMeterData);
    setSigningMethod(signingMethod);
    setEncodingMethod(encodingMethod);
    setPublicKey(publicKey);
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

  /**
   * Gets base64 encoded, contains the signed data which might contain more then just the meter
   * value. It can contain information like timestamps, reference to a customer etc.
   *
   * @return Base64 encoded, contains the signed data which might contain more then just the meter
   *     value
   */
  public String getSignedMeterData() {
    return signedMeterData;
  }

  /**
   * Sets base64 encoded, contains the signed data which might contain more then just the meter
   * value. It can contain information like timestamps, reference to a customer etc.
   *
   * @param signedMeterData Base64 encoded, contains the signed data which might contain more then
   *     just the meter value
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
    return signedMeterData != null && signedMeterData.length() <= 2500;
  }

  /**
   * Gets method used to create the digital signature.
   *
   * @return Method used to create the digital signature
   */
  public String getSigningMethod() {
    return signingMethod;
  }

  /**
   * Sets method used to create the digital signature.
   *
   * @param signingMethod Method used to create the digital signature
   */
  public void setSigningMethod(String signingMethod) {
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
  private boolean isValidSigningMethod(String signingMethod) {
    return signingMethod != null && signingMethod.length() <= 50;
  }

  /**
   * Gets method used to encode the meter values before applying the digital signature algorithm.
   *
   * @return Method used to encode the meter values before applying the digital signature algorithm
   */
  public String getEncodingMethod() {
    return encodingMethod;
  }

  /**
   * Sets method used to encode the meter values before applying the digital signature algorithm.
   *
   * @param encodingMethod Method used to encode the meter values before applying the digital
   *     signature algorithm
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
  public String getPublicKey() {
    return publicKey;
  }

  /**
   * Sets base64 encoded, sending depends on configuration variable PublicKeyWithSignedMeterValue.
   *
   * @param publicKey Base64 encoded, sending depends on configuration variable
   *     PublicKeyWithSignedMeterValue
   */
  public void setPublicKey(String publicKey) {
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
  private boolean isValidPublicKey(String publicKey) {
    return publicKey != null && publicKey.length() <= 2500;
  }

  public boolean validate() {
    return isValidCustomData(customData)
        && isValidSignedMeterData(signedMeterData)
        && isValidSigningMethod(signingMethod)
        && isValidEncodingMethod(encodingMethod)
        && isValidPublicKey(publicKey);
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
    return Objects.equals(customData, that.customData)
        && Objects.equals(signedMeterData, that.signedMeterData)
        && Objects.equals(signingMethod, that.signingMethod)
        && Objects.equals(encodingMethod, that.encodingMethod)
        && Objects.equals(publicKey, that.publicKey);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customData, signedMeterData, signingMethod, encodingMethod, publicKey);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("signedMeterData", signedMeterData)
        .add("signingMethod", signingMethod)
        .add("encodingMethod", encodingMethod)
        .add("publicKey", publicKey)
        .add("isValid", validate())
        .toString();
  }
}
