package eu.chargetime.ocpp.test.core.json

import spock.util.concurrent.PollingConditions

class JSONBootNotificationSpec extends JSONBaseSpec
{

    def "Charge point sends Boot Notification and receives a response"() {
        def conditions = new PollingConditions(timeout: 1)

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
