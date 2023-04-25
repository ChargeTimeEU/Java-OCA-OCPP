package extrawest.ocpp.model.dataTypes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;


/**
 * This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "vendorId"
})
@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class CustomDataType {

    /**
     *
     * (Required)
     *
     */
    @JsonProperty("vendorId")
    public String vendorId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

}
