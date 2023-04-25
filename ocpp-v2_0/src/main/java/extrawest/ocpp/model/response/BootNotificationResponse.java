package extrawest.ocpp.model.response;
/*
   ChargeTime.eu - Java-OCA-OCPP

   MIT License

   Copyright (C) 2018 Thomas Volden <tv@chargetime.eu>
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

import extrawest.ocpp.model.Confirmation;
import extrawest.ocpp.model.dataTypes.enums.RegistrationStatusEnumType;
import extrawest.ocpp.model.request.BootNotificationRequest;
import extrawest.ocpp.model.validation.RequiredValidator;
import extrawest.ocpp.utilities.MoreObjects;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

/** sent by the CSMS to the Charging Station in response to a {@link BootNotificationRequest}. */
@NoArgsConstructor
public class BootNotificationResponse extends Confirmation {
  private final transient RequiredValidator validator = new RequiredValidator();

  private LocalDateTime currentTime;
  private int interval;
  private RegistrationStatusEnumType status;

  public BootNotificationResponse(LocalDateTime currentTime, int interval, RegistrationStatusEnumType status) {
    validator.validate(currentTime);
    validator.validate(status);
    this.currentTime = currentTime;
    this.interval = interval;
    this.status = status;
  }

  /**
   * This contains the CSMS’s current time.
   *
   * @return {@link LocalDateTime}
   */
  public LocalDateTime getCurrentTime() {
    return currentTime;
  }

  /**
   * Required. This contains the CSMS’s current time.
   *
   * @param currentTime {@link LocalDateTime}
   */
  public void setCurrentTime(LocalDateTime currentTime) {
    validator.validate(currentTime);
    this.currentTime = currentTime;
  }

  /**
   * When Status is Accepted, this contains the heartbeat interval in seconds. If the CSMS returns
   * something other than Accepted, the value of the interval field indicates the minimum wait time
   * before sending a next BootNotification request.
   *
   * @return integer
   */
  public int getInterval() {
    return interval;
  }

  /**
   * Required. When Status is Accepted, this contains the heartbeat interval in seconds. If the CSMS
   * returns something other than Accepted, the value of the interval field indicates the minimum
   * wait time before sending a next BootNotification request.
   *
   * @param interval integer
   */
  public void setInterval(int interval) {
    this.interval = interval;
  }

  /**
   * This contains whether the Charging Station has been registered within the CSMS.
   *
   * @return {@link RegistrationStatusEnumType}.
   */
  public RegistrationStatusEnumType getStatus() {
    return status;
  }

  /**
   * Required. This contains whether the Charging Station has been registered within the CSMS.
   *
   * @param status {@link RegistrationStatusEnumType}.
   */
  public void setStatus(RegistrationStatusEnumType status) {
    validator.validate(status);
    this.status = status;
  }

  @Override
  public boolean validate() {
    return currentTime != null && interval > 0 && status != null;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    BootNotificationResponse that = (BootNotificationResponse) o;
    return currentTime.compareTo(that.currentTime) == 0
        && Objects.equals(interval, that.interval)
        && Objects.equals(status, that.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(currentTime, interval, status);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("currentTime", currentTime)
        .add("interval", interval)
        .add("status", status)
        .toString();
  }
}
