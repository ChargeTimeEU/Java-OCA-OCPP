package eu.chargetime.ocpp.model.core;

/*
ChargeTime.eu - Java-OCA-OCPP
Copyright (C) 2015-2016 Thomas Volden <tv@chargetime.eu>
Copyright (C) 2019 Kevin Raddatz <kevin.raddatz@valtech-mobility.com>

MIT License

Copyright (C) 2016-2018 Thomas Volden
Copyright (C) 2019 Kevin Raddatz <kevin.raddatz@valtech-mobility.com>
Copyright (C) 2022 Emil Melar <emil@iconsultable.no>

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

import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.utilities.MoreObjects;
import java.util.Objects;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/** Returned from Charge Point to Central System */
@XmlRootElement(name = "changeConfigurationResponse")
public class ChangeConfigurationConfirmation extends Confirmation {

  private ConfigurationStatus status;

  /**
   * @deprecated use {@link #ChangeConfigurationConfirmation(ConfigurationStatus)} to be sure to set
   *     required fields
   */
  @Deprecated
  public ChangeConfigurationConfirmation() {}

  /**
   * Handle required fields.
   *
   * @param status the {@link ConfigurationStatus}, see {@link #setStatus(ConfigurationStatus)}
   */
  public ChangeConfigurationConfirmation(ConfigurationStatus status) {
    setStatus(status);
  }

  /**
   * Returns whether configuration change has been accepted.
   *
   * @return String, the {@link ConfigurationStatus}.
   */
  public ConfigurationStatus getStatus() {
    return status;
  }

  /**
   * Required. Returns whether configuration change has been accepted.
   *
   * @param status the {@link ConfigurationStatus}.
   */
  @XmlElement
  public void setStatus(ConfigurationStatus status) {
    this.status = status;
  }

  /**
   * Returns whether configuration change has been accepted.
   *
   * @return the {@link ConfigurationStatus}.
   */
  @Deprecated
  public ConfigurationStatus objStatus() {
    return status;
  }

  @Override
  public boolean validate() {
    return status != null;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ChangeConfigurationConfirmation that = (ChangeConfigurationConfirmation) o;
    return status == that.status;
  }

  @Override
  public int hashCode() {
    return Objects.hash(status);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("status", status)
        .add("isValid", validate())
        .toString();
  }
}
