package eu.chargetime.ocpp.model;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.utilities.ModelUtil;

/**
 * ChargeTime.eu - Java-OCA-OCPP
 *
 * MIT License
 *
 * Copyright (C) 2016 Thomas Volden <tv@chargetime.eu>
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
public class SampledValue implements Validatable {
    private String value;
    private String context;
    private ValueFormat format;
    private String measurand;
    private String phase;
    private Location location;
    private String unit;

    public SampledValue() {
        try {
            setContext("Sample.Periodic");
            setFormat(ValueFormat.Raw);
            setMeasurand("Energy.Active.Import.Register");
            setLocation(Location.Outlet);
            setUnit("Wh");
        } catch (PropertyConstraintException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public boolean validate() {
        return this.value != null;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setContext(String context) throws PropertyConstraintException {
        if (!isValidContext(context))
            throw new PropertyConstraintException("context", context);

        this.context = context;
    }

    private boolean isValidContext(String context) {
        String[] readingContext = {"Interruption.Begin", "Interruption.End", "Other", "Sample.Clock", "Sample.Periodic", "Transaction.Begin", "Transaction.End", "Trigger"};
        return ModelUtil.isAmong(context, readingContext);
    }

    public String getContext() {
        return context;
    }

    public void setFormat(ValueFormat format) {
        this.format = format;
    }

    public String getFormat() {
        return format.toString();
    }

    public ValueFormat objFormat() {
        return format;
    }

    public void setMeasurand(String measurand) throws PropertyConstraintException {
        if (!isValidMeasurand(measurand))
            throw new PropertyConstraintException("measurand", measurand);

        this.measurand = measurand;
    }

    private boolean isValidMeasurand(String measurand) {
        String[] measurandValues = {"Current.Export", "Current.Import", "Current.Offered", "Energy.Active.Export.Register", "Energy.Active.Import.Register", "Energy.Reactive.Export.Register", "Energy.Reactive.Import.Register", "Energy.Active.Export.Interval", "Energy.Active.Import.Interval", "Energy.Reactive.Export.Interval", "Energy.Reactive.Import.Interval", "Frequency", "Power.Active.Export", "Power.Active.Import", "Power.Factor", "Power.Offered", "Power.Reactive.Export", "Power.Reactive.Import", "RPM", "SoC", "Temperature", "Voltage"};
        return ModelUtil.isAmong(measurand, measurandValues);
    }

    public String getMeasurand() {
        return measurand;
    }

    public void setPhase(String phase) throws PropertyConstraintException {
        if (!isValidPhase(phase))
            throw new PropertyConstraintException("phase", phase);

        this.phase = phase;
    }

    private boolean isValidPhase(String phase) {
        return ModelUtil.isAmong(phase, "L1", "L2", "L3", "N", "L1-N", "L2-N", "L3-N", "L1-L2", "L2-L3", "L3-L1");
    }

    public String getPhase() {
        return phase;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getLocation() {
        return location.toString();
    }

    public Location objLocation() {
        return location;
    }

    public void setUnit(String unit) throws PropertyConstraintException {
        if (!isValidUnit(unit))
            throw new PropertyConstraintException("unit", unit);
        this.unit = unit;
    }

    private boolean isValidUnit(String unit) {
        String[] unitOfMeasure = {"Wh", "kWh", "varh", "kvarh", "W", "kW", "VA", "kVA", "var", "kvar", "A", "V", "Celsius", "Fahrenheit", "K", "Percent"};
        return ModelUtil.isAmong(unit, unitOfMeasure);
    }

    public String getUnit() {
        return unit;
    }
}
