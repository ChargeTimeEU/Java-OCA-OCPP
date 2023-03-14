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
import java.util.Arrays;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * ClearVariableMonitoringRequest
 *
 * <p>OCPP 2.0.1 FINAL
 */
public final class ClearVariableMonitoringRequest extends RequestWithId {
  /** Custom data */
  @Nullable private CustomData customData;

  /** List of the monitors to be cleared, identified by there Id. */
  private Integer[] id;

  /**
   * Constructor for the ClearVariableMonitoringRequest class
   *
   * @param id List of the monitors to be cleared, identified by there Id.
   */
  public ClearVariableMonitoringRequest(Integer[] id) {
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
  public ClearVariableMonitoringRequest withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  /**
   * Gets list of the monitors to be cleared, identified by there Id.
   *
   * @return List of the monitors to be cleared, identified by there Id
   */
  public Integer[] getId() {
    return id;
  }

  /**
   * Sets list of the monitors to be cleared, identified by there Id.
   *
   * @param id List of the monitors to be cleared, identified by there Id
   */
  public void setId(Integer[] id) {
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
  private boolean isValidId(Integer[] id) {
    return id != null && id.length >= 1;
  }

  @Override
  public boolean validate() {
    return isValidCustomData(customData) && isValidId(id);
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
    ClearVariableMonitoringRequest that = (ClearVariableMonitoringRequest) o;
    return Objects.equals(customData, that.customData) && Arrays.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customData, Arrays.hashCode(id));
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("id", id)
        .add("isValid", validate())
        .toString();
  }
}
