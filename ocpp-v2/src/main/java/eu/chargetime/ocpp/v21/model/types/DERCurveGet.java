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

/** DERCurveGetType */
public final class DERCurveGet {
  /** curve */
  private DERCurve curve;

  /** Id of DER curve */
  private String id;

  /** Type of DER curve */
  private DERControlEnum curveType;

  /** True if this is a default curve */
  private Boolean isDefault;

  /**
   * True if this setting is superseded by a higher priority setting (i.e. lower value of priority)
   */
  private Boolean isSuperseded;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the DERCurveGet class
   *
   * @param curve curve
   * @param id Id of DER curve
   * @param curveType Type of DER curve
   * @param isDefault True if this is a default curve
   * @param isSuperseded True if this setting is superseded by a higher priority setting (i.e. lower
   *     value of priority)
   */
  public DERCurveGet(
      DERCurve curve,
      String id,
      DERControlEnum curveType,
      Boolean isDefault,
      Boolean isSuperseded) {
    setCurve(curve);
    setId(id);
    setCurveType(curveType);
    setIsDefault(isDefault);
    setIsSuperseded(isSuperseded);
  }

  /**
   * Gets curve
   *
   * @return curve
   */
  public DERCurve getCurve() {
    return curve;
  }

  /**
   * Sets curve
   *
   * @param curve curve
   */
  public void setCurve(DERCurve curve) {
    if (!isValidCurve(curve)) {
      throw new PropertyConstraintException(curve, "curve is invalid");
    }
    this.curve = curve;
  }

  /**
   * Returns whether the given curve is valid
   *
   * @param curve the curve to check the validity of
   * @return {@code true} if curve is valid, {@code false} if not
   */
  private boolean isValidCurve(DERCurve curve) {
    return curve != null && curve.validate();
  }

  /**
   * Gets id of DER curve
   *
   * @return Id of DER curve
   */
  public String getId() {
    return id;
  }

  /**
   * Sets id of DER curve
   *
   * @param id Id of DER curve
   */
  public void setId(String id) {
    if (!isValidId(id)) {
      throw new PropertyConstraintException(id, "id is invalid");
    }
    this.id = id;
  }

  /**
   * Returns whether the given id is valid
   *
   * @param id the id to check the validity of
   * @return {@code true} if id is valid, {@code false} if not
   */
  private boolean isValidId(String id) {
    return id != null && id.length() <= 36;
  }

  /**
   * Gets type of DER curve
   *
   * @return Type of DER curve
   */
  public DERControlEnum getCurveType() {
    return curveType;
  }

  /**
   * Sets type of DER curve
   *
   * @param curveType Type of DER curve
   */
  public void setCurveType(DERControlEnum curveType) {
    if (!isValidCurveType(curveType)) {
      throw new PropertyConstraintException(curveType, "curveType is invalid");
    }
    this.curveType = curveType;
  }

  /**
   * Returns whether the given curveType is valid
   *
   * @param curveType the curveType to check the validity of
   * @return {@code true} if curveType is valid, {@code false} if not
   */
  private boolean isValidCurveType(DERControlEnum curveType) {
    return curveType != null;
  }

  /**
   * Gets true if this is a default curve
   *
   * @return True if this is a default curve
   */
  public Boolean getIsDefault() {
    return isDefault;
  }

  /**
   * Sets true if this is a default curve
   *
   * @param isDefault True if this is a default curve
   */
  public void setIsDefault(Boolean isDefault) {
    if (!isValidIsDefault(isDefault)) {
      throw new PropertyConstraintException(isDefault, "isDefault is invalid");
    }
    this.isDefault = isDefault;
  }

  /**
   * Returns whether the given isDefault is valid
   *
   * @param isDefault the isDefault to check the validity of
   * @return {@code true} if isDefault is valid, {@code false} if not
   */
  private boolean isValidIsDefault(Boolean isDefault) {
    return isDefault != null;
  }

  /**
   * Gets true if this setting is superseded by a higher priority setting (i.e. lower value of
   * priority)
   *
   * @return True if this setting is superseded by a higher priority setting (i.e. lower value of
   *     priority)
   */
  public Boolean getIsSuperseded() {
    return isSuperseded;
  }

  /**
   * Sets true if this setting is superseded by a higher priority setting (i.e. lower value of
   * priority)
   *
   * @param isSuperseded True if this setting is superseded by a higher priority setting (i.e. lower
   *     value of priority)
   */
  public void setIsSuperseded(Boolean isSuperseded) {
    if (!isValidIsSuperseded(isSuperseded)) {
      throw new PropertyConstraintException(isSuperseded, "isSuperseded is invalid");
    }
    this.isSuperseded = isSuperseded;
  }

  /**
   * Returns whether the given isSuperseded is valid
   *
   * @param isSuperseded the isSuperseded to check the validity of
   * @return {@code true} if isSuperseded is valid, {@code false} if not
   */
  private boolean isValidIsSuperseded(Boolean isSuperseded) {
    return isSuperseded != null;
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
  public DERCurveGet withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  public boolean validate() {
    return isValidCurve(curve)
        && isValidId(id)
        && isValidCurveType(curveType)
        && isValidIsDefault(isDefault)
        && isValidIsSuperseded(isSuperseded)
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
    DERCurveGet that = (DERCurveGet) o;
    return Objects.equals(curve, that.curve)
        && Objects.equals(id, that.id)
        && Objects.equals(curveType, that.curveType)
        && Objects.equals(isDefault, that.isDefault)
        && Objects.equals(isSuperseded, that.isSuperseded)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(curve, id, curveType, isDefault, isSuperseded, customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("curve", curve)
        .add("id", id)
        .add("curveType", curveType)
        .add("isDefault", isDefault)
        .add("isSuperseded", isSuperseded)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
