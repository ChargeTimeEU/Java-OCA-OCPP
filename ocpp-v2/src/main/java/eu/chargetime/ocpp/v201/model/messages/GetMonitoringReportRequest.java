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
import eu.chargetime.ocpp.v201.model.types.ComponentVariable;
import eu.chargetime.ocpp.v201.model.types.CustomData;
import eu.chargetime.ocpp.v201.model.types.MonitoringCriterionEnum;
import java.util.Arrays;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * GetMonitoringReportRequest
 *
 * <p>OCPP 2.0.1 FINAL
 */
public final class GetMonitoringReportRequest extends RequestWithId {
  /** Custom data */
  @Nullable private CustomData customData;

  /** Class to report components, variables and variable attributes and characteristics. */
  @Nullable private ComponentVariable[] componentVariable;

  /** The Id of the request. */
  private Integer requestId;

  /** This field contains criteria for components for which a monitoring report is requested */
  @Nullable private MonitoringCriterionEnum[] monitoringCriteria;

  /**
   * Constructor for the GetMonitoringReportRequest class
   *
   * @param requestId The Id of the request.
   */
  public GetMonitoringReportRequest(Integer requestId) {
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
  public GetMonitoringReportRequest withCustomData(@Nullable CustomData customData) {
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
  public GetMonitoringReportRequest withComponentVariable(
      @Nullable ComponentVariable[] componentVariable) {
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
   * Gets this field contains criteria for components for which a monitoring report is requested
   *
   * @return This field contains criteria for components for which a monitoring report is requested
   */
  @Nullable
  public MonitoringCriterionEnum[] getMonitoringCriteria() {
    return monitoringCriteria;
  }

  /**
   * Sets this field contains criteria for components for which a monitoring report is requested
   *
   * @param monitoringCriteria This field contains criteria for components for which a monitoring
   *     report is requested
   */
  public void setMonitoringCriteria(@Nullable MonitoringCriterionEnum[] monitoringCriteria) {
    if (!isValidMonitoringCriteria(monitoringCriteria)) {
      throw new PropertyConstraintException(monitoringCriteria, "monitoringCriteria is invalid");
    }
    this.monitoringCriteria = monitoringCriteria;
  }

  /**
   * Returns whether the given monitoringCriteria is valid
   *
   * @param monitoringCriteria the monitoringCriteria to check the validity of
   * @return {@code true} if monitoringCriteria is valid, {@code false} if not
   */
  private boolean isValidMonitoringCriteria(
      @Nullable MonitoringCriterionEnum[] monitoringCriteria) {
    return monitoringCriteria == null
        || (monitoringCriteria.length >= 1 && monitoringCriteria.length <= 3);
  }

  /**
   * Adds this field contains criteria for components for which a monitoring report is requested
   *
   * @param monitoringCriteria This field contains criteria for components for which a monitoring
   *     report is requested
   * @return this
   */
  public GetMonitoringReportRequest withMonitoringCriteria(
      @Nullable MonitoringCriterionEnum[] monitoringCriteria) {
    setMonitoringCriteria(monitoringCriteria);
    return this;
  }

  @Override
  public boolean validate() {
    return isValidCustomData(customData)
        && isValidComponentVariable(componentVariable)
        && isValidRequestId(requestId)
        && isValidMonitoringCriteria(monitoringCriteria);
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
    GetMonitoringReportRequest that = (GetMonitoringReportRequest) o;
    return Objects.equals(customData, that.customData)
        && Arrays.equals(componentVariable, that.componentVariable)
        && Objects.equals(requestId, that.requestId)
        && Arrays.equals(monitoringCriteria, that.monitoringCriteria);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        customData,
        Arrays.hashCode(componentVariable),
        requestId,
        Arrays.hashCode(monitoringCriteria));
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("componentVariable", componentVariable)
        .add("requestId", requestId)
        .add("monitoringCriteria", monitoringCriteria)
        .add("isValid", validate())
        .toString();
  }
}
