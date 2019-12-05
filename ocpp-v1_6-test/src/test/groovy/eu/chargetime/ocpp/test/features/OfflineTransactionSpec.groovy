package eu.chargetime.ocpp.test.features

import eu.chargetime.ocpp.test.base.json.JSONBaseSpec
import spock.util.concurrent.AsyncConditions
import spock.util.concurrent.PollingConditions

/*
    ChargeTime.eu - Java-OCA-OCPP

    MIT License

    Copyright (C) 2016-2018 Thomas Volden <tv@chargetime.eu>

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

class OfflineTransactionSpec extends JSONBaseSpec {

    def "Disconnected during a transaction, stores and recovers transaction related commands"() {
        def conditions = new PollingConditions(timeout: 1)
        def acon = new AsyncConditions()
        when: "Send BootNotificationRequest"
        chargePoint.sendBootNotification("ChargeTimeEU", "UnstableCharger")

        then: "Received response"
        conditions.eventually {
            assert chargePoint.hasReceivedBootConfirmation("Accepted")
        }

        when: "Send StartTransactionRequest"
        chargePoint.sendStartTransactionRequest()

        then: "Received response"
        conditions.eventually {
            assert chargePoint.hasReceivedStartTransactionConfirmation()
        }

        when: "Disconnected"
        centralSystem.clientLost()

        and: "Send AuthorizationRequest"
        chargePoint.sendAuthorizeRequest("test123")

        then: "Received not connected error"
        conditions.eventually {
            assert chargePoint.hasReceivedNotConnectedError()
        }

        when: "Send StopTransactionRequest"
        chargePoint.clearMemory()
        chargePoint.sendStopTransactionRequest()

        then: "Received not connected error"
        conditions.eventually {
            assert !chargePoint.hasReceivedNotConnectedError()
            assert !chargePoint.hasReceivedStartTransactionConfirmation()
        }

        when: "Connected"
        chargePoint.connect()

        then:
        conditions.eventually {
            chargePoint.hasReceivedStartTransactionConfirmation()
        }
    }

}
