package eu.chargetime.ocpp.test.core

import eu.chargetime.ocpp.test.FakeCentralSystem
import eu.chargetime.ocpp.test.FakeChargePoint
import spock.lang.Shared
import spock.lang.Specification
import spock.util.concurrent.PollingConditions

class ClearCache extends Specification
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

    def "Central System sends a ClearCache request and receives a response"() {
        def conditions = new PollingConditions(timeout: 1)

        when:
        centralSystem.sendClearCacheRequest();

        then:
        conditions.eventually {
            assert chargePoint.hasHandledClearCacheRequest();
            assert centralSystem.hasReceivedClearCacheConfirmation();
        }
    }
}
