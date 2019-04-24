package eu.chargetime.ocpp.model.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.model.core.Location;
import eu.chargetime.ocpp.model.core.SampledValue;
import eu.chargetime.ocpp.model.core.ValueFormat;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/*
 * ChargeTime.eu - Java-OCA-OCPP
 *
 * MIT License
 *
 * Copyright (C) 2016-2018 Thomas Volden <tv@chargetime.eu>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
public class SampledValueTest {

  @Rule public ExpectedException thrownException = ExpectedException.none();

  private SampledValue sampledValue;

  @Before
  public void setUp() {
    sampledValue = new SampledValue();
  }

  @Test
  public void setValue_anyString_valueIsSet() {
    // Given
    String aString = "Some string";

    // When
    sampledValue.setValue(aString);

    // Then
    assertThat(sampledValue.getValue(), equalTo(aString));
  }

  @Test
  public void validate_valueIsSet_returnTrue() {
    // Given
    sampledValue.setValue("something");

    // When
    boolean isValid = sampledValue.validate();

    // Then
    assertThat(isValid, is(true));
  }

  @Test
  public void setContext_illegalValue_throwsPropertyConstraintException() {
    thrownException.expect(instanceOf(PropertyConstraintException.class));
    thrownException.expectMessage(
        equalTo(
            "Validation failed: [context is not properly defined]. Current Value: [some value]"));

    String illegalValue = "some value";

    sampledValue.setContext(illegalValue);
  }

  @Test
  public void setContext_interruptionBegin_contextIsSet() {
    // Given
    String readingContext = "Interruption.Begin";

    // When
    sampledValue.setContext(readingContext);

    // Then
    assertThat(sampledValue.getContext(), equalTo(readingContext));
  }

  @Test
  public void setContext_interruptionEnd_contextIsSet() {
    // Given
    String readingContext = "Interruption.End";

    // When
    sampledValue.setContext(readingContext);

    // Then
    assertThat(sampledValue.getContext(), equalTo(readingContext));
  }

  @Test
  public void setContext_other_contextIsSet() {
    // Given
    String readingContext = "Other";

    // When
    sampledValue.setContext(readingContext);

    // Then
    assertThat(sampledValue.getContext(), equalTo(readingContext));
  }

  @Test
  public void setContext_sampleClock_contextIsSet() {
    // Given
    String readingContext = "Sample.Clock";

    // When
    sampledValue.setContext(readingContext);

    // Then
    assertThat(sampledValue.getContext(), equalTo(readingContext));
  }

  @Test
  public void setContext_samplePeriodic_contextIsSet() {
    // Given
    String readingContext = "Sample.Periodic";

    // When
    sampledValue.setContext(readingContext);

    // Then
    assertThat(sampledValue.getContext(), equalTo(readingContext));
  }

  @Test
  public void setContext_transactionBegin_contextIsSet() {
    // Given
    String readingContext = "Transaction.Begin";

    // When
    sampledValue.setContext(readingContext);

    // Then
    assertThat(sampledValue.getContext(), equalTo(readingContext));
  }

  @Test
  public void setContext_transactionEnd_contextIsSet() {
    // Given
    String readingContext = "Transaction.End";

    // When
    sampledValue.setContext(readingContext);

    // Then
    assertThat(sampledValue.getContext(), equalTo(readingContext));
  }

  @Test
  public void setContext_trigger_contextIsSet() {
    // Given
    String readingContext = "Trigger";

    // When
    sampledValue.setContext(readingContext);

    // Then
    assertThat(sampledValue.getContext(), equalTo(readingContext));
  }

  @Test
  public void getContext_returnSamplePeriodic() {
    // Given
    String expected = "Sample.Periodic";

    // When
    String context = sampledValue.getContext();

    // Then
    assertThat(context, equalTo(expected));
  }

  @Test
  public void setFormat_valueFormat_formatIsSet() {
    // Given
    ValueFormat valueFormat = ValueFormat.Raw;

    // When
    sampledValue.setFormat(valueFormat);

    // Then
    assertThat(sampledValue.getFormat(), equalTo(valueFormat));
  }

  @Test
  public void getFormat_returnRaw() {
    // Given
    ValueFormat expected = ValueFormat.Raw;

    // When
    ValueFormat format = sampledValue.getFormat();

    // Then
    assertThat(format, equalTo(expected));
  }

  @Test
  public void setMeasurand_illegalValue_throwsPropertyConstraintException() {
    thrownException.expect(instanceOf(PropertyConstraintException.class));
    thrownException.expectMessage(
        equalTo(
            "Validation failed: [measurand value is not properly defined]. Current Value: [some measurand]"));

    String illegalValue = "some measurand";

    sampledValue.setMeasurand(illegalValue);
  }

  @Test
  public void setMeasurand_currentExport_measurandIsSet() {
    // Given
    String measurand = "Current.Export";

    // When
    sampledValue.setMeasurand(measurand);

    // Then
    assertThat(sampledValue.getMeasurand(), equalTo(measurand));
  }

  @Test
  public void setMeasurand_currentImport_measurandIsSet() {
    // Given
    String measurand = "Current.Import";

    // When
    sampledValue.setMeasurand(measurand);

    // Then
    assertThat(sampledValue.getMeasurand(), equalTo(measurand));
  }

  @Test
  public void setMeasurand_currentOffered_measurandIsSet() {
    // Given
    String measurand = "Current.Offered";

    // When
    sampledValue.setMeasurand(measurand);

    // Then
    assertThat(sampledValue.getMeasurand(), equalTo(measurand));
  }

  @Test
  public void setMeasurand_energyActiveExportRegister_measurandIsSet() {
    // Given
    String measurand = "Energy.Active.Export.Register";

    // When
    sampledValue.setMeasurand(measurand);

    // Then
    assertThat(sampledValue.getMeasurand(), equalTo(measurand));
  }

  @Test
  public void setMeasurand_energyActiveImportRegister_measurandIsSet() {
    // Given
    String measurand = "Energy.Active.Import.Register";

    // When
    sampledValue.setMeasurand(measurand);

    // Then
    assertThat(sampledValue.getMeasurand(), equalTo(measurand));
  }

  @Test
  public void setMeasurand_energyReactiveExportRegister_measurandIsSet() {
    // Given
    String measurand = "Energy.Reactive.Export.Register";

    // When
    sampledValue.setMeasurand(measurand);

    // Then
    assertThat(sampledValue.getMeasurand(), equalTo(measurand));
  }

  @Test
  public void setMeasurand_energyReactiveImportRegister_measurandIsSet() {
    // Given
    String measurand = "Energy.Reactive.Import.Register";

    // When
    sampledValue.setMeasurand(measurand);

    // Then
    assertThat(sampledValue.getMeasurand(), equalTo(measurand));
  }

  @Test
  public void setMeasurand_energyActiveExportInterval_measurandIsSet() {
    // Given
    String measurand = "Energy.Active.Export.Interval";

    // When
    sampledValue.setMeasurand(measurand);

    // Then
    assertThat(sampledValue.getMeasurand(), equalTo(measurand));
  }

  @Test
  public void setMeasurand_energyActiveImportInterval_measurandIsSet() {
    // Given
    String measurand = "Energy.Active.Import.Interval";

    // When
    sampledValue.setMeasurand(measurand);

    // Then
    assertThat(sampledValue.getMeasurand(), equalTo(measurand));
  }

  @Test
  public void setMeasurand_energyReactiveExportInterval_measurandIsSet() {
    // Given
    String measurand = "Energy.Reactive.Export.Interval";

    // When
    sampledValue.setMeasurand(measurand);

    // Then
    assertThat(sampledValue.getMeasurand(), equalTo(measurand));
  }

  @Test
  public void setMeasurand_energyReactiveImportInterval_measurandIsSet() {
    // Given
    String measurand = "Energy.Reactive.Import.Interval";

    // When
    sampledValue.setMeasurand(measurand);

    // Then
    assertThat(sampledValue.getMeasurand(), equalTo(measurand));
  }

  @Test
  public void setMeasurand_frequency_measurandIsSet() {
    // Given
    String measurand = "Frequency";

    // When
    sampledValue.setMeasurand(measurand);

    // Then
    assertThat(sampledValue.getMeasurand(), equalTo(measurand));
  }

  @Test
  public void setMeasurand_powerActiveExport_measurandIsSet() {
    // Given
    String measurand = "Power.Active.Export";

    // When
    sampledValue.setMeasurand(measurand);

    // Then
    assertThat(sampledValue.getMeasurand(), equalTo(measurand));
  }

  @Test
  public void setMeasurand_powerActiveImport_measurandIsSet() {
    // Given
    String measurand = "Power.Active.Import";

    // When
    sampledValue.setMeasurand(measurand);

    // Then
    assertThat(sampledValue.getMeasurand(), equalTo(measurand));
  }

  @Test
  public void setMeasurand_powerFactor_measurandIsSet() {
    // Given
    String measurand = "Power.Factor";

    // When
    sampledValue.setMeasurand(measurand);

    // Then
    assertThat(sampledValue.getMeasurand(), equalTo(measurand));
  }

  @Test
  public void setMeasurand_powerOffered_measurandIsSet() {
    // Given
    String measurand = "Power.Offered";

    // When
    sampledValue.setMeasurand(measurand);

    // Then
    assertThat(sampledValue.getMeasurand(), equalTo(measurand));
  }

  @Test
  public void setMeasurand_powerReactiveExport_measurandIsSet() {
    // Given
    String measurand = "Power.Reactive.Export";

    // When
    sampledValue.setMeasurand(measurand);

    // Then
    assertThat(sampledValue.getMeasurand(), equalTo(measurand));
  }

  @Test
  public void setMeasurand_powerReactiveImport_measurandIsSet() {
    // Given
    String measurand = "Power.Reactive.Import";

    // When
    sampledValue.setMeasurand(measurand);

    // Then
    assertThat(sampledValue.getMeasurand(), equalTo(measurand));
  }

  @Test
  public void setMeasurand_rpm_measurandIsSet() {
    // Given
    String measurand = "RPM";

    // When
    sampledValue.setMeasurand(measurand);

    // Then
    assertThat(sampledValue.getMeasurand(), equalTo(measurand));
  }

  @Test
  public void setMeasurand_soc_measurandIsSet() {
    // Given
    String measurand = "SoC";

    // When
    sampledValue.setMeasurand(measurand);

    // Then
    assertThat(sampledValue.getMeasurand(), equalTo(measurand));
  }

  @Test
  public void setMeasurand_temperature_measurandIsSet() {
    // Given
    String measurand = "Temperature";

    // When
    sampledValue.setMeasurand(measurand);

    // Then
    assertThat(sampledValue.getMeasurand(), equalTo(measurand));
  }

  @Test
  public void setMeasurand_voltage_measurandIsSet() {
    // Given
    String measurand = "Voltage";

    // When
    sampledValue.setMeasurand(measurand);

    // Then
    assertThat(sampledValue.getMeasurand(), equalTo(measurand));
  }

  @Test
  public void getMeasurand_returnEnergyActiveImportRegister() {
    // Given
    String expected = "Energy.Active.Import.Register";

    // When
    String measurand = sampledValue.getMeasurand();

    // Then
    assertThat(measurand, equalTo(expected));
  }

  @Test
  public void setPhase_illegalValue_throwsPropertyConstraintException() {
    thrownException.expect(instanceOf(PropertyConstraintException.class));
    thrownException.expectMessage(
        equalTo("Validation failed: [phase is not properly defined]. Current Value: [some phase]"));

    String illegalValue = "some phase";

    sampledValue.setPhase(illegalValue);
  }

  @Test
  public void setPhase_l1_phaseIsSet() {
    // Given
    String phase = "L1";

    // When
    sampledValue.setPhase(phase);

    // Then
    assertThat(sampledValue.getPhase(), equalTo(phase));
  }

  @Test
  public void setPhase_l2_phaseIsSet() {
    // Given
    String phase = "L2";

    // When
    sampledValue.setPhase(phase);

    // Then
    assertThat(sampledValue.getPhase(), equalTo(phase));
  }

  @Test
  public void setPhase_l3_phaseIsSet() {
    // Given
    String phase = "L3";

    // When
    sampledValue.setPhase(phase);

    // Then
    assertThat(sampledValue.getPhase(), equalTo(phase));
  }

  @Test
  public void setPhase_n_phaseIsSet() {
    // Given
    String phase = "N";

    // When
    sampledValue.setPhase(phase);

    // Then
    assertThat(sampledValue.getPhase(), equalTo(phase));
  }

  @Test
  public void setPhase_l1N_phaseIsSet() {
    // Given
    String phase = "L1-N";

    // When
    sampledValue.setPhase(phase);

    // Then
    assertThat(sampledValue.getPhase(), equalTo(phase));
  }

  @Test
  public void setPhase_l2N_phaseIsSet() {
    // Given
    String phase = "L2-N";

    // When
    sampledValue.setPhase(phase);

    // Then
    assertThat(sampledValue.getPhase(), equalTo(phase));
  }

  @Test
  public void setPhase_l3N_phaseIsSet() {
    // Given
    String phase = "L3-N";

    // When
    sampledValue.setPhase(phase);

    // Then
    assertThat(sampledValue.getPhase(), equalTo(phase));
  }

  @Test
  public void setPhase_l1L2_phaseIsSet() {
    // Given
    String phase = "L1-L2";

    // When
    sampledValue.setPhase(phase);

    // Then
    assertThat(sampledValue.getPhase(), equalTo(phase));
  }

  @Test
  public void setPhase_l2L3_phaseIsSet() {
    // Given
    String phase = "L2-L3";

    // When
    sampledValue.setPhase(phase);

    // Then
    assertThat(sampledValue.getPhase(), equalTo(phase));
  }

  @Test
  public void setPhase_l3L1_phaseIsSet() {
    // Given
    String phase = "L3-L1";

    // When
    sampledValue.setPhase(phase);

    // Then
    assertThat(sampledValue.getPhase(), equalTo(phase));
  }

  @Test
  public void setLocation_location_locationIsSet() {
    // Given
    Location location = Location.Outlet;

    // When
    sampledValue.setLocation(location);

    // Then
    assertThat(sampledValue.getLocation(), equalTo(location));
  }

  @Test
  public void getLocation_returnOutlet() {
    // Given
    Location expected = Location.Outlet;

    // When
    Location location = sampledValue.getLocation();

    // Then
    assertThat(location, equalTo(expected));
  }

  @Test
  public void setUnit_illegalValue_throwsPropertyConstraintException() {
    thrownException.expect(instanceOf(PropertyConstraintException.class));
    thrownException.expectMessage(
        equalTo("Validation failed: [unit is not properly defined]. Current Value: [Some unit]"));

    String illegalValue = "Some unit";

    sampledValue.setUnit(illegalValue);
  }

  @Test
  public void setUnit_wh_unitIsSet() {
    // Given
    String unitOfMeasure = "Wh";

    // When
    sampledValue.setUnit(unitOfMeasure);

    // Then
    assertThat(sampledValue.getUnit(), equalTo(unitOfMeasure));
  }

  @Test
  public void setUnit_kwh_unitIsSet() {
    // Given
    String unitOfMeasure = "kWh";

    // When
    sampledValue.setUnit(unitOfMeasure);

    // Then
    assertThat(sampledValue.getUnit(), equalTo(unitOfMeasure));
  }

  @Test
  public void setUnit_varh_unitIsSet() {
    // Given
    String unitOfMeasure = "varh";

    // When
    sampledValue.setUnit(unitOfMeasure);

    // Then
    assertThat(sampledValue.getUnit(), equalTo(unitOfMeasure));
  }

  @Test
  public void setUnit_kvarh_unitIsSet() {
    // Given
    String unitOfMeasure = "kvarh";

    // When
    sampledValue.setUnit(unitOfMeasure);

    // Then
    assertThat(sampledValue.getUnit(), equalTo(unitOfMeasure));
  }

  @Test
  public void setUnit_w_unitIsSet() {
    // Given
    String unitOfMeasure = "W";

    // When
    sampledValue.setUnit(unitOfMeasure);

    // Then
    assertThat(sampledValue.getUnit(), equalTo(unitOfMeasure));
  }

  @Test
  public void setUnit_kw_unitIsSet() {
    // Given
    String unitOfMeasure = "kW";

    // When
    sampledValue.setUnit(unitOfMeasure);

    // Then
    assertThat(sampledValue.getUnit(), equalTo(unitOfMeasure));
  }

  @Test
  public void setUnit_va_unitIsSet() {
    // Given
    String unitOfMeasure = "VA";

    // When
    sampledValue.setUnit(unitOfMeasure);

    // Then
    assertThat(sampledValue.getUnit(), equalTo(unitOfMeasure));
  }

  @Test
  public void setUnit_kva_unitIsSet() {
    // Given
    String unitOfMeasure = "kVA";

    // When
    sampledValue.setUnit(unitOfMeasure);

    // Then
    assertThat(sampledValue.getUnit(), equalTo(unitOfMeasure));
  }

  @Test
  public void setUnit_var_unitIsSet() {
    // Given
    String unitOfMeasure = "var";

    // When
    sampledValue.setUnit(unitOfMeasure);

    // Then
    assertThat(sampledValue.getUnit(), equalTo(unitOfMeasure));
  }

  @Test
  public void setUnit_kvar_unitIsSet() {
    // Given
    String unitOfMeasure = "kvar";

    // When
    sampledValue.setUnit(unitOfMeasure);

    // Then
    assertThat(sampledValue.getUnit(), equalTo(unitOfMeasure));
  }

  @Test
  public void setUnit_a_unitIsSet() {
    // Given
    String unitOfMeasure = "A";

    // When
    sampledValue.setUnit(unitOfMeasure);

    // Then
    assertThat(sampledValue.getUnit(), equalTo(unitOfMeasure));
  }

  @Test
  public void setUnit_v_unitIsSet() {
    // Given
    String unitOfMeasure = "V";

    // When
    sampledValue.setUnit(unitOfMeasure);

    // Then
    assertThat(sampledValue.getUnit(), equalTo(unitOfMeasure));
  }

  @Test
  public void setUnit_celsius_unitIsSet() {
    // Given
    String unitOfMeasure = "Celsius";

    // When
    sampledValue.setUnit(unitOfMeasure);

    // Then
    assertThat(sampledValue.getUnit(), equalTo(unitOfMeasure));
  }

  @Test
  public void setUnit_fahrenheit_unitIsSet() {
    // Given
    String unitOfMeasure = "Fahrenheit";

    // When
    sampledValue.setUnit(unitOfMeasure);

    // Then
    assertThat(sampledValue.getUnit(), equalTo(unitOfMeasure));
  }

  @Test
  public void setUnit_k_unitIsSet() {
    // Given
    String unitOfMeasure = "K";

    // When
    sampledValue.setUnit(unitOfMeasure);

    // Then
    assertThat(sampledValue.getUnit(), equalTo(unitOfMeasure));
  }

  @Test
  public void setUnit_percent_unitIsSet() {
    // Given
    String unitOfMeasure = "Percent";

    // When
    sampledValue.setUnit(unitOfMeasure);

    // Then
    assertThat(sampledValue.getUnit(), equalTo(unitOfMeasure));
  }

  @Test
  public void getUnit_returnEnergy() {
    // Given
    String expected = "Wh";

    // When
    String unit = sampledValue.getUnit();

    // Then
    assertThat(unit, equalTo(expected));
  }
}
