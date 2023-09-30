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

import com.google.gson.annotations.SerializedName;

/**
 * Sampled Value. Measurand. Measurand Code
 *
 * <p>Type of measurement. Default = "Energy.Active.Import.Register"
 */
public enum MeasurandEnum {
  @SerializedName("Current.Export")
  CurrentExport,
  @SerializedName("Current.Import")
  CurrentImport,
  @SerializedName("Current.Offered")
  CurrentOffered,
  @SerializedName("Energy.Active.Export.Register")
  EnergyActiveExportRegister,
  @SerializedName("Energy.Active.Import.Register")
  EnergyActiveImportRegister,
  @SerializedName("Energy.Reactive.Export.Register")
  EnergyReactiveExportRegister,
  @SerializedName("Energy.Reactive.Import.Register")
  EnergyReactiveImportRegister,
  @SerializedName("Energy.Active.Export.Interval")
  EnergyActiveExportInterval,
  @SerializedName("Energy.Active.Import.Interval")
  EnergyActiveImportInterval,
  @SerializedName("Energy.Active.Net")
  EnergyActiveNet,
  @SerializedName("Energy.Reactive.Export.Interval")
  EnergyReactiveExportInterval,
  @SerializedName("Energy.Reactive.Import.Interval")
  EnergyReactiveImportInterval,
  @SerializedName("Energy.Reactive.Net")
  EnergyReactiveNet,
  @SerializedName("Energy.Apparent.Net")
  EnergyApparentNet,
  @SerializedName("Energy.Apparent.Import")
  EnergyApparentImport,
  @SerializedName("Energy.Apparent.Export")
  EnergyApparentExport,
  Frequency,
  @SerializedName("Power.Active.Export")
  PowerActiveExport,
  @SerializedName("Power.Active.Import")
  PowerActiveImport,
  @SerializedName("Power.Factor")
  PowerFactor,
  @SerializedName("Power.Offered")
  PowerOffered,
  @SerializedName("Power.Reactive.Export")
  PowerReactiveExport,
  @SerializedName("Power.Reactive.Import")
  PowerReactiveImport,
  SoC,
  Voltage
}
