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
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.UUID;

/** Class with client request creators and handlers for the Security functional block. */
public class ClientSecurityFunction implements Function {

  private final ClientSecurityEventHandler eventHandler;
  private final ArrayList<FunctionFeature> features;

  public ClientSecurityFunction(ClientSecurityEventHandler eventHandler) {
    this.eventHandler = eventHandler;
    features = new ArrayList<>();
    features.add(new CertificateSignedFeature(this));
    features.add(new InstallCertificateFeature(this));
    features.add(new SecurityEventNotificationFeature(null));
    features.add(new SignCertificateFeature(null));
  }

  @Override
  public FunctionFeature[] getFeatureList() {
    return features.toArray(new FunctionFeature[0]);
  }

  @Override
  public Confirmation handleRequest(UUID sessionIndex, Request request) {
    if (request instanceof CertificateSignedRequest) {
      return eventHandler.handleCertificateSignedRequest((CertificateSignedRequest) request);
    } else if (request instanceof InstallCertificateRequest) {
      return eventHandler.handleInstallCertificateRequest((InstallCertificateRequest) request);
    }
    return null;
  }

  /**
   * Create a client {@link SecurityEventNotificationRequest} with all required fields.
   *
   * @param type Type of the security event. This value should be taken from the Security events
   *     list.
   * @param timestamp Date and time at which the event occurred.
   * @return an instance of {@link SecurityEventNotificationRequest}
   */
  public SecurityEventNotificationRequest createSecurityEventNotificationRequest(
      String type, ZonedDateTime timestamp) {
    return new SecurityEventNotificationRequest(type, timestamp);
  }

  /**
   * Create a client {@link SignCertificateRequest} with all required fields.
   *
   * @param csr The Charging Station SHALL send the public key in form of a Certificate Signing
   *     Request (CSR) as described in RFC 2986 [22] and then PEM encoded, using the
   *     SignCertificateRequest message.
   * @return an instance of {@link SignCertificateRequest}
   */
  public SignCertificateRequest createSignCertificateRequest(String csr) {
    return new SignCertificateRequest(csr);
  }
}
