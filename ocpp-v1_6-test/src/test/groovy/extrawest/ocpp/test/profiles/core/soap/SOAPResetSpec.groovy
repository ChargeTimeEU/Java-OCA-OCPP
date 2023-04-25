package extrawest.ocpp.test.profiles.core.soap


import extrawest.ocpp.test.base.soap.SOAPBaseSpec
import extrawest.ocpp.model.core.ResetType
import spock.util.concurrent.PollingConditions

class SOAPResetSpec extends SOAPBaseSpec {
    def setup() {
        chargePoint.sendBootNotification("VendorX", "SingleSocketCharger")
    }

    def "Central System sends a Reset request and receives a response"() {
        def conditions = new PollingConditions(timeout: 1)
        given:
        conditions.eventually {
            assert centralSystem.connected()
        }

        when:
        centralSystem.sendResetRequest(ResetType.Hard)

        then:
        conditions.eventually {
            assert chargePoint.hasHandledResetRequest()
            assert centralSystem.hasReceivedResetConfirmation("Accepted")
        }
    }
}
