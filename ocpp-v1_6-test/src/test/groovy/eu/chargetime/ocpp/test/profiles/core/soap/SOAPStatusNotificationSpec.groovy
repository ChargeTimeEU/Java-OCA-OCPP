package eu.chargetime.ocpp.test.profiles.core.soap

import eu.chargetime.ocpp.test.base.soap.SOAPBaseSpec
import spock.util.concurrent.PollingConditions

class SOAPStatusNotificationSpec extends SOAPBaseSpec {

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
