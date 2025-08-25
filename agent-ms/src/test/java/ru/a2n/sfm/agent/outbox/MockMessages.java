package ru.a2n.sfm.agent.outbox;

import ru.a2n.sfm.message.dto.MessageDto;

public class MockMessages {
    public static MessageDto newMessageDummy() {
        return new MessageDto(null, null, "EVENTTemperature", 25, "C", null, null, null);
    }

    public static MessageDto newMessage(String messageType, long value, String unit) {
        return new MessageDto(null, null, messageType, value, unit, null, null, null);
    }
}
