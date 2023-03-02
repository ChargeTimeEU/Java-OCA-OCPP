package eu.chargetime.ocpp.test.profiles.core.soap

import eu.chargetime.ocpp.test.base.soap.SOAPBaseSpec
import spock.util.concurrent.PollingConditions

class SOAPStopTransactionSpec extends SOAPBaseSpec {

    def "Charge point sends StopTransaction request and receives a response"() {
        def conditions = new PollingConditions(timeout: 1)
        when:
        chargePoint.sendStopTransactionRequest()

        then:
        conditions.eventually {
            assert centralSystem.hasHandledStopTransactionRequest()
        }

        then:
        conditions.eventually {
            assert chargePoint.hasReceivedStopTransactionConfirmation()
        }
    }

    def "StopTransaction request is stored when offline"() {
        def conditions = new PollingConditions(initialDelay: 0.5, timeout: 1)

        given:
        chargePoint.disconnect()
        centralSystem.clientLost()

        when:
        chargePoint.sendStopTransactionRequest()

        then:
        conditions.within(1) {
            assert !centralSystem.hasHandledStopTransactionRequest()
        }

        when:
        chargePoint.connect()

        then:
        conditions.setInitialDelay(0)
        conditions.eventually {
            assert centralSystem.hasHandledStopTransactionRequest()
        }
    }
}
