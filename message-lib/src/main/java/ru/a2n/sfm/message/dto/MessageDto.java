package ru.a2n.sfm.message.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.Builder;

@Builder
public record MessageDto(
        @Schema(
                        name = "message_id",
                        example = "01988059-b005-7480-82fb-1d64d045bb1e",
                        requiredMode = Schema.RequiredMode.REQUIRED)
                String messageId,
        @Schema(
                        name = "facilityId",
                        example = "01988059-b005-7480-82fb-1d64d045bb1e",
                        requiredMode = Schema.RequiredMode.NOT_REQUIRED)
                String facilityId,
        @Schema(name = "messageType", example = "Leak", requiredMode = Schema.RequiredMode.REQUIRED) String messageType,
        @Schema(name = "value", example = "21132", requiredMode = Schema.RequiredMode.NOT_REQUIRED) long value,
        @Schema(name = "unit", example = "percent", requiredMode = Schema.RequiredMode.NOT_REQUIRED) String unit,
        @Schema(name = "createdAt", example = "2025-08-07T22:00:01.0003", requiredMode = Schema.RequiredMode.REQUIRED)
                LocalDateTime createdAt,
        @Schema(
                        name = "referenceId",
                        example = "01988059-b005-7480-82fb-1d64d045bb1e",
                        requiredMode = Schema.RequiredMode.NOT_REQUIRED)
                String referenceId,
        @Schema(name = "json", example = "{\"aaa\":2}", requiredMode = Schema.RequiredMode.NOT_REQUIRED) String json) {
    public MessageDto {
        Objects.requireNonNull(messageType);
        MessageTypes.requireContains(messageType);
        createdAt = LocalDateTime.now();
    }
}
