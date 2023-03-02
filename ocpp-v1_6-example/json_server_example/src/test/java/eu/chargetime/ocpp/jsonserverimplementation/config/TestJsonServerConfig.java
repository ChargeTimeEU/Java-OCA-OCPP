package eu.chargetime.ocpp.jsonserverimplementation.config;

import eu.chargetime.ocpp.JSONServer;
import eu.chargetime.ocpp.feature.profile.ServerCoreProfile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
@Slf4j
public class TestJsonServerConfig {

    @Bean
    public JSONServer jsonServer(ServerCoreProfile core) {
        return new JSONServer(core);
    }

}
