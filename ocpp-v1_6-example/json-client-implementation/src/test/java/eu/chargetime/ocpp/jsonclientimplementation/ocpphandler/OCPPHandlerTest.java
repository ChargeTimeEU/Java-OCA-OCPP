package eu.chargetime.ocpp2.jsonclientimplementation.ocpphandler;

import eu.chargetime.ocpp2.JSONClient;
import eu.chargetime.ocpp2.OccurrenceConstraintException;
import eu.chargetime.ocpp2.UnsupportedFeatureException;
import eu.chargetime.ocpp2.feature.profile.ClientCoreProfile;
import eu.chargetime.ocpp2.jsonclientimplementation.config.ApiConfigurations;
import eu.chargetime.ocpp2.model.core.AuthorizationStatus;
import eu.chargetime.ocpp2.model.core.AuthorizeConfirmation;
import eu.chargetime.ocpp2.model.core.AuthorizeRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@Slf4j
@SpringBootTest
@Import({JsonClientConfig.class, ClientCoreProfileConfig.class, ClientCoreEventHandlerConfig.class,
        ApiConfigurations.class})
public class OCPPHandlerTest {

    @Autowired
    private JSONClient jsonClient;

    @Autowired
    private ClientCoreProfile clientCoreProfile;

    @Autowired
    private ApiConfigurations apiConfigurations;

    @Test
    public void testOCPPAuthorizeHandler() {
        String url = "ws://" + apiConfigurations.getWebSocketBaseUrl();
        AuthorizeRequest testRequest = clientCoreProfile.createAuthorizeRequest("testId");
        jsonClient.connect(url, null);
        try {
            AuthorizeConfirmation authorizeConfirmation = (AuthorizeConfirmation) jsonClient.send(testRequest)
                    .toCompletableFuture().get();
            assertTrue(true);
            assertEquals(AuthorizationStatus.Accepted,authorizeConfirmation.getIdTagInfo().getStatus());
        } catch (OccurrenceConstraintException | UnsupportedFeatureException
                | ExecutionException | InterruptedException e) {
            log.error("Exception occurred: " + e);
            log.error("Test will fail");
            assertTrue(false);
        }
    }
}
