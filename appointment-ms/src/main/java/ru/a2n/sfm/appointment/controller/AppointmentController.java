package ru.a2n.sfm.appointment.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@SuppressWarnings("squid:S4449")
@RequestMapping("/api/appointment")
@Tag(name = "Appointment Management", description = "Endpoints for managing appointments")
public interface AppointmentController {

    @GetMapping("/getPersonList")
    @Operation(summary = "Get appointments list", description = "Get appointments list")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "200", description = "Appointments list"),
                @ApiResponse(responseCode = "400", description = "Invalid input data"),
                @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
            })
    ResponseEntity<List<String>> getPersonIdListByFacilityIdAndResponsibility(
            @Parameter(description = "Appointment registration data") @RequestParam(value = "facility_id")
                    String facilityId,
            @Parameter(description = "Appointment registration data") @RequestParam(value = "responsibility_name")
                    String responsibilityName);
}
