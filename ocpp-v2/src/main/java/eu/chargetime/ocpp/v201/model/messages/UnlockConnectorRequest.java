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
import eu.chargetime.ocpp.v201.model.types.CustomData;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * UnlockConnectorRequest
 *
 * <p>OCPP 2.0.1 FINAL
 */
public final class UnlockConnectorRequest extends RequestWithId {
  /** Custom data */
  @Nullable private CustomData customData;

  /** The identifier of the EVSE for which a connector needs to be unlocked. */
  private Integer evseId;

  /** The identifier of the connector that needs to be unlocked. */
  private Integer connectorId;

  /**
   * Constructor for the UnlockConnectorRequest class
   *
   * @param evseId The identifier of the EVSE for which a connector needs to be unlocked.
   * @param connectorId The identifier of the connector that needs to be unlocked.
   */
  public UnlockConnectorRequest(Integer evseId, Integer connectorId) {
    setEvseId(evseId);
    setConnectorId(connectorId);
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
  public UnlockConnectorRequest withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  /**
   * Gets the identifier of the EVSE for which a connector needs to be unlocked.
   *
   * @return The identifier of the EVSE for which a connector needs to be unlocked
   */
  public Integer getEvseId() {
    return evseId;
  }

  /**
   * Sets the identifier of the EVSE for which a connector needs to be unlocked.
   *
   * @param evseId The identifier of the EVSE for which a connector needs to be unlocked
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
    return evseId != null;
  }

  /**
   * Gets the identifier of the connector that needs to be unlocked.
   *
   * @return The identifier of the connector that needs to be unlocked
   */
  public Integer getConnectorId() {
    return connectorId;
  }

  /**
   * Sets the identifier of the connector that needs to be unlocked.
   *
   * @param connectorId The identifier of the connector that needs to be unlocked
   */
  public void setConnectorId(Integer connectorId) {
    if (!isValidConnectorId(connectorId)) {
      throw new PropertyConstraintException(connectorId, "connectorId is invalid");
    }
    this.connectorId = connectorId;
  }

  /**
   * Returns whether the given connectorId is valid
   *
   * @param connectorId the connectorId to check the validity of
   * @return {@code true} if connectorId is valid, {@code false} if not
   */
  private boolean isValidConnectorId(Integer connectorId) {
    return connectorId != null;
  }

  @Override
  public boolean validate() {
    return isValidCustomData(customData)
        && isValidEvseId(evseId)
        && isValidConnectorId(connectorId);
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
    UnlockConnectorRequest that = (UnlockConnectorRequest) o;
    return Objects.equals(customData, that.customData)
        && Objects.equals(evseId, that.evseId)
        && Objects.equals(connectorId, that.connectorId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customData, evseId, connectorId);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("evseId", evseId)
        .add("connectorId", connectorId)
        .add("isValid", validate())
        .toString();
  }
}
