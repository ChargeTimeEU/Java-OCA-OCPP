package core_features

import eu.chargetime.ocpp.test.FakeCentralSystem
import eu.chargetime.ocpp.test.FakeChargePoint
import spock.lang.Shared
import spock.lang.Specification
import spock.util.concurrent.PollingConditions

class DataTransfer extends Specification
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

    def "Central System sends a DataTransfer request and receives a response"() {
        def conditions = new PollingConditions(timeout: 1)

        when:
        centralSystem.sendDataTransferRequest("VendorId", "messageId", "data");

        then:
        conditions.eventually {
            chargePoint.hasHandledDataTransferRequest();
            centralSystem.hasReceivedDataTransferConfirmation();
        }
    }

    def "Charge point sends a DataTransfer request and receives a response"() {
        def conditions = new PollingConditions(timeout: 1)

        when:
        chargePoint.sendDataTransferRequest("VendorId", "messageId", "data");

        then:
        conditions.eventually {
            centralSystem.hasReceivedDataTransferRequest();
        }

        when:
            centralSystem.sendDataTransferConfirmation("Accepted");

        then:
        conditions.eventually {
            chargePoint.hasReceivedDataTransferConfirmation("Accepted");
        }
    }
}
