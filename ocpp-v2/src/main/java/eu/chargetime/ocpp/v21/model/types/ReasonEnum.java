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

/**
 * The stoppedReason is the reason/event that initiated the process of stopping the transaction. It
 * will normally be the user stopping authorization via card (Local or MasterPass) or app (Remote),
 * but it can also be CSMS revoking authorization (DeAuthorized), or disconnecting the EV when
 * TxStopPoint = EVConnected (EVDisconnected). Most other reasons are related to technical faults or
 * energy limitations.
 *
 * <p>MAY only be omitted when stoppedReason is "Local"
 */
public enum ReasonEnum {
  DeAuthorized,
  EmergencyStop,
  EnergyLimitReached,
  EVDisconnected,
  GroundFault,
  ImmediateReset,
  MasterPass,
  Local,
  LocalOutOfCredit,
  Other,
  OvercurrentFault,
  PowerLoss,
  PowerQuality,
  Reboot,
  Remote,
  SOCLimitReached,
  StoppedByEV,
  TimeLimitReached,
  Timeout,
  ReqEnergyTransferRejected
}
