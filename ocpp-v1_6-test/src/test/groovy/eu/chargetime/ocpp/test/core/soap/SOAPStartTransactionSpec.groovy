package eu.chargetime.ocpp.test.core.soap

import spock.util.concurrent.PollingConditions

class SOAPStartTransactionSpec extends SOAPBaseSpec {

    def "Charge point sends StartTransaction request and receives a response"() {
        def conditions = new PollingConditions(timeout: 10)
        when:
        chargePoint.sendStartTransactionRequest()

        then:
        conditions.eventually {
            assert centralSystem.hasHandledStartTransactionRequest()
        }

        then:
        conditions.eventually {
            assert chargePoint.hasReceivedStartTransactionConfirmation()
        }

    }

}
