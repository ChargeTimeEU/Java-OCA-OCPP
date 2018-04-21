package eu.chargetime.ocpp.test.core.soap

import eu.chargetime.ocpp.test.FakeCentral
import eu.chargetime.ocpp.test.FakeCentralSystem
import eu.chargetime.ocpp.test.FakeChargePoint
import spock.lang.Shared
import spock.lang.Specification
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
