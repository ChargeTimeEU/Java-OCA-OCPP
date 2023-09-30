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

import static eu.chargetime.ocpp.ProtocolVersion.OCPP1_6;
import static eu.chargetime.ocpp.ProtocolVersion.OCPP2_0_1;

import eu.chargetime.ocpp.MultiProtocolJSONClient;
import eu.chargetime.ocpp.OccurenceConstraintException;
import eu.chargetime.ocpp.ProtocolVersion;
import eu.chargetime.ocpp.UnsupportedFeatureException;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.core.BootNotificationConfirmation;
import eu.chargetime.ocpp.v201.feature.function.ClientProvisioningEventHandler;
import eu.chargetime.ocpp.v201.feature.function.ClientProvisioningFunction;
import eu.chargetime.ocpp.v201.model.messages.BootNotificationResponse;
import eu.chargetime.ocpp.v201.model.messages.GetBaseReportRequest;
import eu.chargetime.ocpp.v201.model.messages.GetBaseReportResponse;
import eu.chargetime.ocpp.v201.model.messages.GetReportRequest;
import eu.chargetime.ocpp.v201.model.messages.GetReportResponse;
import eu.chargetime.ocpp.v201.model.messages.GetVariablesRequest;
import eu.chargetime.ocpp.v201.model.messages.GetVariablesResponse;
import eu.chargetime.ocpp.v201.model.messages.ResetRequest;
import eu.chargetime.ocpp.v201.model.messages.ResetResponse;
import eu.chargetime.ocpp.v201.model.messages.SetNetworkProfileRequest;
import eu.chargetime.ocpp.v201.model.messages.SetNetworkProfileResponse;
import eu.chargetime.ocpp.v201.model.messages.SetVariablesRequest;
import eu.chargetime.ocpp.v201.model.messages.SetVariablesResponse;
import eu.chargetime.ocpp.v201.model.types.BootReasonEnum;
import eu.chargetime.ocpp.v201.model.types.ChargingStation;
import eu.chargetime.ocpp.v201.model.types.GenericDeviceModelStatusEnum;
import eu.chargetime.ocpp.v201.model.types.GetVariableResult;
import eu.chargetime.ocpp.v201.model.types.Modem;
import eu.chargetime.ocpp.v201.model.types.ResetStatusEnum;
import eu.chargetime.ocpp.v201.model.types.SetNetworkProfileStatusEnum;
import eu.chargetime.ocpp.v201.model.types.SetVariableResult;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import javax.annotation.Nullable;

/** Multiple protocol capable fake charging station supporting up to OCPP 2.0.1 */
public class OCPP201MultiProtocolFakeChargingStation extends FakeChargePoint
    implements FakeChargingStation {
  final ClientProvisioningFunction provisioning;

  MultiProtocolJSONClient multiProtocolJSONClient;

  public OCPP201MultiProtocolFakeChargingStation(List<ProtocolVersion> protocolVersions)
      throws MalformedURLException {
    super();

    provisioning =
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
                return new GetVariablesResponse(new GetVariableResult[] {});
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

    multiProtocolJSONClient = new MultiProtocolJSONClient(protocolVersions);

    // overwrite super client instance with multiple protocol capable object instance
    client = multiProtocolJSONClient;

    if (protocolVersions.contains(OCPP1_6)) {
      client.addFeatureProfile(core);
      client.addFeatureProfile(firmware);
      client.addFeatureProfile(localAuthList);
      client.addFeatureProfile(reservation);
      client.addFeatureProfile(smartCharging);
      client.addFeatureProfile(remoteTrigger);
      client.addFeatureProfile(securityExt);
    }

    if (protocolVersions.contains(OCPP2_0_1)) {
      multiProtocolJSONClient.addFunction(OCPP2_0_1, provisioning);
    }
  }

  @Override
  public void connect(int port) {
    url = "ws://localhost:" + port;
    super.connect();
  }

  @Override
  @Nullable
  public ProtocolVersion getProtocolVersion() {
    return multiProtocolJSONClient.getProtocolVersion();
  }

  @Override
  public void sendBootNotification(String vendor, String model) {
    Request request;
    Class<? extends Confirmation> responseClass;
    switch (Objects.requireNonNull(getProtocolVersion())) {
      case OCPP1_6:
        request = core.createBootNotificationRequest(vendor, model);
        responseClass = BootNotificationConfirmation.class;
        break;
      case OCPP2_0_1:
        request =
            provisioning.createBootNotificationRequest(
                new ChargingStation(model, vendor)
                    .withSerialNumber("123456789")
                    .withFirmwareVersion("0.0.1")
                    .withModem(new Modem().withImsi("1851").withIccid("16610")),
                BootReasonEnum.Unknown);
        responseClass = BootNotificationResponse.class;
        break;
      default:
        throw new UnsupportedOperationException(
            "Connected with unsupported protocol version " + getProtocolVersion());
    }
    sendRequestAndWaitForResponse(request, responseClass);
  }

  void sendRequestAndWaitForResponse(Request request, Class<? extends Confirmation> responseClass) {
    try {
      CompletableFuture<Confirmation> future = client.send(request).toCompletableFuture();
      BiConsumer<Confirmation, Throwable> action =
          (confirmation, throwable) -> {
            if (confirmation != null) {
              receivedConfirmation = confirmation;
              receivedException = null;
            } else if (throwable != null) {
              receivedConfirmation = null;
              receivedException = throwable;
            } else {
              receivedConfirmation = null;
              receivedException = null;
            }
          };
      future.whenComplete(action);
      future.join();
    } catch (OccurenceConstraintException | UnsupportedFeatureException e) {
      throw new RuntimeException(e);
    }
    if (receivedException != null) {
      throw new RuntimeException("Received exception in response to request", receivedException);
    }
    if (receivedConfirmation != null) {
      if (!responseClass.isInstance(receivedConfirmation)) {
        throw new IllegalArgumentException("Received confirmation is not of expected class");
      }
    } else {
      throw new IllegalStateException("Received neither a confirmation nor a throwable");
    }
  }
}
