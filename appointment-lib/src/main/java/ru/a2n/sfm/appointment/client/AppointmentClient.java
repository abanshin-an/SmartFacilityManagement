package ru.a2n.sfm.appointment.client;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "appointment", url = "http://appointment-ms:8080")
public interface AppointmentClient {
    @GetMapping(value = "api/appointment/getPersonList", produces = "application/json")
    List<String> getPersonIdListByFacilityIdAndResponsibility(
            @RequestParam("facility_id") String facilityId,
            @RequestParam("responsibility_name") String responsibilityName);
}
