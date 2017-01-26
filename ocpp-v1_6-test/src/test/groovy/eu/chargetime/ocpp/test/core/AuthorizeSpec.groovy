package eu.chargetime.ocpp.test.core

import eu.chargetime.ocpp.OccurenceConstraintException
import eu.chargetime.ocpp.test.FakeCentralSystem
import eu.chargetime.ocpp.test.FakeChargePoint
import spock.lang.Shared
import spock.lang.Specification
import spock.util.concurrent.PollingConditions

class AuthorizeSpec extends Specification
{
    @Shared
    FakeCentralSystem centralSystem = FakeCentralSystem.instance;
    @Shared
    FakeChargePoint chargePoint = new FakeChargePoint(FakeChargePoint.clientType.SOAP);

    def setupSpec() {
        // When a Central System is running
        centralSystem.started(FakeCentralSystem.serverType.SOAP);
    }

    def setup() {
        chargePoint.connect();
    }

    def cleanup() {
        chargePoint.disconnect();
    }

    def "Charge point sends Authorize request and receives a response"() {
        def conditions = new PollingConditions(timeout: 1);
        when:
        chargePoint.sendAuthorizeRequest("test123");

        then:
        conditions.eventually {
            assert centralSystem.hasHandledAuthorizeRequest();
        }

        then:
        conditions.eventually {
            assert chargePoint.hasReceivedAuthorizeConfirmation("Accepted");
        }
    }

    def "Try to send incomplete Authorize request, get local exception"() {
        when:
        chargePoint.sendIncompleteAuthorizeRequest();

        then:
        thrown OccurenceConstraintException
    }

    def "Send Authorize request to a server that's rigged to fail"() {
        def conditions = new PollingConditions(timeout: 1);

        given:
        centralSystem.isRiggedToFailOnNextRequest();

        when:
        chargePoint.sendAuthorizeRequest("")

        then:
        conditions.eventually {
            chargePoint.hasReceivedError();
        }
    }
}
