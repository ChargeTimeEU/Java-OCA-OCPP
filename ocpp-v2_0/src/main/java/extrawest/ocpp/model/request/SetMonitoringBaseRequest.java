package extrawest.ocpp.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import extrawest.ocpp.model.RequestWithId;
import extrawest.ocpp.model.dataTypes.CustomDataType;
import extrawest.ocpp.model.dataTypes.enums.MonitoringBaseEnumType;
import extrawest.ocpp.model.validation.RequiredValidator;
import extrawest.ocpp.model.validation.Validator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "customData",
        "monitoringBase"
})
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class SetMonitoringBaseRequest extends RequestWithId {

    private final transient Validator<Object> requiredValidator = new RequiredValidator();

    /**
     * This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
     *
     */
    @JsonProperty("customData")
    public CustomDataType customData;
    /**
     * Specify which monitoring base will be set
     *
     * (Required)
     *
     */
    @JsonProperty("monitoringBase")
    public MonitoringBaseEnumType monitoringBase;

    public SetMonitoringBaseRequest(MonitoringBaseEnumType monitoringBase) {
        requiredValidator.validate(monitoringBase);
        this.monitoringBase = monitoringBase;
    }

    public void setCustomData(CustomDataType customData) {
        this.customData = customData;
    }

    public void setMonitoringBase(MonitoringBaseEnumType monitoringBase) {
        requiredValidator.validate(monitoringBase);
        this.monitoringBase = monitoringBase;
    }

    @Override
    public boolean transactionRelated() {
        return false;
    }

    @Override
    public boolean validate() {
        return requiredValidator.safeValidate(monitoringBase);
    }
}
