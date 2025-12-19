/*
 * ChargeTime.eu - Java-OCA-OCPP
 *
 * Copyright (C) 2023 Robert Schlabbach (robert.schlabbach@ubitricity.com)
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

import static eu.chargetime.ocpp.ProtocolVersion.OCPP2_1;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.ProtocolVersion;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.v21.feature.function.ClientDiagnosticsEventHandler;
import eu.chargetime.ocpp.v21.feature.function.ClientDiagnosticsFunction;
import eu.chargetime.ocpp.v21.feature.function.ClientProvisioningEventHandler;
import eu.chargetime.ocpp.v21.feature.function.ClientProvisioningFunction;
import eu.chargetime.ocpp.v21.model.messages.*;
import eu.chargetime.ocpp.v21.model.types.*;
import java.net.MalformedURLException;
import java.util.List;

/** Multiple protocol capable fake charging station supporting up to OCPP 2.1 */
public class OCPP21MultiProtocolFakeChargingStation
    extends OCPP201MultiProtocolFakeChargingStation {
  final ClientProvisioningFunction ocpp21provisioning;
  final ClientDiagnosticsFunction ocpp21diagnostics;

  public OCPP21MultiProtocolFakeChargingStation(List<ProtocolVersion> protocolVersions)
      throws MalformedURLException {
    super(protocolVersions);

    ocpp21provisioning =
        new ClientProvisioningFunction(
            new ClientProvisioningEventHandler() {
              @Override
              public GetBaseReportResponse handleGetBaseReportRequest(
                  GetBaseReportRequest request) {
                receivedRequest = request;
                return new GetBaseReportResponse(GenericDeviceModelStatusEnum.Accepted);
              }

              @Override
              public GetReportResponse handleGetReportRequest(GetReportRequest request) {
                receivedRequest = request;
                return new GetReportResponse(GenericDeviceModelStatusEnum.Accepted);
              }

              @Override
              public GetVariablesResponse handleGetVariablesRequest(GetVariablesRequest request) {
                receivedRequest = request;
                return new GetVariablesResponse(
                    new GetVariableResult[] {
                      new GetVariableResult(
                          GetVariableStatusEnum.UnknownVariable,
                          new Component(""),
                          new Variable("")),
                      new GetVariableResult(
                          GetVariableStatusEnum.UnknownVariable,
                          new Component(""),
                          new Variable(""))
                    });
              }

              @Override
              public ResetResponse handleResetRequest(ResetRequest request) {
                receivedRequest = request;
                return new ResetResponse(ResetStatusEnum.Accepted);
              }

              @Override
              public SetNetworkProfileResponse handleSetNetworkProfileRequest(
                  SetNetworkProfileRequest request) {
                receivedRequest = request;
                return new SetNetworkProfileResponse(SetNetworkProfileStatusEnum.Accepted);
              }

              @Override
              public SetVariablesResponse handleSetVariablesRequest(SetVariablesRequest request) {
                receivedRequest = request;
                return new SetVariablesResponse(new SetVariableResult[] {});
              }
            });

    ocpp21diagnostics =
        new ClientDiagnosticsFunction(
            new ClientDiagnosticsEventHandler() {
              @Override
              public AdjustPeriodicEventStreamResponse handleAdjustPeriodicEventStreamRequest(
                  AdjustPeriodicEventStreamRequest request) {
                receivedRequest = request;
                return new AdjustPeriodicEventStreamResponse(GenericStatusEnum.Accepted);
              }

              @Override
              public ClearVariableMonitoringResponse handleClearVariableMonitoringRequest(
                  ClearVariableMonitoringRequest request) {
                receivedRequest = request;
                return new ClearVariableMonitoringResponse(new ClearMonitoringResult[] {});
              }

              @Override
              public ClosePeriodicEventStreamResponse handleClosePeriodicEventStreamRequest(
                  ClosePeriodicEventStreamRequest request) {
                receivedRequest = request;
                return new ClosePeriodicEventStreamResponse();
              }

              @Override
              public CustomerInformationResponse handleCustomerInformationRequest(
                  CustomerInformationRequest request) {
                receivedRequest = request;
                return new CustomerInformationResponse(CustomerInformationStatusEnum.Accepted);
              }

              @Override
              public GetLogResponse handleGetLogRequest(GetLogRequest request) {
                receivedRequest = request;
                return new GetLogResponse(LogStatusEnum.Accepted);
              }

              @Override
              public GetMonitoringReportResponse handleGetMonitoringReportRequest(
                  GetMonitoringReportRequest request) {
                receivedRequest = request;
                return new GetMonitoringReportResponse(GenericDeviceModelStatusEnum.Accepted);
              }

              @Override
              public GetPeriodicEventStreamResponse handleGetPeriodicEventStreamRequest(
                  GetPeriodicEventStreamRequest request) {
                receivedRequest = request;
                return new GetPeriodicEventStreamResponse();
              }

              @Override
              public SetMonitoringBaseResponse handleSetMonitoringBaseRequest(
                  SetMonitoringBaseRequest request) {
                receivedRequest = request;
                return new SetMonitoringBaseResponse(GenericDeviceModelStatusEnum.Accepted);
              }

              @Override
              public SetMonitoringLevelResponse handleSetMonitoringLevelRequest(
                  SetMonitoringLevelRequest request) {
                receivedRequest = request;
                return new SetMonitoringLevelResponse(GenericStatusEnum.Accepted);
              }

              @Override
              public SetVariableMonitoringResponse handleSetVariableMonitoringRequest(
                  SetVariableMonitoringRequest request) {
                receivedRequest = request;
                return new SetVariableMonitoringResponse(new SetMonitoringResult[] {});
              }
            });

    if (protocolVersions.contains(OCPP2_1)) {
      multiProtocolJSONClient.addFunction(OCPP2_1, ocpp21provisioning);
      multiProtocolJSONClient.addFunction(OCPP2_1, ocpp21diagnostics);
    }
  }

  @Override
  public void sendBootNotification(String vendor, String model) {
    if (!OCPP2_1.equals(getProtocolVersion())) {
      super.sendBootNotification(vendor, model);
    } else {
      Request request =
          ocpp21provisioning.createBootNotificationRequest(
              new ChargingStation(model, vendor)
                  .withSerialNumber("123456789")
                  .withFirmwareVersion("0.0.1")
                  .withModem(new Modem().withImsi("1851").withIccid("16610")),
              BootReasonEnum.Unknown);
      Class<? extends Confirmation> responseClass = BootNotificationResponse.class;
      sendRequestAndWaitForResponse(request, responseClass);
    }
  }

  @Override
  void checkConfirmation(Confirmation confirmation) {
    super.checkConfirmation(confirmation);
    if (confirmation instanceof BootNotificationResponse) {
      BootNotificationResponse bootNotificationResponse = (BootNotificationResponse) confirmation;
      if (bootNotificationResponse.getInterval() < 0) {
        throw new PropertyConstraintException(
            bootNotificationResponse.getInterval(),
            "BootNotificationResponse interval must be >= 0");
      }
    }
  }
}
