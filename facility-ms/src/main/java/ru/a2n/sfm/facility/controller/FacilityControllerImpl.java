package ru.a2n.sfm.facility.controller;

import static org.springframework.http.HttpStatus.OK;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.a2n.sfm.facility.dto.FacilityCreateDto;
import ru.a2n.sfm.facility.dto.FacilityFullDto;
import ru.a2n.sfm.facility.dto.FacilityListItemDto;
import ru.a2n.sfm.facility.service.FacilityService;

@SuppressWarnings("squid:S4449")
@RestController
public class FacilityControllerImpl implements FacilityController {

    private static final Logger logger = LoggerFactory.getLogger(FacilityControllerImpl.class);

    private final FacilityService service;
    private final FacilityService facilityService;

    public FacilityControllerImpl(FacilityService service, FacilityService facilityService) {
        this.service = service;
        this.facilityService = facilityService;
    }

    @Override
    public ResponseEntity<List<FacilityListItemDto>> getAllFacility(String name, Integer limit) {
        try {
            if (limit == null) {
                limit = 10000;
            }
            var entities = service.getAllFacility(name, limit);
            if (entities.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return ResponseEntity.status(OK).body(entities);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(List.of());
        }
    }

    @Override
    public ResponseEntity<FacilityFullDto> getById(String id) {
        Optional<FacilityFullDto> facility = service.getById(id);
        return facility.map(value -> ResponseEntity.status(OK).body(value))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Override
    public ResponseEntity<Void> deleteFacility(String id) {
        if (facilityService.deleteFacility(id)) {
            return ResponseEntity.status(OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    public ResponseEntity<FacilityFullDto> createFacility(FacilityCreateDto facility) {
        logger.info("create facility with name {}", facility.name());
        Optional<FacilityFullDto> savedUser = service.createFacility(facility);
        return savedUser
                .map(value -> ResponseEntity.status(HttpStatus.CREATED).body(value))
                .orElseGet(() ->
                        ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }
}
