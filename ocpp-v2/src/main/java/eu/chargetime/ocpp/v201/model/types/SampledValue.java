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
 * Sampled Value
 *
 * <p>Single sampled value in MeterValues. Each value can be accompanied by optional fields.
 *
 * <p>To save on mobile data usage, default values of all of the optional fields are such that. The
 * value without any additional fields will be interpreted, as a register reading of active import
 * energy in Wh (Watt-hour) units.
 */
public final class SampledValue {
  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Sampled Value. Value. Measure
   *
   * <p>The measured value.
   */
  private Double value;

  /**
   * Sampled Value. Context. Reading Context Code
   *
   * <p>Type of detail value: start, end or sample. Default = "Sample.Periodic"
   */
  @Nullable private ReadingContextEnum context;

  /**
   * Sampled Value. Measurand. Measurand Code
   *
   * <p>Type of measurement. Default = "Energy.Active.Import.Register"
   */
  @Nullable private MeasurandEnum measurand;

  /**
   * Sampled Value. Phase. Phase Code
   *
   * <p>How the measured value is to be interpreted. For instance between L1 and neutral (L1-N)
   * Please note that not all values of phase are applicable to all Measurands. When phase is
   * absent, the measured value is interpreted as an overall value.
   */
  @Nullable private PhaseEnum phase;

  /**
   * Sampled Value. Location. Location Code
   *
   * <p>Where the measured value has been sampled. Default = "Outlet"
   */
  @Nullable private LocationEnum location;

  /** A signed version of the meter value. */
  @Nullable private SignedMeterValue signedMeterValue;

  /** A UnitOfMeasure with a multiplier */
  @Nullable private UnitOfMeasure unitOfMeasure;

  /**
   * Constructor for the SampledValue class
   *
   * @param value The measured value.
   */
  public SampledValue(Double value) {
    setValue(value);
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
  public SampledValue withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  /**
   * Gets the measured value.
   *
   * @return The measured value
   */
  public Double getValue() {
    return value;
  }

  /**
   * Sets the measured value.
   *
   * @param value The measured value
   */
  public void setValue(Double value) {
    if (!isValidValue(value)) {
      throw new PropertyConstraintException(value, "value is invalid");
    }
    this.value = value;
  }

  /**
   * Returns whether the given value is valid
   *
   * @param value the value to check the validity of
   * @return {@code true} if value is valid, {@code false} if not
   */
  private boolean isValidValue(Double value) {
    return value != null;
  }

  /**
   * Gets type of detail value: start, end or sample. Default = "Sample.Periodic"
   *
   * @return Type of detail value: start, end or sample
   */
  public ReadingContextEnum getContext() {
    return context != null ? context : ReadingContextEnum.SamplePeriodic;
  }

  /**
   * Sets type of detail value: start, end or sample. Default = "Sample.Periodic"
   *
   * @param context Type of detail value: start, end or sample
   */
  public void setContext(@Nullable ReadingContextEnum context) {
    this.context = context;
  }

  /**
   * Adds type of detail value: start, end or sample. Default = "Sample.Periodic"
   *
   * @param context Type of detail value: start, end or sample
   * @return this
   */
  public SampledValue withContext(@Nullable ReadingContextEnum context) {
    setContext(context);
    return this;
  }

  /**
   * Gets type of measurement. Default = "Energy.Active.Import.Register"
   *
   * @return Type of measurement
   */
  public MeasurandEnum getMeasurand() {
    return measurand != null ? measurand : MeasurandEnum.EnergyActiveImportRegister;
  }

  /**
   * Sets type of measurement. Default = "Energy.Active.Import.Register"
   *
   * @param measurand Type of measurement
   */
  public void setMeasurand(@Nullable MeasurandEnum measurand) {
    this.measurand = measurand;
  }

  /**
   * Adds type of measurement. Default = "Energy.Active.Import.Register"
   *
   * @param measurand Type of measurement
   * @return this
   */
  public SampledValue withMeasurand(@Nullable MeasurandEnum measurand) {
    setMeasurand(measurand);
    return this;
  }

  /**
   * Gets how the measured value is to be interpreted. For instance between L1 and neutral (L1-N)
   * Please note that not all values of phase are applicable to all Measurands. When phase is
   * absent, the measured value is interpreted as an overall value.
   *
   * @return How the measured value is to be interpreted
   */
  @Nullable
  public PhaseEnum getPhase() {
    return phase;
  }

  /**
   * Sets how the measured value is to be interpreted. For instance between L1 and neutral (L1-N)
   * Please note that not all values of phase are applicable to all Measurands. When phase is
   * absent, the measured value is interpreted as an overall value.
   *
   * @param phase How the measured value is to be interpreted
   */
  public void setPhase(@Nullable PhaseEnum phase) {
    this.phase = phase;
  }

  /**
   * Adds how the measured value is to be interpreted. For instance between L1 and neutral (L1-N)
   * Please note that not all values of phase are applicable to all Measurands. When phase is
   * absent, the measured value is interpreted as an overall value.
   *
   * @param phase How the measured value is to be interpreted
   * @return this
   */
  public SampledValue withPhase(@Nullable PhaseEnum phase) {
    setPhase(phase);
    return this;
  }

  /**
   * Gets where the measured value has been sampled. Default = "Outlet"
   *
   * @return Where the measured value has been sampled
   */
  public LocationEnum getLocation() {
    return location != null ? location : LocationEnum.Outlet;
  }

  /**
   * Sets where the measured value has been sampled. Default = "Outlet"
   *
   * @param location Where the measured value has been sampled
   */
  public void setLocation(@Nullable LocationEnum location) {
    this.location = location;
  }

  /**
   * Adds where the measured value has been sampled. Default = "Outlet"
   *
   * @param location Where the measured value has been sampled
   * @return this
   */
  public SampledValue withLocation(@Nullable LocationEnum location) {
    setLocation(location);
    return this;
  }

  /**
   * Gets a signed version of the meter value.
   *
   * @return A signed version of the meter value
   */
  @Nullable
  public SignedMeterValue getSignedMeterValue() {
    return signedMeterValue;
  }

  /**
   * Sets a signed version of the meter value.
   *
   * @param signedMeterValue A signed version of the meter value
   */
  public void setSignedMeterValue(@Nullable SignedMeterValue signedMeterValue) {
    if (!isValidSignedMeterValue(signedMeterValue)) {
      throw new PropertyConstraintException(signedMeterValue, "signedMeterValue is invalid");
    }
    this.signedMeterValue = signedMeterValue;
  }

  /**
   * Returns whether the given signedMeterValue is valid
   *
   * @param signedMeterValue the signedMeterValue to check the validity of
   * @return {@code true} if signedMeterValue is valid, {@code false} if not
   */
  private boolean isValidSignedMeterValue(@Nullable SignedMeterValue signedMeterValue) {
    return signedMeterValue == null || signedMeterValue.validate();
  }

  /**
   * Adds a signed version of the meter value.
   *
   * @param signedMeterValue A signed version of the meter value
   * @return this
   */
  public SampledValue withSignedMeterValue(@Nullable SignedMeterValue signedMeterValue) {
    setSignedMeterValue(signedMeterValue);
    return this;
  }

  /**
   * Gets a UnitOfMeasure with a multiplier
   *
   * @return A UnitOfMeasure with a multiplier
   */
  @Nullable
  public UnitOfMeasure getUnitOfMeasure() {
    return unitOfMeasure;
  }

  /**
   * Sets a UnitOfMeasure with a multiplier
   *
   * @param unitOfMeasure A UnitOfMeasure with a multiplier
   */
  public void setUnitOfMeasure(@Nullable UnitOfMeasure unitOfMeasure) {
    if (!isValidUnitOfMeasure(unitOfMeasure)) {
      throw new PropertyConstraintException(unitOfMeasure, "unitOfMeasure is invalid");
    }
    this.unitOfMeasure = unitOfMeasure;
  }

  /**
   * Returns whether the given unitOfMeasure is valid
   *
   * @param unitOfMeasure the unitOfMeasure to check the validity of
   * @return {@code true} if unitOfMeasure is valid, {@code false} if not
   */
  private boolean isValidUnitOfMeasure(@Nullable UnitOfMeasure unitOfMeasure) {
    return unitOfMeasure == null || unitOfMeasure.validate();
  }

  /**
   * Adds a UnitOfMeasure with a multiplier
   *
   * @param unitOfMeasure A UnitOfMeasure with a multiplier
   * @return this
   */
  public SampledValue withUnitOfMeasure(@Nullable UnitOfMeasure unitOfMeasure) {
    setUnitOfMeasure(unitOfMeasure);
    return this;
  }

  public boolean validate() {
    return isValidCustomData(customData)
        && isValidValue(value)
        && isValidSignedMeterValue(signedMeterValue)
        && isValidUnitOfMeasure(unitOfMeasure);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SampledValue that = (SampledValue) o;
    return Objects.equals(customData, that.customData)
        && Objects.equals(value, that.value)
        && Objects.equals(context, that.context)
        && Objects.equals(measurand, that.measurand)
        && Objects.equals(phase, that.phase)
        && Objects.equals(location, that.location)
        && Objects.equals(signedMeterValue, that.signedMeterValue)
        && Objects.equals(unitOfMeasure, that.unitOfMeasure);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        customData, value, context, measurand, phase, location, signedMeterValue, unitOfMeasure);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("value", value)
        .add("context", context)
        .add("measurand", measurand)
        .add("phase", phase)
        .add("location", location)
        .add("signedMeterValue", signedMeterValue)
        .add("unitOfMeasure", unitOfMeasure)
        .add("isValid", validate())
        .toString();
  }
}
