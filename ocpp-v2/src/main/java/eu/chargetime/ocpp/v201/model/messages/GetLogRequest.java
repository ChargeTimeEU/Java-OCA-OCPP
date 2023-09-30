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
import eu.chargetime.ocpp.v201.model.types.LogEnum;
import eu.chargetime.ocpp.v201.model.types.LogParameters;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * GetLogRequest
 *
 * <p>OCPP 2.0.1 FINAL
 */
public final class GetLogRequest extends RequestWithId {
  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Log
   *
   * <p>Generic class for the configuration of logging entries.
   */
  private LogParameters log;

  /** The type of log file that the Charging Station should send. */
  private LogEnum logType;

  /** The Id of this request */
  private Integer requestId;

  /**
   * How many times the Charging Station must try to upload the log before giving up. If this field
   * is not present, it is left to Charging Station to decide how many times it wants to retry.
   */
  @Nullable private Integer retries;

  /**
   * The interval in seconds after which a retry may be attempted. If this field is not present, it
   * is left to Charging Station to decide how long to wait between attempts.
   */
  @Nullable private Integer retryInterval;

  /**
   * Constructor for the GetLogRequest class
   *
   * @param log Generic class for the configuration of logging entries.
   * @param logType The type of log file that the Charging Station should send.
   * @param requestId The Id of this request
   */
  public GetLogRequest(LogParameters log, LogEnum logType, Integer requestId) {
    setLog(log);
    setLogType(logType);
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
  public GetLogRequest withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  /**
   * Gets generic class for the configuration of logging entries.
   *
   * @return Generic class for the configuration of logging entries
   */
  public LogParameters getLog() {
    return log;
  }

  /**
   * Sets generic class for the configuration of logging entries.
   *
   * @param log Generic class for the configuration of logging entries
   */
  public void setLog(LogParameters log) {
    if (!isValidLog(log)) {
      throw new PropertyConstraintException(log, "log is invalid");
    }
    this.log = log;
  }

  /**
   * Returns whether the given log is valid
   *
   * @param log the log to check the validity of
   * @return {@code true} if log is valid, {@code false} if not
   */
  private boolean isValidLog(LogParameters log) {
    return log != null && log.validate();
  }

  /**
   * Gets the type of log file that the Charging Station should send.
   *
   * @return The type of log file that the Charging Station should send
   */
  public LogEnum getLogType() {
    return logType;
  }

  /**
   * Sets the type of log file that the Charging Station should send.
   *
   * @param logType The type of log file that the Charging Station should send
   */
  public void setLogType(LogEnum logType) {
    if (!isValidLogType(logType)) {
      throw new PropertyConstraintException(logType, "logType is invalid");
    }
    this.logType = logType;
  }

  /**
   * Returns whether the given logType is valid
   *
   * @param logType the logType to check the validity of
   * @return {@code true} if logType is valid, {@code false} if not
   */
  private boolean isValidLogType(LogEnum logType) {
    return logType != null;
  }

  /**
   * Gets the Id of this request
   *
   * @return The Id of this request
   */
  public Integer getRequestId() {
    return requestId;
  }

  /**
   * Sets the Id of this request
   *
   * @param requestId The Id of this request
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
   * Gets how many times the Charging Station must try to upload the log before giving up. If this
   * field is not present, it is left to Charging Station to decide how many times it wants to
   * retry.
   *
   * @return How many times the Charging Station must try to upload the log before giving up
   */
  @Nullable
  public Integer getRetries() {
    return retries;
  }

  /**
   * Sets how many times the Charging Station must try to upload the log before giving up. If this
   * field is not present, it is left to Charging Station to decide how many times it wants to
   * retry.
   *
   * @param retries How many times the Charging Station must try to upload the log before giving up
   */
  public void setRetries(@Nullable Integer retries) {
    this.retries = retries;
  }

  /**
   * Adds how many times the Charging Station must try to upload the log before giving up. If this
   * field is not present, it is left to Charging Station to decide how many times it wants to
   * retry.
   *
   * @param retries How many times the Charging Station must try to upload the log before giving up
   * @return this
   */
  public GetLogRequest withRetries(@Nullable Integer retries) {
    setRetries(retries);
    return this;
  }

  /**
   * Gets the interval in seconds after which a retry may be attempted. If this field is not
   * present, it is left to Charging Station to decide how long to wait between attempts.
   *
   * @return The interval in seconds after which a retry may be attempted
   */
  @Nullable
  public Integer getRetryInterval() {
    return retryInterval;
  }

  /**
   * Sets the interval in seconds after which a retry may be attempted. If this field is not
   * present, it is left to Charging Station to decide how long to wait between attempts.
   *
   * @param retryInterval The interval in seconds after which a retry may be attempted
   */
  public void setRetryInterval(@Nullable Integer retryInterval) {
    this.retryInterval = retryInterval;
  }

  /**
   * Adds the interval in seconds after which a retry may be attempted. If this field is not
   * present, it is left to Charging Station to decide how long to wait between attempts.
   *
   * @param retryInterval The interval in seconds after which a retry may be attempted
   * @return this
   */
  public GetLogRequest withRetryInterval(@Nullable Integer retryInterval) {
    setRetryInterval(retryInterval);
    return this;
  }

  @Override
  public boolean validate() {
    return isValidCustomData(customData)
        && isValidLog(log)
        && isValidLogType(logType)
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
    GetLogRequest that = (GetLogRequest) o;
    return Objects.equals(customData, that.customData)
        && Objects.equals(log, that.log)
        && Objects.equals(logType, that.logType)
        && Objects.equals(requestId, that.requestId)
        && Objects.equals(retries, that.retries)
        && Objects.equals(retryInterval, that.retryInterval);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customData, log, logType, requestId, retries, retryInterval);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("log", log)
        .add("logType", logType)
        .add("requestId", requestId)
        .add("retries", retries)
        .add("retryInterval", retryInterval)
        .add("isValid", validate())
        .toString();
  }
}
