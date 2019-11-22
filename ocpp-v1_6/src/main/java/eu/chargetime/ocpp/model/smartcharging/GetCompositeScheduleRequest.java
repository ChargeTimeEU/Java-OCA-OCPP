package eu.chargetime.ocpp.model.smartcharging;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.utilities.MoreObjects;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@XmlRootElement
public class GetCompositeScheduleRequest implements Request {
    private Integer connectorId;
    private Integer duration;
    private ChangingRateUnitType changingRateUnitType;

    public GetCompositeScheduleRequest(Integer connectorId, Integer duration, ChangingRateUnitType changingRateUnitType) {
        this.connectorId = connectorId;
        this.duration = duration;
        this.changingRateUnitType = changingRateUnitType;
    }

    public Integer getConnectorId() {
        return connectorId;
    }

    @XmlElement
    public void setConnectorId(Integer connectorId) {
        if (connectorId == null || connectorId < 0) {
            throw new PropertyConstraintException(connectorId, "connectorId must be >= 0");
        }

        this.connectorId = connectorId;
    }

    public Integer getDuration() {
        return duration;
    }

    @XmlElement
    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public ChangingRateUnitType getChangingRateUnitType() {
        return changingRateUnitType;
    }

    @XmlElement
    public void setChangingRateUnitType(ChangingRateUnitType changingRateUnitType) {
        this.changingRateUnitType = changingRateUnitType;
    }

    @Override
    public boolean validate() {
        boolean valid = connectorId != null && connectorId >= 0;
        valid &= duration != null;
        valid &= changingRateUnitType != null;

        return valid;
    }

    @Override
    public boolean transactionRelated() {
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(connectorId, duration, changingRateUnitType);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("connectorId", connectorId)
                .add("duration", duration)
                .add("changingRateUnitType", changingRateUnitType)
                .add("isValid", validate())
                .toString();
    }
}
