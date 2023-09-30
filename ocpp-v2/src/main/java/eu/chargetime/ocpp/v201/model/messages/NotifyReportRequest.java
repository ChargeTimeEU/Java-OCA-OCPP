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
import eu.chargetime.ocpp.v201.model.types.ReportData;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * NotifyReportRequest
 *
 * <p>OCPP 2.0.1 FINAL
 */
public final class NotifyReportRequest extends RequestWithId {
  /** Custom data */
  @Nullable private CustomData customData;

  /** The id of the GetReportRequest or GetBaseReportRequest that requested this report */
  private Integer requestId;

  /** Timestamp of the moment this message was generated at the Charging Station. */
  private ZonedDateTime generatedAt;

  /** Class to report components, variables and variable attributes and characteristics. */
  @Nullable private ReportData[] reportData;

  /**
   * “to be continued” indicator. Indicates whether another part of the report follows in an
   * upcoming notifyReportRequest message. Default value when omitted is false.
   */
  @Nullable private Boolean tbc;

  /** Sequence number of this message. First message starts at 0. */
  private Integer seqNo;

  /**
   * Constructor for the NotifyReportRequest class
   *
   * @param requestId The id of the GetReportRequest or GetBaseReportRequest that requested this
   *     report
   * @param generatedAt Timestamp of the moment this message was generated at the Charging Station.
   * @param seqNo Sequence number of this message. First message starts at 0.
   */
  public NotifyReportRequest(Integer requestId, ZonedDateTime generatedAt, Integer seqNo) {
    setRequestId(requestId);
    setGeneratedAt(generatedAt);
    setSeqNo(seqNo);
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
  public NotifyReportRequest withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  /**
   * Gets the id of the GetReportRequest or GetBaseReportRequest that requested this report
   *
   * @return The id of the GetReportRequest or GetBaseReportRequest that requested this report
   */
  public Integer getRequestId() {
    return requestId;
  }

  /**
   * Sets the id of the GetReportRequest or GetBaseReportRequest that requested this report
   *
   * @param requestId The id of the GetReportRequest or GetBaseReportRequest that requested this
   *     report
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
   * Gets timestamp of the moment this message was generated at the Charging Station.
   *
   * @return Timestamp of the moment this message was generated at the Charging Station
   */
  public ZonedDateTime getGeneratedAt() {
    return generatedAt;
  }

  /**
   * Sets timestamp of the moment this message was generated at the Charging Station.
   *
   * @param generatedAt Timestamp of the moment this message was generated at the Charging Station
   */
  public void setGeneratedAt(ZonedDateTime generatedAt) {
    if (!isValidGeneratedAt(generatedAt)) {
      throw new PropertyConstraintException(generatedAt, "generatedAt is invalid");
    }
    this.generatedAt = generatedAt;
  }

  /**
   * Returns whether the given generatedAt is valid
   *
   * @param generatedAt the generatedAt to check the validity of
   * @return {@code true} if generatedAt is valid, {@code false} if not
   */
  private boolean isValidGeneratedAt(ZonedDateTime generatedAt) {
    return generatedAt != null;
  }

  /**
   * Gets class to report components, variables and variable attributes and characteristics.
   *
   * @return Class to report components, variables and variable attributes and characteristics
   */
  @Nullable
  public ReportData[] getReportData() {
    return reportData;
  }

  /**
   * Sets class to report components, variables and variable attributes and characteristics.
   *
   * @param reportData Class to report components, variables and variable attributes and
   *     characteristics
   */
  public void setReportData(@Nullable ReportData[] reportData) {
    if (!isValidReportData(reportData)) {
      throw new PropertyConstraintException(reportData, "reportData is invalid");
    }
    this.reportData = reportData;
  }

  /**
   * Returns whether the given reportData is valid
   *
   * @param reportData the reportData to check the validity of
   * @return {@code true} if reportData is valid, {@code false} if not
   */
  private boolean isValidReportData(@Nullable ReportData[] reportData) {
    return reportData == null
        || (reportData.length >= 1 && Arrays.stream(reportData).allMatch(item -> item.validate()));
  }

  /**
   * Adds class to report components, variables and variable attributes and characteristics.
   *
   * @param reportData Class to report components, variables and variable attributes and
   *     characteristics
   * @return this
   */
  public NotifyReportRequest withReportData(@Nullable ReportData[] reportData) {
    setReportData(reportData);
    return this;
  }

  /**
   * Gets “to be continued” indicator. Indicates whether another part of the report follows in an
   * upcoming notifyReportRequest message. Default value when omitted is false.
   *
   * @return “to be continued” indicator
   */
  public Boolean getTbc() {
    return tbc != null ? tbc : false;
  }

  /**
   * Sets “to be continued” indicator. Indicates whether another part of the report follows in an
   * upcoming notifyReportRequest message. Default value when omitted is false.
   *
   * @param tbc “to be continued” indicator
   */
  public void setTbc(@Nullable Boolean tbc) {
    this.tbc = tbc;
  }

  /**
   * Adds “to be continued” indicator. Indicates whether another part of the report follows in an
   * upcoming notifyReportRequest message. Default value when omitted is false.
   *
   * @param tbc “to be continued” indicator
   * @return this
   */
  public NotifyReportRequest withTbc(@Nullable Boolean tbc) {
    setTbc(tbc);
    return this;
  }

  /**
   * Gets sequence number of this message. First message starts at 0.
   *
   * @return Sequence number of this message
   */
  public Integer getSeqNo() {
    return seqNo;
  }

  /**
   * Sets sequence number of this message. First message starts at 0.
   *
   * @param seqNo Sequence number of this message
   */
  public void setSeqNo(Integer seqNo) {
    if (!isValidSeqNo(seqNo)) {
      throw new PropertyConstraintException(seqNo, "seqNo is invalid");
    }
    this.seqNo = seqNo;
  }

  /**
   * Returns whether the given seqNo is valid
   *
   * @param seqNo the seqNo to check the validity of
   * @return {@code true} if seqNo is valid, {@code false} if not
   */
  private boolean isValidSeqNo(Integer seqNo) {
    return seqNo != null;
  }

  @Override
  public boolean validate() {
    return isValidCustomData(customData)
        && isValidRequestId(requestId)
        && isValidGeneratedAt(generatedAt)
        && isValidReportData(reportData)
        && isValidSeqNo(seqNo);
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
    NotifyReportRequest that = (NotifyReportRequest) o;
    return Objects.equals(customData, that.customData)
        && Objects.equals(requestId, that.requestId)
        && Objects.equals(generatedAt, that.generatedAt)
        && Arrays.equals(reportData, that.reportData)
        && Objects.equals(tbc, that.tbc)
        && Objects.equals(seqNo, that.seqNo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        customData, requestId, generatedAt, Arrays.hashCode(reportData), tbc, seqNo);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("requestId", requestId)
        .add("generatedAt", generatedAt)
        .add("reportData", reportData)
        .add("tbc", tbc)
        .add("seqNo", seqNo)
        .add("isValid", validate())
        .toString();
  }
}
