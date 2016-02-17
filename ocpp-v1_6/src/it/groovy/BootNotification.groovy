import spock.lang.*;

class BootNotification extends Specification
{
    @Shared FakeCentralSystem centralSystem = new FakeCentralSystem();
    @Shared FakeChargePoint chargePoint = new FakeChargePoint();

    def setupSpec() {
        centralSystem.start();
    }

    def setup() {
        centralSystem.clean()
        chargePoint.connected();
    }

    def "Charge point sends boot notification which the central system receives"() {
        when:
        chargePoint.sendBootNotification();

        then:
        centralSystem.hasReceivedBootNotification();
    }

    def "Central system sends boot confirmation which charge point receives"() {
        when:
        centralSystem.sendBootConfirmation();

        then:
        chargePoint.hasReceivedBootConfirmation();
    }

    def "No initial request received"() {
        expect:
        centralSystem.hasReceivedBootNotification() == false;
    }

    def "Charge point sends boot notification, receives no immediate confirmation"() {
        when:
        chargePoint.sendBootNotification();

        then:
        chargePoint.hasReceivedBootConfirmation() == false;
    }

}
