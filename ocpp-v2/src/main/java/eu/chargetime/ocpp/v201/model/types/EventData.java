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
import java.time.ZonedDateTime;
import java.util.Objects;
import javax.annotation.Nullable;

/** Class to report an event notification for a component-variable. */
public final class EventData {
  /** Custom data */
  @Nullable private CustomData customData;

  /** The identifier of the event. This field can be referred to as a cause by other events. */
  private Integer eventId;

  /** Timestamp of the moment the report was generated. */
  private ZonedDateTime timestamp;

  /** Type of monitor that triggered this event, e.g. exceeding a threshold value. */
  private EventTriggerEnum trigger;

  /** Refers to the Id of an event that is considered to be the cause for this event. */
  @Nullable private Integer cause;

  /**
   * Actual value (attributeType Actual) of the variable.
   *
   * <p>The Configuration Variable ReportingValueSize can be used to limit
   * GetVariableResult.attributeValue, VariableAttribute.value and EventData.actualValue. The max
   * size of these values will always remain equal.
   */
  private String actualValue;

  /** Technical (error) code as reported by component. */
  @Nullable private String techCode;

  /** Technical detail information as reported by component. */
  @Nullable private String techInfo;

  /**
   * Cleared is set to true to report the clearing of a monitored situation, i.e. a 'return to
   * normal'.
   */
  @Nullable private Boolean cleared;

  /**
   * If an event notification is linked to a specific transaction, this field can be used to specify
   * its transactionId.
   */
  @Nullable private String transactionId;

  /** A physical or logical component */
  private Component component;

  /** The identifier of the VariableMonitoring which triggered the event. */
  @Nullable private Integer variableMonitoringId;

  /** Specifies the event notification type of the message. */
  private EventNotificationEnum eventNotificationType;

  /** Reference key to a component-variable. */
  private Variable variable;

  /**
   * Constructor for the EventData class
   *
   * @param eventId The identifier of the event. This field can be referred to as a cause by other
   *     events.
   * @param timestamp Timestamp of the moment the report was generated.
   * @param trigger Type of monitor that triggered this event, e.g. exceeding a threshold value.
   * @param actualValue Actual value (attributeType Actual) of the variable.
   * @param component A physical or logical component
   * @param eventNotificationType Specifies the event notification type of the message.
   * @param variable Reference key to a component-variable.
   */
  public EventData(
      Integer eventId,
      ZonedDateTime timestamp,
      EventTriggerEnum trigger,
      String actualValue,
      Component component,
      EventNotificationEnum eventNotificationType,
      Variable variable) {
    setEventId(eventId);
    setTimestamp(timestamp);
    setTrigger(trigger);
    setActualValue(actualValue);
    setComponent(component);
    setEventNotificationType(eventNotificationType);
    setVariable(variable);
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
  public EventData withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  /**
   * Gets the identifier of the event. This field can be referred to as a cause by other events.
   *
   * @return The identifier of the event
   */
  public Integer getEventId() {
    return eventId;
  }

  /**
   * Sets the identifier of the event. This field can be referred to as a cause by other events.
   *
   * @param eventId The identifier of the event
   */
  public void setEventId(Integer eventId) {
    if (!isValidEventId(eventId)) {
      throw new PropertyConstraintException(eventId, "eventId is invalid");
    }
    this.eventId = eventId;
  }

  /**
   * Returns whether the given eventId is valid
   *
   * @param eventId the eventId to check the validity of
   * @return {@code true} if eventId is valid, {@code false} if not
   */
  private boolean isValidEventId(Integer eventId) {
    return eventId != null;
  }

  /**
   * Gets timestamp of the moment the report was generated.
   *
   * @return Timestamp of the moment the report was generated
   */
  public ZonedDateTime getTimestamp() {
    return timestamp;
  }

  /**
   * Sets timestamp of the moment the report was generated.
   *
   * @param timestamp Timestamp of the moment the report was generated
   */
  public void setTimestamp(ZonedDateTime timestamp) {
    if (!isValidTimestamp(timestamp)) {
      throw new PropertyConstraintException(timestamp, "timestamp is invalid");
    }
    this.timestamp = timestamp;
  }

  /**
   * Returns whether the given timestamp is valid
   *
   * @param timestamp the timestamp to check the validity of
   * @return {@code true} if timestamp is valid, {@code false} if not
   */
  private boolean isValidTimestamp(ZonedDateTime timestamp) {
    return timestamp != null;
  }

  /**
   * Gets type of monitor that triggered this event, e.g. exceeding a threshold value.
   *
   * @return Type of monitor that triggered this event, e.g. exceeding a threshold value
   */
  public EventTriggerEnum getTrigger() {
    return trigger;
  }

  /**
   * Sets type of monitor that triggered this event, e.g. exceeding a threshold value.
   *
   * @param trigger Type of monitor that triggered this event, e.g. exceeding a threshold value
   */
  public void setTrigger(EventTriggerEnum trigger) {
    if (!isValidTrigger(trigger)) {
      throw new PropertyConstraintException(trigger, "trigger is invalid");
    }
    this.trigger = trigger;
  }

  /**
   * Returns whether the given trigger is valid
   *
   * @param trigger the trigger to check the validity of
   * @return {@code true} if trigger is valid, {@code false} if not
   */
  private boolean isValidTrigger(EventTriggerEnum trigger) {
    return trigger != null;
  }

  /**
   * Gets refers to the Id of an event that is considered to be the cause for this event.
   *
   * @return Refers to the Id of an event that is considered to be the cause for this event
   */
  @Nullable
  public Integer getCause() {
    return cause;
  }

  /**
   * Sets refers to the Id of an event that is considered to be the cause for this event.
   *
   * @param cause Refers to the Id of an event that is considered to be the cause for this event
   */
  public void setCause(@Nullable Integer cause) {
    this.cause = cause;
  }

  /**
   * Adds refers to the Id of an event that is considered to be the cause for this event.
   *
   * @param cause Refers to the Id of an event that is considered to be the cause for this event
   * @return this
   */
  public EventData withCause(@Nullable Integer cause) {
    setCause(cause);
    return this;
  }

  /**
   * Gets actual value (attributeType Actual) of the variable.
   *
   * @return Actual value (attributeType Actual) of the variable
   */
  public String getActualValue() {
    return actualValue;
  }

  /**
   * Sets actual value (attributeType Actual) of the variable.
   *
   * @param actualValue Actual value (attributeType Actual) of the variable
   */
  public void setActualValue(String actualValue) {
    if (!isValidActualValue(actualValue)) {
      throw new PropertyConstraintException(actualValue, "actualValue is invalid");
    }
    this.actualValue = actualValue;
  }

  /**
   * Returns whether the given actualValue is valid
   *
   * @param actualValue the actualValue to check the validity of
   * @return {@code true} if actualValue is valid, {@code false} if not
   */
  private boolean isValidActualValue(String actualValue) {
    return actualValue != null && actualValue.length() <= 2500;
  }

  /**
   * Gets technical (error) code as reported by component.
   *
   * @return Technical (error) code as reported by component
   */
  @Nullable
  public String getTechCode() {
    return techCode;
  }

  /**
   * Sets technical (error) code as reported by component.
   *
   * @param techCode Technical (error) code as reported by component
   */
  public void setTechCode(@Nullable String techCode) {
    if (!isValidTechCode(techCode)) {
      throw new PropertyConstraintException(techCode, "techCode is invalid");
    }
    this.techCode = techCode;
  }

  /**
   * Returns whether the given techCode is valid
   *
   * @param techCode the techCode to check the validity of
   * @return {@code true} if techCode is valid, {@code false} if not
   */
  private boolean isValidTechCode(@Nullable String techCode) {
    return techCode == null || techCode.length() <= 50;
  }

  /**
   * Adds technical (error) code as reported by component.
   *
   * @param techCode Technical (error) code as reported by component
   * @return this
   */
  public EventData withTechCode(@Nullable String techCode) {
    setTechCode(techCode);
    return this;
  }

  /**
   * Gets technical detail information as reported by component.
   *
   * @return Technical detail information as reported by component
   */
  @Nullable
  public String getTechInfo() {
    return techInfo;
  }

  /**
   * Sets technical detail information as reported by component.
   *
   * @param techInfo Technical detail information as reported by component
   */
  public void setTechInfo(@Nullable String techInfo) {
    if (!isValidTechInfo(techInfo)) {
      throw new PropertyConstraintException(techInfo, "techInfo is invalid");
    }
    this.techInfo = techInfo;
  }

  /**
   * Returns whether the given techInfo is valid
   *
   * @param techInfo the techInfo to check the validity of
   * @return {@code true} if techInfo is valid, {@code false} if not
   */
  private boolean isValidTechInfo(@Nullable String techInfo) {
    return techInfo == null || techInfo.length() <= 500;
  }

  /**
   * Adds technical detail information as reported by component.
   *
   * @param techInfo Technical detail information as reported by component
   * @return this
   */
  public EventData withTechInfo(@Nullable String techInfo) {
    setTechInfo(techInfo);
    return this;
  }

  /**
   * Gets cleared is set to true to report the clearing of a monitored situation, i.e. a 'return to
   * normal'.
   *
   * @return Cleared is set to true to report the clearing of a monitored situation, i.e. a 'return
   *     to normal'
   */
  @Nullable
  public Boolean getCleared() {
    return cleared;
  }

  /**
   * Sets cleared is set to true to report the clearing of a monitored situation, i.e. a 'return to
   * normal'.
   *
   * @param cleared Cleared is set to true to report the clearing of a monitored situation, i.e. a
   *     'return to normal'
   */
  public void setCleared(@Nullable Boolean cleared) {
    this.cleared = cleared;
  }

  /**
   * Adds cleared is set to true to report the clearing of a monitored situation, i.e. a 'return to
   * normal'.
   *
   * @param cleared Cleared is set to true to report the clearing of a monitored situation, i.e. a
   *     'return to normal'
   * @return this
   */
  public EventData withCleared(@Nullable Boolean cleared) {
    setCleared(cleared);
    return this;
  }

  /**
   * Gets if an event notification is linked to a specific transaction, this field can be used to
   * specify its transactionId.
   *
   * @return If an event notification is linked to a specific transaction, this field can be used to
   *     specify its transactionId
   */
  @Nullable
  public String getTransactionId() {
    return transactionId;
  }

  /**
   * Sets if an event notification is linked to a specific transaction, this field can be used to
   * specify its transactionId.
   *
   * @param transactionId If an event notification is linked to a specific transaction, this field
   *     can be used to specify its transactionId
   */
  public void setTransactionId(@Nullable String transactionId) {
    if (!isValidTransactionId(transactionId)) {
      throw new PropertyConstraintException(transactionId, "transactionId is invalid");
    }
    this.transactionId = transactionId;
  }

  /**
   * Returns whether the given transactionId is valid
   *
   * @param transactionId the transactionId to check the validity of
   * @return {@code true} if transactionId is valid, {@code false} if not
   */
  private boolean isValidTransactionId(@Nullable String transactionId) {
    return transactionId == null || transactionId.length() <= 36;
  }

  /**
   * Adds if an event notification is linked to a specific transaction, this field can be used to
   * specify its transactionId.
   *
   * @param transactionId If an event notification is linked to a specific transaction, this field
   *     can be used to specify its transactionId
   * @return this
   */
  public EventData withTransactionId(@Nullable String transactionId) {
    setTransactionId(transactionId);
    return this;
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
   * Gets the identifier of the VariableMonitoring which triggered the event.
   *
   * @return The identifier of the VariableMonitoring which triggered the event
   */
  @Nullable
  public Integer getVariableMonitoringId() {
    return variableMonitoringId;
  }

  /**
   * Sets the identifier of the VariableMonitoring which triggered the event.
   *
   * @param variableMonitoringId The identifier of the VariableMonitoring which triggered the event
   */
  public void setVariableMonitoringId(@Nullable Integer variableMonitoringId) {
    this.variableMonitoringId = variableMonitoringId;
  }

  /**
   * Adds the identifier of the VariableMonitoring which triggered the event.
   *
   * @param variableMonitoringId The identifier of the VariableMonitoring which triggered the event
   * @return this
   */
  public EventData withVariableMonitoringId(@Nullable Integer variableMonitoringId) {
    setVariableMonitoringId(variableMonitoringId);
    return this;
  }

  /**
   * Gets specifies the event notification type of the message.
   *
   * @return Specifies the event notification type of the message
   */
  public EventNotificationEnum getEventNotificationType() {
    return eventNotificationType;
  }

  /**
   * Sets specifies the event notification type of the message.
   *
   * @param eventNotificationType Specifies the event notification type of the message
   */
  public void setEventNotificationType(EventNotificationEnum eventNotificationType) {
    if (!isValidEventNotificationType(eventNotificationType)) {
      throw new PropertyConstraintException(
          eventNotificationType, "eventNotificationType is invalid");
    }
    this.eventNotificationType = eventNotificationType;
  }

  /**
   * Returns whether the given eventNotificationType is valid
   *
   * @param eventNotificationType the eventNotificationType to check the validity of
   * @return {@code true} if eventNotificationType is valid, {@code false} if not
   */
  private boolean isValidEventNotificationType(EventNotificationEnum eventNotificationType) {
    return eventNotificationType != null;
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

  public boolean validate() {
    return isValidCustomData(customData)
        && isValidEventId(eventId)
        && isValidTimestamp(timestamp)
        && isValidTrigger(trigger)
        && isValidActualValue(actualValue)
        && isValidTechCode(techCode)
        && isValidTechInfo(techInfo)
        && isValidTransactionId(transactionId)
        && isValidComponent(component)
        && isValidEventNotificationType(eventNotificationType)
        && isValidVariable(variable);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EventData that = (EventData) o;
    return Objects.equals(customData, that.customData)
        && Objects.equals(eventId, that.eventId)
        && Objects.equals(timestamp, that.timestamp)
        && Objects.equals(trigger, that.trigger)
        && Objects.equals(cause, that.cause)
        && Objects.equals(actualValue, that.actualValue)
        && Objects.equals(techCode, that.techCode)
        && Objects.equals(techInfo, that.techInfo)
        && Objects.equals(cleared, that.cleared)
        && Objects.equals(transactionId, that.transactionId)
        && Objects.equals(component, that.component)
        && Objects.equals(variableMonitoringId, that.variableMonitoringId)
        && Objects.equals(eventNotificationType, that.eventNotificationType)
        && Objects.equals(variable, that.variable);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        customData,
        eventId,
        timestamp,
        trigger,
        cause,
        actualValue,
        techCode,
        techInfo,
        cleared,
        transactionId,
        component,
        variableMonitoringId,
        eventNotificationType,
        variable);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("eventId", eventId)
        .add("timestamp", timestamp)
        .add("trigger", trigger)
        .add("cause", cause)
        .add("actualValue", actualValue)
        .add("techCode", techCode)
        .add("techInfo", techInfo)
        .add("cleared", cleared)
        .add("transactionId", transactionId)
        .add("component", component)
        .add("variableMonitoringId", variableMonitoringId)
        .add("eventNotificationType", eventNotificationType)
        .add("variable", variable)
        .add("isValid", validate())
        .toString();
  }
}
