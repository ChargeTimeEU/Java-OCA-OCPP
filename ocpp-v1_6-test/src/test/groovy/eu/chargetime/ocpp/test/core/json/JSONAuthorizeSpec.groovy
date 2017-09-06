package eu.chargetime.ocpp.test.core.json

import eu.chargetime.ocpp.OccurenceConstraintException
import eu.chargetime.ocpp.test.FakeCentral
import eu.chargetime.ocpp.test.FakeCentralSystem
import eu.chargetime.ocpp.test.FakeChargePoint
import spock.lang.Shared
import spock.lang.Specification
import spock.util.concurrent.PollingConditions

class JSONAuthorizeSpec extends Specification {
    @Shared
    FakeCentralSystem centralSystem = FakeCentral.getSystem(FakeCentral.serverType.JSON)
    @Shared
    FakeChargePoint chargePoint = new FakeChargePoint()

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

    def "Charge point sends Authorize request and receives a response"() {
        def conditions = new PollingConditions(timeout: 1)
        when:
        chargePoint.sendAuthorizeRequest("test123")

        then:
        conditions.eventually {
            assert centralSystem.hasHandledAuthorizeRequest()
        }

        then:
        conditions.eventually {
            assert chargePoint.hasReceivedAuthorizeConfirmation("Accepted")
        }
    }

    def "Try to send incomplete Authorize request, get local exception"() {
        when:
        chargePoint.sendIncompleteAuthorizeRequest()

        then:
        thrown OccurenceConstraintException
    }

    def "Send Authorize request to a server that's rigged to fail"() {
        def conditions = new PollingConditions(timeout: 1)

        given:
        centralSystem.rigNextRequestToFail()

        when:
        chargePoint.sendAuthorizeRequest("")

        then:
        conditions.eventually {
            assert chargePoint.hasReceivedError()
        }
    }
}
