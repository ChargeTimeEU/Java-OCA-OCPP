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
import javax.annotation.Nullable;

/**
 * Wireless Communication Module
 *
 * <p>Parameters required for initiating and maintaining wireless communication with other devices.
 */
public final class Modem {
  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Wireless Communication Module. ICCID. CI20 Text
   *
   * <p>The ICCID of the modem’s SIM card.
   */
  @Nullable private String iccid;

  /**
   * Wireless Communication Module. IMSI. CI20 Text
   *
   * <p>The IMSI of the modem’s SIM card.
   */
  @Nullable private String imsi;

  /** Constructor for the Modem class */
  public Modem() {}

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
  public Modem withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  /**
   * Gets the ICCID of the modem’s SIM card.
   *
   * @return The ICCID of the modem’s SIM card
   */
  @Nullable
  public String getIccid() {
    return iccid;
  }

  /**
   * Sets the ICCID of the modem’s SIM card.
   *
   * @param iccid The ICCID of the modem’s SIM card
   */
  public void setIccid(@Nullable String iccid) {
    if (!isValidIccid(iccid)) {
      throw new PropertyConstraintException(iccid, "iccid is invalid");
    }
    this.iccid = iccid;
  }

  /**
   * Returns whether the given iccid is valid
   *
   * @param iccid the iccid to check the validity of
   * @return {@code true} if iccid is valid, {@code false} if not
   */
  private boolean isValidIccid(@Nullable String iccid) {
    return iccid == null || iccid.length() <= 20;
  }

  /**
   * Adds the ICCID of the modem’s SIM card.
   *
   * @param iccid The ICCID of the modem’s SIM card
   * @return this
   */
  public Modem withIccid(@Nullable String iccid) {
    setIccid(iccid);
    return this;
  }

  /**
   * Gets the IMSI of the modem’s SIM card.
   *
   * @return The IMSI of the modem’s SIM card
   */
  @Nullable
  public String getImsi() {
    return imsi;
  }

  /**
   * Sets the IMSI of the modem’s SIM card.
   *
   * @param imsi The IMSI of the modem’s SIM card
   */
  public void setImsi(@Nullable String imsi) {
    if (!isValidImsi(imsi)) {
      throw new PropertyConstraintException(imsi, "imsi is invalid");
    }
    this.imsi = imsi;
  }

  /**
   * Returns whether the given imsi is valid
   *
   * @param imsi the imsi to check the validity of
   * @return {@code true} if imsi is valid, {@code false} if not
   */
  private boolean isValidImsi(@Nullable String imsi) {
    return imsi == null || imsi.length() <= 20;
  }

  /**
   * Adds the IMSI of the modem’s SIM card.
   *
   * @param imsi The IMSI of the modem’s SIM card
   * @return this
   */
  public Modem withImsi(@Nullable String imsi) {
    setImsi(imsi);
    return this;
  }

  public boolean validate() {
    return isValidCustomData(customData) && isValidIccid(iccid) && isValidImsi(imsi);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Modem that = (Modem) o;
    return Objects.equals(customData, that.customData)
        && Objects.equals(iccid, that.iccid)
        && Objects.equals(imsi, that.imsi);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customData, iccid, imsi);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("iccid", iccid)
        .add("imsi", imsi)
        .add("isValid", validate())
        .toString();
  }
}
