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

import java.util.HashMap;
import java.util.Map;

/** enum of protocol versions along with their registered WebSocket sub-protocol names */
public enum ProtocolVersion {
  OCPP1_6("ocpp1.6"),
  OCPP2_0_1("ocpp2.0.1");

  private static final Map<String, ProtocolVersion> MAP = new HashMap<>();

  static {
    for (ProtocolVersion protocolVersion : ProtocolVersion.values()) {
      MAP.put(protocolVersion.subProtocolName, protocolVersion);
    }
  }

  private final String subProtocolName;

  ProtocolVersion(String subProtocolName) {
    this.subProtocolName = subProtocolName;
  }

  public static ProtocolVersion fromSubProtocolName(String subProtocolName) {
    return MAP.get(subProtocolName);
  }

  public String getSubProtocolName() {
    return subProtocolName;
  }
}
