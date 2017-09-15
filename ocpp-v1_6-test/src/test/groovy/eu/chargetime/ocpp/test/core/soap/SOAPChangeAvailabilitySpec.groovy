package eu.chargetime.ocpp.test.core.soap

import eu.chargetime.ocpp.model.core.AvailabilityType
import eu.chargetime.ocpp.test.FakeCentral
import eu.chargetime.ocpp.test.FakeCentralSystem
import eu.chargetime.ocpp.test.FakeChargePoint
import spock.lang.Shared
import spock.lang.Specification
import spock.util.concurrent.PollingConditions

class SOAPChangeAvailabilitySpec extends Specification {
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
        chargePoint.sendBootNotification("VendorX", "SingleSocketCharger")
    }

    def cleanup() {
        chargePoint.disconnect()
    }

    def cleanupSpec() {
        centralSystem.stopped()
    }

    def "Central System sends a ChangeAvailability request and receives a response"() {
        def conditions = new PollingConditions(timeout: 1)
        given:
        conditions.eventually {
            assert centralSystem.connected()
        }

        when:
        centralSystem.sendChangeAvailabilityRequest(1, AvailabilityType.Inoperative)

        then:
        conditions.eventually {
            assert chargePoint.hasHandledChangeAvailabilityRequest()
            assert centralSystem.hasReceivedChangeAvailabilityConfirmation("Accepted")
        }
    }
}
