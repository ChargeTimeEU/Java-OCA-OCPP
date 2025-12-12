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

package eu.chargetime.ocpp.v21.model.types;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.utilities.MoreObjects;
import java.util.Objects;
import javax.annotation.Nullable;

/** Class to hold results of GetVariables request. */
public final class GetVariableResult {
  /** attributeStatus */
  private GetVariableStatusEnum attributeStatus;

  /** More information about the status. */
  @Nullable private StatusInfo attributeStatusInfo;

  /** attributeType */
  @Nullable private AttributeEnum attributeType;

  /**
   * Value of requested attribute type of component-variable. This field can only be empty when the
   * given status is NOT accepted.
   *
   * <p>The Configuration Variable ReportingValueSize can be used to limit
   * GetVariableResult.attributeValue, VariableAttribute.value and EventData.actualValue. The max
   * size of these values will always remain equal.
   */
  @Nullable private String attributeValue;

  /** A physical or logical component */
  private Component component;

  /** Reference key to a component-variable. */
  private Variable variable;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the GetVariableResult class
   *
   * @param attributeStatus attributeStatus
   * @param component A physical or logical component
   * @param variable Reference key to a component-variable.
   */
  public GetVariableResult(
      GetVariableStatusEnum attributeStatus, Component component, Variable variable) {
    setAttributeStatus(attributeStatus);
    setComponent(component);
    setVariable(variable);
  }

  /**
   * Gets attributeStatus
   *
   * @return attributeStatus
   */
  public GetVariableStatusEnum getAttributeStatus() {
    return attributeStatus;
  }

  /**
   * Sets attributeStatus
   *
   * @param attributeStatus attributeStatus
   */
  public void setAttributeStatus(GetVariableStatusEnum attributeStatus) {
    if (!isValidAttributeStatus(attributeStatus)) {
      throw new PropertyConstraintException(attributeStatus, "attributeStatus is invalid");
    }
    this.attributeStatus = attributeStatus;
  }

  /**
   * Returns whether the given attributeStatus is valid
   *
   * @param attributeStatus the attributeStatus to check the validity of
   * @return {@code true} if attributeStatus is valid, {@code false} if not
   */
  private boolean isValidAttributeStatus(GetVariableStatusEnum attributeStatus) {
    return attributeStatus != null;
  }

  /**
   * Gets more information about the status.
   *
   * @return More information about the status
   */
  @Nullable
  public StatusInfo getAttributeStatusInfo() {
    return attributeStatusInfo;
  }

  /**
   * Sets more information about the status.
   *
   * @param attributeStatusInfo More information about the status
   */
  public void setAttributeStatusInfo(@Nullable StatusInfo attributeStatusInfo) {
    if (!isValidAttributeStatusInfo(attributeStatusInfo)) {
      throw new PropertyConstraintException(attributeStatusInfo, "attributeStatusInfo is invalid");
    }
    this.attributeStatusInfo = attributeStatusInfo;
  }

  /**
   * Returns whether the given attributeStatusInfo is valid
   *
   * @param attributeStatusInfo the attributeStatusInfo to check the validity of
   * @return {@code true} if attributeStatusInfo is valid, {@code false} if not
   */
  private boolean isValidAttributeStatusInfo(@Nullable StatusInfo attributeStatusInfo) {
    return attributeStatusInfo == null || attributeStatusInfo.validate();
  }

  /**
   * Adds more information about the status.
   *
   * @param attributeStatusInfo More information about the status
   * @return this
   */
  public GetVariableResult withAttributeStatusInfo(@Nullable StatusInfo attributeStatusInfo) {
    setAttributeStatusInfo(attributeStatusInfo);
    return this;
  }

  /**
   * Gets attributeType
   *
   * @return attributeType
   */
  public AttributeEnum getAttributeType() {
    return attributeType != null ? attributeType : AttributeEnum.Actual;
  }

  /**
   * Sets attributeType
   *
   * @param attributeType attributeType
   */
  public void setAttributeType(@Nullable AttributeEnum attributeType) {
    this.attributeType = attributeType;
  }

  /**
   * Adds attributeType
   *
   * @param attributeType attributeType
   * @return this
   */
  public GetVariableResult withAttributeType(@Nullable AttributeEnum attributeType) {
    setAttributeType(attributeType);
    return this;
  }

  /**
   * Gets value of requested attribute type of component-variable. This field can only be empty when
   * the given status is NOT accepted.
   *
   * @return Value of requested attribute type of component-variable
   */
  @Nullable
  public String getAttributeValue() {
    return attributeValue;
  }

  /**
   * Sets value of requested attribute type of component-variable. This field can only be empty when
   * the given status is NOT accepted.
   *
   * @param attributeValue Value of requested attribute type of component-variable
   */
  public void setAttributeValue(@Nullable String attributeValue) {
    if (!isValidAttributeValue(attributeValue)) {
      throw new PropertyConstraintException(attributeValue, "attributeValue is invalid");
    }
    this.attributeValue = attributeValue;
  }

  /**
   * Returns whether the given attributeValue is valid
   *
   * @param attributeValue the attributeValue to check the validity of
   * @return {@code true} if attributeValue is valid, {@code false} if not
   */
  private boolean isValidAttributeValue(@Nullable String attributeValue) {
    if (attributeStatus != GetVariableStatusEnum.Accepted) {
      return attributeValue == null || attributeValue.length() <= 2500;
    } else {
      return attributeValue != null && attributeValue.length() <= 2500;
    }
  }

  /**
   * Adds value of requested attribute type of component-variable. This field can only be empty when
   * the given status is NOT accepted.
   *
   * @param attributeValue Value of requested attribute type of component-variable
   * @return this
   */
  public GetVariableResult withAttributeValue(@Nullable String attributeValue) {
    setAttributeValue(attributeValue);
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
  public GetVariableResult withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  public boolean validate() {
    return isValidAttributeStatus(attributeStatus)
        && isValidAttributeStatusInfo(attributeStatusInfo)
        && isValidAttributeValue(attributeValue)
        && isValidComponent(component)
        && isValidVariable(variable)
        && isValidCustomData(customData);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetVariableResult that = (GetVariableResult) o;
    return Objects.equals(attributeStatus, that.attributeStatus)
        && Objects.equals(attributeStatusInfo, that.attributeStatusInfo)
        && Objects.equals(attributeType, that.attributeType)
        && Objects.equals(attributeValue, that.attributeValue)
        && Objects.equals(component, that.component)
        && Objects.equals(variable, that.variable)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        attributeStatus,
        attributeStatusInfo,
        attributeType,
        attributeValue,
        component,
        variable,
        customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("attributeStatus", attributeStatus)
        .add("attributeStatusInfo", attributeStatusInfo)
        .add("attributeType", attributeType)
        .add("attributeValue", attributeValue)
        .add("component", component)
        .add("variable", variable)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
