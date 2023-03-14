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

package eu.chargetime.ocpp.v201.feature.function;

import eu.chargetime.ocpp.v201.model.messages.*;

/** Call back handler for client events of the SmartCharging functional block. */
public interface ClientSmartChargingEventHandler {
  /**
   * Handle a {@link ClearChargingProfileRequest} and return a {@link ClearChargingProfileResponse}.
   *
   * @param request incoming {@link ClearChargingProfileRequest} to handle.
   * @return outgoing {@link ClearChargingProfileResponse} to reply with.
   */
  ClearChargingProfileResponse handleClearChargingProfileRequest(
      ClearChargingProfileRequest request);

  /**
   * Handle a {@link GetChargingProfilesRequest} and return a {@link GetChargingProfilesResponse}.
   *
   * @param request incoming {@link GetChargingProfilesRequest} to handle.
   * @return outgoing {@link GetChargingProfilesResponse} to reply with.
   */
  GetChargingProfilesResponse handleGetChargingProfilesRequest(GetChargingProfilesRequest request);

  /**
   * Handle a {@link GetCompositeScheduleRequest} and return a {@link GetCompositeScheduleResponse}.
   *
   * @param request incoming {@link GetCompositeScheduleRequest} to handle.
   * @return outgoing {@link GetCompositeScheduleResponse} to reply with.
   */
  GetCompositeScheduleResponse handleGetCompositeScheduleRequest(
      GetCompositeScheduleRequest request);

  /**
   * Handle a {@link SetChargingProfileRequest} and return a {@link SetChargingProfileResponse}.
   *
   * @param request incoming {@link SetChargingProfileRequest} to handle.
   * @return outgoing {@link SetChargingProfileResponse} to reply with.
   */
  SetChargingProfileResponse handleSetChargingProfileRequest(SetChargingProfileRequest request);
}
