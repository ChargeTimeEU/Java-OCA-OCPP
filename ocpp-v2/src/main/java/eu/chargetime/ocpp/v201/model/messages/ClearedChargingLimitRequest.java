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
import eu.chargetime.ocpp.v201.model.types.ChargingLimitSourceEnum;
import eu.chargetime.ocpp.v201.model.types.CustomData;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * ClearedChargingLimitRequest
 *
 * <p>OCPP 2.0.1 FINAL
 */
public final class ClearedChargingLimitRequest extends RequestWithId {
  /** Custom data */
  @Nullable private CustomData customData;

  /** Source of the charging limit. */
  private ChargingLimitSourceEnum chargingLimitSource;

  /** EVSE Identifier. */
  @Nullable private Integer evseId;

  /**
   * Constructor for the ClearedChargingLimitRequest class
   *
   * @param chargingLimitSource Source of the charging limit.
   */
  public ClearedChargingLimitRequest(ChargingLimitSourceEnum chargingLimitSource) {
    setChargingLimitSource(chargingLimitSource);
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
  public ClearedChargingLimitRequest withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  /**
   * Gets source of the charging limit.
   *
   * @return Source of the charging limit
   */
  public ChargingLimitSourceEnum getChargingLimitSource() {
    return chargingLimitSource;
  }

  /**
   * Sets source of the charging limit.
   *
   * @param chargingLimitSource Source of the charging limit
   */
  public void setChargingLimitSource(ChargingLimitSourceEnum chargingLimitSource) {
    if (!isValidChargingLimitSource(chargingLimitSource)) {
      throw new PropertyConstraintException(chargingLimitSource, "chargingLimitSource is invalid");
    }
    this.chargingLimitSource = chargingLimitSource;
  }

  /**
   * Returns whether the given chargingLimitSource is valid
   *
   * @param chargingLimitSource the chargingLimitSource to check the validity of
   * @return {@code true} if chargingLimitSource is valid, {@code false} if not
   */
  private boolean isValidChargingLimitSource(ChargingLimitSourceEnum chargingLimitSource) {
    return chargingLimitSource != null;
  }

  /**
   * Gets EVSE Identifier.
   *
   * @return EVSE Identifier
   */
  @Nullable
  public Integer getEvseId() {
    return evseId;
  }

  /**
   * Sets EVSE Identifier.
   *
   * @param evseId EVSE Identifier
   */
  public void setEvseId(@Nullable Integer evseId) {
    this.evseId = evseId;
  }

  /**
   * Adds EVSE Identifier.
   *
   * @param evseId EVSE Identifier
   * @return this
   */
  public ClearedChargingLimitRequest withEvseId(@Nullable Integer evseId) {
    setEvseId(evseId);
    return this;
  }

  @Override
  public boolean validate() {
    return isValidCustomData(customData) && isValidChargingLimitSource(chargingLimitSource);
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
    ClearedChargingLimitRequest that = (ClearedChargingLimitRequest) o;
    return Objects.equals(customData, that.customData)
        && Objects.equals(chargingLimitSource, that.chargingLimitSource)
        && Objects.equals(evseId, that.evseId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customData, chargingLimitSource, evseId);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("chargingLimitSource", chargingLimitSource)
        .add("evseId", evseId)
        .add("isValid", validate())
        .toString();
  }
}
