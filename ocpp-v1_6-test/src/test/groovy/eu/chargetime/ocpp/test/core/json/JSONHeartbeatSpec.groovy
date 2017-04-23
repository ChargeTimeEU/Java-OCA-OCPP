package eu.chargetime.ocpp.test.core.json

import eu.chargetime.ocpp.test.FakeCentral
import eu.chargetime.ocpp.test.FakeCentralSystem
import eu.chargetime.ocpp.test.FakeChargePoint
import spock.lang.Shared
import spock.lang.Specification
import spock.util.concurrent.PollingConditions

class JSONHeartbeatSpec extends Specification
{
    @Shared
    FakeCentralSystem centralSystem = FakeCentral.getSystem(FakeCentral.serverType.JSON)
    @Shared
    FakeChargePoint chargePoint = new FakeChargePoint()

    def setupSpec() {
        // When a Central System is running
        centralSystem.started()
    }

    def setup() {
        chargePoint.connect()
    }

    def cleanup() {
        chargePoint.disconnect()
    }

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
