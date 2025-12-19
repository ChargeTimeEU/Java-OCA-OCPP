/*
 * ChargeTime.eu - Java-OCA-OCPP
 *
 * Copyright (C) 2025 Robert Schlabbach (robert.schlabbach@ubitricity.com)
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package eu.chargetime.ocpp.test;

import static eu.chargetime.ocpp.v21.model.types.GenericStatusEnum.Accepted;

import eu.chargetime.ocpp.v21.feature.function.ServerDiagnosticsEventHandler;
import eu.chargetime.ocpp.v21.feature.function.ServerProvisioningEventHandler;
import eu.chargetime.ocpp.v21.model.messages.*;
import eu.chargetime.ocpp.v21.model.types.RegistrationStatusEnum;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.UUID;

/** Dummy handlers for multiple protocol server functions up to OCPP 2.1 */
public class OCPP21MultiProtocolDummyHandlers extends OCPP201MultiProtocolDummyHandlers {

  /* OCPP 2.1 Provisioning function handlers */

  public ServerProvisioningEventHandler createOCPP21ServerProvisioningEventHandler() {
    return new ServerProvisioningEventHandler() {
      @Override
      public BootNotificationResponse handleBootNotificationRequest(
          UUID sessionIndex, BootNotificationRequest request) {
        setReceivedRequest(request);
        BootNotificationResponse response =
            new BootNotificationResponse(
                ZonedDateTime.now(ZoneOffset.UTC),
                isRiggedToSendInvalidResponse() ? -1 : 1,
                RegistrationStatusEnum.Accepted);
        return failurePoint(response);
      }

      @Override
      public HeartbeatResponse handleHeartbeatRequest(UUID sessionIndex, HeartbeatRequest request) {
        setReceivedRequest(request);
        HeartbeatResponse response = new HeartbeatResponse(ZonedDateTime.now(ZoneOffset.UTC));
        return failurePoint(response);
      }

      @Override
      public NotifyReportResponse handleNotifyReportRequest(
          UUID sessionIndex, NotifyReportRequest request) {
        setReceivedRequest(request);
        NotifyReportResponse response = new NotifyReportResponse();
        return failurePoint(response);
      }
    };
  }

  /* OCPP 2.1 Diagnostics function handlers */

  public ServerDiagnosticsEventHandler createOCPP21ServerDiagnosticsEventHandler() {
    return new ServerDiagnosticsEventHandler() {
      @Override
      public LogStatusNotificationResponse handleLogStatusNotificationRequest(
          UUID sessionIndex, LogStatusNotificationRequest request) {
        setReceivedRequest(request);
        LogStatusNotificationResponse response = new LogStatusNotificationResponse();
        return failurePoint(response);
      }

      @Override
      public NotifyCustomerInformationResponse handleNotifyCustomerInformationRequest(
          UUID sessionIndex, NotifyCustomerInformationRequest request) {
        setReceivedRequest(request);
        NotifyCustomerInformationResponse response = new NotifyCustomerInformationResponse();
        return failurePoint(response);
      }

      @Override
      public NotifyMonitoringReportResponse handleNotifyMonitoringReportRequest(
          UUID sessionIndex, NotifyMonitoringReportRequest request) {
        setReceivedRequest(request);
        NotifyMonitoringReportResponse response = new NotifyMonitoringReportResponse();
        return failurePoint(response);
      }

      @Override
      public void handleNotifyPeriodicEventStream(
          UUID sessionIndex, NotifyPeriodicEventStream request) {
        setReceivedRequest(request);
      }

      @Override
      public OpenPeriodicEventStreamResponse handleOpenPeriodicEventStreamRequest(
          UUID sessionIndex, OpenPeriodicEventStreamRequest request) {
        setReceivedRequest(request);
        OpenPeriodicEventStreamResponse response = new OpenPeriodicEventStreamResponse(Accepted);
        return failurePoint(response);
      }
    };
  }
}
