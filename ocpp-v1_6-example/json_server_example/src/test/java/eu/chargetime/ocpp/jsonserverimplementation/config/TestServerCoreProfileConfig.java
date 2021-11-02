package eu.chargetime.ocpp.jsonserverimplementation.config;

import eu.chargetime.ocpp.feature.profile.ServerCoreEventHandler;
import eu.chargetime.ocpp.feature.profile.ServerCoreProfile;
import eu.chargetime.ocpp.model.core.*;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.time.ZonedDateTime;
import java.util.UUID;

@TestConfiguration
@Getter
@Slf4j
public class TestServerCoreProfileConfig {

    @Bean
    public ServerCoreEventHandler getCoreEventHandler() {
        return new ServerCoreEventHandler() {
            @Override
            public AuthorizeConfirmation handleAuthorizeRequest(UUID sessionIndex, AuthorizeRequest request) {

                System.out.println(request);
                // ... handle event
                IdTagInfo idTagInfo = new IdTagInfo();
                idTagInfo.setExpiryDate(ZonedDateTime.now());
                idTagInfo.setParentIdTag("test");
                idTagInfo.setStatus(AuthorizationStatus.Accepted);

                return new AuthorizeConfirmation(idTagInfo);
            }

            @Override
            public BootNotificationConfirmation handleBootNotificationRequest(UUID sessionIndex, BootNotificationRequest request) {

                System.out.println(request);
                // ... handle event

                return null; // returning null means unsupported feature
            }

            @Override
            public DataTransferConfirmation handleDataTransferRequest(UUID sessionIndex, DataTransferRequest request) {

                System.out.println(request);
                // ... handle event

                return null; // returning null means unsupported feature
            }

            @Override
            public HeartbeatConfirmation handleHeartbeatRequest(UUID sessionIndex, HeartbeatRequest request) {

                System.out.println(request);
                // ... handle event

                return null; // returning null means unsupported feature
            }

            @Override
            public MeterValuesConfirmation handleMeterValuesRequest(UUID sessionIndex, MeterValuesRequest request) {

                System.out.println(request);
                // ... handle event

                return null; // returning null means unsupported feature
            }

            @Override
            public StartTransactionConfirmation handleStartTransactionRequest(UUID sessionIndex, StartTransactionRequest request) {

                System.out.println(request);
                // ... handle event

                return null; // returning null means unsupported feature
            }

            @Override
            public StatusNotificationConfirmation handleStatusNotificationRequest(UUID sessionIndex, StatusNotificationRequest request) {

                System.out.println(request);
                // ... handle event

                return null; // returning null means unsupported feature
            }

            @Override
            public StopTransactionConfirmation handleStopTransactionRequest(UUID sessionIndex, StopTransactionRequest request) {

                System.out.println(request);
                // ... handle event

                return null; // returning null means unsupported feature
            }
        };
    }

    @Bean
    public ServerCoreProfile createCore(ServerCoreEventHandler serverCoreEventHandler) {
        return new ServerCoreProfile(serverCoreEventHandler);
    }
}
