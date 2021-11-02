package eu.chargetime.ocpp.jsonclientimplementation.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@Getter
@Setter
public class ApiConfigurations {
    
    @Value("${websocket.url}")
    private String webSocketBaseUrl;
}
