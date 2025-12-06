package ru.a2n.sfm.message.dto;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public enum MessageGroup {
    ALARM,
    COMMAND,
    EVENT,
    UNKNOWN;

    private static final List<String> groups =
            Arrays.stream(MessageGroup.values()).map(Enum::toString).toList();

    public static MessageGroup getMessageType(String messageType) {
        if (messageType == null || messageType.isEmpty()) {
            return MessageGroup.UNKNOWN;
        }
        Optional<String> group = groups.stream().filter(messageType::startsWith).findFirst();
        return group.map(MessageGroup::valueOf).orElse(MessageGroup.UNKNOWN);
    }
}
