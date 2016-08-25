package eu.chargetime.ocpp.test.core

import eu.chargetime.ocpp.test.FakeChargePoint
import eu.chargetime.ocpp.test.OldFakeCentralSystem
import spock.lang.Shared
import spock.lang.Specification
import spock.util.concurrent.PollingConditions

class GetConfiguration extends Specification
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

    def "Central System sends a GetConfiguration request and receives a response"() {
        def conditions = new PollingConditions(timeout: 1)

        when:
        centralSystem.sendGetConfigurationRequest("key1");

        then:
        conditions.eventually {
            assert chargePoint.hasHandledGetConfigurationRequest();
            assert centralSystem.hasReceivedGetConfigurationConfirmation();
        }
    }
}
