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

/** Class with server request creators and handlers for the Security functional block. */
public class ServerSecurityFunction implements Function {

  private final ServerSecurityEventHandler eventHandler;
  private final ArrayList<FunctionFeature> features;

  public ServerSecurityFunction(ServerSecurityEventHandler eventHandler) {
    this.eventHandler = eventHandler;
    features = new ArrayList<>();
    features.add(new CertificateSignedFeature(null));
    features.add(new InstallCertificateFeature(null));
    features.add(new SecurityEventNotificationFeature(this));
    features.add(new SignCertificateFeature(this));
  }

  @Override
  public FunctionFeature[] getFeatureList() {
    return features.toArray(new FunctionFeature[0]);
  }

  @Override
  public Confirmation handleRequest(UUID sessionIndex, Request request) {
    if (request instanceof SecurityEventNotificationRequest) {
      return eventHandler.handleSecurityEventNotificationRequest(
          sessionIndex, (SecurityEventNotificationRequest) request);
    } else if (request instanceof SignCertificateRequest) {
      return eventHandler.handleSignCertificateRequest(
          sessionIndex, (SignCertificateRequest) request);
    }
    return null;
  }

  /**
   * Create a server {@link CertificateSignedRequest} with all required fields.
   *
   * @param certificateChain The signed PEM encoded X.509 certificate. This can also contain the
   *     necessary sub CA certificates. In that case, the order of the bundle should follow the
   *     certificate chain, starting from the leaf certificate.
   * @return an instance of {@link CertificateSignedRequest}
   */
  public CertificateSignedRequest createCertificateSignedRequest(String certificateChain) {
    return new CertificateSignedRequest(certificateChain);
  }

  /**
   * Create a server {@link InstallCertificateRequest} with all required fields.
   *
   * @param certificateType The certificate type that is sent.
   * @param certificate A PEM encoded X.509 certificate.
   * @return an instance of {@link InstallCertificateRequest}
   */
  public InstallCertificateRequest createInstallCertificateRequest(
      InstallCertificateUseEnum certificateType, String certificate) {
    return new InstallCertificateRequest(certificateType, certificate);
  }
}
