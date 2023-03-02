package eu.chargetime.ocpp.test.profiles.core.soap

import eu.chargetime.ocpp.test.base.soap.SOAPBaseSpec
import spock.util.concurrent.PollingConditions

class SOAPGetConfigurationSpec extends SOAPBaseSpec {
    def setup() {
        chargePoint.sendBootNotification("VendorX", "SingleSocketCharger")
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
