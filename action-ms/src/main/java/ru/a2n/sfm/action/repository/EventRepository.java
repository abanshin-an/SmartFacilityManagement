package ru.a2n.sfm.action.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.a2n.sfm.action.model.Event;
import ru.a2n.sfm.message.model.Status;

@Repository
public interface EventRepository extends JpaRepository<Event, String> {

    List<Event> getByStatus(Status status);
}
