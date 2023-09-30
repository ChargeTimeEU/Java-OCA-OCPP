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

package eu.chargetime.ocpp.v201.model.types;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.utilities.MoreObjects;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * VPN
 *
 * <p>VPN Configuration settings
 */
public final class VPN {
  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * VPN. Server. URI
   *
   * <p>VPN Server Address
   */
  private String server;

  /**
   * VPN. User. User Name
   *
   * <p>VPN User
   */
  private String user;

  /**
   * VPN. Group. Group Name
   *
   * <p>VPN group.
   */
  @Nullable private String group;

  /**
   * VPN. Password. Password
   *
   * <p>VPN Password.
   */
  private String password;

  /**
   * VPN. Key. VPN Key
   *
   * <p>VPN shared secret.
   */
  private String key;

  /**
   * VPN. Type. VPN Code
   *
   * <p>Type of VPN
   */
  private VPNEnum type;

  /**
   * Constructor for the VPN class
   *
   * @param server VPN Server Address
   * @param user VPN User
   * @param password VPN Password.
   * @param key VPN shared secret.
   * @param type Type of VPN
   */
  public VPN(String server, String user, String password, String key, VPNEnum type) {
    setServer(server);
    setUser(user);
    setPassword(password);
    setKey(key);
    setType(type);
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
  public VPN withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  /**
   * Gets VPN Server Address
   *
   * @return VPN Server Address
   */
  public String getServer() {
    return server;
  }

  /**
   * Sets VPN Server Address
   *
   * @param server VPN Server Address
   */
  public void setServer(String server) {
    if (!isValidServer(server)) {
      throw new PropertyConstraintException(server, "server is invalid");
    }
    this.server = server;
  }

  /**
   * Returns whether the given server is valid
   *
   * @param server the server to check the validity of
   * @return {@code true} if server is valid, {@code false} if not
   */
  private boolean isValidServer(String server) {
    return server != null && server.length() <= 512;
  }

  /**
   * Gets VPN User
   *
   * @return VPN User
   */
  public String getUser() {
    return user;
  }

  /**
   * Sets VPN User
   *
   * @param user VPN User
   */
  public void setUser(String user) {
    if (!isValidUser(user)) {
      throw new PropertyConstraintException(user, "user is invalid");
    }
    this.user = user;
  }

  /**
   * Returns whether the given user is valid
   *
   * @param user the user to check the validity of
   * @return {@code true} if user is valid, {@code false} if not
   */
  private boolean isValidUser(String user) {
    return user != null && user.length() <= 20;
  }

  /**
   * Gets VPN group.
   *
   * @return VPN group
   */
  @Nullable
  public String getGroup() {
    return group;
  }

  /**
   * Sets VPN group.
   *
   * @param group VPN group
   */
  public void setGroup(@Nullable String group) {
    if (!isValidGroup(group)) {
      throw new PropertyConstraintException(group, "group is invalid");
    }
    this.group = group;
  }

  /**
   * Returns whether the given group is valid
   *
   * @param group the group to check the validity of
   * @return {@code true} if group is valid, {@code false} if not
   */
  private boolean isValidGroup(@Nullable String group) {
    return group == null || group.length() <= 20;
  }

  /**
   * Adds VPN group.
   *
   * @param group VPN group
   * @return this
   */
  public VPN withGroup(@Nullable String group) {
    setGroup(group);
    return this;
  }

  /**
   * Gets VPN Password.
   *
   * @return VPN Password
   */
  public String getPassword() {
    return password;
  }

  /**
   * Sets VPN Password.
   *
   * @param password VPN Password
   */
  public void setPassword(String password) {
    if (!isValidPassword(password)) {
      throw new PropertyConstraintException(password, "password is invalid");
    }
    this.password = password;
  }

  /**
   * Returns whether the given password is valid
   *
   * @param password the password to check the validity of
   * @return {@code true} if password is valid, {@code false} if not
   */
  private boolean isValidPassword(String password) {
    return password != null && password.length() <= 20;
  }

  /**
   * Gets VPN shared secret.
   *
   * @return VPN shared secret
   */
  public String getKey() {
    return key;
  }

  /**
   * Sets VPN shared secret.
   *
   * @param key VPN shared secret
   */
  public void setKey(String key) {
    if (!isValidKey(key)) {
      throw new PropertyConstraintException(key, "key is invalid");
    }
    this.key = key;
  }

  /**
   * Returns whether the given key is valid
   *
   * @param key the key to check the validity of
   * @return {@code true} if key is valid, {@code false} if not
   */
  private boolean isValidKey(String key) {
    return key != null && key.length() <= 255;
  }

  /**
   * Gets type of VPN
   *
   * @return Type of VPN
   */
  public VPNEnum getType() {
    return type;
  }

  /**
   * Sets type of VPN
   *
   * @param type Type of VPN
   */
  public void setType(VPNEnum type) {
    if (!isValidType(type)) {
      throw new PropertyConstraintException(type, "type is invalid");
    }
    this.type = type;
  }

  /**
   * Returns whether the given type is valid
   *
   * @param type the type to check the validity of
   * @return {@code true} if type is valid, {@code false} if not
   */
  private boolean isValidType(VPNEnum type) {
    return type != null;
  }

  public boolean validate() {
    return isValidCustomData(customData)
        && isValidServer(server)
        && isValidUser(user)
        && isValidGroup(group)
        && isValidPassword(password)
        && isValidKey(key)
        && isValidType(type);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VPN that = (VPN) o;
    return Objects.equals(customData, that.customData)
        && Objects.equals(server, that.server)
        && Objects.equals(user, that.user)
        && Objects.equals(group, that.group)
        && Objects.equals(password, that.password)
        && Objects.equals(key, that.key)
        && Objects.equals(type, that.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customData, server, user, group, password, key, type);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("server", server)
        .add("user", user)
        .add("group", group)
        .add("password", password)
        .add("key", key)
        .add("type", type)
        .add("isValid", validate())
        .toString();
  }
}
