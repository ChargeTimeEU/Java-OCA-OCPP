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

/** Class with client request creators and handlers for the FirmwareManagement functional block. */
public class ClientFirmwareManagementFunction implements Function {

  private final ClientFirmwareManagementEventHandler eventHandler;
  private final ArrayList<FunctionFeature> features;

  public ClientFirmwareManagementFunction(ClientFirmwareManagementEventHandler eventHandler) {
    this.eventHandler = eventHandler;
    features = new ArrayList<>();
    features.add(new FirmwareStatusNotificationFeature(null));
    features.add(new PublishFirmwareFeature(this));
    features.add(new PublishFirmwareStatusNotificationFeature(null));
    features.add(new UnpublishFirmwareFeature(this));
    features.add(new UpdateFirmwareFeature(this));
  }

  @Override
  public FunctionFeature[] getFeatureList() {
    return features.toArray(new FunctionFeature[0]);
  }

  @Override
  public Confirmation handleRequest(UUID sessionIndex, Request request) {
    if (request instanceof PublishFirmwareRequest) {
      return eventHandler.handlePublishFirmwareRequest((PublishFirmwareRequest) request);
    } else if (request instanceof UnpublishFirmwareRequest) {
      return eventHandler.handleUnpublishFirmwareRequest((UnpublishFirmwareRequest) request);
    } else if (request instanceof UpdateFirmwareRequest) {
      return eventHandler.handleUpdateFirmwareRequest((UpdateFirmwareRequest) request);
    }
    return null;
  }

  /**
   * Create a client {@link FirmwareStatusNotificationRequest} with all required fields.
   *
   * @param status The progress status of the firmware installation.
   * @return an instance of {@link FirmwareStatusNotificationRequest}
   */
  public FirmwareStatusNotificationRequest createFirmwareStatusNotificationRequest(
      FirmwareStatusEnum status) {
    return new FirmwareStatusNotificationRequest(status);
  }

  /**
   * Create a client {@link PublishFirmwareStatusNotificationRequest} with all required fields.
   *
   * @param status The progress status of the publishfirmware installation.
   * @return an instance of {@link PublishFirmwareStatusNotificationRequest}
   */
  public PublishFirmwareStatusNotificationRequest createPublishFirmwareStatusNotificationRequest(
      PublishFirmwareStatusEnum status) {
    return new PublishFirmwareStatusNotificationRequest(status);
  }
}
