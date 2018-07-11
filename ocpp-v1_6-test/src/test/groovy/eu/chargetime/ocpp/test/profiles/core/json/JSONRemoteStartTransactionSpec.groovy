package eu.chargetime.ocpp.test.profiles.core.json

import eu.chargetime.ocpp.test.base.json.JSONBaseSpec
import spock.util.concurrent.PollingConditions

class JSONRemoteStartTransactionSpec extends JSONBaseSpec {
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

    def "Central System sends a RemoteStartTransaction with charging profile request and receives a response"() {
        def conditions = new PollingConditions(timeout: 1)
        given:
        conditions.eventually {
            assert centralSystem.connected()
        }

        when:
        centralSystem.sendRemoteStartTransactionWithProfileRequest(1, "some id")

        then:
        conditions.eventually {
            assert chargePoint.hasHandledRemoteStartTransactionRequest()
            assert centralSystem.hasReceivedRemoteStartTransactionConfirmation("Accepted")
        }
    }
}
