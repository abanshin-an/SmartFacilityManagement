package ru.a2n.sfm.agent.inbox;

import org.springframework.stereotype.Service;
import ru.a2n.sfm.message.dto.MessageDto;

@Service
public class InboxService {

    InboxRepository repository;

    InboxMapper mapper;

    public void save(MessageDto messageDto) {
        repository.save(mapper.toEntity(messageDto));
    }

    public void process(int id) {
        // not implemented
    }
}
