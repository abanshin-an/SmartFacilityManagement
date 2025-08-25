package ru.a2n.sfm.appointment.controller;

import static org.springframework.http.HttpStatus.OK;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.a2n.sfm.appointment.service.AppointmentService;

@SuppressWarnings("squid:S4449")
@RestController
public class AppointmentControllerImpl implements AppointmentController {

    private static final Logger logger = LoggerFactory.getLogger(AppointmentControllerImpl.class);

    private final AppointmentService service;

    public AppointmentControllerImpl(AppointmentService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<List<String>> getPersonIdListByFacilityIdAndResponsibility(
            String facilityId, String responsibilityName) {
        try {
            logger.info("Getting person id by Facility Id {} and Responsibility {} ", facilityId, responsibilityName);
            List<String> personIdList =
                    service.getPersonIdByFacilityIdAndResponsibility(facilityId, responsibilityName);
            return ResponseEntity.status(OK).body(personIdList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(List.of());
        }
    }
}
