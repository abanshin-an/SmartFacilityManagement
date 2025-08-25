package ru.a2n.sfm.person.controller;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.a2n.sfm.person.dto.ChangePasswordDto;
import ru.a2n.sfm.person.dto.PersonCreateDto;
import ru.a2n.sfm.person.dto.PersonFullDto;
import ru.a2n.sfm.person.dto.PersonListItemDto;

@SuppressWarnings("squid:S4449")
@RestController
@RequestMapping("/api/person")
@Tag(name = "User Management", description = "Endpoints for managing persons")
public interface PersonController {

    @GetMapping("")
    @Operation(summary = "Get persons list", description = "Get persons list")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "200", description = "Users list"),
                @ApiResponse(responseCode = "400", description = "Invalid input data"),
                @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
            })
    ResponseEntity<List<PersonListItemDto>> getAllPerson(
            @Parameter(description = "User registration data") @RequestParam(value = "name", required = false)
                    String name,
            @Parameter(description = "User registration data") @RequestParam(value = "limit", required = false)
                    Integer limit);

    @GetMapping("/{id}")
    @Operation(summary = "Get person info", description = "Get person description with ")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "200", description = "User data"),
                @ApiResponse(responseCode = "400", description = "Invalid input data"),
                @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
            })
    ResponseEntity<PersonFullDto> getById(
            @Parameter(description = "The ID of the person", in = ParameterIn.PATH, schema = @Schema(type = "string"))
                    String id);

    @PutMapping("/{id}/changePassword")
    @Operation(
            summary = "Change person password",
            description = "Change person with the provided old password login and password")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "200", description = "Password changed successfully"),
                @ApiResponse(responseCode = "400", description = "Invalid input data/old password"),
                @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
            })
    ResponseEntity<PersonFullDto> changePassword(
            @Parameter(description = "Password dto", required = true) @RequestBody ChangePasswordDto changePasswordDto);

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление существующего пользователя")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "200", description = "User deleted"),
                @ApiResponse(responseCode = "400", description = "Incorrect request", content = @Content),
                @ApiResponse(responseCode = "404", description = "User not found", content = @Content),
                @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
            })
    ResponseEntity<Void> deletePerson(
            @Parameter(
                            description = "The ID of the person to delete",
                            required = true,
                            in = ParameterIn.PATH,
                            schema = @Schema(type = "string"))
                    String id);

    @PostMapping("")
    @Operation(summary = "Add a new person", description = "Create a new person with the provided login and password")
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "User registered successfully",
                        content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = PersonCreateDto.class))
                        }),
                @ApiResponse(responseCode = "400", description = "Incorrect request", content = @Content),
                @ApiResponse(responseCode = "404", description = "User not found", content = @Content),
                @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
            })
    ResponseEntity<PersonFullDto> createPerson(
            @Parameter(description = "Person registration data", required = true, in = ParameterIn.PATH) @RequestBody
                    PersonCreateDto person);
}
