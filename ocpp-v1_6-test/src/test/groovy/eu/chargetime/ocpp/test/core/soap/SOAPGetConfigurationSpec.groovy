package eu.chargetime.ocpp.test.core.soap

import eu.chargetime.ocpp.test.FakeCentral
import eu.chargetime.ocpp.test.FakeCentralSystem
import eu.chargetime.ocpp.test.FakeChargePoint
import spock.lang.Shared
import spock.lang.Specification
import spock.util.concurrent.PollingConditions

class SOAPGetConfigurationSpec extends Specification {
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

    def "Central System sends a GetConfiguration request and receives a response"() {
        def conditions = new PollingConditions(timeout: 2)
        given:
        conditions.eventually {
            assert centralSystem.connected()
        }

        when:
        centralSystem.sendGetConfigurationRequest("key1")

        then:
        conditions.eventually {
            assert chargePoint.hasHandledGetConfigurationRequest()
            assert centralSystem.hasReceivedGetConfigurationConfirmation()
        }
    }
}
