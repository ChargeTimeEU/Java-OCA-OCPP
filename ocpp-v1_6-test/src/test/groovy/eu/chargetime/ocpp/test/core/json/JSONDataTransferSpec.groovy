package eu.chargetime.ocpp.test.core.json

import spock.util.concurrent.PollingConditions

class JSONDataTransferSpec extends JSONBaseSpec
{
    def "Central System sends a DataTransfer request and receives a response"() {
        def conditions = new PollingConditions(timeout: 1)

        when:
        centralSystem.sendDataTransferRequest("VendorId", "messageId", "data")

        then:
        conditions.eventually {
            assert chargePoint.hasHandledDataTransferRequest()
            assert centralSystem.hasReceivedDataTransferConfirmation()
        }
    }

    def "Charge point sends a DataTransfer request and receives a response"() {
        def conditions = new PollingConditions(timeout: 1)

        when:
        chargePoint.sendDataTransferRequest("VendorId", "messageId", "data")

        then:
        conditions.eventually {
            assert centralSystem.hasHandledDataTransferRequest()
        }

        then:
        conditions.eventually {
            assert chargePoint.hasReceivedDataTransferConfirmation("Accepted")
        }
    }
}
