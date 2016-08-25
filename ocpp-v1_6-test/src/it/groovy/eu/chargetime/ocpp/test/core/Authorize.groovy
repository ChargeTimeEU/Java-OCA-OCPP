package eu.chargetime.ocpp.test.core

import eu.chargetime.ocpp.test.FakeCentralSystem
import eu.chargetime.ocpp.test.FakeChargePoint
import spock.lang.Shared
import spock.lang.Specification
import spock.util.concurrent.PollingConditions;

class Authorize extends Specification
{
    @Shared
    FakeCentralSystem centralSystem = new FakeCentralSystem();
    @Shared FakeChargePoint chargePoint = new FakeChargePoint();

    def setupSpec() {
        // When a Central System is running
        centralSystem.started();
    }

    def setup() {
        chargePoint.connect();
    }

    def cleanup() {
        chargePoint.disconnect();
        centralSystem.stopped();
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

}
