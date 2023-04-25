package extrawest.ocpp.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import extrawest.ocpp.FeatureRepository;
import extrawest.ocpp.feature.Feature;
import extrawest.ocpp.model.Confirmation;
import extrawest.ocpp.model.Request;
import extrawest.ocpp.model.TestConfirmation;
import extrawest.ocpp.model.TestRequest;
import java.util.Optional;
import java.util.UUID;
import org.junit.Test;

public class FeatureRepositoryTest {

  private final TestFeature feature = new TestFeature();
  private static final String ACTION_NAME = "TestFeature";

  @Test
  public void testFind() {
    FeatureRepository f = new FeatureRepository();
    f.addFeature(feature);

    assertFalse(f.findFeature("TestFeatureFail").isPresent());
    assertFalse(f.findFeature(3).isPresent());

    assertWhenFound(f.findFeature(ACTION_NAME));
    assertWhenFound(f.findFeature(new TestRequest()));
    assertWhenFound(f.findFeature(new TestConfirmation()));
  }

  private void assertWhenFound(Optional<Feature> dummyFeature) {
    assertTrue(dummyFeature.isPresent());
    assertEquals(dummyFeature.get(), feature);
  }

  private static class TestFeature implements Feature {

    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
      throw new RuntimeException("Do not call this method");
    }

    @Override
    public Class<? extends Request> getRequestType() {
      return TestRequest.class;
    }

    @Override
    public Class<? extends Confirmation> getConfirmationType() {
      return TestConfirmation.class;
    }

    @Override
    public String getAction() {
      return ACTION_NAME;
    }
  }
}
