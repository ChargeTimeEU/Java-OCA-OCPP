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

import eu.chargetime.ocpp.feature.FunctionFeature;
import eu.chargetime.ocpp.feature.function.Function;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.v201.feature.*;
import eu.chargetime.ocpp.v201.model.messages.*;
import eu.chargetime.ocpp.v201.model.types.*;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Class with client request creators and handlers for the ISO15118CertificateManagement functional
 * block.
 */
public class ClientISO15118CertificateManagementFunction implements Function {

  private final ClientISO15118CertificateManagementEventHandler eventHandler;
  private final ArrayList<FunctionFeature> features;

  public ClientISO15118CertificateManagementFunction(
      ClientISO15118CertificateManagementEventHandler eventHandler) {
    this.eventHandler = eventHandler;
    features = new ArrayList<>();
    features.add(new DeleteCertificateFeature(this));
    features.add(new Get15118EVCertificateFeature(null));
    features.add(new GetCertificateStatusFeature(null));
    features.add(new GetInstalledCertificateIdsFeature(this));
  }

  @Override
  public FunctionFeature[] getFeatureList() {
    return features.toArray(new FunctionFeature[0]);
  }

  @Override
  public Confirmation handleRequest(UUID sessionIndex, Request request) {
    if (request instanceof DeleteCertificateRequest) {
      return eventHandler.handleDeleteCertificateRequest((DeleteCertificateRequest) request);
    } else if (request instanceof GetInstalledCertificateIdsRequest) {
      return eventHandler.handleGetInstalledCertificateIdsRequest(
          (GetInstalledCertificateIdsRequest) request);
    }
    return null;
  }

  /**
   * Create a client {@link Get15118EVCertificateRequest} with all required fields.
   *
   * @param iso15118SchemaVersion Schema version currently used for the 15118 session between EV and
   *     Charging Station. Needed for parsing of the EXI stream by the CSMS.
   * @param action Whether certificate needs to be installed or updated.
   * @param exiRequest Raw CertificateInstallationReq request from EV, Base64 encoded.
   * @return an instance of {@link Get15118EVCertificateRequest}
   */
  public Get15118EVCertificateRequest createGet15118EVCertificateRequest(
      String iso15118SchemaVersion, CertificateActionEnum action, String exiRequest) {
    return new Get15118EVCertificateRequest(iso15118SchemaVersion, action, exiRequest);
  }

  /**
   * Create a client {@link GetCertificateStatusRequest} with all required fields.
   *
   * @param ocspRequestData ocspRequestData
   * @return an instance of {@link GetCertificateStatusRequest}
   */
  public GetCertificateStatusRequest createGetCertificateStatusRequest(
      OCSPRequestData ocspRequestData) {
    return new GetCertificateStatusRequest(ocspRequestData);
  }
}
