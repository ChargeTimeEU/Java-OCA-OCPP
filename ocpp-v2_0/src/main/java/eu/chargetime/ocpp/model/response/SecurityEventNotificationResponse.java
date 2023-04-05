package eu.chargetime.ocpp.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.dataTypes.CustomDataType;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class SecurityEventNotificationResponse extends Confirmation {
    /**
     * This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
     *
     */
    @JsonProperty("customData")
    public CustomDataType customData;

    @Override
    public boolean validate() {
        return true;
    }
}
