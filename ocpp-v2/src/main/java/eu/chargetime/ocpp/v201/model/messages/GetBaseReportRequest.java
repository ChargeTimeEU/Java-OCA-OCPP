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
import eu.chargetime.ocpp.v201.model.types.CustomData;
import eu.chargetime.ocpp.v201.model.types.ReportBaseEnum;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * GetBaseReportRequest
 *
 * <p>OCPP 2.0.1 FINAL
 */
public final class GetBaseReportRequest extends RequestWithId {
  /** Custom data */
  @Nullable private CustomData customData;

  /** The Id of the request. */
  private Integer requestId;

  /** The report base. */
  private ReportBaseEnum reportBase;

  /**
   * Constructor for the GetBaseReportRequest class
   *
   * @param requestId The Id of the request.
   * @param reportBase The report base.
   */
  public GetBaseReportRequest(Integer requestId, ReportBaseEnum reportBase) {
    setRequestId(requestId);
    setReportBase(reportBase);
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
  public GetBaseReportRequest withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
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
   * Gets the report base.
   *
   * @return The report base
   */
  public ReportBaseEnum getReportBase() {
    return reportBase;
  }

  /**
   * Sets the report base.
   *
   * @param reportBase The report base
   */
  public void setReportBase(ReportBaseEnum reportBase) {
    if (!isValidReportBase(reportBase)) {
      throw new PropertyConstraintException(reportBase, "reportBase is invalid");
    }
    this.reportBase = reportBase;
  }

  /**
   * Returns whether the given reportBase is valid
   *
   * @param reportBase the reportBase to check the validity of
   * @return {@code true} if reportBase is valid, {@code false} if not
   */
  private boolean isValidReportBase(ReportBaseEnum reportBase) {
    return reportBase != null;
  }

  @Override
  public boolean validate() {
    return isValidCustomData(customData)
        && isValidRequestId(requestId)
        && isValidReportBase(reportBase);
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
    GetBaseReportRequest that = (GetBaseReportRequest) o;
    return Objects.equals(customData, that.customData)
        && Objects.equals(requestId, that.requestId)
        && Objects.equals(reportBase, that.reportBase);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customData, requestId, reportBase);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("requestId", requestId)
        .add("reportBase", reportBase)
        .add("isValid", validate())
        .toString();
  }
}
