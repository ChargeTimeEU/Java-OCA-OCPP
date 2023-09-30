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

/**
 * Class with server request creators and handlers for the LocalAuthorizationListManagement
 * functional block.
 */
public class ServerLocalAuthorizationListManagementFunction implements Function {

  private final ArrayList<FunctionFeature> features;

  public ServerLocalAuthorizationListManagementFunction() {
    features = new ArrayList<>();
    features.add(new GetLocalListVersionFeature(null));
    features.add(new SendLocalListFeature(null));
  }

  @Override
  public FunctionFeature[] getFeatureList() {
    return features.toArray(new FunctionFeature[0]);
  }

  @Override
  public Confirmation handleRequest(UUID sessionIndex, Request request) {
    return null;
  }

  /**
   * Create a server {@link GetLocalListVersionRequest}.
   *
   * @return an instance of {@link GetLocalListVersionRequest}
   */
  public GetLocalListVersionRequest createGetLocalListVersionRequest() {
    return new GetLocalListVersionRequest();
  }

  /**
   * Create a server {@link SendLocalListRequest} with all required fields.
   *
   * @param versionNumber In case of a full update this is the version number of the full list. In
   *     case of a differential update it is the version number of the list after the update has
   *     been applied.
   * @param updateType The type of update (full or differential) of this request.
   * @return an instance of {@link SendLocalListRequest}
   */
  public SendLocalListRequest createSendLocalListRequest(
      Integer versionNumber, UpdateEnum updateType) {
    return new SendLocalListRequest(versionNumber, updateType);
  }
}
