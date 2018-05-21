package eu.chargetime.ocpp.test.profiles.core.soap

import eu.chargetime.ocpp.model.core.AvailabilityType
import eu.chargetime.ocpp.test.base.soap.SOAPBaseSpec
import spock.util.concurrent.PollingConditions

class SOAPChangeAvailabilitySpec extends SOAPBaseSpec {

    def setup() {
        chargePoint.sendBootNotification("VendorX", "SingleSocketCharger")
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
