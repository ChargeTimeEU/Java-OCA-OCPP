package extrawest.ocpp.model.dataTypes;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import extrawest.ocpp.model.Validatable;
import extrawest.ocpp.model.dataTypes.enums.ClearMonitoringStatusEnumType;
import extrawest.ocpp.model.validation.RequiredValidator;
import extrawest.ocpp.model.validation.Validator;
import lombok.Getter;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "customData",
        "status",
        "id",
        "statusInfo"
})
@Getter
@NoArgsConstructor
public class ClearMonitoringResultType implements Validatable {

    private transient Validator<Object> requiredValidator = new RequiredValidator();

    /**
     * This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
     *
     */
    @JsonProperty("customData")
    public CustomDataType customData;
    /**
     * Result of the clear request for this monitor, identified by its Id.
     *
     *
     * (Required)
     *
     */
    @JsonProperty("status")
    public ClearMonitoringStatusEnumType status;
    /**
     * Id of the monitor of which a clear was requested.
     *
     *
     * (Required)
     *
     */
    @JsonProperty("id")
    public Integer id;
    /**
     * Element providing more information about the status.
     *
     *
     */
    @JsonProperty("statusInfo")
    public StatusInfoType statusInfo;

    public ClearMonitoringResultType(ClearMonitoringStatusEnumType status, Integer id) {
        requiredValidator.validate(status);
        requiredValidator.validate(id);
        this.status = status;
        this.id = id;
    }

    public void setCustomData(CustomDataType customData) {
        this.customData = customData;
    }

    public void setStatus(ClearMonitoringStatusEnumType status) {
        requiredValidator.validate(status);
        this.status = status;
    }

    public void setId(Integer id) {
        requiredValidator.validate(id);
        this.id = id;
    }

    public void setStatusInfo(StatusInfoType statusInfo) {
        this.statusInfo = statusInfo;
    }

    @Override
    public boolean validate() {
        return requiredValidator.safeValidate(status)
                && requiredValidator.safeValidate(id);
    }
}
