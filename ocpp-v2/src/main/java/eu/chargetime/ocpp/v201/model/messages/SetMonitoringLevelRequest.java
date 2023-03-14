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
 * SetMonitoringLevelRequest
 *
 * <p>OCPP 2.0.1 FINAL
 */
public final class SetMonitoringLevelRequest extends RequestWithId {
  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * The Charging Station SHALL only report events with a severity number lower than or equal to
   * this severity. The severity range is 0-9, with 0 as the highest and 9 as the lowest severity
   * level.
   *
   * <pre>
   * The severity levels have the following meaning: +
   * *0-Danger* +
   * Lives are potentially in danger. Urgent attention is needed and action should be taken immediately. +
   * *1-Hardware Failure* +
   * That the Charging Station is unable to continue regular operations due to Hardware issues. Action is required. +
   * *2-System Failure* +
   * That the Charging Station is unable to continue regular operations due to software or minor hardware issues. Action is required. +
   * *3-Critical* +
   * A critical error. Action is required. +
   * *4-Error* +
   * A non-urgent error. Action is required. +
   * *5-Alert* +
   * An alert event. Default severity for any type of monitoring event. +
   * *6-Warning* +
   * A warning event. Action may be required. +
   * *7-Notice* +
   * An unusual event. No immediate action is required. +
   * *8-Informational* +
   * A regular operational event. May be used for reporting, measuring throughput, etc. No action is required. +
   * *9-Debug* +
   * Information useful to developers for debugging, not useful during operations.
   * </pre>
   */
  private Integer severity;

  /**
   * Constructor for the SetMonitoringLevelRequest class
   *
   * @param severity The Charging Station SHALL only report events with a severity number lower than
   *     or equal to this severity. The severity range is 0-9, with 0 as the highest and 9 as the
   *     lowest severity level.
   */
  public SetMonitoringLevelRequest(Integer severity) {
    setSeverity(severity);
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
  public SetMonitoringLevelRequest withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  /**
   * Gets the Charging Station SHALL only report events with a severity number lower than or equal
   * to this severity. The severity range is 0-9, with 0 as the highest and 9 as the lowest severity
   * level.
   *
   * @return The Charging Station SHALL only report events with a severity number lower than or
   *     equal to this severity
   */
  public Integer getSeverity() {
    return severity;
  }

  /**
   * Sets the Charging Station SHALL only report events with a severity number lower than or equal
   * to this severity. The severity range is 0-9, with 0 as the highest and 9 as the lowest severity
   * level.
   *
   * @param severity The Charging Station SHALL only report events with a severity number lower than
   *     or equal to this severity
   */
  public void setSeverity(Integer severity) {
    if (!isValidSeverity(severity)) {
      throw new PropertyConstraintException(severity, "severity is invalid");
    }
    this.severity = severity;
  }

  /**
   * Returns whether the given severity is valid
   *
   * @param severity the severity to check the validity of
   * @return {@code true} if severity is valid, {@code false} if not
   */
  private boolean isValidSeverity(Integer severity) {
    return severity != null;
  }

  @Override
  public boolean validate() {
    return isValidCustomData(customData) && isValidSeverity(severity);
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
    SetMonitoringLevelRequest that = (SetMonitoringLevelRequest) o;
    return Objects.equals(customData, that.customData) && Objects.equals(severity, that.severity);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customData, severity);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("severity", severity)
        .add("isValid", validate())
        .toString();
  }
}
