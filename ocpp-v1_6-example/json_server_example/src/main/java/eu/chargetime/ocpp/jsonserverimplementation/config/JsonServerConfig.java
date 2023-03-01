package eu.chargetime.ocpp2.jsonserverimplementation.config;

import eu.chargetime.ocpp2.JSONServer;
import eu.chargetime.ocpp2.feature.profile.ServerCoreProfile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class JsonServerConfig {

    @Bean
    public JSONServer jsonServer(ServerCoreProfile core) {
        return new JSONServer(core);
    }

}
