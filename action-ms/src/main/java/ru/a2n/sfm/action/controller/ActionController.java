package ru.a2n.sfm.action.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.a2n.sfm.message.dto.MessageDto;

@SuppressWarnings("squid:S4449")
@RestController
@RequestMapping("/api/action")
@Tag(name = "Action processing", description = "Endpoints for processing incoming messages")
public interface ActionController {

    @PostMapping("")
    @Operation(summary = "Add message", description = "Add message from agent to event inbox")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "200", description = "Message added"),
                @ApiResponse(responseCode = "400", description = "Invalid input data"),
                @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
            })
    ResponseEntity<Void> addMessage(
            @Parameter(description = "Message from agent", required = true) @RequestBody MessageDto messageDto);
}
