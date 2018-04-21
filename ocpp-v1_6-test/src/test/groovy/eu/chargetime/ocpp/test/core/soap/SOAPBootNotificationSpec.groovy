package eu.chargetime.ocpp.test.core.soap

import spock.util.concurrent.PollingConditions

class SOAPBootNotificationSpec extends SOAPBaseSpec {
    def "Charge point sends Boot Notification and receives a response"() {
        def conditions = new PollingConditions(timeout: 10)
        when:
        chargePoint.sendBootNotification("VendorX", "SingleSocketCharger")

        then:
        conditions.eventually {
            assert centralSystem.hasHandledBootNotification("VendorX", "SingleSocketCharger")
        }

        then:
        conditions.eventually {
            assert chargePoint.hasReceivedBootConfirmation("Accepted")
        }
    }
}
