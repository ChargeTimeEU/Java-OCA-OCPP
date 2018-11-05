package eu.chargetime.ocpp.test.profiles.core.json

import eu.chargetime.ocpp.test.base.json.BaseSpec
import eu.chargetime.ocpp.test.features.BootNotification
import spock.util.concurrent.PollingConditions

class BootNotificationSpec extends BaseSpec
{
    def "Charge point sends Boot Notification and receives a response"() {
        def conditions = new PollingConditions(timeout: 1)

        given:
        def tester = new BootNotification()
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
            assert chargePoint.recieved(tester.confirmation)
        }
    }
}
