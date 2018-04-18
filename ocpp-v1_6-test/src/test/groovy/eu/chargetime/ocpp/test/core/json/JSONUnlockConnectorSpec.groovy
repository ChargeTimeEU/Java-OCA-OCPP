package eu.chargetime.ocpp.test.core.json

import spock.util.concurrent.PollingConditions

class JSONUnlockConnectorSpec extends JSONBaseSpec {

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
        }

        then:
        conditions.eventually {
            assert centralSystem.hasReceivedUnlockConnectorConfirmation("Unlocked")
        }
    }
}
