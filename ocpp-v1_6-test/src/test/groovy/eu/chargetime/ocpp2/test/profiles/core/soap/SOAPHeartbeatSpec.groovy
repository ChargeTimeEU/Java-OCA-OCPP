package eu.chargetime.ocpp2.test.profiles.core.soap

import eu.chargetime.ocpp2.test.base.soap.SOAPBaseSpec
import spock.util.concurrent.PollingConditions

class SOAPHeartbeatSpec extends SOAPBaseSpec {

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
