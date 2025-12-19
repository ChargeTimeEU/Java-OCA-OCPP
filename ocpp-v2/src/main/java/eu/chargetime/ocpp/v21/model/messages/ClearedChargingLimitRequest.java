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
 * ClearedChargingLimitRequest
 *
 * <p>OCPP 2.1 Edition 1 (c) OCA, Creative Commons Attribution-NoDerivatives 4.0 International
 * Public License
 */
public final class ClearedChargingLimitRequest extends RequestWithId {
  /**
   * Source of the charging limit. Allowed values defined in Appendix as
   * ChargingLimitSourceEnumStringType.
   */
  private String chargingLimitSource;

  /** EVSE Identifier. */
  @Nullable private Integer evseId;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the ClearedChargingLimitRequest class
   *
   * @param chargingLimitSource Source of the charging limit. Allowed values defined in Appendix as
   *     ChargingLimitSourceEnumStringType.
   */
  public ClearedChargingLimitRequest(String chargingLimitSource) {
    setChargingLimitSource(chargingLimitSource);
  }

  /**
   * Gets source of the charging limit. Allowed values defined in Appendix as
   * ChargingLimitSourceEnumStringType.
   *
   * @return Source of the charging limit
   */
  public String getChargingLimitSource() {
    return chargingLimitSource;
  }

  /**
   * Sets source of the charging limit. Allowed values defined in Appendix as
   * ChargingLimitSourceEnumStringType.
   *
   * @param chargingLimitSource Source of the charging limit
   */
  public void setChargingLimitSource(String chargingLimitSource) {
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
  private boolean isValidChargingLimitSource(String chargingLimitSource) {
    return chargingLimitSource != null && chargingLimitSource.length() <= 20;
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
    return evseId == null || (evseId >= 0);
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

  @Override
  public boolean validate() {
    return isValidChargingLimitSource(chargingLimitSource)
        && isValidEvseId(evseId)
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
    ClearedChargingLimitRequest that = (ClearedChargingLimitRequest) o;
    return Objects.equals(chargingLimitSource, that.chargingLimitSource)
        && Objects.equals(evseId, that.evseId)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(chargingLimitSource, evseId, customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("chargingLimitSource", chargingLimitSource)
        .add("evseId", evseId)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
