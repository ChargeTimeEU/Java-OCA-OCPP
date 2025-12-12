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

/** Part of ISO 15118-20 price schedule. */
public final class AdditionalSelectedServices {
  /** Part of ISO 15118-20 price schedule. */
  private RationalNumber serviceFee;

  /** Human readable string to identify this service. */
  private String serviceName;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the AdditionalSelectedServices class
   *
   * @param serviceFee Part of ISO 15118-20 price schedule.
   * @param serviceName Human readable string to identify this service.
   */
  public AdditionalSelectedServices(RationalNumber serviceFee, String serviceName) {
    setServiceFee(serviceFee);
    setServiceName(serviceName);
  }

  /**
   * Gets part of ISO 15118-20 price schedule.
   *
   * @return Part of ISO 15118-20 price schedule
   */
  public RationalNumber getServiceFee() {
    return serviceFee;
  }

  /**
   * Sets part of ISO 15118-20 price schedule.
   *
   * @param serviceFee Part of ISO 15118-20 price schedule
   */
  public void setServiceFee(RationalNumber serviceFee) {
    if (!isValidServiceFee(serviceFee)) {
      throw new PropertyConstraintException(serviceFee, "serviceFee is invalid");
    }
    this.serviceFee = serviceFee;
  }

  /**
   * Returns whether the given serviceFee is valid
   *
   * @param serviceFee the serviceFee to check the validity of
   * @return {@code true} if serviceFee is valid, {@code false} if not
   */
  private boolean isValidServiceFee(RationalNumber serviceFee) {
    return serviceFee != null && serviceFee.validate();
  }

  /**
   * Gets human readable string to identify this service.
   *
   * @return Human readable string to identify this service
   */
  public String getServiceName() {
    return serviceName;
  }

  /**
   * Sets human readable string to identify this service.
   *
   * @param serviceName Human readable string to identify this service
   */
  public void setServiceName(String serviceName) {
    if (!isValidServiceName(serviceName)) {
      throw new PropertyConstraintException(serviceName, "serviceName is invalid");
    }
    this.serviceName = serviceName;
  }

  /**
   * Returns whether the given serviceName is valid
   *
   * @param serviceName the serviceName to check the validity of
   * @return {@code true} if serviceName is valid, {@code false} if not
   */
  private boolean isValidServiceName(String serviceName) {
    return serviceName != null && serviceName.length() <= 80;
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
  public AdditionalSelectedServices withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  public boolean validate() {
    return isValidServiceFee(serviceFee)
        && isValidServiceName(serviceName)
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
    AdditionalSelectedServices that = (AdditionalSelectedServices) o;
    return Objects.equals(serviceFee, that.serviceFee)
        && Objects.equals(serviceName, that.serviceName)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(serviceFee, serviceName, customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("serviceFee", serviceFee)
        .add("serviceName", serviceName)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
