package eu.chargetime.ocpp.test.features

import eu.chargetime.ocpp.test.base.json.BaseSpec
import eu.chargetime.ocpp.test.features.BootNotification
import spock.util.concurrent.PollingConditions

class SetVariablesSpec extends BaseSpec
{
    def "The central system sends a Set Variables request and receives a response"() {
        def conditions = new PollingConditions(timeout: 11)

        given:
        def tester = new SetVariables()
        chargePoint.addFeature(tester.feature)
        centralSystem.addFeature(tester.feature)
        def request = tester.createRequest()

        when:
        centralSystem.send(request)

        then:
        conditions.eventually {
            assert chargePoint.hasHandled(request)
        }

        then:
        conditions.eventually {
            assert centralSystem.recieved(tester.confirmation)
        }
    }
}
