package ru.a2n.sfm.agent.inbox;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.a2n.sfm.agent.entity.Inbox;

public interface InboxRepository extends JpaRepository<Inbox, String> {}
