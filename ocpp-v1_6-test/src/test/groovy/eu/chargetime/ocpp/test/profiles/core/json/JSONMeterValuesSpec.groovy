package eu.chargetime.ocpp.test.profiles.core.json

import eu.chargetime.ocpp.test.base.json.JSONBaseSpec
import spock.util.concurrent.PollingConditions

class JSONMeterValuesSpec extends JSONBaseSpec
{
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
