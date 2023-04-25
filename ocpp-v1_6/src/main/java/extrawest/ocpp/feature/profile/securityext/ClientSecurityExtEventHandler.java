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

import extrawest.ocpp.model.securityext.CertificateSignedConfirmation;
import extrawest.ocpp.model.securityext.CertificateSignedRequest;
import extrawest.ocpp.model.securityext.DeleteCertificateConfirmation;
import extrawest.ocpp.model.securityext.DeleteCertificateRequest;
import extrawest.ocpp.model.securityext.ExtendedTriggerMessageConfirmation;
import extrawest.ocpp.model.securityext.ExtendedTriggerMessageRequest;
import extrawest.ocpp.model.securityext.GetInstalledCertificateIdsConfirmation;
import extrawest.ocpp.model.securityext.GetInstalledCertificateIdsRequest;
import extrawest.ocpp.model.securityext.GetLogConfirmation;
import extrawest.ocpp.model.securityext.GetLogRequest;
import extrawest.ocpp.model.securityext.InstallCertificateConfirmation;
import extrawest.ocpp.model.securityext.InstallCertificateRequest;
import extrawest.ocpp.model.securityext.SignedUpdateFirmwareConfirmation;
import extrawest.ocpp.model.securityext.SignedUpdateFirmwareRequest;

public interface ClientSecurityExtEventHandler {
  /**
   * Handle a {@link CertificateSignedRequest} and return a {@link CertificateSignedConfirmation}.
   *
   * @param request incoming {@link CertificateSignedRequest} to handle.
   * @return outgoing {@link CertificateSignedConfirmation} to reply with.
   */
  CertificateSignedConfirmation handleCertificateSignedRequest(CertificateSignedRequest request);

  /**
   * Handle a {@link DeleteCertificateRequest} and return a {@link DeleteCertificateConfirmation}.
   *
   * @param request incoming {@link DeleteCertificateRequest} to handle.
   * @return outgoing {@link DeleteCertificateConfirmation} to reply with.
   */
  DeleteCertificateConfirmation handleDeleteCertificateRequest(DeleteCertificateRequest request);

  /**
   * Handle a {@link ExtendedTriggerMessageRequest} and return a {@link ExtendedTriggerMessageConfirmation}.
   *
   * @param request incoming {@link ExtendedTriggerMessageRequest} to handle.
   * @return outgoing {@link ExtendedTriggerMessageConfirmation} to reply with.
   */
  ExtendedTriggerMessageConfirmation handleExtendedTriggerMessageRequest(ExtendedTriggerMessageRequest request);

  /**
   * Handle a {@link GetInstalledCertificateIdsRequest} and return a {@link GetInstalledCertificateIdsConfirmation}.
   *
   * @param request incoming {@link GetInstalledCertificateIdsRequest} to handle.
   * @return outgoing {@link GetInstalledCertificateIdsConfirmation} to reply with.
   */
  GetInstalledCertificateIdsConfirmation handleGetInstalledCertificateIdsRequest(GetInstalledCertificateIdsRequest request);

  /**
   * Handle a {@link GetLogRequest} and return a {@link GetLogConfirmation}.
   *
   * @param request incoming {@link GetLogRequest} to handle.
   * @return outgoing {@link GetLogConfirmation} to reply with.
   */
  GetLogConfirmation handleGetLogRequest(GetLogRequest request);

  /**
   * Handle a {@link InstallCertificateRequest} and return a {@link InstallCertificateConfirmation}.
   *
   * @param request incoming {@link InstallCertificateRequest} to handle.
   * @return outgoing {@link InstallCertificateConfirmation} to reply with.
   */
  InstallCertificateConfirmation handleInstallCertificateRequest(InstallCertificateRequest request);

  /**
   * Handle a {@link SignedUpdateFirmwareRequest} and return a {@link SignedUpdateFirmwareConfirmation}.
   *
   * @param request incoming {@link SignedUpdateFirmwareRequest} to handle.
   * @return outgoing {@link SignedUpdateFirmwareConfirmation} to reply with.
   */
  SignedUpdateFirmwareConfirmation handleSignedUpdateFirmwareRequest(SignedUpdateFirmwareRequest request);
}
