package ru.a2n.sfm.appointment.client;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "appointment")
public interface AppointmentClient {
    @GetMapping(
            value = "api/appointment/getPersonList?facility_id={facility_id}&responsibility_name={responsibility_name}",
            produces = "application/json")
    List<String> getPersonIdListByFacilityIdAndResponsibility(
            @PathVariable("facility_id") String facilityId,
            @PathVariable("responsibility_name") String responsibilityName);
}
