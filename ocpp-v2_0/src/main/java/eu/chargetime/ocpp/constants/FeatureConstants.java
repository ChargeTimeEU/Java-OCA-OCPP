package eu.chargetime.ocpp.constants;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import static eu.chargetime.ocpp.util.EnumUtil.findByField;

public enum FeatureConstants {

    AUTHORIZE("Authorize"),
    BOOT_NOTIFICATION("BootNotification"),
    CANCEL_RESERVATION("CancelReservation"),
    CERTIFICATE_SIGNED("CertificateSigned"),
    CHANGE_AVAILABILITY("ChangeAvailability"),
    CLEAR_CACHE("ClearCache"),
    CLEAR_CHARGING_PROFILE("ClearChargingProfile"),
    CLEAR_DISPLAY_MESSAGE("ClearDisplayMessage"),
    CLEARED_CHARGING_LIMIT("ClearedChargingLimit"),
    CLEAR_VARIABLE_MONITORING("ClearVariableMonitoring"),
    COST_UPDATED("CostUpdated"),
    CUSTOMER_INFORMATION("CustomerInformation"),
    DATA_TRANSFER("DataTransfer"),
    DELETE_CERTIFICATE("DeleteCertificate"),
    FIRMWARE_STATUS_NOTIFICATION("FirmwareStatusNotification"),
    GET15118EV_CERTIFICATE("Get15118EVCertificate"),
    GET_BASE_REPORT("GetBaseReport"),
    GET_CERTIFICATE_STATUS("GetCertificateStatus"),
    GET_CHARGING_PROFILES("GetChargingProfiles"),
    GET_COMPOSITE_SCHEDULE("GetCompositeSchedule"),
    GET_DISPLAY_MESSAGES("GetDisplayMessages"),
    GET_INSTALLED_CERTIFICATE_IDS("GetInstalledCertificateIds"),
    GET_LOCAL_LIST_VERSION("GetLocalListVersion"),
    GET_LOG("GetLog"),
    GET_MONITORING_REPORT("GetMonitoringReport"),
    GET_REPORT("GetReport"),
    GET_TRANSACTION_STATUS("GetTransactionStatus"),
    GET_VARIABLES("GetVariables"),
    HEARTBEAT("Heartbeat"),
    INSTALL_CERTIFICATE("InstallCertificate"),
    LOG_STATUS_NOTIFICATION("LogStatusNotification"),
    METER_VALUES("MeterValues"),
    NOTIFY_CHARGING_LIMIT("NotifyChargingLimit"),
    NOTIFY_CUSTOMER_INFORMATION("NotifyCustomerInformation"),
    NOTIFY_DISPLAY_MESSAGES("NotifyDisplayMessages"),
    NOTIFY_EV_CHARGING_NEEDS("NotifyEVChargingNeeds"),
    NOTIFY_EV_CHARGING_SCHEDULE("NotifyEVChargingSchedule"),
    NOTIFY_EVENT("NotifyEvent"),
    NOTIFY_MONITORING_REPORT("NotifyMonitoringReport"),
    NOTIFY_REPORT("NotifyReport"),
    PUBLISH_FIRMWARE("PublishFirmware"),
    PUBLISH_FIRMWARE_STATUS_NOTIFICATION("PublishFirmwareStatusNotification"),
    REPORT_CHARGING_PROFILES("ReportChargingProfiles"),
    REQUEST_START_TRANSACTION("RequestStartTransaction"),
    REQUEST_STOP_TRANSACTION("RequestStopTransaction"),
    RESERVATION_STATUS_UPDATE("ReservationStatusUpdate"),
    RESERVE_NOW("ReserveNow"),
    RESET("Reset"),
    SECURITY_EVENT_NOTIFICATION("SecurityEventNotification"),
    SEND_LOCAL_LIST("SendLocalList"),
    SET_CHARGING_PROFILE("SetChargingProfile"),
    SET_DISPLAY_MESSAGE("SetDisplayMessage"),
    SET_MONITORING_BASE("SetMonitoringBase"),
    SET_MONITORING_LEVEL("SetMonitoringLevel"),
    SET_NETWORK_PROFILE("SetNetworkProfile"),
    SET_VARIABLE_MONITORING("SetVariableMonitoring"),
    SET_VARIABLES("SetVariables"),
    SIGN_CERTIFICATE("SignCertificate"),
    STATUS_NOTIFICATION("StatusNotification"),
    TRANSACTION_EVENT("TransactionEvent"),
    TRIGGER_MESSAGE("TriggerMessage"),
    UNLOCK_CONNECTOR("UnlockConnector"),
    UNPUBLISH_FIRMWARE("UnpublishFirmware"),
    UPDATE_FIRMWARE("UpdateFirmware");

    private final String value;

    FeatureConstants(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    @JsonValue
    public String value() {
        return this.value;
    }

    @JsonCreator
    public static FeatureConstants fromValue(String value) {
        return findByField(
                FeatureConstants.class,
                FeatureConstants::value,
                value
        );
    }
}
