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
import eu.chargetime.ocpp.v201.model.types.MonitoringBaseEnum;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * SetMonitoringBaseRequest
 *
 * <p>OCPP 2.0.1 FINAL
 */
public final class SetMonitoringBaseRequest extends RequestWithId {
  /** Custom data */
  @Nullable private CustomData customData;

  /** Specify which monitoring base will be set */
  private MonitoringBaseEnum monitoringBase;

  /**
   * Constructor for the SetMonitoringBaseRequest class
   *
   * @param monitoringBase Specify which monitoring base will be set
   */
  public SetMonitoringBaseRequest(MonitoringBaseEnum monitoringBase) {
    setMonitoringBase(monitoringBase);
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
  public SetMonitoringBaseRequest withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  /**
   * Gets specify which monitoring base will be set
   *
   * @return Specify which monitoring base will be set
   */
  public MonitoringBaseEnum getMonitoringBase() {
    return monitoringBase;
  }

  /**
   * Sets specify which monitoring base will be set
   *
   * @param monitoringBase Specify which monitoring base will be set
   */
  public void setMonitoringBase(MonitoringBaseEnum monitoringBase) {
    if (!isValidMonitoringBase(monitoringBase)) {
      throw new PropertyConstraintException(monitoringBase, "monitoringBase is invalid");
    }
    this.monitoringBase = monitoringBase;
  }

  /**
   * Returns whether the given monitoringBase is valid
   *
   * @param monitoringBase the monitoringBase to check the validity of
   * @return {@code true} if monitoringBase is valid, {@code false} if not
   */
  private boolean isValidMonitoringBase(MonitoringBaseEnum monitoringBase) {
    return monitoringBase != null;
  }

  @Override
  public boolean validate() {
    return isValidCustomData(customData) && isValidMonitoringBase(monitoringBase);
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
    SetMonitoringBaseRequest that = (SetMonitoringBaseRequest) o;
    return Objects.equals(customData, that.customData)
        && Objects.equals(monitoringBase, that.monitoringBase);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customData, monitoringBase);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("monitoringBase", monitoringBase)
        .add("isValid", validate())
        .toString();
  }
}
