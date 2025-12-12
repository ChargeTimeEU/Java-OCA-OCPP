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
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * NotifyWebPaymentStartedRequest
 *
 * <p>OCPP 2.1 Edition 1 (c) OCA, Creative Commons Attribution-NoDerivatives 4.0 International
 * Public License
 */
public final class NotifyWebPaymentStartedRequest extends RequestWithId {
  /** EVSE id for which transaction is requested. */
  private Integer evseId;

  /**
   * Timeout value in seconds after which no result of web payment process (e.g. QR code scanning)
   * is to be expected anymore.
   */
  private Integer timeout;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the NotifyWebPaymentStartedRequest class
   *
   * @param evseId EVSE id for which transaction is requested.
   * @param timeout Timeout value in seconds after which no result of web payment process (e.g. QR
   *     code scanning) is to be expected anymore.
   */
  public NotifyWebPaymentStartedRequest(Integer evseId, Integer timeout) {
    setEvseId(evseId);
    setTimeout(timeout);
  }

  /**
   * Gets EVSE id for which transaction is requested.
   *
   * @return EVSE id for which transaction is requested
   */
  public Integer getEvseId() {
    return evseId;
  }

  /**
   * Sets EVSE id for which transaction is requested.
   *
   * @param evseId EVSE id for which transaction is requested
   */
  public void setEvseId(Integer evseId) {
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
  private boolean isValidEvseId(Integer evseId) {
    return evseId != null && evseId >= 0;
  }

  /**
   * Gets timeout value in seconds after which no result of web payment process (e.g. QR code
   * scanning) is to be expected anymore.
   *
   * @return Timeout value in seconds after which no result of web payment process (e.g. QR code
   *     scanning) is to be expected anymore
   */
  public Integer getTimeout() {
    return timeout;
  }

  /**
   * Sets timeout value in seconds after which no result of web payment process (e.g. QR code
   * scanning) is to be expected anymore.
   *
   * @param timeout Timeout value in seconds after which no result of web payment process (e.g. QR
   *     code scanning) is to be expected anymore
   */
  public void setTimeout(Integer timeout) {
    if (!isValidTimeout(timeout)) {
      throw new PropertyConstraintException(timeout, "timeout is invalid");
    }
    this.timeout = timeout;
  }

  /**
   * Returns whether the given timeout is valid
   *
   * @param timeout the timeout to check the validity of
   * @return {@code true} if timeout is valid, {@code false} if not
   */
  private boolean isValidTimeout(Integer timeout) {
    return timeout != null;
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
  public NotifyWebPaymentStartedRequest withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  @Override
  public boolean validate() {
    return isValidEvseId(evseId) && isValidTimeout(timeout) && isValidCustomData(customData);
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
    NotifyWebPaymentStartedRequest that = (NotifyWebPaymentStartedRequest) o;
    return Objects.equals(evseId, that.evseId)
        && Objects.equals(timeout, that.timeout)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(evseId, timeout, customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("evseId", evseId)
        .add("timeout", timeout)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
