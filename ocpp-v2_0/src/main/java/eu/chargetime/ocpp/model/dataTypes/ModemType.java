package eu.chargetime.ocpp.model.dataTypes;
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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import eu.chargetime.ocpp.model.Validatable;
import eu.chargetime.ocpp.model.validation.OCPP2PrimDatatypes;
import eu.chargetime.ocpp.model.validation.Validator;
import eu.chargetime.ocpp.model.validation.ValidatorBuilder;
import eu.chargetime.ocpp.utilities.MoreObjects;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "customData",
        "iccid",
        "imsi"
})
@Getter
public class ModemType implements Validatable {
  private transient Validator validator =
      new ValidatorBuilder()
          .addRule(OCPP2PrimDatatypes.identifierString())
          .addRule(OCPP2PrimDatatypes.string20())
          .build();

  /**
   * This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
   *
   */
  @JsonProperty("customData")
  public CustomDataType customDataType;

  /**
   * Wireless_ Communication_ Module. ICCID. CI20_ Text
   * urn:x-oca:ocpp:uid:1:569327
   * This contains the ICCID of the modem’s SIM card.
   *
   *
   */
  @JsonProperty("iccid")
  private String iccid;

  /**
   * Wireless_ Communication_ Module. IMSI. CI20_ Text
   * urn:x-oca:ocpp:uid:1:569328
   * This contains the IMSI of the modem’s SIM card.
   *
   *
   */
  @JsonProperty("imsi")
  private String imsi;

  /**
   * Optional. This contains the ICCID of the modem’s SIM card.
   *
   * @param iccid identifierString[0..20]
   */
  public void setIccid(String iccid) {
    validator.validate(iccid);
    this.iccid = iccid;
  }

  /**
   * Optional. This contains the IMSI of the modem’s SIM card.
   *
   * @param imsi identifierString[0..20]
   */
  public void setImsi(String imsi) {
    validator.validate(imsi);
    this.imsi = imsi;
  }

  @Override
  public boolean validate() {
    return validator.safeValidate(iccid) && validator.safeValidate(imsi);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ModemType that = (ModemType) o;
    return Objects.equals(iccid, that.iccid) && Objects.equals(imsi, that.imsi);
  }

  @Override
  public int hashCode() {
    return Objects.hash(iccid, imsi);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this).add("iccid", iccid).add("imsi", imsi).toString();
  }
}
