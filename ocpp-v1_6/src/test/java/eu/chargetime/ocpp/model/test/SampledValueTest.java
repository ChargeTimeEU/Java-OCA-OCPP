package eu.chargetime.ocpp.model.test;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.model.core.Location;
import eu.chargetime.ocpp.model.core.SampledValue;
import eu.chargetime.ocpp.model.core.ValueFormat;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

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
    SampledValue sampledValue;

    @Before
    public void setUp() throws Exception {
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
        // Given
        String illegalValue = "some value";

        try {
            // When
            sampledValue.setContext(illegalValue);

            Assert.fail("Expected PropertyConstraintException");
        } catch (PropertyConstraintException ex) {
            assertThat(ex.getFieldKey(), equalTo("context"));
            assertThat(ex.getFieldValue(), equalTo(illegalValue));
        }
    }

    @Test
    public void setContext_interruptionBegin_contextIsSet() throws Exception {
        // Given
        String readingContext = "Interruption.Begin";

        // When
        sampledValue.setContext(readingContext);

        // Then
        assertThat(sampledValue.getContext(), equalTo(readingContext));
    }

    @Test
    public void setContext_interruptionEnd_contextIsSet() throws Exception {
        // Given
        String readingContext = "Interruption.End";

        // When
        sampledValue.setContext(readingContext);

        // Then
        assertThat(sampledValue.getContext(), equalTo(readingContext));
    }

    @Test
    public void setContext_other_contextIsSet() throws Exception {
        // Given
        String readingContext = "Other";

        // When
        sampledValue.setContext(readingContext);

        // Then
        assertThat(sampledValue.getContext(), equalTo(readingContext));
    }

    @Test
    public void setContext_sampleClock_contextIsSet() throws Exception {
        // Given
        String readingContext = "Sample.Clock";

        // When
        sampledValue.setContext(readingContext);

        // Then
        assertThat(sampledValue.getContext(), equalTo(readingContext));
    }

    @Test
    public void setContext_samplePeriodic_contextIsSet() throws Exception {
        // Given
        String readingContext = "Sample.Periodic";

        // When
        sampledValue.setContext(readingContext);

        // Then
        assertThat(sampledValue.getContext(), equalTo(readingContext));
    }

    @Test
    public void setContext_transactionBegin_contextIsSet() throws Exception {
        // Given
        String readingContext = "Transaction.Begin";

        // When
        sampledValue.setContext(readingContext);

        // Then
        assertThat(sampledValue.getContext(), equalTo(readingContext));
    }

    @Test
    public void setContext_transactionEnd_contextIsSet() throws Exception {
        // Given
        String readingContext = "Transaction.End";

        // When
        sampledValue.setContext(readingContext);

        // Then
        assertThat(sampledValue.getContext(), equalTo(readingContext));
    }

    @Test
    public void setContext_trigger_contextIsSet() throws Exception {
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
    public void setFormat_valueFormat_formatIsSet() throws Exception {
        // Given
        ValueFormat valueFormat = ValueFormat.Raw;

        // When
        sampledValue.setFormat(valueFormat);

        // Then
        assertThat(sampledValue.objFormat(), equalTo(valueFormat));
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
        // Given
        String illegalValue = "some measurand";

        try {
            // When
            sampledValue.setMeasurand(illegalValue);

            Assert.fail("Expected PropertyConstraintException");
        } catch (PropertyConstraintException ex) {
            assertThat(ex.getFieldKey(), equalTo("measurand"));
            assertThat(ex.getFieldValue(), equalTo(illegalValue));
        }
    }

    @Test
    public void setMeasurand_currentExport_measurandIsSet() throws Exception {
        // Given
        String measurand = "Current.Export";

        // When
        sampledValue.setMeasurand(measurand);

        // Then
        assertThat(sampledValue.getMeasurand(), equalTo(measurand));
    }

    @Test
    public void setMeasurand_currentImport_measurandIsSet() throws Exception {
        // Given
        String measurand = "Current.Import";

        // When
        sampledValue.setMeasurand(measurand);

        // Then
        assertThat(sampledValue.getMeasurand(), equalTo(measurand));
    }

    @Test
    public void setMeasurand_currentOffered_measurandIsSet() throws Exception {
        // Given
        String measurand = "Current.Offered";

        // When
        sampledValue.setMeasurand(measurand);

        // Then
        assertThat(sampledValue.getMeasurand(), equalTo(measurand));
    }

    @Test
    public void setMeasurand_energyActiveExportRegister_measurandIsSet() throws Exception {
        // Given
        String measurand = "Energy.Active.Export.Register";

        // When
        sampledValue.setMeasurand(measurand);

        // Then
        assertThat(sampledValue.getMeasurand(), equalTo(measurand));
    }

    @Test
    public void setMeasurand_energyActiveImportRegister_measurandIsSet() throws Exception {
        // Given
        String measurand = "Energy.Active.Import.Register";

        // When
        sampledValue.setMeasurand(measurand);

        // Then
        assertThat(sampledValue.getMeasurand(), equalTo(measurand));
    }

    @Test
    public void setMeasurand_energyReactiveExportRegister_measurandIsSet() throws Exception {
        // Given
        String measurand = "Energy.Reactive.Export.Register";

        // When
        sampledValue.setMeasurand(measurand);

        // Then
        assertThat(sampledValue.getMeasurand(), equalTo(measurand));
    }

    @Test
    public void setMeasurand_energyReactiveImportRegister_measurandIsSet() throws Exception {
        // Given
        String measurand = "Energy.Reactive.Import.Register";

        // When
        sampledValue.setMeasurand(measurand);

        // Then
        assertThat(sampledValue.getMeasurand(), equalTo(measurand));
    }

    @Test
    public void setMeasurand_energyActiveExportInterval_measurandIsSet() throws Exception {
        // Given
        String measurand = "Energy.Active.Export.Interval";

        // When
        sampledValue.setMeasurand(measurand);

        // Then
        assertThat(sampledValue.getMeasurand(), equalTo(measurand));
    }

    @Test
    public void setMeasurand_energyActiveImportInterval_measurandIsSet() throws Exception {
        // Given
        String measurand = "Energy.Active.Import.Interval";

        // When
        sampledValue.setMeasurand(measurand);

        // Then
        assertThat(sampledValue.getMeasurand(), equalTo(measurand));
    }

    @Test
    public void setMeasurand_energyReactiveExportInterval_measurandIsSet() throws Exception {
        // Given
        String measurand = "Energy.Reactive.Export.Interval";

        // When
        sampledValue.setMeasurand(measurand);

        // Then
        assertThat(sampledValue.getMeasurand(), equalTo(measurand));
    }

    @Test
    public void setMeasurand_energyReactiveImportInterval_measurandIsSet() throws Exception {
        // Given
        String measurand = "Energy.Reactive.Import.Interval";

        // When
        sampledValue.setMeasurand(measurand);

        // Then
        assertThat(sampledValue.getMeasurand(), equalTo(measurand));
    }

    @Test
    public void setMeasurand_frequency_measurandIsSet() throws Exception {
        // Given
        String measurand = "Frequency";

        // When
        sampledValue.setMeasurand(measurand);

        // Then
        assertThat(sampledValue.getMeasurand(), equalTo(measurand));
    }

    @Test
    public void setMeasurand_powerActiveExport_measurandIsSet() throws Exception {
        // Given
        String measurand = "Power.Active.Export";

        // When
        sampledValue.setMeasurand(measurand);

        // Then
        assertThat(sampledValue.getMeasurand(), equalTo(measurand));
    }

    @Test
    public void setMeasurand_powerActiveImport_measurandIsSet() throws Exception {
        // Given
        String measurand = "Power.Active.Import";

        // When
        sampledValue.setMeasurand(measurand);

        // Then
        assertThat(sampledValue.getMeasurand(), equalTo(measurand));
    }

    @Test
    public void setMeasurand_powerFactor_measurandIsSet() throws Exception {
        // Given
        String measurand = "Power.Factor";

        // When
        sampledValue.setMeasurand(measurand);

        // Then
        assertThat(sampledValue.getMeasurand(), equalTo(measurand));
    }

    @Test
    public void setMeasurand_powerOffered_measurandIsSet() throws Exception {
        // Given
        String measurand = "Power.Offered";

        // When
        sampledValue.setMeasurand(measurand);

        // Then
        assertThat(sampledValue.getMeasurand(), equalTo(measurand));
    }

    @Test
    public void setMeasurand_powerReactiveExport_measurandIsSet() throws Exception {
        // Given
        String measurand = "Power.Reactive.Export";

        // When
        sampledValue.setMeasurand(measurand);

        // Then
        assertThat(sampledValue.getMeasurand(), equalTo(measurand));
    }

    @Test
    public void setMeasurand_powerReactiveImport_measurandIsSet() throws Exception {
        // Given
        String measurand = "Power.Reactive.Import";

        // When
        sampledValue.setMeasurand(measurand);

        // Then
        assertThat(sampledValue.getMeasurand(), equalTo(measurand));
    }

    @Test
    public void setMeasurand_rpm_measurandIsSet() throws Exception {
        // Given
        String measurand = "RPM";

        // When
        sampledValue.setMeasurand(measurand);

        // Then
        assertThat(sampledValue.getMeasurand(), equalTo(measurand));
    }

    @Test
    public void setMeasurand_soc_measurandIsSet() throws Exception {
        // Given
        String measurand = "SoC";

        // When
        sampledValue.setMeasurand(measurand);

        // Then
        assertThat(sampledValue.getMeasurand(), equalTo(measurand));
    }

    @Test
    public void setMeasurand_temperature_measurandIsSet() throws Exception {
        // Given
        String measurand = "Temperature";

        // When
        sampledValue.setMeasurand(measurand);

        // Then
        assertThat(sampledValue.getMeasurand(), equalTo(measurand));
    }

    @Test
    public void setMeasurand_voltage_measurandIsSet() throws Exception {
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
        // Given
        String illegalValue = "some phase";

        try {
            // When
            sampledValue.setPhase(illegalValue);

            Assert.fail("Expected PropertyConstraintException");
        } catch (PropertyConstraintException ex) {
            assertThat(ex.getFieldKey(), equalTo("phase"));
            assertThat(ex.getFieldValue(), equalTo(illegalValue));
        }
    }

    @Test
    public void setPhase_l1_phaseIsSet() throws Exception {
        // Given
        String phase = "L1";

        // When
        sampledValue.setPhase(phase);

        // Then
        assertThat(sampledValue.getPhase(), equalTo(phase));
    }

    @Test
    public void setPhase_l2_phaseIsSet() throws Exception {
        // Given
        String phase = "L2";

        // When
        sampledValue.setPhase(phase);

        // Then
        assertThat(sampledValue.getPhase(), equalTo(phase));
    }

    @Test
    public void setPhase_l3_phaseIsSet() throws Exception {
        // Given
        String phase = "L3";

        // When
        sampledValue.setPhase(phase);

        // Then
        assertThat(sampledValue.getPhase(), equalTo(phase));
    }

    @Test
    public void setPhase_n_phaseIsSet() throws Exception {
        // Given
        String phase = "N";

        // When
        sampledValue.setPhase(phase);

        // Then
        assertThat(sampledValue.getPhase(), equalTo(phase));
    }

    @Test
    public void setPhase_l1N_phaseIsSet() throws Exception {
        // Given
        String phase = "L1-N";

        // When
        sampledValue.setPhase(phase);

        // Then
        assertThat(sampledValue.getPhase(), equalTo(phase));
    }

    @Test
    public void setPhase_l2N_phaseIsSet() throws Exception {
        // Given
        String phase = "L2-N";

        // When
        sampledValue.setPhase(phase);

        // Then
        assertThat(sampledValue.getPhase(), equalTo(phase));
    }

    @Test
    public void setPhase_l3N_phaseIsSet() throws Exception {
        // Given
        String phase = "L3-N";

        // When
        sampledValue.setPhase(phase);

        // Then
        assertThat(sampledValue.getPhase(), equalTo(phase));
    }

    @Test
    public void setPhase_l1L2_phaseIsSet() throws Exception {
        // Given
        String phase = "L1-L2";

        // When
        sampledValue.setPhase(phase);

        // Then
        assertThat(sampledValue.getPhase(), equalTo(phase));
    }

    @Test
    public void setPhase_l2L3_phaseIsSet() throws Exception {
        // Given
        String phase = "L2-L3";

        // When
        sampledValue.setPhase(phase);

        // Then
        assertThat(sampledValue.getPhase(), equalTo(phase));
    }

    @Test
    public void setPhase_l3L1_phaseIsSet() throws Exception {
        // Given
        String phase = "L3-L1";

        // When
        sampledValue.setPhase(phase);

        // Then
        assertThat(sampledValue.getPhase(), equalTo(phase));
    }

    @Test
    public void setLocation_location_locationIsSet() throws Exception {
        // Given
        Location location = Location.Outlet;

        // When
        sampledValue.setLocation(location);

        // Then
        assertThat(sampledValue.objLocation(), equalTo(location));
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
        // Given
        String illegalValue = "Some unit";

        try {
            // When
            sampledValue.setUnit(illegalValue);

            Assert.fail("Expected PropertyConstraintException");
        } catch (PropertyConstraintException ex) {
            assertThat(ex.getFieldKey(), equalTo("unit"));
            assertThat(ex.getFieldValue(), equalTo(illegalValue));
        }
    }

    @Test
    public void setUnit_wh_unitIsSet() throws Exception {
        // Given
        String unitOfMeasure = "Wh";

        // When
        sampledValue.setUnit(unitOfMeasure);

        // Then
        assertThat(sampledValue.getUnit(), equalTo(unitOfMeasure));
    }

    @Test
    public void setUnit_kwh_unitIsSet() throws Exception {
        // Given
        String unitOfMeasure = "kWh";

        // When
        sampledValue.setUnit(unitOfMeasure);

        // Then
        assertThat(sampledValue.getUnit(), equalTo(unitOfMeasure));
    }

    @Test
    public void setUnit_varh_unitIsSet() throws Exception {
        // Given
        String unitOfMeasure = "varh";

        // When
        sampledValue.setUnit(unitOfMeasure);

        // Then
        assertThat(sampledValue.getUnit(), equalTo(unitOfMeasure));
    }

    @Test
    public void setUnit_kvarh_unitIsSet() throws Exception {
        // Given
        String unitOfMeasure = "kvarh";

        // When
        sampledValue.setUnit(unitOfMeasure);

        // Then
        assertThat(sampledValue.getUnit(), equalTo(unitOfMeasure));
    }

    @Test
    public void setUnit_w_unitIsSet() throws Exception {
        // Given
        String unitOfMeasure = "W";

        // When
        sampledValue.setUnit(unitOfMeasure);

        // Then
        assertThat(sampledValue.getUnit(), equalTo(unitOfMeasure));
    }

    @Test
    public void setUnit_kw_unitIsSet() throws Exception {
        // Given
        String unitOfMeasure = "kW";

        // When
        sampledValue.setUnit(unitOfMeasure);

        // Then
        assertThat(sampledValue.getUnit(), equalTo(unitOfMeasure));
    }

    @Test
    public void setUnit_va_unitIsSet() throws Exception {
        // Given
        String unitOfMeasure = "VA";

        // When
        sampledValue.setUnit(unitOfMeasure);

        // Then
        assertThat(sampledValue.getUnit(), equalTo(unitOfMeasure));
    }

    @Test
    public void setUnit_kva_unitIsSet() throws Exception {
        // Given
        String unitOfMeasure = "kVA";

        // When
        sampledValue.setUnit(unitOfMeasure);

        // Then
        assertThat(sampledValue.getUnit(), equalTo(unitOfMeasure));
    }

    @Test
    public void setUnit_var_unitIsSet() throws Exception {
        // Given
        String unitOfMeasure = "var";

        // When
        sampledValue.setUnit(unitOfMeasure);

        // Then
        assertThat(sampledValue.getUnit(), equalTo(unitOfMeasure));
    }

    @Test
    public void setUnit_kvar_unitIsSet() throws Exception {
        // Given
        String unitOfMeasure = "kvar";

        // When
        sampledValue.setUnit(unitOfMeasure);

        // Then
        assertThat(sampledValue.getUnit(), equalTo(unitOfMeasure));
    }

    @Test
    public void setUnit_a_unitIsSet() throws Exception {
        // Given
        String unitOfMeasure = "A";

        // When
        sampledValue.setUnit(unitOfMeasure);

        // Then
        assertThat(sampledValue.getUnit(), equalTo(unitOfMeasure));
    }

    @Test
    public void setUnit_v_unitIsSet() throws Exception {
        // Given
        String unitOfMeasure = "V";

        // When
        sampledValue.setUnit(unitOfMeasure);

        // Then
        assertThat(sampledValue.getUnit(), equalTo(unitOfMeasure));
    }

    @Test
    public void setUnit_celsius_unitIsSet() throws Exception {
        // Given
        String unitOfMeasure = "Celsius";

        // When
        sampledValue.setUnit(unitOfMeasure);

        // Then
        assertThat(sampledValue.getUnit(), equalTo(unitOfMeasure));
    }

    @Test
    public void setUnit_fahrenheit_unitIsSet() throws Exception {
        // Given
        String unitOfMeasure = "Fahrenheit";

        // When
        sampledValue.setUnit(unitOfMeasure);

        // Then
        assertThat(sampledValue.getUnit(), equalTo(unitOfMeasure));
    }

    @Test
    public void setUnit_k_unitIsSet() throws Exception {
        // Given
        String unitOfMeasure = "K";

        // When
        sampledValue.setUnit(unitOfMeasure);

        // Then
        assertThat(sampledValue.getUnit(), equalTo(unitOfMeasure));
    }

    @Test
    public void setUnit_percent_unitIsSet() throws Exception {
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