import spock.lang.*;

class BootNotification extends Specification
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
        ! centralSystem.hasReceivedBootNotification();
    }

    def "Charge point sends boot notification, receives no immediate confirmation"() {
        when:
        chargePoint.sendBootNotification();

        then:
        ! chargePoint.hasConfirmation();

    }

    def "Charge point sends boot notification which the central system receives"() {
        when:
        chargePoint.sendBootNotification();

        then:
        centralSystem.hasReceivedBootNotification();
    }

    def "Central system sends boot confirmation which charge point receives"() {
        def uniqueId = "42";
        chargePoint.sendBootNotification(uniqueId);

        when:
        centralSystem.sendBootConfirmation(uniqueId);

        then:
        chargePoint.hasConfirmation(uniqueId);
    }


}
