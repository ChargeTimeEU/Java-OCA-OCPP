package eu.chargetime.ocpp.test.profiles.core.json

import eu.chargetime.ocpp.test.base.json.BaseSpec
import spock.util.concurrent.PollingConditions

class BootNotificationSpec// extends BaseSpec
{
    def "Charge point sends Boot Notification and receives a response"() {
        def conditions = new PollingConditions(timeout: 1)

        when:
        chargePoint.send("VendorX", "SingleSocketCharger")

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
