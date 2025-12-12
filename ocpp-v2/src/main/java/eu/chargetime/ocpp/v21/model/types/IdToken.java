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
import java.util.Arrays;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * A case insensitive identifier to use for the authorization and the type of authorization to
 * support multiple forms of identifiers.
 */
public final class IdToken {
  /**
   * A case insensitive identifier to use for the authorization and the type of authorization to
   * support multiple forms of identifiers.
   */
  @Nullable private AdditionalInfo[] additionalInfo;

  /**
   * IdToken is case insensitive. Might hold the hidden id of an RFID tag, but can for example also
   * contain a UUID.
   */
  private String idToken;

  /** Enumeration of possible idToken types. Values defined in Appendix as IdTokenEnumStringType. */
  private String type;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the IdToken class
   *
   * @param idToken IdToken is case insensitive. Might hold the hidden id of an RFID tag, but can
   *     for example also contain a UUID.
   * @param type Enumeration of possible idToken types. Values defined in Appendix as
   *     IdTokenEnumStringType.
   */
  public IdToken(String idToken, String type) {
    setIdToken(idToken);
    setType(type);
  }

  /**
   * Gets a case insensitive identifier to use for the authorization and the type of authorization
   * to support multiple forms of identifiers.
   *
   * @return A case insensitive identifier to use for the authorization and the type of
   *     authorization to support multiple forms of identifiers
   */
  @Nullable
  public AdditionalInfo[] getAdditionalInfo() {
    return additionalInfo;
  }

  /**
   * Sets a case insensitive identifier to use for the authorization and the type of authorization
   * to support multiple forms of identifiers.
   *
   * @param additionalInfo A case insensitive identifier to use for the authorization and the type
   *     of authorization to support multiple forms of identifiers
   */
  public void setAdditionalInfo(@Nullable AdditionalInfo[] additionalInfo) {
    if (!isValidAdditionalInfo(additionalInfo)) {
      throw new PropertyConstraintException(additionalInfo, "additionalInfo is invalid");
    }
    this.additionalInfo = additionalInfo;
  }

  /**
   * Returns whether the given additionalInfo is valid
   *
   * @param additionalInfo the additionalInfo to check the validity of
   * @return {@code true} if additionalInfo is valid, {@code false} if not
   */
  private boolean isValidAdditionalInfo(@Nullable AdditionalInfo[] additionalInfo) {
    return additionalInfo == null
        || (additionalInfo.length >= 1
            && Arrays.stream(additionalInfo).allMatch(item -> item.validate()));
  }

  /**
   * Adds a case insensitive identifier to use for the authorization and the type of authorization
   * to support multiple forms of identifiers.
   *
   * @param additionalInfo A case insensitive identifier to use for the authorization and the type
   *     of authorization to support multiple forms of identifiers
   * @return this
   */
  public IdToken withAdditionalInfo(@Nullable AdditionalInfo[] additionalInfo) {
    setAdditionalInfo(additionalInfo);
    return this;
  }

  /**
   * Gets idToken is case insensitive. Might hold the hidden id of an RFID tag, but can for example
   * also contain a UUID.
   *
   * @return IdToken is case insensitive
   */
  public String getIdToken() {
    return idToken;
  }

  /**
   * Sets idToken is case insensitive. Might hold the hidden id of an RFID tag, but can for example
   * also contain a UUID.
   *
   * @param idToken IdToken is case insensitive
   */
  public void setIdToken(String idToken) {
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
  private boolean isValidIdToken(String idToken) {
    return idToken != null && idToken.length() <= 255;
  }

  /**
   * Gets enumeration of possible idToken types. Values defined in Appendix as
   * IdTokenEnumStringType.
   *
   * @return Enumeration of possible idToken types
   */
  public String getType() {
    return type;
  }

  /**
   * Sets enumeration of possible idToken types. Values defined in Appendix as
   * IdTokenEnumStringType.
   *
   * @param type Enumeration of possible idToken types
   */
  public void setType(String type) {
    if (!isValidType(type)) {
      throw new PropertyConstraintException(type, "type is invalid");
    }
    this.type = type;
  }

  /**
   * Returns whether the given type is valid
   *
   * @param type the type to check the validity of
   * @return {@code true} if type is valid, {@code false} if not
   */
  private boolean isValidType(String type) {
    return type != null && type.length() <= 20;
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
  public IdToken withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  public boolean validate() {
    return isValidAdditionalInfo(additionalInfo)
        && isValidIdToken(idToken)
        && isValidType(type)
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
    IdToken that = (IdToken) o;
    return Arrays.equals(additionalInfo, that.additionalInfo)
        && Objects.equals(idToken, that.idToken)
        && Objects.equals(type, that.type)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(Arrays.hashCode(additionalInfo), idToken, type, customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("additionalInfo", additionalInfo)
        .add("idToken", idToken)
        .add("type", type)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
