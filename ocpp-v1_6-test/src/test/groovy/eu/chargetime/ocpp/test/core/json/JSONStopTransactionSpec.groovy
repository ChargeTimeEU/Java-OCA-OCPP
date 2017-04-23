package eu.chargetime.ocpp.test.core.json

import eu.chargetime.ocpp.test.FakeCentral
import eu.chargetime.ocpp.test.FakeCentralSystem
import eu.chargetime.ocpp.test.FakeChargePoint
import spock.lang.Shared
import spock.lang.Specification
import spock.util.concurrent.PollingConditions

class JSONStopTransactionSpec extends Specification {
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

    def "Charge point sends StopTransaction request and receives a response"() {
        def conditions = new PollingConditions(timeout: 1)
        when:
        chargePoint.sendStopTransactionRequest()

        then:
        conditions.eventually {
            assert centralSystem.hasHandledStopTransactionRequest()
        }

        then:
        conditions.eventually {
            assert chargePoint.hasReceivedStopTransactionConfirmation()
        }
    }

    def "StopTransaction request is stored when offline"() {
        def conditions = new PollingConditions(initialDelay: 0.5, timeout: 1)

        given:
        chargePoint.disconnect()

        when:
        chargePoint.sendStopTransactionRequest()

        then:
        conditions.within(1) {
            assert !centralSystem.hasHandledStopTransactionRequest()
        }

        when:
        chargePoint.connect()

        then:
        conditions.setInitialDelay(0)
        conditions.eventually {
            assert centralSystem.hasHandledStopTransactionRequest()
        }
    }

}
