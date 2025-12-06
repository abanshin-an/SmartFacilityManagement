package ru.a2n.sfm.agent.inbox;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.a2n.sfm.agent.entity.Inbox;
import ru.a2n.sfm.message.dto.MessageDto;

@Mapper(componentModel = "spring")
public interface InboxMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "savedAt", ignore = true)
    @Mapping(target = "sentAt", ignore = true)
    @Mapping(target = "status", ignore = true)
    Inbox toEntity(MessageDto source);
}
