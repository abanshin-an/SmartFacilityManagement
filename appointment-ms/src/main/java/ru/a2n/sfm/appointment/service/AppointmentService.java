package ru.a2n.sfm.appointment.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.a2n.sfm.appointment.repository.AppointmentRepository;

@SuppressWarnings("squid:S4449")
@Service
public class AppointmentService {

    private static final Logger logger = LoggerFactory.getLogger(AppointmentService.class);

    private final AppointmentRepository repository;

    public AppointmentService(AppointmentRepository repository) {
        this.repository = repository;
    }

    public List<String> getPersonIdByFacilityIdAndResponsibility(String facilityId, String responsibilityName) {
        logger.info(
                "getPersonIdByFacilityIdAndResponsibility(facilityId = {}, String responsibilityName = {} ",
                facilityId,
                responsibilityName);
        return repository.getPersonIdByFacilityIdAndResponsibility(facilityId, responsibilityName);
    }
}
