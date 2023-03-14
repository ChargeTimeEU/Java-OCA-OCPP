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

/** Class to hold result of SetVariableMonitoring request. */
public final class SetMonitoringResult {
  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Id given to the VariableMonitor by the Charging Station. The Id is only returned when status is
   * accepted. Installed VariableMonitors should have unique id's but the id's of removed Installed
   * monitors should have unique id's but the id's of removed monitors MAY be reused.
   */
  @Nullable private Integer id;

  /** More information about the status. */
  @Nullable private StatusInfo statusInfo;

  /**
   * Status is OK if a value could be returned. Otherwise this will indicate the reason why a value
   * could not be returned.
   */
  private SetMonitoringStatusEnum status;

  /** The type of this monitor, e.g. a threshold, delta or periodic monitor. */
  private MonitorEnum type;

  /** A physical or logical component */
  private Component component;

  /** Reference key to a component-variable. */
  private Variable variable;

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
   * Constructor for the SetMonitoringResult class
   *
   * @param status Status is OK if a value could be returned. Otherwise this will indicate the
   *     reason why a value could not be returned.
   * @param type The type of this monitor, e.g. a threshold, delta or periodic monitor.
   * @param component A physical or logical component
   * @param variable Reference key to a component-variable.
   * @param severity The severity that will be assigned to an event that is triggered by this
   *     monitor. The severity range is 0-9, with 0 as the highest and 9 as the lowest severity
   *     level.
   */
  public SetMonitoringResult(
      SetMonitoringStatusEnum status,
      MonitorEnum type,
      Component component,
      Variable variable,
      Integer severity) {
    setStatus(status);
    setType(type);
    setComponent(component);
    setVariable(variable);
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
  public SetMonitoringResult withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  /**
   * Gets id given to the VariableMonitor by the Charging Station. The Id is only returned when
   * status is accepted. Installed VariableMonitors should have unique id's but the id's of removed
   * Installed monitors should have unique id's but the id's of removed monitors MAY be reused.
   *
   * @return Id given to the VariableMonitor by the Charging Station
   */
  @Nullable
  public Integer getId() {
    return id;
  }

  /**
   * Sets id given to the VariableMonitor by the Charging Station. The Id is only returned when
   * status is accepted. Installed VariableMonitors should have unique id's but the id's of removed
   * Installed monitors should have unique id's but the id's of removed monitors MAY be reused.
   *
   * @param id Id given to the VariableMonitor by the Charging Station
   */
  public void setId(@Nullable Integer id) {
    this.id = id;
  }

  /**
   * Adds id given to the VariableMonitor by the Charging Station. The Id is only returned when
   * status is accepted. Installed VariableMonitors should have unique id's but the id's of removed
   * Installed monitors should have unique id's but the id's of removed monitors MAY be reused.
   *
   * @param id Id given to the VariableMonitor by the Charging Station
   * @return this
   */
  public SetMonitoringResult withId(@Nullable Integer id) {
    setId(id);
    return this;
  }

  /**
   * Gets more information about the status.
   *
   * @return More information about the status
   */
  @Nullable
  public StatusInfo getStatusInfo() {
    return statusInfo;
  }

  /**
   * Sets more information about the status.
   *
   * @param statusInfo More information about the status
   */
  public void setStatusInfo(@Nullable StatusInfo statusInfo) {
    if (!isValidStatusInfo(statusInfo)) {
      throw new PropertyConstraintException(statusInfo, "statusInfo is invalid");
    }
    this.statusInfo = statusInfo;
  }

  /**
   * Returns whether the given statusInfo is valid
   *
   * @param statusInfo the statusInfo to check the validity of
   * @return {@code true} if statusInfo is valid, {@code false} if not
   */
  private boolean isValidStatusInfo(@Nullable StatusInfo statusInfo) {
    return statusInfo == null || statusInfo.validate();
  }

  /**
   * Adds more information about the status.
   *
   * @param statusInfo More information about the status
   * @return this
   */
  public SetMonitoringResult withStatusInfo(@Nullable StatusInfo statusInfo) {
    setStatusInfo(statusInfo);
    return this;
  }

  /**
   * Gets status is OK if a value could be returned. Otherwise this will indicate the reason why a
   * value could not be returned.
   *
   * @return Status is OK if a value could be returned
   */
  public SetMonitoringStatusEnum getStatus() {
    return status;
  }

  /**
   * Sets status is OK if a value could be returned. Otherwise this will indicate the reason why a
   * value could not be returned.
   *
   * @param status Status is OK if a value could be returned
   */
  public void setStatus(SetMonitoringStatusEnum status) {
    if (!isValidStatus(status)) {
      throw new PropertyConstraintException(status, "status is invalid");
    }
    this.status = status;
  }

  /**
   * Returns whether the given status is valid
   *
   * @param status the status to check the validity of
   * @return {@code true} if status is valid, {@code false} if not
   */
  private boolean isValidStatus(SetMonitoringStatusEnum status) {
    return status != null;
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
   * Gets a physical or logical component
   *
   * @return A physical or logical component
   */
  public Component getComponent() {
    return component;
  }

  /**
   * Sets a physical or logical component
   *
   * @param component A physical or logical component
   */
  public void setComponent(Component component) {
    if (!isValidComponent(component)) {
      throw new PropertyConstraintException(component, "component is invalid");
    }
    this.component = component;
  }

  /**
   * Returns whether the given component is valid
   *
   * @param component the component to check the validity of
   * @return {@code true} if component is valid, {@code false} if not
   */
  private boolean isValidComponent(Component component) {
    return component != null && component.validate();
  }

  /**
   * Gets reference key to a component-variable.
   *
   * @return Reference key to a component-variable
   */
  public Variable getVariable() {
    return variable;
  }

  /**
   * Sets reference key to a component-variable.
   *
   * @param variable Reference key to a component-variable
   */
  public void setVariable(Variable variable) {
    if (!isValidVariable(variable)) {
      throw new PropertyConstraintException(variable, "variable is invalid");
    }
    this.variable = variable;
  }

  /**
   * Returns whether the given variable is valid
   *
   * @param variable the variable to check the validity of
   * @return {@code true} if variable is valid, {@code false} if not
   */
  private boolean isValidVariable(Variable variable) {
    return variable != null && variable.validate();
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
        && isValidStatusInfo(statusInfo)
        && isValidStatus(status)
        && isValidType(type)
        && isValidComponent(component)
        && isValidVariable(variable)
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
    SetMonitoringResult that = (SetMonitoringResult) o;
    return Objects.equals(customData, that.customData)
        && Objects.equals(id, that.id)
        && Objects.equals(statusInfo, that.statusInfo)
        && Objects.equals(status, that.status)
        && Objects.equals(type, that.type)
        && Objects.equals(component, that.component)
        && Objects.equals(variable, that.variable)
        && Objects.equals(severity, that.severity);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customData, id, statusInfo, status, type, component, variable, severity);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("id", id)
        .add("statusInfo", statusInfo)
        .add("status", status)
        .add("type", type)
        .add("component", component)
        .add("variable", variable)
        .add("severity", severity)
        .add("isValid", validate())
        .toString();
  }
}
