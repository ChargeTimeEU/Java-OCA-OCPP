package eu.chargetime.ocpp2.test.profiles.core.soap

import eu.chargetime.ocpp2.model.core.ResetType
import eu.chargetime.ocpp2.test.base.soap.SOAPBaseSpec
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
