package ru.a2n.sfm.message.dto;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class MessageTypes {

    private static final Set<String> set = new HashSet<>();

    private MessageTypes() {}

    public static void registerMessageTypes(Collection<String> messageTypes) {
        set.addAll(messageTypes.stream()
                .filter(x -> MessageGroup.getMessageType(x) != MessageGroup.UNKNOWN)
                .collect(Collectors.toSet()));
    }

    public static boolean contains(String messageType) {
        return set.contains(messageType);
    }

    public static void requireContains(String messageType) {
        if (!set.contains(messageType)) {
            throw new IllegalArgumentException(messageType);
        }
    }

    static {
        Alarms.register();
        Commands.register();
        Events.register();
    }
}
