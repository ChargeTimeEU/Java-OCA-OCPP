package eu.chargetime.ocpp2.jsonserverimplementation.server;

import eu.chargetime.ocpp2.JSONServer;
import eu.chargetime.ocpp2.ServerEvents;
import eu.chargetime.ocpp2.feature.profile.ServerCoreProfile;
import eu.chargetime.ocpp2.jsonserverimplementation.config.ApplicationConfiguration;
import eu.chargetime.ocpp2.jsonserverimplementation.config.TestJsonServerConfig;
import eu.chargetime.ocpp2.jsonserverimplementation.config.TestServerCoreProfileConfig;
import eu.chargetime.ocpp2.jsonserverimplementation.config.TestServerEventConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertTrue;


@RunWith(SpringRunner.class)
@Slf4j
@Import({TestJsonServerConfig.class, TestServerCoreProfileConfig.class, TestServerEventConfig.class})
public class JsonServerImplTest {

    @Autowired
    private ServerEvents serverEvents;

    @Autowired
    private JSONServer jsonServer;

    @Autowired
    private ServerCoreProfile serverCoreProfile;

    @Test
    public void couldCreateJsonServerImpl() {
        ApplicationConfiguration applicationConfiguration = new ApplicationConfiguration();
        JsonServerImpl jsonServerImpl = new JsonServerImpl(serverEvents, jsonServer, applicationConfiguration);
        assertTrue(true);
    }
}
