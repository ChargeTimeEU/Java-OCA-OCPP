package eu.chargetime.ocpp.test.profiles.core.json

import eu.chargetime.ocpp.test.base.json.JSONBaseSpec
import spock.util.concurrent.PollingConditions

class JSONClearCacheSpec extends JSONBaseSpec
{
    def "Central System sends a ClearCache request and receives a response"() {
        def conditions = new PollingConditions(timeout: 1)

        when:
        centralSystem.sendClearCacheRequest()

        then:
        conditions.eventually {
            assert chargePoint.hasHandledClearCacheRequest()
            assert centralSystem.hasReceivedClearCacheConfirmation()
        }
    }
}
