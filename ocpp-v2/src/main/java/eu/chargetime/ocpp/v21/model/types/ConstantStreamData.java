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

/** ConstantStreamDataType */
public final class ConstantStreamData {
  /** Uniquely identifies the stream */
  private Integer id;

  /** params */
  private PeriodicEventStreamParams params;

  /** Id of monitor used to report his event. It can be a preconfigured or hardwired monitor. */
  private Integer variableMonitoringId;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the ConstantStreamData class
   *
   * @param id Uniquely identifies the stream
   * @param params params
   * @param variableMonitoringId Id of monitor used to report his event. It can be a preconfigured
   *     or hardwired monitor.
   */
  public ConstantStreamData(
      Integer id, PeriodicEventStreamParams params, Integer variableMonitoringId) {
    setId(id);
    setParams(params);
    setVariableMonitoringId(variableMonitoringId);
  }

  /**
   * Gets uniquely identifies the stream
   *
   * @return Uniquely identifies the stream
   */
  public Integer getId() {
    return id;
  }

  /**
   * Sets uniquely identifies the stream
   *
   * @param id Uniquely identifies the stream
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
    return id != null && id >= 0;
  }

  /**
   * Gets params
   *
   * @return params
   */
  public PeriodicEventStreamParams getParams() {
    return params;
  }

  /**
   * Sets params
   *
   * @param params params
   */
  public void setParams(PeriodicEventStreamParams params) {
    if (!isValidParams(params)) {
      throw new PropertyConstraintException(params, "params is invalid");
    }
    this.params = params;
  }

  /**
   * Returns whether the given params is valid
   *
   * @param params the params to check the validity of
   * @return {@code true} if params is valid, {@code false} if not
   */
  private boolean isValidParams(PeriodicEventStreamParams params) {
    return params != null && params.validate();
  }

  /**
   * Gets id of monitor used to report his event. It can be a preconfigured or hardwired monitor.
   *
   * @return Id of monitor used to report his event
   */
  public Integer getVariableMonitoringId() {
    return variableMonitoringId;
  }

  /**
   * Sets id of monitor used to report his event. It can be a preconfigured or hardwired monitor.
   *
   * @param variableMonitoringId Id of monitor used to report his event
   */
  public void setVariableMonitoringId(Integer variableMonitoringId) {
    if (!isValidVariableMonitoringId(variableMonitoringId)) {
      throw new PropertyConstraintException(
          variableMonitoringId, "variableMonitoringId is invalid");
    }
    this.variableMonitoringId = variableMonitoringId;
  }

  /**
   * Returns whether the given variableMonitoringId is valid
   *
   * @param variableMonitoringId the variableMonitoringId to check the validity of
   * @return {@code true} if variableMonitoringId is valid, {@code false} if not
   */
  private boolean isValidVariableMonitoringId(Integer variableMonitoringId) {
    return variableMonitoringId != null && variableMonitoringId >= 0;
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
  public ConstantStreamData withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  public boolean validate() {
    return isValidId(id)
        && isValidParams(params)
        && isValidVariableMonitoringId(variableMonitoringId)
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
    ConstantStreamData that = (ConstantStreamData) o;
    return Objects.equals(id, that.id)
        && Objects.equals(params, that.params)
        && Objects.equals(variableMonitoringId, that.variableMonitoringId)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, params, variableMonitoringId, customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("id", id)
        .add("params", params)
        .add("variableMonitoringId", variableMonitoringId)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
