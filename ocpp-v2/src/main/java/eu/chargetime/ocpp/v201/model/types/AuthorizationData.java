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

/** The identifier to use for authorization. */
public final class AuthorizationData {
  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * A case insensitive identifier to use for the authorization and the type of authorization to
   * support multiple forms of identifiers.
   */
  private IdToken idToken;

  /**
   * ID Token
   *
   * <p>Status information about an identifier. It is advised to not stop charging for a token that
   * expires during charging, as ExpiryDate is only used for caching purposes. If ExpiryDate is not
   * given, the status has no end date.
   */
  @Nullable private IdTokenInfo idTokenInfo;

  /**
   * Constructor for the AuthorizationData class
   *
   * @param idToken A case insensitive identifier to use for the authorization and the type of
   *     authorization to support multiple forms of identifiers.
   */
  public AuthorizationData(IdToken idToken) {
    setIdToken(idToken);
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
  public AuthorizationData withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  /**
   * Gets a case insensitive identifier to use for the authorization and the type of authorization
   * to support multiple forms of identifiers.
   *
   * @return A case insensitive identifier to use for the authorization and the type of
   *     authorization to support multiple forms of identifiers
   */
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
  public void setIdToken(IdToken idToken) {
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
  private boolean isValidIdToken(IdToken idToken) {
    return idToken != null && idToken.validate();
  }

  /**
   * Gets status information about an identifier. It is advised to not stop charging for a token
   * that expires during charging, as ExpiryDate is only used for caching purposes. If ExpiryDate is
   * not given, the status has no end date.
   *
   * @return Status information about an identifier
   */
  @Nullable
  public IdTokenInfo getIdTokenInfo() {
    return idTokenInfo;
  }

  /**
   * Sets status information about an identifier. It is advised to not stop charging for a token
   * that expires during charging, as ExpiryDate is only used for caching purposes. If ExpiryDate is
   * not given, the status has no end date.
   *
   * @param idTokenInfo Status information about an identifier
   */
  public void setIdTokenInfo(@Nullable IdTokenInfo idTokenInfo) {
    if (!isValidIdTokenInfo(idTokenInfo)) {
      throw new PropertyConstraintException(idTokenInfo, "idTokenInfo is invalid");
    }
    this.idTokenInfo = idTokenInfo;
  }

  /**
   * Returns whether the given idTokenInfo is valid
   *
   * @param idTokenInfo the idTokenInfo to check the validity of
   * @return {@code true} if idTokenInfo is valid, {@code false} if not
   */
  private boolean isValidIdTokenInfo(@Nullable IdTokenInfo idTokenInfo) {
    return idTokenInfo == null || idTokenInfo.validate();
  }

  /**
   * Adds status information about an identifier. It is advised to not stop charging for a token
   * that expires during charging, as ExpiryDate is only used for caching purposes. If ExpiryDate is
   * not given, the status has no end date.
   *
   * @param idTokenInfo Status information about an identifier
   * @return this
   */
  public AuthorizationData withIdTokenInfo(@Nullable IdTokenInfo idTokenInfo) {
    setIdTokenInfo(idTokenInfo);
    return this;
  }

  public boolean validate() {
    return isValidCustomData(customData)
        && isValidIdToken(idToken)
        && isValidIdTokenInfo(idTokenInfo);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AuthorizationData that = (AuthorizationData) o;
    return Objects.equals(customData, that.customData)
        && Objects.equals(idToken, that.idToken)
        && Objects.equals(idTokenInfo, that.idTokenInfo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customData, idToken, idTokenInfo);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("idToken", idToken)
        .add("idTokenInfo", idTokenInfo)
        .add("isValid", validate())
        .toString();
  }
}
