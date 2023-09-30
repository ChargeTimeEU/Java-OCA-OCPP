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
import java.time.ZonedDateTime;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * NotifyCustomerInformationRequest
 *
 * <p>OCPP 2.0.1 FINAL
 */
public final class NotifyCustomerInformationRequest extends RequestWithId {
  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * (Part of) the requested data. No format specified in which the data is returned. Should be
   * human readable.
   */
  private String data;

  /**
   * “to be continued” indicator. Indicates whether another part of the monitoringData follows in an
   * upcoming notifyMonitoringReportRequest message. Default value when omitted is false.
   */
  @Nullable private Boolean tbc;

  /** Sequence number of this message. First message starts at 0. */
  private Integer seqNo;

  /** Timestamp of the moment this message was generated at the Charging Station. */
  private ZonedDateTime generatedAt;

  /** The Id of the request. */
  private Integer requestId;

  /**
   * Constructor for the NotifyCustomerInformationRequest class
   *
   * @param data (Part of) the requested data. No format specified in which the data is returned.
   *     Should be human readable.
   * @param seqNo Sequence number of this message. First message starts at 0.
   * @param generatedAt Timestamp of the moment this message was generated at the Charging Station.
   * @param requestId The Id of the request.
   */
  public NotifyCustomerInformationRequest(
      String data, Integer seqNo, ZonedDateTime generatedAt, Integer requestId) {
    setData(data);
    setSeqNo(seqNo);
    setGeneratedAt(generatedAt);
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
  public NotifyCustomerInformationRequest withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  /**
   * Gets (Part of) the requested data. No format specified in which the data is returned. Should be
   * human readable.
   *
   * @return (Part of) the requested data
   */
  public String getData() {
    return data;
  }

  /**
   * Sets (Part of) the requested data. No format specified in which the data is returned. Should be
   * human readable.
   *
   * @param data (Part of) the requested data
   */
  public void setData(String data) {
    if (!isValidData(data)) {
      throw new PropertyConstraintException(data, "data is invalid");
    }
    this.data = data;
  }

  /**
   * Returns whether the given data is valid
   *
   * @param data the data to check the validity of
   * @return {@code true} if data is valid, {@code false} if not
   */
  private boolean isValidData(String data) {
    return data != null && data.length() <= 512;
  }

  /**
   * Gets “to be continued” indicator. Indicates whether another part of the monitoringData follows
   * in an upcoming notifyMonitoringReportRequest message. Default value when omitted is false.
   *
   * @return “to be continued” indicator
   */
  public Boolean getTbc() {
    return tbc != null ? tbc : false;
  }

  /**
   * Sets “to be continued” indicator. Indicates whether another part of the monitoringData follows
   * in an upcoming notifyMonitoringReportRequest message. Default value when omitted is false.
   *
   * @param tbc “to be continued” indicator
   */
  public void setTbc(@Nullable Boolean tbc) {
    this.tbc = tbc;
  }

  /**
   * Adds “to be continued” indicator. Indicates whether another part of the monitoringData follows
   * in an upcoming notifyMonitoringReportRequest message. Default value when omitted is false.
   *
   * @param tbc “to be continued” indicator
   * @return this
   */
  public NotifyCustomerInformationRequest withTbc(@Nullable Boolean tbc) {
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

  @Override
  public boolean validate() {
    return isValidCustomData(customData)
        && isValidData(data)
        && isValidSeqNo(seqNo)
        && isValidGeneratedAt(generatedAt)
        && isValidRequestId(requestId);
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
    NotifyCustomerInformationRequest that = (NotifyCustomerInformationRequest) o;
    return Objects.equals(customData, that.customData)
        && Objects.equals(data, that.data)
        && Objects.equals(tbc, that.tbc)
        && Objects.equals(seqNo, that.seqNo)
        && Objects.equals(generatedAt, that.generatedAt)
        && Objects.equals(requestId, that.requestId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customData, data, tbc, seqNo, generatedAt, requestId);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("data", data)
        .add("tbc", tbc)
        .add("seqNo", seqNo)
        .add("generatedAt", generatedAt)
        .add("requestId", requestId)
        .add("isValid", validate())
        .toString();
  }
}
