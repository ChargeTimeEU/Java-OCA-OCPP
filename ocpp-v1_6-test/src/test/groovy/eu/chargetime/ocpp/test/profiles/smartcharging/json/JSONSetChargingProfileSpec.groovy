package eu.chargetime.ocpp.test.profiles.smartcharging.json

import eu.chargetime.ocpp.model.core.*
import eu.chargetime.ocpp.test.base.json.JSONBaseSpec
import spock.util.concurrent.PollingConditions

/*
    ChargeTime.eu - Java-OCA-OCPP

    MIT License

    Copyright (C) 2016-2018 Thomas Volden <tv@chargetime.eu>
    Copyright (C) 2018 Mikhail Kladkevich <kladmv@ecp-share.com>

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

class JSONSetChargingProfileSpec extends JSONBaseSpec {

    def "Central System sends a SetChargingProfile request and receives a response"() {
        def conditions = new PollingConditions(timeout: 1)
        given:
        conditions.eventually {
            assert centralSystem.connected()
        }

        when:
        ChargingSchedulePeriod[] chargingSchedulePeriod = new ChargingSchedulePeriod[1]
        chargingSchedulePeriod[0] = new ChargingSchedulePeriod(1, 2D)
        centralSystem.sendSetChargingProfileRequest(1, new ChargingProfile(1, 2, ChargingProfilePurposeType.ChargePointMaxProfile, ChargingProfileKindType.Recurring,
                new ChargingSchedule(ChargingRateUnitType.A, chargingSchedulePeriod)))

        then:
        conditions.eventually {
            assert chargePoint.hasHandledSetChargingProfileRequest()
            assert centralSystem.hasReceivedSetChargingProfileConfirmation()
        }
    }
}
