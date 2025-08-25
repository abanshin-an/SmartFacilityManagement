package ru.a2n.sfm.agent.outbox;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.a2n.sfm.agent.entity.Outbox;

public interface OutboxRepository extends JpaRepository<Outbox, String> {}
