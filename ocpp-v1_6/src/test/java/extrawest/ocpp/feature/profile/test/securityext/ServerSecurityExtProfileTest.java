package extrawest.ocpp.feature.profile.test.securityext;

import extrawest.ocpp.feature.Feature;
import extrawest.ocpp.feature.profile.securityext.ServerSecurityExtEventHandler;
import extrawest.ocpp.feature.profile.securityext.ServerSecurityExtProfile;
import extrawest.ocpp.feature.profile.test.ProfileTest;
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
import extrawest.ocpp.model.securityext.CertificateSignedRequest;
import extrawest.ocpp.model.securityext.DeleteCertificateRequest;
import extrawest.ocpp.model.securityext.ExtendedTriggerMessageRequest;
import extrawest.ocpp.model.securityext.GetInstalledCertificateIdsRequest;
import extrawest.ocpp.model.securityext.GetLogRequest;
import extrawest.ocpp.model.securityext.InstallCertificateRequest;
import extrawest.ocpp.model.securityext.LogStatusNotificationConfirmation;
import extrawest.ocpp.model.securityext.LogStatusNotificationRequest;
import extrawest.ocpp.model.securityext.SecurityEventNotificationConfirmation;
import extrawest.ocpp.model.securityext.SecurityEventNotificationRequest;
import extrawest.ocpp.model.securityext.SignCertificateConfirmation;
import extrawest.ocpp.model.securityext.SignCertificateRequest;
import extrawest.ocpp.model.securityext.SignedFirmwareStatusNotificationConfirmation;
import extrawest.ocpp.model.securityext.SignedFirmwareStatusNotificationRequest;
import extrawest.ocpp.model.securityext.SignedUpdateFirmwareRequest;
import extrawest.ocpp.model.securityext.types.CertificateHashDataType;
import extrawest.ocpp.model.securityext.types.CertificateUseEnumType;
import extrawest.ocpp.model.securityext.types.FirmwareType;
import extrawest.ocpp.model.securityext.types.LogEnumType;
import extrawest.ocpp.model.securityext.types.LogParametersType;
import extrawest.ocpp.model.securityext.types.MessageTriggerEnumType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

@RunWith(MockitoJUnitRunner.class)
public class ServerSecurityExtProfileTest extends ProfileTest {
  private static final UUID SESSION_ID = UUID.randomUUID();

  private ServerSecurityExtProfile profile;

  @Mock
  private ServerSecurityExtEventHandler handler;

  @Before
  public void setup() {
    profile = new ServerSecurityExtProfile(handler);
  }

  @Test
  public void getFeatureList_containsAllFeature() {
    // When
    Feature[] features = profile.getFeatureList();

    // then
    assertTrue(findFeature(features, "CertificateSigned") instanceof CertificateSignedFeature);
    assertTrue(findFeature(features, "DeleteCertificate") instanceof DeleteCertificateFeature);
    assertTrue(findFeature(features, "ExtendedTriggerMessage") instanceof ExtendedTriggerMessageFeature);
    assertTrue(findFeature(features, "GetInstalledCertificateIds") instanceof GetInstalledCertificateIdsFeature);
    assertTrue(findFeature(features, "GetLog") instanceof GetLogFeature);
    assertTrue(findFeature(features, "InstallCertificate") instanceof InstallCertificateFeature);
    assertTrue(findFeature(features, "LogStatusNotification") instanceof LogStatusNotificationFeature);
    assertTrue(findFeature(features, "SecurityEventNotification") instanceof SecurityEventNotificationFeature);
    assertTrue(findFeature(features, "SignCertificate") instanceof SignCertificateFeature);
    assertTrue(findFeature(features, "SignedFirmwareStatusNotification") instanceof SignedFirmwareStatusNotificationFeature);
    assertTrue(findFeature(features, "SignedUpdateFirmware") instanceof SignedUpdateFirmwareFeature);
  }

  @Test
  public void createCertificateSignedRequest_withParams_returnsCompleteRequest() {
    // Given
    String certificateChain = "certChain";

    // When
    CertificateSignedRequest actual = profile.createCertificateSignedRequest(certificateChain);

    // Then
    assertEquals(certificateChain, actual.getCertificateChain());
  }

  @Test
  public void createDeleteCertificateRequest_withParams_returnsCompleteRequest() {
    // Given
    CertificateHashDataType certificateHashData = new CertificateHashDataType();

    // When
    DeleteCertificateRequest actual = profile.createDeleteCertificateRequest(certificateHashData);

    // Then
    Assert.assertEquals(certificateHashData, actual.getCertificateHashData());
  }

  @Test
  public void createExtendedTriggerMessageRequest_withParams_returnsCompleteRequest() {
    // Given
    MessageTriggerEnumType requestedMessage = MessageTriggerEnumType.BootNotification;

    // When
    ExtendedTriggerMessageRequest actual = profile.createExtendedTriggerMessageRequest(requestedMessage);

    // Then
    assertEquals(requestedMessage, actual.getRequestedMessage());
    assertNull(actual.getConnectorId());
  }

  @Test
  public void createGetInstalledCertificateIdsRequest_withParams_returnsCompleteRequest() {
    // Given
    CertificateUseEnumType certificateType = CertificateUseEnumType.CentralSystemRootCertificate;

    // When
    GetInstalledCertificateIdsRequest actual = profile.createGetInstalledCertificateIdsRequest(certificateType);

    // Then
    assertEquals(certificateType, actual.getCertificateType());
  }

  @Test
  public void createGetLogRequest_withParams_returnsCompleteRequest() {
    // Given
    LogEnumType logType = LogEnumType.SecurityLog;
    Integer requestId = 123;
    LogParametersType log = new LogParametersType();

    // When
    GetLogRequest actual = profile.createGetLogRequest(logType, requestId, log);

    // Then
    assertEquals(logType, actual.getLogType());
    assertEquals(requestId, actual.getRequestId());
    assertNull(actual.getRetries());
    assertNull(actual.getRetryInterval());
    assertEquals(log, actual.getLog());
  }

  @Test
  public void createInstallCertificateRequest_withParams_returnsCompleteRequest() {
    // Given
    CertificateUseEnumType certificateType = CertificateUseEnumType.CentralSystemRootCertificate;
    String certificate = "certificate";

    // When
    InstallCertificateRequest actual = profile.createInstallCertificateRequest(certificateType, certificate);

    // Then
    assertEquals(certificateType, actual.getCertificateType());
    assertEquals(certificate, actual.getCertificate());
  }

  @Test
  public void createSignedUpdateFirmwareRequest_withParams_returnsCompleteRequest() {
    // Given
    Integer requestId = 123;
    FirmwareType firmware = new FirmwareType();

    // When
    SignedUpdateFirmwareRequest actual = profile.createSignedUpdateFirmwareRequest(requestId, firmware);

    // Then
    assertNull(actual.getRetries());
    assertNull(actual.getRetryInterval());
    assertEquals(requestId, actual.getRequestId());
    assertEquals(firmware, actual.getFirmware());
  }

  @Test
  public void handleRequest_whenLogStatusNotificationRequest_callsHandleLogStatusNotificationRequest() {
    // Given
    LogStatusNotificationRequest request = mock(LogStatusNotificationRequest.class);
    LogStatusNotificationConfirmation confirmation = mock(LogStatusNotificationConfirmation.class);
    when(handler.handleLogStatusNotificationRequest(SESSION_ID, request)).thenReturn(confirmation);

    // When
    Confirmation actual = profile.handleRequest(SESSION_ID, request);

    // Then
    assertEquals(confirmation, actual);
  }

  @Test
  public void handleRequest_whenSecurityEventNotificationRequest_callsHandleSecurityEventNotificationRequest() {
    // Given
    SecurityEventNotificationRequest request = mock(SecurityEventNotificationRequest.class);
    SecurityEventNotificationConfirmation confirmation = mock(SecurityEventNotificationConfirmation.class);
    when(handler.handleSecurityEventNotificationRequest(SESSION_ID, request)).thenReturn(confirmation);

    // When
    Confirmation actual = profile.handleRequest(SESSION_ID, request);

    // Then
    assertEquals(confirmation, actual);
  }

  @Test
  public void handleRequest_whenSignCertificateRequest_callsSignCertificateRequest() {
    // Given
    SignCertificateRequest request = mock(SignCertificateRequest.class);
    SignCertificateConfirmation confirmation = mock(SignCertificateConfirmation.class);
    when(handler.handleSignCertificateRequest(SESSION_ID, request)).thenReturn(confirmation);

    // When
    Confirmation actual = profile.handleRequest(SESSION_ID, request);

    // Then
    assertEquals(confirmation, actual);
  }

  @Test
  public void handleRequest_whenSignedFirmwareStatusNotificationRequest_callsHandleSignedFirmwareStatusNotificationRequest() {
    // Given
    SignedFirmwareStatusNotificationRequest request = mock(SignedFirmwareStatusNotificationRequest.class);
    SignedFirmwareStatusNotificationConfirmation confirmation = mock(SignedFirmwareStatusNotificationConfirmation.class);
    when(handler.handleSignedFirmwareStatusNotificationRequest(SESSION_ID, request)).thenReturn(confirmation);

    // When
    Confirmation actual = profile.handleRequest(SESSION_ID, request);

    // Then
    assertEquals(confirmation, actual);
  }

}
