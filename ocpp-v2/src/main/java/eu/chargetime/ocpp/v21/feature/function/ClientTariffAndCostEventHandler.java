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

package eu.chargetime.ocpp.v21.feature.function;

import eu.chargetime.ocpp.v21.model.messages.*;

/** Call back handler for client events of the TariffAndCost functional block. */
public interface ClientTariffAndCostEventHandler {
  /**
   * Handle a {@link ChangeTransactionTariffRequest} and return a {@link
   * ChangeTransactionTariffResponse}.
   *
   * @param request incoming {@link ChangeTransactionTariffRequest} to handle.
   * @return outgoing {@link ChangeTransactionTariffResponse} to reply with.
   */
  ChangeTransactionTariffResponse handleChangeTransactionTariffRequest(
      ChangeTransactionTariffRequest request);

  /**
   * Handle a {@link ClearTariffsRequest} and return a {@link ClearTariffsResponse}.
   *
   * @param request incoming {@link ClearTariffsRequest} to handle.
   * @return outgoing {@link ClearTariffsResponse} to reply with.
   */
  ClearTariffsResponse handleClearTariffsRequest(ClearTariffsRequest request);

  /**
   * Handle a {@link CostUpdatedRequest} and return a {@link CostUpdatedResponse}.
   *
   * @param request incoming {@link CostUpdatedRequest} to handle.
   * @return outgoing {@link CostUpdatedResponse} to reply with.
   */
  CostUpdatedResponse handleCostUpdatedRequest(CostUpdatedRequest request);

  /**
   * Handle a {@link GetTariffsRequest} and return a {@link GetTariffsResponse}.
   *
   * @param request incoming {@link GetTariffsRequest} to handle.
   * @return outgoing {@link GetTariffsResponse} to reply with.
   */
  GetTariffsResponse handleGetTariffsRequest(GetTariffsRequest request);

  /**
   * Handle a {@link SetDefaultTariffRequest} and return a {@link SetDefaultTariffResponse}.
   *
   * @param request incoming {@link SetDefaultTariffRequest} to handle.
   * @return outgoing {@link SetDefaultTariffResponse} to reply with.
   */
  SetDefaultTariffResponse handleSetDefaultTariffRequest(SetDefaultTariffRequest request);
}
