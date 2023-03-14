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

/** Fixed read-only parameters of a variable. */
public final class VariableCharacteristics {
  /** Custom data */
  @Nullable private CustomData customData;

  /** Unit of the variable. When the transmitted value has a unit, this field SHALL be included. */
  @Nullable private String unit;

  /** Data type of this variable. */
  private DataEnum dataType;

  /** Minimum possible value of this variable. */
  @Nullable private Double minLimit;

  /**
   * Maximum possible value of this variable. When the datatype of this Variable is String,
   * OptionList, SequenceList or MemberList, this field defines the maximum length of the (CSV)
   * string.
   */
  @Nullable private Double maxLimit;

  /**
   * Allowed values when variable is Option/Member/SequenceList.
   *
   * <p>* OptionList: The (Actual) Variable value must be a single value from the reported (CSV)
   * enumeration list.
   *
   * <p>* MemberList: The (Actual) Variable value may be an (unordered) (sub-)set of the reported
   * (CSV) valid values list.
   *
   * <p>* SequenceList: The (Actual) Variable value may be an ordered (priority, etc) (sub-)set of
   * the reported (CSV) valid values.
   *
   * <p>This is a comma separated list.
   *
   * <p>The Configuration Variable ConfigurationValueSize can be used to limit
   * SetVariableData.attributeValue and VariableCharacteristics.valueList. The max size of these
   * values will always remain equal.
   */
  @Nullable private String valuesList;

  /** Flag indicating if this variable supports monitoring. */
  private Boolean supportsMonitoring;

  /**
   * Constructor for the VariableCharacteristics class
   *
   * @param dataType Data type of this variable.
   * @param supportsMonitoring Flag indicating if this variable supports monitoring.
   */
  public VariableCharacteristics(DataEnum dataType, Boolean supportsMonitoring) {
    setDataType(dataType);
    setSupportsMonitoring(supportsMonitoring);
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
  public VariableCharacteristics withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  /**
   * Gets unit of the variable. When the transmitted value has a unit, this field SHALL be included.
   *
   * @return Unit of the variable
   */
  @Nullable
  public String getUnit() {
    return unit;
  }

  /**
   * Sets unit of the variable. When the transmitted value has a unit, this field SHALL be included.
   *
   * @param unit Unit of the variable
   */
  public void setUnit(@Nullable String unit) {
    if (!isValidUnit(unit)) {
      throw new PropertyConstraintException(unit, "unit is invalid");
    }
    this.unit = unit;
  }

  /**
   * Returns whether the given unit is valid
   *
   * @param unit the unit to check the validity of
   * @return {@code true} if unit is valid, {@code false} if not
   */
  private boolean isValidUnit(@Nullable String unit) {
    return unit == null || unit.length() <= 16;
  }

  /**
   * Adds unit of the variable. When the transmitted value has a unit, this field SHALL be included.
   *
   * @param unit Unit of the variable
   * @return this
   */
  public VariableCharacteristics withUnit(@Nullable String unit) {
    setUnit(unit);
    return this;
  }

  /**
   * Gets data type of this variable.
   *
   * @return Data type of this variable
   */
  public DataEnum getDataType() {
    return dataType;
  }

  /**
   * Sets data type of this variable.
   *
   * @param dataType Data type of this variable
   */
  public void setDataType(DataEnum dataType) {
    if (!isValidDataType(dataType)) {
      throw new PropertyConstraintException(dataType, "dataType is invalid");
    }
    this.dataType = dataType;
  }

  /**
   * Returns whether the given dataType is valid
   *
   * @param dataType the dataType to check the validity of
   * @return {@code true} if dataType is valid, {@code false} if not
   */
  private boolean isValidDataType(DataEnum dataType) {
    return dataType != null;
  }

  /**
   * Gets minimum possible value of this variable.
   *
   * @return Minimum possible value of this variable
   */
  @Nullable
  public Double getMinLimit() {
    return minLimit;
  }

  /**
   * Sets minimum possible value of this variable.
   *
   * @param minLimit Minimum possible value of this variable
   */
  public void setMinLimit(@Nullable Double minLimit) {
    this.minLimit = minLimit;
  }

  /**
   * Adds minimum possible value of this variable.
   *
   * @param minLimit Minimum possible value of this variable
   * @return this
   */
  public VariableCharacteristics withMinLimit(@Nullable Double minLimit) {
    setMinLimit(minLimit);
    return this;
  }

  /**
   * Gets maximum possible value of this variable. When the datatype of this Variable is String,
   * OptionList, SequenceList or MemberList, this field defines the maximum length of the (CSV)
   * string.
   *
   * @return Maximum possible value of this variable
   */
  @Nullable
  public Double getMaxLimit() {
    return maxLimit;
  }

  /**
   * Sets maximum possible value of this variable. When the datatype of this Variable is String,
   * OptionList, SequenceList or MemberList, this field defines the maximum length of the (CSV)
   * string.
   *
   * @param maxLimit Maximum possible value of this variable
   */
  public void setMaxLimit(@Nullable Double maxLimit) {
    this.maxLimit = maxLimit;
  }

  /**
   * Adds maximum possible value of this variable. When the datatype of this Variable is String,
   * OptionList, SequenceList or MemberList, this field defines the maximum length of the (CSV)
   * string.
   *
   * @param maxLimit Maximum possible value of this variable
   * @return this
   */
  public VariableCharacteristics withMaxLimit(@Nullable Double maxLimit) {
    setMaxLimit(maxLimit);
    return this;
  }

  /**
   * Gets allowed values when variable is Option/Member/SequenceList.
   *
   * @return Allowed values when variable is Option/Member/SequenceList
   */
  @Nullable
  public String getValuesList() {
    return valuesList;
  }

  /**
   * Sets allowed values when variable is Option/Member/SequenceList.
   *
   * @param valuesList Allowed values when variable is Option/Member/SequenceList
   */
  public void setValuesList(@Nullable String valuesList) {
    if (!isValidValuesList(valuesList)) {
      throw new PropertyConstraintException(valuesList, "valuesList is invalid");
    }
    this.valuesList = valuesList;
  }

  /**
   * Returns whether the given valuesList is valid
   *
   * @param valuesList the valuesList to check the validity of
   * @return {@code true} if valuesList is valid, {@code false} if not
   */
  private boolean isValidValuesList(@Nullable String valuesList) {
    return valuesList == null || valuesList.length() <= 1000;
  }

  /**
   * Adds allowed values when variable is Option/Member/SequenceList.
   *
   * @param valuesList Allowed values when variable is Option/Member/SequenceList
   * @return this
   */
  public VariableCharacteristics withValuesList(@Nullable String valuesList) {
    setValuesList(valuesList);
    return this;
  }

  /**
   * Gets flag indicating if this variable supports monitoring.
   *
   * @return Flag indicating if this variable supports monitoring
   */
  public Boolean getSupportsMonitoring() {
    return supportsMonitoring;
  }

  /**
   * Sets flag indicating if this variable supports monitoring.
   *
   * @param supportsMonitoring Flag indicating if this variable supports monitoring
   */
  public void setSupportsMonitoring(Boolean supportsMonitoring) {
    if (!isValidSupportsMonitoring(supportsMonitoring)) {
      throw new PropertyConstraintException(supportsMonitoring, "supportsMonitoring is invalid");
    }
    this.supportsMonitoring = supportsMonitoring;
  }

  /**
   * Returns whether the given supportsMonitoring is valid
   *
   * @param supportsMonitoring the supportsMonitoring to check the validity of
   * @return {@code true} if supportsMonitoring is valid, {@code false} if not
   */
  private boolean isValidSupportsMonitoring(Boolean supportsMonitoring) {
    return supportsMonitoring != null;
  }

  public boolean validate() {
    return isValidCustomData(customData)
        && isValidUnit(unit)
        && isValidDataType(dataType)
        && isValidValuesList(valuesList)
        && isValidSupportsMonitoring(supportsMonitoring);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VariableCharacteristics that = (VariableCharacteristics) o;
    return Objects.equals(customData, that.customData)
        && Objects.equals(unit, that.unit)
        && Objects.equals(dataType, that.dataType)
        && Objects.equals(minLimit, that.minLimit)
        && Objects.equals(maxLimit, that.maxLimit)
        && Objects.equals(valuesList, that.valuesList)
        && Objects.equals(supportsMonitoring, that.supportsMonitoring);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        customData, unit, dataType, minLimit, maxLimit, valuesList, supportsMonitoring);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("unit", unit)
        .add("dataType", dataType)
        .add("minLimit", minLimit)
        .add("maxLimit", maxLimit)
        .add("valuesList", valuesList)
        .add("supportsMonitoring", supportsMonitoring)
        .add("isValid", validate())
        .toString();
  }
}
