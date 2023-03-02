package eu.chargetime.ocpp.jsonserverimplementation.handler;

import eu.chargetime.ocpp.feature.profile.ServerCoreEventHandler;
import eu.chargetime.ocpp.jsonserverimplementation.config.TestServerCoreProfileConfig;
import eu.chargetime.ocpp.jsonserverimplementation.config.TestServerEventConfig;
import eu.chargetime.ocpp.model.core.AuthorizationStatus;
import eu.chargetime.ocpp.model.core.AuthorizeConfirmation;
import eu.chargetime.ocpp.model.core.AuthorizeRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@Slf4j
@Import({TestServerCoreProfileConfig.class, TestServerEventConfig.class})
public class AuthorizeHandlerTest {

    @Autowired
    private ServerCoreEventHandler serverCoreEventHandler;

    @Test
    public void testAuthorizeHandlerMethod() throws Exception {
        UUID randomId = UUID.randomUUID();
        String testIdTag = "Test";
        AuthorizeRequest request = new AuthorizeRequest(testIdTag);
        AuthorizeConfirmation confirmation = serverCoreEventHandler.handleAuthorizeRequest(randomId, request);
        assertEquals(AuthorizationStatus.Accepted, confirmation.getIdTagInfo().getStatus());
    }
}
