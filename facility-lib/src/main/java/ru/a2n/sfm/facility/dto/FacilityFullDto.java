package ru.a2n.sfm.facility.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Objects;

public record FacilityFullDto(
        @Schema(
                        name = "id",
                        example = "0198b903-7f9f-7366-b05a-e04faf39b7dd",
                        requiredMode = Schema.RequiredMode.NOT_REQUIRED)
                String id,
        @Schema(name = "name", example = "Коворкинг 1905", requiredMode = Schema.RequiredMode.REQUIRED) String name,
        @Schema(name = "address", example = "г. Москва ул. 1905 года,д 1", requiredMode = Schema.RequiredMode.REQUIRED)
                String address) {
    public FacilityFullDto {
        Objects.requireNonNull(name);
        Objects.requireNonNull(address);
    }
}
