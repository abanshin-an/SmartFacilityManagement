package ru.a2n.sfm.agent.outbox;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.a2n.sfm.message.dto.MessageDto;
import ru.a2n.sfm.message.model.Status;

// @DataJpaTest
@SpringBootTest
class OutboxServiceTest {

    @Autowired
    private OutboxService service;

    @Autowired
    private OutboxRepository repository;

    @Test
    void should_save() {
        MessageDto expected = MockMessages.newMessageDummy();
        var saved = service.save(expected);
        var actual = repository.findById(saved.getId());
        assertThat(actual).isNotEmpty();
        var actualEntity = actual.get();
        assertThat(actualEntity.getId()).isEqualTo(saved.getId());
        assertThat(actualEntity.getSavedAt()).isNotNull();
        assertThat(actualEntity.getStatus()).isEqualTo(Status.SAVED);
    }
}
