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

/** A monitoring setting for a variable. */
public final class VariableMonitoring {
  /** Custom data */
  @Nullable private CustomData customData;

  /** The identifier of the monitor. */
  private Integer id;

  /**
   * Monitor only active when a transaction is ongoing on a component relevant to this transaction.
   */
  private Boolean transaction;

  /**
   * Value for threshold or delta monitoring. For Periodic or PeriodicClockAligned this is the
   * interval in seconds.
   */
  private Double value;

  /** The type of this monitor, e.g. a threshold, delta or periodic monitor. */
  private MonitorEnum type;

  /**
   * The severity that will be assigned to an event that is triggered by this monitor. The severity
   * range is 0-9, with 0 as the highest and 9 as the lowest severity level.
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
   * Constructor for the VariableMonitoring class
   *
   * @param id The identifier of the monitor.
   * @param transaction Monitor only active when a transaction is ongoing on a component relevant to
   *     this transaction.
   * @param value Value for threshold or delta monitoring. For Periodic or PeriodicClockAligned this
   *     is the interval in seconds.
   * @param type The type of this monitor, e.g. a threshold, delta or periodic monitor.
   * @param severity The severity that will be assigned to an event that is triggered by this
   *     monitor. The severity range is 0-9, with 0 as the highest and 9 as the lowest severity
   *     level.
   */
  public VariableMonitoring(
      Integer id, Boolean transaction, Double value, MonitorEnum type, Integer severity) {
    setId(id);
    setTransaction(transaction);
    setValue(value);
    setType(type);
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
  public VariableMonitoring withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  /**
   * Gets the identifier of the monitor.
   *
   * @return The identifier of the monitor
   */
  public Integer getId() {
    return id;
  }

  /**
   * Sets the identifier of the monitor.
   *
   * @param id The identifier of the monitor
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
   * Gets monitor only active when a transaction is ongoing on a component relevant to this
   * transaction.
   *
   * @return Monitor only active when a transaction is ongoing on a component relevant to this
   *     transaction
   */
  public Boolean getTransaction() {
    return transaction;
  }

  /**
   * Sets monitor only active when a transaction is ongoing on a component relevant to this
   * transaction.
   *
   * @param transaction Monitor only active when a transaction is ongoing on a component relevant to
   *     this transaction
   */
  public void setTransaction(Boolean transaction) {
    if (!isValidTransaction(transaction)) {
      throw new PropertyConstraintException(transaction, "transaction is invalid");
    }
    this.transaction = transaction;
  }

  /**
   * Returns whether the given transaction is valid
   *
   * @param transaction the transaction to check the validity of
   * @return {@code true} if transaction is valid, {@code false} if not
   */
  private boolean isValidTransaction(Boolean transaction) {
    return transaction != null;
  }

  /**
   * Gets value for threshold or delta monitoring. For Periodic or PeriodicClockAligned this is the
   * interval in seconds.
   *
   * @return Value for threshold or delta monitoring
   */
  public Double getValue() {
    return value;
  }

  /**
   * Sets value for threshold or delta monitoring. For Periodic or PeriodicClockAligned this is the
   * interval in seconds.
   *
   * @param value Value for threshold or delta monitoring
   */
  public void setValue(Double value) {
    if (!isValidValue(value)) {
      throw new PropertyConstraintException(value, "value is invalid");
    }
    this.value = value;
  }

  /**
   * Returns whether the given value is valid
   *
   * @param value the value to check the validity of
   * @return {@code true} if value is valid, {@code false} if not
   */
  private boolean isValidValue(Double value) {
    return value != null;
  }

  /**
   * Gets the type of this monitor, e.g. a threshold, delta or periodic monitor.
   *
   * @return The type of this monitor, e.g. a threshold, delta or periodic monitor
   */
  public MonitorEnum getType() {
    return type;
  }

  /**
   * Sets the type of this monitor, e.g. a threshold, delta or periodic monitor.
   *
   * @param type The type of this monitor, e.g. a threshold, delta or periodic monitor
   */
  public void setType(MonitorEnum type) {
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
  private boolean isValidType(MonitorEnum type) {
    return type != null;
  }

  /**
   * Gets the severity that will be assigned to an event that is triggered by this monitor. The
   * severity range is 0-9, with 0 as the highest and 9 as the lowest severity level.
   *
   * @return The severity that will be assigned to an event that is triggered by this monitor
   */
  public Integer getSeverity() {
    return severity;
  }

  /**
   * Sets the severity that will be assigned to an event that is triggered by this monitor. The
   * severity range is 0-9, with 0 as the highest and 9 as the lowest severity level.
   *
   * @param severity The severity that will be assigned to an event that is triggered by this
   *     monitor
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

  public boolean validate() {
    return isValidCustomData(customData)
        && isValidId(id)
        && isValidTransaction(transaction)
        && isValidValue(value)
        && isValidType(type)
        && isValidSeverity(severity);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VariableMonitoring that = (VariableMonitoring) o;
    return Objects.equals(customData, that.customData)
        && Objects.equals(id, that.id)
        && Objects.equals(transaction, that.transaction)
        && Objects.equals(value, that.value)
        && Objects.equals(type, that.type)
        && Objects.equals(severity, that.severity);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customData, id, transaction, value, type, severity);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("id", id)
        .add("transaction", transaction)
        .add("value", value)
        .add("type", type)
        .add("severity", severity)
        .add("isValid", validate())
        .toString();
  }
}
