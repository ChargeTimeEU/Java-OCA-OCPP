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

/** Call back handler for client events of the ISO15118CertificateManagement functional block. */
public interface ClientISO15118CertificateManagementEventHandler {
  /**
   * Handle a {@link DeleteCertificateRequest} and return a {@link DeleteCertificateResponse}.
   *
   * @param request incoming {@link DeleteCertificateRequest} to handle.
   * @return outgoing {@link DeleteCertificateResponse} to reply with.
   */
  DeleteCertificateResponse handleDeleteCertificateRequest(DeleteCertificateRequest request);

  /**
   * Handle a {@link GetInstalledCertificateIdsRequest} and return a {@link
   * GetInstalledCertificateIdsResponse}.
   *
   * @param request incoming {@link GetInstalledCertificateIdsRequest} to handle.
   * @return outgoing {@link GetInstalledCertificateIdsResponse} to reply with.
   */
  GetInstalledCertificateIdsResponse handleGetInstalledCertificateIdsRequest(
      GetInstalledCertificateIdsRequest request);
}
