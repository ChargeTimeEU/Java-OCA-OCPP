import eu.chargetime.ocpp.test.FakeCentralSystem
import eu.chargetime.ocpp.test.FakeChargePoint
import spock.lang.*
import spock.util.concurrent.PollingConditions;

class BootNotification extends Specification
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

    def "No initial request received"() {
        expect:
        ! centralSystem.hasReceivedBootNotification();
    }

    def "Charge point sends boot notification and receives a response"() {
        def conditions = new PollingConditions(timeout: 1)
        when:
        chargePoint.sendBootNotification("VendorX", "SingleSocketCharger");

        then:
        conditions.eventually {
            centralSystem.hasReceivedBootNotification("VendorX", "SingleSocketCharger");
        }

        when:
        centralSystem.sendBootConfirmation(FakeCentralSystem.RegistrationStatus.Accepted);

        then:
        conditions.eventually {
            chargePoint.hasReceivedBootConfirmation();
        }
    }
}
