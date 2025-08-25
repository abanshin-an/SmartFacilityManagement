package ru.a2n.sfm.appointment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.a2n.sfm.appointment.model.Responsibility;

public interface ResponsibilityRepository extends JpaRepository<Responsibility, String> {}
