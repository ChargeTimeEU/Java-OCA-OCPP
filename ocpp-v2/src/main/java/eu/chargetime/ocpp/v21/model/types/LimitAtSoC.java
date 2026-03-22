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

/** LimitAtSoCType */
public final class LimitAtSoC {
  /** The SoC value beyond which the charging rate limit should be applied. */
  private Integer soc;

  /**
   * Charging rate limit beyond the SoC value. The unit is defined by
   * chargingSchedule.chargingRateUnit.
   */
  private Double limit;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the LimitAtSoC class
   *
   * @param soc The SoC value beyond which the charging rate limit should be applied.
   * @param limit Charging rate limit beyond the SoC value. The unit is defined by
   *     chargingSchedule.chargingRateUnit.
   */
  public LimitAtSoC(Integer soc, Double limit) {
    setSoc(soc);
    setLimit(limit);
  }

  /**
   * Gets the SoC value beyond which the charging rate limit should be applied.
   *
   * @return The SoC value beyond which the charging rate limit should be applied
   */
  public Integer getSoc() {
    return soc;
  }

  /**
   * Sets the SoC value beyond which the charging rate limit should be applied.
   *
   * @param soc The SoC value beyond which the charging rate limit should be applied
   */
  public void setSoc(Integer soc) {
    if (!isValidSoc(soc)) {
      throw new PropertyConstraintException(soc, "soc is invalid");
    }
    this.soc = soc;
  }

  /**
   * Returns whether the given soc is valid
   *
   * @param soc the soc to check the validity of
   * @return {@code true} if soc is valid, {@code false} if not
   */
  private boolean isValidSoc(Integer soc) {
    return soc != null && soc >= 0 && soc <= 100;
  }

  /**
   * Gets charging rate limit beyond the SoC value. The unit is defined by
   * chargingSchedule.chargingRateUnit.
   *
   * @return Charging rate limit beyond the SoC value
   */
  public Double getLimit() {
    return limit;
  }

  /**
   * Sets charging rate limit beyond the SoC value. The unit is defined by
   * chargingSchedule.chargingRateUnit.
   *
   * @param limit Charging rate limit beyond the SoC value
   */
  public void setLimit(Double limit) {
    if (!isValidLimit(limit)) {
      throw new PropertyConstraintException(limit, "limit is invalid");
    }
    this.limit = limit;
  }

  /**
   * Returns whether the given limit is valid
   *
   * @param limit the limit to check the validity of
   * @return {@code true} if limit is valid, {@code false} if not
   */
  private boolean isValidLimit(Double limit) {
    return limit != null;
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
  public LimitAtSoC withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  public boolean validate() {
    return isValidSoc(soc) && isValidLimit(limit) && isValidCustomData(customData);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LimitAtSoC that = (LimitAtSoC) o;
    return Objects.equals(soc, that.soc)
        && Objects.equals(limit, that.limit)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(soc, limit, customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("soc", soc)
        .add("limit", limit)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
