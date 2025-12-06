package ru.a2n.sfm.appointment.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.a2n.sfm.appointment.model.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, String> {
    @Query(
            value =
                    "SELECT person_id FROM appointment_ms.appointment a join appointment_ms.responsibility r on (r.id = a.responsibility_id) WHERE facility_id=:facilityId and responsibility_name=:responsibilityName",
            nativeQuery = true)
    List<String> getPersonIdByFacilityIdAndResponsibility(String facilityId, String responsibilityName);
}
