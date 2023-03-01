package eu.chargetime.ocpp2.test.features


import spock.util.concurrent.PollingConditions

class GetVariablesSpec extends BaseSpec
{
    def "The central system sends a Get Variables request and receives a response"() {
        def conditions = new PollingConditions(timeout: 11)

        given:
        def tester = new GetVariables()
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
            assert centralSystem.received(tester.confirmation)
        }
    }
}
