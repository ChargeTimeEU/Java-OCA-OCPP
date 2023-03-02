package eu.chargetime.ocpp.test.profiles.core.soap

import eu.chargetime.ocpp.test.base.soap.SOAPBaseSpec
import spock.util.concurrent.PollingConditions

class SOAPDataTransferSpec extends SOAPBaseSpec {

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
