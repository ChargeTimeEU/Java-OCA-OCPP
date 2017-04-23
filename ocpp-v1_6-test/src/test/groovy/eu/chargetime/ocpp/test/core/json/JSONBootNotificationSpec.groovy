package eu.chargetime.ocpp.test.core.json

import eu.chargetime.ocpp.test.FakeCentral
import eu.chargetime.ocpp.test.FakeCentralSystem
import eu.chargetime.ocpp.test.FakeChargePoint
import spock.lang.Shared
import spock.lang.Specification
import spock.util.concurrent.PollingConditions

class JSONBootNotificationSpec extends Specification
{
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
    
    def "Charge point sends Boot Notification and receives a response"() {
        def conditions = new PollingConditions(timeout: 1)
        when:
        chargePoint.sendBootNotification("VendorX", "SingleSocketCharger")

        then:
        conditions.eventually {
            assert centralSystem.hasHandledBootNotification("VendorX", "SingleSocketCharger")
        }

        then:
        conditions.eventually {
            assert chargePoint.hasReceivedBootConfirmation("Accepted")
        }
    }
}
