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

import eu.chargetime.ocpp.feature.FunctionFeature;
import eu.chargetime.ocpp.feature.function.Function;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.v21.feature.*;
import eu.chargetime.ocpp.v21.model.messages.*;
import eu.chargetime.ocpp.v21.model.types.*;
import java.util.ArrayList;
import java.util.UUID;

/** Class with server request creators and handlers for the TariffAndCost functional block. */
public class ServerTariffAndCostFunction implements Function {

  private final ArrayList<FunctionFeature> features;

  public ServerTariffAndCostFunction() {
    features = new ArrayList<>();
    features.add(new ChangeTransactionTariffFeature(null));
    features.add(new ClearTariffsFeature(null));
    features.add(new CostUpdatedFeature(null));
    features.add(new GetTariffsFeature(null));
    features.add(new SetDefaultTariffFeature(null));
  }

  @Override
  public FunctionFeature[] getFeatureList() {
    return features.toArray(new FunctionFeature[0]);
  }

  @Override
  public Confirmation handleRequest(UUID sessionIndex, Request request) {
    return null;
  }

  /**
   * Create a server {@link ChangeTransactionTariffRequest} with all required fields.
   *
   * @param tariff A tariff is described by fields with prices for: energy, charging time, idle
   *     time, fixed fee, reservation time,
   * @param transactionId Transaction id for new tariff.
   * @return an instance of {@link ChangeTransactionTariffRequest}
   */
  public ChangeTransactionTariffRequest createChangeTransactionTariffRequest(
      Tariff tariff, String transactionId) {
    return new ChangeTransactionTariffRequest(tariff, transactionId);
  }

  /**
   * Create a server {@link ClearTariffsRequest}.
   *
   * @return an instance of {@link ClearTariffsRequest}
   */
  public ClearTariffsRequest createClearTariffsRequest() {
    return new ClearTariffsRequest();
  }

  /**
   * Create a server {@link CostUpdatedRequest} with all required fields.
   *
   * @param totalCost Current total cost, based on the information known by the CSMS, of the
   *     transaction including taxes. In the currency configured with the configuration Variable:
   *     [Currency]
   * @param transactionId Transaction Id of the transaction the current cost are asked for.
   * @return an instance of {@link CostUpdatedRequest}
   */
  public CostUpdatedRequest createCostUpdatedRequest(Double totalCost, String transactionId) {
    return new CostUpdatedRequest(totalCost, transactionId);
  }

  /**
   * Create a server {@link GetTariffsRequest} with all required fields.
   *
   * @param evseId EVSE id to get tariff from. When evseId = 0, this gets tariffs from all EVSEs.
   * @return an instance of {@link GetTariffsRequest}
   */
  public GetTariffsRequest createGetTariffsRequest(Integer evseId) {
    return new GetTariffsRequest(evseId);
  }

  /**
   * Create a server {@link SetDefaultTariffRequest} with all required fields.
   *
   * @param evseId EVSE that tariff applies to. When evseId = 0, then tarriff applies to all EVSEs.
   * @param tariff A tariff is described by fields with prices for: energy, charging time, idle
   *     time, fixed fee, reservation time,
   * @return an instance of {@link SetDefaultTariffRequest}
   */
  public SetDefaultTariffRequest createSetDefaultTariffRequest(Integer evseId, Tariff tariff) {
    return new SetDefaultTariffRequest(evseId, tariff);
  }
}
