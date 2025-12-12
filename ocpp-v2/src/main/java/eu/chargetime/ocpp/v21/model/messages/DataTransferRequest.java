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

package eu.chargetime.ocpp.v21.model.messages;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.model.RequestWithId;
import eu.chargetime.ocpp.utilities.MoreObjects;
import eu.chargetime.ocpp.v21.model.types.CustomData;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * DataTransferRequest
 *
 * <p>OCPP 2.1 Edition 1 (c) OCA, Creative Commons Attribution-NoDerivatives 4.0 International
 * Public License
 */
public final class DataTransferRequest extends RequestWithId {
  /** May be used to indicate a specific message or implementation. */
  @Nullable private String messageId;

  /**
   * Data without specified length or format. This needs to be decided by both parties (Open to
   * implementation).
   */
  @Nullable private Object data;

  /** The identifier of the Vendor specific implementation */
  private String vendorId;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the DataTransferRequest class
   *
   * @param vendorId The identifier of the Vendor specific implementation
   */
  public DataTransferRequest(String vendorId) {
    setVendorId(vendorId);
  }

  /**
   * Gets may be used to indicate a specific message or implementation.
   *
   * @return May be used to indicate a specific message or implementation
   */
  @Nullable
  public String getMessageId() {
    return messageId;
  }

  /**
   * Sets may be used to indicate a specific message or implementation.
   *
   * @param messageId May be used to indicate a specific message or implementation
   */
  public void setMessageId(@Nullable String messageId) {
    if (!isValidMessageId(messageId)) {
      throw new PropertyConstraintException(messageId, "messageId is invalid");
    }
    this.messageId = messageId;
  }

  /**
   * Returns whether the given messageId is valid
   *
   * @param messageId the messageId to check the validity of
   * @return {@code true} if messageId is valid, {@code false} if not
   */
  private boolean isValidMessageId(@Nullable String messageId) {
    return messageId == null || messageId.length() <= 50;
  }

  /**
   * Adds may be used to indicate a specific message or implementation.
   *
   * @param messageId May be used to indicate a specific message or implementation
   * @return this
   */
  public DataTransferRequest withMessageId(@Nullable String messageId) {
    setMessageId(messageId);
    return this;
  }

  /**
   * Gets data without specified length or format. This needs to be decided by both parties (Open to
   * implementation).
   *
   * @return Data without specified length or format
   */
  @Nullable
  public Object getData() {
    return data;
  }

  /**
   * Sets data without specified length or format. This needs to be decided by both parties (Open to
   * implementation).
   *
   * @param data Data without specified length or format
   */
  public void setData(@Nullable Object data) {
    this.data = data;
  }

  /**
   * Adds data without specified length or format. This needs to be decided by both parties (Open to
   * implementation).
   *
   * @param data Data without specified length or format
   * @return this
   */
  public DataTransferRequest withData(@Nullable Object data) {
    setData(data);
    return this;
  }

  /**
   * Gets the identifier of the Vendor specific implementation
   *
   * @return The identifier of the Vendor specific implementation
   */
  public String getVendorId() {
    return vendorId;
  }

  /**
   * Sets the identifier of the Vendor specific implementation
   *
   * @param vendorId The identifier of the Vendor specific implementation
   */
  public void setVendorId(String vendorId) {
    if (!isValidVendorId(vendorId)) {
      throw new PropertyConstraintException(vendorId, "vendorId is invalid");
    }
    this.vendorId = vendorId;
  }

  /**
   * Returns whether the given vendorId is valid
   *
   * @param vendorId the vendorId to check the validity of
   * @return {@code true} if vendorId is valid, {@code false} if not
   */
  private boolean isValidVendorId(String vendorId) {
    return vendorId != null && vendorId.length() <= 255;
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
  public DataTransferRequest withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  @Override
  public boolean validate() {
    return isValidMessageId(messageId)
        && isValidVendorId(vendorId)
        && isValidCustomData(customData);
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
    DataTransferRequest that = (DataTransferRequest) o;
    return Objects.equals(messageId, that.messageId)
        && Objects.equals(data, that.data)
        && Objects.equals(vendorId, that.vendorId)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(messageId, data, vendorId, customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("messageId", messageId)
        .add("data", data)
        .add("vendorId", vendorId)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
