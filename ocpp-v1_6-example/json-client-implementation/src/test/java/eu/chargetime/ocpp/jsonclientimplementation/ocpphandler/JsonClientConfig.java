package eu.chargetime.ocpp.jsonclientimplementation.ocpphandler;

import eu.chargetime.ocpp.JSONClient;
import eu.chargetime.ocpp.feature.profile.ClientCoreProfile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
@Slf4j
public class JsonClientConfig {

    @Bean
    public JSONClient configureJsonClient(ClientCoreProfile core) {
        return new JSONClient(core);
    }
}
