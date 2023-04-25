package extrawest.ocpp.model.dataTypes;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import extrawest.ocpp.model.Validatable;
import extrawest.ocpp.model.dataTypes.enums.EventNotificationEnumType;
import extrawest.ocpp.model.dataTypes.enums.EventTriggerEnumType;
import extrawest.ocpp.model.validation.OCPP2PrimDatatypes;
import extrawest.ocpp.model.validation.RequiredValidator;
import extrawest.ocpp.model.validation.Validator;
import extrawest.ocpp.model.validation.ValidatorBuilder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;


/**
 * Class to report an event notification for a component-variable.
 *
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "customData",
        "eventId",
        "timestamp",
        "trigger",
        "cause",
        "actualValue",
        "techCode",
        "techInfo",
        "cleared",
        "transactionId",
        "component",
        "variableMonitoringId",
        "eventNotificationType",
        "variable"
})
@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class EventDataType implements Validatable {

    private final transient Validator<Object> requiredValidator = new RequiredValidator();

    private final transient Validator actualValueValidator =
            new ValidatorBuilder()
                    .setRequired(true)
                    .addRule(OCPP2PrimDatatypes.string2500())
                    .build();

    private final transient Validator techCodeValidator =
            new ValidatorBuilder()
                    .addRule(OCPP2PrimDatatypes.string50())
                    .build();

    private final transient Validator techInfoValidator =
            new ValidatorBuilder()
                    .addRule(OCPP2PrimDatatypes.string500())
                    .build();

    private final transient Validator transactionIdValidator =
            new ValidatorBuilder()
                    .addRule(OCPP2PrimDatatypes.identifierString())
                    .addRule(OCPP2PrimDatatypes.string36())
                    .build();

    /**
     * This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
     *
     */
    @JsonProperty("customData")
    public CustomDataType customData;
    /**
     * Identifies the event. This field can be referred to as a cause by other events.
     *
     *
     * (Required)
     *
     */
    @JsonProperty("eventId")
    public Integer eventId;
    /**
     * Timestamp of the moment the report was generated.
     *
     * (Required)
     *
     */
    @JsonProperty("timestamp")
    public LocalDateTime timestamp;
    /**
     * Type of monitor that triggered this event, e.g. exceeding a threshold value.
     *
     *
     * (Required)
     *
     */
    @JsonProperty("trigger")
    public EventTriggerEnumType trigger;
    /**
     * Refers to the Id of an event that is considered to be the cause for this event.
     *
     *
     *
     */
    @JsonProperty("cause")
    public Integer cause;
    /**
     * Actual value (_attributeType_ Actual) of the variable.
     *
     * The Configuration Variable &lt;<configkey-reporting-value-size,ReportingValueSize>> can be used to limit GetVariableResult.attributeValue, VariableAttribute.value and EventData.actualValue. The max size of these values will always remain equal.
     *
     *
     * (Required)
     *
     */
    @JsonProperty("actualValue")
    public String actualValue;
    /**
     * Technical (error) code as reported by component.
     *
     *
     */
    @JsonProperty("techCode")
    public String techCode;
    /**
     * Technical detail information as reported by component.
     *
     *
     */
    @JsonProperty("techInfo")
    public String techInfo;
    /**
     * _Cleared_ is set to true to report the clearing of a monitored situation, i.e. a 'return to normal'.
     *
     *
     *
     */
    @JsonProperty("cleared")
    public Boolean cleared;
    /**
     * If an event notification is linked to a specific transaction, this field can be used to specify its transactionId.
     *
     *
     */
    @JsonProperty("transactionId")
    public String transactionId;
    /**
     * A physical or logical component
     *
     * (Required)
     *
     */
    @JsonProperty("component")
    public ComponentType component;
    /**
     * Identifies the VariableMonitoring which triggered the event.
     *
     *
     */
    @JsonProperty("variableMonitoringId")
    public Integer variableMonitoringId;
    /**
     * Specifies the event notification type of the message.
     *
     *
     * (Required)
     *
     */
    @JsonProperty("eventNotificationType")
    public EventNotificationEnumType eventNotificationType;
    /**
     * Reference key to a component-variable.
     *
     * (Required)
     *
     */
    @JsonProperty("variable")
    public VariableType variable;

    public EventDataType(Integer eventId,
                         LocalDateTime timestamp,
                         EventTriggerEnumType trigger,
                         String actualValue,
                         ComponentType component,
                         EventNotificationEnumType eventNotificationType,
                         VariableType variable) {
        actualValueValidator.validate(actualValue);
        requiredValidator.validate(eventId);
        requiredValidator.validate(timestamp);
        requiredValidator.validate(trigger);
        requiredValidator.validate(component);
        requiredValidator.validate(eventNotificationType);
        requiredValidator.validate(variable);
        this.eventId = eventId;
        this.timestamp = timestamp;
        this.trigger = trigger;
        this.actualValue = actualValue;
        this.component = component;
        this.eventNotificationType = eventNotificationType;
        this.variable = variable;
    }

    public void setCustomData(CustomDataType customData) {
        this.customData = customData;
    }

    public void setEventId(Integer eventId) {
        requiredValidator.validate(eventId);
        this.eventId = eventId;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        requiredValidator.validate(timestamp);
        this.timestamp = timestamp;
    }

    public void setTrigger(EventTriggerEnumType trigger) {
        requiredValidator.validate(trigger);
        this.trigger = trigger;
    }

    public void setCause(Integer cause) {
        this.cause = cause;
    }

    public void setActualValue(String actualValue) {
        actualValueValidator.validate(actualValue);
        this.actualValue = actualValue;
    }

    public void setTechCode(String techCode) {
        this.techCode = techCode;
    }

    public void setTechInfo(String techInfo) {
        this.techInfo = techInfo;
    }

    public void setCleared(Boolean cleared) {
        this.cleared = cleared;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public void setComponent(ComponentType component) {
        requiredValidator.validate(component);
        this.component = component;
    }

    public void setVariableMonitoringId(Integer variableMonitoringId) {
        this.variableMonitoringId = variableMonitoringId;
    }

    public void setEventNotificationType(EventNotificationEnumType eventNotificationType) {
        requiredValidator.validate(eventNotificationType);
        this.eventNotificationType = eventNotificationType;
    }

    public void setVariable(VariableType variable) {
        requiredValidator.validate(variable);
        this.variable = variable;
    }

    @Override
    public boolean validate() {
        return actualValueValidator.safeValidate(actualValue)
                &&requiredValidator.safeValidate(eventId)
                &&requiredValidator.safeValidate(timestamp)
                &&requiredValidator.safeValidate(trigger)
                &&requiredValidator.safeValidate(component)
                &&requiredValidator.safeValidate(eventNotificationType)
                &&requiredValidator.safeValidate(variable);
    }
}
