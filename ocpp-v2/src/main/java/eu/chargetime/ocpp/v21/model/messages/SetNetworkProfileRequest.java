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
import eu.chargetime.ocpp.v21.model.types.NetworkConnectionProfile;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * SetNetworkProfileRequest
 *
 * <p>OCPP 2.1 Edition 1 (c) OCA, Creative Commons Attribution-NoDerivatives 4.0 International
 * Public License
 */
public final class SetNetworkProfileRequest extends RequestWithId {
  /** Slot in which the configuration should be stored. */
  private Integer configurationSlot;

  /**
   * The NetworkConnectionProfile defines the functional and technical parameters of a communication
   * link.
   */
  private NetworkConnectionProfile connectionData;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the SetNetworkProfileRequest class
   *
   * @param configurationSlot Slot in which the configuration should be stored.
   * @param connectionData The NetworkConnectionProfile defines the functional and technical
   *     parameters of a communication link.
   */
  public SetNetworkProfileRequest(
      Integer configurationSlot, NetworkConnectionProfile connectionData) {
    setConfigurationSlot(configurationSlot);
    setConnectionData(connectionData);
  }

  /**
   * Gets slot in which the configuration should be stored.
   *
   * @return Slot in which the configuration should be stored
   */
  public Integer getConfigurationSlot() {
    return configurationSlot;
  }

  /**
   * Sets slot in which the configuration should be stored.
   *
   * @param configurationSlot Slot in which the configuration should be stored
   */
  public void setConfigurationSlot(Integer configurationSlot) {
    if (!isValidConfigurationSlot(configurationSlot)) {
      throw new PropertyConstraintException(configurationSlot, "configurationSlot is invalid");
    }
    this.configurationSlot = configurationSlot;
  }

  /**
   * Returns whether the given configurationSlot is valid
   *
   * @param configurationSlot the configurationSlot to check the validity of
   * @return {@code true} if configurationSlot is valid, {@code false} if not
   */
  private boolean isValidConfigurationSlot(Integer configurationSlot) {
    return configurationSlot != null;
  }

  /**
   * Gets the NetworkConnectionProfile defines the functional and technical parameters of a
   * communication link.
   *
   * @return The NetworkConnectionProfile defines the functional and technical parameters of a
   *     communication link
   */
  public NetworkConnectionProfile getConnectionData() {
    return connectionData;
  }

  /**
   * Sets the NetworkConnectionProfile defines the functional and technical parameters of a
   * communication link.
   *
   * @param connectionData The NetworkConnectionProfile defines the functional and technical
   *     parameters of a communication link
   */
  public void setConnectionData(NetworkConnectionProfile connectionData) {
    if (!isValidConnectionData(connectionData)) {
      throw new PropertyConstraintException(connectionData, "connectionData is invalid");
    }
    this.connectionData = connectionData;
  }

  /**
   * Returns whether the given connectionData is valid
   *
   * @param connectionData the connectionData to check the validity of
   * @return {@code true} if connectionData is valid, {@code false} if not
   */
  private boolean isValidConnectionData(NetworkConnectionProfile connectionData) {
    return connectionData != null && connectionData.validate();
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
  public SetNetworkProfileRequest withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  @Override
  public boolean validate() {
    return isValidConfigurationSlot(configurationSlot)
        && isValidConnectionData(connectionData)
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
    SetNetworkProfileRequest that = (SetNetworkProfileRequest) o;
    return Objects.equals(configurationSlot, that.configurationSlot)
        && Objects.equals(connectionData, that.connectionData)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(configurationSlot, connectionData, customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("configurationSlot", configurationSlot)
        .add("connectionData", connectionData)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
