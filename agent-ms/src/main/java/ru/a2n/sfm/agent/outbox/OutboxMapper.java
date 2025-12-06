package ru.a2n.sfm.agent.outbox;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.a2n.sfm.agent.entity.Outbox;
import ru.a2n.sfm.message.dto.MessageDto;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OutboxMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "savedAt", ignore = true)
    @Mapping(target = "sentAt", ignore = true)
    @Mapping(target = "status", ignore = true)
    Outbox toEntity(MessageDto source);
}
