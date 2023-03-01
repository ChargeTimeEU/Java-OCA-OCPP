package eu.chargetime.ocpp2.test.features

import eu.chargetime.ocpp2.test.FakeCentralSystem
import eu.chargetime.ocpp2.test.FakeChargePoint
import spock.lang.Shared
import spock.lang.Specification

abstract class BaseSpec extends Specification {

    @Shared
    FakeCentralSystem centralSystem = new FakeCentralSystem()
    @Shared
    FakeChargePoint chargePoint = new FakeChargePoint()

    def setupSpec() {
        // When a Central System is running
        centralSystem.started()
        Thread.sleep(100)
    }

    def setup() {
        //Thread.sleep(100);
        chargePoint.connect()
    }

    def cleanup() {
        chargePoint.disconnect()
    }

    def cleanupSpec() {
        centralSystem.stopped()
    }
}
