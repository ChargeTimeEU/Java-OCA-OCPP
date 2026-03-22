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
import eu.chargetime.ocpp.v21.model.types.DERCurveGet;
import eu.chargetime.ocpp.v21.model.types.EnterServiceGet;
import eu.chargetime.ocpp.v21.model.types.FixedPFGet;
import eu.chargetime.ocpp.v21.model.types.FixedVarGet;
import eu.chargetime.ocpp.v21.model.types.FreqDroopGet;
import eu.chargetime.ocpp.v21.model.types.GradientGet;
import eu.chargetime.ocpp.v21.model.types.LimitMaxDischargeGet;
import java.util.Arrays;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * ReportDERControlRequest
 *
 * <p>OCPP 2.1 Edition 1 (c) OCA, Creative Commons Attribution-NoDerivatives 4.0 International
 * Public License
 */
public final class ReportDERControlRequest extends RequestWithId {
  /** curve */
  @Nullable private DERCurveGet[] curve;

  /** enterService */
  @Nullable private EnterServiceGet[] enterService;

  /** fixedPFAbsorb */
  @Nullable private FixedPFGet[] fixedPFAbsorb;

  /** fixedPFInject */
  @Nullable private FixedPFGet[] fixedPFInject;

  /** fixedVar */
  @Nullable private FixedVarGet[] fixedVar;

  /** freqDroop */
  @Nullable private FreqDroopGet[] freqDroop;

  /** gradient */
  @Nullable private GradientGet[] gradient;

  /** limitMaxDischarge */
  @Nullable private LimitMaxDischargeGet[] limitMaxDischarge;

  /** RequestId from GetDERControlRequest. */
  private Integer requestId;

  /**
   * To Be Continued. Default value when omitted: false.
   *
   * <p>False indicates that there are no further messages as part of this report.
   */
  @Nullable private Boolean tbc;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the ReportDERControlRequest class
   *
   * @param requestId RequestId from GetDERControlRequest.
   */
  public ReportDERControlRequest(Integer requestId) {
    setRequestId(requestId);
  }

  /**
   * Gets curve
   *
   * @return curve
   */
  @Nullable
  public DERCurveGet[] getCurve() {
    return curve;
  }

  /**
   * Sets curve
   *
   * @param curve curve
   */
  public void setCurve(@Nullable DERCurveGet[] curve) {
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
  private boolean isValidCurve(@Nullable DERCurveGet[] curve) {
    return curve == null
        || (curve.length >= 1
            && curve.length <= 24
            && Arrays.stream(curve).allMatch(item -> item.validate()));
  }

  /**
   * Adds curve
   *
   * @param curve curve
   * @return this
   */
  public ReportDERControlRequest withCurve(@Nullable DERCurveGet[] curve) {
    setCurve(curve);
    return this;
  }

  /**
   * Gets enterService
   *
   * @return enterService
   */
  @Nullable
  public EnterServiceGet[] getEnterService() {
    return enterService;
  }

  /**
   * Sets enterService
   *
   * @param enterService enterService
   */
  public void setEnterService(@Nullable EnterServiceGet[] enterService) {
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
  private boolean isValidEnterService(@Nullable EnterServiceGet[] enterService) {
    return enterService == null
        || (enterService.length >= 1
            && enterService.length <= 24
            && Arrays.stream(enterService).allMatch(item -> item.validate()));
  }

  /**
   * Adds enterService
   *
   * @param enterService enterService
   * @return this
   */
  public ReportDERControlRequest withEnterService(@Nullable EnterServiceGet[] enterService) {
    setEnterService(enterService);
    return this;
  }

  /**
   * Gets fixedPFAbsorb
   *
   * @return fixedPFAbsorb
   */
  @Nullable
  public FixedPFGet[] getFixedPFAbsorb() {
    return fixedPFAbsorb;
  }

  /**
   * Sets fixedPFAbsorb
   *
   * @param fixedPFAbsorb fixedPFAbsorb
   */
  public void setFixedPFAbsorb(@Nullable FixedPFGet[] fixedPFAbsorb) {
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
  private boolean isValidFixedPFAbsorb(@Nullable FixedPFGet[] fixedPFAbsorb) {
    return fixedPFAbsorb == null
        || (fixedPFAbsorb.length >= 1
            && fixedPFAbsorb.length <= 24
            && Arrays.stream(fixedPFAbsorb).allMatch(item -> item.validate()));
  }

  /**
   * Adds fixedPFAbsorb
   *
   * @param fixedPFAbsorb fixedPFAbsorb
   * @return this
   */
  public ReportDERControlRequest withFixedPFAbsorb(@Nullable FixedPFGet[] fixedPFAbsorb) {
    setFixedPFAbsorb(fixedPFAbsorb);
    return this;
  }

  /**
   * Gets fixedPFInject
   *
   * @return fixedPFInject
   */
  @Nullable
  public FixedPFGet[] getFixedPFInject() {
    return fixedPFInject;
  }

  /**
   * Sets fixedPFInject
   *
   * @param fixedPFInject fixedPFInject
   */
  public void setFixedPFInject(@Nullable FixedPFGet[] fixedPFInject) {
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
  private boolean isValidFixedPFInject(@Nullable FixedPFGet[] fixedPFInject) {
    return fixedPFInject == null
        || (fixedPFInject.length >= 1
            && fixedPFInject.length <= 24
            && Arrays.stream(fixedPFInject).allMatch(item -> item.validate()));
  }

  /**
   * Adds fixedPFInject
   *
   * @param fixedPFInject fixedPFInject
   * @return this
   */
  public ReportDERControlRequest withFixedPFInject(@Nullable FixedPFGet[] fixedPFInject) {
    setFixedPFInject(fixedPFInject);
    return this;
  }

  /**
   * Gets fixedVar
   *
   * @return fixedVar
   */
  @Nullable
  public FixedVarGet[] getFixedVar() {
    return fixedVar;
  }

  /**
   * Sets fixedVar
   *
   * @param fixedVar fixedVar
   */
  public void setFixedVar(@Nullable FixedVarGet[] fixedVar) {
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
  private boolean isValidFixedVar(@Nullable FixedVarGet[] fixedVar) {
    return fixedVar == null
        || (fixedVar.length >= 1
            && fixedVar.length <= 24
            && Arrays.stream(fixedVar).allMatch(item -> item.validate()));
  }

  /**
   * Adds fixedVar
   *
   * @param fixedVar fixedVar
   * @return this
   */
  public ReportDERControlRequest withFixedVar(@Nullable FixedVarGet[] fixedVar) {
    setFixedVar(fixedVar);
    return this;
  }

  /**
   * Gets freqDroop
   *
   * @return freqDroop
   */
  @Nullable
  public FreqDroopGet[] getFreqDroop() {
    return freqDroop;
  }

  /**
   * Sets freqDroop
   *
   * @param freqDroop freqDroop
   */
  public void setFreqDroop(@Nullable FreqDroopGet[] freqDroop) {
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
  private boolean isValidFreqDroop(@Nullable FreqDroopGet[] freqDroop) {
    return freqDroop == null
        || (freqDroop.length >= 1
            && freqDroop.length <= 24
            && Arrays.stream(freqDroop).allMatch(item -> item.validate()));
  }

  /**
   * Adds freqDroop
   *
   * @param freqDroop freqDroop
   * @return this
   */
  public ReportDERControlRequest withFreqDroop(@Nullable FreqDroopGet[] freqDroop) {
    setFreqDroop(freqDroop);
    return this;
  }

  /**
   * Gets gradient
   *
   * @return gradient
   */
  @Nullable
  public GradientGet[] getGradient() {
    return gradient;
  }

  /**
   * Sets gradient
   *
   * @param gradient gradient
   */
  public void setGradient(@Nullable GradientGet[] gradient) {
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
  private boolean isValidGradient(@Nullable GradientGet[] gradient) {
    return gradient == null
        || (gradient.length >= 1
            && gradient.length <= 24
            && Arrays.stream(gradient).allMatch(item -> item.validate()));
  }

  /**
   * Adds gradient
   *
   * @param gradient gradient
   * @return this
   */
  public ReportDERControlRequest withGradient(@Nullable GradientGet[] gradient) {
    setGradient(gradient);
    return this;
  }

  /**
   * Gets limitMaxDischarge
   *
   * @return limitMaxDischarge
   */
  @Nullable
  public LimitMaxDischargeGet[] getLimitMaxDischarge() {
    return limitMaxDischarge;
  }

  /**
   * Sets limitMaxDischarge
   *
   * @param limitMaxDischarge limitMaxDischarge
   */
  public void setLimitMaxDischarge(@Nullable LimitMaxDischargeGet[] limitMaxDischarge) {
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
  private boolean isValidLimitMaxDischarge(@Nullable LimitMaxDischargeGet[] limitMaxDischarge) {
    return limitMaxDischarge == null
        || (limitMaxDischarge.length >= 1
            && limitMaxDischarge.length <= 24
            && Arrays.stream(limitMaxDischarge).allMatch(item -> item.validate()));
  }

  /**
   * Adds limitMaxDischarge
   *
   * @param limitMaxDischarge limitMaxDischarge
   * @return this
   */
  public ReportDERControlRequest withLimitMaxDischarge(
      @Nullable LimitMaxDischargeGet[] limitMaxDischarge) {
    setLimitMaxDischarge(limitMaxDischarge);
    return this;
  }

  /**
   * Gets requestId from GetDERControlRequest.
   *
   * @return RequestId from GetDERControlRequest
   */
  public Integer getRequestId() {
    return requestId;
  }

  /**
   * Sets requestId from GetDERControlRequest.
   *
   * @param requestId RequestId from GetDERControlRequest
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
   * Gets to Be Continued. Default value when omitted: false.
   *
   * @return To Be Continued
   */
  @Nullable
  public Boolean getTbc() {
    return tbc;
  }

  /**
   * Sets to Be Continued. Default value when omitted: false.
   *
   * @param tbc To Be Continued
   */
  public void setTbc(@Nullable Boolean tbc) {
    this.tbc = tbc;
  }

  /**
   * Adds to Be Continued. Default value when omitted: false.
   *
   * @param tbc To Be Continued
   * @return this
   */
  public ReportDERControlRequest withTbc(@Nullable Boolean tbc) {
    setTbc(tbc);
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
  public ReportDERControlRequest withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  @Override
  public boolean validate() {
    return isValidCurve(curve)
        && isValidEnterService(enterService)
        && isValidFixedPFAbsorb(fixedPFAbsorb)
        && isValidFixedPFInject(fixedPFInject)
        && isValidFixedVar(fixedVar)
        && isValidFreqDroop(freqDroop)
        && isValidGradient(gradient)
        && isValidLimitMaxDischarge(limitMaxDischarge)
        && isValidRequestId(requestId)
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
    ReportDERControlRequest that = (ReportDERControlRequest) o;
    return Arrays.equals(curve, that.curve)
        && Arrays.equals(enterService, that.enterService)
        && Arrays.equals(fixedPFAbsorb, that.fixedPFAbsorb)
        && Arrays.equals(fixedPFInject, that.fixedPFInject)
        && Arrays.equals(fixedVar, that.fixedVar)
        && Arrays.equals(freqDroop, that.freqDroop)
        && Arrays.equals(gradient, that.gradient)
        && Arrays.equals(limitMaxDischarge, that.limitMaxDischarge)
        && Objects.equals(requestId, that.requestId)
        && Objects.equals(tbc, that.tbc)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        Arrays.hashCode(curve),
        Arrays.hashCode(enterService),
        Arrays.hashCode(fixedPFAbsorb),
        Arrays.hashCode(fixedPFInject),
        Arrays.hashCode(fixedVar),
        Arrays.hashCode(freqDroop),
        Arrays.hashCode(gradient),
        Arrays.hashCode(limitMaxDischarge),
        requestId,
        tbc,
        customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("curve", curve)
        .add("enterService", enterService)
        .add("fixedPFAbsorb", fixedPFAbsorb)
        .add("fixedPFInject", fixedPFInject)
        .add("fixedVar", fixedVar)
        .add("freqDroop", freqDroop)
        .add("gradient", gradient)
        .add("limitMaxDischarge", limitMaxDischarge)
        .add("requestId", requestId)
        .add("tbc", tbc)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
