package eu.chargetime.ocpp2.test.profiles.core.soap

import eu.chargetime.ocpp2.test.base.soap.SOAPBaseSpec
import spock.util.concurrent.PollingConditions

class SOAPMeterValuesSpec extends SOAPBaseSpec {

    def "Charge point sends MeterValues request and receives a response"() {
        def conditions = new PollingConditions(timeout: 1)
        when:
        chargePoint.sendMeterValuesRequest()

        then:
        conditions.eventually {
            assert centralSystem.hasHandledMeterValuesRequest()
        }

        then:
        conditions.eventually {
            assert chargePoint.hasReceivedMeterValuesConfirmation()
        }

    }

}
