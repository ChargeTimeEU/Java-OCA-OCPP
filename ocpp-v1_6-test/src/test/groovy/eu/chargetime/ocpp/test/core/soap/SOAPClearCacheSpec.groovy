package eu.chargetime.ocpp.test.core.soap

import eu.chargetime.ocpp.test.FakeCentralSystem
import eu.chargetime.ocpp.test.FakeChargePoint
import spock.lang.Shared
import spock.lang.Specification
import spock.util.concurrent.PollingConditions

class SOAPClearCacheSpec extends Specification {
    @Shared
    FakeCentralSystem centralSystem = FakeCentralSystem.getInstance();
    @Shared
    FakeChargePoint chargePoint = new FakeChargePoint(FakeChargePoint.clientType.SOAP)

    def setupSpec() {
        // When a Central System is running
        centralSystem.started(FakeCentralSystem.serverType.SOAP)
    }

    def setup() {
        chargePoint.connect()
        chargePoint.sendBootNotification("VendorX", "SingleSocketCharger")
    }

    def cleanup() {
        chargePoint.disconnect()
    }

    def "Central System sends a ClearCache request and receives a response"() {
        def conditions = new PollingConditions(timeout: 1)
        given:
        conditions.eventually {
            assert centralSystem.connected()
        }

        when:
        centralSystem.sendClearCacheRequest()

        then:
        conditions.eventually {
            assert chargePoint.hasHandledClearCacheRequest()
            assert centralSystem.hasReceivedClearCacheConfirmation()
        }
    }
}
