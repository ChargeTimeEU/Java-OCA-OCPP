package eu.chargetime.ocpp.test.core.json

import spock.util.concurrent.PollingConditions

class JSONGetConfigurationSpec extends JSONBaseSpec
{
    def "Central System sends a GetConfiguration request and receives a response"() {
        def conditions = new PollingConditions(timeout: 1)

        when:
        centralSystem.sendGetConfigurationRequest("key1")

        then:
        conditions.eventually {
            assert chargePoint.hasHandledGetConfigurationRequest()
            assert centralSystem.hasReceivedGetConfigurationConfirmation()
        }
    }
}
