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
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * EVSE
 *
 * <p>Electric Vehicle Supply Equipment
 */
public final class EVSE {
  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Identified Object. MRID. Numeric Identifier
   *
   * <p>EVSE Identifier. This contains a number (greater than 0) designating an EVSE of the Charging
   * Station.
   */
  private Integer id;

  /** An id to designate a specific connector (on an EVSE) by connector index number. */
  @Nullable private Integer connectorId;

  /**
   * Constructor for the EVSE class
   *
   * @param id EVSE Identifier. This contains a number (greater than 0) designating an EVSE of the
   *     Charging Station.
   */
  public EVSE(Integer id) {
    setId(id);
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
  public EVSE withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  /**
   * Gets EVSE Identifier. This contains a number (greater than 0) designating an EVSE of the
   * Charging Station.
   *
   * @return EVSE Identifier
   */
  public Integer getId() {
    return id;
  }

  /**
   * Sets EVSE Identifier. This contains a number (greater than 0) designating an EVSE of the
   * Charging Station.
   *
   * @param id EVSE Identifier
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
   * Gets an id to designate a specific connector (on an EVSE) by connector index number.
   *
   * @return An id to designate a specific connector (on an EVSE) by connector index number
   */
  @Nullable
  public Integer getConnectorId() {
    return connectorId;
  }

  /**
   * Sets an id to designate a specific connector (on an EVSE) by connector index number.
   *
   * @param connectorId An id to designate a specific connector (on an EVSE) by connector index
   *     number
   */
  public void setConnectorId(@Nullable Integer connectorId) {
    this.connectorId = connectorId;
  }

  /**
   * Adds an id to designate a specific connector (on an EVSE) by connector index number.
   *
   * @param connectorId An id to designate a specific connector (on an EVSE) by connector index
   *     number
   * @return this
   */
  public EVSE withConnectorId(@Nullable Integer connectorId) {
    setConnectorId(connectorId);
    return this;
  }

  public boolean validate() {
    return isValidCustomData(customData) && isValidId(id);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EVSE that = (EVSE) o;
    return Objects.equals(customData, that.customData)
        && Objects.equals(id, that.id)
        && Objects.equals(connectorId, that.connectorId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customData, id, connectorId);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("id", id)
        .add("connectorId", connectorId)
        .add("isValid", validate())
        .toString();
  }
}
