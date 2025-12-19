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

/** FixedVarGetType */
public final class FixedVarGet {
  /** fixedVar */
  private FixedVar fixedVar;

  /** Id of setting */
  private String id;

  /** True if setting is a default control. */
  private Boolean isDefault;

  /** True if this setting is superseded by a lower priority setting */
  private Boolean isSuperseded;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the FixedVarGet class
   *
   * @param fixedVar fixedVar
   * @param id Id of setting
   * @param isDefault True if setting is a default control.
   * @param isSuperseded True if this setting is superseded by a lower priority setting
   */
  public FixedVarGet(FixedVar fixedVar, String id, Boolean isDefault, Boolean isSuperseded) {
    setFixedVar(fixedVar);
    setId(id);
    setIsDefault(isDefault);
    setIsSuperseded(isSuperseded);
  }

  /**
   * Gets fixedVar
   *
   * @return fixedVar
   */
  public FixedVar getFixedVar() {
    return fixedVar;
  }

  /**
   * Sets fixedVar
   *
   * @param fixedVar fixedVar
   */
  public void setFixedVar(FixedVar fixedVar) {
    if (!isValidFixedVar(fixedVar)) {
      throw new PropertyConstraintException(fixedVar, "fixedVar is invalid");
    }
    this.fixedVar = fixedVar;
  }

  /**
   * Returns whether the given fixedVar is valid
   *
   * @param fixedVar the fixedVar to check the validity of
   * @return {@code true} if fixedVar is valid, {@code false} if not
   */
  private boolean isValidFixedVar(FixedVar fixedVar) {
    return fixedVar != null && fixedVar.validate();
  }

  /**
   * Gets id of setting
   *
   * @return Id of setting
   */
  public String getId() {
    return id;
  }

  /**
   * Sets id of setting
   *
   * @param id Id of setting
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
   * Gets true if setting is a default control.
   *
   * @return True if setting is a default control
   */
  public Boolean getIsDefault() {
    return isDefault;
  }

  /**
   * Sets true if setting is a default control.
   *
   * @param isDefault True if setting is a default control
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
   * Gets true if this setting is superseded by a lower priority setting
   *
   * @return True if this setting is superseded by a lower priority setting
   */
  public Boolean getIsSuperseded() {
    return isSuperseded;
  }

  /**
   * Sets true if this setting is superseded by a lower priority setting
   *
   * @param isSuperseded True if this setting is superseded by a lower priority setting
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
  public FixedVarGet withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  public boolean validate() {
    return isValidFixedVar(fixedVar)
        && isValidId(id)
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
    FixedVarGet that = (FixedVarGet) o;
    return Objects.equals(fixedVar, that.fixedVar)
        && Objects.equals(id, that.id)
        && Objects.equals(isDefault, that.isDefault)
        && Objects.equals(isSuperseded, that.isSuperseded)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(fixedVar, id, isDefault, isSuperseded, customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("fixedVar", fixedVar)
        .add("id", id)
        .add("isDefault", isDefault)
        .add("isSuperseded", isSuperseded)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
