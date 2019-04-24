package eu.chargetime.ocpp.model.basic.types;
/*
   ChargeTime.eu - Java-OCA-OCPP

   MIT License

   Copyright (C) 2018 Thomas Volden <tv@chargetime.eu>

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

import eu.chargetime.ocpp.model.Validatable;
import eu.chargetime.ocpp.utilities.MoreObjects;
import java.util.Objects;

/** Electric Vehicle Supply Equipment */
public class EVSEType implements Validatable {
  private int id;
  private Integer connectorId;

  /**
   * EVSE Identifier. When 0, the ID references the Charging Station as a whole.
   *
   * @return integer
   */
  public int getId() {
    return id;
  }

  /**
   * Required. EVSE Identifier. When 0, the ID references the Charging Station as a whole.
   *
   * @param id integer
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * An id to designate a specific connector (on an EVSE) by connector index number.
   *
   * @return integer
   */
  public Integer getConnectorId() {
    return connectorId;
  }

  /**
   * Optional. An id to designate a specific connector (on an EVSE) by connector index number.
   *
   * @param connectorId integer
   */
  public void setConnectorId(Integer connectorId) {
    this.connectorId = connectorId;
  }

  @Override
  public boolean validate() {
    return true;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    EVSEType that = (EVSEType) o;
    return Objects.equals(id, that.id) && Objects.equals(connectorId, that.connectorId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, connectorId);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("id", id)
        .add("connectorId", connectorId)
        .toString();
  }
}
