package ru.a2n.sfm.agent.inbox;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.a2n.sfm.message.dto.MessageDto;

@RestController
@RequestMapping("/api/inbox")
@Tag(name = "Data from controller", description = "Endpoints for managing home controller")
public class InboxController {
    InboxService service;

    @PostMapping
    @Operation(summary = "To inbox", description = "Post message to inbox queue")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "200", description = "Put successful"),
                @ApiResponse(responseCode = "400", description = "Invalid input data"),
                @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
            })
    public void processAlert(@Parameter(description = "Message", required = true) @RequestBody MessageDto messageDto) {
        service.save(messageDto);
    }
}
