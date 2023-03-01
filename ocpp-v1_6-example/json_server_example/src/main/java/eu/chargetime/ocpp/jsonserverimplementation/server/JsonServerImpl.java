package eu.chargetime.ocpp2.jsonserverimplementation.server;

import eu.chargetime.ocpp2.JSONServer;
import eu.chargetime.ocpp2.ServerEvents;
import eu.chargetime.ocpp2.jsonserverimplementation.config.ApplicationConfiguration;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
@AllArgsConstructor
public class JsonServerImpl {

    private final ServerEvents serverEvents;
    private final JSONServer server;
    private final ApplicationConfiguration applicationConfiguration;

    @PostConstruct
    public void startServer() throws Exception {
        server.open("localhost", applicationConfiguration.getServerPort(), serverEvents);
    }
}
