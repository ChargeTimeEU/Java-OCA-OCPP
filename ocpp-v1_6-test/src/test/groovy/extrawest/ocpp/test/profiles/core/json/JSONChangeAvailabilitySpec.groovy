package extrawest.ocpp.test.profiles.core.json


import extrawest.ocpp.test.base.json.JSONBaseSpec
import extrawest.ocpp.model.core.AvailabilityType
import spock.util.concurrent.PollingConditions

class JSONChangeAvailabilitySpec extends JSONBaseSpec
{

    def "Central System sends a ChangeAvailability request and receives a response"() {
        def conditions = new PollingConditions(timeout: 1)
        when:
        centralSystem.sendChangeAvailabilityRequest(1, AvailabilityType.Inoperative)

        then:
        conditions.eventually {
            assert chargePoint.hasHandledChangeAvailabilityRequest()
            assert centralSystem.hasReceivedChangeAvailabilityConfirmation("Accepted")
        }
    }
}
