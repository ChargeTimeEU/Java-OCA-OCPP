package eu.chargetime.ocpp.test.profiles.core.soap

import eu.chargetime.ocpp.test.base.soap.SOAPBaseSpec
import spock.util.concurrent.PollingConditions

class SOAPDataTransferSpec extends SOAPBaseSpec {
    def "Central System sends a DataTransfer request and receives a response"() {
        def conditions = new PollingConditions(timeout: 2)
        given:
        chargePoint.sendBootNotification("VendorX", "SingleSocketCharger")
        conditions.eventually {
            assert centralSystem.connected()
        }

        when:
        centralSystem.sendDataTransferRequest("VendorId", "messageId", "data")

        then:
        conditions.eventually {
            assert chargePoint.hasHandledDataTransferRequest()
            assert centralSystem.hasReceivedDataTransferConfirmation()
        }
    }

    def "Charge point sends a DataTransfer request and receives a response"() {
        def conditions = new PollingConditions(timeout: 10)

        when:
        chargePoint.sendDataTransferRequest("VendorId", "messageId", "data")

        then:
        conditions.eventually {
            assert centralSystem.hasHandledDataTransferRequest()
            assert chargePoint.hasReceivedDataTransferConfirmation("Accepted")
        }
    }
}
