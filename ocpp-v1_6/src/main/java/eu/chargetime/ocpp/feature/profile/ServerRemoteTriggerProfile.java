package eu.chargetime.ocpp.feature.profile;

import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.feature.ProfileFeature;
import eu.chargetime.ocpp.feature.TriggerMessageFeature;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.remotetrigger.TriggerMessageRequest;
import eu.chargetime.ocpp.model.remotetrigger.TriggerMessageRequestType;
import java.util.HashSet;
import java.util.UUID;

/*
ChargeTime.eu - Java-OCA-OCPP
Copyright (C) 2017 Emil Christopher Solli Melar <emil@iconsultable.no>
Copyright (C) 2019 Kevin Raddatz <kevin.raddatz@valtech-mobility.com>

MIT License

Copyright (C) 2017 Emil Christopher Solli Melar
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

public class ServerRemoteTriggerProfile implements Profile {

  private HashSet<Feature> features;

  public ServerRemoteTriggerProfile() {

    features = new HashSet<>();
    features.add(new TriggerMessageFeature(null));
  }

  @Override
  public ProfileFeature[] getFeatureList() {
    return features.toArray(new ProfileFeature[0]);
  }

  @Override
  public Confirmation handleRequest(UUID sessionIndex, Request request) {
    return null;
  }

  /**
   * Create a client {@link TriggerMessageRequest} with required values.
   *
   * @param triggerMessageRequestType {@link TriggerMessageRequestType}
   * @return an instance of {@link TriggerMessageRequest}
   * @see TriggerMessageRequest
   * @see TriggerMessageFeature
   */
  public TriggerMessageRequest createTriggerMessageRequest(
      TriggerMessageRequestType triggerMessageRequestType) {
    return createTriggerMessageRequest(triggerMessageRequestType, null);
  }

  /**
   * Create a client {@link TriggerMessageRequest} with required values.
   *
   * @param triggerMessageRequestType {@link TriggerMessageRequestType}
   * @param connectorId integer. value &gt; 0
   * @return an instance of {@link TriggerMessageRequest}
   * @see TriggerMessageRequest
   * @see TriggerMessageFeature
   */
  public TriggerMessageRequest createTriggerMessageRequest(
      TriggerMessageRequestType triggerMessageRequestType, Integer connectorId) {
    TriggerMessageRequest request = new TriggerMessageRequest(triggerMessageRequestType);
    request.setConnectorId(connectorId);
    return request;
  }
}
