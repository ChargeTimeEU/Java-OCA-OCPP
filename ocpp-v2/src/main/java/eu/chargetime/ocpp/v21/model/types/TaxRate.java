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

/** Tax percentage */
public final class TaxRate {
  /** Type of this tax, e.g. "Federal ", "State", for information on receipt. */
  private String type;

  /** Tax percentage */
  private Double tax;

  /**
   * Stack level for this type of tax. Default value, when absent, is 0.
   *
   * <pre>
   * stack = 0: tax on net price;
   * stack = 1: tax added on top of stack 0;
   * stack = 2: tax added on top of stack 1, etc.
   * </pre>
   */
  @Nullable private Integer stack;

  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Constructor for the TaxRate class
   *
   * @param type Type of this tax, e.g. "Federal ", "State", for information on receipt.
   * @param tax Tax percentage
   */
  public TaxRate(String type, Double tax) {
    setType(type);
    setTax(tax);
  }

  /**
   * Gets type of this tax, e.g. "Federal ", "State", for information on receipt.
   *
   * @return Type of this tax, e.g. "Federal ", "State", for information on receipt
   */
  public String getType() {
    return type;
  }

  /**
   * Sets type of this tax, e.g. "Federal ", "State", for information on receipt.
   *
   * @param type Type of this tax, e.g. "Federal ", "State", for information on receipt
   */
  public void setType(String type) {
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
  private boolean isValidType(String type) {
    return type != null && type.length() <= 20;
  }

  /**
   * Gets tax percentage
   *
   * @return Tax percentage
   */
  public Double getTax() {
    return tax;
  }

  /**
   * Sets tax percentage
   *
   * @param tax Tax percentage
   */
  public void setTax(Double tax) {
    if (!isValidTax(tax)) {
      throw new PropertyConstraintException(tax, "tax is invalid");
    }
    this.tax = tax;
  }

  /**
   * Returns whether the given tax is valid
   *
   * @param tax the tax to check the validity of
   * @return {@code true} if tax is valid, {@code false} if not
   */
  private boolean isValidTax(Double tax) {
    return tax != null;
  }

  /**
   * Gets stack level for this type of tax. Default value, when absent, is 0.
   *
   * @return Stack level for this type of tax
   */
  @Nullable
  public Integer getStack() {
    return stack;
  }

  /**
   * Sets stack level for this type of tax. Default value, when absent, is 0.
   *
   * @param stack Stack level for this type of tax
   */
  public void setStack(@Nullable Integer stack) {
    if (!isValidStack(stack)) {
      throw new PropertyConstraintException(stack, "stack is invalid");
    }
    this.stack = stack;
  }

  /**
   * Returns whether the given stack is valid
   *
   * @param stack the stack to check the validity of
   * @return {@code true} if stack is valid, {@code false} if not
   */
  private boolean isValidStack(@Nullable Integer stack) {
    return stack == null || (stack >= 0);
  }

  /**
   * Adds stack level for this type of tax. Default value, when absent, is 0.
   *
   * @param stack Stack level for this type of tax
   * @return this
   */
  public TaxRate withStack(@Nullable Integer stack) {
    setStack(stack);
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
  public TaxRate withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  public boolean validate() {
    return isValidType(type)
        && isValidTax(tax)
        && isValidStack(stack)
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
    TaxRate that = (TaxRate) o;
    return Objects.equals(type, that.type)
        && Objects.equals(tax, that.tax)
        && Objects.equals(stack, that.stack)
        && Objects.equals(customData, that.customData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, tax, stack, customData);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("type", type)
        .add("tax", tax)
        .add("stack", stack)
        .add("customData", customData)
        .add("isValid", validate())
        .toString();
  }
}
