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
 * A case insensitive identifier to use for the authorization and the type of authorization to
 * support multiple forms of identifiers.
 */
public final class AdditionalInfo {
  /** Custom data */
  @Nullable private CustomData customData;

  /** The additional IdToken. */
  private String additionalIdToken;

  /**
   * The type of the additionalIdToken. This is a custom type, so the implementation needs to be
   * agreed upon by all involved parties.
   */
  private String type;

  /**
   * Constructor for the AdditionalInfo class
   *
   * @param additionalIdToken The additional IdToken.
   * @param type The type of the additionalIdToken. This is a custom type, so the implementation
   *     needs to be agreed upon by all involved parties.
   */
  public AdditionalInfo(String additionalIdToken, String type) {
    setAdditionalIdToken(additionalIdToken);
    setType(type);
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
  public AdditionalInfo withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  /**
   * Gets the additional IdToken.
   *
   * @return The additional IdToken
   */
  public String getAdditionalIdToken() {
    return additionalIdToken;
  }

  /**
   * Sets the additional IdToken.
   *
   * @param additionalIdToken The additional IdToken
   */
  public void setAdditionalIdToken(String additionalIdToken) {
    if (!isValidAdditionalIdToken(additionalIdToken)) {
      throw new PropertyConstraintException(additionalIdToken, "additionalIdToken is invalid");
    }
    this.additionalIdToken = additionalIdToken;
  }

  /**
   * Returns whether the given additionalIdToken is valid
   *
   * @param additionalIdToken the additionalIdToken to check the validity of
   * @return {@code true} if additionalIdToken is valid, {@code false} if not
   */
  private boolean isValidAdditionalIdToken(String additionalIdToken) {
    return additionalIdToken != null && additionalIdToken.length() <= 36;
  }

  /**
   * Gets the type of the additionalIdToken. This is a custom type, so the implementation needs to
   * be agreed upon by all involved parties.
   *
   * @return The type of the additionalIdToken
   */
  public String getType() {
    return type;
  }

  /**
   * Sets the type of the additionalIdToken. This is a custom type, so the implementation needs to
   * be agreed upon by all involved parties.
   *
   * @param type The type of the additionalIdToken
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
    return type != null && type.length() <= 50;
  }

  public boolean validate() {
    return isValidCustomData(customData)
        && isValidAdditionalIdToken(additionalIdToken)
        && isValidType(type);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AdditionalInfo that = (AdditionalInfo) o;
    return Objects.equals(customData, that.customData)
        && Objects.equals(additionalIdToken, that.additionalIdToken)
        && Objects.equals(type, that.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customData, additionalIdToken, type);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("additionalIdToken", additionalIdToken)
        .add("type", type)
        .add("isValid", validate())
        .toString();
  }
}
