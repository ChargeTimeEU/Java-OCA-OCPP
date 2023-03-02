package eu.chargetime.ocpp.test.profiles.core.json

import eu.chargetime.ocpp.test.base.json.JSONBaseSpec
import spock.util.concurrent.PollingConditions

class JSONRemoteStopTransactionSpec extends JSONBaseSpec {

    def "Central System sends a RemoteStopTransaction request and receives a response"() {
        def conditions = new PollingConditions(timeout: 10)
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
