package ru.a2n.sfm.action.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.a2n.sfm.action.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, String> {}
