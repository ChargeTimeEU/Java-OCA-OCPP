package eu.chargetime.ocpp.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import eu.chargetime.ocpp.model.RequestWithId;
import eu.chargetime.ocpp.model.dataTypes.CustomDataType;
import eu.chargetime.ocpp.model.dataTypes.enums.ReservationUpdateStatusEnumType;
import eu.chargetime.ocpp.model.validation.RequiredValidator;
import eu.chargetime.ocpp.model.validation.Validator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "customData",
        "reservationId",
        "reservationUpdateStatus"
})
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class ReservationStatusUpdateRequest extends RequestWithId {

    private transient Validator<Object> requiredValidator = new RequiredValidator();

    /**
     * This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
     *
     */
    @JsonProperty("customData")
    public CustomDataType customData;
    /**
     * The ID of the reservation.
     *
     * (Required)
     *
     */
    @JsonProperty("reservationId")
    public Integer reservationId;
    /**
     * The updated reservation status.
     *
     * (Required)
     *
     */
    @JsonProperty("reservationUpdateStatus")
    public ReservationUpdateStatusEnumType reservationUpdateStatus;

    public ReservationStatusUpdateRequest(Integer reservationId, ReservationUpdateStatusEnumType reservationUpdateStatus) {
        requiredValidator.validate(reservationId);
        requiredValidator.validate(reservationUpdateStatus);
        this.reservationId = reservationId;
        this.reservationUpdateStatus = reservationUpdateStatus;
    }

    public void setCustomData(CustomDataType customData) {
        this.customData = customData;
    }

    public void setReservationId(Integer reservationId) {
        requiredValidator.validate(reservationId);
        this.reservationId = reservationId;
    }

    public void setReservationUpdateStatus(ReservationUpdateStatusEnumType reservationUpdateStatus) {
        requiredValidator.validate(reservationUpdateStatus);
        this.reservationUpdateStatus = reservationUpdateStatus;
    }

    @Override
    public boolean transactionRelated() {
        return false;
    }

    @Override
    public boolean validate() {
        return requiredValidator.safeValidate(reservationId)
                &&requiredValidator.safeValidate(reservationUpdateStatus);
    }
}
