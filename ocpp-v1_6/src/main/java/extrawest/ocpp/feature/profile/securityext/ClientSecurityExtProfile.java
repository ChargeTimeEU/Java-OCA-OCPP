package extrawest.ocpp.feature.profile.securityext;

/*
   ChargeTime.eu - Java-OCA-OCPP

   MIT License

   Copyright (C) 2022 Mathias Oben <mathias.oben@enervalis.com>

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

import extrawest.ocpp.feature.Feature;
import extrawest.ocpp.feature.ProfileFeature;
import extrawest.ocpp.feature.profile.Profile;
import extrawest.ocpp.feature.securityext.CertificateSignedFeature;
import extrawest.ocpp.feature.securityext.DeleteCertificateFeature;
import extrawest.ocpp.feature.securityext.ExtendedTriggerMessageFeature;
import extrawest.ocpp.feature.securityext.GetInstalledCertificateIdsFeature;
import extrawest.ocpp.feature.securityext.GetLogFeature;
import extrawest.ocpp.feature.securityext.InstallCertificateFeature;
import extrawest.ocpp.feature.securityext.LogStatusNotificationFeature;
import extrawest.ocpp.feature.securityext.SecurityEventNotificationFeature;
import extrawest.ocpp.feature.securityext.SignCertificateFeature;
import extrawest.ocpp.feature.securityext.SignedFirmwareStatusNotificationFeature;
import extrawest.ocpp.feature.securityext.SignedUpdateFirmwareFeature;
import extrawest.ocpp.model.Confirmation;
import extrawest.ocpp.model.Request;
import extrawest.ocpp.model.securityext.CertificateSignedRequest;
import extrawest.ocpp.model.securityext.DeleteCertificateRequest;
import extrawest.ocpp.model.securityext.ExtendedTriggerMessageRequest;
import extrawest.ocpp.model.securityext.GetInstalledCertificateIdsRequest;
import extrawest.ocpp.model.securityext.GetLogRequest;
import extrawest.ocpp.model.securityext.InstallCertificateRequest;
import extrawest.ocpp.model.securityext.LogStatusNotificationRequest;
import extrawest.ocpp.model.securityext.SecurityEventNotificationRequest;
import extrawest.ocpp.model.securityext.SignCertificateRequest;
import extrawest.ocpp.model.securityext.SignedFirmwareStatusNotificationRequest;
import extrawest.ocpp.model.securityext.SignedUpdateFirmwareRequest;
import extrawest.ocpp.model.securityext.types.FirmwareStatusEnumType;
import extrawest.ocpp.model.securityext.types.UploadLogStatusEnumType;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class ClientSecurityExtProfile implements Profile {

  private final Set<Feature> features;
  private final ClientSecurityExtEventHandler eventHandler;

  public ClientSecurityExtProfile(ClientSecurityExtEventHandler handler) {
    this.features = new HashSet<>();
    this.eventHandler = handler;

    features.add(new CertificateSignedFeature(this));
    features.add(new DeleteCertificateFeature(this));
    features.add(new ExtendedTriggerMessageFeature(this));
    features.add(new GetInstalledCertificateIdsFeature(this));
    features.add(new GetLogFeature(this));
    features.add(new InstallCertificateFeature(this));
    features.add(new LogStatusNotificationFeature(null));
    features.add(new SecurityEventNotificationFeature(null));
    features.add(new SignCertificateFeature(null));
    features.add(new SignedFirmwareStatusNotificationFeature(null));
    features.add(new SignedUpdateFirmwareFeature(this));
  }

  @Override
  public ProfileFeature[] getFeatureList() {
    return features.toArray(new ProfileFeature[0]);
  }

  @Override
  public Confirmation handleRequest(UUID sessionIndex, Request request) {
    Confirmation result = null;

    if (request instanceof CertificateSignedRequest) {
      result = eventHandler.handleCertificateSignedRequest((CertificateSignedRequest) request);
    } else if (request instanceof DeleteCertificateRequest) {
      result = eventHandler.handleDeleteCertificateRequest((DeleteCertificateRequest) request);
    } else if (request instanceof ExtendedTriggerMessageRequest) {
      result = eventHandler.handleExtendedTriggerMessageRequest((ExtendedTriggerMessageRequest) request);
    } else if (request instanceof GetInstalledCertificateIdsRequest) {
      result = eventHandler.handleGetInstalledCertificateIdsRequest((GetInstalledCertificateIdsRequest) request);
    } else if (request instanceof GetLogRequest) {
      result = eventHandler.handleGetLogRequest((GetLogRequest) request);
    } else if (request instanceof InstallCertificateRequest) {
      result = eventHandler.handleInstallCertificateRequest((InstallCertificateRequest) request);
    }else if (request instanceof SignedUpdateFirmwareRequest) {
      result = eventHandler.handleSignedUpdateFirmwareRequest((SignedUpdateFirmwareRequest) request);
    }

    return result;
  }

  /**
   * Create a client {@link LogStatusNotificationRequest} with required values.
   *
   * @return an instance of {@link LogStatusNotificationRequest}.
   * @see LogStatusNotificationRequest
   * @see LogStatusNotificationFeature
   */
  public LogStatusNotificationRequest createLogStatusNotificationRequest(UploadLogStatusEnumType status) {
    return new LogStatusNotificationRequest(status);
  }

  /**
   * Create a client {@link SecurityEventNotificationRequest} with required values.
   *
   * @return an instance of {@link SecurityEventNotificationRequest}
   * @see SecurityEventNotificationRequest
   * @see SecurityEventNotificationFeature
   */
  public SecurityEventNotificationRequest createSecurityEventNotificationRequest(String type, ZonedDateTime timestamp) {
    return new SecurityEventNotificationRequest(type, timestamp);
  }

  /**
   * Create a client {@link SignCertificateRequest} with required values.
   *
   * @return an instance of {@link SignCertificateRequest}.
   * @see SignCertificateRequest
   * @see SignCertificateFeature
   */
  public SignCertificateRequest createSignCertificateRequest(String csr) {
    return new SignCertificateRequest(csr);
  }

  /**
   * Create a client {@link SignedFirmwareStatusNotificationRequest}.
   *
   * @return an instance of {@link SignedFirmwareStatusNotificationRequest}
   * @see SignedFirmwareStatusNotificationRequest
   * @see SignedFirmwareStatusNotificationFeature
   */
  public SignedFirmwareStatusNotificationRequest createSignedFirmwareStatusNotificationRequest(FirmwareStatusEnumType status) {
    return new SignedFirmwareStatusNotificationRequest(status);
  }
}
