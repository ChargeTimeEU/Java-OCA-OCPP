package eu.chargetime.ocpp.test.core.soap

import eu.chargetime.ocpp.test.FakeCentral
import eu.chargetime.ocpp.test.FakeCentralSystem
import eu.chargetime.ocpp.test.FakeChargePoint
import spock.lang.Shared
import spock.lang.Specification
import spock.util.concurrent.PollingConditions

class SOAPDataTransferSpec extends Specification {
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

    def "Central System sends a DataTransfer request and receives a response"() {
        def conditions = new PollingConditions(timeout: 2)
        given:
        chargePoint.sendBootNotification("VendorX", "SingleSocketCharger")
        conditions.eventually {
            assert centralSystem.connected()
        }

        when:
        centralSystem.sendDataTransferRequest("VendorId", "messageId", "data")

        then:
        conditions.eventually {
            assert chargePoint.hasHandledDataTransferRequest()
            assert centralSystem.hasReceivedDataTransferConfirmation()
        }
    }

    def "Charge point sends a DataTransfer request and receives a response"() {
        def conditions = new PollingConditions(timeout: 10)

        when:
        chargePoint.sendDataTransferRequest("VendorId", "messageId", "data")

        then:
        conditions.eventually {
            assert centralSystem.hasHandledDataTransferRequest()
            assert chargePoint.hasReceivedDataTransferConfirmation("Accepted")
        }
    }
}
