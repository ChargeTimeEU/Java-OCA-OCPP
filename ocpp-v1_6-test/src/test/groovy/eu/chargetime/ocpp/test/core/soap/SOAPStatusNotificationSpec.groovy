package eu.chargetime.ocpp.test.core.soap

import eu.chargetime.ocpp.test.FakeCentral
import eu.chargetime.ocpp.test.FakeCentralSystem
import eu.chargetime.ocpp.test.FakeChargePoint
import spock.lang.Shared
import spock.lang.Specification
import spock.util.concurrent.PollingConditions

class SOAPStatusNotificationSpec extends Specification {
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

    def "Charge point sends StatusNotification request and receives a response"() {
        def conditions = new PollingConditions(timeout: 1)
        when:
        chargePoint.sendStatusNotificationRequest()

        then:
        conditions.eventually {
            assert centralSystem.hasHandledStatusNotificationRequest()
        }

        then:
        conditions.eventually {
            assert chargePoint.hasReceivedStatusNotificationConfirmation()
        }

    }

}
