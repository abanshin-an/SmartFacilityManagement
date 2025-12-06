package ru.a2n.sfm.facility.service;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.a2n.sfm.facility.dto.FacilityCreateDto;
import ru.a2n.sfm.facility.dto.FacilityFullDto;
import ru.a2n.sfm.facility.dto.FacilityListItemDto;
import ru.a2n.sfm.facility.mapper.FacilityMapper;
import ru.a2n.sfm.facility.model.Facility;
import ru.a2n.sfm.facility.repository.FacilityRepository;

@SuppressWarnings("squid:S4449")
@Service
public class FacilityService {

    private static final Logger logger = LoggerFactory.getLogger(FacilityService.class);

    private final FacilityRepository repository;
    private final FacilityMapper mapper;

    public FacilityService(FacilityRepository repository, FacilityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public List<FacilityListItemDto> getAllFacility(String name, int limit) {
        try {
            logger.info("getAllUsers {}", name);
            List<FacilityListItemDto> entities;
            if (name == null) {
                if (limit > 1000 || limit < 0) {
                    limit = 1000;
                }
                entities = repository.findAllFacility(limit);
            } else {
                entities = repository.findByNameContaining(name).stream()
                        .map(mapper::toFacilityListItemDto)
                        .toList();
            }
            logger.info("found {}", entities.size());
            return entities;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return List.of();
        }
    }

    public Optional<FacilityFullDto> getById(String id) {
        Facility facility = repository.findById(id).orElse(null);
        return mapFullDto(facility);
    }

    public boolean deleteFacility(String id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException exception) {
            return false;
        }
        return true;
    }

    @Transactional
    public Optional<FacilityFullDto> createFacility(FacilityCreateDto facilityCreateDto) {
        try {
            logger.info("create facility with name {}", facilityCreateDto.name());
            Facility facility = mapper.toFacility(facilityCreateDto);
            Facility createdFacility = repository.save(facility);
            return mapFullDto(createdFacility);
        } catch (Exception e) {
            logger.error("createUser exception {}", e.getMessage());
        }
        return Optional.empty();
    }

    private Optional<FacilityFullDto> mapFullDto(Facility facility) {
        return Optional.of(mapper.toFacilityFullDto(facility));
    }
}
