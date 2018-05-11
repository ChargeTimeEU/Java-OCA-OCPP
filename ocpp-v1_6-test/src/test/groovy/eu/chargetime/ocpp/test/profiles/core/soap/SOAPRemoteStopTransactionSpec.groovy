package eu.chargetime.ocpp.test.profiles.core.soap

import eu.chargetime.ocpp.test.base.soap.SOAPBaseSpec
import spock.util.concurrent.PollingConditions

class SOAPRemoteStopTransactionSpec extends SOAPBaseSpec {
    def setup() {
        chargePoint.sendBootNotification("VendorX", "SingleSocketCharger")
    }

    def "Central System sends a RemoteStopTransaction request and receives a response"() {
        def conditions = new PollingConditions(timeout: 1)
        given:
        conditions.eventually {
            assert centralSystem.connected()
        }

        when:
        centralSystem.sendRemoteStopTransactionRequest(0)

        then:
        conditions.eventually {
            assert chargePoint.hasHandledRemoteStopTransactionRequest()
            assert centralSystem.hasReceivedRemoteStopTransactionConfirmation("Accepted")
        }
    }
}
