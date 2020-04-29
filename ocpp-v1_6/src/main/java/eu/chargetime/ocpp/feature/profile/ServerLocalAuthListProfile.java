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

import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.feature.GetLocalListVersionFeature;
import eu.chargetime.ocpp.feature.ProfileFeature;
import eu.chargetime.ocpp.feature.SendLocalListFeature;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.localauthlist.*;
import java.util.HashSet;
import java.util.UUID;

public class ServerLocalAuthListProfile implements Profile {

  private HashSet<Feature> featureList;

  public ServerLocalAuthListProfile() {
    featureList = new HashSet<>();
    featureList.add(new GetLocalListVersionFeature(null));
    featureList.add(new SendLocalListFeature(null));
  }

  @Override
  public ProfileFeature[] getFeatureList() {
    return featureList.toArray(new ProfileFeature[0]);
  }

  @Override
  public Confirmation handleRequest(UUID sessionIndex, Request request) {
    return null;
  }

  /**
   * Create a client {@link SendLocalListRequest} with required values.
   *
   * @param listVersion required, version number of the list.
   * @param updateType required, type of update
   * @return an instance of {@link SendLocalListConfirmation}.
   * @see SendLocalListRequest
   * @see SendLocalListFeature
   */
  public SendLocalListRequest createSendLocalListRequest(int listVersion, UpdateType updateType) {
    return new SendLocalListRequest(listVersion, updateType);
  }

  /**
   * Create a client {@link GetLocalListVersionRequest} with required values.
   *
   * @return an instance of {@link GetLocalListVersionConfirmation}.
   * @see GetLocalListVersionRequest
   * @see GetLocalListVersionFeature
   */
  public GetLocalListVersionRequest createGetLocalListVersionRequest() {
    return new GetLocalListVersionRequest();
  }
}
