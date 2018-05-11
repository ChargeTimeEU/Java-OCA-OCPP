package eu.chargetime.ocpp.test.profiles.core.soap

import eu.chargetime.ocpp.test.base.soap.SOAPBaseSpec
import spock.util.concurrent.PollingConditions

class SOAPRemoteStartTransactionSpec extends SOAPBaseSpec {
    def setup() {
        chargePoint.sendBootNotification("VendorX", "SingleSocketCharger")
    }

    def "Central System sends a RemoteStartTransaction request and receives a response"() {
        def conditions = new PollingConditions(timeout: 1)
        given:
        conditions.eventually {
            assert centralSystem.connected()
        }

        when:
        centralSystem.sendRemoteStartTransactionRequest(1, "some id")

        then:
        conditions.eventually {
            assert chargePoint.hasHandledRemoteStartTransactionRequest()
            assert centralSystem.hasReceivedRemoteStartTransactionConfirmation("Accepted")
        }
    }
}
