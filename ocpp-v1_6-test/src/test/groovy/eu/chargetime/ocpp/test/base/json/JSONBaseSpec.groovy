package eu.chargetime.ocpp.test.base.json

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
        def conditions = new PollingConditions(timeout: 11)

        // When a Central System is running
        centralSystem.started()

        conditions.eventually {
            assert !centralSystem.isClosed()
        }
    }

    def setup() {
        Thread.sleep(100);

        chargePoint.connect()
    }

    def cleanup() {
        chargePoint.disconnect()
        centralSystem.clearRiggedToFailFlag()
    }

    def cleanupSpec() {
        def conditions = new PollingConditions(timeout: 11)

        centralSystem.stopped()

        conditions.eventually {
            assert centralSystem.isClosed()
        }
    }
}
