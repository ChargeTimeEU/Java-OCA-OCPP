package eu.chargetime.ocpp.test.profiles.core.json

import eu.chargetime.ocpp.test.base.json.JSONBaseSpec
import spock.util.concurrent.PollingConditions

class JSONHeartbeatSpec extends JSONBaseSpec
{
    def "Charge point sends Heartbeat and receives a response"() {
        def conditions = new PollingConditions(timeout: 1)
        when:
        chargePoint.sendHeartbeatRequest()


        then:
        conditions.eventually {
            assert centralSystem.hasHandledHeartbeat()
        }

        then:
        conditions.eventually {
            assert chargePoint.hasReceivedHeartbeatConfirmation()
        }

    }

}
