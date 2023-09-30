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

/** Class with server request creators and handlers for the FirmwareManagement functional block. */
public class ServerFirmwareManagementFunction implements Function {

  private final ServerFirmwareManagementEventHandler eventHandler;
  private final ArrayList<FunctionFeature> features;

  public ServerFirmwareManagementFunction(ServerFirmwareManagementEventHandler eventHandler) {
    this.eventHandler = eventHandler;
    features = new ArrayList<>();
    features.add(new FirmwareStatusNotificationFeature(this));
    features.add(new PublishFirmwareFeature(null));
    features.add(new PublishFirmwareStatusNotificationFeature(this));
    features.add(new UnpublishFirmwareFeature(null));
    features.add(new UpdateFirmwareFeature(null));
  }

  @Override
  public FunctionFeature[] getFeatureList() {
    return features.toArray(new FunctionFeature[0]);
  }

  @Override
  public Confirmation handleRequest(UUID sessionIndex, Request request) {
    if (request instanceof FirmwareStatusNotificationRequest) {
      return eventHandler.handleFirmwareStatusNotificationRequest(
          sessionIndex, (FirmwareStatusNotificationRequest) request);
    } else if (request instanceof PublishFirmwareStatusNotificationRequest) {
      return eventHandler.handlePublishFirmwareStatusNotificationRequest(
          sessionIndex, (PublishFirmwareStatusNotificationRequest) request);
    }
    return null;
  }

  /**
   * Create a server {@link PublishFirmwareRequest} with all required fields.
   *
   * @param location A string containing a URI pointing to a location from which to retrieve the
   *     firmware.
   * @param checksum The MD5 checksum over the entire firmware file as a hexadecimal string of
   *     length 32.
   * @param requestId The Id of the request.
   * @return an instance of {@link PublishFirmwareRequest}
   */
  public PublishFirmwareRequest createPublishFirmwareRequest(
      String location, String checksum, Integer requestId) {
    return new PublishFirmwareRequest(location, checksum, requestId);
  }

  /**
   * Create a server {@link UnpublishFirmwareRequest} with all required fields.
   *
   * @param checksum The MD5 checksum over the entire firmware file as a hexadecimal string of
   *     length 32.
   * @return an instance of {@link UnpublishFirmwareRequest}
   */
  public UnpublishFirmwareRequest createUnpublishFirmwareRequest(String checksum) {
    return new UnpublishFirmwareRequest(checksum);
  }

  /**
   * Create a server {@link UpdateFirmwareRequest} with all required fields.
   *
   * @param requestId The Id of this request
   * @param firmware A copy of the firmware that can be loaded/updated on the Charging Station.
   * @return an instance of {@link UpdateFirmwareRequest}
   */
  public UpdateFirmwareRequest createUpdateFirmwareRequest(Integer requestId, Firmware firmware) {
    return new UpdateFirmwareRequest(requestId, firmware);
  }
}
