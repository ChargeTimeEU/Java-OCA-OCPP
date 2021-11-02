package eu.chargetime.ocpp.jsonclientimplementation.ocpphandler;

import eu.chargetime.ocpp.feature.profile.ClientCoreEventHandler;
import eu.chargetime.ocpp.feature.profile.ClientCoreProfile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
@Slf4j
public class ClientCoreProfileConfig {

    @Bean
    public ClientCoreProfile configureClientCoreProfile(ClientCoreEventHandler clientCoreEventHandler) {
        return new ClientCoreProfile(clientCoreEventHandler);
    }
}
