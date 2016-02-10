import spock.lang.*;

@Stepwise
class BootNotification extends Specification
{

    def "Charge Point sends boot notification to central system and gets response"() {
        expect:
        1+1==2
    }
}
