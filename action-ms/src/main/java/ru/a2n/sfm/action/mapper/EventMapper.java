package ru.a2n.sfm.action.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.a2n.sfm.action.model.Event;
import ru.a2n.sfm.message.dto.MessageDto;

@Mapper(componentModel = "spring")
@SuppressWarnings("java:S6813")
public interface EventMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "savedAt", ignore = true)
    @Mapping(target = "sentAt", ignore = true)
    @Mapping(target = "status", ignore = true)
    Event toEvent(MessageDto messageDto);
}
