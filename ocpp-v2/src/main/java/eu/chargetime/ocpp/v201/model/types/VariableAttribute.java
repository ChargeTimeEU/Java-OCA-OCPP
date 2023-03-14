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

/** Attribute data of a variable. */
public final class VariableAttribute {
  /** Custom data */
  @Nullable private CustomData customData;

  /** Attribute: Actual, MinSet, MaxSet, etc. Defaults to Actual if absent. */
  @Nullable private AttributeEnum type;

  /**
   * Value of the attribute. May only be omitted when mutability is set to 'WriteOnly'.
   *
   * <p>The Configuration Variable ReportingValueSize can be used to limit
   * GetVariableResult.attributeValue, VariableAttribute.value and EventData.actualValue. The max
   * size of these values will always remain equal.
   */
  @Nullable private String value;

  /** The mutability of this attribute. Default is ReadWrite when omitted. */
  @Nullable private MutabilityEnum mutability;

  /**
   * Whether value will be persistent across system reboots or power down. Default when omitted is
   * false.
   */
  @Nullable private Boolean persistent;

  /**
   * Whether value that will never be changed by the Charging Station at runtime. Default when
   * omitted is false.
   */
  @Nullable private Boolean constant;

  /** Constructor for the VariableAttribute class */
  public VariableAttribute() {}

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
  public VariableAttribute withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  /**
   * Gets attribute: Actual, MinSet, MaxSet, etc. Defaults to Actual if absent.
   *
   * @return Attribute: Actual, MinSet, MaxSet, etc
   */
  public AttributeEnum getType() {
    return type != null ? type : AttributeEnum.Actual;
  }

  /**
   * Sets attribute: Actual, MinSet, MaxSet, etc. Defaults to Actual if absent.
   *
   * @param type Attribute: Actual, MinSet, MaxSet, etc
   */
  public void setType(@Nullable AttributeEnum type) {
    this.type = type;
  }

  /**
   * Adds attribute: Actual, MinSet, MaxSet, etc. Defaults to Actual if absent.
   *
   * @param type Attribute: Actual, MinSet, MaxSet, etc
   * @return this
   */
  public VariableAttribute withType(@Nullable AttributeEnum type) {
    setType(type);
    return this;
  }

  /**
   * Gets value of the attribute. May only be omitted when mutability is set to 'WriteOnly'.
   *
   * @return Value of the attribute
   */
  @Nullable
  public String getValue() {
    return value;
  }

  /**
   * Sets value of the attribute. May only be omitted when mutability is set to 'WriteOnly'.
   *
   * @param value Value of the attribute
   */
  public void setValue(@Nullable String value) {
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
  private boolean isValidValue(@Nullable String value) {
    return value == null || value.length() <= 2500;
  }

  /**
   * Adds value of the attribute. May only be omitted when mutability is set to 'WriteOnly'.
   *
   * @param value Value of the attribute
   * @return this
   */
  public VariableAttribute withValue(@Nullable String value) {
    setValue(value);
    return this;
  }

  /**
   * Gets the mutability of this attribute. Default is ReadWrite when omitted.
   *
   * @return The mutability of this attribute
   */
  public MutabilityEnum getMutability() {
    return mutability != null ? mutability : MutabilityEnum.ReadWrite;
  }

  /**
   * Sets the mutability of this attribute. Default is ReadWrite when omitted.
   *
   * @param mutability The mutability of this attribute
   */
  public void setMutability(@Nullable MutabilityEnum mutability) {
    this.mutability = mutability;
  }

  /**
   * Adds the mutability of this attribute. Default is ReadWrite when omitted.
   *
   * @param mutability The mutability of this attribute
   * @return this
   */
  public VariableAttribute withMutability(@Nullable MutabilityEnum mutability) {
    setMutability(mutability);
    return this;
  }

  /**
   * Gets whether value will be persistent across system reboots or power down. Default when omitted
   * is false.
   *
   * @return Whether value will be persistent across system reboots or power down
   */
  public Boolean getPersistent() {
    return persistent != null ? persistent : false;
  }

  /**
   * Sets whether value will be persistent across system reboots or power down. Default when omitted
   * is false.
   *
   * @param persistent Whether value will be persistent across system reboots or power down
   */
  public void setPersistent(@Nullable Boolean persistent) {
    this.persistent = persistent;
  }

  /**
   * Adds whether value will be persistent across system reboots or power down. Default when omitted
   * is false.
   *
   * @param persistent Whether value will be persistent across system reboots or power down
   * @return this
   */
  public VariableAttribute withPersistent(@Nullable Boolean persistent) {
    setPersistent(persistent);
    return this;
  }

  /**
   * Gets whether value that will never be changed by the Charging Station at runtime. Default when
   * omitted is false.
   *
   * @return Whether value that will never be changed by the Charging Station at runtime
   */
  public Boolean getConstant() {
    return constant != null ? constant : false;
  }

  /**
   * Sets whether value that will never be changed by the Charging Station at runtime. Default when
   * omitted is false.
   *
   * @param constant Whether value that will never be changed by the Charging Station at runtime
   */
  public void setConstant(@Nullable Boolean constant) {
    this.constant = constant;
  }

  /**
   * Adds whether value that will never be changed by the Charging Station at runtime. Default when
   * omitted is false.
   *
   * @param constant Whether value that will never be changed by the Charging Station at runtime
   * @return this
   */
  public VariableAttribute withConstant(@Nullable Boolean constant) {
    setConstant(constant);
    return this;
  }

  public boolean validate() {
    return isValidCustomData(customData) && isValidValue(value);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VariableAttribute that = (VariableAttribute) o;
    return Objects.equals(customData, that.customData)
        && Objects.equals(type, that.type)
        && Objects.equals(value, that.value)
        && Objects.equals(mutability, that.mutability)
        && Objects.equals(persistent, that.persistent)
        && Objects.equals(constant, that.constant);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customData, type, value, mutability, persistent, constant);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("type", type)
        .add("value", value)
        .add("mutability", mutability)
        .add("persistent", persistent)
        .add("constant", constant)
        .add("isValid", validate())
        .toString();
  }
}
