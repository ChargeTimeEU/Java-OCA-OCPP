package eu.chargetime.ocpp2.test.base.soap

import eu.chargetime.ocpp2.test.FakeCentral
import eu.chargetime.ocpp2.test.FakeCentralSystem
import eu.chargetime.ocpp2.test.FakeChargePoint
import spock.lang.Shared
import spock.lang.Specification


abstract class SOAPBaseSpec extends Specification
{
    @Shared
    FakeCentralSystem centralSystem = new FakeCentralSystem(FakeCentral.serverType.SOAP)
    @Shared
    FakeChargePoint chargePoint = new FakeChargePoint(FakeChargePoint.clientType.SOAP)

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

    def cleanupSpec() {
        centralSystem.stopped()
    }
}
