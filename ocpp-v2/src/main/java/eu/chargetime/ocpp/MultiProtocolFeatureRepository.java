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

package eu.chargetime.ocpp;

import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.feature.function.Function;
import eu.chargetime.ocpp.feature.profile.Profile;
import eu.chargetime.ocpp.utilities.MoreObjects;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Nullable;

/** Feature repository managing the feature repositories for multiple protocol versions */
public class MultiProtocolFeatureRepository implements IFeatureRepository {

  private final Map<ProtocolVersion, FeatureRepository> featureRepositories;

  private IFeatureRepository selectedFeatureRepository;

  public MultiProtocolFeatureRepository(List<ProtocolVersion> protocolVersions) {
    featureRepositories = new HashMap<>();
    for (ProtocolVersion protocolVersion : protocolVersions) {
      featureRepositories.put(protocolVersion, new FeatureRepository(protocolVersion));
    }
    selectedFeatureRepository = null;
  }

  /* methods used by both client and server */

  public void addFeatureProfile(ProtocolVersion protocolVersion, Profile profile) {
    getFeatureRepository(protocolVersion).addFeatureProfile(profile);
  }

  public void addFeatureFunction(ProtocolVersion protocolVersion, Function function) {
    FeatureRepository featureRepository = getFeatureRepository(protocolVersion);
    for (Feature feature : function.getFeatureList()) {
      featureRepository.addFeature(feature);
    }
  }

  /* methods used by the server */

  public FeatureRepository getFeatureRepository(ProtocolVersion protocolVersion) {
    FeatureRepository featureRepository = featureRepositories.get(protocolVersion);
    if (featureRepository == null) {
      throw new IllegalArgumentException("No feature repository for protocol " + protocolVersion);
    }
    return featureRepository;
  }

  /* methods used by the client */

  public void selectProtocolVersion(@Nullable ProtocolVersion protocolVersion) {
    selectedFeatureRepository = featureRepositories.get(protocolVersion);
  }

  @Nullable
  public ProtocolVersion getProtocolVersion() {
    IFeatureRepository featureRepository = selectedFeatureRepository;
    return featureRepository != null ? featureRepository.getProtocolVersion() : null;
  }

  @Override
  public Optional<Feature> findFeature(Object needle) {
    IFeatureRepository featureRepository = selectedFeatureRepository;
    return featureRepository != null ? featureRepository.findFeature(needle) : Optional.empty();
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("featureRepositories", featureRepositories)
        .add("selectedFeatureRepository", selectedFeatureRepository)
        .toString();
  }
}
