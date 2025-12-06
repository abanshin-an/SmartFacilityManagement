package ru.a2n.sfm.facility.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.a2n.sfm.facility.dto.FacilityListItemDto;
import ru.a2n.sfm.facility.model.Facility;

public interface FacilityRepository extends JpaRepository<Facility, String> {

    List<Facility> findByNameContaining(String name);

    @Query(value = "SELECT id, facility_name FROM facility LIMIT :limit", nativeQuery = true)
    List<FacilityListItemDto> findAllFacility(int limit);
}
