package eu.chargetime.ocpp.test.profiles.core.soap

import eu.chargetime.ocpp.test.base.soap.SOAPBaseSpec
import spock.util.concurrent.PollingConditions

class SOAPUnlockConnectorSpec extends SOAPBaseSpec {
    def setup() {
        chargePoint.sendBootNotification("VendorX", "SingleSocketCharger")
    }

    def "Central System sends a UnlockConnector request and receives a response"() {
        def conditions = new PollingConditions(timeout: 10)
        given:
        conditions.eventually {
            assert centralSystem.connected()
        }

        when:
        centralSystem.sendUnlockConnectorRequest(1)

        then:
        conditions.eventually {
            assert chargePoint.hasHandledUnlockConnectorRequest()
            assert centralSystem.hasReceivedUnlockConnectorConfirmation("Unlocked")
        }
    }
}
