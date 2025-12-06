package ru.a2n.sfm.facility.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.a2n.sfm.facility.dto.FacilityCreateDto;
import ru.a2n.sfm.facility.dto.FacilityFullDto;
import ru.a2n.sfm.facility.dto.FacilityListItemDto;

@SuppressWarnings("squid:S4449")
@RestController
@RequestMapping("/api/facility")
@Tag(name = "Facility Management", description = "Endpoints for managing facilities")
public interface FacilityController {

    @GetMapping("")
    @Operation(summary = "Get facility list", description = "Get facility list")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "200", description = "Facility list"),
                @ApiResponse(responseCode = "400", description = "Invalid input data"),
                @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
            })
    ResponseEntity<List<FacilityListItemDto>> getAllFacility(
            @Parameter(description = "Facility registration data") @RequestParam(value = "name", required = false)
                    String name,
            @Parameter(description = "Facility registration data") @RequestParam(value = "limit", required = false)
                    Integer limit);

    @GetMapping("/{id}")
    @Operation(summary = "Get facility info", description = "Get facility description ")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "200", description = "Facility data"),
                @ApiResponse(responseCode = "400", description = "Invalid input data"),
                @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
            })
    ResponseEntity<FacilityFullDto> getById(
            @Parameter(
                            description = "The ID of the facility",
                            in = ParameterIn.PATH,
                            schema = @Schema(type = "integer", format = "int64"))
                    String id);

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление существующего объекта")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "200", description = "Facility deleted"),
                @ApiResponse(responseCode = "400", description = "Incorrect request", content = @Content),
                @ApiResponse(responseCode = "404", description = "Facility not found", content = @Content),
                @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
            })
    ResponseEntity<Void> deleteFacility(
            @Parameter(
                            description = "The ID of the facility to delete",
                            required = true,
                            in = ParameterIn.PATH,
                            schema = @Schema(type = "integer", format = "int64"))
                    String id);

    @PostMapping("")
    @Operation(summary = "Add a new facility", description = "Create a new facility with the provided name and address")
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Facility registered successfully",
                        content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = FacilityCreateDto.class))
                        }),
                @ApiResponse(responseCode = "400", description = "Incorrect request", content = @Content),
                @ApiResponse(responseCode = "404", description = "Facility not found", content = @Content),
                @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
            })
    ResponseEntity<FacilityFullDto> createFacility(
            @Parameter(description = "Facility registration data", required = true, in = ParameterIn.PATH) @RequestBody
                    FacilityCreateDto facilityCreateDto);
}
