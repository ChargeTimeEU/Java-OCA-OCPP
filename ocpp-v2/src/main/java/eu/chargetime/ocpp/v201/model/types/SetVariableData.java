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

/** SetVariableDataType */
public final class SetVariableData {
  /** Custom data */
  @Nullable private CustomData customData;

  /** Type of attribute: Actual, Target, MinSet, MaxSet. Default is Actual when omitted. */
  @Nullable private AttributeEnum attributeType;

  /**
   * Value to be assigned to attribute of variable.
   *
   * <p>The Configuration Variable ConfigurationValueSize can be used to limit
   * SetVariableData.attributeValue and VariableCharacteristics.valueList. The max size of these
   * values will always remain equal.
   */
  private String attributeValue;

  /** A physical or logical component */
  private Component component;

  /** Reference key to a component-variable. */
  private Variable variable;

  /**
   * Constructor for the SetVariableData class
   *
   * @param attributeValue Value to be assigned to attribute of variable.
   * @param component A physical or logical component
   * @param variable Reference key to a component-variable.
   */
  public SetVariableData(String attributeValue, Component component, Variable variable) {
    setAttributeValue(attributeValue);
    setComponent(component);
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
  public SetVariableData withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  /**
   * Gets type of attribute: Actual, Target, MinSet, MaxSet. Default is Actual when omitted.
   *
   * @return Type of attribute: Actual, Target, MinSet, MaxSet
   */
  public AttributeEnum getAttributeType() {
    return attributeType != null ? attributeType : AttributeEnum.Actual;
  }

  /**
   * Sets type of attribute: Actual, Target, MinSet, MaxSet. Default is Actual when omitted.
   *
   * @param attributeType Type of attribute: Actual, Target, MinSet, MaxSet
   */
  public void setAttributeType(@Nullable AttributeEnum attributeType) {
    this.attributeType = attributeType;
  }

  /**
   * Adds type of attribute: Actual, Target, MinSet, MaxSet. Default is Actual when omitted.
   *
   * @param attributeType Type of attribute: Actual, Target, MinSet, MaxSet
   * @return this
   */
  public SetVariableData withAttributeType(@Nullable AttributeEnum attributeType) {
    setAttributeType(attributeType);
    return this;
  }

  /**
   * Gets value to be assigned to attribute of variable.
   *
   * @return Value to be assigned to attribute of variable
   */
  public String getAttributeValue() {
    return attributeValue;
  }

  /**
   * Sets value to be assigned to attribute of variable.
   *
   * @param attributeValue Value to be assigned to attribute of variable
   */
  public void setAttributeValue(String attributeValue) {
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
  private boolean isValidAttributeValue(String attributeValue) {
    return attributeValue != null && attributeValue.length() <= 1000;
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

  public boolean validate() {
    return isValidCustomData(customData)
        && isValidAttributeValue(attributeValue)
        && isValidComponent(component)
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
    SetVariableData that = (SetVariableData) o;
    return Objects.equals(customData, that.customData)
        && Objects.equals(attributeType, that.attributeType)
        && Objects.equals(attributeValue, that.attributeValue)
        && Objects.equals(component, that.component)
        && Objects.equals(variable, that.variable);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customData, attributeType, attributeValue, component, variable);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("attributeType", attributeType)
        .add("attributeValue", attributeValue)
        .add("component", component)
        .add("variable", variable)
        .add("isValid", validate())
        .toString();
  }
}
