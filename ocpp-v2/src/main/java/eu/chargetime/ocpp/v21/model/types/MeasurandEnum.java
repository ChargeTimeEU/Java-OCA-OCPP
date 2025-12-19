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

import com.google.gson.annotations.SerializedName;

/** Type of measurement. Default = "Energy.Active.Import.Register" */
public enum MeasurandEnum {
  @SerializedName("Current.Export")
  CurrentExport,
  @SerializedName("Current.Export.Offered")
  CurrentExportOffered,
  @SerializedName("Current.Export.Minimum")
  CurrentExportMinimum,
  @SerializedName("Current.Import")
  CurrentImport,
  @SerializedName("Current.Import.Offered")
  CurrentImportOffered,
  @SerializedName("Current.Import.Minimum")
  CurrentImportMinimum,
  @SerializedName("Current.Offered")
  CurrentOffered,
  @SerializedName("Display.PresentSOC")
  DisplayPresentSOC,
  @SerializedName("Display.MinimumSOC")
  DisplayMinimumSOC,
  @SerializedName("Display.TargetSOC")
  DisplayTargetSOC,
  @SerializedName("Display.MaximumSOC")
  DisplayMaximumSOC,
  @SerializedName("Display.RemainingTimeToMinimumSOC")
  DisplayRemainingTimeToMinimumSOC,
  @SerializedName("Display.RemainingTimeToTargetSOC")
  DisplayRemainingTimeToTargetSOC,
  @SerializedName("Display.RemainingTimeToMaximumSOC")
  DisplayRemainingTimeToMaximumSOC,
  @SerializedName("Display.ChargingComplete")
  DisplayChargingComplete,
  @SerializedName("Display.BatteryEnergyCapacity")
  DisplayBatteryEnergyCapacity,
  @SerializedName("Display.InletHot")
  DisplayInletHot,
  @SerializedName("Energy.Active.Export.Interval")
  EnergyActiveExportInterval,
  @SerializedName("Energy.Active.Export.Register")
  EnergyActiveExportRegister,
  @SerializedName("Energy.Active.Import.Interval")
  EnergyActiveImportInterval,
  @SerializedName("Energy.Active.Import.Register")
  EnergyActiveImportRegister,
  @SerializedName("Energy.Active.Import.CableLoss")
  EnergyActiveImportCableLoss,
  @SerializedName("Energy.Active.Import.LocalGeneration.Register")
  EnergyActiveImportLocalGenerationRegister,
  @SerializedName("Energy.Active.Net")
  EnergyActiveNet,
  @SerializedName("Energy.Active.Setpoint.Interval")
  EnergyActiveSetpointInterval,
  @SerializedName("Energy.Apparent.Export")
  EnergyApparentExport,
  @SerializedName("Energy.Apparent.Import")
  EnergyApparentImport,
  @SerializedName("Energy.Apparent.Net")
  EnergyApparentNet,
  @SerializedName("Energy.Reactive.Export.Interval")
  EnergyReactiveExportInterval,
  @SerializedName("Energy.Reactive.Export.Register")
  EnergyReactiveExportRegister,
  @SerializedName("Energy.Reactive.Import.Interval")
  EnergyReactiveImportInterval,
  @SerializedName("Energy.Reactive.Import.Register")
  EnergyReactiveImportRegister,
  @SerializedName("Energy.Reactive.Net")
  EnergyReactiveNet,
  @SerializedName("EnergyRequest.Target")
  EnergyRequestTarget,
  @SerializedName("EnergyRequest.Minimum")
  EnergyRequestMinimum,
  @SerializedName("EnergyRequest.Maximum")
  EnergyRequestMaximum,
  @SerializedName("EnergyRequest.Minimum.V2X")
  EnergyRequestMinimumV2X,
  @SerializedName("EnergyRequest.Maximum.V2X")
  EnergyRequestMaximumV2X,
  @SerializedName("EnergyRequest.Bulk")
  EnergyRequestBulk,
  Frequency,
  @SerializedName("Power.Active.Export")
  PowerActiveExport,
  @SerializedName("Power.Active.Import")
  PowerActiveImport,
  @SerializedName("Power.Active.Setpoint")
  PowerActiveSetpoint,
  @SerializedName("Power.Active.Residual")
  PowerActiveResidual,
  @SerializedName("Power.Export.Minimum")
  PowerExportMinimum,
  @SerializedName("Power.Export.Offered")
  PowerExportOffered,
  @SerializedName("Power.Factor")
  PowerFactor,
  @SerializedName("Power.Import.Offered")
  PowerImportOffered,
  @SerializedName("Power.Import.Minimum")
  PowerImportMinimum,
  @SerializedName("Power.Offered")
  PowerOffered,
  @SerializedName("Power.Reactive.Export")
  PowerReactiveExport,
  @SerializedName("Power.Reactive.Import")
  PowerReactiveImport,
  SoC,
  Voltage,
  @SerializedName("Voltage.Minimum")
  VoltageMinimum,
  @SerializedName("Voltage.Maximum")
  VoltageMaximum
}
