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

/** StreamDataElementType */
public final class StreamDataElement {
  /** Offset relative to basetime of this message. basetime + t is timestamp of recorded value. */
  private Double t;

  /** v */
  private String v;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the StreamDataElement class
   *
   * @param t Offset relative to basetime of this message. basetime + t is timestamp of recorded
   *     value.
   * @param v v
   */
  public StreamDataElement(Double t, String v) {
    setT(t);
    setV(v);
  }

  /**
   * Gets offset relative to basetime of this message. basetime + t is timestamp of recorded value.
   *
   * @return Offset relative to basetime of this message
   */
  public Double getT() {
    return t;
  }

  /**
   * Sets offset relative to basetime of this message. basetime + t is timestamp of recorded value.
   *
   * @param t Offset relative to basetime of this message
   */
  public void setT(Double t) {
    if (!isValidT(t)) {
      throw new PropertyConstraintException(t, "t is invalid");
    }
    this.t = t;
  }

  /**
   * Returns whether the given t is valid
   *
   * @param t the t to check the validity of
   * @return {@code true} if t is valid, {@code false} if not
   */
  private boolean isValidT(Double t) {
    return t != null;
  }

  /**
   * Gets v
   *
   * @return v
   */
  public String getV() {
    return v;
  }

  /**
   * Sets v
   *
   * @param v v
   */
  public void setV(String v) {
    if (!isValidV(v)) {
      throw new PropertyConstraintException(v, "v is invalid");
    }
    this.v = v;
  }

  /**
   * Returns whether the given v is valid
   *
   * @param v the v to check the validity of
   * @return {@code true} if v is valid, {@code false} if not
   */
  private boolean isValidV(String v) {
    return v != null && v.length() <= 2500;
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
  public StreamDataElement withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  public boolean validate() {
    return isValidT(t) && isValidV(v) && isValidCustomData(customData);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StreamDataElement that = (StreamDataElement) o;
    return Objects.equals(t, that.t)
        && Objects.equals(v, that.v)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(t, v, customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("t", t)
        .add("v", v)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
