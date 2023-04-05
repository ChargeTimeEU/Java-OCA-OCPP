package eu.chargetime.ocpp.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.dataTypes.CustomDataType;
import eu.chargetime.ocpp.model.dataTypes.StatusInfoType;
import eu.chargetime.ocpp.model.dataTypes.enums.GenericDeviceModelStatusEnumType;
import eu.chargetime.ocpp.model.validation.RequiredValidator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class GetBaseReportResponse extends Confirmation {

    private transient RequiredValidator requiredValidator = new RequiredValidator();

    /**
     * This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
     *
     */
    @JsonProperty("customData")
    public CustomDataType customData;

    /**
     * This indicates whether the Charging Station is able to accept this request.
     *
     * (Required)
     *
     */
    @JsonProperty("status")
    public GenericDeviceModelStatusEnumType status;
    /**
     * Element providing more information about the status.
     *
     *
     */
    @JsonProperty("statusInfo")
    public StatusInfoType statusInfo;

    public GetBaseReportResponse(GenericDeviceModelStatusEnumType status) {
        requiredValidator.validate(status);
        this.status = status;
    }

    public void setCustomData(CustomDataType customData) {
        this.customData = customData;
    }

    public void setStatus(GenericDeviceModelStatusEnumType status) {
        requiredValidator.validate(status);
        this.status = status;
    }

    public void setStatusInfo(StatusInfoType statusInfo) {
        this.statusInfo = statusInfo;
    }

    @Override
    public boolean validate() {
        return requiredValidator.safeValidate(status);
    }

}
