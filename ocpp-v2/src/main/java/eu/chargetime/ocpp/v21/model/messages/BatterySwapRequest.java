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
import eu.chargetime.ocpp.v21.model.types.BatteryData;
import eu.chargetime.ocpp.v21.model.types.BatterySwapEventEnum;
import eu.chargetime.ocpp.v21.model.types.CustomData;
import eu.chargetime.ocpp.v21.model.types.IdToken;
import java.util.Arrays;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * BatterySwapRequest
 *
 * <p>OCPP 2.1 Edition 1 (c) OCA, Creative Commons Attribution-NoDerivatives 4.0 International
 * Public License
 */
public final class BatterySwapRequest extends RequestWithId {
  /** batteryData */
  private BatteryData[] batteryData;

  /** Battery in/out */
  private BatterySwapEventEnum eventType;

  /**
   * A case insensitive identifier to use for the authorization and the type of authorization to
   * support multiple forms of identifiers.
   */
  private IdToken idToken;

  /** RequestId to correlate BatteryIn/Out events and optional RequestBatterySwapRequest. */
  private Integer requestId;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the BatterySwapRequest class
   *
   * @param batteryData batteryData
   * @param eventType Battery in/out
   * @param idToken A case insensitive identifier to use for the authorization and the type of
   *     authorization to support multiple forms of identifiers.
   * @param requestId RequestId to correlate BatteryIn/Out events and optional
   *     RequestBatterySwapRequest.
   */
  public BatterySwapRequest(
      BatteryData[] batteryData,
      BatterySwapEventEnum eventType,
      IdToken idToken,
      Integer requestId) {
    setBatteryData(batteryData);
    setEventType(eventType);
    setIdToken(idToken);
    setRequestId(requestId);
  }

  /**
   * Gets batteryData
   *
   * @return batteryData
   */
  public BatteryData[] getBatteryData() {
    return batteryData;
  }

  /**
   * Sets batteryData
   *
   * @param batteryData batteryData
   */
  public void setBatteryData(BatteryData[] batteryData) {
    if (!isValidBatteryData(batteryData)) {
      throw new PropertyConstraintException(batteryData, "batteryData is invalid");
    }
    this.batteryData = batteryData;
  }

  /**
   * Returns whether the given batteryData is valid
   *
   * @param batteryData the batteryData to check the validity of
   * @return {@code true} if batteryData is valid, {@code false} if not
   */
  private boolean isValidBatteryData(BatteryData[] batteryData) {
    return batteryData != null
        && batteryData.length >= 1
        && Arrays.stream(batteryData).allMatch(item -> item.validate());
  }

  /**
   * Gets battery in/out
   *
   * @return Battery in/out
   */
  public BatterySwapEventEnum getEventType() {
    return eventType;
  }

  /**
   * Sets battery in/out
   *
   * @param eventType Battery in/out
   */
  public void setEventType(BatterySwapEventEnum eventType) {
    if (!isValidEventType(eventType)) {
      throw new PropertyConstraintException(eventType, "eventType is invalid");
    }
    this.eventType = eventType;
  }

  /**
   * Returns whether the given eventType is valid
   *
   * @param eventType the eventType to check the validity of
   * @return {@code true} if eventType is valid, {@code false} if not
   */
  private boolean isValidEventType(BatterySwapEventEnum eventType) {
    return eventType != null;
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
   * Gets requestId to correlate BatteryIn/Out events and optional RequestBatterySwapRequest.
   *
   * @return RequestId to correlate BatteryIn/Out events and optional RequestBatterySwapRequest
   */
  public Integer getRequestId() {
    return requestId;
  }

  /**
   * Sets requestId to correlate BatteryIn/Out events and optional RequestBatterySwapRequest.
   *
   * @param requestId RequestId to correlate BatteryIn/Out events and optional
   *     RequestBatterySwapRequest
   */
  public void setRequestId(Integer requestId) {
    if (!isValidRequestId(requestId)) {
      throw new PropertyConstraintException(requestId, "requestId is invalid");
    }
    this.requestId = requestId;
  }

  /**
   * Returns whether the given requestId is valid
   *
   * @param requestId the requestId to check the validity of
   * @return {@code true} if requestId is valid, {@code false} if not
   */
  private boolean isValidRequestId(Integer requestId) {
    return requestId != null;
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
  public BatterySwapRequest withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  @Override
  public boolean validate() {
    return isValidBatteryData(batteryData)
        && isValidEventType(eventType)
        && isValidIdToken(idToken)
        && isValidRequestId(requestId)
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
    BatterySwapRequest that = (BatterySwapRequest) o;
    return Arrays.equals(batteryData, that.batteryData)
        && Objects.equals(eventType, that.eventType)
        && Objects.equals(idToken, that.idToken)
        && Objects.equals(requestId, that.requestId)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(Arrays.hashCode(batteryData), eventType, idToken, requestId, customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("batteryData", batteryData)
        .add("eventType", eventType)
        .add("idToken", idToken)
        .add("requestId", requestId)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
