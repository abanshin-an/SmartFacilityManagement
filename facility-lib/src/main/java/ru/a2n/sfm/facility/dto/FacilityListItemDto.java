package ru.a2n.sfm.facility.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Objects;

public record FacilityListItemDto(
        @Schema(
                        name = "id",
                        example = "01988059-b005-7480-82fb-1d64d045bb1e",
                        requiredMode = Schema.RequiredMode.NOT_REQUIRED)
                String id,
        @Schema(name = "name", example = "abc", requiredMode = Schema.RequiredMode.REQUIRED) String name) {
    public FacilityListItemDto {
        Objects.requireNonNull(name);
    }
}
