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

package eu.chargetime.ocpp.v21.model.messages;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.model.RequestWithId;
import eu.chargetime.ocpp.utilities.MoreObjects;
import eu.chargetime.ocpp.v21.model.types.ChargingProfile;
import eu.chargetime.ocpp.v21.model.types.CustomData;
import eu.chargetime.ocpp.v21.model.types.IdToken;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * RequestStartTransactionRequest
 *
 * <p>OCPP 2.1 Edition 1 (c) OCA, Creative Commons Attribution-NoDerivatives 4.0 International
 * Public License
 */
public final class RequestStartTransactionRequest extends RequestWithId {
  /** Number of the EVSE on which to start the transaction. EvseId SHALL be greater than 0 */
  @Nullable private Integer evseId;

  /**
   * A case insensitive identifier to use for the authorization and the type of authorization to
   * support multiple forms of identifiers.
   */
  @Nullable private IdToken groupIdToken;

  /**
   * A case insensitive identifier to use for the authorization and the type of authorization to
   * support multiple forms of identifiers.
   */
  private IdToken idToken;

  /**
   * Id given by the server to this start request. The Charging Station will return this in the
   * TransactionEventRequest, letting the server know which transaction was started for this
   * request. Use to start a transaction.
   */
  private Integer remoteStartId;

  /**
   * A ChargingProfile consists of 1 to 3 ChargingSchedules with a list of ChargingSchedulePeriods,
   * describing the amount of power or current that can be delivered per time interval.
   *
   * <p>image::images/ChargingProfile-Simple.png[]
   */
  @Nullable private ChargingProfile chargingProfile;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the RequestStartTransactionRequest class
   *
   * @param idToken A case insensitive identifier to use for the authorization and the type of
   *     authorization to support multiple forms of identifiers.
   * @param remoteStartId Id given by the server to this start request. The Charging Station will
   *     return this in the TransactionEventRequest, letting the server know which transaction was
   *     started for this request. Use to start a transaction.
   */
  public RequestStartTransactionRequest(IdToken idToken, Integer remoteStartId) {
    setIdToken(idToken);
    setRemoteStartId(remoteStartId);
  }

  /**
   * Gets number of the EVSE on which to start the transaction. EvseId SHALL be greater than 0
   *
   * @return Number of the EVSE on which to start the transaction
   */
  @Nullable
  public Integer getEvseId() {
    return evseId;
  }

  /**
   * Sets number of the EVSE on which to start the transaction. EvseId SHALL be greater than 0
   *
   * @param evseId Number of the EVSE on which to start the transaction
   */
  public void setEvseId(@Nullable Integer evseId) {
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
  private boolean isValidEvseId(@Nullable Integer evseId) {
    return evseId == null || (evseId >= 1);
  }

  /**
   * Adds number of the EVSE on which to start the transaction. EvseId SHALL be greater than 0
   *
   * @param evseId Number of the EVSE on which to start the transaction
   * @return this
   */
  public RequestStartTransactionRequest withEvseId(@Nullable Integer evseId) {
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
  public RequestStartTransactionRequest withGroupIdToken(@Nullable IdToken groupIdToken) {
    setGroupIdToken(groupIdToken);
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
   * Gets id given by the server to this start request. The Charging Station will return this in the
   * TransactionEventRequest, letting the server know which transaction was started for this
   * request. Use to start a transaction.
   *
   * @return Id given by the server to this start request
   */
  public Integer getRemoteStartId() {
    return remoteStartId;
  }

  /**
   * Sets id given by the server to this start request. The Charging Station will return this in the
   * TransactionEventRequest, letting the server know which transaction was started for this
   * request. Use to start a transaction.
   *
   * @param remoteStartId Id given by the server to this start request
   */
  public void setRemoteStartId(Integer remoteStartId) {
    if (!isValidRemoteStartId(remoteStartId)) {
      throw new PropertyConstraintException(remoteStartId, "remoteStartId is invalid");
    }
    this.remoteStartId = remoteStartId;
  }

  /**
   * Returns whether the given remoteStartId is valid
   *
   * @param remoteStartId the remoteStartId to check the validity of
   * @return {@code true} if remoteStartId is valid, {@code false} if not
   */
  private boolean isValidRemoteStartId(Integer remoteStartId) {
    return remoteStartId != null;
  }

  /**
   * Gets a ChargingProfile consists of 1 to 3 ChargingSchedules with a list of
   * ChargingSchedulePeriods, describing the amount of power or current that can be delivered per
   * time interval.
   *
   * @return A ChargingProfile consists of 1 to 3 ChargingSchedules with a list of
   *     ChargingSchedulePeriods, describing the amount of power or current that can be delivered
   *     per time interval
   */
  @Nullable
  public ChargingProfile getChargingProfile() {
    return chargingProfile;
  }

  /**
   * Sets a ChargingProfile consists of 1 to 3 ChargingSchedules with a list of
   * ChargingSchedulePeriods, describing the amount of power or current that can be delivered per
   * time interval.
   *
   * @param chargingProfile A ChargingProfile consists of 1 to 3 ChargingSchedules with a list of
   *     ChargingSchedulePeriods, describing the amount of power or current that can be delivered
   *     per time interval
   */
  public void setChargingProfile(@Nullable ChargingProfile chargingProfile) {
    if (!isValidChargingProfile(chargingProfile)) {
      throw new PropertyConstraintException(chargingProfile, "chargingProfile is invalid");
    }
    this.chargingProfile = chargingProfile;
  }

  /**
   * Returns whether the given chargingProfile is valid
   *
   * @param chargingProfile the chargingProfile to check the validity of
   * @return {@code true} if chargingProfile is valid, {@code false} if not
   */
  private boolean isValidChargingProfile(@Nullable ChargingProfile chargingProfile) {
    return chargingProfile == null || chargingProfile.validate();
  }

  /**
   * Adds a ChargingProfile consists of 1 to 3 ChargingSchedules with a list of
   * ChargingSchedulePeriods, describing the amount of power or current that can be delivered per
   * time interval.
   *
   * @param chargingProfile A ChargingProfile consists of 1 to 3 ChargingSchedules with a list of
   *     ChargingSchedulePeriods, describing the amount of power or current that can be delivered
   *     per time interval
   * @return this
   */
  public RequestStartTransactionRequest withChargingProfile(
      @Nullable ChargingProfile chargingProfile) {
    setChargingProfile(chargingProfile);
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
  public RequestStartTransactionRequest withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  @Override
  public boolean validate() {
    return isValidEvseId(evseId)
        && isValidGroupIdToken(groupIdToken)
        && isValidIdToken(idToken)
        && isValidRemoteStartId(remoteStartId)
        && isValidChargingProfile(chargingProfile)
        && isValidCustomData(customData);
  }

  @Override
  public boolean transactionRelated() {
    return false;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RequestStartTransactionRequest that = (RequestStartTransactionRequest) o;
    return Objects.equals(evseId, that.evseId)
        && Objects.equals(groupIdToken, that.groupIdToken)
        && Objects.equals(idToken, that.idToken)
        && Objects.equals(remoteStartId, that.remoteStartId)
        && Objects.equals(chargingProfile, that.chargingProfile)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(evseId, groupIdToken, idToken, remoteStartId, chargingProfile, customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("evseId", evseId)
        .add("groupIdToken", groupIdToken)
        .add("idToken", idToken)
        .add("remoteStartId", remoteStartId)
        .add("chargingProfile", chargingProfile)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
