package eu.chargetime.ocpp.test.profiles.core.soap

import eu.chargetime.ocpp.test.base.soap.SOAPBaseSpec
import spock.util.concurrent.PollingConditions

class SOAPChangeConfigurationSpec extends SOAPBaseSpec {
    def setup() {
        chargePoint.sendBootNotification("VendorX", "SingleSocketCharger")
    }

    def "Central System sends a ChangeConfiguration request and receives a response"() {
        def conditions = new PollingConditions(timeout: 1)
        given:
        conditions.eventually {
            assert centralSystem.connected()
        }

        when:
        centralSystem.sendChangeConfigurationRequest("key", "value")

        then:
        conditions.eventually {
            assert chargePoint.hasHandledChangeConfigurationRequest()
            assert centralSystem.hasReceivedChangeConfigurationConfirmation()
        }
    }
}
