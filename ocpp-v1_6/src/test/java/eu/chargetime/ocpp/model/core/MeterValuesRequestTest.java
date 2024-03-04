package eu.chargetime.ocpp.model.core;

import junit.framework.TestCase;

import java.time.ZonedDateTime;

public class MeterValuesRequestTest extends TestCase {

    public void testEmptyMeterValuesArray_FailsValidation() {
        MeterValuesRequest request = new MeterValuesRequest(1);
        request.setTransactionId(2);
        request.setMeterValue(new MeterValue[]{});
        assertFalse(request.validate());
    }

    public void testEmptySampledValuesArray_failsValidation() {
        MeterValuesRequest request = new MeterValuesRequest(1);
        MeterValue measured = new MeterValue(ZonedDateTime.now(), new SampledValue[]{});
        request.setTransactionId(2);
        request.setMeterValue(new MeterValue[]{measured});
        assertFalse(request.validate());
    }

    public void testMeterValuesWithAtLeastOneMeasurementPassesValidation() {
        MeterValuesRequest request = new MeterValuesRequest(1);
        SampledValue sample = new SampledValue("5");
        MeterValue measured = new MeterValue(ZonedDateTime.now(), new SampledValue[]{sample});
        request.setTransactionId(2);
        request.setMeterValue(new MeterValue[]{measured});
        assertTrue(request.validate());
    }
}