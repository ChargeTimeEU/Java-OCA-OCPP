package extrawest.ocpp.test.base.soap

import extrawest.ocpp.test.FakeCentral
import extrawest.ocpp.test.FakeCentralSystem
import extrawest.ocpp.test.FakeChargePoint
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
