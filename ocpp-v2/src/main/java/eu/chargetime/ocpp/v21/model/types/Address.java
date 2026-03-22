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

/** A generic address format. */
public final class Address {
  /** Name of person/company */
  private String name;

  /** Address line 1 */
  private String address1;

  /** Address line 2 */
  @Nullable private String address2;

  /** City */
  private String city;

  /** Postal code */
  @Nullable private String postalCode;

  /** Country name */
  private String country;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the Address class
   *
   * @param name Name of person/company
   * @param address1 Address line 1
   * @param city City
   * @param country Country name
   */
  public Address(String name, String address1, String city, String country) {
    setName(name);
    setAddress1(address1);
    setCity(city);
    setCountry(country);
  }

  /**
   * Gets name of person/company
   *
   * @return Name of person/company
   */
  public String getName() {
    return name;
  }

  /**
   * Sets name of person/company
   *
   * @param name Name of person/company
   */
  public void setName(String name) {
    if (!isValidName(name)) {
      throw new PropertyConstraintException(name, "name is invalid");
    }
    this.name = name;
  }

  /**
   * Returns whether the given name is valid
   *
   * @param name the name to check the validity of
   * @return {@code true} if name is valid, {@code false} if not
   */
  private boolean isValidName(String name) {
    return name != null && name.length() <= 50;
  }

  /**
   * Gets address line 1
   *
   * @return Address line 1
   */
  public String getAddress1() {
    return address1;
  }

  /**
   * Sets address line 1
   *
   * @param address1 Address line 1
   */
  public void setAddress1(String address1) {
    if (!isValidAddress1(address1)) {
      throw new PropertyConstraintException(address1, "address1 is invalid");
    }
    this.address1 = address1;
  }

  /**
   * Returns whether the given address1 is valid
   *
   * @param address1 the address1 to check the validity of
   * @return {@code true} if address1 is valid, {@code false} if not
   */
  private boolean isValidAddress1(String address1) {
    return address1 != null && address1.length() <= 100;
  }

  /**
   * Gets address line 2
   *
   * @return Address line 2
   */
  @Nullable
  public String getAddress2() {
    return address2;
  }

  /**
   * Sets address line 2
   *
   * @param address2 Address line 2
   */
  public void setAddress2(@Nullable String address2) {
    if (!isValidAddress2(address2)) {
      throw new PropertyConstraintException(address2, "address2 is invalid");
    }
    this.address2 = address2;
  }

  /**
   * Returns whether the given address2 is valid
   *
   * @param address2 the address2 to check the validity of
   * @return {@code true} if address2 is valid, {@code false} if not
   */
  private boolean isValidAddress2(@Nullable String address2) {
    return address2 == null || address2.length() <= 100;
  }

  /**
   * Adds address line 2
   *
   * @param address2 Address line 2
   * @return this
   */
  public Address withAddress2(@Nullable String address2) {
    setAddress2(address2);
    return this;
  }

  /**
   * Gets city
   *
   * @return City
   */
  public String getCity() {
    return city;
  }

  /**
   * Sets city
   *
   * @param city City
   */
  public void setCity(String city) {
    if (!isValidCity(city)) {
      throw new PropertyConstraintException(city, "city is invalid");
    }
    this.city = city;
  }

  /**
   * Returns whether the given city is valid
   *
   * @param city the city to check the validity of
   * @return {@code true} if city is valid, {@code false} if not
   */
  private boolean isValidCity(String city) {
    return city != null && city.length() <= 100;
  }

  /**
   * Gets postal code
   *
   * @return Postal code
   */
  @Nullable
  public String getPostalCode() {
    return postalCode;
  }

  /**
   * Sets postal code
   *
   * @param postalCode Postal code
   */
  public void setPostalCode(@Nullable String postalCode) {
    if (!isValidPostalCode(postalCode)) {
      throw new PropertyConstraintException(postalCode, "postalCode is invalid");
    }
    this.postalCode = postalCode;
  }

  /**
   * Returns whether the given postalCode is valid
   *
   * @param postalCode the postalCode to check the validity of
   * @return {@code true} if postalCode is valid, {@code false} if not
   */
  private boolean isValidPostalCode(@Nullable String postalCode) {
    return postalCode == null || postalCode.length() <= 20;
  }

  /**
   * Adds postal code
   *
   * @param postalCode Postal code
   * @return this
   */
  public Address withPostalCode(@Nullable String postalCode) {
    setPostalCode(postalCode);
    return this;
  }

  /**
   * Gets country name
   *
   * @return Country name
   */
  public String getCountry() {
    return country;
  }

  /**
   * Sets country name
   *
   * @param country Country name
   */
  public void setCountry(String country) {
    if (!isValidCountry(country)) {
      throw new PropertyConstraintException(country, "country is invalid");
    }
    this.country = country;
  }

  /**
   * Returns whether the given country is valid
   *
   * @param country the country to check the validity of
   * @return {@code true} if country is valid, {@code false} if not
   */
  private boolean isValidCountry(String country) {
    return country != null && country.length() <= 50;
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
  public Address withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  public boolean validate() {
    return isValidName(name)
        && isValidAddress1(address1)
        && isValidAddress2(address2)
        && isValidCity(city)
        && isValidPostalCode(postalCode)
        && isValidCountry(country)
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
    Address that = (Address) o;
    return Objects.equals(name, that.name)
        && Objects.equals(address1, that.address1)
        && Objects.equals(address2, that.address2)
        && Objects.equals(city, that.city)
        && Objects.equals(postalCode, that.postalCode)
        && Objects.equals(country, that.country)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, address1, address2, city, postalCode, country, customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("name", name)
        .add("address1", address1)
        .add("address2", address2)
        .add("city", city)
        .add("postalCode", postalCode)
        .add("country", country)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
