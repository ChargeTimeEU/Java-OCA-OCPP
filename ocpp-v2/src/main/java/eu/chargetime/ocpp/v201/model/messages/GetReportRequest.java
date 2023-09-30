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
import eu.chargetime.ocpp.v201.model.types.ComponentCriterionEnum;
import eu.chargetime.ocpp.v201.model.types.ComponentVariable;
import eu.chargetime.ocpp.v201.model.types.CustomData;
import java.util.Arrays;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * GetReportRequest
 *
 * <p>OCPP 2.0.1 FINAL
 */
public final class GetReportRequest extends RequestWithId {
  /** Custom data */
  @Nullable private CustomData customData;

  /** Class to report components, variables and variable attributes and characteristics. */
  @Nullable private ComponentVariable[] componentVariable;

  /** The Id of the request. */
  private Integer requestId;

  /** This field contains criteria for components for which a report is requested */
  @Nullable private ComponentCriterionEnum[] componentCriteria;

  /**
   * Constructor for the GetReportRequest class
   *
   * @param requestId The Id of the request.
   */
  public GetReportRequest(Integer requestId) {
    setRequestId(requestId);
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
  public GetReportRequest withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  /**
   * Gets class to report components, variables and variable attributes and characteristics.
   *
   * @return Class to report components, variables and variable attributes and characteristics
   */
  @Nullable
  public ComponentVariable[] getComponentVariable() {
    return componentVariable;
  }

  /**
   * Sets class to report components, variables and variable attributes and characteristics.
   *
   * @param componentVariable Class to report components, variables and variable attributes and
   *     characteristics
   */
  public void setComponentVariable(@Nullable ComponentVariable[] componentVariable) {
    if (!isValidComponentVariable(componentVariable)) {
      throw new PropertyConstraintException(componentVariable, "componentVariable is invalid");
    }
    this.componentVariable = componentVariable;
  }

  /**
   * Returns whether the given componentVariable is valid
   *
   * @param componentVariable the componentVariable to check the validity of
   * @return {@code true} if componentVariable is valid, {@code false} if not
   */
  private boolean isValidComponentVariable(@Nullable ComponentVariable[] componentVariable) {
    return componentVariable == null
        || (componentVariable.length >= 1
            && Arrays.stream(componentVariable).allMatch(item -> item.validate()));
  }

  /**
   * Adds class to report components, variables and variable attributes and characteristics.
   *
   * @param componentVariable Class to report components, variables and variable attributes and
   *     characteristics
   * @return this
   */
  public GetReportRequest withComponentVariable(@Nullable ComponentVariable[] componentVariable) {
    setComponentVariable(componentVariable);
    return this;
  }

  /**
   * Gets the Id of the request.
   *
   * @return The Id of the request
   */
  public Integer getRequestId() {
    return requestId;
  }

  /**
   * Sets the Id of the request.
   *
   * @param requestId The Id of the request
   */
  public void setRequestId(Integer requestId) {
    if (!isValidRequestId(requestId)) {
      throw new PropertyConstraintException(requestId, "requestId is invalid");
    }
    this.requestId = requestId;
  }

  /**
   * Returns whether the given requestId is valid
   *
   * @param requestId the requestId to check the validity of
   * @return {@code true} if requestId is valid, {@code false} if not
   */
  private boolean isValidRequestId(Integer requestId) {
    return requestId != null;
  }

  /**
   * Gets this field contains criteria for components for which a report is requested
   *
   * @return This field contains criteria for components for which a report is requested
   */
  @Nullable
  public ComponentCriterionEnum[] getComponentCriteria() {
    return componentCriteria;
  }

  /**
   * Sets this field contains criteria for components for which a report is requested
   *
   * @param componentCriteria This field contains criteria for components for which a report is
   *     requested
   */
  public void setComponentCriteria(@Nullable ComponentCriterionEnum[] componentCriteria) {
    if (!isValidComponentCriteria(componentCriteria)) {
      throw new PropertyConstraintException(componentCriteria, "componentCriteria is invalid");
    }
    this.componentCriteria = componentCriteria;
  }

  /**
   * Returns whether the given componentCriteria is valid
   *
   * @param componentCriteria the componentCriteria to check the validity of
   * @return {@code true} if componentCriteria is valid, {@code false} if not
   */
  private boolean isValidComponentCriteria(@Nullable ComponentCriterionEnum[] componentCriteria) {
    return componentCriteria == null
        || (componentCriteria.length >= 1 && componentCriteria.length <= 4);
  }

  /**
   * Adds this field contains criteria for components for which a report is requested
   *
   * @param componentCriteria This field contains criteria for components for which a report is
   *     requested
   * @return this
   */
  public GetReportRequest withComponentCriteria(
      @Nullable ComponentCriterionEnum[] componentCriteria) {
    setComponentCriteria(componentCriteria);
    return this;
  }

  @Override
  public boolean validate() {
    return isValidCustomData(customData)
        && isValidComponentVariable(componentVariable)
        && isValidRequestId(requestId)
        && isValidComponentCriteria(componentCriteria);
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
    GetReportRequest that = (GetReportRequest) o;
    return Objects.equals(customData, that.customData)
        && Arrays.equals(componentVariable, that.componentVariable)
        && Objects.equals(requestId, that.requestId)
        && Arrays.equals(componentCriteria, that.componentCriteria);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        customData,
        Arrays.hashCode(componentVariable),
        requestId,
        Arrays.hashCode(componentCriteria));
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("componentVariable", componentVariable)
        .add("requestId", requestId)
        .add("componentCriteria", componentCriteria)
        .add("isValid", validate())
        .toString();
  }
}
