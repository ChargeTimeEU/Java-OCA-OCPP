package core_features

import eu.chargetime.ocpp.test.FakeCentralSystem
import eu.chargetime.ocpp.test.FakeChargePoint
import spock.lang.*
import spock.util.concurrent.PollingConditions;

class ChangeAvailability extends Specification
{
    @Shared FakeCentralSystem centralSystem = FakeCentralSystem.getInstance();
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
    }

    def "Central System sends a ChangeAvailability request and receives a response"() {
        def conditions = new PollingConditions(timeout: 1)
        when:
        centralSystem.sendChangeAvailability(0, FakeCentralSystem.AvailabilityType.Inoperative);

        then:
        conditions.eventually {
            chargePoint.hasHandledChangeAvailabilityRequest();
            centralSystem.hasReceivedChangeAvailabilityConfirmation("Accepted");
        }
    }
}
