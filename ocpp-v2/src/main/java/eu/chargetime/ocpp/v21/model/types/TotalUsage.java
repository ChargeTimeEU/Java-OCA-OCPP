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

package eu.chargetime.ocpp.v21.model.types;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.utilities.MoreObjects;
import java.util.Objects;
import javax.annotation.Nullable;

/** The calculated usage of energy, charging time and idle time during a transaction. */
public final class TotalUsage {
  /** energy */
  private Double energy;

  /**
   * Total duration of the charging session (including the duration of charging and not charging),
   * in seconds.
   */
  private Integer chargingTime;

  /**
   * Total duration of the charging session where the EV was not charging (no energy was transferred
   * between EVSE and EV), in seconds.
   */
  private Integer idleTime;

  /** Total time of reservation in seconds. */
  @Nullable private Integer reservationTime;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the TotalUsage class
   *
   * @param energy energy
   * @param chargingTime Total duration of the charging session (including the duration of charging
   *     and not charging), in seconds.
   * @param idleTime Total duration of the charging session where the EV was not charging (no energy
   *     was transferred between EVSE and EV), in seconds.
   */
  public TotalUsage(Double energy, Integer chargingTime, Integer idleTime) {
    setEnergy(energy);
    setChargingTime(chargingTime);
    setIdleTime(idleTime);
  }

  /**
   * Gets energy
   *
   * @return energy
   */
  public Double getEnergy() {
    return energy;
  }

  /**
   * Sets energy
   *
   * @param energy energy
   */
  public void setEnergy(Double energy) {
    if (!isValidEnergy(energy)) {
      throw new PropertyConstraintException(energy, "energy is invalid");
    }
    this.energy = energy;
  }

  /**
   * Returns whether the given energy is valid
   *
   * @param energy the energy to check the validity of
   * @return {@code true} if energy is valid, {@code false} if not
   */
  private boolean isValidEnergy(Double energy) {
    return energy != null;
  }

  /**
   * Gets total duration of the charging session (including the duration of charging and not
   * charging), in seconds.
   *
   * @return Total duration of the charging session (including the duration of charging and not
   *     charging), in seconds
   */
  public Integer getChargingTime() {
    return chargingTime;
  }

  /**
   * Sets total duration of the charging session (including the duration of charging and not
   * charging), in seconds.
   *
   * @param chargingTime Total duration of the charging session (including the duration of charging
   *     and not charging), in seconds
   */
  public void setChargingTime(Integer chargingTime) {
    if (!isValidChargingTime(chargingTime)) {
      throw new PropertyConstraintException(chargingTime, "chargingTime is invalid");
    }
    this.chargingTime = chargingTime;
  }

  /**
   * Returns whether the given chargingTime is valid
   *
   * @param chargingTime the chargingTime to check the validity of
   * @return {@code true} if chargingTime is valid, {@code false} if not
   */
  private boolean isValidChargingTime(Integer chargingTime) {
    return chargingTime != null;
  }

  /**
   * Gets total duration of the charging session where the EV was not charging (no energy was
   * transferred between EVSE and EV), in seconds.
   *
   * @return Total duration of the charging session where the EV was not charging (no energy was
   *     transferred between EVSE and EV), in seconds
   */
  public Integer getIdleTime() {
    return idleTime;
  }

  /**
   * Sets total duration of the charging session where the EV was not charging (no energy was
   * transferred between EVSE and EV), in seconds.
   *
   * @param idleTime Total duration of the charging session where the EV was not charging (no energy
   *     was transferred between EVSE and EV), in seconds
   */
  public void setIdleTime(Integer idleTime) {
    if (!isValidIdleTime(idleTime)) {
      throw new PropertyConstraintException(idleTime, "idleTime is invalid");
    }
    this.idleTime = idleTime;
  }

  /**
   * Returns whether the given idleTime is valid
   *
   * @param idleTime the idleTime to check the validity of
   * @return {@code true} if idleTime is valid, {@code false} if not
   */
  private boolean isValidIdleTime(Integer idleTime) {
    return idleTime != null;
  }

  /**
   * Gets total time of reservation in seconds.
   *
   * @return Total time of reservation in seconds
   */
  @Nullable
  public Integer getReservationTime() {
    return reservationTime;
  }

  /**
   * Sets total time of reservation in seconds.
   *
   * @param reservationTime Total time of reservation in seconds
   */
  public void setReservationTime(@Nullable Integer reservationTime) {
    this.reservationTime = reservationTime;
  }

  /**
   * Adds total time of reservation in seconds.
   *
   * @param reservationTime Total time of reservation in seconds
   * @return this
   */
  public TotalUsage withReservationTime(@Nullable Integer reservationTime) {
    setReservationTime(reservationTime);
    return this;
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
  public TotalUsage withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  public boolean validate() {
    return isValidEnergy(energy)
        && isValidChargingTime(chargingTime)
        && isValidIdleTime(idleTime)
        && isValidCustomData(customData);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TotalUsage that = (TotalUsage) o;
    return Objects.equals(energy, that.energy)
        && Objects.equals(chargingTime, that.chargingTime)
        && Objects.equals(idleTime, that.idleTime)
        && Objects.equals(reservationTime, that.reservationTime)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(energy, chargingTime, idleTime, reservationTime, customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("energy", energy)
        .add("chargingTime", chargingTime)
        .add("idleTime", idleTime)
        .add("reservationTime", reservationTime)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
