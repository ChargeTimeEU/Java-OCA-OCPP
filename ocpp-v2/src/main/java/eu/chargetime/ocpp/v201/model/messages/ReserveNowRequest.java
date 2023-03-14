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

package eu.chargetime.ocpp.v201.model.messages;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.model.RequestWithId;
import eu.chargetime.ocpp.utilities.MoreObjects;
import eu.chargetime.ocpp.v201.model.types.ConnectorEnum;
import eu.chargetime.ocpp.v201.model.types.CustomData;
import eu.chargetime.ocpp.v201.model.types.IdToken;
import java.time.ZonedDateTime;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * ReserveNowRequest
 *
 * <p>OCPP 2.0.1 FINAL
 */
public final class ReserveNowRequest extends RequestWithId {
  /** Custom data */
  @Nullable private CustomData customData;

  /** Id of reservation. */
  private Integer id;

  /** Date and time at which the reservation expires. */
  private ZonedDateTime expiryDateTime;

  /** The connector type. */
  @Nullable private ConnectorEnum connectorType;

  /**
   * A case insensitive identifier to use for the authorization and the type of authorization to
   * support multiple forms of identifiers.
   */
  private IdToken idToken;

  /** ID of the evse to be reserved. */
  @Nullable private Integer evseId;

  /**
   * A case insensitive identifier to use for the authorization and the type of authorization to
   * support multiple forms of identifiers.
   */
  @Nullable private IdToken groupIdToken;

  /**
   * Constructor for the ReserveNowRequest class
   *
   * @param id Id of reservation.
   * @param expiryDateTime Date and time at which the reservation expires.
   * @param idToken A case insensitive identifier to use for the authorization and the type of
   *     authorization to support multiple forms of identifiers.
   */
  public ReserveNowRequest(Integer id, ZonedDateTime expiryDateTime, IdToken idToken) {
    setId(id);
    setExpiryDateTime(expiryDateTime);
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
  public ReserveNowRequest withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  /**
   * Gets id of reservation.
   *
   * @return Id of reservation
   */
  public Integer getId() {
    return id;
  }

  /**
   * Sets id of reservation.
   *
   * @param id Id of reservation
   */
  public void setId(Integer id) {
    if (!isValidId(id)) {
      throw new PropertyConstraintException(id, "id is invalid");
    }
    this.id = id;
  }

  /**
   * Returns whether the given id is valid
   *
   * @param id the id to check the validity of
   * @return {@code true} if id is valid, {@code false} if not
   */
  private boolean isValidId(Integer id) {
    return id != null;
  }

  /**
   * Gets date and time at which the reservation expires.
   *
   * @return Date and time at which the reservation expires
   */
  public ZonedDateTime getExpiryDateTime() {
    return expiryDateTime;
  }

  /**
   * Sets date and time at which the reservation expires.
   *
   * @param expiryDateTime Date and time at which the reservation expires
   */
  public void setExpiryDateTime(ZonedDateTime expiryDateTime) {
    if (!isValidExpiryDateTime(expiryDateTime)) {
      throw new PropertyConstraintException(expiryDateTime, "expiryDateTime is invalid");
    }
    this.expiryDateTime = expiryDateTime;
  }

  /**
   * Returns whether the given expiryDateTime is valid
   *
   * @param expiryDateTime the expiryDateTime to check the validity of
   * @return {@code true} if expiryDateTime is valid, {@code false} if not
   */
  private boolean isValidExpiryDateTime(ZonedDateTime expiryDateTime) {
    return expiryDateTime != null;
  }

  /**
   * Gets the connector type.
   *
   * @return The connector type
   */
  @Nullable
  public ConnectorEnum getConnectorType() {
    return connectorType;
  }

  /**
   * Sets the connector type.
   *
   * @param connectorType The connector type
   */
  public void setConnectorType(@Nullable ConnectorEnum connectorType) {
    this.connectorType = connectorType;
  }

  /**
   * Adds the connector type.
   *
   * @param connectorType The connector type
   * @return this
   */
  public ReserveNowRequest withConnectorType(@Nullable ConnectorEnum connectorType) {
    setConnectorType(connectorType);
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
   * Gets ID of the evse to be reserved.
   *
   * @return ID of the evse to be reserved
   */
  @Nullable
  public Integer getEvseId() {
    return evseId;
  }

  /**
   * Sets ID of the evse to be reserved.
   *
   * @param evseId ID of the evse to be reserved
   */
  public void setEvseId(@Nullable Integer evseId) {
    this.evseId = evseId;
  }

  /**
   * Adds ID of the evse to be reserved.
   *
   * @param evseId ID of the evse to be reserved
   * @return this
   */
  public ReserveNowRequest withEvseId(@Nullable Integer evseId) {
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
  public ReserveNowRequest withGroupIdToken(@Nullable IdToken groupIdToken) {
    setGroupIdToken(groupIdToken);
    return this;
  }

  @Override
  public boolean validate() {
    return isValidCustomData(customData)
        && isValidId(id)
        && isValidExpiryDateTime(expiryDateTime)
        && isValidIdToken(idToken)
        && isValidGroupIdToken(groupIdToken);
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
    ReserveNowRequest that = (ReserveNowRequest) o;
    return Objects.equals(customData, that.customData)
        && Objects.equals(id, that.id)
        && Objects.equals(expiryDateTime, that.expiryDateTime)
        && Objects.equals(connectorType, that.connectorType)
        && Objects.equals(idToken, that.idToken)
        && Objects.equals(evseId, that.evseId)
        && Objects.equals(groupIdToken, that.groupIdToken);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        customData, id, expiryDateTime, connectorType, idToken, evseId, groupIdToken);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("id", id)
        .add("expiryDateTime", expiryDateTime)
        .add("connectorType", connectorType)
        .add("idToken", idToken)
        .add("evseId", evseId)
        .add("groupIdToken", groupIdToken)
        .add("isValid", validate())
        .toString();
  }
}
