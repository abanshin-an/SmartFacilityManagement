package ru.a2n.sfm.message;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.a2n.sfm.message.dto.MessageGroup.ALARM;
import static ru.a2n.sfm.message.dto.MessageGroup.COMMAND;
import static ru.a2n.sfm.message.dto.MessageGroup.EVENT;
import static ru.a2n.sfm.message.dto.MessageGroup.UNKNOWN;

import org.junit.jupiter.api.Test;
import ru.a2n.sfm.message.dto.MessageGroup;

class MessageTypeTest {

    @Test
    void should_getEventType() {
        assertThat(MessageGroup.getMessageType("ALARMLeak")).isEqualTo(ALARM);
        assertThat(MessageGroup.getMessageType("COMMANDDoorOpen")).isEqualTo(COMMAND);
        assertThat(MessageGroup.getMessageType("EVENT")).isEqualTo(EVENT);
        assertThat(MessageGroup.getMessageType("+EVENT")).isEqualTo(UNKNOWN);
        assertThat(MessageGroup.getMessageType("")).isEqualTo(UNKNOWN);
    }
}
