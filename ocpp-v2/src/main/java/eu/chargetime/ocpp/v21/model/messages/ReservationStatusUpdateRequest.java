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

package eu.chargetime.ocpp.v21.model.messages;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.model.RequestWithId;
import eu.chargetime.ocpp.utilities.MoreObjects;
import eu.chargetime.ocpp.v21.model.types.CustomData;
import eu.chargetime.ocpp.v21.model.types.ReservationUpdateStatusEnum;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * ReservationStatusUpdateRequest
 *
 * <p>OCPP 2.1 Edition 1 (c) OCA, Creative Commons Attribution-NoDerivatives 4.0 International
 * Public License
 */
public final class ReservationStatusUpdateRequest extends RequestWithId {
  /** The ID of the reservation. */
  private Integer reservationId;

  /** The updated reservation status. */
  private ReservationUpdateStatusEnum reservationUpdateStatus;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the ReservationStatusUpdateRequest class
   *
   * @param reservationId The ID of the reservation.
   * @param reservationUpdateStatus The updated reservation status.
   */
  public ReservationStatusUpdateRequest(
      Integer reservationId, ReservationUpdateStatusEnum reservationUpdateStatus) {
    setReservationId(reservationId);
    setReservationUpdateStatus(reservationUpdateStatus);
  }

  /**
   * Gets the ID of the reservation.
   *
   * @return The ID of the reservation
   */
  public Integer getReservationId() {
    return reservationId;
  }

  /**
   * Sets the ID of the reservation.
   *
   * @param reservationId The ID of the reservation
   */
  public void setReservationId(Integer reservationId) {
    if (!isValidReservationId(reservationId)) {
      throw new PropertyConstraintException(reservationId, "reservationId is invalid");
    }
    this.reservationId = reservationId;
  }

  /**
   * Returns whether the given reservationId is valid
   *
   * @param reservationId the reservationId to check the validity of
   * @return {@code true} if reservationId is valid, {@code false} if not
   */
  private boolean isValidReservationId(Integer reservationId) {
    return reservationId != null && reservationId >= 0;
  }

  /**
   * Gets the updated reservation status.
   *
   * @return The updated reservation status
   */
  public ReservationUpdateStatusEnum getReservationUpdateStatus() {
    return reservationUpdateStatus;
  }

  /**
   * Sets the updated reservation status.
   *
   * @param reservationUpdateStatus The updated reservation status
   */
  public void setReservationUpdateStatus(ReservationUpdateStatusEnum reservationUpdateStatus) {
    if (!isValidReservationUpdateStatus(reservationUpdateStatus)) {
      throw new PropertyConstraintException(
          reservationUpdateStatus, "reservationUpdateStatus is invalid");
    }
    this.reservationUpdateStatus = reservationUpdateStatus;
  }

  /**
   * Returns whether the given reservationUpdateStatus is valid
   *
   * @param reservationUpdateStatus the reservationUpdateStatus to check the validity of
   * @return {@code true} if reservationUpdateStatus is valid, {@code false} if not
   */
  private boolean isValidReservationUpdateStatus(
      ReservationUpdateStatusEnum reservationUpdateStatus) {
    return reservationUpdateStatus != null;
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
  public ReservationStatusUpdateRequest withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  @Override
  public boolean validate() {
    return isValidReservationId(reservationId)
        && isValidReservationUpdateStatus(reservationUpdateStatus)
        && isValidCustomData(customData);
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
    ReservationStatusUpdateRequest that = (ReservationStatusUpdateRequest) o;
    return Objects.equals(reservationId, that.reservationId)
        && Objects.equals(reservationUpdateStatus, that.reservationUpdateStatus)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(reservationId, reservationUpdateStatus, customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("reservationId", reservationId)
        .add("reservationUpdateStatus", reservationUpdateStatus)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
