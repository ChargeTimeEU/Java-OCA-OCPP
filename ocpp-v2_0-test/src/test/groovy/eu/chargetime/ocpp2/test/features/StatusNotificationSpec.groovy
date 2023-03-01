package eu.chargetime.ocpp2.test.features


import spock.util.concurrent.PollingConditions

class StatusNotificationSpec extends BaseSpec {
    def "Charge point sends Status Notification and receives a response"() {
        def conditions = new PollingConditions(timeout: 1)

        given:
        def tester = new StatusNotification()
        chargePoint.addFeature(tester.feature)
        centralSystem.addFeature(tester.feature)
        def request = tester.createRequest()

        when:
        chargePoint.send(request)

        then:
        conditions.eventually {
            assert centralSystem.hasHandled(request)
        }

        then:
        conditions.eventually {
            assert chargePoint.received(tester.confirmation)
        }
    }
}
