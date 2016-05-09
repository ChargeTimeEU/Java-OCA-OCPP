import eu.chargetime.ocpp.test.FakeCentralSystem
import eu.chargetime.ocpp.test.FakeChargePoint
import spock.lang.Shared
import spock.lang.Specification
import spock.util.concurrent.PollingConditions;

class AuthorizeRequest extends Specification
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
        ! centralSystem.hasReceivedAuthorizeRequest();
    }

    def "Central system sends boot confirmation which charge point receives"() {
        def conditions = new PollingConditions(timeout: 1);
        when:
        chargePoint.sendAuthorizeRequest("test123");

        then:
        conditions.eventually {
            centralSystem.hasReceivedAuthorizeRequest();
        }

        when:
            centralSystem.sendAuthorizeConfirmation(FakeCentralSystem.AuthorizationStatus.Accepted);

        then:
        conditions.eventually {
            chargePoint.hasReceivedAuthorizeConfirmation("Accepted");
        }
    }

    def "A authorize request isn't seen as a boot notification"() {
        when:
        chargePoint.sendAuthorizeRequest("token");

        then:
        centralSystem.receivedMessageIsNot("BootNotification");
    }

}
