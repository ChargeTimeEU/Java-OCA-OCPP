package eu.chargetime.ocpp.model.basic.types;
/*
   ChargeTime.eu - Java-OCA-OCPP

   MIT License

   Copyright (C) 2018 Thomas Volden <tv@chargetime.eu>

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

import eu.chargetime.ocpp.model.Validatable;
import eu.chargetime.ocpp.model.validation.OCPP2PrimDatatypes;
import eu.chargetime.ocpp.model.validation.Validator;
import eu.chargetime.ocpp.model.validation.ValidatorBuilder;
import eu.chargetime.ocpp.utilities.MoreObjects;
import java.util.Objects;

/** A physical or logical component. */
public class ComponentType implements Validatable {
  private transient Validator nameValidator =
      new ValidatorBuilder().setRequired(true).addRule(OCPP2PrimDatatypes.string50()).build();
  private transient Validator instanceValidator =
      new ValidatorBuilder().addRule(OCPP2PrimDatatypes.string50()).build();

  private String name;
  private String instance;
  private EVSEType evse;

  /**
   * Name of the component. Name should be taken from the list of standardized component names
   * whenever possible. Case Insensitive. strongly advised to use Camel Case.
   *
   * @return string[0..50]
   */
  public String getName() {
    return name;
  }

  /**
   * Required. Name of the component. Name should be taken from the list of standardized component
   * names whenever possible. Case Insensitive. strongly advised to use Camel Case.
   *
   * @param name string[0..50]
   */
  public void setName(String name) {
    nameValidator.validate(name);

    this.name = name;
  }

  /**
   * Name of instance in case the component exists as multiple instances. Case Insensitive. strongly
   * advised to use Camel Case.
   *
   * @return string[0..50]
   */
  public String getInstance() {
    return instance;
  }

  /**
   * Optional. Name of instance in case the component exists as multiple instances. Case
   * Insensitive. strongly advised to use Camel Case.
   *
   * @param instance string[0..50]
   */
  public void setInstance(String instance) {
    instanceValidator.validate(instance);

    this.instance = instance;
  }

  /**
   * Specifies the EVSE when component is located at EVSE level, also specifies the connector when
   * component is located at Connector level.
   *
   * @return {@link EVSEType}
   */
  public EVSEType getEvse() {
    return evse;
  }

  /**
   * Optional. Specifies the EVSE when component is located at EVSE level, also specifies the
   * connector when component is located at Connector level.
   *
   * @param evse {@link EVSEType}
   */
  public void setEvse(EVSEType evse) {
    this.evse = evse;
  }

  @Override
  public boolean validate() {
    return nameValidator.safeValidate(name)
        && instanceValidator.safeValidate(instance)
        && (evse == null || evse.validate());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ComponentType that = (ComponentType) o;
    return Objects.equals(name, that.name)
        && Objects.equals(instance, that.instance)
        && Objects.equals(evse, that.evse);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, instance, evse);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("name", name)
        .add("instance", instance)
        .add("evse", evse)
        .toString();
  }
}
