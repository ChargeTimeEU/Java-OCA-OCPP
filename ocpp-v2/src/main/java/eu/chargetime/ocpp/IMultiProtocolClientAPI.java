/*
 * ChargeTime.eu - Java-OCA-OCPP
 *
 * MIT License
 *
 * Copyright (C) 2023 Robert Schlabbach <robert.schlabbach@ubitricity.com>
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

import eu.chargetime.ocpp.feature.function.Function;
import eu.chargetime.ocpp.feature.profile.Profile;

public interface IMultiProtocolClientAPI extends IClientAPI {
  /**
   * Adds an OCPP 1.x profile implementation for a given protocol version
   *
   * @param protocolVersion the {@link ProtocolVersion}
   * @param profile the {@link Profile} implementation to add
   */
  void addFeatureProfile(ProtocolVersion protocolVersion, Profile profile);

  /**
   * Adds an OCPP 2.x function implementation for a given protocol version
   *
   * @param protocolVersion the {@link ProtocolVersion}
   * @param function the {@link Function} implementation to add
   */
  void addFunction(ProtocolVersion protocolVersion, Function function);

  /**
   * Returns the protocol version selected by the server
   *
   * @return the {@link ProtocolVersion} selected by the server, or {@code null} if not connected
   */
  ProtocolVersion getProtocolVersion();
}
