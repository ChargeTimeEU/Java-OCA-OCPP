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
import eu.chargetime.ocpp.v21.model.types.DERControlEnum;
import eu.chargetime.ocpp.v21.model.types.DERCurve;
import eu.chargetime.ocpp.v21.model.types.EnterService;
import eu.chargetime.ocpp.v21.model.types.FixedPF;
import eu.chargetime.ocpp.v21.model.types.FixedVar;
import eu.chargetime.ocpp.v21.model.types.FreqDroop;
import eu.chargetime.ocpp.v21.model.types.Gradient;
import eu.chargetime.ocpp.v21.model.types.LimitMaxDischarge;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * SetDERControlRequest
 *
 * <p>OCPP 2.1 Edition 1 (c) OCA, Creative Commons Attribution-NoDerivatives 4.0 International
 * Public License
 */
public final class SetDERControlRequest extends RequestWithId {
  /** True if this is a default DER control */
  private Boolean isDefault;

  /** Unique id of this control, e.g. UUID */
  private String controlId;

  /** Type of control. Determines which setting field below is used. */
  private DERControlEnum controlType;

  /** curve */
  @Nullable private DERCurve curve;

  /** enterService */
  @Nullable private EnterService enterService;

  /** fixedPFAbsorb */
  @Nullable private FixedPF fixedPFAbsorb;

  /** fixedPFInject */
  @Nullable private FixedPF fixedPFInject;

  /** fixedVar */
  @Nullable private FixedVar fixedVar;

  /** freqDroop */
  @Nullable private FreqDroop freqDroop;

  /** gradient */
  @Nullable private Gradient gradient;

  /** limitMaxDischarge */
  @Nullable private LimitMaxDischarge limitMaxDischarge;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the SetDERControlRequest class
   *
   * @param isDefault True if this is a default DER control
   * @param controlId Unique id of this control, e.g. UUID
   * @param controlType Type of control. Determines which setting field below is used.
   */
  public SetDERControlRequest(Boolean isDefault, String controlId, DERControlEnum controlType) {
    setIsDefault(isDefault);
    setControlId(controlId);
    setControlType(controlType);
  }

  /**
   * Gets true if this is a default DER control
   *
   * @return True if this is a default DER control
   */
  public Boolean getIsDefault() {
    return isDefault;
  }

  /**
   * Sets true if this is a default DER control
   *
   * @param isDefault True if this is a default DER control
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
   * Gets unique id of this control, e.g. UUID
   *
   * @return Unique id of this control, e.g. UUID
   */
  public String getControlId() {
    return controlId;
  }

  /**
   * Sets unique id of this control, e.g. UUID
   *
   * @param controlId Unique id of this control, e.g. UUID
   */
  public void setControlId(String controlId) {
    if (!isValidControlId(controlId)) {
      throw new PropertyConstraintException(controlId, "controlId is invalid");
    }
    this.controlId = controlId;
  }

  /**
   * Returns whether the given controlId is valid
   *
   * @param controlId the controlId to check the validity of
   * @return {@code true} if controlId is valid, {@code false} if not
   */
  private boolean isValidControlId(String controlId) {
    return controlId != null && controlId.length() <= 36;
  }

  /**
   * Gets type of control. Determines which setting field below is used.
   *
   * @return Type of control
   */
  public DERControlEnum getControlType() {
    return controlType;
  }

  /**
   * Sets type of control. Determines which setting field below is used.
   *
   * @param controlType Type of control
   */
  public void setControlType(DERControlEnum controlType) {
    if (!isValidControlType(controlType)) {
      throw new PropertyConstraintException(controlType, "controlType is invalid");
    }
    this.controlType = controlType;
  }

  /**
   * Returns whether the given controlType is valid
   *
   * @param controlType the controlType to check the validity of
   * @return {@code true} if controlType is valid, {@code false} if not
   */
  private boolean isValidControlType(DERControlEnum controlType) {
    return controlType != null;
  }

  /**
   * Gets curve
   *
   * @return curve
   */
  @Nullable
  public DERCurve getCurve() {
    return curve;
  }

  /**
   * Sets curve
   *
   * @param curve curve
   */
  public void setCurve(@Nullable DERCurve curve) {
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
  private boolean isValidCurve(@Nullable DERCurve curve) {
    return curve == null || curve.validate();
  }

  /**
   * Adds curve
   *
   * @param curve curve
   * @return this
   */
  public SetDERControlRequest withCurve(@Nullable DERCurve curve) {
    setCurve(curve);
    return this;
  }

  /**
   * Gets enterService
   *
   * @return enterService
   */
  @Nullable
  public EnterService getEnterService() {
    return enterService;
  }

  /**
   * Sets enterService
   *
   * @param enterService enterService
   */
  public void setEnterService(@Nullable EnterService enterService) {
    if (!isValidEnterService(enterService)) {
      throw new PropertyConstraintException(enterService, "enterService is invalid");
    }
    this.enterService = enterService;
  }

  /**
   * Returns whether the given enterService is valid
   *
   * @param enterService the enterService to check the validity of
   * @return {@code true} if enterService is valid, {@code false} if not
   */
  private boolean isValidEnterService(@Nullable EnterService enterService) {
    return enterService == null || enterService.validate();
  }

  /**
   * Adds enterService
   *
   * @param enterService enterService
   * @return this
   */
  public SetDERControlRequest withEnterService(@Nullable EnterService enterService) {
    setEnterService(enterService);
    return this;
  }

  /**
   * Gets fixedPFAbsorb
   *
   * @return fixedPFAbsorb
   */
  @Nullable
  public FixedPF getFixedPFAbsorb() {
    return fixedPFAbsorb;
  }

  /**
   * Sets fixedPFAbsorb
   *
   * @param fixedPFAbsorb fixedPFAbsorb
   */
  public void setFixedPFAbsorb(@Nullable FixedPF fixedPFAbsorb) {
    if (!isValidFixedPFAbsorb(fixedPFAbsorb)) {
      throw new PropertyConstraintException(fixedPFAbsorb, "fixedPFAbsorb is invalid");
    }
    this.fixedPFAbsorb = fixedPFAbsorb;
  }

  /**
   * Returns whether the given fixedPFAbsorb is valid
   *
   * @param fixedPFAbsorb the fixedPFAbsorb to check the validity of
   * @return {@code true} if fixedPFAbsorb is valid, {@code false} if not
   */
  private boolean isValidFixedPFAbsorb(@Nullable FixedPF fixedPFAbsorb) {
    return fixedPFAbsorb == null || fixedPFAbsorb.validate();
  }

  /**
   * Adds fixedPFAbsorb
   *
   * @param fixedPFAbsorb fixedPFAbsorb
   * @return this
   */
  public SetDERControlRequest withFixedPFAbsorb(@Nullable FixedPF fixedPFAbsorb) {
    setFixedPFAbsorb(fixedPFAbsorb);
    return this;
  }

  /**
   * Gets fixedPFInject
   *
   * @return fixedPFInject
   */
  @Nullable
  public FixedPF getFixedPFInject() {
    return fixedPFInject;
  }

  /**
   * Sets fixedPFInject
   *
   * @param fixedPFInject fixedPFInject
   */
  public void setFixedPFInject(@Nullable FixedPF fixedPFInject) {
    if (!isValidFixedPFInject(fixedPFInject)) {
      throw new PropertyConstraintException(fixedPFInject, "fixedPFInject is invalid");
    }
    this.fixedPFInject = fixedPFInject;
  }

  /**
   * Returns whether the given fixedPFInject is valid
   *
   * @param fixedPFInject the fixedPFInject to check the validity of
   * @return {@code true} if fixedPFInject is valid, {@code false} if not
   */
  private boolean isValidFixedPFInject(@Nullable FixedPF fixedPFInject) {
    return fixedPFInject == null || fixedPFInject.validate();
  }

  /**
   * Adds fixedPFInject
   *
   * @param fixedPFInject fixedPFInject
   * @return this
   */
  public SetDERControlRequest withFixedPFInject(@Nullable FixedPF fixedPFInject) {
    setFixedPFInject(fixedPFInject);
    return this;
  }

  /**
   * Gets fixedVar
   *
   * @return fixedVar
   */
  @Nullable
  public FixedVar getFixedVar() {
    return fixedVar;
  }

  /**
   * Sets fixedVar
   *
   * @param fixedVar fixedVar
   */
  public void setFixedVar(@Nullable FixedVar fixedVar) {
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
  private boolean isValidFixedVar(@Nullable FixedVar fixedVar) {
    return fixedVar == null || fixedVar.validate();
  }

  /**
   * Adds fixedVar
   *
   * @param fixedVar fixedVar
   * @return this
   */
  public SetDERControlRequest withFixedVar(@Nullable FixedVar fixedVar) {
    setFixedVar(fixedVar);
    return this;
  }

  /**
   * Gets freqDroop
   *
   * @return freqDroop
   */
  @Nullable
  public FreqDroop getFreqDroop() {
    return freqDroop;
  }

  /**
   * Sets freqDroop
   *
   * @param freqDroop freqDroop
   */
  public void setFreqDroop(@Nullable FreqDroop freqDroop) {
    if (!isValidFreqDroop(freqDroop)) {
      throw new PropertyConstraintException(freqDroop, "freqDroop is invalid");
    }
    this.freqDroop = freqDroop;
  }

  /**
   * Returns whether the given freqDroop is valid
   *
   * @param freqDroop the freqDroop to check the validity of
   * @return {@code true} if freqDroop is valid, {@code false} if not
   */
  private boolean isValidFreqDroop(@Nullable FreqDroop freqDroop) {
    return freqDroop == null || freqDroop.validate();
  }

  /**
   * Adds freqDroop
   *
   * @param freqDroop freqDroop
   * @return this
   */
  public SetDERControlRequest withFreqDroop(@Nullable FreqDroop freqDroop) {
    setFreqDroop(freqDroop);
    return this;
  }

  /**
   * Gets gradient
   *
   * @return gradient
   */
  @Nullable
  public Gradient getGradient() {
    return gradient;
  }

  /**
   * Sets gradient
   *
   * @param gradient gradient
   */
  public void setGradient(@Nullable Gradient gradient) {
    if (!isValidGradient(gradient)) {
      throw new PropertyConstraintException(gradient, "gradient is invalid");
    }
    this.gradient = gradient;
  }

  /**
   * Returns whether the given gradient is valid
   *
   * @param gradient the gradient to check the validity of
   * @return {@code true} if gradient is valid, {@code false} if not
   */
  private boolean isValidGradient(@Nullable Gradient gradient) {
    return gradient == null || gradient.validate();
  }

  /**
   * Adds gradient
   *
   * @param gradient gradient
   * @return this
   */
  public SetDERControlRequest withGradient(@Nullable Gradient gradient) {
    setGradient(gradient);
    return this;
  }

  /**
   * Gets limitMaxDischarge
   *
   * @return limitMaxDischarge
   */
  @Nullable
  public LimitMaxDischarge getLimitMaxDischarge() {
    return limitMaxDischarge;
  }

  /**
   * Sets limitMaxDischarge
   *
   * @param limitMaxDischarge limitMaxDischarge
   */
  public void setLimitMaxDischarge(@Nullable LimitMaxDischarge limitMaxDischarge) {
    if (!isValidLimitMaxDischarge(limitMaxDischarge)) {
      throw new PropertyConstraintException(limitMaxDischarge, "limitMaxDischarge is invalid");
    }
    this.limitMaxDischarge = limitMaxDischarge;
  }

  /**
   * Returns whether the given limitMaxDischarge is valid
   *
   * @param limitMaxDischarge the limitMaxDischarge to check the validity of
   * @return {@code true} if limitMaxDischarge is valid, {@code false} if not
   */
  private boolean isValidLimitMaxDischarge(@Nullable LimitMaxDischarge limitMaxDischarge) {
    return limitMaxDischarge == null || limitMaxDischarge.validate();
  }

  /**
   * Adds limitMaxDischarge
   *
   * @param limitMaxDischarge limitMaxDischarge
   * @return this
   */
  public SetDERControlRequest withLimitMaxDischarge(@Nullable LimitMaxDischarge limitMaxDischarge) {
    setLimitMaxDischarge(limitMaxDischarge);
    return this;
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
  public SetDERControlRequest withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  @Override
  public boolean validate() {
    return isValidIsDefault(isDefault)
        && isValidControlId(controlId)
        && isValidControlType(controlType)
        && isValidCurve(curve)
        && isValidEnterService(enterService)
        && isValidFixedPFAbsorb(fixedPFAbsorb)
        && isValidFixedPFInject(fixedPFInject)
        && isValidFixedVar(fixedVar)
        && isValidFreqDroop(freqDroop)
        && isValidGradient(gradient)
        && isValidLimitMaxDischarge(limitMaxDischarge)
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
    SetDERControlRequest that = (SetDERControlRequest) o;
    return Objects.equals(isDefault, that.isDefault)
        && Objects.equals(controlId, that.controlId)
        && Objects.equals(controlType, that.controlType)
        && Objects.equals(curve, that.curve)
        && Objects.equals(enterService, that.enterService)
        && Objects.equals(fixedPFAbsorb, that.fixedPFAbsorb)
        && Objects.equals(fixedPFInject, that.fixedPFInject)
        && Objects.equals(fixedVar, that.fixedVar)
        && Objects.equals(freqDroop, that.freqDroop)
        && Objects.equals(gradient, that.gradient)
        && Objects.equals(limitMaxDischarge, that.limitMaxDischarge)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        isDefault,
        controlId,
        controlType,
        curve,
        enterService,
        fixedPFAbsorb,
        fixedPFInject,
        fixedVar,
        freqDroop,
        gradient,
        limitMaxDischarge,
        customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("isDefault", isDefault)
        .add("controlId", controlId)
        .add("controlType", controlType)
        .add("curve", curve)
        .add("enterService", enterService)
        .add("fixedPFAbsorb", fixedPFAbsorb)
        .add("fixedPFInject", fixedPFInject)
        .add("fixedVar", fixedVar)
        .add("freqDroop", freqDroop)
        .add("gradient", gradient)
        .add("limitMaxDischarge", limitMaxDischarge)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
