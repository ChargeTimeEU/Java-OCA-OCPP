package eu.chargetime.ocpp.feature.profile.test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import eu.chargetime.ocpp.feature.Feature;
import eu.chargetime.ocpp.feature.GetLocalListVersionFeature;
import eu.chargetime.ocpp.feature.SendLocalListFeature;
import eu.chargetime.ocpp.feature.profile.ClientLocalAuthListEventHandler;
import eu.chargetime.ocpp.feature.profile.ClientLocalAuthListProfile;
import eu.chargetime.ocpp.model.localauthlist.GetLocalListVersionRequest;
import eu.chargetime.ocpp.model.localauthlist.SendLocalListRequest;
import java.util.UUID;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ClientLocalAuthListProfileTest extends ProfileTest {
  private static final UUID SESSION_NULL = null;

  private ClientLocalAuthListProfile profile;

  @Mock private ClientLocalAuthListEventHandler handler;

  @Before
  public void setup() {
    profile = new ClientLocalAuthListProfile(handler);
  }

  @Test
  public void getFeatureList_containsGetLocalListVersionFeature() {
    // When
    Feature[] featureList = profile.getFeatureList();

    // Then
    assertThat(
        findFeature(featureList, "GetLocalListVersion"),
        is(instanceOf(GetLocalListVersionFeature.class)));
  }

  @Test
  public void getFeatureList_containsSendLocalListFeature() {
    // When
    Feature[] featureList = profile.getFeatureList();

    // Then
    assertThat(
        findFeature(featureList, "SendLocalList"), is(instanceOf(SendLocalListFeature.class)));
  }

  public void handleRequest_GetLocalListVersion_callsHandleGetLocalListVersionRequest() {
    // Given
    GetLocalListVersionRequest request = new GetLocalListVersionRequest();

    // When
    profile.handleRequest(SESSION_NULL, request);

    // Then
    verify(handler, times(1)).handleGetLocalListVersionRequest(eq(request));
  }

  public void handleRequest_SendLocalList_callsHandleSendLocalListRequest() {
    // Given
    SendLocalListRequest request = new SendLocalListRequest();

    // When
    profile.handleRequest(SESSION_NULL, request);

    // Then
    verify(handler, times(1)).handleSendLocalListRequest(eq(request));
  }
}
