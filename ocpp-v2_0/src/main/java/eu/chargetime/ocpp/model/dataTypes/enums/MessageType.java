package eu.chargetime.ocpp.model.dataTypes.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum MessageType {

    CALL(2),
    CALL_RESULT(3),
    CALL_ERROR(4);

    private final int messageTypeId;

    MessageType(int messageTypeId) {
        this.messageTypeId = messageTypeId;
    }

    @JsonValue
    public int getMessageTypeId() {
        return messageTypeId;
    }

    public static MessageType fromMessageTypeId(int messageTypeId) {
        return Arrays.stream(MessageType.values())
            .filter(messageType -> messageType.getMessageTypeId() == messageTypeId)
            .findFirst()
            .orElseThrow(
                () -> new IllegalArgumentException("MessageType (id = %d) was not found".formatted(messageTypeId))
            );
    }
}
