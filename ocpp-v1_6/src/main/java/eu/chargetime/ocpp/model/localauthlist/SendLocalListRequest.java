package eu.chargetime.ocpp.model.localauthlist;

/*
 * ChargeTime.eu - Java-OCA-OCPP
 *
 * MIT License
 *
 * Copyright (C) 2016-2018 Thomas Volden <tv@chargetime.eu>
 * Copyright (C) 2019 Kevin Raddatz <kevin.raddatz@valtech-mobility.com>
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

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.utilities.MoreObjects;
import java.util.Arrays;
import java.util.Objects;

public class SendLocalListRequest implements Request {

  private Integer listVersion = 0;
  private AuthorizationData[] localAuthorizationList = null;
  private UpdateType updateType = null;

  /**
   * @deprecated use {@link #SendLocalListRequest(Integer, UpdateType)} to be sure to set required
   *     fields
   */
  @Deprecated
  public SendLocalListRequest() {}

  /**
   * Handle required fields.
   *
   * @param listVersion, the version number of the list, see {@link #setListVersion(Integer)}
   * @param updateType, {@link UpdateType}}, see {@link #setUpdateType(UpdateType)}
   */
  public SendLocalListRequest(Integer listVersion, UpdateType updateType) {
    this.listVersion = listVersion;
    this.updateType = updateType;
  }

  /**
   * In case of a full update this is the version number of the full list. In case of a differential
   * update it is the version number of the list after the update has been applied.
   *
   * @return Integer, the version number of the list
   */
  public Integer getListVersion() {
    return listVersion;
  }

  /**
   * Required. In case of a full update this is the version number of the full list. In case of a
   * differential update it is the version number of the list after the update has been applied.
   *
   * @param listVersion, the version number of the list
   */
  public void setListVersion(Integer listVersion) {
    if (listVersion < 1) {
      throw new PropertyConstraintException(listVersion, "listVersion must be > 0");
    }
    this.listVersion = listVersion;
  }

  /**
   * In case of a full update this contains the list of values that form the new local authorization
   * list. In case of a differential update it contains the changes to be applied to the local
   * authorization list in the Charge Point.
   *
   * @return Array of {@link AuthorizationData}
   */
  public AuthorizationData[] getLocalAuthorizationList() {
    return localAuthorizationList;
  }

  /**
   * Optional. In case of a full update this contains the list of values that form the new local
   * authorization list. In case of a differential update it contains the changes to be applied to
   * the local authorization list in the Charge Point.
   *
   * @param localAuthorizationList, Array of {@link AuthorizationData}
   */
  public void setLocalAuthorizationList(AuthorizationData[] localAuthorizationList) {
    this.localAuthorizationList = localAuthorizationList;
  }

  /**
   * Required. This contains the type of update (full or differential) of this request.
   *
   * @return {@link UpdateType}
   */
  public UpdateType getUpdateType() {
    return updateType;
  }

  /**
   * Required. This contains the type of update (full or differential) of this request.
   *
   * @param updateType, {@link UpdateType}}
   */
  public void setUpdateType(UpdateType updateType) {
    this.updateType = updateType;
  }

  @Override
  public boolean validate() {
    boolean valid = listVersion != null && (listVersion >= 1) && (updateType != null);

    if (localAuthorizationList != null) {
      for (AuthorizationData data : localAuthorizationList) {
        valid &= data.validate();
      }
    }

    return valid;
  }

  @Override
  public boolean transactionRelated() {
    return false;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SendLocalListRequest that = (SendLocalListRequest) o;
    return Objects.equals(listVersion, that.listVersion)
        && Arrays.equals(localAuthorizationList, that.localAuthorizationList)
        && updateType == that.updateType;
  }

  @Override
  public int hashCode() {
    return Objects.hash(listVersion, localAuthorizationList, updateType);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("listVersion", listVersion)
        .add("localAuthorizationList", localAuthorizationList)
        .add("updateType", updateType)
        .add("isValid", validate())
        .toString();
  }
}
