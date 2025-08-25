package ru.a2n.sfm.person.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Objects;

public record PersonListItemDto(
        @Schema(
                        name = "id",
                        example = "01988059-b005-7480-82fb-1d64d045bb1e",
                        requiredMode = Schema.RequiredMode.NOT_REQUIRED)
                String id,
        @Schema(name = "login", example = "abc", requiredMode = Schema.RequiredMode.REQUIRED) String login) {
    public PersonListItemDto {
        Objects.requireNonNull(login);
    }
}
