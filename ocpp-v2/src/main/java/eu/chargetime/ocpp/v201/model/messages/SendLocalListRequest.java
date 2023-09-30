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
import eu.chargetime.ocpp.v201.model.types.AuthorizationData;
import eu.chargetime.ocpp.v201.model.types.CustomData;
import eu.chargetime.ocpp.v201.model.types.UpdateEnum;
import java.util.Arrays;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * SendLocalListRequest
 *
 * <p>OCPP 2.0.1 FINAL
 */
public final class SendLocalListRequest extends RequestWithId {
  /** Custom data */
  @Nullable private CustomData customData;

  /** The identifier to use for authorization. */
  @Nullable private AuthorizationData[] localAuthorizationList;

  /**
   * In case of a full update this is the version number of the full list. In case of a differential
   * update it is the version number of the list after the update has been applied.
   */
  private Integer versionNumber;

  /** The type of update (full or differential) of this request. */
  private UpdateEnum updateType;

  /**
   * Constructor for the SendLocalListRequest class
   *
   * @param versionNumber In case of a full update this is the version number of the full list. In
   *     case of a differential update it is the version number of the list after the update has
   *     been applied.
   * @param updateType The type of update (full or differential) of this request.
   */
  public SendLocalListRequest(Integer versionNumber, UpdateEnum updateType) {
    setVersionNumber(versionNumber);
    setUpdateType(updateType);
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
  public SendLocalListRequest withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  /**
   * Gets the identifier to use for authorization.
   *
   * @return The identifier to use for authorization
   */
  @Nullable
  public AuthorizationData[] getLocalAuthorizationList() {
    return localAuthorizationList;
  }

  /**
   * Sets the identifier to use for authorization.
   *
   * @param localAuthorizationList The identifier to use for authorization
   */
  public void setLocalAuthorizationList(@Nullable AuthorizationData[] localAuthorizationList) {
    if (!isValidLocalAuthorizationList(localAuthorizationList)) {
      throw new PropertyConstraintException(
          localAuthorizationList, "localAuthorizationList is invalid");
    }
    this.localAuthorizationList = localAuthorizationList;
  }

  /**
   * Returns whether the given localAuthorizationList is valid
   *
   * @param localAuthorizationList the localAuthorizationList to check the validity of
   * @return {@code true} if localAuthorizationList is valid, {@code false} if not
   */
  private boolean isValidLocalAuthorizationList(
      @Nullable AuthorizationData[] localAuthorizationList) {
    return localAuthorizationList == null
        || (localAuthorizationList.length >= 1
            && Arrays.stream(localAuthorizationList).allMatch(item -> item.validate()));
  }

  /**
   * Adds the identifier to use for authorization.
   *
   * @param localAuthorizationList The identifier to use for authorization
   * @return this
   */
  public SendLocalListRequest withLocalAuthorizationList(
      @Nullable AuthorizationData[] localAuthorizationList) {
    setLocalAuthorizationList(localAuthorizationList);
    return this;
  }

  /**
   * Gets in case of a full update this is the version number of the full list. In case of a
   * differential update it is the version number of the list after the update has been applied.
   *
   * @return In case of a full update this is the version number of the full list
   */
  public Integer getVersionNumber() {
    return versionNumber;
  }

  /**
   * Sets in case of a full update this is the version number of the full list. In case of a
   * differential update it is the version number of the list after the update has been applied.
   *
   * @param versionNumber In case of a full update this is the version number of the full list
   */
  public void setVersionNumber(Integer versionNumber) {
    if (!isValidVersionNumber(versionNumber)) {
      throw new PropertyConstraintException(versionNumber, "versionNumber is invalid");
    }
    this.versionNumber = versionNumber;
  }

  /**
   * Returns whether the given versionNumber is valid
   *
   * @param versionNumber the versionNumber to check the validity of
   * @return {@code true} if versionNumber is valid, {@code false} if not
   */
  private boolean isValidVersionNumber(Integer versionNumber) {
    return versionNumber != null;
  }

  /**
   * Gets the type of update (full or differential) of this request.
   *
   * @return The type of update (full or differential) of this request
   */
  public UpdateEnum getUpdateType() {
    return updateType;
  }

  /**
   * Sets the type of update (full or differential) of this request.
   *
   * @param updateType The type of update (full or differential) of this request
   */
  public void setUpdateType(UpdateEnum updateType) {
    if (!isValidUpdateType(updateType)) {
      throw new PropertyConstraintException(updateType, "updateType is invalid");
    }
    this.updateType = updateType;
  }

  /**
   * Returns whether the given updateType is valid
   *
   * @param updateType the updateType to check the validity of
   * @return {@code true} if updateType is valid, {@code false} if not
   */
  private boolean isValidUpdateType(UpdateEnum updateType) {
    return updateType != null;
  }

  @Override
  public boolean validate() {
    return isValidCustomData(customData)
        && isValidLocalAuthorizationList(localAuthorizationList)
        && isValidVersionNumber(versionNumber)
        && isValidUpdateType(updateType);
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
    SendLocalListRequest that = (SendLocalListRequest) o;
    return Objects.equals(customData, that.customData)
        && Arrays.equals(localAuthorizationList, that.localAuthorizationList)
        && Objects.equals(versionNumber, that.versionNumber)
        && Objects.equals(updateType, that.updateType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        customData, Arrays.hashCode(localAuthorizationList), versionNumber, updateType);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("localAuthorizationList", localAuthorizationList)
        .add("versionNumber", versionNumber)
        .add("updateType", updateType)
        .add("isValid", validate())
        .toString();
  }
}
