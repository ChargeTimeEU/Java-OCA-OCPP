import spock.lang.Shared
import spock.lang.Specification;

class AuthorizeRequest extends Specification
{
    @Shared FakeCentralSystem centralSystem = new FakeCentralSystem();
    @Shared FakeChargePoint chargePoint = new FakeChargePoint();

    def setupSpec() {
        // When a Central System is running
        centralSystem.start();
    }

    def setup() {
        chargePoint.connected();
    }

    def "No initial request received"() {
        expect:
        ! centralSystem.hasReceivedAuthorizeRequest();
    }

    def "Charge point sends authorize request, receives no immediate confirmation"() {
        when:
        chargePoint.sendAuthorizeRequest();

        then:
        ! chargePoint.hasConfirmation();

    }

    def "Charge point sends authorize request which the central system receives"() {
        when:
        chargePoint.sendAuthorizeRequest();

        then:
        centralSystem.hasReceivedAuthorizeRequest();
    }

    def "Central system sends boot confirmation which charge point receives"() {
        def uniqueId = "42";
        chargePoint.sendAuthorizeRequest(uniqueId);

        when:
        centralSystem.sendAuthorizeConfirmation(uniqueId);

        then:
        chargePoint.hasConfirmation(uniqueId);
    }

    def "A authorize request isn't seen as a boot notification"() {
        when:
        chargePoint.sendAuthorizeRequest();

        then:
        ! centralSystem.hasReceivedBootNotification();
    }

}
