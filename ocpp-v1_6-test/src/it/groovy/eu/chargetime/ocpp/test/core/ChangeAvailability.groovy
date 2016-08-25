package eu.chargetime.ocpp.test.core

import eu.chargetime.ocpp.model.core.AvailabilityType
import eu.chargetime.ocpp.test.FakeChargePoint
import eu.chargetime.ocpp.test.OldFakeCentralSystem
import spock.lang.Shared
import spock.lang.Specification
import spock.util.concurrent.PollingConditions

class ChangeAvailability extends Specification
{
    @Shared
    OldFakeCentralSystem centralSystem = OldFakeCentralSystem.getInstance();
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
        centralSystem.sendChangeAvailabilityRequest(0, AvailabilityType.Inoperative);

        then:
        conditions.eventually {
            assert chargePoint.hasHandledChangeAvailabilityRequest();
            assert centralSystem.hasReceivedChangeAvailabilityConfirmation("Accepted");
        }
    }
}
