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
 * A case insensitive identifier to use for the authorization and the type of authorization to
 * support multiple forms of identifiers.
 */
public final class AdditionalInfo {
  /** The additional IdToken. */
  private String additionalIdToken;

  /**
   * additionalInfo can be used to send extra information to CSMS in addition to the regular
   * authorization with IdToken. AdditionalInfo contains one or more custom types, which need to be
   * agreed upon by all parties involved. When the type is not supported, the CSMS/Charging Station
   * MAY ignore the additionalInfo.
   */
  private String type;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the AdditionalInfo class
   *
   * @param additionalIdToken The additional IdToken.
   * @param type additionalInfo can be used to send extra information to CSMS in addition to the
   *     regular authorization with IdToken. AdditionalInfo contains one or more custom types, which
   *     need to be agreed upon by all parties involved. When the type is not supported, the
   *     CSMS/Charging Station MAY ignore the additionalInfo.
   */
  public AdditionalInfo(String additionalIdToken, String type) {
    setAdditionalIdToken(additionalIdToken);
    setType(type);
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
    return additionalIdToken != null && additionalIdToken.length() <= 255;
  }

  /**
   * Gets additionalInfo can be used to send extra information to CSMS in addition to the regular
   * authorization with IdToken. AdditionalInfo contains one or more custom types, which need to be
   * agreed upon by all parties involved. When the type is not supported, the CSMS/Charging Station
   * MAY ignore the additionalInfo.
   *
   * @return additionalInfo can be used to send extra information to CSMS in addition to the regular
   *     authorization with IdToken
   */
  public String getType() {
    return type;
  }

  /**
   * Sets additionalInfo can be used to send extra information to CSMS in addition to the regular
   * authorization with IdToken. AdditionalInfo contains one or more custom types, which need to be
   * agreed upon by all parties involved. When the type is not supported, the CSMS/Charging Station
   * MAY ignore the additionalInfo.
   *
   * @param type additionalInfo can be used to send extra information to CSMS in addition to the
   *     regular authorization with IdToken
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

  public boolean validate() {
    return isValidAdditionalIdToken(additionalIdToken)
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
    AdditionalInfo that = (AdditionalInfo) o;
    return Objects.equals(additionalIdToken, that.additionalIdToken)
        && Objects.equals(type, that.type)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(additionalIdToken, type, customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("additionalIdToken", additionalIdToken)
        .add("type", type)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
