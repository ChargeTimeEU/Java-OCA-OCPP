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
import eu.chargetime.ocpp.v201.model.types.ResetEnum;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * ResetRequest
 *
 * <p>OCPP 2.0.1 FINAL
 */
public final class ResetRequest extends RequestWithId {
  /** Custom data */
  @Nullable private CustomData customData;

  /** The type of reset that the Charging Station or EVSE should perform. */
  private ResetEnum type;

  /** The ID of a specific EVSE that needs to be reset, instead of the entire Charging Station. */
  @Nullable private Integer evseId;

  /**
   * Constructor for the ResetRequest class
   *
   * @param type The type of reset that the Charging Station or EVSE should perform.
   */
  public ResetRequest(ResetEnum type) {
    setType(type);
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
  public ResetRequest withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  /**
   * Gets the type of reset that the Charging Station or EVSE should perform.
   *
   * @return The type of reset that the Charging Station or EVSE should perform
   */
  public ResetEnum getType() {
    return type;
  }

  /**
   * Sets the type of reset that the Charging Station or EVSE should perform.
   *
   * @param type The type of reset that the Charging Station or EVSE should perform
   */
  public void setType(ResetEnum type) {
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
  private boolean isValidType(ResetEnum type) {
    return type != null;
  }

  /**
   * Gets the ID of a specific EVSE that needs to be reset, instead of the entire Charging Station.
   *
   * @return The ID of a specific EVSE that needs to be reset, instead of the entire Charging
   *     Station
   */
  @Nullable
  public Integer getEvseId() {
    return evseId;
  }

  /**
   * Sets the ID of a specific EVSE that needs to be reset, instead of the entire Charging Station.
   *
   * @param evseId The ID of a specific EVSE that needs to be reset, instead of the entire Charging
   *     Station
   */
  public void setEvseId(@Nullable Integer evseId) {
    this.evseId = evseId;
  }

  /**
   * Adds the ID of a specific EVSE that needs to be reset, instead of the entire Charging Station.
   *
   * @param evseId The ID of a specific EVSE that needs to be reset, instead of the entire Charging
   *     Station
   * @return this
   */
  public ResetRequest withEvseId(@Nullable Integer evseId) {
    setEvseId(evseId);
    return this;
  }

  @Override
  public boolean validate() {
    return isValidCustomData(customData) && isValidType(type);
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
    ResetRequest that = (ResetRequest) o;
    return Objects.equals(customData, that.customData)
        && Objects.equals(type, that.type)
        && Objects.equals(evseId, that.evseId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customData, type, evseId);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("type", type)
        .add("evseId", evseId)
        .add("isValid", validate())
        .toString();
  }
}
