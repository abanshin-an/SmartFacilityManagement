package ru.a2n.sfm.agent.outbox;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.a2n.sfm.agent.entity.Outbox;
import ru.a2n.sfm.message.dto.MessageDto;
import ru.a2n.sfm.message.model.Status;

@Service
public class OutboxService {

    private final OutboxRepository repository;
    private final OutboxMapper mapper;

    public OutboxService(OutboxRepository repository, @Qualifier("outboxMapperImpl") OutboxMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Outbox save(MessageDto messageDto) {
        Outbox outbox = mapper.toEntity(messageDto);
        outbox.setStatus(Status.SAVED);
        return repository.save(outbox);
    }

    @Transactional
    public void sendAll() {
        // not implemented
    }

    public void send(int id) {
        // not implemented
    }
}
