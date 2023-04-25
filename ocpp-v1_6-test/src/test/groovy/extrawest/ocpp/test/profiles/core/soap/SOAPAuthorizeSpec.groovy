package extrawest.ocpp.test.profiles.core.soap

import extrawest.ocpp.OccurenceConstraintException
import extrawest.ocpp.test.base.soap.SOAPBaseSpec
import spock.util.concurrent.PollingConditions

class SOAPAuthorizeSpec extends SOAPBaseSpec
{
    def "Charge point sends Authorize request and receives a response"() {
        def conditions = new PollingConditions(timeout: 1)
        when:
        chargePoint.sendAuthorizeRequest("test123")

        then:
        conditions.eventually {
            assert centralSystem.hasHandledAuthorizeRequest()
        }

        then:
        conditions.eventually {
            assert chargePoint.hasReceivedAuthorizeConfirmation("Accepted")
        }
    }

    def "Try to send incomplete Authorize request, get local exception"() {
        when:
        chargePoint.sendIncompleteAuthorizeRequest()

        then:
        thrown OccurenceConstraintException
    }

    def "Send Authorize request to a server that's rigged to fail"() {
        def conditions = new PollingConditions(timeout: 1)

        given:
        centralSystem.rigNextRequestToFail()

        when:
        chargePoint.sendAuthorizeRequest("")

        then:
        conditions.eventually {
            assert chargePoint.hasReceivedError()
        }
    }
}
