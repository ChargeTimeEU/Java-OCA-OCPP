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

/** EnterServiceGetType */
public final class EnterServiceGet {
  /** enterService */
  private EnterService enterService;

  /** Id of setting */
  private String id;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the EnterServiceGet class
   *
   * @param enterService enterService
   * @param id Id of setting
   */
  public EnterServiceGet(EnterService enterService, String id) {
    setEnterService(enterService);
    setId(id);
  }

  /**
   * Gets enterService
   *
   * @return enterService
   */
  public EnterService getEnterService() {
    return enterService;
  }

  /**
   * Sets enterService
   *
   * @param enterService enterService
   */
  public void setEnterService(EnterService enterService) {
    if (!isValidEnterService(enterService)) {
      throw new PropertyConstraintException(enterService, "enterService is invalid");
    }
    this.enterService = enterService;
  }

  /**
   * Returns whether the given enterService is valid
   *
   * @param enterService the enterService to check the validity of
   * @return {@code true} if enterService is valid, {@code false} if not
   */
  private boolean isValidEnterService(EnterService enterService) {
    return enterService != null && enterService.validate();
  }

  /**
   * Gets id of setting
   *
   * @return Id of setting
   */
  public String getId() {
    return id;
  }

  /**
   * Sets id of setting
   *
   * @param id Id of setting
   */
  public void setId(String id) {
    if (!isValidId(id)) {
      throw new PropertyConstraintException(id, "id is invalid");
    }
    this.id = id;
  }

  /**
   * Returns whether the given id is valid
   *
   * @param id the id to check the validity of
   * @return {@code true} if id is valid, {@code false} if not
   */
  private boolean isValidId(String id) {
    return id != null && id.length() <= 36;
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
  public EnterServiceGet withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  public boolean validate() {
    return isValidEnterService(enterService) && isValidId(id) && isValidCustomData(customData);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EnterServiceGet that = (EnterServiceGet) o;
    return Objects.equals(enterService, that.enterService)
        && Objects.equals(id, that.id)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(enterService, id, customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("enterService", enterService)
        .add("id", id)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
