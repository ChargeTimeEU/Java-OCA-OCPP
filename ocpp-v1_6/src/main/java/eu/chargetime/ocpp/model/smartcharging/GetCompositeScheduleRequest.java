package eu.chargetime.ocpp.model.smartcharging;

/*
   ChargeTime.eu - Java-OCA-OCPP

   MIT License

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

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.utilities.MoreObjects;
import java.util.Objects;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class GetCompositeScheduleRequest implements Request {

  private Integer connectorId;
  private Integer duration;
  private ChangingRateUnitType changingRateUnitType;

  /**
   * @deprecated use {@link #GetCompositeScheduleRequest(Integer, Integer)} to be sure to set
   *     required fields
   */
  @Deprecated
  public GetCompositeScheduleRequest() {}

  /**
   * Handle required fields.
   *
   * @param connectorId Integer, see {@link #setConnectorId(Integer)}
   * @param duration Integer, see {@link #setDuration(Integer)}
   */
  public GetCompositeScheduleRequest(Integer connectorId, Integer duration) {
    setConnectorId(connectorId);
    setDuration(duration);
  }

  /**
   * The ID of the Connector for which the schedule is requested. When ConnectorId=0, the Charge
   * Point will calculate the expected consumption for the grid connection.
   *
   * @return ID of the connector.
   */
  public Integer getConnectorId() {
    return connectorId;
  }

  /**
   * Required. The ID of the Connector for which the schedule is requested. When ConnectorId=0, the
   * Charge Point will calculate the expected consumption for the grid connection.
   *
   * @param connectorId Integer
   */
  @XmlElement
  public void setConnectorId(Integer connectorId) {
    if (connectorId == null || connectorId < 0) {
      throw new PropertyConstraintException(connectorId, "connectorId must be >= 0");
    }

    this.connectorId = connectorId;
  }

  /**
   * Time in seconds. length of requested schedule
   *
   * @return length of requested schedule
   */
  public Integer getDuration() {
    return duration;
  }

  /**
   * Required. Time in seconds. length of requested schedule
   *
   * @param duration Integer
   */
  @XmlElement
  public void setDuration(Integer duration) {
    this.duration = duration;
  }

  /**
   * Can be used to force a power or current profile
   *
   * @return current profile
   */
  public ChangingRateUnitType getChangingRateUnitType() {
    return changingRateUnitType;
  }

  /**
   * Optional. Can be used to force a power or current profile
   *
   * @param changingRateUnitType the {@link ChangingRateUnitType}
   */
  @XmlElement
  public void setChangingRateUnitType(ChangingRateUnitType changingRateUnitType) {
    this.changingRateUnitType = changingRateUnitType;
  }

  @Override
  public boolean validate() {
    boolean valid = connectorId != null && connectorId >= 0;
    valid &= duration != null;

    return valid;
  }

  @Override
  public boolean transactionRelated() {
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(connectorId, duration, changingRateUnitType);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("connectorId", connectorId)
        .add("duration", duration)
        .add("changingRateUnitType", changingRateUnitType)
        .add("isValid", validate())
        .toString();
  }
}
