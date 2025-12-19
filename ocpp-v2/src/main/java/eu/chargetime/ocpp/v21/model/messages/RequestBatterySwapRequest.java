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
import eu.chargetime.ocpp.v21.model.types.CustomData;
import eu.chargetime.ocpp.v21.model.types.IdToken;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * RequestBatterySwapRequest
 *
 * <p>OCPP 2.1 Edition 1 (c) OCA, Creative Commons Attribution-NoDerivatives 4.0 International
 * Public License
 */
public final class RequestBatterySwapRequest extends RequestWithId {
  /**
   * A case insensitive identifier to use for the authorization and the type of authorization to
   * support multiple forms of identifiers.
   */
  private IdToken idToken;

  /** Request id to match with BatterySwapRequest. */
  private Integer requestId;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the RequestBatterySwapRequest class
   *
   * @param idToken A case insensitive identifier to use for the authorization and the type of
   *     authorization to support multiple forms of identifiers.
   * @param requestId Request id to match with BatterySwapRequest.
   */
  public RequestBatterySwapRequest(IdToken idToken, Integer requestId) {
    setIdToken(idToken);
    setRequestId(requestId);
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
   * Gets request id to match with BatterySwapRequest.
   *
   * @return Request id to match with BatterySwapRequest
   */
  public Integer getRequestId() {
    return requestId;
  }

  /**
   * Sets request id to match with BatterySwapRequest.
   *
   * @param requestId Request id to match with BatterySwapRequest
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
  public RequestBatterySwapRequest withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  @Override
  public boolean validate() {
    return isValidIdToken(idToken) && isValidRequestId(requestId) && isValidCustomData(customData);
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
    RequestBatterySwapRequest that = (RequestBatterySwapRequest) o;
    return Objects.equals(idToken, that.idToken)
        && Objects.equals(requestId, that.requestId)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idToken, requestId, customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("idToken", idToken)
        .add("requestId", requestId)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
