package eu.chargetime.ocpp.feature.profile;
/*
   ChargeTime.eu - Java-OCA-OCPP

   MIT License

   Copyright (C) 2016-2018 Thomas Volden <tv@chargetime.eu>
   Copyright (C) 2019 Kevin Raddatz <kevin.raddatz@valtech-mobility.com>

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

import eu.chargetime.ocpp.feature.*;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.core.*;
import java.util.HashSet;
import java.util.UUID;

public class ServerCoreProfile implements Profile {

  private ServerCoreEventHandler handler;
  private HashSet<Feature> features;

  public ServerCoreProfile(ServerCoreEventHandler handler) {
    this.handler = handler;

    features = new HashSet<>();
    features.add(new AuthorizeFeature(this));
    features.add(new BootNotificationFeature(this));
    features.add(new ChangeAvailabilityFeature(null));
    features.add(new ChangeConfigurationFeature(null));
    features.add(new ClearCacheFeature(null));
    features.add(new DataTransferFeature(this));
    features.add(new GetConfigurationFeature(null));
    features.add(new HeartbeatFeature(this));
    features.add(new MeterValuesFeature(this));
    features.add(new RemoteStartTransactionFeature(null));
    features.add(new RemoteStopTransactionFeature(null));
    features.add(new ResetFeature(null));
    features.add(new StartTransactionFeature(this));
    features.add(new StatusNotificationFeature(this));
    features.add(new StopTransactionFeature(this));
    features.add(new UnlockConnectorFeature(null));
  }

  @Override
  public ProfileFeature[] getFeatureList() {
    return features.toArray(new ProfileFeature[0]);
  }

  @Override
  public Confirmation handleRequest(UUID sessionIndex, Request request) {
    Confirmation result = null;

    if (request instanceof AuthorizeRequest) {
      result = handler.handleAuthorizeRequest(sessionIndex, (AuthorizeRequest) request);
    } else if (request instanceof BootNotificationRequest) {
      result =
          handler.handleBootNotificationRequest(sessionIndex, (BootNotificationRequest) request);
    } else if (request instanceof DataTransferRequest) {
      result = handler.handleDataTransferRequest(sessionIndex, (DataTransferRequest) request);
    } else if (request instanceof HeartbeatRequest) {
      result = handler.handleHeartbeatRequest(sessionIndex, (HeartbeatRequest) request);
    } else if (request instanceof MeterValuesRequest) {
      result = handler.handleMeterValuesRequest(sessionIndex, (MeterValuesRequest) request);
    } else if (request instanceof StartTransactionRequest) {
      result =
          handler.handleStartTransactionRequest(sessionIndex, (StartTransactionRequest) request);
    } else if (request instanceof StatusNotificationRequest) {
      result =
          handler.handleStatusNotificationRequest(
              sessionIndex, (StatusNotificationRequest) request);
    } else if (request instanceof StopTransactionRequest) {
      result = handler.handleStopTransactionRequest(sessionIndex, (StopTransactionRequest) request);
    }

    return result;
  }

  /**
   * Create a {@link ChangeAvailabilityRequest} with required values.
   *
   * @param connectorId integer, must be a non-negative number. * @param type the {@link
   *     AvailabilityType} of the connector.
   * @return an instance of {@link ChangeAvailabilityRequest}.
   * @see ChangeAvailabilityRequest
   * @see ChangeAvailabilityFeature
   */
  public ChangeAvailabilityRequest createChangeAvailabilityRequest(
      AvailabilityType type, int connectorId) {
    return new ChangeAvailabilityRequest(connectorId, type);
  }

  /**
   * Create a client {@link ChangeConfigurationRequest} with required values.
   *
   * @param key String, max 50 characters, case insensitive
   * @param value String, max 500 characters, case insensitive
   * @return an instance of {@link ChangeConfigurationRequest}
   * @see ChangeConfigurationRequest
   * @see ChangeConfigurationFeature
   */
  public ChangeConfigurationRequest createChangeConfigurationRequest(String key, String value) {
    return new ChangeConfigurationRequest(key, value);
  }

  /**
   * Create a client {@link ClearCacheRequest}.
   *
   * @return an instance of {@link ClearCacheRequest}
   * @see ClearCacheRequest
   * @see ClearCacheFeature
   */
  public ClearCacheRequest createClearCacheRequest() {
    return new ClearCacheRequest();
  }

  /**
   * Create a client {@link DataTransferRequest} required values.
   *
   * @param vendorId Vendor identification.
   * @return an instance of {@link DataTransferRequest}
   * @see DataTransferRequest
   * @see DataTransferFeature
   */
  public DataTransferRequest createDataTransferRequest(String vendorId) {
    return new DataTransferRequest(vendorId);
  }

  /**
   * Create a client {@link GetConfigurationRequest}.
   *
   * @return an instance of {@link GetConfigurationRequest}
   * @see GetConfigurationRequest
   * @see GetConfigurationFeature
   */
  public GetConfigurationRequest createGetConfigurationRequest() {
    return new GetConfigurationRequest();
  }

  /**
   * Create a client {@link RemoteStartTransactionRequest} with required values.
   *
   * @param idTag a String with max length 20
   * @return an instance of {@link RemoteStartTransactionRequest}
   * @see RemoteStartTransactionRequest
   * @see RemoteStartTransactionFeature
   */
  public RemoteStartTransactionRequest createRemoteStartTransactionRequest(String idTag) {
    return new RemoteStartTransactionRequest(idTag);
  }

  /**
   * Create a client {@link RemoteStopTransactionRequest} with required values.
   *
   * @param transactionId integer, transaction id
   * @return an instance of {@link RemoteStopTransactionRequest}
   * @see RemoteStopTransactionRequest
   * @see RemoteStartTransactionFeature
   */
  public RemoteStopTransactionRequest createRemoteStopTransactionRequest(Integer transactionId) {
    return new RemoteStopTransactionRequest(transactionId);
  }

  /**
   * Create a client {@link ResetRequest} with required values.
   *
   * @param type the {@link ResetType}
   * @return an instance of {@link ResetRequest}
   * @see ResetRequest
   * @see ResetFeature
   */
  public ResetRequest createResetRequest(ResetType type) {
    return new ResetRequest(type);
  }

  /**
   * Create a client {@link UnlockConnectorRequest} with required values..
   *
   * @param connectorId integer, value &gt; 0
   * @return an instance of {@link UnlockConnectorRequest}
   * @see UnlockConnectorRequest
   * @see UnlockConnectorFeature
   */
  public UnlockConnectorRequest createUnlockConnectorRequest(int connectorId) {
    return new UnlockConnectorRequest(connectorId);
  }
}
