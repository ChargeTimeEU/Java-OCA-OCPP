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
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * ID Token
 *
 * <p>Status information about an identifier. It is advised to not stop charging for a token that
 * expires during charging, as ExpiryDate is only used for caching purposes. If ExpiryDate is not
 * given, the status has no end date.
 */
public final class IdTokenInfo {
  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * ID Token. Status. Authorization Status
   *
   * <p>Current status of the ID Token.
   */
  private AuthorizationStatusEnum status;

  /**
   * ID Token. Expiry. Date Time
   *
   * <p>Date and Time after which the token must be considered invalid.
   */
  @Nullable private ZonedDateTime cacheExpiryDateTime;

  /**
   * Priority from a business point of view. Default priority is 0, The range is from -9 to 9.
   * Higher values indicate a higher priority. The chargingPriority in TransactionEventResponse
   * overrules this one.
   */
  @Nullable private Integer chargingPriority;

  /**
   * ID Token. Language1. Language Code
   *
   * <p>Preferred user interface language of identifier user. Contains a language code as defined in
   * [RFC5646].
   */
  @Nullable private String language1;

  /**
   * Only used when the IdToken is only valid for one or more specific EVSEs, not for the entire
   * Charging Station.
   */
  @Nullable private Integer[] evseId;

  /**
   * A case insensitive identifier to use for the authorization and the type of authorization to
   * support multiple forms of identifiers.
   */
  @Nullable private IdToken groupIdToken;

  /**
   * ID Token. Language2. Language Code
   *
   * <p>Second preferred user interface language of identifier user. Don’t use when language1 is
   * omitted, has to be different from language1. Contains a language code as defined in [RFC5646].
   */
  @Nullable private String language2;

  /**
   * Message Content
   *
   * <p>Message details, for a message to be displayed on a Charging Station.
   */
  @Nullable private MessageContent personalMessage;

  /**
   * Constructor for the IdTokenInfo class
   *
   * @param status Current status of the ID Token.
   */
  public IdTokenInfo(AuthorizationStatusEnum status) {
    setStatus(status);
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
  public IdTokenInfo withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  /**
   * Gets current status of the ID Token.
   *
   * @return Current status of the ID Token
   */
  public AuthorizationStatusEnum getStatus() {
    return status;
  }

  /**
   * Sets current status of the ID Token.
   *
   * @param status Current status of the ID Token
   */
  public void setStatus(AuthorizationStatusEnum status) {
    if (!isValidStatus(status)) {
      throw new PropertyConstraintException(status, "status is invalid");
    }
    this.status = status;
  }

  /**
   * Returns whether the given status is valid
   *
   * @param status the status to check the validity of
   * @return {@code true} if status is valid, {@code false} if not
   */
  private boolean isValidStatus(AuthorizationStatusEnum status) {
    return status != null;
  }

  /**
   * Gets date and Time after which the token must be considered invalid.
   *
   * @return Date and Time after which the token must be considered invalid
   */
  @Nullable
  public ZonedDateTime getCacheExpiryDateTime() {
    return cacheExpiryDateTime;
  }

  /**
   * Sets date and Time after which the token must be considered invalid.
   *
   * @param cacheExpiryDateTime Date and Time after which the token must be considered invalid
   */
  public void setCacheExpiryDateTime(@Nullable ZonedDateTime cacheExpiryDateTime) {
    this.cacheExpiryDateTime = cacheExpiryDateTime;
  }

  /**
   * Adds date and Time after which the token must be considered invalid.
   *
   * @param cacheExpiryDateTime Date and Time after which the token must be considered invalid
   * @return this
   */
  public IdTokenInfo withCacheExpiryDateTime(@Nullable ZonedDateTime cacheExpiryDateTime) {
    setCacheExpiryDateTime(cacheExpiryDateTime);
    return this;
  }

  /**
   * Gets priority from a business point of view. Default priority is 0, The range is from -9 to 9.
   * Higher values indicate a higher priority. The chargingPriority in TransactionEventResponse
   * overrules this one.
   *
   * @return Priority from a business point of view
   */
  @Nullable
  public Integer getChargingPriority() {
    return chargingPriority;
  }

  /**
   * Sets priority from a business point of view. Default priority is 0, The range is from -9 to 9.
   * Higher values indicate a higher priority. The chargingPriority in TransactionEventResponse
   * overrules this one.
   *
   * @param chargingPriority Priority from a business point of view
   */
  public void setChargingPriority(@Nullable Integer chargingPriority) {
    this.chargingPriority = chargingPriority;
  }

  /**
   * Adds priority from a business point of view. Default priority is 0, The range is from -9 to 9.
   * Higher values indicate a higher priority. The chargingPriority in TransactionEventResponse
   * overrules this one.
   *
   * @param chargingPriority Priority from a business point of view
   * @return this
   */
  public IdTokenInfo withChargingPriority(@Nullable Integer chargingPriority) {
    setChargingPriority(chargingPriority);
    return this;
  }

  /**
   * Gets preferred user interface language of identifier user. Contains a language code as defined
   * in [RFC5646].
   *
   * @return Preferred user interface language of identifier user
   */
  @Nullable
  public String getLanguage1() {
    return language1;
  }

  /**
   * Sets preferred user interface language of identifier user. Contains a language code as defined
   * in [RFC5646].
   *
   * @param language1 Preferred user interface language of identifier user
   */
  public void setLanguage1(@Nullable String language1) {
    if (!isValidLanguage1(language1)) {
      throw new PropertyConstraintException(language1, "language1 is invalid");
    }
    this.language1 = language1;
  }

  /**
   * Returns whether the given language1 is valid
   *
   * @param language1 the language1 to check the validity of
   * @return {@code true} if language1 is valid, {@code false} if not
   */
  private boolean isValidLanguage1(@Nullable String language1) {
    return language1 == null || language1.length() <= 8;
  }

  /**
   * Adds preferred user interface language of identifier user. Contains a language code as defined
   * in [RFC5646].
   *
   * @param language1 Preferred user interface language of identifier user
   * @return this
   */
  public IdTokenInfo withLanguage1(@Nullable String language1) {
    setLanguage1(language1);
    return this;
  }

  /**
   * Gets only used when the IdToken is only valid for one or more specific EVSEs, not for the
   * entire Charging Station.
   *
   * @return Only used when the IdToken is only valid for one or more specific EVSEs, not for the
   *     entire Charging Station
   */
  @Nullable
  public Integer[] getEvseId() {
    return evseId;
  }

  /**
   * Sets only used when the IdToken is only valid for one or more specific EVSEs, not for the
   * entire Charging Station.
   *
   * @param evseId Only used when the IdToken is only valid for one or more specific EVSEs, not for
   *     the entire Charging Station
   */
  public void setEvseId(@Nullable Integer[] evseId) {
    if (!isValidEvseId(evseId)) {
      throw new PropertyConstraintException(evseId, "evseId is invalid");
    }
    this.evseId = evseId;
  }

  /**
   * Returns whether the given evseId is valid
   *
   * @param evseId the evseId to check the validity of
   * @return {@code true} if evseId is valid, {@code false} if not
   */
  private boolean isValidEvseId(@Nullable Integer[] evseId) {
    return evseId == null || (evseId.length >= 1);
  }

  /**
   * Adds only used when the IdToken is only valid for one or more specific EVSEs, not for the
   * entire Charging Station.
   *
   * @param evseId Only used when the IdToken is only valid for one or more specific EVSEs, not for
   *     the entire Charging Station
   * @return this
   */
  public IdTokenInfo withEvseId(@Nullable Integer[] evseId) {
    setEvseId(evseId);
    return this;
  }

  /**
   * Gets a case insensitive identifier to use for the authorization and the type of authorization
   * to support multiple forms of identifiers.
   *
   * @return A case insensitive identifier to use for the authorization and the type of
   *     authorization to support multiple forms of identifiers
   */
  @Nullable
  public IdToken getGroupIdToken() {
    return groupIdToken;
  }

  /**
   * Sets a case insensitive identifier to use for the authorization and the type of authorization
   * to support multiple forms of identifiers.
   *
   * @param groupIdToken A case insensitive identifier to use for the authorization and the type of
   *     authorization to support multiple forms of identifiers
   */
  public void setGroupIdToken(@Nullable IdToken groupIdToken) {
    if (!isValidGroupIdToken(groupIdToken)) {
      throw new PropertyConstraintException(groupIdToken, "groupIdToken is invalid");
    }
    this.groupIdToken = groupIdToken;
  }

  /**
   * Returns whether the given groupIdToken is valid
   *
   * @param groupIdToken the groupIdToken to check the validity of
   * @return {@code true} if groupIdToken is valid, {@code false} if not
   */
  private boolean isValidGroupIdToken(@Nullable IdToken groupIdToken) {
    return groupIdToken == null || groupIdToken.validate();
  }

  /**
   * Adds a case insensitive identifier to use for the authorization and the type of authorization
   * to support multiple forms of identifiers.
   *
   * @param groupIdToken A case insensitive identifier to use for the authorization and the type of
   *     authorization to support multiple forms of identifiers
   * @return this
   */
  public IdTokenInfo withGroupIdToken(@Nullable IdToken groupIdToken) {
    setGroupIdToken(groupIdToken);
    return this;
  }

  /**
   * Gets second preferred user interface language of identifier user. Don’t use when language1 is
   * omitted, has to be different from language1. Contains a language code as defined in [RFC5646].
   *
   * @return Second preferred user interface language of identifier user
   */
  @Nullable
  public String getLanguage2() {
    return language2;
  }

  /**
   * Sets second preferred user interface language of identifier user. Don’t use when language1 is
   * omitted, has to be different from language1. Contains a language code as defined in [RFC5646].
   *
   * @param language2 Second preferred user interface language of identifier user
   */
  public void setLanguage2(@Nullable String language2) {
    if (!isValidLanguage2(language2)) {
      throw new PropertyConstraintException(language2, "language2 is invalid");
    }
    this.language2 = language2;
  }

  /**
   * Returns whether the given language2 is valid
   *
   * @param language2 the language2 to check the validity of
   * @return {@code true} if language2 is valid, {@code false} if not
   */
  private boolean isValidLanguage2(@Nullable String language2) {
    return language2 == null || language2.length() <= 8;
  }

  /**
   * Adds second preferred user interface language of identifier user. Don’t use when language1 is
   * omitted, has to be different from language1. Contains a language code as defined in [RFC5646].
   *
   * @param language2 Second preferred user interface language of identifier user
   * @return this
   */
  public IdTokenInfo withLanguage2(@Nullable String language2) {
    setLanguage2(language2);
    return this;
  }

  /**
   * Gets message details, for a message to be displayed on a Charging Station.
   *
   * @return Message details, for a message to be displayed on a Charging Station
   */
  @Nullable
  public MessageContent getPersonalMessage() {
    return personalMessage;
  }

  /**
   * Sets message details, for a message to be displayed on a Charging Station.
   *
   * @param personalMessage Message details, for a message to be displayed on a Charging Station
   */
  public void setPersonalMessage(@Nullable MessageContent personalMessage) {
    if (!isValidPersonalMessage(personalMessage)) {
      throw new PropertyConstraintException(personalMessage, "personalMessage is invalid");
    }
    this.personalMessage = personalMessage;
  }

  /**
   * Returns whether the given personalMessage is valid
   *
   * @param personalMessage the personalMessage to check the validity of
   * @return {@code true} if personalMessage is valid, {@code false} if not
   */
  private boolean isValidPersonalMessage(@Nullable MessageContent personalMessage) {
    return personalMessage == null || personalMessage.validate();
  }

  /**
   * Adds message details, for a message to be displayed on a Charging Station.
   *
   * @param personalMessage Message details, for a message to be displayed on a Charging Station
   * @return this
   */
  public IdTokenInfo withPersonalMessage(@Nullable MessageContent personalMessage) {
    setPersonalMessage(personalMessage);
    return this;
  }

  public boolean validate() {
    return isValidCustomData(customData)
        && isValidStatus(status)
        && isValidLanguage1(language1)
        && isValidEvseId(evseId)
        && isValidGroupIdToken(groupIdToken)
        && isValidLanguage2(language2)
        && isValidPersonalMessage(personalMessage);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    IdTokenInfo that = (IdTokenInfo) o;
    return Objects.equals(customData, that.customData)
        && Objects.equals(status, that.status)
        && Objects.equals(cacheExpiryDateTime, that.cacheExpiryDateTime)
        && Objects.equals(chargingPriority, that.chargingPriority)
        && Objects.equals(language1, that.language1)
        && Arrays.equals(evseId, that.evseId)
        && Objects.equals(groupIdToken, that.groupIdToken)
        && Objects.equals(language2, that.language2)
        && Objects.equals(personalMessage, that.personalMessage);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        customData,
        status,
        cacheExpiryDateTime,
        chargingPriority,
        language1,
        Arrays.hashCode(evseId),
        groupIdToken,
        language2,
        personalMessage);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("status", status)
        .add("cacheExpiryDateTime", cacheExpiryDateTime)
        .add("chargingPriority", chargingPriority)
        .add("language1", language1)
        .add("evseId", evseId)
        .add("groupIdToken", groupIdToken)
        .add("language2", language2)
        .add("personalMessage", personalMessage)
        .add("isValid", validate())
        .toString();
  }
}
