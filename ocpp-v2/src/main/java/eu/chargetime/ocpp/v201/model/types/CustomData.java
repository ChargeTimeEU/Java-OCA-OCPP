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

package eu.chargetime.ocpp.v201.model.types;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.utilities.MoreObjects;
import java.util.Objects;

/** Custom data */
public class CustomData {
  /** vendorId */
  private String vendorId;

  /**
   * Constructor for the CustomData class
   *
   * @param vendorId vendorId
   */
  public CustomData(String vendorId) {
    setVendorId(vendorId);
  }

  /**
   * Gets vendorId
   *
   * @return vendorId
   */
  public String getVendorId() {
    return vendorId;
  }

  /**
   * Sets vendorId
   *
   * @param vendorId vendorId
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

  public boolean validate() {
    return isValidVendorId(vendorId);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CustomData that = (CustomData) o;
    return Objects.equals(vendorId, that.vendorId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vendorId);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("vendorId", vendorId)
        .add("isValid", validate())
        .toString();
  }
}
