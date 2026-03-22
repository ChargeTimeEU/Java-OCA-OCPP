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
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Objects;
import javax.annotation.Nullable;

/** Shows assignment of tariffs to EVSE or IdToken. */
public final class TariffAssignment {
  /** Tariff id. */
  private String tariffId;

  /** Kind of tariff (driver/default) */
  private TariffKindEnum tariffKind;

  /** Date/time when this tariff become active. */
  @Nullable private ZonedDateTime validFrom;

  /** evseIds */
  @Nullable private Integer[] evseIds;

  /** IdTokens related to tariff */
  @Nullable private String[] idTokens;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the TariffAssignment class
   *
   * @param tariffId Tariff id.
   * @param tariffKind Kind of tariff (driver/default)
   */
  public TariffAssignment(String tariffId, TariffKindEnum tariffKind) {
    setTariffId(tariffId);
    setTariffKind(tariffKind);
  }

  /**
   * Gets tariff id.
   *
   * @return Tariff id
   */
  public String getTariffId() {
    return tariffId;
  }

  /**
   * Sets tariff id.
   *
   * @param tariffId Tariff id
   */
  public void setTariffId(String tariffId) {
    if (!isValidTariffId(tariffId)) {
      throw new PropertyConstraintException(tariffId, "tariffId is invalid");
    }
    this.tariffId = tariffId;
  }

  /**
   * Returns whether the given tariffId is valid
   *
   * @param tariffId the tariffId to check the validity of
   * @return {@code true} if tariffId is valid, {@code false} if not
   */
  private boolean isValidTariffId(String tariffId) {
    return tariffId != null && tariffId.length() <= 60;
  }

  /**
   * Gets kind of tariff (driver/default)
   *
   * @return Kind of tariff (driver/default)
   */
  public TariffKindEnum getTariffKind() {
    return tariffKind;
  }

  /**
   * Sets kind of tariff (driver/default)
   *
   * @param tariffKind Kind of tariff (driver/default)
   */
  public void setTariffKind(TariffKindEnum tariffKind) {
    if (!isValidTariffKind(tariffKind)) {
      throw new PropertyConstraintException(tariffKind, "tariffKind is invalid");
    }
    this.tariffKind = tariffKind;
  }

  /**
   * Returns whether the given tariffKind is valid
   *
   * @param tariffKind the tariffKind to check the validity of
   * @return {@code true} if tariffKind is valid, {@code false} if not
   */
  private boolean isValidTariffKind(TariffKindEnum tariffKind) {
    return tariffKind != null;
  }

  /**
   * Gets date/time when this tariff become active.
   *
   * @return Date/time when this tariff become active
   */
  @Nullable
  public ZonedDateTime getValidFrom() {
    return validFrom;
  }

  /**
   * Sets date/time when this tariff become active.
   *
   * @param validFrom Date/time when this tariff become active
   */
  public void setValidFrom(@Nullable ZonedDateTime validFrom) {
    this.validFrom = validFrom;
  }

  /**
   * Adds date/time when this tariff become active.
   *
   * @param validFrom Date/time when this tariff become active
   * @return this
   */
  public TariffAssignment withValidFrom(@Nullable ZonedDateTime validFrom) {
    setValidFrom(validFrom);
    return this;
  }

  /**
   * Gets evseIds
   *
   * @return evseIds
   */
  @Nullable
  public Integer[] getEvseIds() {
    return evseIds;
  }

  /**
   * Sets evseIds
   *
   * @param evseIds evseIds
   */
  public void setEvseIds(@Nullable Integer[] evseIds) {
    if (!isValidEvseIds(evseIds)) {
      throw new PropertyConstraintException(evseIds, "evseIds is invalid");
    }
    this.evseIds = evseIds;
  }

  /**
   * Returns whether the given evseIds is valid
   *
   * @param evseIds the evseIds to check the validity of
   * @return {@code true} if evseIds is valid, {@code false} if not
   */
  private boolean isValidEvseIds(@Nullable Integer[] evseIds) {
    return evseIds == null || (evseIds.length >= 1);
  }

  /**
   * Adds evseIds
   *
   * @param evseIds evseIds
   * @return this
   */
  public TariffAssignment withEvseIds(@Nullable Integer[] evseIds) {
    setEvseIds(evseIds);
    return this;
  }

  /**
   * Gets idTokens related to tariff
   *
   * @return IdTokens related to tariff
   */
  @Nullable
  public String[] getIdTokens() {
    return idTokens;
  }

  /**
   * Sets idTokens related to tariff
   *
   * @param idTokens IdTokens related to tariff
   */
  public void setIdTokens(@Nullable String[] idTokens) {
    if (!isValidIdTokens(idTokens)) {
      throw new PropertyConstraintException(idTokens, "idTokens is invalid");
    }
    this.idTokens = idTokens;
  }

  /**
   * Returns whether the given idTokens is valid
   *
   * @param idTokens the idTokens to check the validity of
   * @return {@code true} if idTokens is valid, {@code false} if not
   */
  private boolean isValidIdTokens(@Nullable String[] idTokens) {
    return idTokens == null
        || (idTokens.length >= 1 && Arrays.stream(idTokens).allMatch(item -> item.length() <= 255));
  }

  /**
   * Adds idTokens related to tariff
   *
   * @param idTokens IdTokens related to tariff
   * @return this
   */
  public TariffAssignment withIdTokens(@Nullable String[] idTokens) {
    setIdTokens(idTokens);
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
  public TariffAssignment withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  public boolean validate() {
    return isValidTariffId(tariffId)
        && isValidTariffKind(tariffKind)
        && isValidEvseIds(evseIds)
        && isValidIdTokens(idTokens)
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
    TariffAssignment that = (TariffAssignment) o;
    return Objects.equals(tariffId, that.tariffId)
        && Objects.equals(tariffKind, that.tariffKind)
        && Objects.equals(validFrom, that.validFrom)
        && Arrays.equals(evseIds, that.evseIds)
        && Arrays.equals(idTokens, that.idTokens)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        tariffId,
        tariffKind,
        validFrom,
        Arrays.hashCode(evseIds),
        Arrays.hashCode(idTokens),
        customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("tariffId", tariffId)
        .add("tariffKind", tariffKind)
        .add("validFrom", validFrom)
        .add("evseIds", evseIds)
        .add("idTokens", idTokens)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
