package eu.chargetime.ocpp.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import eu.chargetime.ocpp.model.RequestWithId;
import eu.chargetime.ocpp.model.dataTypes.AuthorizationDataType;
import eu.chargetime.ocpp.model.dataTypes.CustomDataType;
import eu.chargetime.ocpp.model.dataTypes.enums.UpdateEnumType;
import eu.chargetime.ocpp.model.validation.RequiredValidator;
import eu.chargetime.ocpp.model.validation.Validator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "customData",
        "localAuthorizationList",
        "versionNumber",
        "updateType"
})
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class SendLocalListRequest extends RequestWithId {

    private transient Validator<Object> requiredValidator = new RequiredValidator();

    /**
     * This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
     *
     */
    @JsonProperty("customData")
    public CustomDataType customData;

    @JsonProperty("localAuthorizationList")
    public List<AuthorizationDataType> localAuthorizationList;
    /**
     * In case of a full update this is the version number of the full list. In case of a differential update it is the version number of the list after the update has been applied.
     *
     * (Required)
     *
     */
    @JsonProperty("versionNumber")
    public Integer versionNumber;
    /**
     * This contains the type of update (full or differential) of this request.
     *
     * (Required)
     *
     */
    @JsonProperty("updateType")
    public UpdateEnumType updateType;

    public SendLocalListRequest(Integer versionNumber, UpdateEnumType updateType) {
        requiredValidator.validate(versionNumber);
        requiredValidator.validate(updateType);
        this.versionNumber = versionNumber;
        this.updateType = updateType;
    }

    public void setCustomData(CustomDataType customData) {
        this.customData = customData;
    }

    public void setLocalAuthorizationList(List<AuthorizationDataType> localAuthorizationList) {
        this.localAuthorizationList = localAuthorizationList;
    }

    public void setVersionNumber(Integer versionNumber) {
        requiredValidator.validate(versionNumber);
        this.versionNumber = versionNumber;
    }

    public void setUpdateType(UpdateEnumType updateType) {
        requiredValidator.validate(updateType);
        this.updateType = updateType;
    }

    @Override
    public boolean transactionRelated() {
        return false;
    }

    @Override
    public boolean validate() {
        return requiredValidator.safeValidate(versionNumber)
                &&requiredValidator.safeValidate(updateType);
    }
}
