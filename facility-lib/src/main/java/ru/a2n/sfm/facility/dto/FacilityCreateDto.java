package ru.a2n.sfm.facility.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Objects;

public record FacilityCreateDto(
        @Schema(name = "name", example = "Коворкинг 1905", requiredMode = Schema.RequiredMode.REQUIRED) String name,
        @Schema(name = "address", example = "г. Москва ул. 1905 года,д 1", requiredMode = Schema.RequiredMode.REQUIRED)
                String address) {
    public FacilityCreateDto {
        Objects.requireNonNull(name);
        Objects.requireNonNull(address);
    }
}
