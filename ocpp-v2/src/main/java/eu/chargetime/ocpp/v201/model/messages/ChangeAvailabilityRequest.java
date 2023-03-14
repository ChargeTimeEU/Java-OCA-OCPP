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

package eu.chargetime.ocpp.v201.model.messages;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.model.RequestWithId;
import eu.chargetime.ocpp.utilities.MoreObjects;
import eu.chargetime.ocpp.v201.model.types.CustomData;
import eu.chargetime.ocpp.v201.model.types.EVSE;
import eu.chargetime.ocpp.v201.model.types.OperationalStatusEnum;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * ChangeAvailabilityRequest
 *
 * <p>OCPP 2.0.1 FINAL
 */
public final class ChangeAvailabilityRequest extends RequestWithId {
  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * EVSE
   *
   * <p>Electric Vehicle Supply Equipment
   */
  @Nullable private EVSE evse;

  /** The type of availability change that the Charging Station should perform. */
  private OperationalStatusEnum operationalStatus;

  /**
   * Constructor for the ChangeAvailabilityRequest class
   *
   * @param operationalStatus The type of availability change that the Charging Station should
   *     perform.
   */
  public ChangeAvailabilityRequest(OperationalStatusEnum operationalStatus) {
    setOperationalStatus(operationalStatus);
  }

  /**
   * Gets custom data
   *
   * @return Custom data
   */
  @Nullable
  public CustomData getCustomData() {
    return customData;
  }

  /**
   * Sets custom data
   *
   * @param customData Custom data
   */
  public void setCustomData(@Nullable CustomData customData) {
    if (!isValidCustomData(customData)) {
      throw new PropertyConstraintException(customData, "customData is invalid");
    }
    this.customData = customData;
  }

  /**
   * Returns whether the given customData is valid
   *
   * @param customData the customData to check the validity of
   * @return {@code true} if customData is valid, {@code false} if not
   */
  private boolean isValidCustomData(@Nullable CustomData customData) {
    return customData == null || customData.validate();
  }

  /**
   * Adds custom data
   *
   * @param customData Custom data
   * @return this
   */
  public ChangeAvailabilityRequest withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  /**
   * Gets electric Vehicle Supply Equipment
   *
   * @return Electric Vehicle Supply Equipment
   */
  @Nullable
  public EVSE getEvse() {
    return evse;
  }

  /**
   * Sets electric Vehicle Supply Equipment
   *
   * @param evse Electric Vehicle Supply Equipment
   */
  public void setEvse(@Nullable EVSE evse) {
    if (!isValidEvse(evse)) {
      throw new PropertyConstraintException(evse, "evse is invalid");
    }
    this.evse = evse;
  }

  /**
   * Returns whether the given evse is valid
   *
   * @param evse the evse to check the validity of
   * @return {@code true} if evse is valid, {@code false} if not
   */
  private boolean isValidEvse(@Nullable EVSE evse) {
    return evse == null || evse.validate();
  }

  /**
   * Adds electric Vehicle Supply Equipment
   *
   * @param evse Electric Vehicle Supply Equipment
   * @return this
   */
  public ChangeAvailabilityRequest withEvse(@Nullable EVSE evse) {
    setEvse(evse);
    return this;
  }

  /**
   * Gets the type of availability change that the Charging Station should perform.
   *
   * @return The type of availability change that the Charging Station should perform
   */
  public OperationalStatusEnum getOperationalStatus() {
    return operationalStatus;
  }

  /**
   * Sets the type of availability change that the Charging Station should perform.
   *
   * @param operationalStatus The type of availability change that the Charging Station should
   *     perform
   */
  public void setOperationalStatus(OperationalStatusEnum operationalStatus) {
    if (!isValidOperationalStatus(operationalStatus)) {
      throw new PropertyConstraintException(operationalStatus, "operationalStatus is invalid");
    }
    this.operationalStatus = operationalStatus;
  }

  /**
   * Returns whether the given operationalStatus is valid
   *
   * @param operationalStatus the operationalStatus to check the validity of
   * @return {@code true} if operationalStatus is valid, {@code false} if not
   */
  private boolean isValidOperationalStatus(OperationalStatusEnum operationalStatus) {
    return operationalStatus != null;
  }

  @Override
  public boolean validate() {
    return isValidCustomData(customData)
        && isValidEvse(evse)
        && isValidOperationalStatus(operationalStatus);
  }

  @Override
  public boolean transactionRelated() {
    return false;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ChangeAvailabilityRequest that = (ChangeAvailabilityRequest) o;
    return Objects.equals(customData, that.customData)
        && Objects.equals(evse, that.evse)
        && Objects.equals(operationalStatus, that.operationalStatus);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customData, evse, operationalStatus);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("evse", evse)
        .add("operationalStatus", operationalStatus)
        .add("isValid", validate())
        .toString();
  }
}
