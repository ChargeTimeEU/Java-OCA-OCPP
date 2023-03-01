package eu.chargetime.ocpp2.test.profiles.core.json

import eu.chargetime.ocpp2.test.base.json.JSONBaseSpec
import spock.util.concurrent.PollingConditions

class JSONDataTransferSpec extends JSONBaseSpec
{
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
