package eu.chargetime.ocpp.test.core.json

import eu.chargetime.ocpp.test.FakeCentral
import eu.chargetime.ocpp.test.FakeCentralSystem
import eu.chargetime.ocpp.test.FakeChargePoint
import spock.lang.Shared
import spock.lang.Specification
import spock.util.concurrent.PollingConditions

abstract class JSONBaseSpec extends Specification {
    @Shared
    FakeCentralSystem centralSystem = FakeCentral.getSystem(FakeCentral.serverType.JSON)
    @Shared
    FakeChargePoint chargePoint = new FakeChargePoint()

    def setupSpec() {
        def conditions = new PollingConditions(timeout: 10)

        Thread.sleep(100);

        // When a Central System is running
        centralSystem.started()

        conditions.eventually {
            assert !centralSystem.isClosed()
        }
    }

    def setup() {
        chargePoint.connect()
    }

    def cleanup() {
        chargePoint.disconnect()
        centralSystem.clearRiggedToFailFlag()
    }

    def cleanupSpec() {
        def conditions = new PollingConditions(timeout: 10)

        centralSystem.stopped()

        conditions.eventually {
            assert centralSystem.isClosed()
        }
    }
}
