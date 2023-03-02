package eu.chargetime.ocpp.test.profiles.core.json

import eu.chargetime.ocpp.test.base.json.JSONBaseSpec
import spock.util.concurrent.PollingConditions

class JSONStatusNotificationSpec extends JSONBaseSpec {
    
    def "Charge point sends StatusNotification request and receives a response"() {
        def conditions = new PollingConditions(timeout: 1)
        when:
        chargePoint.sendStatusNotificationRequest()

        then:
        conditions.eventually {
            assert centralSystem.hasHandledStatusNotificationRequest()
        }

        then:
        conditions.eventually {
            assert chargePoint.hasReceivedStatusNotificationConfirmation()
        }

    }

}
